package manajador_ventanas;

import accesoDatos.ConexionBD;
import accesoDatos.DAOVoluntarios;
import javafx.application.Application;
import objetosNegocio.Voluntario;
import org.jetbrains.annotations.Contract;
import vista.adopcion.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WindowsControlador {

    public static final int VENTANA_LOGIN = 0;
    public static final int VENTANA_REGISTRO_MASCOTA = 1;
    public static final int VENTANA_REGISTRO_ADOPCION = 2;
    public static final int VENTANA_REGISTRO_VOLUNTARIO = 3;
    public Application active = null;
    public static Voluntario voluntarioActivo = null;





    public Object[] validarLogin(String username, String pwd){
        Connection conexion = ConexionBD.getConection();
        Object results []= new Object[2];
        DAOVoluntarios voluntarios = new DAOVoluntarios();
        String query = "SELECT * FROM Usuario WHERE username ='"+username+"' AND password='"+pwd+"'";
        try{
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();


            int idVoluntario = 0;
            while(rs.next()){
                idVoluntario = Integer.valueOf(rs.getString("idVoluntario"));
                System.out.println(idVoluntario);
            }

            if(idVoluntario > 0){
                Voluntario voluntario = voluntarios.queryGetVoluntarioPorID(idVoluntario);
                results[0] = new Boolean(true);
                System.out.println("-----------------------");
                System.out.println(voluntario.getNombre());
                System.out.println("-----------------------");
                results[1] = voluntario;
                return results;
            }



        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        results[0] = false;
        results[1] = null;
        return  results;


    }

    @Contract(pure = true)

    public static void setVoluntarioActual(Voluntario voluntario){
        voluntarioActivo = voluntario;
    }




}
