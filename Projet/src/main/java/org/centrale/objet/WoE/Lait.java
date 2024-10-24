/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author thoma
 */
public class Lait extends Nourriture implements Utilisable{
    
    /**
     *
     */
    public Lait(){
        super();
    }
    
    /**
     * 
     * @param l 
     */
    public Lait(Lait l){
        super(l);
    }
    
    /**
     * 
     * @param n
     * @param p
     * @param i
     * @param d 
     */
    public Lait(String n, Point2D p, int i, int d){
        super(n, p, i, d);
    }
    
    /**
     * 
     * @param p 
     */
    @Override
    public void affecter(Personnage p){
        p.pageAtt = p.pageAtt + this.getIntensite();
    }
    
    /**
     * 
     * @param p 
     */
    @Override
    public void desaffecter(Personnage p){
        p.pageAtt = p.pageAtt - this.getIntensite();
    }
    
}
