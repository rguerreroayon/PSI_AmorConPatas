/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import accesoDatos.DAOAdoptantes;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objetosNegocio.Adoptante;

/**
 *
 * @author roberto
 */
@WebServlet(name = "ServletAdoptantes", urlPatterns = {"/ServletAdoptantes"})
public class ServletAdoptantes extends HttpServlet {

    DAOAdoptantes adoptantes = new DAOAdoptantes();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        obtenerAdoptantesNombre(request.getParameter("nombreCampoAdoptante"));

        rd = request.getRequestDispatcher("JSP_adopcionPage.jsp");

        rd.forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void obtenerAdoptantesNombre(String nombre) {

        try {
            ArrayList<Adoptante> ad = adoptantes.queryGetAdoptantesPorNombre(nombre);
            
            out.println("<select name='adoptanteSelect'>");
            for (Adoptante adoptante : ad) {
                out.println("<option name='idAdoptante' value='" + String.valueOf(adoptante.getIdAdoptante()) + "'>" + adoptante.getIdAdoptante() + " - " + adoptante.getNombre() + "</option>");
            }   
            out.println("</select>");

        } catch (Exception e) {
            out.println("U DID SOMETHING NASTY!");
        }

    }

}
