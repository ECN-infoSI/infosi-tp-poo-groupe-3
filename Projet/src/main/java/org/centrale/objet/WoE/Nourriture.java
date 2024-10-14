/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author thoma
 */
public class Nourriture{
    
    private String nom;
    
    private Point2D pos;
    
    private int intensite;
    
    private int duree;
  
    public Nourriture(){
        nom = "";
        pos = new Point2D();
        intensite = 0;
        duree = 0;
    }
    
    public Nourriture(Nourriture n){
        nom = n.getNom();
        pos = n.getPos();
        intensite = n.getIntensite();
        duree = n.getDuree();
    }
    
    public Nourriture(String n, Point2D p, int i, int d){
        nom = n;
        pos = p;
        intensite = i;
        duree = d;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
    
    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    public int getIntensite() {
        return intensite;
    }

    public void setIntensite(int intensite) {
        this.intensite = intensite;
    }
}
