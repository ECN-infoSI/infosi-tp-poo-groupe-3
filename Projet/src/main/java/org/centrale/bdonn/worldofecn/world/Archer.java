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
public class Archer extends Personnage {
    /**
     * Nombre de fleches de l'archer
     */
    private int nbFleche;

    /**
     *
     * @return
     */
    public int getNbFleche() {
        return nbFleche;
    }

    /**
     *
     * @param nbFleche
     */
    public void setNbFleche(int nbFleche) {
        this.nbFleche = nbFleche;
    }
    
    /**
     *
     * @param world
     */
    public Archer(World world) {
        super(world);
        nbFleche = 0;
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
     * @param nbFl
     */
        
    public Archer(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, World monde, int nbFl){
        super(n, pV, dA, pPar, paAtt, paPar, dMax, monde);
        nbFleche = nbFl;
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
