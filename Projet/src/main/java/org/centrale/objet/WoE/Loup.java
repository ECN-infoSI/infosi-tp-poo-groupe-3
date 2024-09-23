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
public class Loup extends Monstre{
    public Loup(){
        /**
     *par défault le loup se situe au centre du monde et n'a pas de ptVie ni de degAtt
     */
        super();       
    }
    
    /**
     *
     * @param lp
     */
    public Loup(Loup lp){
        super(lp.ptVie, lp.degatAtt, lp.ptPar, lp.pageAtt, lp.pagePar, new Point2D(lp.getPos()));
    }
    
    /**
     *
     * @param ptVie
     * @param degAtt
     * @param ptPar
     * @param pageAtt
     * @param pagePar
     * @param pos
     */
    public Loup(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos){
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos);      
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
    public void combattre(Creature c){
        if (this.pos.distance(c.pos)==1){
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
                System.out.println("attaque manquée");
            }
        }
        else {
            System.out.println("attaque impossible");
        }
    }
}
