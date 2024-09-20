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
    
    public void Archer(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p, int nbFl){
        this.Personnage(n, pV, dA, pPar, paAtt, paPar, dMax, p);
        nbFleche = nbFl;
    }
    
    public void Archer(Archer a){
        nom = a.getNom();
        ptVie = a.getPtVie();
        degatAtt = a.getDegatAtt();
        ptPar = a.getPtPar();
        pagePar = a.getPagePar();
        pageAtt = a.getPageAtt();
        distAttMax = a.getDistAttMax();
        pos = new Point2D(a.getPos());
    }
    
    public void Archer(){
        nom = "";
        ptVie = 0;
        degatAtt = 0;
        ptPar = 0;
        pagePar = 0;
        pageAtt = 0;
        distAttMax = 0;
        pos = new Point2D();
        nbFleche = 0;
    }
}
