/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name = "ARRIENDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arriendo.findAll", query = "SELECT a FROM Arriendo a"),
    @NamedQuery(name = "Arriendo.findByNumarriendo", query = "SELECT a FROM Arriendo a WHERE a.numarriendo = :numarriendo"),
    @NamedQuery(name = "Arriendo.findByNumpropiedad", query = "SELECT a FROM Arriendo a WHERE a.numpropiedad = :numpropiedad"),
    @NamedQuery(name = "Arriendo.findByNumcliente", query = "SELECT a FROM Arriendo a WHERE a.numcliente = :numcliente"),
    @NamedQuery(name = "Arriendo.findByRenta", query = "SELECT a FROM Arriendo a WHERE a.renta = :renta"),
    @NamedQuery(name = "Arriendo.findByFormapago", query = "SELECT a FROM Arriendo a WHERE a.formapago = :formapago"),
    @NamedQuery(name = "Arriendo.findByDeposito", query = "SELECT a FROM Arriendo a WHERE a.deposito = :deposito"),
    @NamedQuery(name = "Arriendo.findByPagado", query = "SELECT a FROM Arriendo a WHERE a.pagado = :pagado"),
    @NamedQuery(name = "Arriendo.findByIniciorenta", query = "SELECT a FROM Arriendo a WHERE a.iniciorenta = :iniciorenta"),
    @NamedQuery(name = "Arriendo.findByFinrenta", query = "SELECT a FROM Arriendo a WHERE a.finrenta = :finrenta")})
public class Arriendo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "NUMARRIENDO")
    private BigDecimal numarriendo;
    @Basic(optional = false)
    @Column(name = "NUMPROPIEDAD")
    private String numpropiedad;
    @Basic(optional = false)
    @Column(name = "NUMCLIENTE")
    private BigInteger numcliente;
    @Column(name = "RENTA")
    private BigInteger renta;
    @Column(name = "FORMAPAGO")
    private String formapago;
    @Column(name = "DEPOSITO")
    private BigInteger deposito;
    @Column(name = "PAGADO")
    private String pagado;
    @Column(name = "INICIORENTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date iniciorenta;
    @Column(name = "FINRENTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finrenta;

    public Arriendo() {
    }

    public Arriendo(BigDecimal numarriendo) {
        this.numarriendo = numarriendo;
    }

    public Arriendo(BigDecimal numarriendo, String numpropiedad, BigInteger numcliente) {
        this.numarriendo = numarriendo;
        this.numpropiedad = numpropiedad;
        this.numcliente = numcliente;
    }

    public BigDecimal getNumarriendo() {
        return numarriendo;
    }

    public void setNumarriendo(BigDecimal numarriendo) {
        this.numarriendo = numarriendo;
    }

    public String getNumpropiedad() {
        return numpropiedad;
    }

    public void setNumpropiedad(String numpropiedad) {
        this.numpropiedad = numpropiedad;
    }

    public BigInteger getNumcliente() {
        return numcliente;
    }

    public void setNumcliente(BigInteger numcliente) {
        this.numcliente = numcliente;
    }

    public BigInteger getRenta() {
        return renta;
    }

    public void setRenta(BigInteger renta) {
        this.renta = renta;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }

    public BigInteger getDeposito() {
        return deposito;
    }

    public void setDeposito(BigInteger deposito) {
        this.deposito = deposito;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public Date getIniciorenta() {
        return iniciorenta;
    }

    public void setIniciorenta(Date iniciorenta) {
        this.iniciorenta = iniciorenta;
    }

    public Date getFinrenta() {
        return finrenta;
    }

    public void setFinrenta(Date finrenta) {
        this.finrenta = finrenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numarriendo != null ? numarriendo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arriendo)) {
            return false;
        }
        Arriendo other = (Arriendo) object;
        if ((this.numarriendo == null && other.numarriendo != null) || (this.numarriendo != null && !this.numarriendo.equals(other.numarriendo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODELO.Arriendo[ numarriendo=" + numarriendo + " ]";
    }
    
}
