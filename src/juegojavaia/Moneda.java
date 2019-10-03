/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegojavaia;

import java.util.ArrayList;
import utilidades.CargaImagenes;

/**
 *
 * @author elnik
 */
public class Moneda extends GameObject{
    public enum Tipo {ORO, PLATA, COBRE};
    
    int valor;
    Tipo tipo;
    int velAnimacion = 10;
    
    int fpsCount;
    int spriteIndex;
    
    public Moneda(Tipo tipo) {
        this.tipo = tipo;
        if(tipo == null){
            this.tipo = Tipo.COBRE;
        }
        init();
    }
    
    @Override
    protected void init (){
        prioridad = 4;
        transparente = true;
        if(this.tipo == Tipo.COBRE){
            valor = 1;
            sprites = CargaImagenes.monedaCobre;
        }
        else if(this.tipo == Tipo.PLATA){
            valor = 3 ;
            sprites = CargaImagenes.monedaPlata;
        }
        else{
            valor = 5;
            sprites = CargaImagenes.monedaOro;
        }
        
        spriteActual = sprites[0];
    }
    
    @Override 
    protected void update (){
        fpsCount++;
        if(fpsCount == (FPS/velAnimacion)){
            if(spriteIndex == sprites.length ){
                spriteIndex = 0;
            }
            spriteActual = sprites[spriteIndex];
            spriteIndex++;
            fpsCount=0;
        }
    }
    
    @Override
    protected void onCollision(ArrayList<GameObject> coll){
        for(GameObject go : coll){
            if(go.tag.equals("jugador")){
                celda.removerObjeto(this);
                System.out.println("coll");
            }
        }
    }

}
