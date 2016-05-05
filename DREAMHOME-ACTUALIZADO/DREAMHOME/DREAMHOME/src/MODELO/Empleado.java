/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cetecom
 */
@Entity
@Table(name = "EMPLEADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByNumempleado", query = "SELECT e FROM Empleado e WHERE e.numempleado = :numempleado"),
    @NamedQuery(name = "Empleado.findByNombre", query = "SELECT e FROM Empleado e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Empleado.findByApellido", query = "SELECT e FROM Empleado e WHERE e.apellido = :apellido"),
    @NamedQuery(name = "Empleado.findByCargo", query = "SELECT e FROM Empleado e WHERE e.cargo = :cargo"),
    @NamedQuery(name = "Empleado.findBySexo", query = "SELECT e FROM Empleado e WHERE e.sexo = :sexo"),
    @NamedQuery(name = "Empleado.findByFechnac", query = "SELECT e FROM Empleado e WHERE e.fechnac = :fechnac"),
    @NamedQuery(name = "Empleado.findBySalario", query = "SELECT e FROM Empleado e WHERE e.salario = :salario"),
    @NamedQuery(name = "Empleado.findByNumoficina", query = "SELECT e FROM Empleado e WHERE e.numoficina = :numoficina")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NUMEMPLEADO")
    private String numempleado;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "CARGO")
    private String cargo;
    @Column(name = "SEXO")
    private String sexo;
    @Column(name = "FECHNAC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechnac;
    @Column(name = "SALARIO")
    private BigInteger salario;
    @Column(name = "NUMOFICINA")
    private BigInteger numoficina;
    @OneToMany(mappedBy = "numempleado")
    private Collection<Propiedad> propiedadCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "empleado")
    private Totpropempleado totpropempleado;

    public Empleado() {
    }

    public Empleado(String numempleado) {
        this.numempleado = numempleado;
    }

    public String getNumempleado() {
        return numempleado;
    }

    public void setNumempleado(String numempleado) {
        this.numempleado = numempleado;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechnac() {
        return fechnac;
    }

    public void setFechnac(Date fechnac) {
        this.fechnac = fechnac;
    }

    public BigInteger getSalario() {
        return salario;
    }

    public void setSalario(BigInteger salario) {
        this.salario = salario;
    }

    public BigInteger getNumoficina() {
        return numoficina;
    }

    public void setNumoficina(BigInteger numoficina) {
        this.numoficina = numoficina;
    }

    @XmlTransient
    public Collection<Propiedad> getPropiedadCollection() {
        return propiedadCollection;
    }

    public void setPropiedadCollection(Collection<Propiedad> propiedadCollection) {
        this.propiedadCollection = propiedadCollection;
    }

    public Totpropempleado getTotpropempleado() {
        return totpropempleado;
    }

    public void setTotpropempleado(Totpropempleado totpropempleado) {
        this.totpropempleado = totpropempleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numempleado != null ? numempleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.numempleado == null && other.numempleado != null) || (this.numempleado != null && !this.numempleado.equals(other.numempleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODELO.Empleado[ numempleado=" + numempleado + " ]";
    }
    
}
