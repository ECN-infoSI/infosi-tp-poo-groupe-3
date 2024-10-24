 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Random;

/**
 *
 * @author thoma
 */
public class NuageToxique extends Objet implements Deplacable{
    
    private int degatpartour;
    private int taille;
    
    /**
     *
     */
    public NuageToxique(){
        super();
    }
    
    /**
     *
     * @param n
     * @param p
     */
    public NuageToxique(String n, Point2D p){
        super(n, p);
    }
    
    /**
     *
     * @param nt
     */
    public NuageToxique(NuageToxique nt){
        super(nt.getNom(), nt.getPos());
    }

    /**
     *
     * @return
     */
    public int getDegatpartour() {
        return degatpartour;
    }

    /**
     *
     * @return
     */
    public int getTaille() {
        return taille;
    }

    /**
     *
     * @param degatpartour
     */
    public void setDegatpartour(int degatpartour) {
        this.degatpartour = degatpartour;
    }

    /**
     *
     * @param taille
     */
    public void setTaille(int taille) {
        this.taille = taille;
    }

    /**
    * Déplace le nuage toxique sur une case adjacente à sa position.
    */
    @Override
    public void deplace(){
        Random genAl = new Random();
        int xAl = genAl.nextInt(2)-1;
        int yAl = genAl.nextInt(2)-1;
        while ((xAl == 0)&&(yAl == 0)){
            xAl = genAl.nextInt(2)-1;
            yAl = genAl.nextInt(2)-1;
        }
        pos.Translate(xAl, yAl);
    }
}
