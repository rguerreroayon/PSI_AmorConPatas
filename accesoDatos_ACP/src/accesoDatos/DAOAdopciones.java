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
import objetosNegocio.Adopcion;

/**
 *
 * @author Rob Guerrero
 */
public class DAOAdopciones implements DAOConexion {

    @Override
    public ArrayList<Adopcion> transformarQuerySet() {
        Connection c = ConexionBD.getConection();
        String query = "SELECT * FROM Adopcion ORDER BY idAdopcion";
        ArrayList<Adopcion> adopciones = new ArrayList();
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                adopciones.add(new Adopcion(
                        Integer.valueOf(rs.getString("idAdopcion")),
                        Integer.valueOf(rs.getString("idAnimal")),
                        Integer.valueOf(rs.getString("idAdoptante")),
                        Integer.valueOf(rs.getString("idVoluntario")),
                        Utilidades.convertirFecha_StringToGregorian(rs.getString("fecha")),
                        rs.getString("descripcion")
                ));
            }
            return adopciones;

        } catch (SQLException exception) {
            System.out.println("Oops, algo salio mal");
        }

        return null;
    }

    public int queryAnadirAdopcion(Adopcion adopcion,String nombreAnimal){
        Connection c = ConexionBD.getConection();
        String query = "INSERT INTO Adopcion (idAnimal,idAdoptante,idVoluntario,fecha,descripcion) "
                + "VALUES ("+adopcion.getIdAnimal()+","+adopcion.getIdAdoptante()+","+adopcion.getIdVoluntario()+",'"+Utilidades.convertirFecha_GregorianToString(adopcion.getFecha())+"','"+adopcion.getDescripcion()+"')";

        String query2 = "UPDATE Animal "
                + "SET esAdoptado="+1+", nombreAnimal='"+nombreAnimal+"' "
                + "WHERE idAnimal="+adopcion.getIdAnimal();
        
        
        try {

            PreparedStatement ps = c.prepareStatement(query);
            PreparedStatement ps2 = c.prepareStatement(query2);
            
            int v1 = ps.executeUpdate();
            int v2 = ps2.executeUpdate();
            
            return v1+v2;

        } catch (SQLException exception) {
            System.out.println("Oops, algo salio mal");
        }

        return -1;
    }
    
    public int queryEliminarAdopcion(int idAdopcion){
        Connection c = ConexionBD.getConection();
        String query = "DELETE FROM Adopcion WHERE idAdopcion=" + String.valueOf(idAdopcion);

        try {

            PreparedStatement ps = c.prepareStatement(query);

            return ps.executeUpdate();

        } catch (SQLException exception) {
            System.out.println("Oops, algo salio mal");
        }

        return -1;
    }
    
    public Adopcion queryGetAdopcionPorIDAdopcion(int idAdopcion) {

        ArrayList<Adopcion> todas = transformarQuerySet();

        for (Adopcion adopcion : todas) {
            if (adopcion.getIdAdopcion() == idAdopcion) {
                return adopcion;
            }
        }

        return null;

    }

    public ArrayList<Adopcion> queryGetAdopcionesPorIDVoluntario(int idVoluntario) {
        ArrayList<Adopcion> todas = transformarQuerySet();
        ArrayList<Adopcion> porV = new ArrayList();

        for (Adopcion adopcion : todas) {
            if (adopcion.getIdVoluntario() == idVoluntario) {
                porV.add(adopcion);
            }
        }

        return porV;
    }

    public ArrayList<Adopcion> queryGetAdopcionesPorIDAdoptante(int idAdoptante) {
        ArrayList<Adopcion> todas = transformarQuerySet();
        ArrayList<Adopcion> porA = new ArrayList();

        for (Adopcion adopcion : todas) {
            if (adopcion.getIdAdoptante() == idAdoptante) {
                porA.add(adopcion);
            }
        }

        return porA;
    }

    public ArrayList<Adopcion> queryGetAdopcionesPorNombreAnimal(String nombreAnimal) {
        ArrayList<Adopcion> todas = transformarQuerySet();
        ArrayList<Adopcion> porN = new ArrayList();
        DAOAnimales da = new DAOAnimales();

        for (Adopcion adopcion : todas) {
            if (da.queryGetAnimalPorID(adopcion.getIdAnimal()).getNombre().contains(nombreAnimal)) {
                porN.add(adopcion);
            }
        }

        return porN;

    }

    public ArrayList<Adopcion> queryGetAdopcionesPorEspecie(String especie){
        ArrayList<Adopcion> todas = transformarQuerySet();
        ArrayList<Adopcion> porE = new ArrayList();
        DAOAnimales da = new DAOAnimales();

        for (Adopcion adopcion : todas) {
            if (da.queryGetAnimalPorID(adopcion.getIdAnimal()).getEspecie().equals(especie)){
                porE.add(adopcion);
            }
        }

        return porE;
    }
}
