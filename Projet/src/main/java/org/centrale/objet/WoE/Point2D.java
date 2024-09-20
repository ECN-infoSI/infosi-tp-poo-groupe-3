/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author titou
 */
public class Point2D {
    private int x;
    private int y;
    
    public Point2D(){
        x = 0;
        y = 0;
    }
    
    public Point2D(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public Point2D(Point2D p){
        x = p.x;
        y = p.y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setPosition(int x, int y){
        setX(x);
        setY(y);
    }
    
    public void Translate(int dx, int dy){
        setY(getY() + dy);
        setX(getX() + dx);
    }
    
    public void affiche(){
        System.out.println("["+x+"; "+y+"]");
    }
    
    public float distance(Point2D p){
        float a = this.getY() - p.getY();
        float b = this.getX() - p.getX();
        float d;
        d = (float)Math.sqrt(a*a + b*b);
        return d;
    }
}
