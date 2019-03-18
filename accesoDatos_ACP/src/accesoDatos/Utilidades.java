/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoDatos;

import java.sql.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author roberto
 */
public class Utilidades {
    
    
    public static GregorianCalendar convertirFecha(Date date){
        GregorianCalendar fecha = new GregorianCalendar();
        fecha.setTime(date);
        return fecha; 
        
    }
}
