package beans;

import DAO.*;
import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DTO.*;
import bd.MetodosBD;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.model.UploadedFile;

public class beanModificar {

    private String nombreUsuarioB;
    String tablaAModificar;
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
//******************************************************************************    
//*CLIENTE                                                                     *
//******************************************************************************
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
    private String imagenCliente;
    private UploadedFile imgCli;
    //******************************************************************************  
//******************************************************************************    
//*DIETA                                                                   *
//****************************************************************************** 

    Dieta dieta;
    private String diaDieta;
    private String nombreUsuarioDieta;
    private String codigoComidaDieta;

//******************************************************************************    
//*RUTINA                                                                     *
//******************************************************************************
    private String codEjercicioRutina;
    private String repeticionesRutina;
    private String diaRutina;
    private String nombreUsuarioRutina;

//******************************************************************************  
//******************************************************************************    
//*RESERVA                                                                     *
//******************************************************************************     
    private String fechaReserva;
    private String hora;
    private String codSalaReserva;
    private String nombreUsuarioReserva;
//******************************************************************************  
//******************************************************************************    
//*SALA                                                              *
//****************************************************************************** 
    private Dieta dietaSel;
    private String nombreSala;
    private String capacidad;
    private String descripcionSala;
    private String imagenSala;
    private UploadedFile imgSala;
//******************************************************************************    
//*TARIFA                                                                   *
//******************************************************************************     

    private String nombreTarifa;
    private String precio;
    private String meses;
//******************************************************************************    
// BLOG                                                                        *
//******************************************************************************
    private String tituloNoticia;
    private String descripcionBlog;
    private String videoBlog;

//******************************************************************************    
// COMIDA                                                                      *
//******************************************************************************    
    private String nombreComida;
    private String fotoComida;
    private UploadedFile fotoCo;
    private String receta;
    private String cantidad;
    private String tipoComida;

//******************************************************************************    
//*SELECCION                                                               *
//******************************************************************************
    private String Seleccionado;
    private Cliente clienteSel;
    private Blog blogSel;
    private Comida comidaSel;
    private Rutina rutinaSel;
    private Ejercicio ejercicioSel;
    private Reserva reservaSel;
    private Sala salaSel;
    private Tarifa tarifaSel;

//******************************************************************************    
//*RUTINA                                                                   *
//******************************************************************************       
//******************************************************************************    
//*EJERCICIO                                                                   *
//******************************************************************************    
    private String nombreEjercicio;
    private String video;
    private String fotoEjercicio;
    private UploadedFile fotoEjer;
    private String grupoMuscular;

    /*---------------------------------------------------------------------------------------*/
    public beanModificar() {
        clienteSel = new Cliente();
        blogSel = new Blog();
        comidaSel = new Comida();
        rutinaSel = new Rutina();
        ejercicioSel = new Ejercicio();
        reservaSel = new Reserva();
        salaSel = new Sala();
        tarifaSel = new Tarifa();
        dietaSel = new Dieta();
        dieta = new Dieta();
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
        ctrRutina = new RutinaJpaController(emf);
        listaBlog = new ArrayList();
        listaClientes = new ArrayList();
        listaComidas = new ArrayList();
        listaDieta = new ArrayList();
        listaEjercicio = new ArrayList();
        listaFactura = new ArrayList();
        listacabeceras = new ArrayList();
        listaTarifas = new ArrayList();
        listaSala = new ArrayList();
        listaReservas = new ArrayList();
        listaRutina = new ArrayList();
    }

