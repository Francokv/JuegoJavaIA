/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegojavaia;

import javax.swing.JFrame;
import utilidades.CargaImagenes;

/**
 *
 * @author elnik
 */
public class JuegoJavaIA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CargaImagenes.cargarImagenes();
        VentanaPrincipal vp=new VentanaPrincipal();
        vp.setVisible(true);
        vp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
}
