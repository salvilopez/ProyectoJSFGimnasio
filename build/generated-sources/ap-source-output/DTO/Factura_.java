package DTO;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-31T11:11:17")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, Float> precio;
    public static volatile SingularAttribute<Factura, Integer> meses;
    public static volatile SingularAttribute<Factura, String> nombreUsuario;
    public static volatile SingularAttribute<Factura, Integer> codFactura;
    public static volatile SingularAttribute<Factura, Integer> codTarifa;
    public static volatile SingularAttribute<Factura, Date> fechaPago;

}