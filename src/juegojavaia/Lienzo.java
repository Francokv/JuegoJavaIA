/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegojavaia;

import IA.BusquedaAnchura;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import utilidades.Input;

/**
 *
 * @author elnik
 */
public class Lienzo extends Canvas implements Constantes {

    public Escenario escenario;
    public HUD HUD;
    public boolean pausa;
    private Graphics graficoBuffer;
    private Image imagenBuffer;

    
    public static int vidas = 3;
    public static int recompensas = 0;
    public static int recompensasTotales = 4;


        
        
    
    
    private static Lienzo instancia;

    private Lienzo() {
        escenario = new Escenario();
        escenario.generarEscenaAleatoria();
        BusquedaAnchura.escenario = escenario;
        addKeyListener(new Input());

    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }

    @Override
    public void update(Graphics g) {
        if (!pausa) {
            if (graficoBuffer == null) {
                imagenBuffer = createImage(this.getWidth(), this.getHeight());
                graficoBuffer = imagenBuffer.getGraphics();
            }
            escenario.update(graficoBuffer);
            graficoBuffer.setColor(Color.WHITE);
            graficoBuffer.drawString("Vidas : "+vidas+"  Recompensas : "+recompensas+"/"+recompensasTotales, 10, 15);
            g.drawImage(imagenBuffer, 0, 0, null);
        }
    }

    public static Lienzo getLienzo() {
        if (instancia == null) {
            instancia = new Lienzo();
        }
        return instancia;
    }
}
