/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Salvi
 */
@Entity
@Table(name = "sala")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sala.findAll", query = "SELECT s FROM Sala s")
    , @NamedQuery(name = "Sala.findByCodigoSala", query = "SELECT s FROM Sala s WHERE s.codigoSala = :codigoSala")
    , @NamedQuery(name = "Sala.findByNombre", query = "SELECT s FROM Sala s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "Sala.findByCapacidad", query = "SELECT s FROM Sala s WHERE s.capacidad = :capacidad")
    , @NamedQuery(name = "Sala.findByDescripcion", query = "SELECT s FROM Sala s WHERE s.descripcion = :descripcion")
    , @NamedQuery(name = "Sala.findByImagen", query = "SELECT s FROM Sala s WHERE s.imagen = :imagen")})
public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoSala")
    private Integer codigoSala;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "capacidad")
    private int capacidad;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "imagen")
    private String imagen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codSala")
    private List<Reserva> reservaList;

    public Sala() {
    }

    public Sala(Integer codigoSala) {
        this.codigoSala = codigoSala;
    }

    public Sala(Integer codigoSala, String nombre, int capacidad, String descripcion, String imagen) {
        this.codigoSala = codigoSala;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Integer getCodigoSala() {
        return codigoSala;
    }

    public void setCodigoSala(Integer codigoSala) {
        this.codigoSala = codigoSala;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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
        hash += (codigoSala != null ? codigoSala.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sala)) {
            return false;
        }
        Sala other = (Sala) object;
        if ((this.codigoSala == null && other.codigoSala != null) || (this.codigoSala != null && !this.codigoSala.equals(other.codigoSala))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Sala[ codigoSala=" + codigoSala + " ]";
    }
    
}
