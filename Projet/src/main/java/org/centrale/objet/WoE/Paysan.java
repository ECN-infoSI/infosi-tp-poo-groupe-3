/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author thomas
 */
public class Paysan extends Personnage{

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
    final boolean estjouable = false;

    public Paysan(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p){
        super(n, pV, dA, pPar, paAtt, paPar, dMax, p);
    }
    
    /**
     *
     * @param p
     */
    
    public Paysan(Paysan p){
        super(p.getNom(), p.getPtVie(), p.getDegatAtt(), p.getPtPar(), p.getPagePar(), p.getPageAtt(), p.getDistAttMax(), new Point2D(p.getPos()));
    }
    
    /**
     *
     */
    
    public Paysan(){
        super();
    }
}
