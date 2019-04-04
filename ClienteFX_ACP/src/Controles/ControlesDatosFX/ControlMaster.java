/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles.ControlesDatosFX;

/**
 *
 * @author rob
 */
public class ControlMaster {
    ControlAdopciones cAdopciones;
    ControlAdoptantes cAdoptantes;
    ControlAnimales cAnimales;
    ControlVoluntarios cVoluntarios;
    
   public ControlMaster(){
       cAdopciones = new ControlAdopciones();
       cAdoptantes = new ControlAdoptantes();
       cAnimales = new ControlAnimales();
       cVoluntarios = new ControlVoluntarios();
   }

    public ControlAdopciones getcAdopciones() {
        return cAdopciones;
    }

    public ControlAdoptantes getcAdoptantes() {
        return cAdoptantes;
    }

    public ControlAnimales getcAnimales() {
        return cAnimales;
    }

    public ControlVoluntarios getcVoluntarios() {
        return cVoluntarios;
    }
    
    
    
    
}
