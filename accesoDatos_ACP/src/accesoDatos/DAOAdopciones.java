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
import java.util.GregorianCalendar;
import objetosNegocio.Adopcion;

/**
 *
 * @author Rob Guerrero
 */
public class DAOAdopciones implements DAOConexion {

    private ArrayList<Adopcion> adopciones;

    public DAOAdopciones() {

    }

    @Override
    public ArrayList<Adopcion> transformarQuerySet() {
        Connection c = ConexionBD.getConection();
        String query = "SELECT * FROM Adopcion ORDER BY idAdopcion";

        try {
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                adopciones.add(new Adopcion(
                        Integer.valueOf(rs.getString("idAdopcion")),
                        Integer.valueOf(rs.getString("idAnimal")),
                        Integer.valueOf(rs.getString("idAdoptante")),
                        Integer.valueOf(rs.getString("idVoluntario")),
                        new GregorianCalendar(),
                        rs.getString("descripcion")
                ));
            }
            return adopciones;

        } catch (SQLException exception) {
            System.out.println("Oops, algo salio mal");
        }

        return null;
    }

    
}
