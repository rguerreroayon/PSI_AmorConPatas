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
        
        
        
     //pruebasDAOAdoptantes();
        
     pruebasDAOAnimales();
     
     
    
            

        
    }
    
    
    public static void pruebasDAOVoluntarios(){
           DAOVoluntarios dv = new DAOVoluntarios();

        
        
        //TransformarQuerySet - Recuperar todos los voluntarios
        //        
        //        ArrayList<Voluntario> voluntarios = dv.transformarQuerySet();
        //        
        //        for (Voluntario voluntario : voluntarios) {
        //            System.out.println(voluntario.getNombre());
        //        }
        
        //AnadirVoluntario - Anadir un voluntario
        
        //Voluntario voluntario = new Voluntario("Fernando Rodriguez","6444585858","Pruebas desde Netbeans");
        System.out.println("------------------------------------------------------------------");
        //dv.queryAnadirVoluntario(voluntario);
        System.out.println("------------------------------------------------------------------");
        //System.out.println(dv.queryEditarVoluntario(voluntario,1));

        System.out.println("------------------------------------------------------------------");

        Voluntario voluntario = dv.queryGetVoluntarioPorID(1);
        Voluntario voluntario2 = dv.queryGetVoluntarioPorID(2);
        
//        System.out.println(voluntario.getNombre());
//        System.out.println(voluntario.getDireccion());
//        System.out.println(voluntario.getTelefono());
//        System.out.println(voluntario.getHorasAcumuladas());

        System.out.println("------------------------------------------------------------------");

        //Get Voluntarios por Nombre
        
//        ArrayList<Voluntario> v = dv.queryGetVoluntariosPorNombre("e");
//        
//        for (Voluntario voluntario1 : v) {
//            System.out.println(voluntario1.getNombre());
//        }
        
        //AgregarHorasPorVoluntarios
        
        ArrayList<Voluntario> v2 = new ArrayList();
        v2.add(voluntario);
        v2.add(voluntario2);
        
        dv.queryAnadirHorasVoluntarios(v2, 2);
    }
    
    public static void pruebasDAOAnimales(){
        
        DAOAnimales a = new DAOAnimales();
        
        Animal animal = new Animal("Hercules","Perro","Cocker", Utilidades.convertirFecha_StringToGregorian("2019-03-25"), 1,"Netbeans testing edicion :D", 0);
        
        a.queryEditarAnimal(animal,3);
        
    }
      
    public static void pruebasDAOAdoptantes(){
        DAOAdoptantes da = new DAOAdoptantes();
        
       
        
    }
          

    
    
}
