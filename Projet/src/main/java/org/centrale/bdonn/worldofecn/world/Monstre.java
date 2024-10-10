/* --------------------------------------------------------------------------------
 * WoE
 * 
 * Ecole Centrale Nantes - Septembre 2022
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin
 * -------------------------------------------------------------------------------- */

package org.centrale.bdonn.worldofecn.world;

import java.sql.Connection;

/**
 *
 * @author ECN
 */
public abstract class Monstre extends Creature {

    public int getPtVie() {
        return ptVie;
    }

    public int getDegatAtt() {
        return degatAtt;
    }

    public int getPagePar() {
        return pagePar;
    }

    public int getPageAtt() {
        return pageAtt;
    }

    public int getDegaAtt() {
        return degaAtt;
    }

    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    public void setDegatAtt(int degatAtt) {
        this.degatAtt = degatAtt;
    }

    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    public void setDegaAtt(int degaAtt) {
        this.degaAtt = degaAtt;
    }
    
    /**
     *
     * @param world
     */
    public Monstre(World world) {
        super(world);
    }
    
    /**
     *
     * @param ptVie
     * @param degAtt
     * @param pageAtt
     * @param pagePar
     * @param monde
     */
    public Monstre(int ptVie, int degAtt, int pageAtt, int pagePar, World monde){
        super(ptVie, degAtt, pageAtt, pagePar, monde);   
    }
    
}
