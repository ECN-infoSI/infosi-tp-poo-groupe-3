/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Random;
/**
 *
 * @author thomas
 */

public class Personnage extends Creature {

    /**
     * Nom du personnage
     */
    protected String nom;

    /**
     * Portée maximale d'une attaque
     */
    protected int distAttMax;

    public int getDistAttMax() {
        return distAttMax;
    }

    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }

    
    
    /** 
     * Constructeur de l'objet Personnage.
     * @param n     Nom du personnage
     * @param pV    Nombre de points de vie du personnage
     * @param dA    Nombre de dégats que le personnage peut infliger en attaquant
     * @param pPar  Nombre de points d'aptitude à parer du personnage
     * @param paAtt Pourcentage d'attaque du personnage
     * @param paPar Pourcentage de parade du personnage
     * @param dMax  Distance maximale d'attaque du personnage
     * @param p     Position du personnage dans le monde
     */
    public Personnage(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p){
        nom = n;
        distAttMax = dMax;
    }
    
    /**
     * Constructeur de l'objet personnage
     * @param perso
     */
        
    public Personnage(Personnage perso){
        nom = perso.getNom();
        ptVie = perso.getPtVie();
        degatAtt = perso.getDegatAtt();
        ptPar = perso.getPtPar();
        pagePar = perso.getPagePar();
        pageAtt = perso.getPageAtt();
        distAttMax = perso.getDistAttMax();
        pos = new Point2D(perso.getPos());
    }
    
    /**
     * Constructeur de l'objet personnage
     */
        
    public Personnage(){
        nom = "";
        ptVie = 0;
        degatAtt = 0;
        ptPar = 0;
        pagePar = 0;
        pageAtt = 0;
        distAttMax = 0;
        pos = new Point2D(0,0);
    }
    
    /**
     *
     * @return le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
     * Déplace le personnage sur une case adjacente à sa position.
     */
    public void deplace(){
        Random genAl = new Random();
        int xAl = genAl.nextInt(2)-1;
        int yAl = genAl.nextInt(2)-1;
        while ((xAl == 0)&&(yAl == 0)){
            xAl = genAl.nextInt(2)-1;
            yAl = genAl.nextInt(2)-1;
        }
        pos.Translate(xAl, yAl);
    }
    
    /**
     * Affiche les caractéristiques du personnage
     */
    public void affiche(){
        System.out.println("nom : "+nom+"\n;points de vie : "+ptVie);
        System.out.println("\n;degats d'attaque : "+degatAtt);
        System.out.println("\n;points de parade : "+ptPar);
        System.out.println("\n;pourcentage de parade : "+pagePar);
        System.out.println("\n;pourcentage d'attaque : "+pageAtt);
        System.out.println("\n;distance d'attaque maximale : "+distAttMax);
        pos.affiche();
    }
}
