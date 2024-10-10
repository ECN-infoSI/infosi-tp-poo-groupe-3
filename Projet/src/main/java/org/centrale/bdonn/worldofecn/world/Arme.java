/* --------------------------------------------------------------------------------
 * WoE
 * 
 * Ecole Centrale Nantes - Septembre 2022
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin
 * -------------------------------------------------------------------------------- */

package org.centrale.bdonn.worldofecn.world;

/**
 *
 * @author kwyhr
 */
public abstract class Arme extends Objet {
    
    /**
     * @param nom
     * @param world
     */
    public Arme(String nom, World world) {
        super(nom, world);
    }
    
}
