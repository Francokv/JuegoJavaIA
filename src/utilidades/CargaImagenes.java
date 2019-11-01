/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import juegojavaia.Constantes;

/**
 *
 * @author elnik
 */
public class CargaImagenes implements Constantes{

    public static Image[] piso = new Image[7];
    public static Image[] pared= new Image[9];
    public static Image[] jugador= new Image[12];
    public static Image[] adversario= new Image[12];
    public static Image portal;
    public static Image[] monedaOro = new Image[8];
    public static Image[] monedaPlata = new Image[8];
    public static Image[] monedaCobre = new Image[8];
    public static Image[] corazones = new Image[3];
    public static Image[] obstaculos = new Image[2];

    

    public static void cargarImagenes() {
        BufferedImage terrenoIm,jugadorIm, monedaOroIm, monedaCobreIm,monedaPlataIm,corazonesIm,obstaculosIm,adversarioIm,portalIm;
        try {
            terrenoIm = ImageIO.read(new File("src/sprites/pasto.png"));
            jugadorIm = ImageIO.read(new File("src/sprites/personaje.png"));
            monedaOroIm = ImageIO.read(new File("src/sprites/coin_gold.png"));
            monedaCobreIm = ImageIO.read(new File("src/sprites/coin_copper.png"));
            monedaPlataIm = ImageIO.read(new File("src/sprites/coin_silver.png"));
            corazonesIm = ImageIO.read(new File("src/sprites/hearts32x32.png"));
            obstaculosIm = ImageIO.read(new File("src/sprites/vegetacion.png"));
            adversarioIm = ImageIO.read(new File("src/sprites/adversario.png"));
            portalIm = ImageIO.read(new File("src/sprites/portal.png"));
            
            int pixeles = 32;
            
            
  
            
            portal = portalIm.getSubimage(0* pixeles,2* pixeles, pixeles, pixeles).getScaledInstance(PIXEL_CELDA, PIXEL_CELDA, Image.SCALE_DEFAULT);
            //carga texturas del terreno
            for (int i = 0; i < piso.length; i++) {
                piso[i] = terrenoIm.getSubimage(i* pixeles,0, pixeles, pixeles).getScaledInstance(PIXEL_CELDA, PIXEL_CELDA, Image.SCALE_DEFAULT);
            }
            
            //carga sprites del jugador
            for (int i = 0; i < jugador.length; i++) {
                jugador[i] = jugadorIm.getSubimage(i % 3 * pixeles, (i/3) * pixeles, pixeles, pixeles).getScaledInstance(PIXEL_CELDA, PIXEL_CELDA, Image.SCALE_DEFAULT);
            }
            //carga sprites del adversarios
            for (int i = 0; i < jugador.length; i++) {
                adversario[i] = adversarioIm.getSubimage(i % 3 * pixeles, (i/3) * pixeles, pixeles, pixeles).getScaledInstance(PIXEL_CELDA, PIXEL_CELDA, Image.SCALE_DEFAULT);
            }
            //carga sprites de las monedas 
            for (int i = 0; i < monedaOro.length; i++) {
                monedaOro[i] = monedaOroIm.getSubimage(i * pixeles,0, pixeles, pixeles).getScaledInstance(PIXEL_CELDA, PIXEL_CELDA, Image.SCALE_DEFAULT);
            }
            for (int i = 0; i < monedaPlata.length; i++) {
                monedaPlata[i] = monedaPlataIm.getSubimage(i * pixeles,0, pixeles, pixeles).getScaledInstance(PIXEL_CELDA, PIXEL_CELDA, Image.SCALE_DEFAULT);
            }
            for (int i = 0; i < monedaCobre.length; i++) {
                monedaCobre[i] = monedaCobreIm.getSubimage(i * pixeles,0, pixeles, pixeles).getScaledInstance(PIXEL_CELDA, PIXEL_CELDA, Image.SCALE_DEFAULT);
            }
            //carga imagenes de corazones
            for (int i = 0; i < 2; i++) {
                corazones[i] = corazonesIm.getSubimage(i * pixeles,0, pixeles, pixeles).getScaledInstance(PIXEL_CELDA, PIXEL_CELDA, Image.SCALE_DEFAULT);
            }
            corazones[2] = corazonesIm.getSubimage(2 * pixeles,3 * pixeles, pixeles, pixeles).getScaledInstance(PIXEL_CELDA, PIXEL_CELDA, Image.SCALE_DEFAULT);
             //carga imagenes de vegetacion
            
            obstaculos[0] = obstaculosIm.getSubimage(0,0, pixeles, pixeles).getScaledInstance(PIXEL_CELDA, PIXEL_CELDA, Image.SCALE_DEFAULT);
            obstaculos[1] = obstaculosIm.getSubimage(0,pixeles, pixeles, 2*pixeles).getScaledInstance(PIXEL_CELDA, PIXEL_CELDA*2, Image.SCALE_DEFAULT);
            
        } catch (IOException error) {
            System.out.println("Error : " + error.toString());
        }

    }
}
