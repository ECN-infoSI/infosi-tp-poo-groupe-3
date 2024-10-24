/* --------------------------------------------------------------------------------
 * WoE
 * 
 * Ecole Centrale Nantes - Septembre 2022
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin
 * -------------------------------------------------------------------------------- */

package org.centrale.bdonn.worldofecn.world;

import java.sql.Connection;
import java.util.Random;

/**
 *
 * @author ECN
 */
public abstract class ElementDeJeu {
    private Point2D position;

    /**
     *
     * @param position
     */
    public ElementDeJeu(Point2D position) {
        this.position = position;
    }

    /**
     *
     */
    public ElementDeJeu() {
    }
    
    /**
     * generate element in the world
     * @param world
     */
    public ElementDeJeu(World world) {
        super();
        
        Random rand = new Random();
        this.position = new Point2D(rand.nextInt(world.getWidth()), rand.nextInt(world.getHeight()));
    }

    /**
     *
     * @return
     */
    public Point2D getPosition() {
        return position;
    }

    /**
     *
     * @param position
     */
    public void setPosition(Point2D position) {
        this.position = position;
    }
    
    /**
     *
     * @param connection
     */
    public abstract void saveToDatabase(Connection connection);
    
    /**
     *
     * @param connection
     * @param id
     */
    public abstract void getFromDatabase(Connection connection, Integer id);
}
