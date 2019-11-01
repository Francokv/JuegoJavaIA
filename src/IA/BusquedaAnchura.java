/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TimerTask;
import juegojavaia.Celda;
import juegojavaia.Escenario;
import juegojavaia.GameObject;
import utilidades.Vector2Int;

/**
 *
 * @author Alumno
 */
public class BusquedaAnchura extends TimerTask {

    public static Escenario escenario;

    public static ArrayList<Estado> getRuta(Vector2Int inicio, Vector2Int objetivo) {

        if (inicio.equals(objetivo) || escenario.getCelda(objetivo.x, objetivo.y) == null) {
            return null;
        }

        ArrayList<Estado> colaEstados = new ArrayList<>();
        ArrayList<Estado> historial = new ArrayList<>();

        colaEstados.add(new Estado(inicio, null));

        boolean exito = false;
        while (!colaEstados.isEmpty() && !exito) {
            OrdenarPorDistancia ord = new OrdenarPorDistancia();
            ord.objetivo = objetivo;
            Collections.sort(colaEstados, ord);
            Estado actual = colaEstados.get(0);
            Vector2Int p = actual.posicion;
            colaEstados.remove(0);
            Celda celda;
            System.out.println("x " + p.x + " y " + p.y);
            if ((celda = escenario.getCelda(p.x + 1, p.y)) != null && celda.estaLibre()) {
                Estado nuevo = new Estado(celda.cooredenadas, actual);
                if (!historial.contains(nuevo)) {
                    historial.add(nuevo);
                    colaEstados.add(nuevo);
                    if (nuevo.posicion.equals(objetivo)) {
                        exito = true;
                    }
                }
            }
            if ((celda = escenario.getCelda(p.x - 1, p.y)) != null && celda.estaLibre() && !exito) {
                Estado nuevo = new Estado(celda.cooredenadas, actual);
                if (!historial.contains(nuevo)) {
                    historial.add(nuevo);
                    colaEstados.add(nuevo);
                    if (nuevo.posicion.equals(objetivo)) {
                        exito = true;
                    }
                }
            }
            if ((celda = escenario.getCelda(p.x, p.y + 1)) != null && celda.estaLibre() && !exito) {
                Estado nuevo = new Estado(celda.cooredenadas, actual);
                if (!historial.contains(nuevo)) {
                    historial.add(nuevo);
                    colaEstados.add(nuevo);
                    if (nuevo.posicion.equals(objetivo)) {
                        exito = true;
                    }
                }

            }
            if ((celda = escenario.getCelda(p.x, p.y - 1)) != null && celda.estaLibre() && !exito) {
                Estado nuevo = new Estado(celda.cooredenadas, actual);
                if (!historial.contains(nuevo)) {
                    historial.add(nuevo);
                    colaEstados.add(nuevo);
                    if (nuevo.posicion.equals(objetivo)) {
                        exito = true;
                    }
                }
            }
        }

        if (exito) {
            return calcularRuta(historial.get(historial.size() - 1));
        }
        return null;
    }

    private static ArrayList<Estado> calcularRuta(Estado objetivo) {
        ArrayList<Estado> pasos = new ArrayList<Estado>();
        Estado predecesor = objetivo;
        do {
            System.out.println("x : " + predecesor.posicion.x + "  y : " + predecesor.posicion.y);
            pasos.add(predecesor);
            predecesor = predecesor.predecesor;
        } while (predecesor != null);
        return pasos;
    }

    @Override
    public void run() {

    }

}

class OrdenarPorDistancia implements Comparator<Estado> {

    public Vector2Int objetivo;

    @Override
    public int compare(Estado t, Estado t1) {
        if (t.posicion.distancia(objetivo) - t1.posicion.distancia(objetivo) < 0) {
            return -1;
        } else {
            return 1;
        }

    }
}
