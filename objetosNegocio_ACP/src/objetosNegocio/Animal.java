/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosNegocio;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author Rob Guerrero
 */
public class Animal {
    private String nombre;
    private String especie;
    private String raza;
    private int idAnimal;
    private GregorianCalendar fecha;
    private int idVoluntario;
    private String descripcionRescate;
    private ArrayList historialMedico;
    private int isAdoptado;

    public Animal(String nombre, int idAnimal, String especie, String raza, GregorianCalendar fecha, int idVoluntario, String descripcionRescate, ArrayList historialMedico, int isAdoptado) {
        this.nombre = nombre;
        this.idAnimal = idAnimal;
        this.especie = especie;
        this.raza = raza;
        this.fecha = fecha;
        this.idVoluntario = idVoluntario;
        this.descripcionRescate = descripcionRescate;
        this.historialMedico = historialMedico;
        this.isAdoptado = isAdoptado;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getIdVoluntario() {
        return idVoluntario;
    }

    public void setIdVoluntario(int idVoluntario) {
        this.idVoluntario = idVoluntario;
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

    public GregorianCalendar getFecha() {
        return fecha;
    }

    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
    }

    public int getIDVoluntario() {
        return idVoluntario;
    }

    public void setIDVoluntario(int idVoluntario) {
        this.idVoluntario = idVoluntario;
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

    public int getIsAdoptado() {
        return isAdoptado;
    }

    
    
    
    
}
