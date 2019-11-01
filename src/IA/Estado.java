/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import java.util.Objects;
import juegojavaia.Celda;
import utilidades.Vector2Int;

/**
 *
 * @author Alumno
 */
public class Estado {
    public Vector2Int posicion = new Vector2Int();
    public Estado predecesor;



    public Estado(Vector2Int posicion, Estado predecesor) {
        this.posicion = posicion;
        this.predecesor = predecesor;
    }
    
    @Override
    public boolean equals(Object o){
        Estado e = (Estado) o;
        return this.posicion.equals(e.posicion
        );
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.posicion);
        return hash;
    }
    
        @Override
    public String toString(){
        return posicion.toString();
    }
    
}
