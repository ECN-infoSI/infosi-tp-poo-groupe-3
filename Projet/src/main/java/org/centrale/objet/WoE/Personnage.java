/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Random;
/**
 *
 * @author thomas
 */

public class Personnage {
    protected String nom;
    protected int ptVie;
    protected int degatAtt;
    protected int ptPar;
    protected int pagePar;
    protected int pageAtt;
    protected int distAttMax;
    protected Point2D pos;

    public int getPtVie() {
        return ptVie;
    }

    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    public int getDegatAtt() {
        return degatAtt;
    }

    public void setDegatAtt(int degatAtt) {
        this.degatAtt = degatAtt;
    }

    public int getPtPar() {
        return ptPar;
    }

    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    public int getPagePar() {
        return pagePar;
    }

    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    public int getPageAtt() {
        return pageAtt;
    }

    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    public int getDistAttMax() {
        return distAttMax;
    }

    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    
    /** 
     * Constructeur de l'objet Personnage.
     * @param n     Nom du personnage
     * @param pV    Nombre de points de vie du personnage
     * @param dA    Nombre de dégats que le personnage peut infliger en attaquant
     * @param pPar  Nombre de points d'aptitude à parer du personnage
     * @param paAtt Pourcentage d'attaque du personnage
     * @param paPar Pourcentage de parade du personnage
     * @param dMax  Distance maximale d'attaque du personnage
     * @param p     Position du personnage dans le monde
     */
    public void Personnage(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p){
        nom = n;
        ptVie = pV;
        degatAtt = dA;
        ptPar = pPar;
        pagePar = paPar;
        pageAtt = paAtt;
        distAttMax = dMax;
        pos = p;
    }
    
    public void Personnage(Personnage perso){
        nom = perso.getNom();
        ptVie = perso.getPtVie();
        degatAtt = perso.getDegatAtt();
        ptPar = perso.getPtPar();
        pagePar = perso.getPagePar();
        pageAtt = perso.getPageAtt();
        distAttMax = perso.getDistAttMax();
        pos = new Point2D(perso.getPos());
    }
    
    public void Personnage(){
        nom = "";
        ptVie = 0;
        degatAtt = 0;
        ptPar = 0;
        pagePar = 0;
        pageAtt = 0;
        distAttMax = 0;
        pos = new Point2D();
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
        System.out.println("nom : "+nom+"\n;points de vie : "+ptVie);
        System.out.println("\n;degats d'attaque : "+degatAtt);
        System.out.println("\n;points de parade : "+ptPar);
        System.out.println("\n;pourcentage de parade : "+pagePar);
        System.out.println("\n;pourcentage d'attaque : "+pageAtt);
        System.out.println("\n;distance d'attaque maximale : "+distAttMax);
        pos.affiche();
    }
}
