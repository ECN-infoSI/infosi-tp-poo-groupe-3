/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author titou
 */
public class World {
    private Lapin bug1;
    private Lapin bug2;
    private Archer robin;
    private Paysan peon;
    
    public World(){
        bug1 = new Lapin();
        bug2 = new Lapin();
        robin = new Archer();
        peon = new Paysan();
    }
    
    public void creerMondeAlea(){
        World Monmonde = new World();
        while(!((bug1.pos!=bug2.pos)&&(bug1.pos!=robin.pos)&&(bug1.pos!=peon.pos)&&(bug2.pos!=robin.pos)&&(bug2.pos!=peon.pos)&&(peon.pos!=robin.pos))){
            Monmonde.bug1.deplace();
            Monmonde.bug2.deplace();
            Monmonde.robin.deplace();
            Monmonde.peon.deplace();
        }
        
        
    }
}
