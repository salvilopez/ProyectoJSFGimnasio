package DTO;

import DTO.Cliente;
import DTO.Sala;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-31T11:11:18")
@StaticMetamodel(Reserva.class)
public class Reserva_ { 

    public static volatile SingularAttribute<Reserva, Sala> codSala;
    public static volatile SingularAttribute<Reserva, Date> fecha;
    public static volatile SingularAttribute<Reserva, Integer> hora;
    public static volatile SingularAttribute<Reserva, Cliente> nombreUsuario;
    public static volatile SingularAttribute<Reserva, Integer> codCita;

}