/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author thomas
 */
public class Epee extends Objet{
    
    public Epee(String n, Point2D p){
        super(n, p);
    }
    
    public Epee(Epee e){
        super(e.getNom(), e.getPos());
    }
    
    public Epee(){
        super();
    }
}
