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
public class Voluntario extends Persona{
    private int idAdoptante;
    private String username;
    
    public Voluntario(String nombre, String telefono, String direccion,int idAdoptante) {
        super(nombre, telefono, direccion);
    }

    public int getIdAdoptante() {
        return idAdoptante;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   
    
    
    
    
    
}
