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
        super();       
    }
    
    /**
     *
     * @param l
     */
    public Lapin(Lapin l){
        super(l.ptVie, l.degatAtt, l.ptPar, l.pageAtt, l.pagePar, new Point2D(l.getPos()));
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
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos);      
    }
}
