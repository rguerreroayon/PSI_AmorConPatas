/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosNegocio;

import java.util.Calendar;

/**
 *
 * @author Rob Guerrero
 */
public class EntradaMedica {
    private Calendar fecha;
    private String descripcion;
    private int idAnimal;

    public EntradaMedica(Calendar fecha, String descripcion,int idAnimal) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.idAnimal = idAnimal;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }
    
    
}