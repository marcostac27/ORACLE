package MODELO;

import MODELO.Visita;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-04T00:47:05")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, BigInteger> maxrent;
    public static volatile SingularAttribute<Cliente, String> tipopref;
    public static volatile SingularAttribute<Cliente, BigDecimal> numcliente;
    public static volatile SingularAttribute<Cliente, String> apellido;
    public static volatile SingularAttribute<Cliente, String> direccion;
    public static volatile SingularAttribute<Cliente, String> telefono;
    public static volatile CollectionAttribute<Cliente, Visita> visitaCollection;
    public static volatile SingularAttribute<Cliente, String> nombre;

}