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
     */
    public Integer getPlayerID(String nomJoueur, String password) {
        if (this.login.equals(nomJoueur) && this.password.equals(password)){
            try{
            String sql = "SELECT id FROM joueur WHERE nom_code="+nomJoueur;
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
     * On commence par récupérer id_partie
     * On crée une nouvelle ligne dans la table sauvegarde
     * On récupère id_sauv de la nouvelle sauvegarde
     * On rajoute dans les tables personnage, monstre et objet des copies des données avec lesquels on jouait avec le nouvel id_sauv
     */
    public void saveWorld(Integer idJoueur, String nomPartie, String nomSauvegarde, World monde) {
        try{
            String sql1 = "SELECT id_partie FROM partie WHERE nomPartie = "+nomPartie+" and id_joueur = "+idJoueur;
            PreparedStatement stmt1 = this.connection.prepareStatement(sql1);
            ResultSet rs = stmt1.executeQuery();
            int idPartie = rs.getInt("id_partie");
            boolean rapide = (nomSauvegarde == null);
            String sql2 = "INSERT INTO sauvegarde (rapide, nom, id_partie) VALUES ("+rapide+", "+nomSauvegarde+", "+idPartie+")";
            PreparedStatement stmt2 = this.connection.prepareStatement(sql2);
            stmt2.executeUpdate();
            String sql3 = "SELECT id_sauv FROM sauvegarde WHERE nom = "+nomSauvegarde+" and id_partie = "+idPartie;
            PreparedStatement stmt3 = this.connection.prepareStatement(sql3);
            ResultSet rs3 = stmt3.executeQuery();
            int idSauv = rs.getInt("id_sauv");
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
}
