/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoDatos;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author roberto
 */
public class Utilidades {
    
    
    public static GregorianCalendar convertirFecha_StringToGregorian(String dateString){
        
        try{
        DateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formateador.parse(dateString);
        
        GregorianCalendar cal = new GregorianCalendar();
        
        cal.setTime(date);
        
        return cal;
        }catch(ParseException e){
            System.out.println("Oops, no se pudo parsear la fecha");
        }
        
        return null;
    }
    
    public static String convertirFecha_GregorianToString(GregorianCalendar dateG){
        String ano = String.valueOf(dateG.get(Calendar.YEAR));
        String mes = String.valueOf(dateG.get(Calendar.MONTH) + 1);
        String dia = String.valueOf(dateG.get(Calendar.DAY_OF_MONTH));
        
        
        return ano+"-"+mes+"-"+dia;

    }
}
