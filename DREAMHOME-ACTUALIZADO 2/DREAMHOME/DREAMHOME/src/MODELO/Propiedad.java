/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name = "PROPIEDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Propiedad.findAll", query = "SELECT p FROM Propiedad p"),
    @NamedQuery(name = "Propiedad.findByNumpropiedad", query = "SELECT p FROM Propiedad p WHERE p.numpropiedad = :numpropiedad"),
    @NamedQuery(name = "Propiedad.findByCalle", query = "SELECT p FROM Propiedad p WHERE p.calle = :calle"),
    @NamedQuery(name = "Propiedad.findByCiudad", query = "SELECT p FROM Propiedad p WHERE p.ciudad = :ciudad"),
    @NamedQuery(name = "Propiedad.findByCodigopostal", query = "SELECT p FROM Propiedad p WHERE p.codigopostal = :codigopostal"),
    @NamedQuery(name = "Propiedad.findByTipo", query = "SELECT p FROM Propiedad p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "Propiedad.findByHab", query = "SELECT p FROM Propiedad p WHERE p.hab = :hab"),
    @NamedQuery(name = "Propiedad.findByRenta", query = "SELECT p FROM Propiedad p WHERE p.renta = :renta"),
    @NamedQuery(name = "Propiedad.findByNumpropietario", query = "SELECT p FROM Propiedad p WHERE p.numpropietario = :numpropietario")})
public class Propiedad implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NUMPROPIEDAD")
    private String numpropiedad;
    @Column(name = "CALLE")
    private String calle;
    @Column(name = "CIUDAD")
    private String ciudad;
    @Column(name = "CODIGOPOSTAL")
    private String codigopostal;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "HAB")
    private BigInteger hab;
    @Column(name = "RENTA")
    private BigInteger renta;
    @Basic(optional = false)
    @Column(name = "NUMPROPIETARIO")
    private String numpropietario;
    @JoinColumn(name = "NUMEMPLEADO", referencedColumnName = "NUMEMPLEADO")
    @ManyToOne
    private Empleado numempleado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numpropiedad")
    private List<Visita> visitaList;

    public Propiedad() {
    }

    public Propiedad(String numpropiedad) {
        this.numpropiedad = numpropiedad;
    }

    public Propiedad(String numpropiedad, String numpropietario) {
        this.numpropiedad = numpropiedad;
        this.numpropietario = numpropietario;
    }

    public String getNumpropiedad() {
        return numpropiedad;
    }

    public void setNumpropiedad(String numpropiedad) {
        String oldNumpropiedad = this.numpropiedad;
        this.numpropiedad = numpropiedad;
        changeSupport.firePropertyChange("numpropiedad", oldNumpropiedad, numpropiedad);
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        String oldCalle = this.calle;
        this.calle = calle;
        changeSupport.firePropertyChange("calle", oldCalle, calle);
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        String oldCiudad = this.ciudad;
        this.ciudad = ciudad;
        changeSupport.firePropertyChange("ciudad", oldCiudad, ciudad);
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(String codigopostal) {
        String oldCodigopostal = this.codigopostal;
        this.codigopostal = codigopostal;
        changeSupport.firePropertyChange("codigopostal", oldCodigopostal, codigopostal);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        String oldTipo = this.tipo;
        this.tipo = tipo;
        changeSupport.firePropertyChange("tipo", oldTipo, tipo);
    }

    public BigInteger getHab() {
        return hab;
    }

    public void setHab(BigInteger hab) {
        BigInteger oldHab = this.hab;
        this.hab = hab;
        changeSupport.firePropertyChange("hab", oldHab, hab);
    }

    public BigInteger getRenta() {
        return renta;
    }

    public void setRenta(BigInteger renta) {
        BigInteger oldRenta = this.renta;
        this.renta = renta;
        changeSupport.firePropertyChange("renta", oldRenta, renta);
    }

    public String getNumpropietario() {
        return numpropietario;
    }

    public void setNumpropietario(String numpropietario) {
        String oldNumpropietario = this.numpropietario;
        this.numpropietario = numpropietario;
        changeSupport.firePropertyChange("numpropietario", oldNumpropietario, numpropietario);
    }

    public Empleado getNumempleado() {
        return numempleado;
    }

    public void setNumempleado(Empleado numempleado) {
        Empleado oldNumempleado = this.numempleado;
        this.numempleado = numempleado;
        changeSupport.firePropertyChange("numempleado", oldNumempleado, numempleado);
    }

    @XmlTransient
    public List<Visita> getVisitaList() {
        return visitaList;
    }

    public void setVisitaList(List<Visita> visitaList) {
        this.visitaList = visitaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numpropiedad != null ? numpropiedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Propiedad)) {
            return false;
        }
        Propiedad other = (Propiedad) object;
        if ((this.numpropiedad == null && other.numpropiedad != null) || (this.numpropiedad != null && !this.numpropiedad.equals(other.numpropiedad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODELO.Propiedad[ numpropiedad=" + numpropiedad + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
