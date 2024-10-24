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
public abstract class Objet extends ElementDeJeu{
    
    /**
     *
     */
    protected String nom;
    
    /**
     *
     * @param world
     */
    public Objet(World world) {
        super(world);
        nom = "";
    }

    /**
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
     *
     * @param n
     * @param monde
     */
    public Objet(String n, World monde){
        super(monde);
        nom = n;
    }
}
