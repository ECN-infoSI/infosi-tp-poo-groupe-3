/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author titou
 */
public class Lapin extends Monstre{

    /**
     *
     */
    public Lapin(){
        /**
     *par défault le lapin se situe au centre du monde et n'a pas de ptVie ni de degAtt
     */
        ptVie = 10;
        degatAtt = 1;
        ptPar = 0;
        pageAtt = 50;
        pagePar = 70;
        pos = new Point2D(0,0);       
    }
    
    /**
     *
     * @param l
     */
    public Lapin(Lapin l){
        ptVie = l.ptVie;
        degatAtt = l.degatAtt;
        ptPar = l.ptPar;
        pageAtt = l.pageAtt;
        pagePar = l.pagePar;
        this.pos = new Point2D(l.getPos());
    }
    
    /**
     *
     * @param ptVie
     * @param degAtt
     * @param ptPar
     * @param pageAtt
     * @param pagePar
     * @param pos
     */
    public Lapin(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos){
        this.ptVie = ptVie;
        this.degatAtt = degAtt;
        this.ptPar = ptPar;
        this.pageAtt = pageAtt;
        this.pagePar = pagePar;
        this.pos = new Point2D(pos);       
    }
}
