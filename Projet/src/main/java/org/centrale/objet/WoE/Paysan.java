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
     * @param nbFl
     */
    public void Paysan(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p, int nbFl){
        this.Personnage(n, pV, dA, pPar, paAtt, paPar, dMax, p);
    }
    
    /**
     *
     * @param p
     */
    public void Paysan(Paysan p){
        this.Personnage(p);
    }
    
    /**
     *
     */
    public void Paysan(){
        this.Personnage();
    }
}
