/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoDatos;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Rob Guerrero
 */
public interface DAOConexion {
    ConexionBD conexion = new ConexionBD();

    
    public ArrayList transformarQuerySet();
    
}
