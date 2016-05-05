package MODELO;

import MODELO.Propiedad;
import MODELO.Totpropempleado;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-04T00:47:05")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile CollectionAttribute<Empleado, Propiedad> propiedadCollection;
    public static volatile SingularAttribute<Empleado, Date> fechnac;
    public static volatile SingularAttribute<Empleado, String> numempleado;
    public static volatile SingularAttribute<Empleado, String> apellido;
    public static volatile SingularAttribute<Empleado, BigInteger> salario;
    public static volatile SingularAttribute<Empleado, BigInteger> numoficina;
    public static volatile SingularAttribute<Empleado, Totpropempleado> totpropempleado;
    public static volatile SingularAttribute<Empleado, String> cargo;
    public static volatile SingularAttribute<Empleado, String> sexo;
    public static volatile SingularAttribute<Empleado, String> nombre;

}