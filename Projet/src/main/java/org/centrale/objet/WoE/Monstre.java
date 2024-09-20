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
        ptVie = 0;
        degatAtt = 0;
        ptPar = 0;
        pageAtt = 0;
        pagePar = 0;
        pos = new Point2D(0,0);       
    }
    
    /**
     *
     * @param m
     */
    public Monstre(Monstre m){
        ptVie = m.ptVie;
        degatAtt = m.degatAtt;
        ptPar = m.ptPar;
        pageAtt = m.pageAtt;
        pagePar = m.pagePar;
        this.pos = new Point2D(m.getPos());
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
        this.ptVie = ptVie;
        this.degatAtt = degAtt;
        this.ptPar = ptPar;
        this.pageAtt = pageAtt;
        this.pagePar = pagePar;
        this.pos = new Point2D(pos);       
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
