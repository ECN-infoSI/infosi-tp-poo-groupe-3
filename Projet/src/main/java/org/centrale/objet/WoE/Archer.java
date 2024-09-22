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
public class Archer extends Personnage{
    private int nbFleche;

    public int getNbFleche() {
        return nbFleche;
    }

    public void setNbFleche(int nbFleche) {
        this.nbFleche = nbFleche;
    }
    
      
    /**
     *
     * @param n
     * @param pV
     * @param dA
     * @param pPar
     * @param paAtt
     * @param paPar
     * @param dMax
     * @param p
     * @param nbFl
     */
        
    public Archer(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p, int nbFl){
        super(n, pV, dA, pPar, paAtt, paPar, dMax, p);
        nbFleche = nbFl;
    }
    
    /**
     *
     * @param a
     */
        
    public Archer(Archer a){
        super(a.getNom(), a.getPtVie(), a.getDegatAtt(), a.getPtPar(), a.getPageAtt(), a.getPagePar(), a.getDistAttMax(), new Point2D(a.getPos()));
        nbFleche = a.getNbFleche();
    }
    
    /**
     *
     */
    public Archer(){
        super();
        nbFleche = 0;
    }
    
    /**
     * 
     * @param c 
     * On commence par vérifier que le combat à distance est possible et que l'archer a au moins une flèche
     * On test la réussite de l'attaque
     * Si les ptVie tombent en dessous de 0 on les remets à 0.
     */
    public void combattre(Creature c){
        if ((this.pos.distance(c.pos)<=this.distAttMax)&&(this.pos.distance(c.pos)>1)&&(this.nbFleche>0)){
            this.nbFleche -= 1;
            Random genAl = new Random();
            int jetAtt = genAl.nextInt(99)+1;
            int deg = 0;
            if (jetAtt<=this.pageAtt){
                deg += this.degatAtt;          
            }
            c.setPtVie(deg);
            if (c.getPtVie() < 0){
                c.setPtVie(0);
            }
        }
    }
}
