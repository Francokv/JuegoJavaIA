/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegojavaia;

import java.awt.Toolkit;
import utilidades.Vector2Int;

/**
 *
 * @author elnik
 */
public interface Constantes {
    final int FPS = 30;
    final int PIXEL_CELDA = 64;

    final int BORDE = 20;
    final float FRAME_TIME = (float)1/FPS;
    public final int ANCHO_PANTALLA=Toolkit.getDefaultToolkit().getScreenSize().width; //1920
    public final int LARGO_PANTALLA=Toolkit.getDefaultToolkit().getScreenSize().height; //1080
 
    public final int CANTIDAD_CELDAS_X=(ANCHO_PANTALLA )/PIXEL_CELDA;
    public final int CANTIDAD_CELDAS_Y=(LARGO_PANTALLA-64)/PIXEL_CELDA;
}
