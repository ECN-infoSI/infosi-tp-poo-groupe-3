/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author thomas
 */
public class PotionSoin extends Objet {
    
    /**
     *
     */
    protected int soin;
    
    /**
     *
     * @param n
     * @param p
     * @param s
     */
    public PotionSoin(String n, Point2D p, int s){
        super(n, p);
        soin = s;
    }
    
    /**
     *
     * @param ps
     */
    public PotionSoin(PotionSoin ps){
        super(ps.getNom(), ps.getPos());
        soin = ps.getSoin();
    }
    
    /**
     *
     */
    public PotionSoin(){
        super();
    }

    /**
     *
     * @return
     */
    public int getSoin() {
        return soin;
    }

    /**
     *
     * @param soin
     */
    public void setSoin(int soin) {
        this.soin = soin;
    }
    
    
}
