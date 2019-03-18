/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosNegocio;

import java.util.GregorianCalendar;

/**
 *
 * @author Rob Guerrero
 */
public class Adopcion {
    private int idAdopcion;
    private int idAnimal;
    private int idAdoptante;
    private int idVoluntario;
    private GregorianCalendar fecha;
    private String descripcion;

    public Adopcion(int idAdopcion, int idAnimal, int idAdoptante, int idVoluntario, GregorianCalendar fecha, String descripcion) {
        this.idAdopcion = idAdopcion;
        this.idAnimal = idAnimal;
        this.idAdoptante = idAdoptante;
        this.idVoluntario = idVoluntario;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public int getIdAdopcion() {
        return idAdopcion;
    }

    public void setIdAdopcion(int idAdopcion) {
        this.idAdopcion = idAdopcion;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getIdAdoptante() {
        return idAdoptante;
    }

    public void setIdAdoptante(int idAdoptante) {
        this.idAdoptante = idAdoptante;
    }

    public int getIdVoluntario() {
        return idVoluntario;
    }

    public void setIdVoluntario(int idVoluntario) {
        this.idVoluntario = idVoluntario;
    }

    public GregorianCalendar getFecha() {
        return fecha;
    }

    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
    
}
