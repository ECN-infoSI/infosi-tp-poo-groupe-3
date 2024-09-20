/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author titou
 */
public class Loup extends Monstre{
    public Loup(){
        /**
     *par défault le loup se situe au centre du monde et n'a pas de ptVie ni de degAtt
     */
        ptVie = 20;
        degatAtt = 5;
        ptPar = 2;
        pageAtt = 70;
        pagePar = 50;
        pos = new Point2D(0,0);       
    }
    
    /**
     *
     * @param lp
     */
    public Loup(Loup lp){
        ptVie = lp.ptVie;
        degatAtt = lp.degatAtt;
        ptPar = lp.ptPar;
        pageAtt = lp.pageAtt;
        pagePar = lp.pagePar;
        this.pos = new Point2D(lp.getPos());
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
    public Loup(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos){
        this.ptVie = ptVie;
        this.degatAtt = degAtt;
        this.ptPar = ptPar;
        this.pageAtt = pageAtt;
        this.pagePar = pagePar;
        this.pos = new Point2D(pos);       
    }
    
    public void combattre(Creature c){
        
    }
}
