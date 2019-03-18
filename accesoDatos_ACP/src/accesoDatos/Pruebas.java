/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoDatos;

import java.util.ArrayList;
import objetosNegocio.*;

/**
 *
 * @author roberto
 */
public class Pruebas {
    public static void main(String[] args) {
        DAOVoluntarios dv = new DAOVoluntarios();

        
        
        //TransformarQuerySet - Recuperar todos los voluntarios
        //        
        //        ArrayList<Voluntario> voluntarios = dv.transformarQuerySet();
        //        
        //        for (Voluntario voluntario : voluntarios) {
        //            System.out.println(voluntario.getNombre());
        //        }
        
        //AnadirVoluntario - Anadir un voluntario
        
        Voluntario voluntario = new Voluntario("Fernando Rodriguez","6444585858","Pruebas desde Netbeans");
        System.out.println("------------------------------------------------------------------");
        //dv.queryAnadirVoluntario(voluntario);
        System.out.println("------------------------------------------------------------------");
        //System.out.println(dv.queryEditarVoluntario(voluntario,1));

        System.out.println("------------------------------------------------------------------");

        voluntario = dv.queryGetVoluntarioPorID(1);
        
//        System.out.println(voluntario.getNombre());
//        System.out.println(voluntario.getDireccion());
//        System.out.println(voluntario.getTelefono());
//        System.out.println(voluntario.getHorasAcumuladas());

        System.out.println("------------------------------------------------------------------");

        //Get Voluntarios por Nombre
        
        ArrayList<Voluntario> v = dv.queryGetVoluntariosPorNombre("e");
        
        for (Voluntario voluntario1 : v) {
            System.out.println(voluntario1.getNombre());
        }
        
        
    }
}
