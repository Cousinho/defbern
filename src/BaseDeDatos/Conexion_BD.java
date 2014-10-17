package BaseDeDatos;

import Pantallas.D_Conexion;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion_BD {
    private static FileInputStream inputStream = null;
    private static String driver = "";
    private static String url = "";
    private static String usuario = "";
    private static String contrasena ="";
    private static String conexiondatos;
    private static Connection conexion = null;
        

    //método que devuelve una conexion a la base de datos
    public static  Connection getConnection() {
        //Connection conexion = null;
        try {
            inputStream =  new FileInputStream("config/Config.properties");
            //Ahora inicializamos el properties:
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conexion_BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            inputStream.close();
            //Si el archivo de propiedades NO está vacio retorna las propiedades leidas
            if(!properties.isEmpty()){
            //Y ahora si queremos los valores del properties:
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            usuario = properties.getProperty("usuario");
            contrasena = properties.getProperty("contrasena");
            }else{
                System.out.println("El archivo se encuentra vacio...");
            }
        } catch (IOException ex) {
            Logger.getLogger(Conexion_BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //datos de la conexion
        try {
            Class.forName(driver);
            conexion= DriverManager.getConnection(url, usuario, contrasena);
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



