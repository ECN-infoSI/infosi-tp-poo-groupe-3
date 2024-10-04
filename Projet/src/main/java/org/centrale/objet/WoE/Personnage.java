/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

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

    /**
     *
     * @return
     */
    public int getDistAttMax() {
        return distAttMax;
    }

    /**
     *
     * @param distAttMax
     */
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
        super(pV, dA, pPar, paAtt, paPar, p);
        nom = n;
        distAttMax = dMax;
    }
    
    /**
     * Constructeur de l'objet personnage
     * @param p
     */
        
    public Personnage(Personnage p){
        super(p.getPtVie(), p.getDegatAtt(), p.getPtPar(), p.getPagePar(), p.getPageAtt(), new Point2D(p.getPos()));
        nom = p.getNom();
        distAttMax = p.getDistAttMax();
    }
    
    /**
     * Constructeur de l'objet personnage
     */
        
    public Personnage(){
        super();
        nom = "";
        distAttMax = 0;
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
     * Affiche les caractéristiques du personnage
     */
    public void affiche(){
        System.out.println("nom : "+nom+"\npoints de vie : "+ptVie);
        System.out.println("degats d'attaque : "+degatAtt);
        System.out.println("points de parade : "+ptPar);
        System.out.println("pourcentage de parade : "+pagePar);
        System.out.println("pourcentage d'attaque : "+pageAtt);
        System.out.println("distance d'attaque maximale : "+distAttMax);
        pos.affiche();
    }
}
