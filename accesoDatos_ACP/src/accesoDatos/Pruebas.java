/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoDatos;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import objetosNegocio.*;

/**
 *
 * @author roberto
 */
public class Pruebas {
    public static void main(String[] args) throws ParseException {
        
        
        
    pruebasDAOVoluntarios();
     
     
    
            

        
    }
    
    
    public static void pruebasDAOVoluntarios(){
        DAOVoluntarios dv = new DAOVoluntarios();
        
        Voluntario volun = dv.queryGetVoluntarioPorID(2);
        
        System.out.println(volun.getNombre());
        
        
    }
    
    public static void pruebasDAOAnimales(){
        
        DAOAnimales da = new DAOAnimales();
        
        ArrayList <Animal> animales = da.queryGetAnimalesRescatadosPorNombreAnimal("h");
        
        
        for (Object animale : animales) {
            System.out.println(animale);
        }
    }
      
    public static void pruebasDAOAdoptantes(){
        
        
    }
          
    public static void pruebasDAOAdopciones(){
        DAOAdopciones da = new DAOAdopciones();
        
       Adopcion adopcion = new Adopcion(1, 1, 1, new GregorianCalendar(), "Prueba Netbeans");
       
       
       da.queryEliminarAdopcion(3);
       da.queryEliminarAdopcion(4);
       da.queryAnadirAdopcion(adopcion,"Corneta");
    }
    
    
}
