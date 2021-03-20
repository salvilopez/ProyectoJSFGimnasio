package DTO;

import DTO.Cliente;
import DTO.Comida;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-31T11:11:18")
@StaticMetamodel(Dieta.class)
public class Dieta_ { 

    public static volatile SingularAttribute<Dieta, Comida> codComida;
    public static volatile SingularAttribute<Dieta, Integer> codDieta;
    public static volatile SingularAttribute<Dieta, Cliente> nombreUsuario;
    public static volatile SingularAttribute<Dieta, String> dia;

}