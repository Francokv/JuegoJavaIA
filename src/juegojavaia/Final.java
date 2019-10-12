/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegojavaia;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import utilidades.CargaImagenes;

/**
 *
 * @author elnik
 */
public class Final extends GameObject {
    @Override
    protected void init() {

        spriteActual = CargaImagenes.portal;
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
