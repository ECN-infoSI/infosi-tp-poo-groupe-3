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
    public Guerrier grosBill;
    public Loup Wolfie;
    
    /**
     *
     */
    public World(){
        bug1 = new Lapin();
        bug2 = new Lapin();
        robin = new Archer();
        guillaumeT = new Archer(robin);
        peon = new Paysan();
        grosBill = new Guerrier();
        Wolfie = new Loup();
    }
    
    /**
     * @return le monde crée aléatoirement
     */
    public World creerMondeAlea(){
        World Monmonde = new World();
        while(!((bug1.pos!=bug2.pos)&&(bug1.pos!=robin.pos)&&(bug1.pos!=peon.pos)&&(bug1.pos!=grosBill.pos)&&(bug1.pos!=Wolfie.pos)&&(bug2.pos!=robin.pos)&&(bug2.pos!=grosBill.pos)&&(bug2.pos!=Wolfie.pos)&&(bug2.pos!=peon.pos)&&(peon.pos!=robin.pos)&&(peon.pos!=grosBill.pos)&&(peon.pos!=Wolfie.pos)&&(grosBill.pos!=robin.pos)&&(Wolfie.pos!=robin.pos)&&(grosBill.pos!=Wolfie.pos))){
            Monmonde.bug1.deplace();
            Monmonde.bug2.deplace();
            Monmonde.robin.deplace();
            Monmonde.peon.deplace();
            Monmonde.grosBill.deplace();
            Monmonde.Wolfie.deplace();
            Monmonde.guillaumeT.deplace();
        }
        return Monmonde;
    }
}
