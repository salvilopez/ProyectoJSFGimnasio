/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Salvi
 */
@Entity
@Table(name = "tarifa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarifa.findAll", query = "SELECT t FROM Tarifa t")
    , @NamedQuery(name = "Tarifa.findByCodTarifa", query = "SELECT t FROM Tarifa t WHERE t.codTarifa = :codTarifa")
    , @NamedQuery(name = "Tarifa.findByMeses", query = "SELECT t FROM Tarifa t WHERE t.meses = :meses")
    , @NamedQuery(name = "Tarifa.findByNombreTarifa", query = "SELECT t FROM Tarifa t WHERE t.nombreTarifa = :nombreTarifa")
    , @NamedQuery(name = "Tarifa.findByPrecio", query = "SELECT t FROM Tarifa t WHERE t.precio = :precio")})
public class Tarifa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codTarifa")
    private Integer codTarifa;
    @Basic(optional = false)
    @Column(name = "meses")
    private int meses;
    @Basic(optional = false)
    @Column(name = "nombreTarifa")
    private String nombreTarifa;
    @Basic(optional = false)
    @Column(name = "precio")
    private long precio;

    public Tarifa() {
    }

    public Tarifa(Integer codTarifa) {
        this.codTarifa = codTarifa;
    }

    public Tarifa(Integer codTarifa, int meses, String nombreTarifa, long precio) {
        this.codTarifa = codTarifa;
        this.meses = meses;
        this.nombreTarifa = nombreTarifa;
        this.precio = precio;
    }

    public Integer getCodTarifa() {
        return codTarifa;
    }

    public void setCodTarifa(Integer codTarifa) {
        this.codTarifa = codTarifa;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    public String getNombreTarifa() {
        return nombreTarifa;
    }

    public void setNombreTarifa(String nombreTarifa) {
        this.nombreTarifa = nombreTarifa;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTarifa != null ? codTarifa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarifa)) {
            return false;
        }
        Tarifa other = (Tarifa) object;
        if ((this.codTarifa == null && other.codTarifa != null) || (this.codTarifa != null && !this.codTarifa.equals(other.codTarifa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Tarifa[ codTarifa=" + codTarifa + " ]";
    }
    
}
