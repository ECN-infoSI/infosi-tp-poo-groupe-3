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
public class Monstre {
    protected int ptVie;
    protected int degAtt;
    protected int ptPar;
    protected int pageAtt;
    protected int pagePar;
    protected Point2D pos;
    
    
    public Monstre(){
        /**
     *par défault le monstre se situe au centre du monde et n'a pas de ptVie ni de degAtt
     */
        ptVie = 0;
        degAtt = 0;
        ptPar = 0;
        pageAtt = 0;
        pagePar = 0;
        pos = new Point2D(0,0);       
    }
    
    public Monstre(Monstre m){
        ptVie = m.ptVie;
        degAtt = m.degAtt;
        ptPar = m.ptPar;
        pageAtt = m.pageAtt;
        pagePar = m.pagePar;
        this.pos = new Point2D(m.getPos());
    }
    
    public Monstre(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos){
        this.ptVie = ptVie;
        this.degAtt = degAtt;
        this.ptPar = ptPar;
        this.pageAtt = pageAtt;
        this.pagePar = pagePar;
        this.pos = new Point2D(pos);       
    }

    public int getPtVie() {
        return ptVie;
    }

    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    public int getDegAtt() {
        return degAtt;
    }

    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    public int getPtPar() {
        return ptPar;
    }

    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    public int getPageAtt() {
        return pageAtt;
    }

    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    public int getPagePar() {
        return pagePar;
    }

    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    
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
    
    public void affiche(){
        System.out.println("ptVie = "+ptVie+"\n;degAtt = "+degAtt+"\n;ptPar = "+ptPar+"\n;pageAtt = "+pageAtt+"\n;pagePar = " +pagePar);
        pos.affiche();
    }
}
