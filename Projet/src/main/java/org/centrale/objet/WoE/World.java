/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author titou
 */
public class World {
    public ArrayList<Creature> structcrea;
    final int taille = 50;
    public int[][] posmonde;
    
    /**
     *
     */
    public World(){
        this.structcrea = new ArrayList<>();
        this.posmonde = new int[this.taille][this.taille];
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                this.posmonde[i][j] = -1;
      }
    }
        
    }
    
    
    /**
     * @param c La creature etudiee dans la liste
     * @return le fait qu'une creature est dans les limites du monde
     */
    public boolean Estdanslimite (Creature c){
        int x = c.pos.getX();
        int y = c.pos.getY();
        return ((x<this.taille)&&(x>=0)&&(y<this.taille)&&(y>=0));
    }
    
    
    /**
     * @param c La creature etudiee dans la liste
     * deplace une creature en prenant compte des limites du monde et des autres creature
     * Puis on actualise la matrice de position en remplissant la nouvelle case avec le code de la creature 
     * et en vidant l'ancienne case (en mettant la valeur a -1)
     */
    public void deplacelimite(Creature c){
        Point2D p0 = c.pos;
        c.deplace();
        while ((!Estdanslimite(c))||(this.posmonde[c.pos.getX()][c.pos.getY()] != -1)){
            c.pos = p0;
            c.deplace();
        }
        this.posmonde[c.pos.getX()][c.pos.getY()] = this.posmonde[p0.getX()][p0.getY()];
        this.posmonde[p0.getX()][p0.getY()] = -1;
    }
    
    /**
     * @param nbpa nombre de paysans
     * @param nbgu nombre de guerriers
     * @param nbar nombre d'archers
     * @param nblo nombre de loups
     * @param nbla nombre de lapins
     * @return le monde cree aléatoirement
     * On cree chaque creature en fonction du nombre demande
     * On fait bouger une creature tant qu'elle n'est pas toute seule sur sa case
     * On actualise la matrice de position à chaque ajout de personnage
     * Dans la matrice, les -1 representent des cases vides
     * les 1 representent des paysans
     * les 2 representent des guerriers
     * les 3 representent des archers
     * les 4 representent des loups
     * les 5 representent des lapins
     */
    public World creerMondeAlea(int nbpa, int nbgu, int nbar, int nblo, int nbla){
        World Monmonde = new World();
        Random genAl = new Random();
        for (int i = 0; i<nbpa; i++){
            String nom = "Paysan"+i;
            int pv = genAl.nextInt(10)+1;
            int dA = genAl.nextInt(3)+1;
            int pPar = genAl.nextInt(2)+1;
            int paAtt = genAl.nextInt(99)+1;
            int paPar = genAl.nextInt(99)+1;
            int x = genAl.nextInt(0, (this.taille)-1);
            int y = genAl.nextInt(0, (this.taille)-1);
            Paysan peon = new Paysan(nom, pv, dA, pPar, paAtt, paPar, 1, new Point2D(x, y));
            if (Monmonde.posmonde[x][y] != -1){
                Monmonde.deplacelimite(peon);
                x = peon.pos.getX();
                y = peon.pos.getY();
            }
            Monmonde.structcrea.add(i, peon);
            Monmonde.posmonde[x][y] = 1;
        }
        for (int i = 0; i<nbgu; i++){
            String nom = "Guerrier"+i;
            int pv = genAl.nextInt(15)+1;
            int dA = genAl.nextInt(5)+1;
            int pPar = genAl.nextInt(3)+1;
            int paPar = genAl.nextInt(49)+50;
            int paAtt = genAl.nextInt(49)+50;
            int x = genAl.nextInt(0, (this.taille)-1);
            int y = genAl.nextInt(0, (this.taille)-1);
            Guerrier grosBill = new Guerrier(nom, pv,dA, pPar, paAtt, paPar, 1, new Point2D(x, y));
            if (Monmonde.posmonde[x][y] != -1){
                Monmonde.deplacelimite(grosBill);
                x = grosBill.pos.getX();
                y = grosBill.pos.getY();
            }
            Monmonde.posmonde[x][y] = 2;
            Monmonde.structcrea.add(i+nbpa, grosBill);
        }
        for (int i = 0; i<nbar; i++){
            String nom = "Archer"+i;
            int pv = genAl.nextInt(12)+1;
            int dA = genAl.nextInt(4)+1;
            int pPar = genAl.nextInt(2)+1;
            int paPar = genAl.nextInt(74)+25;
            int paAtt = genAl.nextInt(74)+25;
            int dMax = genAl.nextInt(5)+3;
            int nbFleche = genAl.nextInt(10);
            int x = genAl.nextInt(0, (this.taille)-1);
            int y = genAl.nextInt(0, (this.taille)-1);
            Archer robin = new Archer(nom, pv, dA, pPar, paAtt, paPar, dMax, new Point2D(x, y), nbFleche);
            if (Monmonde.posmonde[x][y] != -1){
                Monmonde.deplacelimite(robin);
                x = robin.pos.getX();
                y = robin.pos.getY();
            }
            Monmonde.posmonde[x][y] = 3;
            Monmonde.structcrea.add(i+nbpa+nbgu, robin);
        }
        for (int i = 0; i<nblo; i++){
            int pv = genAl.nextInt(10)+1;
            int dA = genAl.nextInt(6)+1;
            int pPar = genAl.nextInt(2)+1;
            int paAtt = genAl.nextInt(70)+30;
            int paPar = genAl.nextInt(70)+30;
            int x = genAl.nextInt(0, (this.taille)-1);
            int y = genAl.nextInt(0, (this.taille)-1);
            Loup wolfie = new Loup(pv, dA, pPar, paAtt, paPar, new Point2D(x, y));
            if (Monmonde.posmonde[x][y] != -1){
                Monmonde.deplacelimite(wolfie);
                x = wolfie.pos.getX();
                y = wolfie.pos.getY();
            }
            Monmonde.posmonde[x][y] = 4;
            Monmonde.structcrea.add(i+nbpa+nbgu+nbar, wolfie);
        }
        for (int i = 0; i<nbla; i++){
            int pv = genAl.nextInt(4)+1;
            int dA = genAl.nextInt(1)+1;
            int pPar = genAl.nextInt(1)+1;
            int paAtt = genAl.nextInt(99);
            int paPar = genAl.nextInt(99);
            int x = genAl.nextInt(0, (this.taille)-1);
            int y = genAl.nextInt(0, (this.taille)-1);
            Lapin bunny = new Lapin(pv, dA, pPar, paAtt, paPar, new Point2D(x, y));
            if (Monmonde.posmonde[x][y] != -1){
                Monmonde.deplacelimite(bunny);
                x = bunny.pos.getX();
                y = bunny.pos.getY();
            }
            Monmonde.posmonde[x][y] = 5;            
            Monmonde.structcrea.add(i+nbpa+nbgu+nbar+nblo, bunny);
        }
        return Monmonde;
    }
}
