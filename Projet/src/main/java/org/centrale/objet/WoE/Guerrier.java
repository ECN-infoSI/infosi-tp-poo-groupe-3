/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Random;

/**
 *
 * @author titou
 */
public class Guerrier extends Personnage implements Combattant {
    
    
    /**
     *
     * @param n
     * @param pV
     * @param dA
     * @param pPar
     * @param paAtt
     * @param paPar
     * @param dMax
     * @param p
     */

    public Guerrier(String n, int pV, int dA, int pPar, int paAtt, int paPar, float dMax, Point2D p){
        super(n, pV, dA, pPar, paAtt, paPar, dMax, p);
    }
    
    /**
     *
     * @param g
     */
    
    public Guerrier(Guerrier g){
        super(g.getNom(), g.getPtVie(), g.getDegatAtt(), g.getPtPar(), g.getPageAtt(), g.getPagePar(), g.getDistAttMax(), new Point2D(g.getPos()));
    }

    /**
     *
     */
    public Guerrier() {
        super();
    }
    
    
    
    /**
     *
     * @param monde
     * @return  */
    
    public Guerrier guerrierAlea(World monde){
        Random genAl = new Random();
        int pv = genAl.nextInt(15)+1;
        int dA = genAl.nextInt(5)+1;
        int pPar = genAl.nextInt(3)+1;
        int paPar = genAl.nextInt(49)+50;
        int paAtt = genAl.nextInt(49)+50;
        int x = genAl.nextInt(0, (monde.taille)-1);
        int y = genAl.nextInt(0, (monde.taille)-1);
        float DistMax = 1;
        Guerrier guegue = new Guerrier(nom, pv,dA, pPar, paAtt, paPar, DistMax, new Point2D(x, y));
        return guegue;
    }
    
    /**
     * 
     * @param c 
     * On commence par vérifier que le combat au corps à corps est possible
     * On test la réussite de l'attaque
     * Puis on test la réussite de la parade
     * En cas de parade, on vérifie que les dégâts sont positifs
     * Si les ptVie tombent en dessous de 0 on les remets à 0.
     */
    @Override
    public void combattre(Creature c){
        if (this.pos.distance(c.pos)<=this.distAttMax){
            System.out.println(this.getNom()+" attaque !");
            Random genAl = new Random();
            int jetAtt = genAl.nextInt(99)+1;
            int deg = 0;
            if (jetAtt<=this.pageAtt){
                deg += this.degatAtt;
                int jetPar = genAl.nextInt(99)+1;
                if (jetPar<=c.pagePar){
                    System.out.println("parade reussie");
                    deg -= c.ptPar;
                }
                if (deg>0){
                c.setPtVie(c.getPtVie()- deg);
                System.out.println(deg+" degats infliges !");
                }
                else {
                    System.out.println("Attaque trop faible");
                }
                if (c.getPtVie() < 0){
                    c.setPtVie(0);
                    System.out.println("adversaire KO");
                }
            }
            else{
                System.out.println("attaque manquee");
            }
        }
        else{
            System.out.println("attaque impossible");
        }
    }
}
