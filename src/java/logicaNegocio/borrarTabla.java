/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocio;

import DAO.BlogJpaController;
import DAO.ClienteJpaController;
import DAO.ComidaJpaController;
import DAO.DietaJpaController;
import DAO.EjercicioJpaController;
import DAO.ReservaJpaController;
import DAO.RutinaJpaController;
import DAO.SalaJpaController;
import DAO.TarifaJpaController;
import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Salvi
 */
public class borrarTabla extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectoGimnasioPU");
        
        String tabla = request.getParameter("tabla");
        
        switch (tabla) {
            case "cliente":
                ClienteJpaController ctrCliente = new ClienteJpaController(emf);
                String nombreUsuario = request.getParameter("nombreUsuario");
                 {
                    try {
                        ctrCliente.destroy(nombreUsuario);
                    } catch (IllegalOrphanException ex) {
                        Logger.getLogger(borrarTabla.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(borrarTabla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                break;
            case "blog":
                BlogJpaController ctrBlog = new BlogJpaController(emf);
                String codog = request.getParameter("codBlog");
                int codBlog = Integer.parseInt(codog);
                 {
                    try {
                        ctrBlog.destroy(codBlog);
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(borrarTabla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                break;
            case "comida":
                String codC = request.getParameter("codComida");
                int codComida = Integer.parseInt(codC);
                ComidaJpaController ctrComida = new ComidaJpaController(emf);
                 {
                    try {
                        ctrComida.destroy(codComida);
                    } catch (IllegalOrphanException ex) {
                        Logger.getLogger(borrarTabla.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(borrarTabla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "ejercicio":
                String codEj = request.getParameter("codEjercicio");
                int codEjercicio = Integer.parseInt(codEj);
                EjercicioJpaController ctrEjer = new EjercicioJpaController(emf);
                 {
                    try {
                        ctrEjer.destroy(codEjercicio);
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(borrarTabla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "reserva":
               
                 String codCi = request.getParameter("codCita");
                int codCita = Integer.parseInt(codCi);
                ReservaJpaController ctrReserva =new ReservaJpaController(emf);
        {
            try {
                ctrReserva.destroy(codCita);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(borrarTabla.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
                
                break;
            case "rutina":

                
              String codRuti = request.getParameter("codRutina");
                int codRutina = Integer.parseInt(codRuti);
                RutinaJpaController ctrRuti=new RutinaJpaController(emf);
        {
            try {
                ctrRuti.destroy(codRutina);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(borrarTabla.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
                
                
                break;
            case "sala":
                
                String codsal = request.getParameter("codigoSala");
                int codigoSala = Integer.parseInt(codsal);                
                SalaJpaController ctrSala = new SalaJpaController(emf);
        {
            try {
                ctrSala.destroy(codigoSala);
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(borrarTabla.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(borrarTabla.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
                break;
            case "tarifa":
                String codta = request.getParameter("codTarifa");
                int codTarifa = Integer.parseInt(codta);
                TarifaJpaController ctrTarifa = new TarifaJpaController(emf);
                 {
                    try {
                        ctrTarifa.destroy(codTarifa);
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(borrarTabla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "dieta":
                String codiet = request.getParameter("codDieta");
                int codDieta = Integer.parseInt(codiet);
                DietaJpaController ctrDieta= new DietaJpaController(emf);
        {
            try {
                ctrDieta.destroy(codDieta);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(borrarTabla.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
                break;
        }
        
        response.sendRedirect("faces/borrarAdmin.xhtml");
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
