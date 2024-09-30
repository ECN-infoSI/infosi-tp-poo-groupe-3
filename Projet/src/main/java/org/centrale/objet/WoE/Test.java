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

    /**
     *
     * @param args
     */
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
        
        System.out.println("nombre de fleche restantes : "+monde.robin.getNbFleche());
        
        // caracteristiques du guerrier grosBill
        monde.grosBill.setPtVie(8);
        monde.grosBill.setDegatAtt(3);
        monde.grosBill.setPtPar(2);
        monde.grosBill.setPageAtt(80);
        monde.grosBill.setPagePar(60);
        monde.grosBill.setDistAttMax(1);
                
        monde.wolfie.setPtVie(5);
        monde.wolfie.setDegatAtt(4);
        monde.wolfie.setPtPar(1);
        monde.wolfie.setPageAtt(70);
        monde.wolfie.setPagePar(40);
                   
        Point2D p1 = new Point2D(0, 0);
        Point2D p2 = new Point2D(0, 1);
        Point2D p3 = new Point2D(1,1);
        
        monde.robin.setPos(p1);
        monde.grosBill.setPos(p2);
        monde.wolfie.setPos(p3);
        
        System.out.println("stat de wolfie");
        monde.wolfie.affiche();
        System.out.println("stat de grosBill");
        monde.grosBill.affiche();
        System.out.println("stat de robin");
        monde.robin.affiche();
        
        monde.robin.combattre(monde.grosBill);
        monde.grosBill.combattre(monde.robin);
        monde.wolfie.combattre(monde.robin);
        monde.robin.combattre(monde.wolfie);
        monde.wolfie.combattre(monde.grosBill);
        
        System.out.println("stat de wolfie");
        monde.wolfie.affiche();
        System.out.println("stat de grosBill");
        monde.grosBill.affiche();
        System.out.println("stat de robin");
        monde.robin.affiche();
        System.out.println("nombre de fleche restantes : "+monde.robin.getNbFleche());

    }
}
