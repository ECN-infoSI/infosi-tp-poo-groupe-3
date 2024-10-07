/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;
import java.util.Scanner;

/**
 *
 * @author thoma
 */
public class Joueur {
    private Personnage perso;
    
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
                System.out.println("Ce personnage n'existe pas.");
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
}
