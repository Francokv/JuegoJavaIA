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
import static juegojavaia.Constantes.FPS;
import static juegojavaia.Constantes.FRAME_TIME;
import utilidades.CargaImagenes;
import utilidades.Input;
import utilidades.Vector2Int;

/**
 *
 * @author elnik
 */
public class Adversario extends GameObject {

    int damage = 1;

    boolean enMovimiento;
    int fpsCount;
    float velocidad = 5f; // se mide en celdas por segundo
    int velAnimacion = 12;
    int animCount;

    float t_invulnerable = 0.7f;
    float t_totalInv = 0;

    Image[] animacionActual;

    public ArrayList<Estado> pasos = new ArrayList<Estado>();

    @Override
    protected void init() {
        sprites = CargaImagenes.adversario;
        transparente = true;
        spriteActual = sprites[1];
        setAnimacion(Arrays.copyOfRange(sprites, 0, 3));
        prioridad = 5;
    }

    @Override
    protected void update() {

        if (enMovimiento) {
            animacion();
        } else {
            moverIA();
        }
        if (!enMovimiento) {
            spriteActual = animacionActual[1];
        }

    }

    private void mover() {
        Celda destino;

        if (Math.random() < 0.5) {
            if ((destino = escenario.getCelda(celda.cooredenadas.x + 1, celda.cooredenadas.y)) != null && destino.estaLibre() && Math.random() < 0.5) {
                moverA(destino.cooredenadas);
                posicion.x = -1f;
                enMovimiento = true;
                //settear animacion

                setAnimacion(Arrays.copyOfRange(sprites, 6, 9));

            } else if ((destino = escenario.getCelda(celda.cooredenadas.x - 1, celda.cooredenadas.y)) != null && destino.estaLibre()) {
                moverA(destino.cooredenadas);
                posicion.x = +1f;
                enMovimiento = true;
                //settear animacion
                setAnimacion(Arrays.copyOfRange(sprites, 3, 6));

            }
        } else {
            if ((destino = escenario.getCelda(celda.cooredenadas.x, celda.cooredenadas.y + 1)) != null && destino.estaLibre() && Math.random() < 0.5) {
                moverA(destino.cooredenadas);
                posicion.y = +1f;
                enMovimiento = true;
                //settear animacion
                setAnimacion(Arrays.copyOfRange(sprites, 0, 3));
            } else if ((destino = escenario.getCelda(celda.cooredenadas.x, celda.cooredenadas.y - 1)) != null && destino.estaLibre()) {
                moverA(destino.cooredenadas);
                posicion.y = -1f;
                enMovimiento = true;
                //settear animacion
                setAnimacion(Arrays.copyOfRange(sprites, 9, 12));
            }
        }

    }

    private void moverIA() {
        Vector2Int p = new Vector2Int();
        Celda destino;

        pasos = BusquedaAnchura.getRuta(celda.cooredenadas, Jugador.jugador.celda.cooredenadas);
        if (pasos != null && !pasos.isEmpty()) {
            pasos.remove(pasos.size() - 1);
        }

        if (pasos != null && !pasos.isEmpty()) {
            p = pasos.get(pasos.size() - 1).posicion;
            pasos.remove(pasos.size() - 1);

            int horizontal = p.x - celda.cooredenadas.x;
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

    @Override
    protected void onCollision(ArrayList<GameObject> coll) {
        for (GameObject go : coll) {
            if (go.tag.equals("jugador")) {
                ((Jugador) go).damage(damage);
            }
        }
    }
}
