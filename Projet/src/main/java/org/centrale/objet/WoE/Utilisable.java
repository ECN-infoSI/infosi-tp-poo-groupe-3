/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author thoma
 */
public interface Utilisable {
    public void affecter(Personnage p);
    
    public void desaffecter(Personnage p);
    
    public int getDuree();
    
    public void setDuree(int i);
    
    public String getNom();
}
