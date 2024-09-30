/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;
import java.util.LinkedList;
import java.util.Random;
/**
 *
 * @author titou
 */
public class World {
    public LinkedList<Creature> structcrea;
    
    /**
     *
     */
    public World(){
        this.structcrea = new LinkedList<>();
    }
    
    public World(int nbpa, int nbgu, int nbar, int nblo, int nbla){
        this.structcrea = new LinkedList<>();
        for (int i = 0; i<nbpa; i++){
            Paysan peon = new Paysan();
            this.structcrea.add(i, peon);
        }
        for (int i = 0; i<nbgu; i++){
            Guerrier peon = new Guerrier();
            this.structcrea.add(i+nbpa, peon);
        }
        for (int i = 0; i<nbar; i++){
            Archer peon = new Archer();
            this.structcrea.add(i+nbpa+nbgu, peon);
        }
        for (int i = 0; i<nblo; i++){
            Loup peon = new Loup();
            this.structcrea.add(i+nbpa+nbgu+nbar, peon);
        }
        for (int i = 0; i<nbla; i++){
            Lapin peon = new Lapin();
            this.structcrea.add(i+nbpa+nbgu+nbar+nblo, peon);
        }
    }
    
    /**
     * @return le monde crée aléatoirement
     */
    public World creerMondeAlea(){
        Random genAl = new Random();
        int nbpa = genAl.nextInt(99)+1;
        int nbgu = genAl.nextInt(99)+1;
        int nbar = genAl.nextInt(99)+1;
        int nblo = genAl.nextInt(99)+1;
        int nbla = genAl.nextInt(99)+1;
        World Monmonde = new World(nbpa, nbgu, nbar, nblo, nbla);
        return Monmonde;
    }
}
