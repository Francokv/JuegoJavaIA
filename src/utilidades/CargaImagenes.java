/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author elnik
 */
public class CargaImagenes {

    public static BufferedImage[] piso = new BufferedImage[9];
    public static BufferedImage[] pared= new BufferedImage[9];
    public static BufferedImage[] jugador= new BufferedImage[12];
    public static BufferedImage[] esqueleto;
    public static BufferedImage[] monedaOro = new BufferedImage[8];
    public static BufferedImage[] monedaPlata = new BufferedImage[8];
    public static BufferedImage[] monedaCobre = new BufferedImage[8];

    

    public static void cargarImagenes() {
        BufferedImage terrenoIm,jugadorIm, monedaOroIm, monedaCobreIm,monedaPlataIm;
        try {
            terrenoIm = ImageIO.read(new File("src/sprites/texturas_terreno.png"));
            jugadorIm = ImageIO.read(new File("src/sprites/personaje.png"));
            monedaOroIm = ImageIO.read(new File("src/sprites/coin_gold.png"));
            monedaCobreIm = ImageIO.read(new File("src/sprites/coin_copper.png"));
            monedaPlataIm = ImageIO.read(new File("src/sprites/coin_silver.png"));
            
            int pixeles = 32;
            
            //carga texturas del terreno
            for (int i = 0; i < piso.length; i++) {
                piso[i] = terrenoIm.getSubimage(i % 3 * pixeles, (i/3 + 4) * pixeles, pixeles, pixeles);
            }
            //carga sprites del jugador
            for (int i = 0; i < jugador.length; i++) {
                jugador[i] = jugadorIm.getSubimage(i % 3 * pixeles, (i/3) * pixeles, pixeles, pixeles);
            }
            //carga sprites de las monedas 
            for (int i = 0; i < monedaOro.length; i++) {
                monedaOro[i] = monedaOroIm.getSubimage(i * pixeles,0, pixeles, pixeles);
            }
            for (int i = 0; i < monedaPlata.length; i++) {
                monedaPlata[i] = monedaPlataIm.getSubimage(i * pixeles,0, pixeles, pixeles);
            }
            for (int i = 0; i < monedaCobre.length; i++) {
                monedaCobre[i] = monedaCobreIm.getSubimage(i * pixeles,0, pixeles, pixeles);
            }
            
        } catch (IOException error) {
            System.out.println("Error : " + error.toString());
        }

    }
}
