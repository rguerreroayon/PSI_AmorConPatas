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

            voluntarios = new ArrayList<>();

            while (rs.next()) {
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

    public int queryAnadirVoluntario(Voluntario voluntario) {
        Connection c = ConexionBD.getConection();
        String query = "INSERT INTO Voluntario(nombre,direccion,telefono) "
                + " VALUES ('" + voluntario.getNombre() + "','" + voluntario.getDireccion() + "','" + voluntario.getTelefono() + "')";

        try {

            PreparedStatement ps = c.prepareStatement(query);

            return ps.executeUpdate();

        } catch (SQLException exception) {
            System.out.println("Oops, algo salio mal");
        }

        return -1;
    }

    public int queryEliminarVoluntario(int idVoluntario) {
        Connection c = ConexionBD.getConection();
        String query = "DELETE FROM Voluntario WHERE idVoluntario=" + String.valueOf(idVoluntario);

        try {

            PreparedStatement ps = c.prepareStatement(query);

            return ps.executeUpdate();

        } catch (SQLException exception) {
            System.out.println("Oops, algo salio mal");
        }

        return -1;
    }

    public int queryEditarVoluntario(Voluntario voluntario, int idVoluntarioEdicion) {
        Connection c = ConexionBD.getConection();
        String query = "UPDATE Voluntario "
                + "SET nombre ='" + voluntario.getNombre() + "', direccion='" + voluntario.getDireccion() + "', telefono='" + voluntario.getTelefono() + "' "
                + "WHERE idVoluntario =" + idVoluntarioEdicion;

        try {

            PreparedStatement ps = c.prepareStatement(query);

            return ps.executeUpdate();

        } catch (SQLException exception) {
            System.out.println("Oops, algo salio mal");
        }

        return -1;
    }

    public Voluntario queryGetVoluntarioPorID(int idVoluntario) {
        Connection c = ConexionBD.getConection();
        String query = "SELECT * FROM Voluntario WHERE idVoluntario=" + idVoluntario;

        try {

            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            Voluntario voluntario = null;

            while (rs.next()) {
                voluntario = new Voluntario(rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        Integer.valueOf(rs.getString("idVoluntario")),
                        Integer.valueOf(rs.getString("horasAcumuladas"))
                );
            }

            return voluntario;

        } catch (SQLException exception) {
            System.out.println("Oops, algo salio mal");
        }

        return null;

    }

    public ArrayList<Voluntario> queryGetVoluntariosPorNombre(String nombre) {
        voluntarios = transformarQuerySet();
        ArrayList<Voluntario> respond = new ArrayList();

        voluntarios.stream().filter((voluntario) -> (voluntario.getNombre().contains(nombre))).forEachOrdered((voluntario) -> {
            respond.add(voluntario);
        });

        return respond;
    }

    public void queryAnadirHorasVoluntarios(ArrayList<Voluntario> voluntarios, int numeroHoras) {
        Connection c = ConexionBD.getConection();

        for (Voluntario voluntario : voluntarios) {
            String query = "UPDATE Voluntario "
                    + "SET horasAcumuladas=" + numeroHoras + " "
                    + "WHERE idVoluntario=" + voluntario.getIdVoluntario();
            try {
                PreparedStatement ps = c.prepareStatement(query);
                ps.executeUpdate();
                
                
            } catch (SQLException exception) {
                System.out.println("Oops, algo salio mal");
            }

        }


    }
}
