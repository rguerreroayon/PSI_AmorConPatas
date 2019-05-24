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
 * @author robPC
 */
public class Evento {
    
    private String nombreEvento;
    private int idEvento;
    private GregorianCalendar fechaEvento;
    private ArrayList <Voluntario> voluntarios;
    
    public Evento(String nombreEvento,GregorianCalendar fechaEvento){
        this.nombreEvento = nombreEvento;
        this.fechaEvento = fechaEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public GregorianCalendar getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(GregorianCalendar fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public ArrayList<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    public void setVoluntarios(ArrayList<Voluntario> voluntarios) {
        this.voluntarios = voluntarios;
    }
    
    
}
