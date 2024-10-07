/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author titou
 */
public abstract class Monstre extends Creature {
    
    /**
     *
     */
    public Monstre(){
        /**
     *par défault le monstre se situe au centre du monde et n'a pas de ptVie ni de degAtt
     */
        super();
    }
    
    /**
     *
     * @param m
     */
    public Monstre(Monstre m){
        super(m.ptVie, m.degatAtt, m.ptPar, m.pageAtt, m.pagePar, new Point2D(m.getPos()));
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
    public Monstre(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos){
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos);   
    }
       
    /**
     *
     */
    public void affiche(){
        System.out.println("points de vie : "+ptVie);
        System.out.println("degats d'attaque : "+degatAtt);
        System.out.println("points de parade : "+ptPar);
        System.out.println("pourcentage de parade : "+pagePar);
        System.out.println("pourcentage d'attaque : "+pageAtt);
        pos.affiche();
    }
}
