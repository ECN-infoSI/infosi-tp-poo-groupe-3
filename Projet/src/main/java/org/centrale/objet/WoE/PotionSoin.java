/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author thoma
 */
public class PotionSoin extends Objet {
    
    public PotionSoin(String n){
        super(n);
    }
    
    public PotionSoin(PotionSoin ps){
        super(ps.getNom());
    }
    
    public PotionSoin(){
        super();
    }
}
