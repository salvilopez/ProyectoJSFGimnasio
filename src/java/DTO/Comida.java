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
@Table(name = "comida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comida.findAll", query = "SELECT c FROM Comida c")
    , @NamedQuery(name = "Comida.findByCodComida", query = "SELECT c FROM Comida c WHERE c.codComida = :codComida")
    , @NamedQuery(name = "Comida.findByNombreComida", query = "SELECT c FROM Comida c WHERE c.nombreComida = :nombreComida")
    , @NamedQuery(name = "Comida.findByFotoComida", query = "SELECT c FROM Comida c WHERE c.fotoComida = :fotoComida")
    , @NamedQuery(name = "Comida.findByReceta", query = "SELECT c FROM Comida c WHERE c.receta = :receta")
    , @NamedQuery(name = "Comida.findByCantidad", query = "SELECT c FROM Comida c WHERE c.cantidad = :cantidad")
    , @NamedQuery(name = "Comida.findByTipoComida", query = "SELECT c FROM Comida c WHERE c.tipoComida = :tipoComida")})
public class Comida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codComida")
    private Integer codComida;
    @Basic(optional = false)
    @Column(name = "nombreComida")
    private String nombreComida;
    @Basic(optional = false)
    @Column(name = "fotoComida")
    private String fotoComida;
    @Basic(optional = false)
    @Column(name = "receta")
    private String receta;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "tipoComida")
    private String tipoComida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codComida")
    private List<Dieta> dietaList;

    public Comida() {
    }

    public Comida(Integer codComida) {
        this.codComida = codComida;
    }

    public Comida(Integer codComida, String nombreComida, String fotoComida, String receta, int cantidad, String tipoComida) {
        this.codComida = codComida;
        this.nombreComida = nombreComida;
        this.fotoComida = fotoComida;
        this.receta = receta;
        this.cantidad = cantidad;
        this.tipoComida = tipoComida;
    }

    public Integer getCodComida() {
        return codComida;
    }

    public void setCodComida(Integer codComida) {
        this.codComida = codComida;
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

    public void setReceta(String receta) {
        this.receta = receta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }

    @XmlTransient
    public List<Dieta> getDietaList() {
        return dietaList;
    }

    public void setDietaList(List<Dieta> dietaList) {
        this.dietaList = dietaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codComida != null ? codComida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comida)) {
            return false;
        }
        Comida other = (Comida) object;
        if ((this.codComida == null && other.codComida != null) || (this.codComida != null && !this.codComida.equals(other.codComida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Comida[ codComida=" + codComida + " ]";
    }
    
}
