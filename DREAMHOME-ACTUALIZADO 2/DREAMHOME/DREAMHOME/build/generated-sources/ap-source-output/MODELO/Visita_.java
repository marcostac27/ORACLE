package MODELO;

import MODELO.Cliente;
import MODELO.Propiedad;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-06T09:18:32")
@StaticMetamodel(Visita.class)
public class Visita_ { 

    public static volatile SingularAttribute<Visita, Date> fecha;
    public static volatile SingularAttribute<Visita, Cliente> numcliente;
    public static volatile SingularAttribute<Visita, String> comentario;
    public static volatile SingularAttribute<Visita, BigDecimal> numvisita;
    public static volatile SingularAttribute<Visita, Propiedad> numpropiedad;

}