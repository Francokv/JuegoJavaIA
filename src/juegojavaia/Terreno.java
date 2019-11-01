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
public class Terreno extends GameObject {

    @Override
    protected void init() {

        sprites = CargaImagenes.piso;
        int num = (int) (Math.random() * 50);
        if (num < 6) {
            spriteActual = sprites[num].getScaledInstance(PIXEL_CELDA, PIXEL_CELDA, Image.SCALE_DEFAULT);
        } else {
            spriteActual = sprites[6].getScaledInstance(PIXEL_CELDA, PIXEL_CELDA, Image.SCALE_DEFAULT);
        }
        transparente = true;
    }

    @Override
    protected void update() {

    }

}
