/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocio;

import DAO.ClienteJpaController;
import DTO.Cliente;

import beans.manageSesion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Salvi
 */
public class loguear extends HttpServlet {

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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectoGimnasioPU");
        ClienteJpaController ctrCliente = new ClienteJpaController(emf);

        String nombreUsuario = (String) request.getParameter("nombreUsu");
        String pass = (String) request.getParameter("pass");
        Cliente usu = null;
        List<Cliente> listaClientes = ctrCliente.findClienteEntities();
        PrintWriter out = response.getWriter();
        boolean encontrado = false;
        boolean esMonitor = false;
        boolean esAdmin = false;
        boolean esCliente = false;
        boolean contraIncorrecta = false;
        String mensaje = "";
        String url = "";
        for (int i = 0; i < listaClientes.size(); i++) {

            if (nombreUsuario.equals(listaClientes.get(i).getNombreUsuario())) {

                if (pass.equals(listaClientes.get(i).getPass())) {

                    encontrado = true;
                    usu = ctrCliente.findCliente(nombreUsuario);
                    if (listaClientes.get(i).getTipo().equals("monitor")||listaClientes.get(i).getTipo().equals("MONITOR")) {
                        esMonitor = true;

                    }
                    if (listaClientes.get(i).getTipo().equals("admin")||listaClientes.get(i).getTipo().equals("ADMIN")) {
                        esAdmin = true;

                    }
                    if (listaClientes.get(i).getTipo().equals("cliente")||listaClientes.get(i).getTipo().equals("CLIENTE")) {
                        esCliente = true;

                    }
                } else {
                    contraIncorrecta = true;

                }

            }

        }

        HttpSession sesion = request.getSession();

        manageSesion mg = new manageSesion();

        mg.setClienteLogeado(usu);
        mg.setOklog(true);
        sesion.setAttribute("bSesion", mg);

        if (encontrado == true) {

            if (esMonitor == true) {
                mensaje = "monitor";
            }
            if (esAdmin == true) {
                mensaje = "admin";
            }
            if (esCliente == true) {
                mensaje = "cliente";
            }

        } else {
            mensaje = "Usuario no encontrado";
        }
        if (contraIncorrecta == true) {

            mensaje = "Contraseña Incorrecta";

        }
        response.getWriter().print(mensaje);

        out.flush();
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
