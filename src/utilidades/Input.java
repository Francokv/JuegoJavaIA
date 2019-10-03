/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author elnik
 */
public class Input implements KeyListener{
    
    public static int horizontal;
    public static int vertical;
    
    public static int arriba = KeyEvent.VK_UP;
    public static int abajo = KeyEvent.VK_DOWN;
    public static int izquierda = KeyEvent.VK_LEFT;
    public static int derecha = KeyEvent.VK_RIGHT;
    
    private static int auxHorizontal;
    private static int auxVertical;
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() ==  arriba){
            auxVertical = 1; 
        }
        else if (ke.getKeyCode() ==  abajo){
            auxVertical = -1;
        }
        else if (ke.getKeyCode() ==  izquierda){
            auxHorizontal = -1;
        }
        else if (ke.getKeyCode() ==  derecha){
            auxHorizontal = 1;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        auxHorizontal = 0;
        auxVertical = 0; 
    }

    public static void set(){
        horizontal = auxHorizontal;
        vertical = auxVertical;
    }
    
}
