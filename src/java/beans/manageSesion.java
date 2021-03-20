/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DAO.BlogJpaController;
import DAO.ClienteJpaController;
import DAO.exceptions.NonexistentEntityException;
import DTO.Cliente;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Salvi
 */
@ManagedBean(name = "bSesion")
@SessionScoped
public class manageSesion {

    /**
     * Creates a new instance of manageSesion
     */
    private UploadedFile imgArt;
    private EntityManagerFactory emf;
    private ClienteJpaController ctrCliente;
    private Cliente clienteLogeado;
    public boolean Oklog;
    private String email;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String peso;
    private String observaciones;
    private String objetivo;
    private String pass;
    private String imagen;

    public manageSesion() {
        Oklog = false;
        emf = Persistence.createEntityManagerFactory("proyectoGimnasioPU");

        ctrCliente = new ClienteJpaController(emf);
    }

    public UploadedFile getImgArt() {
        return imgArt;
    }

    public void setImgArt(UploadedFile imgArt) {
        this.imgArt = imgArt;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ClienteJpaController getCtrCliente() {
        return ctrCliente;
    }

    public void setCtrCliente(ClienteJpaController ctrCliente) {
        this.ctrCliente = ctrCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public Cliente getClienteLogeado() {
        return clienteLogeado;
    }

    public void setClienteLogeado(Cliente clienteLogeado) {
        this.clienteLogeado = clienteLogeado;
    }

    public boolean isOklog() {
        return Oklog;
    }

    public void setOklog(boolean Oklog) {
        this.Oklog = Oklog;
    }

    public static void redireccionar(String url) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ctx.redirect(url);
        } catch (Exception ex) {
        }

    }

    public static void logout(String url) {

        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ((HttpSession) ctx.getSession(false)).invalidate();
            ctx.redirect(url);
        } catch (Exception ex) {
        }

    }

    public void ActualizarPerfil() throws FileNotFoundException {

        if (!email.equals("")) {
            clienteLogeado.setEmail(email);
        }
        if (!nombre.equals("")) {
            clienteLogeado.setNombre(nombre);
        }
        if (!apellidos.equals("")) {
            clienteLogeado.setApellidos(apellidos);
        }
        if (!direccion.equals("")) {
            clienteLogeado.setApellidos(apellidos);
        }
        if (!telefono.equals("")) {
            int tel = Integer.parseInt(telefono);
            clienteLogeado.setTelefono(tel);
        }
        if (!peso.equals("")) {
            int pes = Integer.parseInt(peso);
            clienteLogeado.setPeso(pes);
        }
        if (!observaciones.equals("")) {
            clienteLogeado.setObservacionesSalud(observaciones);
        }
        if (!objetivo.equals("")) {
            clienteLogeado.setObservacionesSalud(observaciones);
        }
        if (!pass.equals("")) {
            clienteLogeado.setPass(pass);
        }

        if (!imgArt.getFileName().equals("")) {

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            String path = ec.getRealPath("/");
            String realPath = path + "imagenes/clientes/" + imgArt.getFileName();
            imagen = imgArt.getFileName();
            try {
                FileInputStream in = (FileInputStream) imgArt.getInputstream();
                FileOutputStream out = new FileOutputStream(realPath);

                byte[] buffer = new byte[(int) imgArt.getSize()];
                int cont = 0;

                while ((cont = in.read(buffer)) != -1) {
                    out.write(buffer, 0, cont);
                }

                in.close();
                out.close();

            } catch (IOException ex) {
                Logger.getLogger(manageSesion.class.getName()).log(Level.SEVERE, null, ex);
            }
            clienteLogeado.setFoto(imagen);
        }

        try {
            ctrCliente.edit(clienteLogeado);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(manageSesion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(manageSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
}
