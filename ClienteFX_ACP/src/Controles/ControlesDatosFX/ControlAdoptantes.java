/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles.ControlesDatosFX;

import accesoDatos.DAOAdoptantes;

/**
 *
 * @author roberto
 */
public class ControlAdoptantes {

    DAOAdoptantes adoptantes;

    public ControlAdoptantes() {
        adoptantes = new DAOAdoptantes();
    }

    public DAOAdoptantes getAdoptantes() {
        return adoptantes;
    }
    
    
}
