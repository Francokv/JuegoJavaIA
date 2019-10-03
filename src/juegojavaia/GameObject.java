/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegojavaia;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import utilidades.Vector2;
import utilidades.Vector2Int;

/**
 *
 * @author elnik
 */
public class GameObject implements Constantes{
    public String tag = new String ();
    public String nombre;
    public BufferedImage spriteActual;
    public BufferedImage[] sprites;
    public Celda celda;
    public Escenario escenario;
    public int prioridad;
    public boolean transparente;
    public Vector2 posicion = new Vector2();
    public boolean redibujar = true;
 
    
    public GameObject (){
        
        init();
    }
    
    protected void init(){
        
    }
    
    protected void onCollision(ArrayList<GameObject> coll){
        
    }
    
    protected void update(){
        
    }
    
    protected void destruirObjeto(){
        celda.removerObjeto(this);
        celda = null;
    }

    public Vector2 getPosicion() {
        return posicion;
    }

    public void setPosicion(Vector2Int p) {

    }
    
    public void moverA (Vector2Int p){
        if(p.x >=0 && p.y >= 0 && p.x < CANTIDAD_CELDAS_X && p.y < CANTIDAD_CELDAS_Y){
                    celda.removerObjeto(this);
                    celda = escenario.celdas[p.x][p.y];
                    celda.agregarObjeto(this);
                    System.out.println("x : "+p.x + "  y : "+p.y);
        }
    }
    
    public void dibujar (Graphics g){
        g.drawImage(spriteActual, celda.posicion.x + (int)(posicion.x * PIXEL_CELDA), celda.posicion.y - (int)(posicion.y * PIXEL_CELDA), null);
    }
}

class OrdenarPorPrioridad implements Comparator<GameObject> 
{ 
    // Used for sorting in ascending order of 
    // roll number 
    public int compare(GameObject a, GameObject b) 
    { 
        return a.prioridad - b.prioridad; 
    } 
} 
