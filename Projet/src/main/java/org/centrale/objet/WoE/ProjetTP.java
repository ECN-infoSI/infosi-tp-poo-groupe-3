/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;
import java.util.Iterator;

/**
 *
 * @author titou
 */
public class ProjetTP {

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        World monde = new World();
        monde = monde.creerMondeAlea(0,0,0,5,30, 10, 10);
        monde.AjoutPJ();
        monde.afficheWorld();
        System.out.println("debut du jeu");
        System.out.println("vous etes au 0");
        System.out.println("1 : Paysan, 2 : Archer, 3 : Guerrier, 4 : Loup, 5 : Lapin");
        while (monde.joueur.perso.getPtVie()>0){
            monde.TourDeJeu();
            }
        System.out.println("Vous etes ko, fin du jeu !");
    }
}
