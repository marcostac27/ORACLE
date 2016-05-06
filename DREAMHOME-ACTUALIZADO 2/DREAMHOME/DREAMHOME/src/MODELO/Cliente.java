/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cetecom
 */
@Entity
@Table(name = "CLIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByNumcliente", query = "SELECT c FROM Cliente c WHERE c.numcliente = :numcliente"),
    @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cliente.findByApellido", query = "SELECT c FROM Cliente c WHERE c.apellido = :apellido"),
    @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Cliente.findByTipopref", query = "SELECT c FROM Cliente c WHERE c.tipopref = :tipopref"),
    @NamedQuery(name = "Cliente.findByMaxrent", query = "SELECT c FROM Cliente c WHERE c.maxrent = :maxrent")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "NUMCLIENTE")
    private BigDecimal numcliente;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "TIPOPREF")
    private String tipopref;
    @Column(name = "MAXRENT")
    private BigInteger maxrent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numcliente")
    private Collection<Visita> visitaCollection;

    public Cliente() {
    }

    public Cliente(BigDecimal numcliente) {
        this.numcliente = numcliente;
    }

    public BigDecimal getNumcliente() {
        return numcliente;
    }

    public void setNumcliente(BigDecimal numcliente) {
        this.numcliente = numcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipopref() {
        return tipopref;
    }

    public void setTipopref(String tipopref) {
        this.tipopref = tipopref;
    }

    public BigInteger getMaxrent() {
        return maxrent;
    }

    public void setMaxrent(BigInteger maxrent) {
        this.maxrent = maxrent;
    }

    @XmlTransient
    public Collection<Visita> getVisitaCollection() {
        return visitaCollection;
    }

    public void setVisitaCollection(Collection<Visita> visitaCollection) {
        this.visitaCollection = visitaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numcliente != null ? numcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.numcliente == null && other.numcliente != null) || (this.numcliente != null && !this.numcliente.equals(other.numcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODELO.Cliente[ numcliente=" + numcliente + " ]";
    }
    
}
