/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author titou
 */
public class World {

    /**
     *
     */
    public ArrayList<Creature> structcrea;
    final int taille = 50;
    public Personnage PJ;

    /** 
     *
     */
    public int[][] posmonde;
    
    /**
     *
     */
    public World(){
        this.structcrea = new ArrayList<>();
        this.posmonde = new int[this.taille][this.taille];
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                this.posmonde[i][j] = -1;
            }     
        }    
    }
    
    
    /**
     * @param x 
     * @param y Les coordonnees etudiees
     * @return le fait qu'une creature est dans les limites du monde
     */
    public boolean Estdanslimite (int x, int y){
        return ((x<this.taille)&&(x>=0)&&(y<this.taille)&&(y>=0));
    }
    
    
    /**
     * @param c La creature etudiee dans la liste
     * C'est un deplacement aleatoire dans une case adjacente libre
     * On explore les positions possibles autour de la creature en prenant en compte les limites du monde et les autres creature
     * On verifie qu'il y a bien au moins une position possible
     * On tire au sort une position qui est disponible soit telle que pospossible[rand] == true
     * On retrouve les i et j correspondants par la division euclidienne
     * On translate la position de la creature
     * Puis on actualise la matrice de position en remplissant la nouvelle case avec le code de la creature 
     * et en vidant l'ancienne case (en mettant la valeur a -1)
     */
    public void deplacealealimite(Creature c){
        Point2D p0 = c.pos;
        int x = p0.getX();
        int y = p0.getY();
        boolean[] pospossible = {false, false, false, false, false, false, false, false, false};
        boolean[] reffalse = {false, false, false, false, false, false, false, false, false};
        for (int i = -1; i<2; i++){
            for (int j = -1; j<2;j++){
                int k = 3*(i+1)+j+1;
                if ((k!=4)&&(Estdanslimite(x+i,y+j))){/*on ne verifie pas la position k=4 car c'est celle sur laquelle on est deja*/
                    pospossible[k] = (this.posmonde[x+i][y+j]==-1);
                }
            }
        }
        if (pospossible==reffalse){
            System.out.println("Impossible de bouger ou de generer position");
        } else {
            Random genAl = new Random();
            int rand = genAl.nextInt(8);
            while (!pospossible[rand]){
                rand = genAl.nextInt(8);
            }
            int newi = rand/3;
            int newj = rand - newi*3;
            c.pos.Translate(newi, newj);
            this.posmonde[c.pos.getX()][c.pos.getY()] = this.posmonde[p0.getX()][p0.getY()];
            this.posmonde[p0.getX()][p0.getY()] = -1;
        }
        
        
    }
    
    public void afficheWorld(){
        for (int i=0; i<this.taille; i++){
            for (int j=0; j<this.taille; j++){
                System.out.print(this.posmonde[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    /**
     * @param nbpa nombre de paysans
     * @param nbgu nombre de guerriers
     * @param nbar nombre d'archers
     * @param nblo nombre de loups
     * @param nbla nombre de lapins
     * @return le monde cree aléatoirement
     * On cree chaque creature en fonction du nombre demande
     * On fait bouger une creature tant qu'elle n'est pas toute seule sur sa case
     * On actualise la matrice de position à chaque ajout de personnage
     * Dans la matrice, les -1 representent des cases vides
     * les 1 representent des paysans
     * les 2 representent des guerriers
     * les 3 representent des archers
     * les 4 representent des loups
     * les 5 representent des lapins
     */
    public World creerMondeAlea(int nbpa, int nbgu, int nbar, int nblo, int nbla){
        World Monmonde = new World();
        Random genAl = new Random();
        for (int i = 0; i<nbpa; i++){
            String nom = "Paysan"+i;
            int pv = genAl.nextInt(10)+1;
            int dA = genAl.nextInt(3)+1;
            int pPar = genAl.nextInt(2)+1;
            int paAtt = genAl.nextInt(99)+1;
            int paPar = genAl.nextInt(99)+1;
            int x = genAl.nextInt(0, (this.taille)-1);
            int y = genAl.nextInt(0, (this.taille)-1);
            Paysan peon = new Paysan(nom, pv, dA, pPar, paAtt, paPar, 1, new Point2D(x, y));
            Monmonde.ajoutecrea(peon);
            Monmonde.structcrea.add(i, peon);
        }
        for (int i = 0; i<nbgu; i++){
            String nom = "Guerrier"+i;
            int pv = genAl.nextInt(15)+1;
            int dA = genAl.nextInt(5)+1;
            int pPar = genAl.nextInt(3)+1;
            int paPar = genAl.nextInt(49)+50;
            int paAtt = genAl.nextInt(49)+50;
            int x = genAl.nextInt(0, (this.taille)-1);
            int y = genAl.nextInt(0, (this.taille)-1);
            Guerrier grosBill = new Guerrier(nom, pv,dA, pPar, paAtt, paPar, 1, new Point2D(x, y));
            Monmonde.ajoutecrea(grosBill);
            Monmonde.structcrea.add(i+nbpa, grosBill);
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
            int x = genAl.nextInt(0, (this.taille)-1);
            int y = genAl.nextInt(0, (this.taille)-1);
            Archer robin = new Archer(nom, pv, dA, pPar, paAtt, paPar, dMax, new Point2D(x, y), nbFleche);
            Monmonde.ajoutecrea(robin);
            Monmonde.structcrea.add(i+nbpa+nbgu, robin);
        }
        for (int i = 0; i<nblo; i++){
            int pv = genAl.nextInt(10)+1;
            int dA = genAl.nextInt(6)+1;
            int pPar = genAl.nextInt(2)+1;
            int paAtt = genAl.nextInt(70)+30;
            int paPar = genAl.nextInt(70)+30;
            int x = genAl.nextInt(0, (this.taille)-1);
            int y = genAl.nextInt(0, (this.taille)-1);
            Loup wolfie = new Loup(pv, dA, pPar, paAtt, paPar, new Point2D(x, y));
            Monmonde.ajoutecrea(wolfie);
            Monmonde.structcrea.add(i+nbpa+nbgu+nbar, wolfie);
        }
        for (int i = 0; i<nbla; i++){
            int pv = genAl.nextInt(4)+1;
            int dA = genAl.nextInt(1)+1;
            int pPar = genAl.nextInt(1)+1;
            int paAtt = genAl.nextInt(99);
            int paPar = genAl.nextInt(99);
            int x = genAl.nextInt(0, (this.taille)-1);
            int y = genAl.nextInt(0, (this.taille)-1);
            Lapin bunny = new Lapin(pv, dA, pPar, paAtt, paPar, new Point2D(x, y));
            Monmonde.ajoutecrea(bunny);
            Monmonde.structcrea.add(i+nbpa+nbgu+nbar+nblo, bunny);
        }
        return Monmonde;
    }
    
    public void ajoutecrea(Creature c){
        int x = c.pos.getX();
        int y = c.pos.getY();
        if (this.posmonde[x][y] != -1){
            this.deplacealealimite(c);
            x = c.pos.getX();
            y = c.pos.getY();
        }
        Class classe = c.getClass();
        String nomclasse = classe.getName();
        int indiceclasse = 0;
        switch (nomclasse){
            case "Paysan":
                indiceclasse = 1;
                break;
            case "Guerrier":
                indiceclasse = 2;
                break;
            case "Archer":
                indiceclasse = 3;
                break;
            case "Loup":
                indiceclasse = 4;
                break;
            case "Lapin":
                indiceclasse = 5;
                break;
        }
        this.posmonde[x][y] = indiceclasse;
        this.structcrea.add(this.structcrea.size(), c);
    }
    
    /**
     * @param j 
     * on ajoute le personnage jouable dans le monde
     * comme c'est un attribut, on aura un accès constant a sa position, ses stats etc
     */
    public void AjoutPJ(Joueur j){
        j.choixPerso();
        j.choixNom();
        this.PJ = j.perso;
        ajoutecrea(this.PJ);
    }
    
    /**
     * On verifie quels deplacements sont autorises
     * si aucun n'est autorise on force le joueur a combattre
     * sinon on lui demande la direction du deplacement
     * si cette direction est possible, on selectionne les dx et dy correspondant
     * sinon on lui redemande
     * On applique la translation
     */
    public void deplacePJ(){
        int x = this.PJ.pos.getX();
        int y = this.PJ.pos.getY();
        boolean[] pospossible = {false, false, false, false, false, false, false, false, false};
        boolean[] reffalse = {false, false, false, false, false, false, false, false, false};
        for (int i = -1; i<2; i++){
            for (int j = -1; j<2;j++){
                int k = 3*(i+1)+j+1;
                if ((k!=4)&&(Estdanslimite(x+i,y+j))){/*on ne verifie pas la position k=4 car c'est celle sur laquelle on est deja*/
                    pospossible[k] = (this.posmonde[x+i][y+j]==-1);
                }
            }
        }
        if (pospossible==reffalse){
            System.out.println("Impossible de bouger, obligation de combattre");
            this.ActionJoueur();
        }else{
            System.out.print("Ou aller ? N, NE, E, SE, S, SO, O ou NO ?");
            Scanner input2 = new Scanner(System.in);
            String direction = input2.next();
            int dx = 0;
            int dy = 0;
            switch (direction){
                case "N":
                    if (pospossible[5]){
                        dx = 0;
                        dy = 1;
                    } else {
                        System.out.println("Direction non valide");
                        System.out.println("Choisir a nouveau");
                        this.deplacePJ();
                    } 
                case "NE":
                    if (pospossible[2]){
                        dx = -1;
                        dy = 1;
                    } else {
                        System.out.println("Direction non valide");
                        System.out.println("Choisir a nouveau");
                        this.deplacePJ();
                    } 
                case "E":
                    if (pospossible[1]){
                        dx = -1;
                        dy = 0;
                    } else {
                        System.out.println("Direction non valide");
                        System.out.println("Choisir a nouveau");
                        this.deplacePJ();
                    }
                case "SE":
                    if (pospossible[0]){
                        dx = -1;
                        dy = -1;
                    } else {
                        System.out.println("Direction non valide");
                        System.out.println("Choisir a nouveau");
                        this.deplacePJ();
                    }                    
                case "S":
                    if (pospossible[3]){
                        dx = 0;
                        dy = -1;
                    } else {
                        System.out.println("Direction non valide");
                        System.out.println("Choisir a nouveau");
                        this.deplacePJ();
                    } 
                case "SO":
                    if (pospossible[6]){
                        dx = 1;
                        dy = -1;
                    } else {
                        System.out.println("Direction non valide");
                        System.out.println("Choisir a nouveau");
                        this.deplacePJ();
                    } 
                case "O":
                    if (pospossible[7]){
                        dx = 1;
                        dy = 0;
                    } else {
                        System.out.println("Direction non valide");
                        System.out.println("Choisir a nouveau");
                        this.deplacePJ();
                    } 
                case "NO":
                    if (pospossible[8]){
                        dx = 1;
                        dy = 1;
                    } else {
                        System.out.println("Direction non valide");
                        System.out.println("Choisir a nouveau");
                        this.deplacePJ();
                    }
                default:
                    System.out.println("Direction non valide");
                    System.out.println("Choisir a nouveau");
                    this.deplacePJ();
            }
            this.PJ.pos.Translate(dx, dy);   
        }                            
    }
    
    /**
     * On demande la position relative au joueur
     * en cas d'absence de creature a cet endroit, on redemande au joueur ce qu'il veut faire
     * S'il y a bien une creature, on va la chercher dans notre structure qui stocke les creatures
     * Puis on fait combattre le pj (qui est nécessairement un combattant et la creature)
     * Si la creature n'a plus de point de vie, on l'enleve de la bdd et on enleve son corps de la grille de positions
     */
    public void CombattrePJ(){
        System.out.println("Ou est la creature a combattre ?");
        System.out.println("Entrer la distance en x avec la creature");
        Scanner input3 = new Scanner(System.in);
        String rep3 = input3.next();
        int dx = Integer.parseInt(rep3);
        System.out.println("Entrer la distance en y avec la creature");
        Scanner input4 = new Scanner(System.in);
        String rep4 = input4.next();
        int dy = Integer.parseInt(rep4);
        int posx = this.PJ.pos.getX() + dx;
        int posy = this.PJ.pos.getY() + dy;
        if (this.posmonde[posx][posy]==-1){
            System.out.println("Pas de creature la");
            this.ActionJoueur();
        } else{
            int i = 0;
            int X = this.structcrea.get(i).pos.getX();
            int Y = this.structcrea.get(i).pos.getY();
            while ((i<this.structcrea.size())&&((posx!=X)||(posy!=Y))){
                i++;
                X = this.structcrea.get(i).pos.getX();
                Y = this.structcrea.get(i).pos.getY();
            }
            ((Combattant)(this.PJ)).combattre(this.structcrea.get(i));
            if (this.structcrea.get(i).getPtVie()<=0){
                System.out.println("Vous avez tuer la creature");
                this.posmonde[X][Y] = -1;
                this.structcrea.remove(i);
            }
        }
    }
    
    /**
     * On definit quelle est l'action que le joueur effectue et on utilise la fonction associee
     */
    public void ActionJoueur(){
        System.out.println("Voulez-vous : Deplacer ou Combattre");
        Scanner input1 = new Scanner(System.in);
        String choix = input1.next();
        switch (choix) {
            case "Deplacer":
                this.deplacePJ();
            case "Combattre":
                this.CombattrePJ();                
        }
    }
    
    /**
     * On effetue l'action du joueur
     * Pour chaque creature :
     * on verifie si elle est combattante
     * si c'est le cas elle essaie d'attaquer
     * Puis on verifie si les pv du PJ ont bouge a cause de cette creature
     * si c'est pas le cas, la creature bouge
     * Lorsque les
     */
    public void TourDeJeu(){
        if (PJ.getPtVie()<=0){
            System.out.println("Vous etes KO");
        }else{
            this.ActionJoueur();
            /**if (PJ.pos.distance(tcloud.getPos())<tcloud.getTaille()){
                    PJ.setPtVie(c.getPtVie()-tcloud.getDegat());
            }*/
            for (int i = 0; i<this.structcrea.size(); i++){
                Creature c = this.structcrea.get(i);
                int pvPJ = PJ.getPtVie();
                if (c instanceof Combattant combattant){
                    combattant.combattre(PJ);
                }
                if (PJ.getPtVie()==pvPJ){
                    this.deplacealealimite(c);
                }
                /**if (c.pos.distance(tcloud.getPos())<tcloud.getTaille()){
                    c.setPtVie(c.getPtVie()-tcloud.getDegat());
                }*/
            }
        }
    }
    
}