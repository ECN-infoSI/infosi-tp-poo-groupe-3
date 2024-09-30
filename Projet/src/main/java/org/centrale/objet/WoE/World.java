/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;
import java.util.LinkedList;
import java.util.Random;
/**
 *
 * @author titou
 */
public class World {
    public LinkedList<Creature> structcrea;
    final int taille = 50;
    
    /**
     *
     */
    public World(){
        this.structcrea = new LinkedList<>();
    }
    
    /**
     * 
     * @param nbpa nombre de paysan
     * @param nbgu nombre de guerrier
     * @param nbar nombre d'archer
     * @param nblo nombre de loup
     * @param nbla nombre de lapin
     * Genere aleatoirement le nombre de creatures passe en parametre
     */
    public World(int nbpa, int nbgu, int nbar, int nblo, int nbla){
        Random genAl = new Random();
        this.structcrea = new LinkedList<>();
        for (int i = 0; i<nbpa; i++){
            String nom = "Paysan"+i;
            int pv = genAl.nextInt(10)+1;
            int dA = genAl.nextInt(3)+1;
            int pPar = genAl.nextInt(2)+1;
            int paAtt = genAl.nextInt(99)+1;
            int paPar = genAl.nextInt(99)+1;
            Paysan peon = new Paysan(nom, pv,dA, pPar, paAtt, paPar, 1, new Point2D());
            this.structcrea.add(i, peon);
        }
        for (int i = 0; i<nbgu; i++){
            String nom = "Guerrier"+i;
            int pv = genAl.nextInt(15)+1;
            int dA = genAl.nextInt(5)+1;
            int pPar = genAl.nextInt(3)+1;
            int paPar = genAl.nextInt(49)+50;
            int paAtt = genAl.nextInt(49)+50;
            Guerrier grosBill = new Guerrier(nom, pv,dA, pPar, paAtt, paPar, 1, new Point2D());
            this.structcrea.add(i+nbpa, grosBill);
        }
        for (int i = 0; i<nbar; i++){
            String nom = "Archer"+i;
            int pv = genAl.nextInt(12)+1;
            int dA = genAl.nextInt(4)+1;
            int pPar = genAl.nextInt(2)+1;
            int paPar = genAl.nextInt(74)+25;
            int paAtt = genAl.nextInt(74)+25;
            int dMax = genAl.nextInt(5)+3;
            int nbFleche = genAl.nextInt(10);
            Archer robin = new Archer(nom, pv, dA, pPar, paAtt, paPar, dMax, new Point2D(), nbFleche);
            this.structcrea.add(i+nbpa+nbgu, robin);
        }
        for (int i = 0; i<nblo; i++){
            int pv = genAl.nextInt(10)+1;
            int dA = genAl.nextInt(6)+1;
            int pPar = genAl.nextInt(2)+1;
            int paAtt = genAl.nextInt(70)+30;
            int paPar = genAl.nextInt(70)+30;
            Loup wolfie = new Loup(pv, dA, pPar, paAtt, paPar, new Point2D());
            this.structcrea.add(i+nbpa+nbgu+nbar, wolfie);
        }
        for (int i = 0; i<nbla; i++){
            int pv = genAl.nextInt(4)+1;
            int dA = genAl.nextInt(1)+1;
            int pPar = genAl.nextInt(1)+1;
            int paAtt = genAl.nextInt(99);
            int paPar = genAl.nextInt(99);
            Lapin bunny = new Lapin(pv, dA, pPar, paAtt, paPar, new Point2D());            
            this.structcrea.add(i+nbpa+nbgu+nbar+nblo, bunny);
        }
    }
    
    /**
     * @param c La creature etudiee dans la liste
     * @return le fait qu'une creature est dans les limites du monde
     */
    public boolean Estdanslimite (Creature c){
        int x = c.pos.getX();
        int y = c.pos.getY();
        return ((x<=(this.taille)/2)&&(x>-(this.taille-1)/2)&&(y<=(this.taille)/2)&&(y>-(this.taille-1)/2));
    }
    
    
    /**
     * @param c La creature etudiee dans la liste
     * deplace une creature en prenant compte des limites du monde et des autres creatures
     */
    public void deplacelimite(Creature c){
        Point2D p0 = c.pos;
        c.deplace();
        while ((!Estdanslimite(c))||(!Esttoutseul(c))){
            c.pos = p0;
            c.deplace();
        }
    }
    
    /**
     * @param c La creature etudiee dans la liste
     * @return le fait qu'une creature est toute seule sur sa case ou non
     */
    public boolean Esttoutseul(Creature c){
        Point2D p0 = c.pos;
        int indic = 0;
        for (int i = 0 ; i<this.structcrea.size() ; i++){
            if (this.structcrea.get(i).pos == p0){
                indic++;
            }
        }
        return indic==1;
    }
    /**
     * @param nbpa nombre de paysans
     * @param nbgu nombre de guerriers
     * @param nbar nombre d'archers
     * @param nblo nombre de loups
     * @param nbla nombre de lapins
     * @return le monde cree aléatoirement
     * On fait bouger une creature tant qu'elle n'est pas toute seule sur sa case
     * Pour le moment ça a ete fait au plus simple (en terme de code) donc la complexite est en n^n
     */
    public World creerMondeAlea(int nbpa, int nbgu, int nbar, int nblo, int nbla){
        World Monmonde = new World(nbpa, nbgu, nbar, nblo, nbla);
        for (int j = 0 ; j<this.structcrea.size() ; j++){
            while (!Esttoutseul(this.structcrea.get(j))){
                deplacelimite(this.structcrea.get(j));
            }
        }
        return Monmonde;
    }
}
