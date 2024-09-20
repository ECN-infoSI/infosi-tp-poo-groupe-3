/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author titou
 */
public class Guerrier extends Personnage {
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

    public Guerrier(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p, int nbFl){
        nom = n;
        ptVie = pV;
        degatAtt = dA;
        ptPar = pPar;
        pagePar = paPar;
        pageAtt = paAtt;
        distAttMax = dMax;
        pos = p;
    }
    
    /**
     *
     * @param g
     */
    
    public Guerrier(Guerrier g){
        nom = g.getNom();
        ptVie = g.getPtVie();
        degatAtt = g.getDegatAtt();
        ptPar = g.getPtPar();
        pagePar = g.getPagePar();
        pageAtt = g.getPageAtt();
        distAttMax = g.getDistAttMax();
        pos = new Point2D(g.getPos());
    }
    
    /**
     *
     */
    
    public Guerrier(){
        nom = "";
        ptVie = 0;
        degatAtt = 0;
        ptPar = 0;
        pagePar = 0;
        pageAtt = 0;
        distAttMax = 0;
        pos = new Point2D(0,0);
    }
    
    public void combattre(Creature c){
        
    }
}
