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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Salvi
 */
@Entity
@Table(name = "rutina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rutina.findAll", query = "SELECT r FROM Rutina r")
    , @NamedQuery(name = "Rutina.findByCodEjercicio", query = "SELECT r FROM Rutina r WHERE r.codEjercicio = :codEjercicio")
    , @NamedQuery(name = "Rutina.findByCodRutina", query = "SELECT r FROM Rutina r WHERE r.codRutina = :codRutina")
    , @NamedQuery(name = "Rutina.findByRepeticiones", query = "SELECT r FROM Rutina r WHERE r.repeticiones = :repeticiones")
    , @NamedQuery(name = "Rutina.findByDia", query = "SELECT r FROM Rutina r WHERE r.dia = :dia")})
public class Rutina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "codEjercicio")
    private int codEjercicio;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codRutina")
    private Integer codRutina;
    @Basic(optional = false)
    @Column(name = "repeticiones")
    private int repeticiones;
    @Basic(optional = false)
    @Column(name = "dia")
    private String dia;
    @JoinColumn(name = "codRutina", referencedColumnName = "codEjercicio", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Ejercicio ejercicio;
    @JoinColumn(name = "nombreUsuario", referencedColumnName = "nombreUsuario")
    @ManyToOne(optional = false)
    private Cliente nombreUsuario;

    public Rutina() {
    }

    public Rutina(Integer codRutina) {
        this.codRutina = codRutina;
    }

    public Rutina(Integer codRutina, int codEjercicio, int repeticiones, String dia) {
        this.codRutina = codRutina;
        this.codEjercicio = codEjercicio;
        this.repeticiones = repeticiones;
        this.dia = dia;
    }

    public int getCodEjercicio() {
        return codEjercicio;
    }

    public void setCodEjercicio(int codEjercicio) {
        this.codEjercicio = codEjercicio;
    }

    public Integer getCodRutina() {
        return codRutina;
    }

    public void setCodRutina(Integer codRutina) {
        this.codRutina = codRutina;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
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
        hash += (codRutina != null ? codRutina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rutina)) {
            return false;
        }
        Rutina other = (Rutina) object;
        if ((this.codRutina == null && other.codRutina != null) || (this.codRutina != null && !this.codRutina.equals(other.codRutina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Rutina[ codRutina=" + codRutina + " ]";
    }
    
}
