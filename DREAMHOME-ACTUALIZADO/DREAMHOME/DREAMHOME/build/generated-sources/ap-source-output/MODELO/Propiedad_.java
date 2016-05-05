package MODELO;

import MODELO.Empleado;
import MODELO.Visita;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-04T00:47:05")
@StaticMetamodel(Propiedad.class)
public class Propiedad_ { 

    public static volatile SingularAttribute<Propiedad, String> tipo;
    public static volatile SingularAttribute<Propiedad, String> ciudad;
    public static volatile SingularAttribute<Propiedad, String> calle;
    public static volatile SingularAttribute<Propiedad, Empleado> numempleado;
    public static volatile SingularAttribute<Propiedad, BigInteger> hab;
    public static volatile SingularAttribute<Propiedad, String> numpropietario;
    public static volatile CollectionAttribute<Propiedad, Visita> visitaCollection;
    public static volatile SingularAttribute<Propiedad, String> numpropiedad;
    public static volatile SingularAttribute<Propiedad, BigInteger> renta;
    public static volatile SingularAttribute<Propiedad, String> codigopostal;

}