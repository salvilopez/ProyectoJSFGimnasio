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
@Table(name = "ejercicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ejercicio.findAll", query = "SELECT e FROM Ejercicio e")
    , @NamedQuery(name = "Ejercicio.findByCodEjercicio", query = "SELECT e FROM Ejercicio e WHERE e.codEjercicio = :codEjercicio")
    , @NamedQuery(name = "Ejercicio.findByNombreEjercicio", query = "SELECT e FROM Ejercicio e WHERE e.nombreEjercicio = :nombreEjercicio")
    , @NamedQuery(name = "Ejercicio.findByVideo", query = "SELECT e FROM Ejercicio e WHERE e.video = :video")
    , @NamedQuery(name = "Ejercicio.findByFoto", query = "SELECT e FROM Ejercicio e WHERE e.foto = :foto")
    , @NamedQuery(name = "Ejercicio.findByGrupo", query = "SELECT e FROM Ejercicio e WHERE e.grupo = :grupo")})
public class Ejercicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codEjercicio")
    private Integer codEjercicio;
    @Basic(optional = false)
    @Column(name = "nombreEjercicio")
    private String nombreEjercicio;
    @Basic(optional = false)
    @Column(name = "video")
    private String video;
    @Basic(optional = false)
    @Column(name = "foto")
    private String foto;
    @Basic(optional = false)
    @Column(name = "grupo")
    private String grupo;

    public Ejercicio() {
    }

    public Ejercicio(Integer codEjercicio) {
        this.codEjercicio = codEjercicio;
    }

    public Ejercicio(Integer codEjercicio, String nombreEjercicio, String video, String foto, String grupo) {
        this.codEjercicio = codEjercicio;
        this.nombreEjercicio = nombreEjercicio;
        this.video = video;
        this.foto = foto;
        this.grupo = grupo;
    }

    public Integer getCodEjercicio() {
        return codEjercicio;
    }

    public void setCodEjercicio(Integer codEjercicio) {
        this.codEjercicio = codEjercicio;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEjercicio != null ? codEjercicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ejercicio)) {
            return false;
        }
        Ejercicio other = (Ejercicio) object;
        if ((this.codEjercicio == null && other.codEjercicio != null) || (this.codEjercicio != null && !this.codEjercicio.equals(other.codEjercicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Ejercicio[ codEjercicio=" + codEjercicio + " ]";
    }
    
}
