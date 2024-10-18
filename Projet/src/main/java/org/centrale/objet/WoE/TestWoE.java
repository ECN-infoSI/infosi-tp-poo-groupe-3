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
public class TestWoE {

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        World monde = new World();
        monde = monde.creerMondeAlea(0,0,0,1,4, 2, 2);
        monde.AjoutPJ();
        monde.afficheWorld();
        System.out.println("debut du jeu");
        for (int i = 0; i< 100; i++){
            monde.TourDeJeu();
        }
    }
}
