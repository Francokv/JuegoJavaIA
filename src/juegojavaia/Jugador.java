/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegojavaia;

import IA.BusquedaAnchura;
import IA.Estado;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import utilidades.CargaImagenes;
import utilidades.Input;
import utilidades.Vector2Int;

/**
 *
 * @author elnik
 */
public class Jugador extends GameObject {

    public static Jugador jugador;
    
    boolean enMovimiento;
    int fpsCount;
    float velocidad = 3f; // se mide en celdas por segundo
    int velAnimacion = 12;
    int animCount;

    float t_invulnerable = 0.7f;
    float t_totalInv = 0;

    boolean invulnerable = false;

    Image[] animacionActual;

    public ArrayList<Estado> pasos = new ArrayList<Estado>();

    @Override
    protected void init() {
        jugador = this;
        nombre = "Jugador 1";
        tag = "jugador";
        sprites = CargaImagenes.jugador;
        transparente = true;
        spriteActual = sprites[1];
        setAnimacion(Arrays.copyOfRange(sprites, 0, 3));
        prioridad = 5;
        pasos = new ArrayList<Estado>();
    }

    @Override
    protected void update() {
        if (Lienzo.vidas == 0) {
                JOptionPane.showMessageDialog(null, "Haz perdido, lograste obtener " + Lienzo.recompensas + " de " + Lienzo.recompensasTotales + " recompensas");
                System.exit(0);
        }
        
        if (enMovimiento) {
            animacion();
        } else {
            moverIA();
        }
        if (!enMovimiento) {
            spriteActual = animacionActual[1];
        }

        if (t_totalInv > t_invulnerable) {
            t_totalInv = 0;
            invulnerable = false;
        }
        if (invulnerable) {
            t_totalInv += FRAME_TIME;
        }
    }

    private void moverIA() {
        Vector2Int p = new Vector2Int();
        Celda destino;

        if (pasos == null || pasos.isEmpty()) {
            if(Recompensa.recompensas.isEmpty()){
                pasos = BusquedaAnchura.getRuta(celda.cooredenadas, Final.fin.celda.cooredenadas);
            }else{
                Recompensa objetivo = Recompensa.recompensas.get(0);
                double distancia = celda.cooredenadas.distancia(objetivo.celda.cooredenadas);

                for(Recompensa r : Recompensa.recompensas){
                    if(celda.cooredenadas.distancia(r.celda.cooredenadas) < distancia){
                        objetivo = r;
                        distancia = celda.cooredenadas.distancia(r.celda.cooredenadas);
                    }
                }
                Recompensa.recompensas.remove(objetivo);
                pasos = BusquedaAnchura.getRuta(celda.cooredenadas, objetivo.celda.cooredenadas);
            }
            
        }

        p = pasos.get(pasos.size() - 1).posicion;
        pasos.remove(pasos.size() - 1);

        int horizontal = p.x -celda.cooredenadas.x ;
        int vertical = celda.cooredenadas.y - p.y;

        if (horizontal != 0) {
            destino = escenario.getCelda(celda.cooredenadas.x + horizontal, celda.cooredenadas.y);
            if (destino != null && destino.estaLibre()) {
                moverA(destino.cooredenadas);
                posicion.x = -horizontal;
                enMovimiento = true;
                //settear animacion
                if (posicion.x < 0) {
                    setAnimacion(Arrays.copyOfRange(sprites, 6, 9));
                } else {
                    setAnimacion(Arrays.copyOfRange(sprites, 3, 6));
                }

                //Lienzo.vidas--;
            }

        } else if (vertical != 0) {
            destino = escenario.getCelda(celda.cooredenadas.x, celda.cooredenadas.y - vertical);
            if (destino != null && destino.estaLibre()) {
                moverA(destino.cooredenadas);
                posicion.y = -vertical;
                enMovimiento = true;

                if (posicion.y < 0) {
                    setAnimacion(Arrays.copyOfRange(sprites, 9, 12));
                } else {
                    setAnimacion(Arrays.copyOfRange(sprites, 0, 3));
                }
                //Lienzo.vidas--;
            }
        }
    }

    private void mover() {
        Celda destino;
        if (Input.horizontal != 0) {
            destino = escenario.getCelda(celda.cooredenadas.x + Input.horizontal, celda.cooredenadas.y);
            if (destino != null && destino.estaLibre()) {
                moverA(destino.cooredenadas);
                posicion.x = -Input.horizontal;
                enMovimiento = true;
                //settear animacion
                if (posicion.x < 0) {
                    setAnimacion(Arrays.copyOfRange(sprites, 6, 9));
                } else {
                    setAnimacion(Arrays.copyOfRange(sprites, 3, 6));
                }

                //Lienzo.vidas--;
            }

        } else if (Input.vertical != 0) {
            destino = escenario.getCelda(celda.cooredenadas.x, celda.cooredenadas.y - Input.vertical);
            if (destino != null && destino.estaLibre()) {
                moverA(destino.cooredenadas);
                posicion.y = -Input.vertical;
                enMovimiento = true;

                if (posicion.y < 0) {
                    setAnimacion(Arrays.copyOfRange(sprites, 9, 12));
                } else {
                    setAnimacion(Arrays.copyOfRange(sprites, 0, 3));
                }
                //Lienzo.vidas--;
            }
        }

        /*
         if(Lienzo.vidas == 0){
         JOptionPane.showMessageDialog(null, "Haz perdido, lograste obtener "+ Lienzo.recompensas+" de "+ Lienzo.recompensasTotales+" recompensas");
         }
         */
    }

    private void animacion() {

        if (fpsCount % (FPS / velAnimacion) == 0 && enMovimiento) {
            spriteActual = animacionActual[(animCount % animacionActual.length)];
            animCount++;
            fpsCount = 0;
        }

        if (posicion.x < 0) {
            posicion.x += velocidad * FRAME_TIME;
            if (posicion.x > 0) {
                posicion.x = 0;
                enMovimiento = false;
                spriteActual = animacionActual[1];
            }
        } else if (posicion.x > 0) {
            posicion.x -= velocidad * FRAME_TIME;
            if (posicion.x < 0) {
                posicion.x = 0;
                enMovimiento = false;
            }
        } else if (posicion.y < 0) {
            posicion.y += velocidad * FRAME_TIME;
            if (posicion.y > 0) {
                posicion.y = 0;
                enMovimiento = false;
            }
        } else if (posicion.y > 0) {
            posicion.y -= velocidad * FRAME_TIME;
            if (posicion.y < 0) {
                posicion.y = 0;
                enMovimiento = false;
            }
        }

        fpsCount++;
    }

    private void setAnimacion(Image[] anim) {
        animacionActual = new Image[anim.length + 1];
        for (int i = 0; i < anim.length; i++) {
            animacionActual[i] = anim[i];
        }
        animacionActual[animacionActual.length - 1] = animacionActual[1];
    }

    public void damage(int val) {
        if (!invulnerable) {
            Lienzo.vidas -= val;
        }
        invulnerable = true;
    }
}
