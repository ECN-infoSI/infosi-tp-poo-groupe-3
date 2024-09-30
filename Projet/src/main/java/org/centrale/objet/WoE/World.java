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
    final int taille = 50;
    
    /**
     *
     */
    public World(){
        this.structcrea = new LinkedList<>();
    }
    
    
    
    public World(int nbpa, int nbgu, int nbar, int nblo, int nbla){
        Random genAl = new Random();
        this.structcrea = new LinkedList<>();
        for (int i = 0; i<nbpa; i++){
            String nom = "Paysan"+i;
            int pv = genAl.nextInt(10)+1;
            int dA = genAl.nextInt(3)+1;
            int pPar = genAl.nextInt(2)+1;
            int paAtt = genAl.nextInt(99)+1;
            int paPar = genAl.nextInt(99)+1;
            Paysan peon = new Paysan(nom, pv,dA, pPar, paAtt, paPar, 1, new Point2D());
            this.structcrea.add(i, peon);
        }
        for (int i = 0; i<nbgu; i++){
            String nom = "Guerrier"+i;
            int pv = genAl.nextInt(15)+1;
            int dA = genAl.nextInt(5)+1;
            int pPar = genAl.nextInt(3)+1;
            int paPar = genAl.nextInt(49)+50;
            int paAtt = genAl.nextInt(49)+50;
            Guerrier grosBill = new Guerrier(nom, pv,dA, pPar, paAtt, paPar, 1, new Point2D());
            this.structcrea.add(i+nbpa, grosBill);
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
            Archer robin = new Archer(nom, pv, dA, pPar, paAtt, paPar, dMax, new Point2D(), nbFleche);
            this.structcrea.add(i+nbpa+nbgu, robin);
        }
        for (int i = 0; i<nblo; i++){
            int pv = genAl.nextInt(10)+1;
            int dA = genAl.nextInt(6)+1;
            int pPar = genAl.nextInt(2)+1;
            int paAtt = genAl.nextInt(70)+30;
            int paPar = genAl.nextInt(70)+30;
            Loup wolfie = new Loup(pv, dA, pPar, paAtt, paPar, new Point2D());
            this.structcrea.add(i+nbpa+nbgu+nbar, wolfie);
        }
        for (int i = 0; i<nbla; i++){
            int pv = genAl.nextInt(4)+1;
            int dA = genAl.nextInt(1)+1;
            int pPar = genAl.nextInt(1)+1;
            int paAtt = genAl.nextInt(99);
            int paPar = genAl.nextInt(99);
            Lapin bunny = new Lapin(pv, dA, pPar, paAtt, paPar, new Point2D());            
            this.structcrea.add(i+nbpa+nbgu+nbar+nblo, bunny);
        }
    }
    
    public void deplacelimite(Creature c){
        int x = c.pos.getX();
        int y = c.pos.getY();
        int xAl = 0;
        int yAl = 0;
        Random genAl = new Random();
        if (x==(this.taille)/2){
            xAl = genAl.nextInt(1)-1;
        } else if (x==-(this.taille-1)/2){
            xAl = genAl.nextInt(1);
        } else {xAl = genAl.nextInt(2)-1;}
        if (y==(this.taille)/2){
            yAl = genAl.nextInt(1)-1;
        } else if (y==-(this.taille-1)/2){
            yAl = genAl.nextInt(1);
        } else {yAl = genAl.nextInt(2)-1;}
        c.pos.Translate(xAl, yAl);
    }
    
    /**
     * @param j l'indice de la créature étudiée dans la liste
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
     * Pour le moment ça a été fait au plus simple (en terme de code) donc la complexité est en n^n
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
                deplacelimite(this.structcrea.get(j));
            }
        }
        return Monmonde;
    }
}
