package bd;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    
  public static Connection conectar() {

        //Paso 1: Cargar el driver JDBC.
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            // Paso 2: Conectarse a la Base de Datos utilizando la clase Connection
            String userName = "servidores";
            String password = "servidores";

            //URL de la base de datos(equipo, puerto, base de datos)
            String url = "jdbc:mysql://localhost/gimnasio";

            //String url="jdbc:mysql://192.168.9.247/peliculas";
            Connection c = (Connection) DriverManager.getConnection(url, userName, password);
            return c;
        } catch (Exception e) {
            return null;
        }

    }
}