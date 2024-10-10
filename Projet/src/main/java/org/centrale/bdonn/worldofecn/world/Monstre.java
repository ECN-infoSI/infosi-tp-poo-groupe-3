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
     * @param world
     */
    public Monstre(World world) {
        super(world);
    }
    
    /**
     *
     * @param ptVie
     * @param degAtt
     * @param ptPar
     * @param pageAtt
     * @param pagePar
     * @param monde
     */
    public Monstre(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, World monde){
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, monde);   
    }
    
}
