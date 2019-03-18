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
        ConexionBD bd = new ConexionBD();
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
        
        dv.anadirVoluntario(voluntario);
    
        
    }
}
