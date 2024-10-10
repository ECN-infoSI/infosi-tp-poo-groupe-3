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
import org.centrale.bdonn.worldofecn.world.*;

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
     * On recupere les personnages, les objets et les monstres associes a la sauvegarde
     * On les ajoute dans le container du Monde
     */
    public void readWorld(Integer idJoueur, String nomPartie, String nomSauvegarde, World monde) {
        
        try {
            String sqlPerso = "SELECT nom, pv, pour_att, pt_att, pour_par, pt_par, dist_max, nb_fleche, pos_x, pos_y, type.nom"
                    + "FROM Partie JOIN Sauvegarde ON partie.id_partie = sauvegarde.id_partie"
                    + "JOIN Joueur ON joueur.id_joueur = partie.id_joueur"
                    + "JOIN Personnage ON personnage.id_sauve = sauvegarde.id_sauve"
                    + "JOIN Type ON personnage.id_type = type.id_type"
                    + "WHERE joueur.id_joueur = "+idJoueur+" AND partie.nom = "+nomPartie
                    +"AND sauvegarde.nom = "+nomSauvegarde;
            String sqlObjet = "SELECT nom, pos_x, pos_y, type.nom "
                    + "FROM Partie JOIN Sauvegarde ON partie.id_partie = sauvegarde.id_partie"
                    + "JOIN Joueur ON joueur.id_joueur = partie.id_joueur"
                    + "JOIN Objet ON objet.id_sauve = sauvegarde.id_sauve"
                    + "JOIN Type ON personnage.id_type = type.id_type"
                    + "WHERE joueur.id_joueur = "+idJoueur+" AND partie.nom = "+nomPartie
                    +"AND sauvegarde.nom = "+nomSauvegarde;
            String sqlMonstre = "SELECT FROM Partie JOIN Sauvegarde ON partie.id_partie = sauvegarde.id_partie"
                    + "JOIN Joueur ON joueur.id_joueur = partie.id_joueur"
                    + "JOIN Monstre ON monstre.id_sauve = sauvegarde.id_sauve"
                    + "JOIN Type ON personnage.id_type = type.id_type"
                    + "WHERE joueur.id_joueur = "+idJoueur+" AND partie.nom = "+nomPartie
                    +"AND sauvegarde.nom = "+nomSauvegarde;
            PreparedStatement stmt1 = this.connection.prepareStatement(sqlPerso);
            ResultSet rsPerso = stmt1.executeQuery();
            PreparedStatement stmt2 = this.connection.prepareStatement(sqlObjet);
            ResultSet rsObjet = stmt2.executeQuery();
            PreparedStatement stmt3 = this.connection.prepareStatement(sqlMonstre);
            ResultSet rsMonstre = stmt3.executeQuery();
            
            while (rsPerso.next()){
                String nom = rsPerso.getString(1);
                int pv = rsPerso.getInt(2);
                int pour_att = rsPerso.getInt(3);
                int pt_att = rsPerso.getInt(4);
                int pour_par = rsPerso.getInt(5);
                int pt_par = rsPerso.getInt(6);
                int dist_max = rsPerso.getInt(7);
                int nb_fleches = rsPerso.getInt(8);
                int pos_x = rsPerso.getInt(9);
                int pos_y = rsPerso.getInt(10);
                String type = rsPerso.getString(11);
                Personnage item = null;
                switch (type){
                    case "Guerrier":
                        item = new Guerrier(nom, pv, pt_att, pt_par, pour_att, pour_par, dist_max, monde);
                        break;
                    case "Archer":
                        item = new Archer(nom, pv, pt_att, pt_par, pour_att, pour_par, dist_max, monde, nb_fleches);
                        break;
                    default:
                        item = new Paysan(nom, pv, pt_att, pt_par, pour_att, pour_par, dist_max, monde);
                        break;
                }
                Point2D pos = new Point2D(pos_x, pos_y);
                item.setPosition(pos);
                monde.addElement(item);
            }
            
            while (rsObjet.next()){
                String nom = rsObjet.getString(1);
                int pos_x = rsObjet.getInt(2);
                int pos_y = rsObjet.getInt(3);
                String type = rsObjet.getString(4);
                Objet item = null;
                switch (type){
                    case "Epee":
                        item = new Epee(nom, monde);
                        break;
                    default:
                        item = new PotionSoin(nom, monde);
                        break;
                }
                Point2D pos = new Point2D(pos_x, pos_y);
                item.setPosition(pos);
                monde.addElement(item);
            }
            
            while (rsMonstre.next()){
                int pv = rsMonstre.getInt(1);
                int pour_att = rsMonstre.getInt(2);
                int pt_att = rsMonstre.getInt(3);
                int pour_par = rsMonstre.getInt(4);
                int pos_x = rsMonstre.getInt(5);
                int pos_y = rsMonstre.getInt(6);
                String type = rsMonstre.getString(7);
                Monstre item = null;
                switch (type){
                    case "Loup":
                        item = new Loup(pv, pt_att, pour_att, pour_par, monde);
                        break;
                    default:
                        item = new Lapin(pv, pt_att, pour_att, pour_par, monde);
                        break;
                }
                Point2D pos = new Point2D(pos_x, pos_y);
                item.setPosition(pos);
                monde.addElement(item);
            }
            
        } catch (SQLException ex) {
                Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
