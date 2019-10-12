/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegojavaia;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import utilidades.CargaImagenes;

/**
 *
 * @author elnik
 */
public class Recompensa extends GameObject {

    int valor = 5;
    int velAnimacion = 10;
    int fpsCount;
    int spriteIndex;

    @Override
    protected void init() {
        prioridad = 4;
        transparente = true;

        valor = 5;
        sprites = CargaImagenes.monedaOro;

        spriteActual = sprites[0];
    }

    @Override
    protected void update() {
        fpsCount++;
        if (fpsCount == (FPS / velAnimacion)) {
            if (spriteIndex == sprites.length) {
                spriteIndex = 0;
            }
            spriteActual = sprites[spriteIndex];
            spriteIndex++;
            fpsCount = 0;
        }
    }

    @Override
    protected void onCollision(ArrayList<GameObject> coll) {
        for (GameObject go : coll) {
            if (go.tag.equals("jugador")) {
                celda.removerObjeto(this);
                Lienzo.recompensas++;
                if(Lienzo.recompensas == Lienzo.recompensasTotales){
                    JOptionPane.showMessageDialog(null,"Ya tienes todas las recompensas!! ahora dirigete al portal para escapar.");
                }
            }
        }
    }

}
