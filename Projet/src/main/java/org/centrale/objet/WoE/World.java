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
