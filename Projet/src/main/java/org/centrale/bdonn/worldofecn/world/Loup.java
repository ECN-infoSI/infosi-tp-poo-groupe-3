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
public class Loup extends Monstre {
    
    /**
     * @param world
     */
    public Loup(World world) {
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
    public Loup(int ptVie, int degAtt, int pageAtt, int pagePar, World monde){
        super(ptVie, degAtt, pageAtt, pagePar, monde);      
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
