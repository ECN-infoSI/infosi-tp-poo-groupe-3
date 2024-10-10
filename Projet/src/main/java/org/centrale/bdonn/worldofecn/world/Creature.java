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
public abstract class Creature extends ElementDeJeu {
   
        /**
         * Nombre de points de vie
         */
        protected int ptVie;

        /**
         * Dégats infligés par attaque
         */
        protected int degatAtt;

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
     * @param world
     */
    public Creature(World world) {
        super(world);
        ptVie = 0;
        degatAtt = 0;
        pagePar = 0;
        pageAtt = 0;
    }
    
    /**
     *
     * @param pV
     * @param dA
     * @param paAtt
     * @param paPar
     * @param monde
     */
    public Creature(int pV, int dA, int paAtt, int paPar, World monde){
            super(monde);
            ptVie = pV;
            degatAtt = dA;
            pagePar = paPar;
            pageAtt = paAtt;
        }    
    

}
