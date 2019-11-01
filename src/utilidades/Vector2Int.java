/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import IA.Estado;

/**
 *
 * @author elnik
 */
/*
    Vector2 es una clase que encapsula 2 números enteros, x e y los cuales se utilizan para representar una posición en un espacio de 2 dimensiones
*/
public class Vector2Int {
    public int x;
    public int y;

    public Vector2Int() {
    }

    public Vector2Int(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
        @Override
    public boolean equals(Object o){
        Vector2Int v = (Vector2Int) o;
        return this.x == v.x && this.y == v.y;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.x;
        hash = 47 * hash + this.y;
        return hash;
    }
    
    @Override
    public String toString(){
        return "x : "+x+"  y : "+y;
    }
    
    public double distancia(Vector2Int v){
        return Math.sqrt(Math.pow(x-v.x, 2) + Math.pow(y-v.y, 2));
    }
}
