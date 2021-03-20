package DTO;

import DTO.Cliente;
import DTO.Ejercicio;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-31T11:11:18")
@StaticMetamodel(Rutina.class)
public class Rutina_ { 

    public static volatile SingularAttribute<Rutina, Integer> codEjercicio;
    public static volatile SingularAttribute<Rutina, Integer> repeticiones;
    public static volatile SingularAttribute<Rutina, Cliente> nombreUsuario;
    public static volatile SingularAttribute<Rutina, String> dia;
    public static volatile SingularAttribute<Rutina, Integer> codRutina;
    public static volatile SingularAttribute<Rutina, Ejercicio> ejercicio;

}