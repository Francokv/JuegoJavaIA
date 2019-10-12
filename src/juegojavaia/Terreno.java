/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegojavaia;

import java.awt.image.BufferedImage;
import utilidades.CargaImagenes;

/**
 *
 * @author elnik
 */
public class Terreno extends GameObject{
    public enum Tipo {PISO, PARED}
    public Tipo tipo = Tipo.PISO;
    private BufferedImage[] texturas;
    private int count;
    
    public Terreno (Tipo tipo){
        if(tipo == null){
            this.tipo = Tipo.PISO;
        }
        else{
            this.tipo = tipo;
        }
        
        init();
    }
    
    @Override
    protected void init(){
        if(tipo == Tipo.PISO){
            texturas = CargaImagenes.piso;
            int num = (int) (Math.random() * 50);
            if(num < 6){
                spriteActual = texturas[num];
            }else{
                spriteActual = texturas[6];
            }
            transparente = true;
        }else{
            texturas = CargaImagenes.pared;
            //spriteActual = texturas[4];
            transparente = false;
        }
        
    }
    
    @Override
    protected void update(){

    }
    
}
