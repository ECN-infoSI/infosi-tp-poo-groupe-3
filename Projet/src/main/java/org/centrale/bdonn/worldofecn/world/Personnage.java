/* --------------------------------------------------------------------------------
 * WoE
 * 
 * Ecole Centrale Nantes - Septembre 2022
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin
 * -------------------------------------------------------------------------------- */
package org.centrale.bdonn.worldofecn.world;
import java.sql.Connection;

/**
 *
 * @author ECN
 */
public abstract class Personnage extends Creature {
    /**
     * Nom du personnage
     */
    protected String nom;

    /**
     * Portée maximale d'une attaque
     */
    protected int distAttMax;

    public String getNom() {
        return nom;
    }

    public int getDistAttMax() {
        return distAttMax;
    }

    public int getPtVie() {
        return ptVie;
    }

    public int getDegatAtt() {
        return degatAtt;
    }

    public int getPtPar() {
        return ptPar;
    }

    public int getPagePar() {
        return pagePar;
    }

    public int getPageAtt() {
        return pageAtt;
    }

    public int getDegaAtt() {
        return degaAtt;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }

    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    public void setDegatAtt(int degatAtt) {
        this.degatAtt = degatAtt;
    }

    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    public void setDegaAtt(int degaAtt) {
        this.degaAtt = degaAtt;
    }
    
    /**
     *
     * @param world
     */
    public Personnage(World world) {
        super(world);
        nom = "";
        distAttMax = 0;
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
     * @param monde     Position du personnage dans le monde
     */
    public Personnage(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, World monde){
        super(pV, dA, pPar, paAtt, paPar, monde);
        nom = n;
        distAttMax = dMax;
    }
    
}
