/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegojavaia;

import utilidades.CargaImagenes;

/**
 *
 * @author elnik
 */
public class Obstaculo extends GameObject{

    @Override
    protected void init (){
        prioridad = 6;
        transparente = false;
        spriteActual = CargaImagenes.obstaculos[(int)(Math.random() * CargaImagenes.obstaculos.length)];
        posicion.y = spriteActual.getHeight() / PIXEL_CELDA - 1;
    }
}
