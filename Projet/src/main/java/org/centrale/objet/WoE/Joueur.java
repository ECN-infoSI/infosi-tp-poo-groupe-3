    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author thoma
 */
public class Joueur {

    /**
     *
     */
    public Personnage perso;
    
    /**
     *
     */
    public ArrayList<Utilisable> effets;
    
    /**
     *
     */
    public ArrayList<Utilisable> inventaire;
    
    /**
     *
     */
    public Joueur(){
        this.effets = new ArrayList();
        this.inventaire = new ArrayList();
    }
    
    /**
     * Demande au joueur le type de perso qu'il souhaite jouer
     * @param monde 
     */
    public void choixPerso(World monde){
        boolean mauvaistype = true;
        Scanner input = new Scanner(System.in);
        System.out.print("Indiquez le type de personnage que vous souhaitez jouer (Guerrier ou Archer): ");
        String type = input.next();
        while (mauvaistype){
            mauvaistype = false;
        /*try {
            perso = Class.forName("org.centrale.objet.WoE."+type).newInstance();
        } catch (ClassNotFoundException e){
            System.out.println("Ce type de personnage n'existe pas.");
        } finally {                
            }*/
            switch (type) {
                case "Guerrier":
                    Guerrier g = new Guerrier();
                    perso = g.guerrierAlea(monde);
                    break;
                case "Archer":
                    Archer a = new Archer();
                    perso = a.archerAlea(monde);
                    break;
                default:
                    System.out.println("Ce personnage n'est pas jouable.");
                    mauvaistype = true;
                    Scanner input1 = new Scanner(System.in);
                    System.out.print("Indiquez le type de personnage que vous souhaitez jouer : ");
                    type = input1.next();
            }
        }
        System.out.println("Vous etes un personnage de type : "+type);
    }
    
    /**
     * Demande au joueur le nom de son perso
     */
    public void choixNom(){
        System.out.print("Indiquez le nom de votre personnage : ");
        Scanner input = new Scanner(System.in);
        String nom = input.next();
        perso.nom = nom;
        System.out.println("Votre personnage s'appelle : "+nom);
    }
    
    /**
     * Le joueur utilise un objet de son inventaire
     * @param u 
     */
    public void ajoutUtil(Utilisable u){
        this.effets.add(u);
        u.affecter(perso);
    }
    
    /**
     * L'effet d'un objet utilise prend fin
     * @param u 
     */
    public void finUtil(Utilisable u){
        u.desaffecter(perso);
        this.effets.remove(u);
    }
    
    /**
     * Le perso est affecte par les objets utilises
     */
    public void estAffecte(){
        for (Utilisable u : this.effets) {
            int d = u.getDuree();
            u.setDuree(d-1);
            if (d <= 0){
                this.finUtil(u);
            }        
        }
    }
    
    /**
     * Le perso ramasse un objet
     * @param u 
     */
    public void ramasser(Utilisable u){
        this.inventaire.add(u);
    }
    
    /**
     * Le perso utilise un objet
     */
    public void utiliser(){
        Scanner input = new Scanner(System.in);
        if (this.inventaire.isEmpty()){
            System.out.println("Votre inventaire est vide");
        } else {
            this.afficherInventaire();
            System.out.print("Indiquez le numero de l'objet que vous souhaitez utiliser : ");
            int i = input.nextInt();
            if (i>=this.inventaire.size()){
                System.out.println("Tu n'as pas autant d'objet !");
                this.utiliser();
            }else{
                Utilisable u = this.inventaire.get(i);
            System.out.println("Vous avez choisi l'objet : "+u.getNom());
            this.inventaire.remove(u);
            this.ajoutUtil(u);
            }
        }
    }
    
    /**
     * Affichage de l'inventaire
     */
    public void afficherInventaire(){
        if (this.inventaire.isEmpty()){
            System.out.println("Votre inventaire est vide");
        } else {
            Iterator<Utilisable> listIt = this.inventaire.iterator();
            int i = 0;
            while (listIt.hasNext()){
                System.out.println(i+" : "+listIt.next().getNom());
            }
        }
    }
    
    /*public void afficherEffets(){
        if (this.effets.isEmpty()){
            System.out.println("Aucun effet en cours.");
        } else {
            Iterator<Utilisable> listIt = this.effets.iterator();
            while (listIt.hasNext()){
                Class classe = listIt.next().getClass();
                String nClasse = classe.getName();
                nClasse = nClasse.substring(23);
                switch (nClasse){
                    case "Carotte":
                        Carotte c = (Carotte)listIt.next();
                        System.out.println("Carotte : distance maximale d'attaque augmente de "+c.getIntensite());
                        break;
                    case "Lait":
                        Lait l = (Lait)listIt.next();
                        System.out.println("Lait : pourcentage de parade reduit de "+l.getIntensite());
                }
            }
        }
    }*/
}
