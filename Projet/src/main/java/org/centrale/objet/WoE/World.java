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
    public ArrayList<Nourriture> structnou;
    final int taille = 6;
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
     * @param edj La creature etudiee dans la liste
     * C'est un deplacement aleatoire dans une case adjacente libre
     * On explore les positions possibles autour de la creature en prenant en compte les limites du monde et les autres creature
     * On verifie qu'il y a bien au moins une position possible
     * On tire au sort une position qui est disponible soit telle que pospossible[rand] == true
     * On retrouve les i et j correspondants par la division euclidienne
     * On translate la position de la creature
     * Puis on actualise la matrice de position en remplissant la nouvelle case avec le code de la creature 
     * et en vidant l'ancienne case (en mettant la valeur a -1)
     */
    public void deplacealealimite(ElementDeJeu edj){
        Point2D p0 = edj.pos;
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
            int newi = rand/3 - 1;
            int newj = rand - (newi+1)*3 - 1;
            edj.pos.Translate(newi, newj);
            this.posmonde[edj.pos.getX()][edj.pos.getY()] = this.posmonde[x][y];
            this.posmonde[x][y] = -1;
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
    public World creerMondeAlea(int nbpa, int nbgu, int nbar, int nblo, int nbla, int nbca, int nblait){
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
        }
        for (int i = 0; i<nbca; i++){
            String nom = "carotte"+i;
            int x = genAl.nextInt(0, (this.taille)-1);
            int y = genAl.nextInt(0, (this.taille)-1);
            int intensite = genAl.nextInt(5)+1;
            int d = genAl.nextInt(3)+1;
            Carotte charlotte = new Carotte(nom, new Point2D(x, y), intensite, d);
            Monmonde.ajouteNou(charlotte);
        }
        for (int i = 0; i<nblait; i++){
            String nom = "lait"+i;
            int x = genAl.nextInt(0, (this.taille)-1);
            int y = genAl.nextInt(0, (this.taille)-1);
            int intensite = genAl.nextInt(5)+1;
            int d = genAl.nextInt(3)+1;
            Lait yop = new Lait(nom, new Point2D(x, y), intensite, d);
            Monmonde.ajouteNou(yop);
        }
        return Monmonde;
    }
    
    
    /**
     * On recupere la position et si cette derniere est deja prise, on deplace la creature sur un ecase libre voisine
     * On récupere le nom de la creature, en enlevant le nom du dossier devant
     * On actualise la matrice de position et la bdd qui stocke les creatures du monde
     * @param c la creature qu'on veut ajouter
     */
    public void ajoutecrea(Creature c){
        int x = c.pos.getX();
        int y = c.pos.getY();
        int indicpos = this.posmonde[x][y];
        if (indicpos != -1){
            this.deplacealealimite(c);
            this.posmonde[x][y] = indicpos;
            x = c.pos.getX();
            y = c.pos.getY();
        }
        Class classe = c.getClass();
        String nomclasse = classe.getName();
        nomclasse = nomclasse.substring(23);
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
    
    public void ajouteNou(Nourriture n){
        int x = n.pos.getX();
        int y = n.pos.getY();
        int indicpos = this.posmonde[x][y];
        if (indicpos != -1){
            this.deplacealealimite(n);
            this.posmonde[x][y] = indicpos;
             x = n.pos.getX();
            y = n.pos.getY();
        }
        Class classe = n.getClass();
        String nomclasse = classe.getName();
        nomclasse = nomclasse.substring(23);
        int indiceclasse = 0;
        switch (nomclasse){
            case "Carotte":
                indiceclasse = 6;
                break;
            case "Lait":
                indiceclasse = 7;
                break;
        }
        this.posmonde[x][y] = indiceclasse;
        this.structnou.add(this.structnou.size(), n);
    }
    
    /**
     * @param j 
     * on ajoute le personnage jouable dans le monde
     * comme c'est un attribut, on aura un accès constant a sa position, ses stats etc
     */
    public void AjoutPJ(Joueur j){
        j.choixPerso(this);
        j.choixNom();
        this.PJ = j.perso;
        Class classe = PJ.getClass();
        String nomclasse = classe.getName();
        this.posmonde[PJ.pos.getX()][PJ.pos.getY()] = 0;
    }
    
    /**
     * On verifie quels deplacements sont autorises
     * si aucun n'est autorise on force le joueur a combattre
     * sinon on lui demande la direction du deplacement
     * si cette direction est possible, on selectionne les dx et dy correspondant
     * sinon on lui redemande
     * On applique la translation
     * On actualise la matrice de position
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
                    break;
                case "NE":
                    if (pospossible[2]){
                        dx = -1;
                        dy = 1;
                    } else {
                        System.out.println("Direction non valide");
                        System.out.println("Choisir a nouveau");
                        this.deplacePJ();
                    } 
                    break;
                case "E":
                    if (pospossible[1]){
                        dx = -1;
                        dy = 0;
                    } else {
                        System.out.println("Direction non valide");
                        System.out.println("Choisir a nouveau");
                        this.deplacePJ();
                    } 
                    break;
                case "SE":
                    if (pospossible[0]){
                        dx = -1;
                        dy = -1;
                    } else {
                        System.out.println("Direction non valide");
                        System.out.println("Choisir a nouveau");
                        this.deplacePJ();
                    }   
                    break;
                case "S":
                    if (pospossible[3]){
                        dx = 0;
                        dy = -1;
                    } else {
                        System.out.println("Direction non valide");
                        System.out.println("Choisir a nouveau");
                        this.deplacePJ();
                    } 
                    break;
                case "SO":
                    if (pospossible[6]){
                        dx = 1;
                        dy = -1;
                    } else {
                        System.out.println("Direction non valide");
                        System.out.println("Choisir a nouveau");
                        this.deplacePJ();
                    } 
                    break;
                case "O":
                    if (pospossible[7]){
                        dx = 1;
                        dy = 0;
                    } else {
                        System.out.println("Direction non valide");
                        System.out.println("Choisir a nouveau");
                        this.deplacePJ();
                    } 
                    break;
                case "NO":
                    if (pospossible[8]){
                        dx = 1;
                        dy = 1;
                    } else {
                        System.out.println("Direction non valide");
                        System.out.println("Choisir a nouveau");
                        this.deplacePJ();
                    }
                    break;
                default:
                    System.out.println("Direction non valide");
                    System.out.println("Choisir a nouveau");
                    this.deplacePJ();
                    break;
            }
            int X = PJ.getPos().getX();
            int Y = PJ.getPos().getY();
            this.PJ.pos.Translate(dx, dy);
            this.posmonde[PJ.pos.getX()][PJ.pos.getY()] = this.posmonde[X][Y];
            this.posmonde[X][Y] = -1;
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
        int dx = -Integer.parseInt(rep3);
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
            while ((i<this.structcrea.size()-1)&&((posx!=X)||(posy!=Y))){
                i++;
                X = this.structcrea.get(i).pos.getX();
                Y = this.structcrea.get(i).pos.getY();
            }
            if(i==this.structcrea.size()&&((posx!=X)||(posy!=Y))){
                System.out.println("Creature non trouvée");
                this.ActionJoueur();
            }
            ((Combattant)(this.PJ)).combattre(this.structcrea.get(i));
            if (this.structcrea.get(i).getPtVie()<=0){
                Class classe = this.structcrea.get(i).getClass();
                String nomclasse = classe.getName();
                nomclasse = nomclasse.substring(23);
                System.out.println("Vous avez tue un "+nomclasse);
                this.posmonde[X][Y] = -1;
                this.structcrea.remove(i);
            }
        }
    }
    
    /**
     * On affiche les creatures autour
     * On definit quelle est l'action que le joueur effectue et on utilise la fonction associee
     */
    public void ActionJoueur(){
        this.mondeAutour(2);
        System.out.println("Voulez-vous : Deplacer ou Combattre");
        Scanner input1 = new Scanner(System.in);
        String choix = input1.next();
        switch (choix) {
            case "Deplacer":
                this.deplacePJ();
                break;
            case "Combattre":
                this.CombattrePJ();
                break;
            default:
                System.out.println("Action non reconnue");
                this.ActionJoueur();
                break;
        }
    }
    
    /**
     * @param k est la profondeur à laquel le personnage voit : k=0 signifie que le PJ ne voit que lui-meme
     * On recupere les coordonnées du PJ
     * on cree la matrice montrant les cases autour du PJ en remplissant les cases hors du monde par des 0
     * on affiche cette matrice en effectuant une transpose le nord en haut et le sud en bas     * 
     */
    public void mondeAutour(int k){
        int x = PJ.pos.getX();
        int y = PJ.pos.getY();
        int [][] myView = new int[2*k+1][2*k+1];
        for (int i = -k; i<=k; i++){
            for (int j = -k; j<=k; j++){
                if (Estdanslimite(x+i,y+j)){
                    myView[i+k][j+k] = posmonde[x+i][y+j];
                }
                else {
                    myView[i+k][j+k] = -2;
                }
            }
        }
        for (int i=2*k; i>=0; i--){
            for (int j=2*k; j>=0; j--){
                System.out.print(myView[j][i] + "\t");
            }
            System.out.println();
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
                if ((c instanceof Combattant combattant)&&(c instanceof Monstre)&&(c.pos.distance(PJ.pos)==1.)){
                    Class classe = c.getClass();
                    String nomclasse = classe.getName();
                    nomclasse = nomclasse.substring(23);
                    System.out.println("Je me fais attaquer par un "+nomclasse+" !");
                    combattant.combattre(PJ);
                }
                else{
                    
                    this.deplacealealimite(c);
                }
                /**if (c.pos.distance(tcloud.getPos())<tcloud.getTaille()){
                    c.setPtVie(c.getPtVie()-tcloud.getDegat());
                }*/
            }
        }
    }
    
}
