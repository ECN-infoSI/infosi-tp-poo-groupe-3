/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;
import java.util.Random;

/**
 *
 * @author titou
 */
public class Monstre extends Creature {
    
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
    public void deplace(){
        Random genAl = new Random();
        int xAl = genAl.nextInt(2)-1;
        int yAl = genAl.nextInt(2)-1;
        while ((xAl == 0)&&(yAl == 0)){
            xAl = genAl.nextInt(2)-1;
            yAl = genAl.nextInt(2)-1;
        }
        pos.Translate(xAl, yAl);
    }
    
    /**
     *
     */
    public void affiche(){
        System.out.println("ptVie = "+ptVie+"\n;degAtt = "+degatAtt+"\n;ptPar = "+ptPar+"\n;pageAtt = "+pageAtt+"\n;pagePar = " +pagePar);
        pos.affiche();
    }
}
