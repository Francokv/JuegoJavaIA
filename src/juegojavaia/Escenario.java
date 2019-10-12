/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegojavaia;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JComponent;
import utilidades.Vector2Int;

/**
 *
 * @author elnik
 */
public class Escenario extends JComponent implements Constantes {

    public Celda[][] celdas;
    public Image fondo;

    public Escenario() {
        init();
    }

    private void init() {
        celdas = new Celda[CANTIDAD_CELDAS_X][CANTIDAD_CELDAS_Y];

        for (int i = 0; i < CANTIDAD_CELDAS_X; i++) {
            for (int j = 0; j < CANTIDAD_CELDAS_Y; j++) {
                celdas[i][j] = new Celda();
                celdas[i][j].posicion = new Vector2Int(i * PIXEL_CELDA, j * PIXEL_CELDA);
                celdas[i][j].cooredenadas = new Vector2Int(i, j);
            }
        }
    }

    public void generarEscenaAleatoria() {
        int jx = 0, jy = 4;//posicion inicial del jugador
        int c_recompensa = 4;//cantidad de recompnsas
        int c_adversarios = 3;//cantidad de enemigos
        int finalx = 6, finaly = 7;// 

        //crear jugador
        Jugador jugador = new Jugador();
        jugador.escenario = this;
        celdas[jx][jy].agregarObjeto(jugador);
        celdas[finalx][finalx].agregarObjeto(new Final());

        //generar terreno
        for (int i = 0; i < CANTIDAD_CELDAS_X; i++) {
            for (int j = 0; j < CANTIDAD_CELDAS_Y; j++) {
                celdas[i][j].agregarObjeto(new Terreno(null));
            }
        }

        //generar obstaculos
        for (int i = 0; i < CANTIDAD_CELDAS_X; i++) {
            for (int j = 0; j < CANTIDAD_CELDAS_Y; j++) {
                if (Math.random() < 0.2 && i != jx && j != jy && i != finalx && j != finaly) {
                    celdas[i][j].agregarObjeto(new Obstaculo());
                }
            }
        }

        //generar Adversarios
        while (c_adversarios != 0) {
            for (int i = 0; i < CANTIDAD_CELDAS_X; i++) {
                for (int j = 0; j < CANTIDAD_CELDAS_Y; j++) {
                    if (celdas[i][j].estaLibre() && Math.random() < 0.05 && c_adversarios != 0 && i != jx && j != jy) {
                        Adversario nuevo = new Adversario();
                        nuevo.escenario = this;
                        celdas[i][j].agregarObjeto(nuevo);
                        c_adversarios--;
                    }
                }
            }
        }
        
        //generar recompensas
        while (c_recompensa != 0) {
            for (int i = 0; i < CANTIDAD_CELDAS_X; i++) {
                for (int j = 0; j < CANTIDAD_CELDAS_Y; j++) {
                    if (celdas[i][j].estaLibre() && Math.random() < 0.05 && c_recompensa != 0 && i != jx && j != jy) {
                        celdas[i][j].agregarObjeto(new Recompensa());
                        c_recompensa--;
                    }
                }
            }
        }

    }

    @Override
    public void update(Graphics g) {
        ArrayList<GameObject> objetos = new ArrayList<GameObject>();
        for (int i = 0; i < CANTIDAD_CELDAS_X; i++) {
            for (int j = 0; j < CANTIDAD_CELDAS_Y; j++) {
                celdas[i][j].actualizarObjetos();
            }
        }

        for (int i = 0; i < CANTIDAD_CELDAS_X; i++) {
            for (int j = 0; j < CANTIDAD_CELDAS_Y; j++) {
                //celdas[i][j].update(g);
                objetos.addAll(celdas[i][j].objetos);
            }
        }

        Collections.sort(objetos, new OrdenarPorPrioridad());

        for (GameObject go : objetos) {
            go.dibujar(g);
        }
    }

    public Celda getCelda(int x, int y) {
        if (x >= 0 && y >= 0 && x < CANTIDAD_CELDAS_X && y < CANTIDAD_CELDAS_Y) {
            return celdas[x][y];
        }
        return null;
    }
}
