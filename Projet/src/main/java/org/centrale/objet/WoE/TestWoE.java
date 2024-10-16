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
        monde = monde.creerMondeAlea(0,0,0,0,2);
        Joueur moi =  new Joueur();
        monde.AjoutPJ(moi);
        for (int i = 0; i<monde.structcrea.size();i++){
            Creature c = monde.structcrea.get(i);
            Class classe = c.getClass();
            String nomclasse = classe.getName();
            nomclasse = nomclasse.substring(23);
            System.out.println(nomclasse);
        }
        for (int i = 0; i< 100; i++){
            monde.TourDeJeu();
        }
    }
}
