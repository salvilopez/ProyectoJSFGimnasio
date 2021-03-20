/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DAO.ClienteJpaController;

import DTO.Cliente;

import DTO.*;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Salvi
 */
public class beanLogin {

   private Cliente CLienteLogueado;
 
   private String nombreUsuario;
   private String pass;
   private EntityManagerFactory emf;
   private ClienteJpaController ctrCliente;
  
   public boolean Oklog;
    public beanLogin() {
        emf=Persistence.createEntityManagerFactory("proyectoGimnasioPU"); 
        ctrCliente= new ClienteJpaController(emf);

   
   
    }

    public boolean isOklog() {
        return Oklog;
    }

    public void setOklog(boolean Oklog) {
        this.Oklog = Oklog;
    }

    public EntityManagerFactory getEmf() {
        return emf;
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



    public Cliente getCLienteLogueado() {
        return CLienteLogueado;
    }

    public void setCLienteLogueado(Cliente CLienteLogueado) {
        this.CLienteLogueado = CLienteLogueado;
    }



    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
     public  void subirCliente()
    {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        
        try {
             HttpSession sessio= (HttpSession)ctx.getSession(true);
             manageSesion  bs=new manageSesion();
             bs.setClienteLogeado(CLienteLogueado);
             bs.setOklog(Oklog);
             sessio.setAttribute("manageSesion", bs);
               
              
        } catch (Exception ex) {
                                    }
        finally{
             
        }
    }    
 

    
}
