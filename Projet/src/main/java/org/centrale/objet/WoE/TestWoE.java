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
        monde = monde.creerMondeAlea(20,20,20,20,20);
        long debut = System.nanoTime();
        for (int i=0; i<monde.structcrea.size(); i++){
            sum = sum + monde.structcrea.get(i).ptVie;
        }
        for (int i = 0; i < monde.posmonde.length; i++) {
            for (int j = 0; j < monde.posmonde[0].length; j++) {
                System.out.print(monde.posmonde[i][j] + "\t");
            }
            System.out.println();
        }
        long fin = System.nanoTime();
        System.out.println("Le cumul des pv vaut : "+sum);
        System.out.println("pour "+monde.structcrea.size()+" creatures.");
        System.out.println("temps de calcul : "+((fin-debut)/1000)+" ms");
    }
}
