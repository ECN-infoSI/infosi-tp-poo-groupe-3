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
    public Lapin bug1;
    public Lapin bug2;
    public Archer robin;
    public Paysan peon;
    public Archer guillaumeT;
    
    /**
     *
     */
    public World(){
        bug1 = new Lapin();
        bug2 = new Lapin();
        robin = new Archer();
        guillaumeT = new Archer(robin);
        peon = new Paysan();
    }
    
    /**
     * @return le monde crée aléatoirement
     */
    public World creerMondeAlea(){
        World Monmonde = new World();
        while(!((bug1.pos!=bug2.pos)&&(bug1.pos!=robin.pos)&&(bug1.pos!=peon.pos)&&(bug2.pos!=robin.pos)&&(bug2.pos!=peon.pos)&&(peon.pos!=robin.pos))){
            Monmonde.bug1.deplace();
            Monmonde.bug2.deplace();
            Monmonde.robin.deplace();
            Monmonde.peon.deplace();
        }
        return Monmonde;
    }
}
