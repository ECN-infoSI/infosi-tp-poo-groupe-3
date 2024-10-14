/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author thoma
 */
public class NuageToxique extends Objet {
    
    public NuageToxique(){
        super();
    }
    
    public NuageToxique(String n, Point2D p){
        super(n, p);
    }
    
    public NuageToxique(NuageToxique nt){
        super(nt.getNom(), nt.getPos());
    }
    
}
