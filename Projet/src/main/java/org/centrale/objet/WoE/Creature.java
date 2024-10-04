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
public class Creature {

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
        * Position de la creature
        */
       protected Point2D pos;
       
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
            ptVie = pV;
            degatAtt = dA;
            ptPar = pPar;
            pagePar = paPar;
            pageAtt = paAtt;
            pos = p;
        }    
         
    /**
     *
     */
    public Creature(){
            ptVie = 0;
            degatAtt = 0;
            ptPar = 0;
            pagePar = 0;
            pageAtt = 0;
            pos = new Point2D();
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
     *
     * @return
     */
    public Point2D getPos() {
            return pos;
        }

    /**
     *
     * @param pos
     */
    public void setPos(Point2D pos) {
            this.pos = pos;
        }
        
        /**
        * Déplace le personnage sur une case adjacente à sa position.
        */
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
