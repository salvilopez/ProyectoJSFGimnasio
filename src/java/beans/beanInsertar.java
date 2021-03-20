/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DAO.*;
import DTO.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.primefaces.model.UploadedFile;

public class beanInsertar {

    static String tablaSel;
    private EntityManagerFactory emf;
//******************************************************************************    
//*CLIENTE                                                                     *
//******************************************************************************
    private Cliente cliente;
    private String nombreUsuario;
    private String nombreCliente;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String fechaNacimiento;
    private String fechaValidez;
    private String peso;
    private String objetivo;
    private String pass;
    private String tipo;
    private String email;
    private String img;
    private String observacionesSalud;
    private UploadedFile imgArt;
    private ClienteJpaController ctrCliente;

//******************************************************************************  
//******************************************************************************    
//*EJERCICIO                                                                   *
//******************************************************************************    
    private Ejercicio ejer;
    private EjercicioJpaController ctrejercicio;
    private String nombreEjercicio;
    private String video;
    private String fotoEjercicio;
    private UploadedFile fotoEjer;
    private String grupoMuscular;
//******************************************************************************  
//******************************************************************************    
//*RESERVA                                                                   *
//******************************************************************************    

    private Reserva reserva;
    private ReservaJpaController ctrReserva;
    private String fechaReserva;
    private String hora;
    private String codSalaReserva;
    private String nombreUsuarioReserva;
    private List<Cliente> listaClientes;
    private List<Sala> listaSalas;

//******************************************************************************  
//******************************************************************************    
//*SALA                                                              *
//****************************************************************************** 
    private Sala sala;
    private SalaJpaController ctrSala;
    private String nombreSala;
    private String capacidad;
    private String descripcionSala;
    private String imagenSala;
    private UploadedFile imgSala;
//******************************************************************************  
//******************************************************************************    
//*DIETA                                                                   *
//****************************************************************************** 
    List<Comida> listaComidas;
    Dieta dieta;
    private String diaDieta;
    private String nombreUsuarioDieta;
    private String codigoComidaDieta;
    DietaJpaController ctrDieta;

//******************************************************************************  
//******************************************************************************    
//*TARIFA                                                                   *
//******************************************************************************     
    private Tarifa tari;
    private TarifaJpaController ctrTarifa;
    private String nombreTarifa;
    private String precio;
    private String meses;

//******************************************************************************  
//******************************************************************************    
//*BLOG                                                                        *
//******************************************************************************
    private Blog blog;
    private BlogJpaController ctrBlog;
    private String tituloNoticia;
    private String descripcionBlog;
    private String videoBlog;
//******************************************************************************  
//******************************************************************************    
//*RUTINA                                                                     *
//******************************************************************************

    private String codEjercicioRutina;
    private String repeticionesRutina;
    private String diaRutina;
    private String nombreUsuarioRutina;
    private RutinaJpaController ctrRutina;
    private Rutina rutina;
    private List<Ejercicio> listaEjercicios;

//******************************************************************************
//******************************************************************************    
//*COMIDA                                                                        *
//******************************************************************************
    private Comida comida;
    private ComidaJpaController ctrComida;
    private String nombreComida;
    private String fotoComida;
    private UploadedFile fotoCo;
    private String receta;
    private String cantidad;
    private String tipoComida;

//******************************************************************************
    public beanInsertar() {
        emf = Persistence.createEntityManagerFactory("proyectoGimnasioPU");
        ctrCliente = new ClienteJpaController(emf);
        ctrBlog = new BlogJpaController(emf);
        ctrComida = new ComidaJpaController(emf);
        ctrejercicio = new EjercicioJpaController(emf);
        ctrTarifa = new TarifaJpaController(emf);
        ctrSala = new SalaJpaController(emf);
        ctrReserva = new ReservaJpaController(emf);
        ctrRutina = new RutinaJpaController(emf);
        comida = new Comida();
        blog = new Blog();
        cliente = new Cliente();
        ejer = new Ejercicio();
        tari = new Tarifa();
        sala = new Sala();
        reserva = new Reserva();
        rutina = new Rutina();
        dieta = new Dieta();
        listaClientes = ctrCliente.findClienteEntities();
        listaEjercicios = ctrejercicio.findEjercicioEntities();
        listaSalas = ctrSala.findSalaEntities();
        listaComidas = new ArrayList();
        ctrDieta= new DietaJpaController(emf);
    }

    public List<Comida> getListaComidas() {
        listaComidas = ctrComida.findComidaEntities();
        return listaComidas;
    }

