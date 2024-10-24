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

    /**
     *
     * @return
     */
    public int getPtVie() {
        return ptVie;
    }

    /**
     *
     * @return
     */
    public int getDegatAtt() {
        return degatAtt;
    }

    /**
     *
     * @return
     */
    public int getPagePar() {
        return pagePar;
    }

    /**
     *
     * @return
     */
    public int getPageAtt() {
        return pageAtt;
    }

    /**
     *
     * @return
     */
    public int getDegaAtt() {
        return degaAtt;
    }

    /**
     *
     * @param ptVie
     */
    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    /**
     *
     * @param degatAtt
     */
    public void setDegatAtt(int degatAtt) {
        this.degatAtt = degatAtt;
    }

    /**
     *
     * @param pagePar
     */
    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    /**
     *
     * @param pageAtt
     */
    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    /**
     *
     * @param degaAtt
     */
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
