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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.idAdoptante;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Adoptante other = (Adoptante) obj;
        return this.idAdoptante == other.idAdoptante;
    }

    public void setIdAdoptante(int idAdoptante) {
        this.idAdoptante = idAdoptante;
    }
    
    
}
