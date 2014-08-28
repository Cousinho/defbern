package BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public abstract class Conexion_BD {
    //método que devuelve una conexion a la base de datos
    public static Connection getConnection() {
        Connection conexion = null;
        //datos de la conexion
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/defbern";
            String user = "postgres";
            String password = "123";
            conexion= DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            conexion=null;
            System.out.println("Error no se puede cargar el driver:" + e.getMessage());
        } catch (SQLException e) {
            conexion=null;
            if(e.getErrorCode() == 0)
            {
                JOptionPane.showMessageDialog(null, "El servidor de base de datos no responde.\n"
                                                    + "Verifique que el servidor de base de datos este activo.\n"
                                                    + "También Verifique que exista la base de datos", 
                                                    "Error: Coneccion Fallida.", JOptionPane.ERROR_MESSAGE);
            }
        }
        return conexion;
    }
}
