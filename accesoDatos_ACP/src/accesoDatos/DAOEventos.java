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
import objetosNegocio.Evento;
import objetosNegocio.Voluntario;

/**
 *
 * @author robPC
 */
public class DAOEventos implements DAOConexion{

    ArrayList<Evento> eventos = new ArrayList();
    
    
    @Override
    public ArrayList<Evento> transformarQuerySet(){
        Connection bd = ConexionBD.getConection();
        DAOVoluntarios dv = new DAOVoluntarios();
        String queryEventos = "SELECT * FROM Evento";
        ArrayList<Evento> nueva = new ArrayList();
        
        
        try{
            PreparedStatement ps = bd.prepareStatement(queryEventos);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                eventos.add(new Evento(rs.getString("nombreEvento"),Utilidades.convertirFecha_StringToGregorian(rs.getString("fechaEvento"))));
            }
            
            
            for (Evento evento : eventos) {
                String queryVoluntarios = "SElECT * FROM ListaAsistencia WHERE idEvento='"+evento.getIdEvento()+"'";
                ps = bd.prepareStatement(queryVoluntarios);
                rs = ps.executeQuery();
                ArrayList<Voluntario> voluntarios = new ArrayList();
                
                while(rs.next()){
                    int idVoluntario = Integer.valueOf(rs.getString("idVoluntario"));
                    
                    Voluntario v = dv.queryGetVoluntarioPorID(idVoluntario);
                    voluntarios.add(v);
                    
                }
                
                evento.setVoluntarios(voluntarios);
                nueva.add(evento);
                   
            }
            bd.close();
            return nueva;
              
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        
        return null;
    }
    
    public int anadirEvento(Evento evento){
        Connection c = ConexionBD.getConection();
        
        String query = "INSERT INTO `Evento`(`nombreEvento`, `fechaEvento`) "
                + "OUTPUT       "
                + "VALUES ('"+evento.getNombreEvento()+"','"+Utilidades.convertirFecha_GregorianToString(evento.getFechaEvento())+"')";
        
        try{
            PreparedStatement ps = c.prepareStatement(query);
            
            return ps.executeUpdate();
            
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return -1;
        
    }
    
    
    
    
    
    
}
