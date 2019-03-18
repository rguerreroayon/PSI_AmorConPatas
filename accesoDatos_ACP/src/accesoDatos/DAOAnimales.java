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
import objetosNegocio.Animal;
import objetosNegocio.EntradaMedica;

/**
 *
 * @author Rob Guerrero
 */
public class DAOAnimales implements DAOConexion {

    ArrayList<Animal> animales;

    @Override
    public ArrayList transformarQuerySet() {
//        Connection c = ConexionBD.getConecction();
//        String query = "SELECT * FROM Animal ORDER BY idAnimal";
//
//        try {
//            PreparedStatement ps = c.prepareStatement(query);
//            ResultSet rs = ps.executeQuery();
//
//            while(rs.next()){
//                String nombreAnimal = rs.getString("nombreAnimal");
//                int idAnimal = Integer.valueOf(rs.getString("idAnimal"));
//                int idVoluntario = Integer.valueOf(rs.getString("idVoluntario"));
//                String especie = rs.getString("especie");
//                String raza = rs.getString("raza");
//                boolean esAdoptado = Integer.valueOf(rs.getString("esAdoptado")) != 0;
//                String descripcionRescate = rs.getString("descripcionRescate");
//                
//                
////                    animales.add(new Animal(nombreAnimal, idAnimal, especie, raza, fecha, new GregorianCalendar(), idVoluntario,
////                    ))
//
//            
//            }
//            return adopciones;
//
//        } catch (SQLException exception) {
//            System.out.println("Oops, algo salio mal");
//        }
//
        return null;
    }

    
    public ArrayList<EntradaMedica> queryGetNotaMedicaPorID(int idAnimal){
        Connection c = ConexionBD.getConection();
        String query = "SELECT * FROM NotaMedica WHERE idAnimal ="+idAnimal;
        ArrayList<EntradaMedica> entradas = new ArrayList();

        try {
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            

        } catch (SQLException exception) {
            System.out.println("Oops, algo salio mal");
        }

        return null;
        
        
    }
    
    
    
    
    
  

   

}
