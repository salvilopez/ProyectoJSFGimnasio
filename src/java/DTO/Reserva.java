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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "reserva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r")
    , @NamedQuery(name = "Reserva.findByCodCita", query = "SELECT r FROM Reserva r WHERE r.codCita = :codCita")
    , @NamedQuery(name = "Reserva.findByFecha", query = "SELECT r FROM Reserva r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "Reserva.findByHora", query = "SELECT r FROM Reserva r WHERE r.hora = :hora")})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codCita")
    private Integer codCita;
    @Basic(optional = false)
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "hora")
    private int hora;
    @JoinColumn(name = "codSala", referencedColumnName = "codigoSala")
    @ManyToOne(optional = false)
    private Sala codSala;
    @JoinColumn(name = "nombreUsuario", referencedColumnName = "nombreUsuario")
    @ManyToOne(optional = false)
    private Cliente nombreUsuario;

    public Reserva() {
    }

    public Reserva(Integer codCita) {
        this.codCita = codCita;
    }

    public Reserva(Integer codCita, Date fecha, int hora) {
        this.codCita = codCita;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Integer getCodCita() {
        return codCita;
    }

    public void setCodCita(Integer codCita) {
        this.codCita = codCita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public Sala getCodSala() {
        return codSala;
    }

    public void setCodSala(Sala codSala) {
        this.codSala = codSala;
    }

    public Cliente getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(Cliente nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCita != null ? codCita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.codCita == null && other.codCita != null) || (this.codCita != null && !this.codCita.equals(other.codCita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Reserva[ codCita=" + codCita + " ]";
    }
    
}
