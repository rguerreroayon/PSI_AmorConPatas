/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles.ControlesDatosFX;

import accesoDatos.DAOAnimales;

/**
 *
 * @author roberto
 */
public class ControlAnimales {

    DAOAnimales animales;

    public ControlAnimales() {
        animales = new DAOAnimales();
    }

    public DAOAnimales getAnimales() {
        return animales;
    }

    

    
    
    
}
