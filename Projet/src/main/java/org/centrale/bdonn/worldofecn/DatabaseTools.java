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
            ResourceBundle properties = ResourceBundle.getBundle(DatabaseTools.class.getPackage().getName()+ ".database");

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
            return null;
            }
        }else{
            return null;
        }
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
     * On regarde quelle est la classe de l'element de jeu qu'on regarde
     * On extrait les donnees selon la classe de l'element de jeu
     * On rajoute dans les tables personnage, monstre et objet des copies des donnees avec lesquels on jouait avec le nouvel id_sauv
     */
    public void saveWorld(Integer idJoueur, String nomPartie, String nomSauvegarde, World monde) {
        try{
            String sql11 = "SELECT nom FROM partie WHERE id_joueur = "+idJoueur+" and nom = "+nomPartie+";";
            PreparedStatement stmt11 = this.connection.prepareStatement(sql11);
            ResultSet rs11 = stmt11.executeQuery();
            if (!rs11.next()){
                String sql12 = "INSERT INTO partie (nom, id_joueur) VALUES ("+nomPartie+", "+idJoueur+");";
                PreparedStatement stmt12 = this.connection.prepareStatement(sql12);
                stmt12.executeUpdate();
            }
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
            for (int i = 0 ; i<= monde.listElements.size() ; i++){
                ElementDeJeu e = monde.player.personnage;
                if (i<monde.listElements.size()){
                    e = monde.listElements.get(i);
                }
                Class classe = e.getClass();
                String nomclasse = classe.getName();
                int indiceclasse = 0;
                switch (nomclasse){
                    case "Paysan":
                        indiceclasse = 1;
                        break;
                    case "Guerrier":
                        indiceclasse = 2;
                        break;
                    case "Archer":
                        indiceclasse = 3;
                        break;
                    case "Loup":
                        indiceclasse = 4;
                        break;
                    case "Lapin":
                        indiceclasse = 5;
                        break;
                    case "PotionSoin":
                        indiceclasse = 6;
                        break;
                    case "Epee":
                        indiceclasse = 7;
                        break;
                }
                switch (nomclasse){
                    case "Guerrier":
                        /** on recupere les attributs des personnages*/
                        String nomg = ((Personnage)e).getNom();
                        Integer pvg = ((Personnage)e).getPtVie();
                        Integer pourattg = ((Personnage)e).getPageAtt();
                        Integer ptattg = ((Personnage)e).getDegaAtt();
                        Integer pourparg = ((Personnage)e).getPagePar();
                        Integer ptparg = ((Personnage)e).getPtPar();
                        Integer distmaxg = ((Personnage)e).getDistAttMax();
                        Integer posxg = ((Personnage)e).getPosition().getX();
                        Integer posyg = ((Personnage)e).getPosition().getY();
                        String sql4 = "INSERT INTO personnage (nom, id_partie, id_sauv, pv, pour_att, pt_att, pour_par, pt_par, dist_max, nb_fleches, pos_x, pos_y, id_type) "
                                + "VALUES ("+nomg+", "+idPartie+", "+idSauv+", "+pvg+", "+pourattg+", "+ptattg+", "+pourparg+", "+ptparg+", "+distmaxg+", NULL, "+posxg+", "+posyg+", "+indiceclasse+");";
                        PreparedStatement stmt4 = this.connection.prepareStatement(sql4);
                        stmt4.executeUpdate();
                        break;
                    case "Paysan":
                        /** on recupere les attributs des personnages*/
                        String nomp = ((Personnage)e).getNom();
                        Integer pvp = ((Personnage)e).getPtVie();
                        Integer pourattp = ((Personnage)e).getPageAtt();
                        Integer ptattp = ((Personnage)e).getDegaAtt();
                        Integer pourparp = ((Personnage)e).getPagePar();
                        Integer ptparp = ((Personnage)e).getPtPar();
                        Integer distmaxp = ((Personnage)e).getDistAttMax();
                        Integer posxp = ((Personnage)e).getPosition().getX();
                        Integer posyp = ((Personnage)e).getPosition().getY();
                        String sql8 = "INSERT INTO personnage (nom, id_partie, id_sauv, pv, pour_att, pt_att, pour_par, pt_par, dist_max, nb_fleches, pos_x, pos_y, id_type) "
                                + "VALUES ("+nomp+", "+idPartie+", "+idSauv+", "+pvp+", "+pourattp+", "+ptattp+", "+pourparp+", "+ptparp+", "+distmaxp+", NULL, "+posxp+", "+posyp+", "+indiceclasse+");";
                        PreparedStatement stmt8 = this.connection.prepareStatement(sql8);
                        stmt8.executeUpdate();
                        break;
                    case "Archer":
                        /** on recupere les attributs des personnages*/
                        String noma = ((Personnage)e).getNom();
                        Integer pva = ((Personnage)e).getPtVie();
                        Integer pouratta = ((Personnage)e).getPageAtt();
                        Integer ptatta = ((Personnage)e).getDegaAtt();
                        Integer pourpara = ((Personnage)e).getPagePar();
                        Integer ptpara = ((Personnage)e).getPtPar();
                        Integer distmaxa = ((Personnage)e).getDistAttMax();
                        Integer nbflechesa = ((Archer)e).getNbFleche();
                        Integer posxa = ((Personnage)e).getPosition().getX();
                        Integer posya = ((Personnage)e).getPosition().getY();
                        String sql7 = "INSERT INTO personnage (nom, id_partie, id_sauv, pv, pour_att, pt_att, pour_par, pt_par, dist_max, nb_fleches, pos_x, pos_y, id_type) "
                                + "VALUES ("+noma+", "+idPartie+", "+idSauv+", "+pva+", "+pouratta+", "+ptatta+", "+pourpara+", "+ptpara+", "+distmaxa+", "+nbflechesa+", "+posxa+", "+posya+", "+indiceclasse+");";
                        PreparedStatement stmt7 = this.connection.prepareStatement(sql7);
                        stmt7.executeUpdate();
                        break;
                    case "Loup":
                        /** on recupere les attributs des monstres*/
                        Integer pvlo = ((Monstre)e).getPtVie();
                        Integer pourattlo = ((Monstre)e).getPageAtt();
                        Integer ptattlo = ((Monstre)e).getDegaAtt();
                        Integer pourparlo = ((Monstre)e).getPagePar();
                        Integer posxlo = ((Monstre)e).getPosition().getX();
                        Integer posylo = ((Monstre)e).getPosition().getY();
                        String sql9 = "INSERT INTO monstre (id_partie, id_sauv, pv, pour_att, pt_att, pour_par, pos_x, pos_y, id_type)"
                                + "VALUES ("+idPartie+", "+idSauv+", "+pvlo+", "+pourattlo+", "+ptattlo+", "+pourparlo+", "+posxlo+", "+posylo+", "+indiceclasse+") ";
                        PreparedStatement stmt9 = this.connection.prepareStatement(sql9);
                        stmt9.executeUpdate();
                        break;
                    case "Lapin":
                        /** on recupere les attributs des monstres*/
                        Integer pvla = ((Monstre)e).getPtVie();
                        Integer pourattla = ((Monstre)e).getPageAtt();
                        Integer ptattla = ((Monstre)e).getDegaAtt();
                        Integer pourparla = ((Monstre)e).getPagePar();
                        Integer posxla = ((Monstre)e).getPosition().getX();
                        Integer posyla = ((Monstre)e).getPosition().getY();
                        String sql10 = "INSERT INTO monstre (id_partie, id_sauv, pv, pour_att, pt_att, pour_par, pos_x, pos_y, id_type)"
                                + "VALUES ("+idPartie+", "+idSauv+", "+pvla+", "+pourattla+", "+ptattla+", "+pourparla+", "+posxla+", "+posyla+", "+indiceclasse+") ";
                        PreparedStatement stmt10 = this.connection.prepareStatement(sql10);
                        stmt10.executeUpdate();
                        break;
                    case "PotionSoin":
                        /** on recupere les attributs des objets*/
                        String nompo = ((Objet)e).getNom();
                        Integer posxpo = ((Objet)e).getPosition().getX();
                        Integer posypo = ((Objet)e).getPosition().getY();
                        String sql6 = "INSERT INTO objet (nom, id_partie, id_sauv, id_perso, pos_x, pos_y, id_type)"
                                + "VALUES ("+nompo+", "+idPartie+", "+idSauv+", id_perso, "+posxpo+", "+posypo+", "+indiceclasse+") ";
                        PreparedStatement stmt6 = this.connection.prepareStatement(sql6);
                        stmt6.executeUpdate();
                        break;
                    case "Epee":
                        /** on recupere les attributs des objets*/
                        String nomep = ((Objet)e).getNom();
                        Integer posxep = ((Objet)e).getPosition().getX();
                        Integer posyep = ((Objet)e).getPosition().getY();
                        String sql13 = "INSERT INTO objet (nom, id_partie, id_sauv, id_perso, pos_x, pos_y, id_type)"
                                + "VALUES ("+nomep+", "+idPartie+", "+idSauv+", id_perso, "+posxep+", "+posyep+", "+indiceclasse+") ";
                        PreparedStatement stmt13 = this.connection.prepareStatement(sql13);
                        stmt13.executeUpdate();
                        break;
                }
            }
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
            String sqlMonstre = "SELECT pv, pour_att, pt_att, pour_par, pos_x, pos_y, type.nom"
                    + "FROM Partie JOIN Sauvegarde ON partie.id_partie = sauvegarde.id_partie"
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
