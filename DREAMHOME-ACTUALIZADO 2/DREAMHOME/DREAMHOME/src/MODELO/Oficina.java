/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name = "OFICINA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oficina.findAll", query = "SELECT o FROM Oficina o"),
    @NamedQuery(name = "Oficina.findByNumoficina", query = "SELECT o FROM Oficina o WHERE o.numoficina = :numoficina"),
    @NamedQuery(name = "Oficina.findByCalle", query = "SELECT o FROM Oficina o WHERE o.calle = :calle"),
    @NamedQuery(name = "Oficina.findByCiudad", query = "SELECT o FROM Oficina o WHERE o.ciudad = :ciudad"),
    @NamedQuery(name = "Oficina.findByCodigopostal", query = "SELECT o FROM Oficina o WHERE o.codigopostal = :codigopostal")})
public class Oficina implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "NUMOFICINA")
    private BigDecimal numoficina;
    @Column(name = "CALLE")
    private String calle;
    @Column(name = "CIUDAD")
    private String ciudad;
    @Column(name = "CODIGOPOSTAL")
    private String codigopostal;

    public Oficina() {
    }

    public Oficina(BigDecimal numoficina) {
        this.numoficina = numoficina;
    }

    public BigDecimal getNumoficina() {
        return numoficina;
    }

    public void setNumoficina(BigDecimal numoficina) {
        this.numoficina = numoficina;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numoficina != null ? numoficina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oficina)) {
            return false;
        }
        Oficina other = (Oficina) object;
        if ((this.numoficina == null && other.numoficina != null) || (this.numoficina != null && !this.numoficina.equals(other.numoficina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODELO.Oficina[ numoficina=" + numoficina + " ]";
    }
    
}
