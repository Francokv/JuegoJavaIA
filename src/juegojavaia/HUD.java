/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegojavaia;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;
import static juegojavaia.Constantes.PIXEL_CELDA;
import utilidades.CargaImagenes;
import utilidades.Vector2Int;

/**
 *
 * @author elnik
 */
public class HUD implements Constantes{
    public static int puntaje;
    public static int tiempo;
    public static int vidas;
    
    Vector2Int posicion = new Vector2Int();
    
    public static void agregarPuntaje (int puntos){
        System.out.println("El jugador obtiene "+ puntos + (puntos == 1 ? " punto":" puntos"));
        puntaje += puntos;
        System.out.println("Nueva puntuación : "+ puntaje);
    }

    public static void update(Graphics g) {
        for(int i = 0; i < 3; i++ ){
            g.drawImage(CargaImagenes.corazones[i], i * PIXEL_CELDA, 0, null);
        }
    }
}
