/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegojavaia;

import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JComponent;
import utilidades.Vector2Int;

/**
 *
 * @author elnik
 */
public class Celda extends JComponent implements Constantes {

    public Vector2Int posicion = new Vector2Int();
    public Vector2Int cooredenadas = new Vector2Int();
    public ArrayList<GameObject> objetos = new ArrayList<GameObject>();

    ArrayList<GameObject> objetosAnteriores = objetos;

    public Celda() {
        objetosAnteriores = (ArrayList<GameObject>) objetos.clone();
    }

    @Override
    public void update(Graphics g) {
        for (GameObject go : objetos) {
            if (go.spriteActual != null && go.redibujar) {
                g.drawImage(go.spriteActual, posicion.x + (int) (go.posicion.x * PIXEL_CELDA), posicion.y - (int) (go.posicion.y * PIXEL_CELDA), null);
            }
            //dispara evento de colision
            if (objetos.size() > 1) {
                ArrayList<GameObject> coliciones = (ArrayList<GameObject>) objetos.clone();
                coliciones.remove(go);
                go.onCollision(coliciones);
            }
        }

    }

    public void agregarObjeto(GameObject go) {
        if (!objetos.contains(go)) {
            objetos.add(go);
            go.celda = this;
            Collections.sort(objetos, new OrdenarPorPrioridad());
        }
    }

    public void removerObjeto(GameObject go) {
        objetos.remove(go);
    }

    public boolean estaLibre() {
        for (GameObject go : objetos) {
            if (!go.transparente) {
                return false;
            }
        }
        return true;
    }

    public void actualizarObjetos() {
        objetosAnteriores = (ArrayList<GameObject>) objetos.clone();
        for (GameObject go : objetosAnteriores) {
            if (objetosAnteriores.size() > 1) {
                ArrayList<GameObject> colisiones = (ArrayList<GameObject>) objetosAnteriores.clone();
                colisiones.remove(go);
                go.onCollision(colisiones);
            }
            go.update();
        }
    }

}
