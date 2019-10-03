/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegojavaia;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import utilidades.CargaImagenes;
import utilidades.Input;

/**
 *
 * @author elnik
 */
public class VentanaPrincipal extends JFrame implements Constantes {

    public Lienzo lienzo;

    //constructor
    public VentanaPrincipal() {
        CargaImagenes.cargarImagenes();
        
        lienzo = new Lienzo();
        
        add(lienzo);
        setSize(1430, 690);
        setVisible(true);
        setDefaultCloseOperation(3);
        lienzo.setFocusable(true);
        
            Timer timer;
    timer = new Timer();

    TimerTask task = new TimerTask() {
        @Override
        public void run()
        {
            Input.set();
            lienzo.update(lienzo.getGraphics());
            
        }
        };
        
    timer.schedule(task,0, 1000/FPS);

    }

}
