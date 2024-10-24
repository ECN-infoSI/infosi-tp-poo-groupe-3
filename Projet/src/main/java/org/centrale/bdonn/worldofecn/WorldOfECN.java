/* --------------------------------------------------------------------------------
 * ECN Tools
 * 
 * Ecole Centrale Nantes - Septembre 2022
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin
 * -------------------------------------------------------------------------------- */

package org.centrale.bdonn.worldofecn;

import org.centrale.bdonn.worldofecn.world.World;

/**
 *
 * @author ECN
 */
public class WorldOfECN {

    /**
     * main program
     * @param args
     */
    public static void main(String[] args) {
        World world = new World();
        world.setPlayer("Saegusa");
        
        // Test phase
        DatabaseTools database = new DatabaseTools();

        // Save world
        database.connect();
        Integer playerId = database.getPlayerID("nom de code", "super mot de passe");
        System.out.println(playerId);
        database.saveWorld(playerId, "partie1", "Start", world);
        
        // Retreive World
        database.readWorld(playerId, "Test", "Start", world);
        database.disconnect();
    }
}
