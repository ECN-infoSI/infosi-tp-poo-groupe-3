/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

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
        int sum = 0;
        World monde = new World();
        monde = monde.creerMondeAlea(2,2,2,2,2);
        System.out.println("Est-ce que la creature est dans les limites du monde ?");
        System.out.println(monde.Estdanslimite(monde.structcrea.get(0)));
        System.out.println("Est-ce que la creature est seule sur sa case ?");
        System.out.println(monde.Esttoutseul(monde.structcrea.get(0)));
        long debut = System.nanoTime();
        for (int i=0; i<monde.structcrea.size(); i++){
            sum = sum + monde.structcrea.get(i).ptVie;
        }
        long fin = System.nanoTime();
        System.out.println("Le cumul des pv vaut : "+sum);
        System.out.println("pour "+monde.structcrea.size()+" creatures.");
        System.out.println("temps de calcul : "+((fin-debut)/1000)+" ms");
    }
}
