/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Salvi
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cliente.findByApellidos", query = "SELECT c FROM Cliente c WHERE c.apellidos = :apellidos")
    , @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "Cliente.findByFechaNacimiento", query = "SELECT c FROM Cliente c WHERE c.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Cliente.findByFoto", query = "SELECT c FROM Cliente c WHERE c.foto = :foto")
    , @NamedQuery(name = "Cliente.findByFechaRegistro", query = "SELECT c FROM Cliente c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Cliente.findByFechaValidez", query = "SELECT c FROM Cliente c WHERE c.fechaValidez = :fechaValidez")
    , @NamedQuery(name = "Cliente.findByPeso", query = "SELECT c FROM Cliente c WHERE c.peso = :peso")
    , @NamedQuery(name = "Cliente.findByObjetivo", query = "SELECT c FROM Cliente c WHERE c.objetivo = :objetivo")
    , @NamedQuery(name = "Cliente.findByObservacionesSalud", query = "SELECT c FROM Cliente c WHERE c.observacionesSalud = :observacionesSalud")
    , @NamedQuery(name = "Cliente.findByNombreUsuario", query = "SELECT c FROM Cliente c WHERE c.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "Cliente.findByPass", query = "SELECT c FROM Cliente c WHERE c.pass = :pass")
    , @NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email")
    , @NamedQuery(name = "Cliente.findByTipo", query = "SELECT c FROM Cliente c WHERE c.tipo = :tipo")})
public class Cliente implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nombreUsuario")
    private List<Dieta> dietaList;

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private Integer telefono;
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "foto")
    private String foto;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "fechaValidez")
    @Temporal(TemporalType.DATE)
    private Date fechaValidez;
    @Column(name = "peso")
    private Integer peso;
    @Column(name = "objetivo")
    private String objetivo;
    @Column(name = "observacionesSalud")
    private String observacionesSalud;
    @Id
    @Basic(optional = false)
    @Column(name = "nombreUsuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "pass")
    private String pass;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nombreUsuario")
    private List<Rutina> rutinaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nombreUsuario")
    private List<Reserva> reservaList;

    public Cliente() {
    }

    public Cliente(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Cliente(String nombreUsuario, String nombre, String pass, String email, String tipo) {
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.pass = pass;
        this.email = email;
        this.tipo = tipo;
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

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
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

    public Date getFechaValidez() {
        return fechaValidez;
    }

    public void setFechaValidez(Date fechaValidez) {
        this.fechaValidez = fechaValidez;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<Rutina> getRutinaList() {
        return rutinaList;
    }

    public void setRutinaList(List<Rutina> rutinaList) {
        this.rutinaList = rutinaList;
    }

    @XmlTransient
    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreUsuario != null ? nombreUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.nombreUsuario == null && other.nombreUsuario != null) || (this.nombreUsuario != null && !this.nombreUsuario.equals(other.nombreUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Cliente[ nombreUsuario=" + nombreUsuario + " ]";
    }

    @XmlTransient
    public List<Dieta> getDietaList() {
        return dietaList;
    }

    public void setDietaList(List<Dieta> dietaList) {
        this.dietaList = dietaList;
    }
    
}
