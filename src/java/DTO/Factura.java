/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Salvi
 */
@Entity
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")
    , @NamedQuery(name = "Factura.findByCodFactura", query = "SELECT f FROM Factura f WHERE f.codFactura = :codFactura")
    , @NamedQuery(name = "Factura.findByFechaPago", query = "SELECT f FROM Factura f WHERE f.fechaPago = :fechaPago")
    , @NamedQuery(name = "Factura.findByMeses", query = "SELECT f FROM Factura f WHERE f.meses = :meses")
    , @NamedQuery(name = "Factura.findByPrecio", query = "SELECT f FROM Factura f WHERE f.precio = :precio")
    , @NamedQuery(name = "Factura.findByCodTarifa", query = "SELECT f FROM Factura f WHERE f.codTarifa = :codTarifa")
    , @NamedQuery(name = "Factura.findByNombreUsuario", query = "SELECT f FROM Factura f WHERE f.nombreUsuario = :nombreUsuario")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codFactura")
    private Integer codFactura;
    @Basic(optional = false)
    @Column(name = "fechaPago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    @Basic(optional = false)
    @Column(name = "meses")
    private int meses;
    @Basic(optional = false)
    @Column(name = "precio")
    private float precio;
    @Basic(optional = false)
    @Column(name = "codTarifa")
    private int codTarifa;
    @Basic(optional = false)
    @Column(name = "nombreUsuario")
    private String nombreUsuario;

    public Factura() {
    }

    public Factura(Integer codFactura) {
        this.codFactura = codFactura;
    }

    public Factura(Integer codFactura, Date fechaPago, int meses, float precio, int codTarifa, String nombreUsuario) {
        this.codFactura = codFactura;
        this.fechaPago = fechaPago;
        this.meses = meses;
        this.precio = precio;
        this.codTarifa = codTarifa;
        this.nombreUsuario = nombreUsuario;
    }

    public Integer getCodFactura() {
        return codFactura;
    }

    public void setCodFactura(Integer codFactura) {
        this.codFactura = codFactura;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCodTarifa() {
        return codTarifa;
    }

    public void setCodTarifa(int codTarifa) {
        this.codTarifa = codTarifa;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codFactura != null ? codFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.codFactura == null && other.codFactura != null) || (this.codFactura != null && !this.codFactura.equals(other.codFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Factura[ codFactura=" + codFactura + " ]";
    }
    
}
