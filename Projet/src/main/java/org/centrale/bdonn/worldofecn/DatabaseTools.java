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
     * On recupere les personnages, les objets et les monstres associes a la sauvegarde
     * On les ajoute dans le container du Monde
     */
    public void readWorld(Integer idJoueur, String nomPartie, String nomSauvegarde, World monde) {
        
        try {
            String sqlPerso = "SELECT FROM Partie JOIN Sauvegarde ON partie.id_partie = sauvegarde.id_partie"
                    + "JOIN Joueur ON joueur.id_joueur = partie.id_joueur"
                    + "JOIN Personnage ON personnage.id_sauve = sauvegarde.id_sauve"
                    + "WHERE joueur.id_joueur = "+idJoueur+" AND partie.nom = "+nomPartie
                    +"AND sauvegarde.nom = "+nomSauvegarde;
            String sqlObjet = "SELECT FROM Partie JOIN Sauvegarde ON partie.id_partie = sauvegarde.id_partie"
                    + "JOIN Joueur ON joueur.id_joueur = partie.id_joueur"
                    + "JOIN Objet ON objet.id_sauve = sauvegarde.id_sauve"
                    + "WHERE joueur.id_joueur = "+idJoueur+" AND partie.nom = "+nomPartie
                    +"AND sauvegarde.nom = "+nomSauvegarde;
            String sqlMonstre = "SELECT FROM Partie JOIN Sauvegarde ON partie.id_partie = sauvegarde.id_partie"
                    + "JOIN Joueur ON joueur.id_joueur = partie.id_joueur"
                    + "JOIN Monstre ON monstre.id_sauve = sauvegarde.id_sauve"
                    + "WHERE joueur.id_joueur = "+idJoueur+" AND partie.nom = "+nomPartie
                    +"AND sauvegarde.nom = "+nomSauvegarde;
            PreparedStatement stmt1 = this.connection.prepareStatement(sqlPerso);
            ResultSet rsPerso = stmt1.executeQuery();
            PreparedStatement stmt2 = this.connection.prepareStatement(sqlObjet);
            ResultSet rsObjet = stmt2.executeQuery();
            PreparedStatement stmt3 = this.connection.prepareStatement(sqlMonstre);
            ResultSet rsMonstre = stmt3.executeQuery();
            
            while (rsPerso.next()){
                String nom = rsPerso.getString(2);
                int pv = rsPerso.getInt(5);
                int pour_att = rsPerso.getInt(6);
                int pt_att = rsPerso.getInt(7);
                int pour_par = rsPerso.getInt(8);
                int pt_par = rsPerso.getInt(9);
                int dist_max = rsPerso.getInt(10);
                int nb_fleches = rsPerso.getInt(11);
                int pos_x = rsPerso.getInt(12);
                int pos_y = rsPerso.getInt(13);
                String type = rsPerso.getString(14);
                Personnage item = null;
                switch (type){
                    case "Guerrier":
                        item = new Guerrier(monde);
                        break;
                    case "Archer":
                        item = new Archer(monde);
                        break;
                    default:
                        item = new Paysan(monde);
                        break;
                }
            }
        } catch (SQLException ex) {
                Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
