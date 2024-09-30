/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;
import java.util.*;

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
        monde = monde.creerMondeAlea(20, 20, 20, 20, 20);
        boolean verif = true;
        for (int i = 0; i<100; i++){
            if((monde.structcrea.get(i).pos.getX()>25)&&(monde.structcrea.get(i).pos.getX()<=-25)&&(monde.structcrea.get(i).pos.getY()>25)&&(monde.structcrea.get(i).pos.getY()<=-25)){
                verif = false;
            }
        }
        System.out.println(verif);
    }
}
