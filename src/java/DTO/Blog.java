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
@Table(name = "blog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Blog.findAll", query = "SELECT b FROM Blog b")
    , @NamedQuery(name = "Blog.findByCodNoticia", query = "SELECT b FROM Blog b WHERE b.codNoticia = :codNoticia")
    , @NamedQuery(name = "Blog.findByTituloNoticia", query = "SELECT b FROM Blog b WHERE b.tituloNoticia = :tituloNoticia")
    , @NamedQuery(name = "Blog.findByDescripcion", query = "SELECT b FROM Blog b WHERE b.descripcion = :descripcion")
    , @NamedQuery(name = "Blog.findByVideo", query = "SELECT b FROM Blog b WHERE b.video = :video")})
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codNoticia")
    private Integer codNoticia;
    @Basic(optional = false)
    @Column(name = "tituloNoticia")
    private String tituloNoticia;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "video")
    private String video;

    public Blog() {
    }

    public Blog(Integer codNoticia) {
        this.codNoticia = codNoticia;
    }

    public Blog(Integer codNoticia, String tituloNoticia, String descripcion, String video) {
        this.codNoticia = codNoticia;
        this.tituloNoticia = tituloNoticia;
        this.descripcion = descripcion;
        this.video = video;
    }

    public Integer getCodNoticia() {
        return codNoticia;
    }

    public void setCodNoticia(Integer codNoticia) {
        this.codNoticia = codNoticia;
    }

    public String getTituloNoticia() {
        return tituloNoticia;
    }

    public void setTituloNoticia(String tituloNoticia) {
        this.tituloNoticia = tituloNoticia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codNoticia != null ? codNoticia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Blog)) {
            return false;
        }
        Blog other = (Blog) object;
        if ((this.codNoticia == null && other.codNoticia != null) || (this.codNoticia != null && !this.codNoticia.equals(other.codNoticia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Blog[ codNoticia=" + codNoticia + " ]";
    }
    
}
