/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author thoma
 */
public class Nourriture extends Objet{
    
    private int intensite;
    
    private int duree;
  
    /**
     *
     */
    public Nourriture(){
        super();
        intensite = 0;
        duree = 0;
    }
    
    /**
     * 
     * @param n 
     */
    public Nourriture(Nourriture n){
        super(n.getNom(), n.getPos());
        nom = n.getNom();
        intensite = n.getIntensite();
        duree = n.getDuree();
    }
    
    /**
     * 
     * @param n
     * @param p
     * @param i
     * @param d 
     */
    public Nourriture(String n, Point2D p, int i, int d){
        super(n, p);
        intensite = i;
        duree = d;
    }

    /**
     *
     * @return
     */
    public int getDuree() {
        return duree;
    }

    /**
     *
     * @param duree
     */
    public void setDuree(int duree) {
        this.duree = duree;
    }

    /**
     *
     * @return
     */
    public int getIntensite() {
        return intensite;
    }

    /**
     *
     * @param intensite
     */
    public void setIntensite(int intensite) {
        this.intensite = intensite;
    }
}
