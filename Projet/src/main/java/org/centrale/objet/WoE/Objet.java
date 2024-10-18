/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author thomas
 */
public class Objet extends ElementDeJeu{

    /**
     *
     */
    protected String nom;
    
    /**
     *
     * @param n
     * @param p
     */
    public Objet(String n, Point2D p){
        super(p);
        nom = n;
    }
    
    /**
     *
     * @param o
     */
    public Objet(Objet o){
        super(o.getPos());
        nom = o.getNom();
    }
    
    /**
     *
     */
    public Objet(){
        super();
        nom = "";
    }
    
    /**
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }  
    
}
