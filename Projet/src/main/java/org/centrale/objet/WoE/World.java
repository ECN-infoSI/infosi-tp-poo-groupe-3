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
            Guerrier grosBill = new Guerrier();
            this.structcrea.add(i+nbpa, grosBill);
        }
        for (int i = 0; i<nbar; i++){
            Archer robin = new Archer();
            this.structcrea.add(i+nbpa+nbgu, robin);
        }
        for (int i = 0; i<nblo; i++){
            Loup wolfie = new Loup();
            this.structcrea.add(i+nbpa+nbgu+nbar, wolfie);
        }
        for (int i = 0; i<nbla; i++){
            Lapin bug = new Lapin();
            this.structcrea.add(i+nbpa+nbgu+nbar+nblo, bug);
        }
    }
    
    /**
     * @param j l'indice de la créture étudiée dans la liste
     * @return le fait qu'une créature est toute seule sur sa case ou non
     */
    public boolean Esttoutseul(int j){
        Point2D p0 = this.structcrea.get(j).pos;
        int indic = 0;
        for (int i = 0 ; i<this.structcrea.size() ; i++){
            if ((this.structcrea.get(i).pos == p0)&&(i!=j)){
                indic = 1;
            }
        }
        return indic==0;
    }
    /**
     * @return le monde crée aléatoirement
     * On fait bouger une créature tant qu'elle n'est pas toute seule sur sa case
     */
    public World creerMondeAlea(){
        Random genAl = new Random();
        int nbpa = genAl.nextInt(99)+1;
        int nbgu = genAl.nextInt(99)+1;
        int nbar = genAl.nextInt(99)+1;
        int nblo = genAl.nextInt(99)+1;
        int nbla = genAl.nextInt(99)+1;
        World Monmonde = new World(nbpa, nbgu, nbar, nblo, nbla);
        for (int j = 0 ; j<this.structcrea.size() ; j++){
            while (!Esttoutseul(j)){
                this.structcrea.get(j).deplace();
            }
        }
        return Monmonde;
    }
}
