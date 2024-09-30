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
        monde = monde.creerMondeAlea();
        Set<Creature> setC = monde.structcrea.keySet();
        Iterator itr = setC.iterator();
        while (itr.hasNext()) {
            String str = (String) itr.next();
            System.out.println(str + ": " + monde.structcrea.get(itr));
        }
        System.out.println();

    }
}
