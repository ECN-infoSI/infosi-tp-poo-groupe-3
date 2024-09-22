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
    protected String nom;
    protected Point2D pos;
    
    public Objet(String n, Point2D p){
        nom = n;
        pos = p;
    }
    
    public Objet(Objet o){
        nom = o.getNom();
        pos = o.getPos();
    }
    
    public Objet(){
        nom = "";
        pos = new Point2D();
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    
    
}
