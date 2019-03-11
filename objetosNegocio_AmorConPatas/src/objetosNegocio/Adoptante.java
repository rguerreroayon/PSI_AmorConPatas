/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosNegocio;

/**
 *
 * @author Rob Guerrero
 */
public class Adoptante extends Persona{
    
    private int idAdoptante;
    
    public Adoptante(String nombre, String telefono, String direccion,int idAdoptante) {
        super(nombre, telefono, direccion);
        this.idAdoptante = idAdoptante;
    }

    public int getIdAdoptante() {
        return idAdoptante;
    }
    
    
}
