/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles.ControlesDatosFX;

import accesoDatos.DAOVoluntarios;

/**
 *
 * @author roberto
 */
public class ControlVoluntarios {

    DAOVoluntarios voluntarios;
    
    public ControlVoluntarios(){
        voluntarios = new DAOVoluntarios();
    }

    public DAOVoluntarios getVoluntarios() {
        return voluntarios;
    }
    
    
    

}
