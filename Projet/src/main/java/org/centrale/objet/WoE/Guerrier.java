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
public class Guerrier extends Personnage {
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
     * @param nbFl
     */

    public Guerrier(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p, int nbFl){
        nom = n;
        ptVie = pV;
        degatAtt = dA;
        ptPar = pPar;
        pagePar = paPar;
        pageAtt = paAtt;
        distAttMax = dMax;
        pos = p;
    }
    
    /**
     *
     * @param g
     */
    
    public Guerrier(Guerrier g){
        nom = g.getNom();
        ptVie = g.getPtVie();
        degatAtt = g.getDegatAtt();
        ptPar = g.getPtPar();
        pagePar = g.getPagePar();
        pageAtt = g.getPageAtt();
        distAttMax = g.getDistAttMax();
        pos = new Point2D(g.getPos());
    }
    
    /**
     *
     */
    
    public Guerrier(){
        nom = "";
        ptVie = 0;
        degatAtt = 0;
        ptPar = 0;
        pagePar = 0;
        pageAtt = 0;
        distAttMax = 0;
        pos = new Point2D(0,0);
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
                    deg -= c.ptPar;
                }             
            }
            if (deg>0){
                c.setPtVie(deg);
            }
            if (c.getPtVie() < 0){
                c.setPtVie(0);
            }
        }
    }
}
