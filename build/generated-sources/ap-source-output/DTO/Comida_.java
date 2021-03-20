package DTO;

import DTO.Dieta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-31T11:11:17")
@StaticMetamodel(Comida.class)
public class Comida_ { 

    public static volatile SingularAttribute<Comida, Integer> codComida;
    public static volatile ListAttribute<Comida, Dieta> dietaList;
    public static volatile SingularAttribute<Comida, String> receta;
    public static volatile SingularAttribute<Comida, String> fotoComida;
    public static volatile SingularAttribute<Comida, String> nombreComida;
    public static volatile SingularAttribute<Comida, Integer> cantidad;
    public static volatile SingularAttribute<Comida, String> tipoComida;

}