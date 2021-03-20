package DTO;

import DTO.Dieta;
import DTO.Reserva;
import DTO.Rutina;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-31T11:11:17")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> apellidos;
    public static volatile SingularAttribute<Cliente, String> tipo;
    public static volatile SingularAttribute<Cliente, Date> fechaNacimiento;
    public static volatile SingularAttribute<Cliente, Integer> peso;
    public static volatile SingularAttribute<Cliente, String> pass;
    public static volatile SingularAttribute<Cliente, Date> fechaRegistro;
    public static volatile SingularAttribute<Cliente, String> direccion;
    public static volatile ListAttribute<Cliente, Rutina> rutinaList;
    public static volatile SingularAttribute<Cliente, String> observacionesSalud;
    public static volatile SingularAttribute<Cliente, String> nombreUsuario;
    public static volatile SingularAttribute<Cliente, String> nombre;
    public static volatile SingularAttribute<Cliente, String> objetivo;
    public static volatile ListAttribute<Cliente, Dieta> dietaList;
    public static volatile SingularAttribute<Cliente, String> foto;
    public static volatile ListAttribute<Cliente, Reserva> reservaList;
    public static volatile SingularAttribute<Cliente, Integer> telefono;
    public static volatile SingularAttribute<Cliente, Date> fechaValidez;
    public static volatile SingularAttribute<Cliente, String> email;

}