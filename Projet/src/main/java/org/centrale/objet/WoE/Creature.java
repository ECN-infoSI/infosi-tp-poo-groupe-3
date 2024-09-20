/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

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
       
       protected int degaAtt;

       /**
        * Position de la creature
        */
       protected Point2D pos;
       
       
         public Creature(int pV, int dA, int pPar, int paAtt, int paPar, Point2D p){
            ptVie = pV;
            degatAtt = dA;
            ptPar = pPar;
            pagePar = paPar;
            pageAtt = paAtt;
            pos = p;
        }    
         
        public Creature(){
            ptVie = 0;
            degatAtt = 0;
            ptPar = 0;
            pagePar = 0;
            pageAtt = 0;
            pos = new Point2D();
        }

        public int getPtVie() {
            return ptVie;
        }

        public void setPtVie(int ptVie) {
            this.ptVie = ptVie;
        }

        public int getDegatAtt() {
            return degatAtt;
        }

        public void setDegatAtt(int degatAtt) {
            this.degatAtt = degatAtt;
        }

        public int getPtPar() {
            return ptPar;
        }

        public void setPtPar(int ptPar) {
            this.ptPar = ptPar;
        }

        public int getPagePar() {
            return pagePar;
        }

        public void setPagePar(int pagePar) {
            this.pagePar = pagePar;
        }

        public int getPageAtt() {
            return pageAtt;
        }

        public void setPageAtt(int pageAtt) {
            this.pageAtt = pageAtt;
        }

        public Point2D getPos() {
            return pos;
        }

        public void setPos(Point2D pos) {
            this.pos = pos;
        }
        
      
}
