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
        World monde = new World();
        monde = monde.creerMondeAlea();
        System.out.println(monde.structcrea.size());
        for (Creature c : monde.structcrea){
            System.out.println(c);
        }
    }
}
