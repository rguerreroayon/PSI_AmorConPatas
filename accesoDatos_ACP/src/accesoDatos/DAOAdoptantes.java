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

/**
 *
 * @author Rob Guerrero
 */
public class DAOAdoptantes implements DAOConexion {

    @Override
    public ArrayList<Adoptante> transformarQuerySet() {
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

    public int queryAnadirAdoptante(Adoptante adoptante){
        Connection c = ConexionBD.getConection();
        String query = "INSERT INTO Adoptante(nombreAdoptante,direccion,telefono) "
                + "VALUES ('" + adoptante.getNombre() + "','" + adoptante.getDireccion() + "','" + adoptante.getTelefono() + "')";

        try {

            PreparedStatement ps = c.prepareStatement(query);

            return ps.executeUpdate();

        } catch (SQLException exception) {
            System.out.println("Oops, algo salio mal");
        }

        return -1;
    }
    
    public int queryEditarAdoptante(Adoptante adoptante,int idAdoptante){
        Connection c = ConexionBD.getConection();
        String query = "UPDATE Adoptante "
                + "SET nombreAdoptante='"+adoptante.getNombre()+"',telefono='"+adoptante.getTelefono()+"',direccion='"+adoptante.getDireccion()+"' WHERE idAdoptante="+idAdoptante;
        try {

            PreparedStatement ps = c.prepareStatement(query);
            return ps.executeUpdate();

        } catch (SQLException exception) {
            System.out.println("Oops, algo salio mal");
        }

        return -1;
    }
    
    public int queryEliminarAdoptante(int idAdoptante){
        Connection c = ConexionBD.getConection();
        String query = "DELETE FROM Adopante WHERE idAdoptante=" + String.valueOf(idAdoptante);

        try {

            PreparedStatement ps = c.prepareStatement(query);

            return ps.executeUpdate();

        } catch (SQLException exception) {
            System.out.println("Oops, algo salio mal");
        }

        return -1;
    }
    
    public Adoptante queryGetAdoptantePorID(int idAdoptante){
       ArrayList<Adoptante> adoptantes = transformarQuerySet();
       
        for (Adoptante adoptante : adoptantes) {
            if(adoptante.getIdAdoptante() == idAdoptante){
                return adoptante;
            }
        }
        
        return null;
       
    }
  
    public ArrayList<Adoptante> queryGetAdoptantesPorNombre(String nombre){
        ArrayList<Adoptante> adoptantes = transformarQuerySet();
        ArrayList<Adoptante> porN = new ArrayList();
        for (Adoptante adoptante : adoptantes) {
            if(adoptante.getNombre().contains(nombre)){
                porN.add(adoptante);
            }
        }
        
        return porN;
    }
}
