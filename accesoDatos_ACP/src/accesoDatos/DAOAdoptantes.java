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
import objetosNegocio.Adoptante;
import objetosNegocio.Voluntario;

/**
 *
 * @author Rob Guerrero
 */
public class DAOAdoptantes implements DAOConexion {

    @Override
    public ArrayList transformarQuerySet() {
        Connection c = ConexionBD.getConection();
        String query = "SELECT * "
                + "FROM Adoptante";

        try {
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            ArrayList<Adoptante> adoptantes = new ArrayList<>();

            while (rs.next()) {
                adoptantes.add(
                        new Adoptante(
                                rs.getString("nombreAdoptante"),
                                rs.getString("telefono"),
                                rs.getString("direccion"),
                                Integer.valueOf(rs.getString("idAdoptante")))
                );
            }

            return adoptantes;

        } catch (SQLException exception) {
            System.out.println("Oops, algo salio mal");
        }

        return null;
    }

  
    
}
