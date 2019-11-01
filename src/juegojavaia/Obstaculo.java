/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegojavaia;

import java.awt.Image;
import java.awt.image.BufferedImage;
import utilidades.CargaImagenes;

/**
 *
 * @author elnik
 */
public class Obstaculo extends GameObject {

    @Override
    protected void init() {
        prioridad = 6;
        transparente = false;

        Image aux = CargaImagenes.obstaculos[(int) (Math.random() * CargaImagenes.obstaculos.length)];
        if (aux.getHeight(null) / aux.getWidth(null) == 1) {
            spriteActual = aux;
            
        } else {
            spriteActual = aux;
            posicion.y = 1;
            escala.y = 1;
        }
        
    }
}
