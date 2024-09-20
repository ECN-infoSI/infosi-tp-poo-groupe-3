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
        monde.bug1.affiche();
        monde.peon.affiche();
        monde.bug1.deplace();
        monde.peon.deplace();
        monde.bug1.affiche();
        monde.peon.affiche();
    }
}
