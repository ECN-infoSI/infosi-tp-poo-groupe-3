/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author thoma
 */
public class Carotte extends Nourriture implements Utilisable{
    
    public Carotte(){
        super();
    }
    
    /**
     * @param rp
    **/
    public Carotte(Carotte rp){
        super(rp);
    }
    
    /**
     * 
     * @param n
     * @param p
     * @param i
     * @param d 
     */
    public Carotte(String n, Point2D p, int i, int d){
        super(n, p, i, d);
    }
    
    /*
    * @p Personnage affecte par le bonus/malus
    */
    @Override
    public void affecter(Personnage p){
        p.distAttMax = p.distAttMax + this.getIntensite();
    }
    
    /*
    * @p Personnage affecte par le bonus/malus
    */
    @Override
    public void desaffecter(Personnage p){
        p.distAttMax = p.distAttMax - this.getIntensite();
    }
}
