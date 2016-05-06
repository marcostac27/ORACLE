package MODELO;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-06T09:18:32")
@StaticMetamodel(Arriendo.class)
public class Arriendo_ { 

    public static volatile SingularAttribute<Arriendo, BigDecimal> numarriendo;
    public static volatile SingularAttribute<Arriendo, String> pagado;
    public static volatile SingularAttribute<Arriendo, Date> iniciorenta;
    public static volatile SingularAttribute<Arriendo, BigInteger> numcliente;
    public static volatile SingularAttribute<Arriendo, BigInteger> deposito;
    public static volatile SingularAttribute<Arriendo, Date> finrenta;
    public static volatile SingularAttribute<Arriendo, String> numpropiedad;
    public static volatile SingularAttribute<Arriendo, BigInteger> renta;
    public static volatile SingularAttribute<Arriendo, String> formapago;

}