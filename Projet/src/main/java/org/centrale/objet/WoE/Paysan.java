/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author thomas
 */
public class Paysan extends Personnage{
    public Paysan(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p, int nbFl){
        nom = n;
        ptVie = pV;
        degatAtt = dA;
        ptPar = pPar;
        pagePar = paPar;
        pageAtt = paAtt;
        distAttMax = dMax;
        pos = p;
    }
    
    public Paysan(Paysan p){
        nom = p.getNom();
        ptVie = p.getPtVie();
        degatAtt = p.getDegatAtt();
        ptPar = p.getPtPar();
        pagePar = p.getPagePar();
        pageAtt = p.getPageAtt();
        distAttMax = p.getDistAttMax();
        pos = new Point2D(p.getPos());
    }
    
    public Paysan(){
        nom = "";
        ptVie = 0;
        degatAtt = 0;
        ptPar = 0;
        pagePar = 0;
        pageAtt = 0;
        distAttMax = 0;
        pos = new Point2D(0,0);
    }
}
