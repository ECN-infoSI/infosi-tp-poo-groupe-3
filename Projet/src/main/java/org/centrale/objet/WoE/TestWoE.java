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
        int sum = 0;        
        World monde = new World();
        monde = monde.creerMondeAlea();
        for (int i=0; i<monde.structcrea.size(); i++){
            sum = sum + monde.structcrea.get(i).ptVie;
        }
        System.out.println(sum);
        System.out.println(monde.structcrea.size());
    }
}
