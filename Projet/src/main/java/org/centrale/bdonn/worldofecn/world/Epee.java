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
 * @author kwyhr
 */
public class Epee extends Arme {
    
    /**
     *
     * @param world
     */
    public Epee(World world) {
        super("", world);
    }
    
    /**
     *
     * @param n
     * @param monde
     */
    public Epee(String n, World monde){
        super(n, monde);
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
