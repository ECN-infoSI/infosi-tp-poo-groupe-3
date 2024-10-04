/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author thomas
 */
public class Objet {

    /**
     *
     */
    protected String nom;

    /**
     *
     */
    protected Point2D pos;
    
    /**
     *
     * @param n
     * @param p
     */
    public Objet(String n, Point2D p){
        nom = n;
        pos = p;
    }
    
    /**
     *
     * @param o
     */
    public Objet(Objet o){
        nom = o.getNom();
        pos = o.getPos();
    }
    
    /**
     *
     */
    public Objet(){
        nom = "";
        pos = new Point2D();
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

    /**
     *
     * @return
     */
    public Point2D getPos() {
        return pos;
    }

    /**
     *
     * @param pos
     */
    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    
    
}
