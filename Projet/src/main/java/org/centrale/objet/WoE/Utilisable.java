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
    
    /**
     *
     * @param p
     */
    public void affecter(Personnage p);
    
    /**
     *
     * @param p
     */
    public void desaffecter(Personnage p);
    
    /**
     *
     * @return
     */
    public int getDuree();
    
    /**
     *
     * @param i
     */
    public void setDuree(int i);
    
    /**
     *
     * @return
     */
    public String getNom();
}
