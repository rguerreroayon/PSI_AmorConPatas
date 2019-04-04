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
public class Voluntario extends Persona {

    private int idVoluntario;
    private String username;
    private int horasAcumuladas = 0;
    private int isAdmin = 0;

    public Voluntario(String nombre, String telefono, String direccion) {
        super(nombre, telefono, direccion);
    }
    
    public Voluntario(String nombre, String telefono, String direccion, int idVoluntario) {
        super(nombre, telefono, direccion);
        this.idVoluntario = idVoluntario;
    }

    public Voluntario(String nombre, String telefono, String direccion, int idVoluntario,int horasAcumuladas) {
        super(nombre, telefono, direccion);
        this.idVoluntario = idVoluntario;
        this.horasAcumuladas = horasAcumuladas;
    }

    public int getIdVoluntario() {
        return idVoluntario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getHorasAcumuladas() {
        return horasAcumuladas;
    }

    public void setHorasAcumuladas(int horasAcumuladas) {
        this.horasAcumuladas = horasAcumuladas;
    }

    public void setIdVoluntario(int idVoluntario) {
        this.idVoluntario = idVoluntario;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.idVoluntario;
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
        final Voluntario other = (Voluntario) obj;
        if (this.idVoluntario != other.idVoluntario) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return "ID Voluntario: "+idVoluntario+", Nombre Voluntario: "+getNombre();
    }

}
