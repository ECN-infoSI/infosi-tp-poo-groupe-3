/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author titou
 */
public class Test {
    public static void main(String[] args){
        World monde = new World();
        monde = monde.creerMondeAlea();
        /**monde.bug1.affiche();
        monde.peon.affiche();
        monde.bug1.deplace();
        monde.peon.deplace();
        monde.bug1.affiche();
        monde.peon.affiche();**/
        // caracteristiques de l'archer robin
        monde.robin.setPtVie(5);
        monde.robin.setDegatAtt(2);
        monde.robin.setPtPar(1);
        monde.robin.setPageAtt(80);
        monde.robin.setPagePar(60);
        monde.robin.setDistAttMax(10);
        monde.robin.setNbFleche(10);
        
        // caracteristiques du guerrier grosBill
        monde.grosBill.setPtVie(8);
        monde.grosBill.setDegatAtt(3);
        monde.grosBill.setPtPar(2);
        monde.grosBill.setPageAtt(80);
        monde.grosBill.setPagePar(60);
        monde.grosBill.setDistAttMax(1);
        
        monde.robin.deplace();
        monde.robin.affiche();
        monde.guillaumeT.affiche();
        
        monde.robin.combattre(monde.grosBill);
        Point2D p1 = new Point2D(0, 0);
        Point2D p2 = new Point2D(0, 1);
        monde.robin.setPos(p1);
        monde.grosBill.setPos(p2);
        monde.robin.combattre(monde.grosBill);
        monde.grosBill.combattre(monde.robin);
        monde.robin.affiche();
    }
}
