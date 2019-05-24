package tests;

import accesoDatos.DAOVoluntarios;
import manajador_ventanas.WindowsControlador;
import objetosNegocio.Voluntario;

import java.util.ArrayList;

public class Pruebas {


    public static void main(String[] args) {
        DAOVoluntarios voluntarios = new DAOVoluntarios();

        ArrayList<Voluntario> lista = voluntarios.transformarQuerySet();

        for (Voluntario v: lista
             ) {
            System.out.println(v.getNombre());
        }



    }


}
