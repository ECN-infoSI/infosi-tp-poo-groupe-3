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
    public Personnage perso;
    
    public ArrayList<Utilisable> effets;
    
    public ArrayList<Utilisable> inventaire;
    
    public void choixPerso(){
        System.out.println("Indiquez le type de personnage que vous souhaitez jouer : ");
        Scanner input = new Scanner(System.in);
        String type = input.next();
        /*try {
            perso = Class.forName("org.centrale.objet.WoE."+type).newInstance();
        } catch (ClassNotFoundException e){
            System.out.println("Ce type de personnage n'existe pas.");
        } finally {                
            }*/
        switch (type) {
            case "Guerrier":
                perso = new Guerrier();
                break;
            case "Archer":
                perso = new Archer();
                break;
            default:
                System.out.println("Ce personnage n'est pas jouable.");
            }
        if (!perso.estJouable){
            System.out.println("Ce personnage ne peut pas etre joue");
            choixPerso();
        } else {
            System.out.println("Vous etes un personnage de type : "+type);
        }
    }
    
    public void choixNom(){
        System.out.println("Indiquez le nom de votre personnage : ");
        Scanner input = new Scanner(System.in);
        String nom = input.next();
        perso.nom = nom;
        System.out.println("Votre personnage s'appelle : "+nom);
    }
    
    public void ajoutUtil(Utilisable u){
        this.effets.add(u);
        u.affecter(perso);
    }
    
    public void finUtil(Utilisable u){
        u.desaffecter(perso);
        this.effets.remove(u);
    }
    
    public void estAffecte(){
        for (Utilisable u : this.effets) {
            int d = u.getDuree();
            u.setDuree(d-1);
            if (d <= 0){
                this.finUtil(u);
            }        
        }
    }
    
    public void ramasser(Utilisable u){
        this.inventaire.add(u);
    }
    
    public void utiliser(){
        Scanner input = new Scanner(System.in);
        System.out.print("Indiquez le numero de l'objet que vous souhaitez utiliser.");
        int i = input.nextInt();
        Utilisable u = this.inventaire.get(i);
        System.out.println("Vous avez choisi l'objet : "+u.getNom());
        this.inventaire.remove(u);
        this.ajoutUtil(u);
    }
    
    public void afficherInventaire(){
        Iterator<Utilisable> listIt = this.inventaire.iterator();
        int i = 0;
        while (listIt.hasNext()){
            System.out.println(i+" : "+listIt.next().getNom());
        }
    }
}
