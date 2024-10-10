/* --------------------------------------------------------------------------------
 * WoE Tools
 * 
 * Ecole Centrale Nantes - Septembre 2022
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin
 * -------------------------------------------------------------------------------- */
package org.centrale.bdonn.worldofecn;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import org.centrale.bdonn.worldofecn.world.World;

/**
 *
 * @author ECN
 */
public class DatabaseTools {

    private String login;
    private String password;
    private String url;
    private Connection connection;

    /**
     * Load infos
     */
    public DatabaseTools() {
        try {
            // Get Properties file
            ResourceBundle properties = ResourceBundle.getBundle(DatabaseTools.class.getPackage().getName() + ".database");

            // USE config parameters
            login = properties.getString("login");
            password = properties.getString("password");
            String server = properties.getString("server");
            String database = properties.getString("database");
            url = "jdbc:postgresql://" + server + "/" + database;

            // Mount driver
            Driver driver = DriverManager.getDriver(url);
            if (driver == null) {
                Class.forName("org.postgresql.Driver");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection = null;
    }

    /**
     * Get connection to the database
     */
    public void connect() {
        if (this.connection == null) {
            try {
                this.connection = DriverManager.getConnection(url, login, password);
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Disconnect from database
     */
    public void disconnect() {
        if (this.connection != null) {
            try {
                this.connection.close();
                this.connection = null;
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * get Player ID
     * @param nomJoueur
     * @param password
     * @return
     * On vérifie que le login et le mot de passe sont corrects
     * On va chercher puis on renvoie l'id_joueur
     */
    public Integer getPlayerID(String nomJoueur, String password) {
        if (this.login.equals(nomJoueur) && this.password.equals(password)){
            try{
            String sql = "SELECT id_joueur FROM joueur WHERE nom_code="+nomJoueur+";";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            int id = rs.getInt("id_joueur");
            return id;
            } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * save world to database
     * @param idJoueur
     * @param nomPartie
     * @param nomSauvegarde
     * @param monde
     * On commence par recuperer id_partie
     * On cree une nouvelle ligne dans la table sauvegarde
     * On recupere id_sauv de la nouvelle sauvegarde
     * On rajoute dans les tables personnage, monstre et objet des copies des données avec lesquels on jouait avec le nouvel id_sauv
     */
    public void saveWorld(Integer idJoueur, String nomPartie, String nomSauvegarde, World monde) {
        try{
            String sql1 = "SELECT id_partie FROM partie WHERE nom = "+nomPartie+" and id_joueur = "+idJoueur+";";
            PreparedStatement stmt1 = this.connection.prepareStatement(sql1);
            ResultSet rs = stmt1.executeQuery();
            int idPartie = rs.getInt("id_partie");
            boolean rapide = (nomSauvegarde == null);
            String sql2 = "INSERT INTO sauvegarde (rapide, nom, id_partie) VALUES ("+rapide+", "+nomSauvegarde+", "+idPartie+");";
            PreparedStatement stmt2 = this.connection.prepareStatement(sql2);
            stmt2.executeUpdate();
            String sql3 = "SELECT id_sauv FROM sauvegarde WHERE nom = "+nomSauvegarde+" and id_partie = "+idPartie+";";
            PreparedStatement stmt3 = this.connection.prepareStatement(sql3);
            ResultSet rs3 = stmt3.executeQuery();
            int idSauv = rs3.getInt("id_sauv");
            String sql4 = "INSERT INTO personnage (nom, id_partie, id_sauv, pv, pour_att, pt_att, pour_par, pt_par, dist_max, nb_fleches, pos_x, pos_y, id_type) "
                    + "SELECT nom, id_partie, "+idSauv+", pv, pour_att, pt_att, pour_par, pt_par, dist_max, nb_fleches, pos_x, pos_y, id_type"
                    + "FROM personnage WHERE id_sauv IS NULL;";
            PreparedStatement stmt4 = this.connection.prepareStatement(sql4);
            stmt4.executeUpdate();
            String sql5 = "INSERT INTO monstre (id_partie, id_sauv, pv, pour_att, pt_att, pour_par, pos_x, pos_y, id_type) "
                    + "SELECT id_partie, "+idSauv+", pv, pour_att, pt_att, pour_par, pos_x, pos_y, id_type "
                    + "FROM monstre WHERE id_sauv IS NULL;";
            PreparedStatement stmt5 = this.connection.prepareStatement(sql5);
            stmt5.executeUpdate();
            String sql6 = "INSERT INTO objet (nom, id_partie, id_sauv, id_perso, pos_x, pos_y, id_type)VALUES "
                    + "SELECT nom, id_partie, "+idSauv+", id_perso, pos_x, pos_y, id_type "
                    + "FROM objet WHERE id_sauv IS NULL;";
            PreparedStatement stmt6 = this.connection.prepareStatement(sql6);
            stmt6.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * get world from database
     * @param idJoueur
     * @param nomPartie
     * @param nomSauvegarde
     * @param monde
     */
    public void readWorld(Integer idJoueur, String nomPartie, String nomSauvegarde, World monde) {

    }
    /**
     * delete world from database
     * @param idJoueur
     * @param nomPartie
     * @param nomSauvegarde
     * On recupere l'id de la sauvegarde concernee
     * On supprime tous les personnages, monstres et objets qui sont enregistres avec cet id
     * On supprime la ligne correspondante dans la bdd sauvegarde
     */
    public void removeWorld(Integer idJoueur,String nomPartie,String nomSauvegarde){
        try{
            String sql1 = "SELECT id_sauv FROM partie NATURAL JOIN sauvegarde WHERE partie.nom = "+nomPartie+" and id_joueur = "+idJoueur+" and sauvegarde.nom = "+nomSauvegarde+";";
            PreparedStatement stmt1 = this.connection.prepareStatement(sql1);
            ResultSet rs = stmt1.executeQuery();
            int idSauv = rs.getInt("id_sauv");
            String sql2 = "DELETE FROM personnage WHERE id_sauv = "+idSauv+";";
            PreparedStatement stmt2 = this.connection.prepareStatement(sql2);
            stmt2.executeUpdate();
            String sql3 = "DELETE FROM monstre WHERE id_sauv = "+idSauv+";";
            PreparedStatement stmt3 = this.connection.prepareStatement(sql3);
            stmt3.executeUpdate();
            String sql4 = "DELETE FROM objet WHERE id_sauv = "+idSauv+";";
            PreparedStatement stmt4 = this.connection.prepareStatement(sql4);
            stmt4.executeUpdate();
            String sql5 = "DELETE FROM sauvegarde WHERE id_sauv = "+idSauv+";";
            PreparedStatement stmt5 = this.connection.prepareStatement(sql5);
            stmt5.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
    }
}