    public RutinaJpaController getCtrRutina() {

        return ctrRutina;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public Dieta getDieta() {
        return dieta;
    }

    public void setDieta(Dieta dieta) {
        this.dieta = dieta;
    }

    public String getDiaDieta() {
        return diaDieta;
    }

    public void setDiaDieta(String diaDieta) {
        this.diaDieta = diaDieta;
    }

    public String getNombreUsuarioDieta() {
        return nombreUsuarioDieta;
    }

    public void setNombreUsuarioDieta(String nombreUsuarioDieta) {
        this.nombreUsuarioDieta = nombreUsuarioDieta;
    }

    public String getCodigoComidaDieta() {
        return codigoComidaDieta;
    }

    public void setCodigoComidaDieta(String codigoComidaDieta) {
        this.codigoComidaDieta = codigoComidaDieta;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getHora() {
        return hora;
    }

    public Dieta getDietaSel() {
        return dietaSel;
    }

    public void setDietaSel(Dieta dietaSel) {
        this.dietaSel = dietaSel;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCodSalaReserva() {
        return codSalaReserva;
    }

    public void setCodSalaReserva(String codSalaReserva) {
        this.codSalaReserva = codSalaReserva;
    }

    public String getNombreUsuarioReserva() {
        return nombreUsuarioReserva;
    }

    public void setNombreUsuarioReserva(String nombreUsuarioReserva) {
        this.nombreUsuarioReserva = nombreUsuarioReserva;
    }

    public void setCtrRutina(RutinaJpaController ctrRutina) {
        this.ctrRutina = ctrRutina;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public String getVideo() {
        return video;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getDescripcionSala() {
        return descripcionSala;
    }

    public void setDescripcionSala(String descripcionSala) {
        this.descripcionSala = descripcionSala;
    }

    public String getImagenSala() {
        return imagenSala;
    }

    public void setImagenSala(String imagenSala) {
        this.imagenSala = imagenSala;
    }

    public UploadedFile getImgSala() {
        return imgSala;
    }

    public void setImgSala(UploadedFile imgSala) {
        this.imgSala = imgSala;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getFotoEjercicio() {
        return fotoEjercicio;
    }

    public void setFotoEjercicio(String fotoEjercicio) {
        this.fotoEjercicio = fotoEjercicio;
    }

    public UploadedFile getFotoEjer() {
        return fotoEjer;
    }

    public void setFotoEjer(UploadedFile fotoEjer) {
        this.fotoEjer = fotoEjer;
    }

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public List<Rutina> getListaRutina() {
        listaRutina = ctrRutina.findRutinaEntities();
        return listaRutina;
    }

    public String getNombreComida() {
        return nombreComida;
    }

    public void setNombreComida(String nombreComida) {
        this.nombreComida = nombreComida;
    }

    public String getFotoComida() {
        return fotoComida;
    }

    public String getNombreTarifa() {
        return nombreTarifa;
    }

    public void setNombreTarifa(String nombreTarifa) {
        this.nombreTarifa = nombreTarifa;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getMeses() {
        return meses;
    }

    public void setMeses(String meses) {
        this.meses = meses;
    }

    public void setFotoComida(String fotoComida) {
        this.fotoComida = fotoComida;
    }

    public UploadedFile getFotoCo() {
        return fotoCo;
    }

    public void setFotoCo(UploadedFile fotoCo) {
        this.fotoCo = fotoCo;
    }

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

    public String getCantidad() {
        return cantidad;
    }

    public String getCodEjercicioRutina() {
        return codEjercicioRutina;
    }

    public void setCodEjercicioRutina(String codEjercicioRutina) {
        this.codEjercicioRutina = codEjercicioRutina;
    }

    public String getRepeticionesRutina() {
        return repeticionesRutina;
    }

    public void setRepeticionesRutina(String repeticionesRutina) {
        this.repeticionesRutina = repeticionesRutina;
    }

    public String getDiaRutina() {
        return diaRutina;
    }

    public void setDiaRutina(String diaRutina) {
        this.diaRutina = diaRutina;
    }

    public String getNombreUsuarioRutina() {
        return nombreUsuarioRutina;
    }

    public void setNombreUsuarioRutina(String nombreUsuarioRutina) {
        this.nombreUsuarioRutina = nombreUsuarioRutina;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }

    public void setListaRutina(List<Rutina> listaRutina) {
        this.listaRutina = listaRutina;
    }

    public List<Reserva> getListaReservas() {
        listaReservas = ctrReserva.findReservaEntities();
        return listaReservas;
    }

    public void setListaReservas(List<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

    public String getTablaAModificar() {
        return tablaAModificar;
    }

    public void setTablaAModificar(String tablaAModificar) {
        this.tablaAModificar = tablaAModificar;
    }

    public List<Sala> getListaSala() {
        listaSala = ctrSala.findSalaEntities();
        return listaSala;
    }

    public void setListaSala(List<Sala> listaSala) {
        this.listaSala = listaSala;
    }

    public List<Tarifa> getListaTarifas() {
        listaTarifas = ctrTarifa.findTarifaEntities();
        return listaTarifas;
    }

    public void setListaTarifas(List<Tarifa> listaTarifas) {
        this.listaTarifas = listaTarifas;
    }

    public Cliente getClienteSel() {
        return clienteSel;
    }

    public void setClienteSel(Cliente clienteSel) {
        this.clienteSel = clienteSel;
    }

    public Blog getBlogSel() {
        return blogSel;
    }

    public void setBlogSel(Blog blogSel) {
        this.blogSel = blogSel;
    }

    public Comida getComidaSel() {
        return comidaSel;
    }

    public String getImagenCliente() {
        return imagenCliente;
    }

    public void setImagenCliente(String imagenCliente) {
        this.imagenCliente = imagenCliente;
    }

    public UploadedFile getImgCli() {
        return imgCli;
    }

    public void setImgCli(UploadedFile imgCli) {
        this.imgCli = imgCli;
    }

    public String getTituloNoticia() {
        return tituloNoticia;
    }

    public void setTituloNoticia(String tituloNoticia) {
        this.tituloNoticia = tituloNoticia;
    }

    public String getDescripcionBlog() {
        return descripcionBlog;
    }

    public void setDescripcionBlog(String descripcionBlog) {
        this.descripcionBlog = descripcionBlog;
    }

    public String getVideoBlog() {
        return videoBlog;
    }

    public void setVideoBlog(String videoBlog) {
        this.videoBlog = videoBlog;
    }

    public void setComidaSel(Comida comidaSel) {
        this.comidaSel = comidaSel;
    }

    public Rutina getRutinaSel() {
        return rutinaSel;
    }

    public void setRutinaSel(Rutina rutinaSel) {
        this.rutinaSel = rutinaSel;
    }

    public Ejercicio getEjercicioSel() {
        return ejercicioSel;
    }

    public void setEjercicioSel(Ejercicio ejercicioSel) {
        this.ejercicioSel = ejercicioSel;
    }

    public Reserva getReservaSel() {
        return reservaSel;
    }

    public void setReservaSel(Reserva reservaSel) {
        this.reservaSel = reservaSel;
    }

    public Sala getSalaSel() {
        return salaSel;
    }

    public void setSalaSel(Sala salaSel) {
        this.salaSel = salaSel;
    }

    public Tarifa getTarifaSel() {
        return tarifaSel;
    }

    public void setTarifaSel(Tarifa tarifaSel) {
        this.tarifaSel = tarifaSel;
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
        listaBlog = ctrBlog.findBlogEntities();
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
        listaComidas = ctrComida.findComidaEntities();
        return listaComidas;
    }

    public void setListaComidas(List<Comida> listaComidas) {
        this.listaComidas = listaComidas;
    }

    public List<Dieta> getListaDieta() {
        listaDieta = ctrDieta.findDietaEntities();
        return listaDieta;
    }

    public void setListaDieta(List<Dieta> listaDieta) {
        this.listaDieta = listaDieta;
    }

    public List<Ejercicio> getListaEjercicio() {
        listaEjercicio = ctrEjercicio.findEjercicioEntities();
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

    public String getSeleccionado() {
        return Seleccionado;
    }

    public void setSeleccionado(String Seleccionado) {
        this.Seleccionado = Seleccionado;
    }

//******************************************************************************    
//*MODIFICAR TABLE                                                             *
//******************************************************************************
    public void modificarTabla(ActionEvent event) {
//clientesel  ==> objeto
//selcliente ==> clave

        Seleccionado = (String) event.getComponent().getAttributes().get("clave").toString();
        tablaAModificar = (String) event.getComponent().getAttributes().get("tabla").toString();
        switch (tablaAModificar) {
            case "cliente":
                clienteSel = ctrCliente.findCliente(Seleccionado);

                break;
            case "blog":
                int codC = Integer.parseInt(Seleccionado);
                blogSel = ctrBlog.findBlog(codC);
                break;
            case "comida":
                int codCo = Integer.parseInt(Seleccionado);
                comidaSel = ctrComida.findComida(codCo);
                break;
            case "rutina":
                int codRu = Integer.parseInt(Seleccionado);
                rutinaSel = ctrRutina.findRutina(codRu);
                break;
            case "ejercicio":
                int codEjer = Integer.parseInt(Seleccionado);
                ejercicioSel = ctrEjercicio.findEjercicio(codEjer);
                break;
            case "reserva":
                int codRe = Integer.parseInt(Seleccionado);
                reservaSel = ctrReserva.findReserva(codRe);
                break;
            case "sala":
                int codSala = Integer.parseInt(Seleccionado);
                salaSel = ctrSala.findSala(codSala);
                break;
            case "tarifa":
                int codTari = Integer.parseInt(Seleccionado);
                tarifaSel = ctrTarifa.findTarifa(codTari);
                break;
            case "dieta":
                int coddi = Integer.parseInt(Seleccionado);
                dietaSel = ctrDieta.findDieta(coddi);
                break;
            default:
            // code block
        }

    }

    //******************************************************************************    
    //*MODIFICAR CLIENTE                                                           *
    //******************************************************************************
    public void modificarCliente() {
        //clienteSel

        if (!nombreCliente.equals("")) {

            clienteSel.setNombre(nombreCliente);
        }
        if (!apellidos.equals("")) {
            clienteSel.setApellidos(apellidos);
        }
        if (!nombreUsuario.equals("")) {
            clienteSel.setNombreUsuario(nombreUsuario);
        }
        if (!pass.equals("")) {
            clienteSel.setPass(pass);
        }
        if (!email.equals("")) {
            clienteSel.setEmail(email);
        }
        if (!direccion.equals("")) {
            clienteSel.setDireccion(direccion);
        }
        if (!telefono.equals("")) {

            int tel = Integer.parseInt(telefono);
            clienteSel.setTelefono(tel);
        }
        if (!fechaNacimiento.equals("")) {
            Date fNaci = Date.valueOf(fechaNacimiento);
            clienteSel.setFechaNacimiento(fNaci);
        }

        if (!fechaValidez.equals("")) {
            Date fValidez = Date.valueOf(fechaValidez);
            clienteSel.setFechaValidez(fValidez);
        }
        if (!peso.equals("")) {
            int pesi = Integer.parseInt(peso);
            clienteSel.setPeso(pesi);

        }
        if (!objetivo.equals("")) {
            clienteSel.setObjetivo(objetivo);
        }
        if (!observacionesSalud.equals("")) {
            clienteSel.setObjetivo(objetivo);
        }
        if (!tipo.equals("")) {
            clienteSel.setTipo(tipo);
        }
        if (!imgCli.getFileName().equals("")) {

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            String path = ec.getRealPath("/");
            String realPath = path + "imagenes/clientes/" + imgCli.getFileName();
            imagenCliente = imgCli.getFileName();
            try {
                FileInputStream in = (FileInputStream) imgCli.getInputstream();
                FileOutputStream out = new FileOutputStream(realPath);

                byte[] buffer = new byte[(int) imgCli.getSize()];
                int cont = 0;

                while ((cont = in.read(buffer)) != -1) {
                    out.write(buffer, 0, cont);
                }

                in.close();
                out.close();

            } catch (IOException ex) {
                Logger.getLogger(manageSesion.class.getName()).log(Level.SEVERE, null, ex);
            }

            clienteSel.setFoto(imagenCliente);
        }

        try {
            ctrCliente.edit(clienteSel);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(beanModificar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(beanModificar.class.getName()).log(Level.SEVERE, null, ex);
        }
        tablaAModificar = "";
    }

    //******************************************************************************    
    //*MODIFICAR BLOG                                                              *
    //******************************************************************************
    public void modificarBlog() {

        if (!tituloNoticia.equals("")) {
            blogSel.setTituloNoticia(tituloNoticia);
        }
        if (!descripcionBlog.equals("")) {
            blogSel.setDescripcion(descripcionBlog);
        }
        if (!videoBlog.equals("")) {
            blogSel.setVideo(videoBlog);
        }
        try {
            ctrBlog.edit(blogSel);
        } catch (Exception ex) {
            Logger.getLogger(beanModificar.class.getName()).log(Level.SEVERE, null, ex);
        }
        tablaAModificar = "";
    }

    //******************************************************************************    
    //*MODIFICAR COMIDA                                                              *
    //******************************************************************************    
    public void modificarComida() {

        if (!nombreComida.equals("")) {
            comidaSel.setNombreComida(nombreComida);
        }
        if (!receta.equals("")) {
            comidaSel.setReceta(receta);
        }
        if (!cantidad.equals("")) {
            int Cant = Integer.parseInt(cantidad);
            comidaSel.setCantidad(Cant);
        }
        if (!tipoComida.equals("")) {
            comidaSel.setTipoComida(tipoComida);
        }
        if (!fotoCo.getFileName().equals("")) {

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            String path = ec.getRealPath("/");
            String realPath = path + "imagenes/alimentos/" + fotoCo.getFileName();
            fotoComida = fotoCo.getFileName();
            try {
                FileInputStream in = (FileInputStream) fotoCo.getInputstream();
                FileOutputStream out = new FileOutputStream(realPath);

                byte[] buffer = new byte[(int) fotoCo.getSize()];
                int cont = 0;

                while ((cont = in.read(buffer)) != -1) {
                    out.write(buffer, 0, cont);
                }

                in.close();
                out.close();

            } catch (IOException ex) {
                Logger.getLogger(manageSesion.class.getName()).log(Level.SEVERE, null, ex);
            }
            comidaSel.setFotoComida(fotoComida);
        }
        try {
            ctrComida.edit(comidaSel);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(beanModificar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(beanModificar.class.getName()).log(Level.SEVERE, null, ex);
        }
        tablaAModificar = "";
    }
    //******************************************************************************    
    //*MODIFICAR EJERCICIO                                                              *
    //******************************************************************************    

    public void modificarEjercicio() {
        if (!nombreEjercicio.equals("")) {
            ejercicioSel.setNombreEjercicio(nombreEjercicio);
        }
        if (!video.equals("")) {
            ejercicioSel.setVideo(video);
        }
        if (!grupoMuscular.equals("")) {
            ejercicioSel.setGrupo(grupoMuscular);
        }
        if (!nombreEjercicio.equals("")) {
            ejercicioSel.setNombreEjercicio(nombreEjercicio);
        }
        if (!fotoEjer.getFileName().equals("")) {

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            String path = ec.getRealPath("/");
            String realPath = path + "imagenes/ejercicios/" + fotoEjer.getFileName();
            fotoEjercicio = fotoEjer.getFileName();
            try {
                FileInputStream in = (FileInputStream) fotoEjer.getInputstream();
                FileOutputStream out = new FileOutputStream(realPath);

                byte[] buffer = new byte[(int) fotoEjer.getSize()];
                int cont = 0;

                while ((cont = in.read(buffer)) != -1) {
                    out.write(buffer, 0, cont);
                }

                in.close();
                out.close();

            } catch (IOException ex) {
                Logger.getLogger(manageSesion.class.getName()).log(Level.SEVERE, null, ex);
            }
            ejercicioSel.setFoto(fotoEjercicio);
        }
        try {
            ctrEjercicio.edit(ejercicioSel);
        } catch (Exception ex) {
            Logger.getLogger(beanModificar.class.getName()).log(Level.SEVERE, null, ex);
        }
        tablaAModificar = "";
    }

//******************************************************************************    
//*MODIFICAR TARIFA                                                              *
    //******************************************************************************    
    public void modificarTarifa() {

        if (!nombreTarifa.equals("")) {
            tarifaSel.setNombreTarifa(nombreTarifa);
        }
        if (!meses.equals("")) {
            int mes = Integer.parseInt(meses);
            tarifaSel.setMeses(mes);
        }
        if (!precio.equals("")) {
            long pre = Long.parseLong(precio);
            tarifaSel.setPrecio(pre);
        }
        try {
            ctrTarifa.edit(tarifaSel);
        } catch (Exception ex) {
            Logger.getLogger(beanModificar.class.getName()).log(Level.SEVERE, null, ex);
        }
        tablaAModificar = "";
    }
//******************************************************************************    
//*MODIFICAR SALA                                                              *
//******************************************************************************        

    public void modificarSala() {
        if (!nombreSala.equals("")) {
            salaSel.setNombre(nombreSala);
        }
        if (!capacidad.equals("")) {
            int sc = Integer.parseInt(capacidad);
            salaSel.setCapacidad(sc);
        }
        if (!nombreSala.equals("")) {
            salaSel.setNombre(nombreSala);
        }
        if (!descripcionSala.equals("")) {
            salaSel.setDescripcion(descripcionSala);
        }

        if (!imgSala.getFileName().equals("")) {

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            String path = ec.getRealPath("/");
            String realPath = path + "imagenes/clases/" + imgSala.getFileName();
            imagenSala = imgSala.getFileName();
            try {
                FileInputStream in = (FileInputStream) imgSala.getInputstream();
                FileOutputStream out = new FileOutputStream(realPath);

                byte[] buffer = new byte[(int) imgSala.getSize()];
                int cont = 0;

                while ((cont = in.read(buffer)) != -1) {
                    out.write(buffer, 0, cont);
                }

                in.close();
                out.close();

            } catch (IOException ex) {
                Logger.getLogger(manageSesion.class.getName()).log(Level.SEVERE, null, ex);
            }
            salaSel.setImagen(imagenSala);
        }
        try {
            ctrSala.edit(salaSel);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(beanModificar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(beanModificar.class.getName()).log(Level.SEVERE, null, ex);
        }
        tablaAModificar = "";
    }

    public void modificarReserva() {
        if (!fechaReserva.equals("")) {
            Date fRe = Date.valueOf(fechaReserva);
            reservaSel.setFecha(fRe);
        }

        if (!nombreUsuarioReserva.equals("")) {

            reservaSel.setNombreUsuario(ctrCliente.findCliente(nombreUsuarioReserva));
        }
        if (!hora.equals("")) {
            int hRe = Integer.parseInt(hora);
            reservaSel.setHora(hRe);
        }
        if (!codSalaReserva.equals("")) {
            int cReser = Integer.parseInt(codSalaReserva);
            reservaSel.setCodSala(ctrSala.findSala(cReser));
        }
        try {
            ctrReserva.edit(reservaSel);
        } catch (Exception ex) {
            Logger.getLogger(beanModificar.class.getName()).log(Level.SEVERE, null, ex);
        }
        tablaAModificar = "";
    }

    public void modificarRutina() {
        if (!codEjercicioRutina.equals("")) {
            int cRu = Integer.parseInt(codEjercicioRutina);
            rutinaSel.setEjercicio(ctrEjercicio.findEjercicio(cRu));
            rutinaSel.setCodEjercicio(cRu);

        }
        if (!repeticionesRutina.equals("")) {
            int crep = Integer.parseInt(repeticionesRutina);
            rutinaSel.setRepeticiones(crep);

        }
        if (!diaRutina.equals("")) {

            rutinaSel.setDia(diaRutina);

        }
        if (!nombreUsuarioRutina.equals("")) {

            rutinaSel.setNombreUsuario(ctrCliente.findCliente(nombreUsuarioRutina));

        }
        try {
            ctrRutina.edit(rutinaSel);
        } catch (Exception ex) {
            Logger.getLogger(beanModificar.class.getName()).log(Level.SEVERE, null, ex);
        }
        tablaAModificar = "";
    }

    public void modificarDieta() {

        if (!diaDieta.equals("")) {

            dietaSel.setDia(diaDieta);

        }
        if (!nombreUsuarioDieta.equals("")) {

            dietaSel.setNombreUsuario(ctrCliente.findCliente(nombreUsuarioDieta));

        }
        if (!codigoComidaDieta.equals("")) {
            int codDie = Integer.parseInt(codigoComidaDieta);
            dietaSel.setCodComida(ctrComida.findComida(codDie));

        }
        
        try {
            ctrDieta.edit(dietaSel);
        } catch (Exception ex) {
            Logger.getLogger(beanModificar.class.getName()).log(Level.SEVERE, null, ex);
        }
         tablaAModificar = "";

    }
}
