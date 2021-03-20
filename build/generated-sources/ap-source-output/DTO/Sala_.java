package DTO;

import DTO.Reserva;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-31T11:11:18")
@StaticMetamodel(Sala.class)
public class Sala_ { 

    public static volatile SingularAttribute<Sala, String> descripcion;
    public static volatile SingularAttribute<Sala, Integer> codigoSala;
    public static volatile ListAttribute<Sala, Reserva> reservaList;
    public static volatile SingularAttribute<Sala, String> imagen;
    public static volatile SingularAttribute<Sala, String> nombre;
    public static volatile SingularAttribute<Sala, Integer> capacidad;

}