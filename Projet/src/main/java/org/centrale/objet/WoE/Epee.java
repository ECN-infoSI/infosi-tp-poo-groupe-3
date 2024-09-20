/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author thoma
 */
public class Epee extends Objet{
    
    public Epee(String n){
        nom = n;
    }
    
    public Epee(Epee e){
        nom = e.getNom();
    }
    
    public Epee(){
        nom = "";
    }
}
