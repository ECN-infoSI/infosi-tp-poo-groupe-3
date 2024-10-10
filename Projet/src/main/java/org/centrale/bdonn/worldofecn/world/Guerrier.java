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
public class Guerrier extends Personnage {
    
    /**
     *
     * @param world
     */
    public Guerrier(World world) {
        super(world);
    }
    
    /**
     *
     * @param n
     * @param pV
     * @param dA
     * @param pPar
     * @param paAtt
     * @param paPar
     * @param dMax
     * @param monde
     */

    public Guerrier(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, World monde){
        super(n, pV, dA, pPar, paAtt, paPar, dMax, monde);
    }
    
    /**
     *
     * @param connection
     */
    @Override
    public void saveToDatabase(Connection connection) {
        
    }

    /**
     *
     * @param connection
     * @param id
     */
    @Override
    public void getFromDatabase(Connection connection, Integer id) {
    }
}
