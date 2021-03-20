package beans;

import DAO.*;
import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DTO.*;
import bd.MetodosBD;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class beanBorrar {
    private String nombreUsuarioB;
    static String tablaSel;
    private BlogJpaController ctrBlog;
    private ClienteJpaController ctrCliente;
    private ComidaJpaController ctrComida;
    private DietaJpaController ctrDieta;
    private EjercicioJpaController ctrEjercicio;
    private FacturaJpaController ctrFactura;
    private ReservaJpaController ctrReserva;
    private SalaJpaController ctrSala;
    private TarifaJpaController ctrTarifa;
    private EntityManagerFactory emf;
    private RutinaJpaController ctrRutina;
    private List<Blog> listaBlog;
    private List<Cliente> listaClientes;
    private List<Comida> listaComidas;
    private List<Dieta> listaDieta;
    private List<Ejercicio> listaEjercicio;
    private List<Factura> listaFactura;
    private List<String> listacabeceras;
    private List<Tarifa> listaTarifas;
    private List<Sala> listaSala;
    private List<Reserva> listaReservas;
    private List<Rutina> listaRutina;
    /*Clientes*/
    private String nombreCliente;
    private String apellidos;
    private String nombreUsuario;
    private String pass;
    private String email;
    private String direccion;
    private String telefono;
    private String fechaNacimiento;
    private String fechaRegistro;
    private String fechaValidez;
    private String peso;
    private String objetivo;
    private String observacionesSalud;
    private String tipo;


    public beanBorrar() {
        emf = Persistence.createEntityManagerFactory("proyectoGimnasioPU");
        ctrBlog = new BlogJpaController(emf);
        ctrCliente = new ClienteJpaController(emf);
        ctrComida = new ComidaJpaController(emf);
        ctrDieta = new DietaJpaController(emf);
        ctrEjercicio = new EjercicioJpaController(emf);
        ctrFactura = new FacturaJpaController(emf);
        ctrReserva = new ReservaJpaController(emf);
        ctrSala = new SalaJpaController(emf);
        ctrTarifa = new TarifaJpaController(emf);
        ctrRutina= new RutinaJpaController(emf);
        listaBlog = new ArrayList();
        listaClientes = new ArrayList();
        listaComidas = new ArrayList();
        listaDieta = new ArrayList();
        listaEjercicio = new ArrayList();
        listaFactura = new ArrayList();
        listacabeceras = new ArrayList();
        listaTarifas=new  ArrayList();
        listaSala= new ArrayList();
        listaReservas= new ArrayList();
        listaRutina=new ArrayList();
    }

    public RutinaJpaController getCtrRutina() {
        
        return ctrRutina;
    }

    public void setCtrRutina(RutinaJpaController ctrRutina) {
        this.ctrRutina = ctrRutina;
    }

    public List<Rutina> getListaRutina() {
        listaRutina=ctrRutina.findRutinaEntities();
        return listaRutina;
    }

    public void setListaRutina(List<Rutina> listaRutina) {
        this.listaRutina = listaRutina;
    }

    public List<Reserva> getListaReservas() {
        listaReservas=ctrReserva.findReservaEntities();
        return listaReservas;
    }

    public void setListaReservas(List<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

    public List<Sala> getListaSala() {
        listaSala=ctrSala.findSalaEntities();
        return listaSala;
    }

    public void setListaSala(List<Sala> listaSala) {
        this.listaSala = listaSala;
    }

    public List<Tarifa> getListaTarifas() {
        listaTarifas=ctrTarifa.findTarifaEntities();
        return listaTarifas;
    }

    public void setListaTarifas(List<Tarifa> listaTarifas) {
        this.listaTarifas = listaTarifas;
    }

    public String getNombreUsuarioB() {
        return nombreUsuarioB;
    }

    public void setNombreUsuarioB(String nombreUsuarioB) {
        this.nombreUsuarioB = nombreUsuarioB;
    }

    public List<String> getListacabeceras() {
        return listacabeceras;
    }

    public void setListacabeceras(List<String> listacabeceras) {
        this.listacabeceras = listacabeceras;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTablaSel() {
        return tablaSel;
    }

    public void setTablaSel(String tablaSel) {
        this.tablaSel = tablaSel;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaValidez() {
        return fechaValidez;
    }

    public void setFechaValidez(String fechaValidez) {
        this.fechaValidez = fechaValidez;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getObservacionesSalud() {
        return observacionesSalud;
    }

    public void setObservacionesSalud(String observacionesSalud) {
        this.observacionesSalud = observacionesSalud;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public BlogJpaController getCtrBlog() {
        return ctrBlog;
    }

    public List<Blog> getListaBlog() {
        listaBlog=ctrBlog.findBlogEntities();
        return listaBlog;
    }

    public void setListaBlog(List<Blog> listaBlog) {
        this.listaBlog = listaBlog;
    }

    public List<Cliente> getListaClientes() {
        listaClientes = ctrCliente.findClienteEntities();
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Comida> getListaComidas() {
        listaComidas=ctrComida.findComidaEntities();
        return listaComidas;
    }

    public void setListaComidas(List<Comida> listaComidas) {
        this.listaComidas = listaComidas;
    }

    public List<Dieta> getListaDieta() {
        listaDieta=ctrDieta.findDietaEntities();
        return listaDieta;
    }

    public void setListaDieta(List<Dieta> listaDieta) {
        this.listaDieta = listaDieta;
    }

    public List<Ejercicio> getListaEjercicio() {
        listaEjercicio=ctrEjercicio.findEjercicioEntities();
        return listaEjercicio;
    }

    public void setListaEjercicio(List<Ejercicio> listaEjercicio) {
        this.listaEjercicio = listaEjercicio;
    }

    public List<Factura> getListaFactura() {
        return listaFactura;
    }

    public void setListaFactura(List<Factura> listaFactura) {
        this.listaFactura = listaFactura;
    }

    public void setCtrBlog(BlogJpaController ctrBlog) {
        this.ctrBlog = ctrBlog;
    }

    public ClienteJpaController getCtrCliente() {
        return ctrCliente;
    }

    public void setCtrCliente(ClienteJpaController ctrCliente) {
        this.ctrCliente = ctrCliente;
    }

    public ComidaJpaController getCtrComida() {
        return ctrComida;
    }

    public void setCtrComida(ComidaJpaController ctrComida) {
        this.ctrComida = ctrComida;
    }

    public DietaJpaController getCtrDieta() {
        return ctrDieta;
    }

    public void setCtrDieta(DietaJpaController ctrDieta) {
        this.ctrDieta = ctrDieta;
    }

    public EjercicioJpaController getCtrEjercicio() {
        return ctrEjercicio;
    }

    public void setCtrEjercicio(EjercicioJpaController ctrEjercicio) {
        this.ctrEjercicio = ctrEjercicio;
    }

    public FacturaJpaController getCtrFactura() {
        return ctrFactura;
    }

    public void setCtrFactura(FacturaJpaController ctrFactura) {
        this.ctrFactura = ctrFactura;
    }

    public ReservaJpaController getCtrReserva() {
        return ctrReserva;
    }

    public void setCtrReserva(ReservaJpaController ctrReserva) {
        this.ctrReserva = ctrReserva;
    }

    public SalaJpaController getCtrSala() {
        return ctrSala;
    }

    public void setCtrSala(SalaJpaController ctrSala) {
        this.ctrSala = ctrSala;
    }

    public TarifaJpaController getCtrTarifa() {
        return ctrTarifa;
    }

    public void setCtrTarifa(TarifaJpaController ctrTarifa) {
        this.ctrTarifa = ctrTarifa;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<String> traerCabecera() {

        String pepe="ddd";
       listacabeceras=MetodosBD.ObtenerCabecera( tablaSel);
       String pdepe="ddd"; 
        return listacabeceras;
    }
    public void borrarCliente(){
        String pepe="";
        try {
            ctrCliente.destroy(nombreUsuarioB);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(beanBorrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(beanBorrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
}
