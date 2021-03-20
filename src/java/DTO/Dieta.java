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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Salvi
 */
@Entity
@Table(name = "dieta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dieta.findAll", query = "SELECT d FROM Dieta d")
    , @NamedQuery(name = "Dieta.findByCodDieta", query = "SELECT d FROM Dieta d WHERE d.codDieta = :codDieta")
    , @NamedQuery(name = "Dieta.findByDia", query = "SELECT d FROM Dieta d WHERE d.dia = :dia")})
public class Dieta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codDieta")
    private Integer codDieta;
    @Basic(optional = false)
    @Column(name = "dia")
    private String dia;
    @JoinColumn(name = "codComida", referencedColumnName = "codComida")
    @ManyToOne(optional = false)
    private Comida codComida;
    @JoinColumn(name = "nombreUsuario", referencedColumnName = "nombreUsuario")
    @ManyToOne(optional = false)
    private Cliente nombreUsuario;

    public Dieta() {
    }

    public Dieta(Integer codDieta) {
        this.codDieta = codDieta;
    }

    public Dieta(Integer codDieta, String dia) {
        this.codDieta = codDieta;
        this.dia = dia;
    }

    public Integer getCodDieta() {
        return codDieta;
    }

    public void setCodDieta(Integer codDieta) {
        this.codDieta = codDieta;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Comida getCodComida() {
        return codComida;
    }

    public void setCodComida(Comida codComida) {
        this.codComida = codComida;
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
        hash += (codDieta != null ? codDieta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dieta)) {
            return false;
        }
        Dieta other = (Dieta) object;
        if ((this.codDieta == null && other.codDieta != null) || (this.codDieta != null && !this.codDieta.equals(other.codDieta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Dieta[ codDieta=" + codDieta + " ]";
    }
    
}
