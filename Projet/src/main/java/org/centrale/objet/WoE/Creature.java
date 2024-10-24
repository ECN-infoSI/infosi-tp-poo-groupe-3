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
public abstract class Creature extends ElementDeJeu implements Deplacable{

        /**
         * Nombre de points de vie
         */
        protected int ptVie;

        /**
         * Dégats infligés par attaque
         */
        protected int degatAtt;

        /**
         * Points de parade
         */
        protected int ptPar;

        /**
         * Pourcentage de parade
         */
        protected int pagePar;

        /**
        * Pourcentage d'attaque
        */
       protected int pageAtt;
       
    /**
     *
     */
    protected int degaAtt;

       
    /**
     *
     * @param pV
     * @param dA
     * @param pPar
     * @param paAtt
     * @param paPar
     * @param p
     */
    public Creature(int pV, int dA, int pPar, int paAtt, int paPar, Point2D p){
        super(p);
        ptVie = pV;
        degatAtt = dA;
        ptPar = pPar;
        pagePar = paPar;
        pageAtt = paAtt;
        }    
         
    /**
     *
     */
    public Creature(){
        super();
        ptVie = 0;
        degatAtt = 0;
        ptPar = 0;
        pagePar = 0;
        pageAtt = 0;
        }
    
    /**
     *
     * @param c
     */
    public Creature(Creature c){
        super(c.getPos());
        ptVie = c.getPtVie();
        degatAtt = c.getDegatAtt();
        ptPar = c.getPtPar();
        pagePar = c.getPagePar();
        pageAtt = c.getPageAtt();
    }

    /**
     *
     * @return
     */
    public int getPtVie() {
            return ptVie;
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
     * @return
     */
    public int getDegatAtt() {
            return degatAtt;
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
     * @return
     */
    public int getPtPar() {
            return ptPar;
        }

    /**
     *
     * @param ptPar
     */
    public void setPtPar(int ptPar) {
            this.ptPar = ptPar;
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
     * @param pagePar
     */
    public void setPagePar(int pagePar) {
            this.pagePar = pagePar;
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
     * @param pageAtt
     */
    public void setPageAtt(int pageAtt) {
            this.pageAtt = pageAtt;
    }
      
    /**
    * Déplace le personnage sur une case adjacente à sa position.
    */
    @Override
    public void deplace(){
        Random genAl = new Random();
        int xAl = genAl.nextInt(2)-1;
        int yAl = genAl.nextInt(2)-1;
        while ((xAl == 0)&&(yAl == 0)){
            xAl = genAl.nextInt(2)-1;
            yAl = genAl.nextInt(2)-1;
        }
        pos.Translate(xAl, yAl);
    }  
}
