/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocio;


import DAO.ClienteJpaController;
import DTO.Cliente;
import beans.*;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sara
 */
public class registrarse extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
     EntityManagerFactory emf=Persistence.createEntityManagerFactory("proyectoGimnasioPU"); 
     ClienteJpaController ctrCliente= new ClienteJpaController(emf);
        String nombreUsur=request.getParameter("nombreUsur");
        String passr1=request.getParameter("passr1");
        String nombrer=request.getParameter("nombrer");
        
        String apellidosr=request.getParameter("apellidosr");
        String emailr=request.getParameter("emailr");
        Cliente cli= new Cliente();
        Date fechaRE =new Date();
        cli.setNombreUsuario(nombreUsur);
        cli.setApellidos(apellidosr);
        cli.setPass(passr1);
        cli.setNombre(nombrer);
        cli.setFechaRegistro(fechaRE);
        cli.setEmail(emailr);
        cli.setTipo("cliente");
             HttpSession sesion = request.getSession();
                    manageSesion mg =new manageSesion();              
         
                 mg.setClienteLogeado(cli);
                 mg.setOklog(true);
                sesion.setAttribute("bSesion", mg);   
        try {
            ctrCliente.create(cli);
        } catch (Exception ex) {
            Logger.getLogger(registrarse.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("faces/index.xhtml");
                
                
                
                        
  
                                
                                
                                
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

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

}
