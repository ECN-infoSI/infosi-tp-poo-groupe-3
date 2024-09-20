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
    
    public Objet(String n){
        nom = n;
    }
    
    public Objet(Objet o){
        nom = o.getNom();
    }
    
    public Objet(){
        nom = "";
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
