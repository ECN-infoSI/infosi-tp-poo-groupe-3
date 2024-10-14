/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author thoma
 */
public class ElementDeJeu {
    /**
    * Position de l'element
    */
    protected Point2D pos;
    
    public ElementDeJeu(){
        pos = new Point2D();
    }
    
    public ElementDeJeu(Point2D p){
        pos = p;
    }
    
    public ElementDeJeu(ElementDeJeu edj){
        pos = edj.getPos();
    }
    /**
     *
     * @return
     */
    public Point2D getPos() {
        return pos;
    }
    
    /**
     *
     * @param pos
     */
    public void setPos(Point2D pos) {
        this.pos = pos;
    }
}