    public void setListaComidas(List<Comida> listaComidas) {
        this.listaComidas = listaComidas;
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

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Sala> getListaSalas() {
        return listaSalas;
    }

    public void setListaSalas(List<Sala> listaSalas) {
        this.listaSalas = listaSalas;
    }

    public Ejercicio getEjer() {
        return ejer;
    }

    public void setEjer(Ejercicio ejer) {
        this.ejer = ejer;
    }

    public EjercicioJpaController getCtrejercicio() {
        return ctrejercicio;
    }

    public void setCtrejercicio(EjercicioJpaController ctrejercicio) {
        this.ctrejercicio = ctrejercicio;
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

    public UploadedFile getFotoCo() {
        return fotoCo;
    }

    public void setFotoCo(UploadedFile fotoCo) {
        this.fotoCo = fotoCo;
    }

    public Comida getComida() {
        return comida;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }

    public ComidaJpaController getCtrComida() {
        return ctrComida;
    }

    public Tarifa getTari() {
        return tari;
    }

    public void setTari(Tarifa tari) {
        this.tari = tari;
    }

    public TarifaJpaController getCtrTarifa() {
        return ctrTarifa;
    }

    public void setCtrTarifa(TarifaJpaController ctrTarifa) {
        this.ctrTarifa = ctrTarifa;
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

    public RutinaJpaController getCtrRutina() {
        return ctrRutina;
    }

    public void setCtrRutina(RutinaJpaController ctrRutina) {
        this.ctrRutina = ctrRutina;
    }

    public Rutina getRutina() {
        return rutina;
    }

    public void setRutina(Rutina rutina) {
        this.rutina = rutina;
    }

    public List<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }

    public void setListaEjercicios(List<Ejercicio> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
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

    public void setCtrComida(ComidaJpaController ctrComida) {
        this.ctrComida = ctrComida;
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

    public void setFotoComida(String fotoComida) {
        this.fotoComida = fotoComida;
    }

    public String getReceta() {
        return receta;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public SalaJpaController getCtrSala() {
        return ctrSala;
    }

    public void setCtrSala(SalaJpaController ctrSala) {
        this.ctrSala = ctrSala;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public UploadedFile getImgSala() {
        return imgSala;
    }

    public void setImgSala(UploadedFile imgSala) {
        this.imgSala = imgSala;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

    public String getCantidad() {
        return cantidad;
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

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public BlogJpaController getCtrBlog() {
        return ctrBlog;
    }

    public void setCtrBlog(BlogJpaController ctrBlog) {
        this.ctrBlog = ctrBlog;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTablaSel() {
        return tablaSel;
    }

    public void setTablaSel(String tablaSel) {
        this.tablaSel = tablaSel;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
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

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getObservacionesSalud() {
        return observacionesSalud;
    }

    public void setObservacionesSalud(String observacionesSalud) {
        this.observacionesSalud = observacionesSalud;
    }

    public UploadedFile getImgArt() {
        return imgArt;
    }

    public void setImgArt(UploadedFile imgArt) {
        this.imgArt = imgArt;
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

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public ReservaJpaController getCtrReserva() {
        return ctrReserva;
    }

    public void setCtrReserva(ReservaJpaController ctrReserva) {
        this.ctrReserva = ctrReserva;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getHora() {
        return hora;
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

    public void crearCliente() {
        if (!nombreUsuario.equals("")) {
            cliente.setNombreUsuario(nombreUsuario);
        }
        if (!nombreCliente.equals("")) {
            cliente.setNombre(nombreCliente);
        }
        if (!apellidos.equals("")) {
            cliente.setApellidos(apellidos);
        }
        if (!direccion.equals("")) {
            cliente.setDireccion(direccion);
        }
        if (!objetivo.equals("")) {
            cliente.setObjetivo(objetivo);
        }
        if (!pass.equals("")) {
            cliente.setPass(pass);
        }
        if (!tipo.equals("")) {
            cliente.setTipo(tipo);
        }
        if (!email.equals("")) {
            cliente.setEmail(email);
        }
        if (!observacionesSalud.equals("")) {
            cliente.setObservacionesSalud(observacionesSalud);
        }
        if (!fechaNacimiento.equals("")) {
            Date fNac = Date.valueOf(fechaNacimiento);
            cliente.setFechaNacimiento(fNac);
        }
        if (!fechaValidez.equals("")) {
            Date fVal = Date.valueOf(fechaValidez);
            cliente.setFechaValidez(fVal);
        }
        if (!nombreUsuario.equals("")) {
            cliente.setEmail(nombreUsuario);
        }
        if (!telefono.equals("")) {
            int tel = Integer.parseInt(telefono);
            cliente.setTelefono(tel);
        }

        if (!peso.equals("")) {
            int pe = Integer.parseInt(peso);
            cliente.setPeso(pe);
        }

        if (!imgArt.getFileName().equals("")) {

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            String path = ec.getRealPath("/");
            String realPath = path + "imagenes/clientes/" + imgArt.getFileName();
            img = imgArt.getFileName();
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
            cliente.setFoto(img);
        }

        try {
            ctrCliente.create(cliente);
        } catch (Exception ex) {
            Logger.getLogger(beanInsertar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void crearBlog() {

        if (!tituloNoticia.equals("")) {
            blog.setTituloNoticia(tituloNoticia);
        }
        if (!descripcionBlog.equals("")) {
            blog.setDescripcion(descripcionBlog);
        }
        if (!videoBlog.equals("")) {
            blog.setVideo(videoBlog);
        }
        ctrBlog.create(blog);
    }

    public void crearComida() {

        if (!nombreComida.equals("")) {
            comida.setNombreComida(nombreComida);
        }
        if (!receta.equals("")) {
            comida.setReceta(receta);
        }
        if (!cantidad.equals("")) {
            int Cant = Integer.parseInt(cantidad);
            comida.setCantidad(Cant);
        }
        if (!tipoComida.equals("")) {
            comida.setTipoComida(tipoComida);
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
            comida.setFotoComida(fotoComida);
        }
        ctrComida.create(comida);
    }

    public void crearEjercicio() {
        if (!nombreEjercicio.equals("")) {
            ejer.setNombreEjercicio(nombreEjercicio);
        }
        if (!video.equals("")) {
            ejer.setVideo(video);
        }
        if (!grupoMuscular.equals("")) {
            ejer.setGrupo(grupoMuscular);
        }
        if (!nombreEjercicio.equals("")) {
            ejer.setNombreEjercicio(nombreEjercicio);
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
            ejer.setFoto(fotoEjercicio);
        }
        ctrejercicio.create(ejer);

    }

    public void crearTarifa() {

        if (!nombreTarifa.equals("")) {
            tari.setNombreTarifa(nombreTarifa);
        }
        if (!meses.equals("")) {
            int mes = Integer.parseInt(meses);
            tari.setMeses(mes);
        }
        if (!precio.equals("")) {
            long pre = Long.parseLong(precio);
            tari.setPrecio(pre);
        }
        ctrTarifa.create(tari);
    }

    public void crearSala() {
        if (!nombreSala.equals("")) {
            sala.setNombre(nombreSala);
        }
        if (!capacidad.equals("")) {
            int sc = Integer.parseInt(capacidad);
            sala.setCapacidad(sc);
        }
        if (!nombreSala.equals("")) {
            sala.setNombre(nombreSala);
        }
        if (!descripcionSala.equals("")) {
            sala.setDescripcion(descripcionSala);
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
            sala.setImagen(imagenSala);
        }
        ctrSala.create(sala);
    }

    public void crearReserva() {
        if (!fechaReserva.equals("")) {
            Date fRe = Date.valueOf(fechaReserva);
            reserva.setFecha(fRe);
        }

        if (!nombreUsuarioReserva.equals("")) {

            reserva.setNombreUsuario(ctrCliente.findCliente(nombreUsuarioReserva));
        }
        if (!hora.equals("")) {
            int hRe = Integer.parseInt(hora);
            reserva.setHora(hRe);
        }
        if (!codSalaReserva.equals("")) {
            int cReser = Integer.parseInt(codSalaReserva);
            reserva.setCodSala(ctrSala.findSala(cReser));
        }
        ctrReserva.create(reserva);
    }

    public void crearRutina() {
        /*  
         rut
    private String nombreUsuarioRutina;*/
        rutina.setCodRutina(null);
        if (!codEjercicioRutina.equals("")) {
            int cRu = Integer.parseInt(codEjercicioRutina);
            rutina.setEjercicio(ctrejercicio.findEjercicio(cRu));
            rutina.setCodEjercicio(cRu);

        }
        if (!repeticionesRutina.equals("")) {
            int crep = Integer.parseInt(repeticionesRutina);
            rutina.setRepeticiones(crep);

        }
        if (!diaRutina.equals("")) {

            rutina.setDia(diaRutina);

        }
        if (!nombreUsuarioRutina.equals("")) {

            rutina.setNombreUsuario(ctrCliente.findCliente(nombreUsuarioRutina));

        }
        ctrRutina.create(rutina);
    }

    public void crearDieta() {
        /*    private String diaDieta;
    private String nombreUsuarioDieta;
    private String codigoComidaDieta;*/
        if (!diaDieta.equals("")) {

            dieta.setDia(diaDieta);

        }
        if (!nombreUsuarioDieta.equals("")) {

            dieta.setNombreUsuario(ctrCliente.findCliente(nombreUsuarioDieta));

        }
        if (!codigoComidaDieta.equals("")) {
int codDie=Integer.parseInt(codigoComidaDieta);
            dieta.setCodComida(ctrComida.findComida(codDie));

        }
        try {
            ctrDieta.create(dieta);
        } catch (Exception ex) {
            Logger.getLogger(beanInsertar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
