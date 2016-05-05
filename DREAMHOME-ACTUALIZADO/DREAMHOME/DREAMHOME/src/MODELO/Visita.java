/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author cetecom
 */
@Entity
@Table(name = "VISITA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Visita.findAll", query = "SELECT v FROM Visita v"),
    @NamedQuery(name = "Visita.findByNumvisita", query = "SELECT v FROM Visita v WHERE v.numvisita = :numvisita"),
    @NamedQuery(name = "Visita.findByFecha", query = "SELECT v FROM Visita v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "Visita.findByComentario", query = "SELECT v FROM Visita v WHERE v.comentario = :comentario")})
public class Visita implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "NUMVISITA")
    private BigDecimal numvisita;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "COMENTARIO")
    private String comentario;
    @JoinColumn(name = "NUMCLIENTE", referencedColumnName = "NUMCLIENTE")
    @ManyToOne(optional = false)
    private Cliente numcliente;
    @JoinColumn(name = "NUMPROPIEDAD", referencedColumnName = "NUMPROPIEDAD")
    @ManyToOne(optional = false)
    private Propiedad numpropiedad;

    public Visita() {
    }

    public Visita(BigDecimal numvisita) {
        this.numvisita = numvisita;
    }

    public Visita(BigDecimal numvisita, Date fecha) {
        this.numvisita = numvisita;
        this.fecha = fecha;
    }

    public BigDecimal getNumvisita() {
        return numvisita;
    }

    public void setNumvisita(BigDecimal numvisita) {
        this.numvisita = numvisita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Cliente getNumcliente() {
        return numcliente;
    }

    public void setNumcliente(Cliente numcliente) {
        this.numcliente = numcliente;
    }

    public Propiedad getNumpropiedad() {
        return numpropiedad;
    }

    public void setNumpropiedad(Propiedad numpropiedad) {
        this.numpropiedad = numpropiedad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numvisita != null ? numvisita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visita)) {
            return false;
        }
        Visita other = (Visita) object;
        if ((this.numvisita == null && other.numvisita != null) || (this.numvisita != null && !this.numvisita.equals(other.numvisita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODELO.Visita[ numvisita=" + numvisita + " ]";
    }
    
}
