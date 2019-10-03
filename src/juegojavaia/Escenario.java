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
                celdas[i][j].posicion = new Vector2Int(i * PIXEL_CELDA,j * PIXEL_CELDA);
                celdas[i][j].cooredenadas = new Vector2Int(i,j);
            }
        }
    }

    public void generarEscenaAleatoria() {
        int c_jugador = 1;
        int c_recompensa = 3;
        int c_enemigo = 3;
        int c_final = 1;
        
        Jugador jugador = new Jugador();
        jugador.escenario = this;
        celdas[2][3].agregarObjeto(jugador);
        
        celdas[4][3].agregarObjeto(new Moneda (Moneda.Tipo.COBRE));
        celdas[5][7].agregarObjeto(new Moneda (Moneda.Tipo.ORO));
        celdas[2][14].agregarObjeto(new Moneda (Moneda.Tipo.PLATA));
        
        for (int i = 0; i < CANTIDAD_CELDAS_X; i++) {
            for (int j = 0; j < CANTIDAD_CELDAS_Y; j++) {
                celdas[i][j].agregarObjeto(new Terreno(null));
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
        
        for(GameObject go : objetos){
            go.dibujar(g);
        }
    }
    
    public Celda getCelda (int x , int y){
        if(x >= 0 && y >= 0 && x < CANTIDAD_CELDAS_X && y <CANTIDAD_CELDAS_Y){
            return celdas[x][y];
        }
        return null;
    }
}
