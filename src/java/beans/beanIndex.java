/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DAO.ClienteJpaController;
import DAO.TarifaJpaController;
import DTO.Cliente;
import DTO.Tarifa;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Salvi
 */
public class beanIndex {

    private EntityManagerFactory emf;
    private ClienteJpaController ctrCliente;
    private TarifaJpaController ctrTarifa;
    List<Tarifa> listaTarifas;
    private Cliente cli;
    private String salud;
    private String mensaje;
    private String nombre;
    private String nombreUsuario;
    private String contrasena;
    private int peso;
    private String apellidos;
    private String email;
    private String direccion;
    private int telefono;
    private String objetivos;
    private String foto;
    private Date fechaNacimiento;
    private Date fechaRegistro;

    public beanIndex() {

        emf = Persistence.createEntityManagerFactory("proyectoGimnasioPU");
        ctrCliente = new ClienteJpaController(emf);
        listaTarifas = new ArrayList();
        ctrTarifa = new TarifaJpaController(emf);
    }

    public TarifaJpaController getCtrTarifa() {

        return ctrTarifa;
    }

    public void setCtrTarifa(TarifaJpaController ctrTarifa) {
        this.ctrTarifa = ctrTarifa;
    }

    public List<Tarifa> getListaTarifas() {
        if (listaTarifas.isEmpty()) {
            listaTarifas = ctrTarifa.findTarifaEntities();
        }

        return listaTarifas;
    }

    public void setListaTarifas(List<Tarifa> listaTarifas) {
        this.listaTarifas = listaTarifas;
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

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getSalud() {
        return salud;
    }

    public void setSalud(String salud) {
        this.salud = salud;
    }

    public void Registrarse() {
        cli = new Cliente();
        cli.setApellidos(apellidos);
        fechaRegistro = new Date();
        cli.setFechaRegistro(fechaRegistro);
        cli.setNombre(nombre);
        cli.setNombreUsuario(nombreUsuario);
        cli.setPass(contrasena);
        cli.setEmail(email);
        cli.setTipo("cliente");
        try {
            ctrCliente.create(cli);
        } catch (Exception ex) {
            Logger.getLogger(beanIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
        mensaje = "Cliente registrado correctamente ";

    }
    
    public void desconectar(){
    manageSesion.logout("faces/index.html");
    
    
    }
    public static Date fechaActual(){
    
      Date dia =new Date();
    
    return dia;
    }
}
