/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegojavaia;

import java.awt.image.BufferedImage;
import utilidades.CargaImagenes;
import utilidades.Input;

/**
 *
 * @author elnik
 */
public class Jugador extends GameObject {

    boolean enMovimiento;
    int fpsCount;
    float velocidad = 7f;
    int velAnimacion = 10;
    int animCount;

    @Override
    protected void init() {
        tag = "jugador";
        sprites = CargaImagenes.jugador;
        spriteActual = sprites[1];
        prioridad = 5;
    }

    @Override
    protected void update() {
        if (enMovimiento) {
            animacion();
        } else {
            mover();
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
            }

        } else if (Input.vertical != 0) {
            destino = escenario.getCelda(celda.cooredenadas.x, celda.cooredenadas.y - Input.vertical);
            if (destino != null && destino.estaLibre()) {
                moverA(destino.cooredenadas);
                posicion.y = -Input.vertical;
                enMovimiento = true;
            }
        }
    }

    private void animacion() {

        if (posicion.x != 0) {
            if (posicion.x < 0) {
                posicion.x += (float) velocidad / FPS;
                if (posicion.x >= 0) {
                    posicion.x = 0;
                    spriteActual = sprites[7];
                    fpsCount = 0;
                    animCount = 0;
                } else {
                    if (fpsCount % (FPS / velAnimacion) == 0) {
                        spriteActual = sprites[6 + (animCount % 3)];
                        animCount++;

                    }
                }
            } else {
                posicion.x -= (float) velocidad / FPS;
                if (posicion.x <= 0) {
                    posicion.x = 0;
                    spriteActual = sprites[4];
                    fpsCount = 0;
                    animCount = 0;
                } else {
                    if (fpsCount % (FPS / velAnimacion) == 0) {
                        spriteActual = sprites[3 + (animCount % 3)];
                        animCount++;
                    }
                }
            }
        } else if (posicion.y != 0) {
            if (posicion.y < 0) {
                posicion.y += (float) velocidad / FPS;
                if (posicion.y >= 0) {
                    posicion.y = 0;
                    spriteActual = sprites[11];
                    fpsCount = 0;
                    animCount = 0;
                } else {
                    if (fpsCount % (FPS / velAnimacion) == 0) {
                        spriteActual = sprites[10 + (animCount % 3)];
                        animCount++;

                    }
                }
            } else {
                posicion.y -= (float) velocidad / FPS;
                if (posicion.y <= 0) {
                    posicion.y = 0;
                    spriteActual = sprites[1];
                    fpsCount = 0;
                    animCount = 0;
                } else {
                    if (fpsCount % (FPS / velAnimacion) == 0) {
                        spriteActual = sprites[(animCount % 3)];
                        animCount++;
                    }
                }
            }
        } else {
            enMovimiento = false;
        }
        fpsCount++;
    }
}
