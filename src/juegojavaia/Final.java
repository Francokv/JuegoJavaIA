/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegojavaia;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import utilidades.CargaImagenes;

/**
 *
 * @author elnik
 */
public class Final extends GameObject {
    
    
    public static Final fin; 
    @Override
    
    protected void init() {
        fin = this;
        spriteActual = CargaImagenes.portal.getScaledInstance(PIXEL_CELDA, PIXEL_CELDA, Image.SCALE_DEFAULT);
        transparente = true;
        prioridad = 2;
    }
    @Override
    protected void onCollision(ArrayList<GameObject> coll) {
        for (GameObject go : coll) {
            if (go.tag.equals("jugador")) {
                if(Lienzo.recompensas == Lienzo.recompensasTotales){
                    JOptionPane.showMessageDialog(null,"Haz ganado!!");
                    System.exit(0);
                }
            }
        }
    }
}
