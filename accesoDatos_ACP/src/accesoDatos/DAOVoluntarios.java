/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import objetosNegocio.Voluntario;

/**
 *
 * @author Rob Guerrero
 */
public class DAOVoluntarios implements DAOConexion {
    ArrayList<Voluntario> voluntarios;
    
    @Override
    public ArrayList transformarQuerySet() {
        Connection c = ConexionBD.getConection();
        String query = "SELECT * "
                + "FROM Voluntario";
        
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                voluntarios.add(
                        new Voluntario(
                            rs.getString("nombre"),
                            rs.getString("telefono"),
                            rs.getString("direccion"),
                            Integer.valueOf(rs.getString("idVoluntario")))
                );
            }
           
            return voluntarios;

        } catch (SQLException exception) {
            System.out.println("Oops, algo salio mal");
        }
        
        return null;
    }

    
    
}
