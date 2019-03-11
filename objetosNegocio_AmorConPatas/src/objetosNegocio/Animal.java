/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosNegocio;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Rob Guerrero
 */
public class Animal {
    private String nombre;
    private Animal animal;
    private String especie;
    private String raza;
    private Calendar fecha;
    private Voluntario voluntario;
    private String descripcionRescate;
    private ArrayList historialMedico;
    private boolean isAdoptado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

    public String getDescripcionRescate() {
        return descripcionRescate;
    }

    public void setDescripcionRescate(String descripcionRescate) {
        this.descripcionRescate = descripcionRescate;
    }

    public ArrayList getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(ArrayList historialMedico) {
        this.historialMedico = historialMedico;
    }

    public boolean isIsAdoptado() {
        return isAdoptado;
    }

    public void setIsAdoptado(boolean isAdoptado) {
        this.isAdoptado = isAdoptado;
    }
    
    
    
    
}
