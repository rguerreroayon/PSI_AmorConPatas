/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author rob
 */
public class ConexionBD {
    private Connection conexion;
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String user = "ACP";
    private final String password = "AmorConPatas";
    private final String bd = "jdbc:mysql://rguerrero.ddns.net:3306/AmorConPatas";
    
    
    public ConexionBD(){
        this.conexion = null;
        try{
            System.out.println("Making the Connection");
            Class.forName(driver);
            conexion = DriverManager.getConnection(
                    bd,
                    user,
                    password);
            System.out.println("Verifying");
            if(conexion!= null) System.out.println("Conexion Exitosa");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static Connection getConection(){
        ConexionBD c = new ConexionBD();
        return c.getConexion();
        
    }

    private Connection getConexion() {
        return conexion;
    }
    
    
    
}
