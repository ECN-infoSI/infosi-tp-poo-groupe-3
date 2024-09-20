/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author thomas
 */
public class Archer extends Personnage{
    private int nbFleche;
    
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
        nom = n;
        ptVie = pV;
        degatAtt = dA;
        ptPar = pPar;
        pagePar = paPar;
        pageAtt = paAtt;
        distAttMax = dMax;
        pos = p;
        nbFleche = nbFl;
    }
    
    /**
     *
     * @param a
     */
        
    public Archer(Archer a){
        nom = a.getNom();
        ptVie = a.getPtVie();
        degatAtt = a.getDegatAtt();
        ptPar = a.getPtPar();
        pagePar = a.getPagePar();
        pageAtt = a.getPageAtt();
        distAttMax = a.getDistAttMax();
        pos = new Point2D(a.getPos());
    }
    
    /**
     *
     */
    public Archer(){
        nom = "";
        ptVie = 0;
        degatAtt = 0;
        ptPar = 0;
        pagePar = 0;
        pageAtt = 0;
        distAttMax = 0;
        pos = new Point2D(0,0);
        nbFleche = 0;
    }
}
