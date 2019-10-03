/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegojavaia;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import utilidades.Input;

/**
 *
 * @author elnik
 */
public class Lienzo extends Canvas implements Constantes {
    public Escenario escenario;
    
    private Graphics graficoBuffer;
    private Image imagenBuffer;
    
    public Lienzo (){
        escenario = new Escenario();
        escenario.generarEscenaAleatoria();
        addKeyListener(new Input());

    }
    
    @Override
    public void paint(Graphics g) {
        update(g);
    }

    @Override
    public void update(Graphics g) {
        if (graficoBuffer == null) {
            imagenBuffer = createImage(this.getWidth(), this.getHeight());
            graficoBuffer = imagenBuffer.getGraphics();
        }
        escenario.update(graficoBuffer);
        g.drawImage(imagenBuffer, 0, 0, null);
    }
    
}
