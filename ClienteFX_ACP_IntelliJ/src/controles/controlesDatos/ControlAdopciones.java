/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles.controlesDatos;

import accesoDatos.DAOAdopciones;

/**
 *
 * @author roberto
 */
public class ControlAdopciones {

    DAOAdopciones adopciones;
    
    public ControlAdopciones(){
        adopciones = new DAOAdopciones();
    }

    public DAOAdopciones getAdopciones() {
        return adopciones;
    }
}
