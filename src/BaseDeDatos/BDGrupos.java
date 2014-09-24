package BaseDeDatos;

import Entidades.Grupo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BDGrupos {
     //método que recibe objeto usuario y lo inserta en la base de datos
    public static boolean insertar(Grupo grupo) throws SQLException{
        boolean insertar=true;
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_insertar= null;
        sentencia_insertar = conexion.prepareStatement("insert into grupos (codigo, descripcion) VALUES (?,?)");
        sentencia_insertar.setInt(1, grupo.getCodigo());
        sentencia_insertar.setString(2, grupo.getDescripcion());
        try {
            sentencia_insertar.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BDPerfiles.class.getName()).log(Level.SEVERE, null, ex);
            insertar= false;
        }
        conexion.close();
        sentencia_insertar.close();
        return insertar;
    }
    
    //metodo que recibe le identificador de la usuario a ser eliminado
    public static boolean eliminar(int codigo) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_eliminar = null;
        sentencia_eliminar = conexion.prepareStatement("delete from grupos where codigo=?");
        sentencia_eliminar.setInt(1, codigo);
        int rowsUpdated = sentencia_eliminar.executeUpdate();
        conexion.close();
        sentencia_eliminar.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }
    
     //método que recibe objeto usuario y actualiza datos en base de datos
    public static boolean actualizar(Grupo grupo) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_actualizar = null;

        sentencia_actualizar = conexion.prepareStatement("update grupos set descripcion=? where codigo=" + grupo.getCodigo());
        sentencia_actualizar.setString(1, grupo.getDescripcion());
        int rowsUpdated = sentencia_actualizar.executeUpdate();
           conexion.close();
           sentencia_actualizar.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    
    //método que busca usuario por codigo
    public static Grupo buscarId(int codigo) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        if(conexion != null)
        {
           PreparedStatement sentencia_buscar = null;
           sentencia_buscar = conexion.prepareStatement("select * from grupos where codigo=?");
           sentencia_buscar.setInt(1, codigo);
           Grupo grupo=null;
           ResultSet resultado = sentencia_buscar.executeQuery();
            if (resultado.next()) {
                if (grupo == null) {
                    grupo = new Grupo() {
                    };
                }
                grupo.setCodigo(codigo);
                grupo.setDescripcion(resultado.getString("descripcion"));
            }
            conexion.close();
            sentencia_buscar.close();
            return grupo; 
        }
        return null;
        
    }
    
    
    //método que devuelve una lista de todas los usuarios 
    public static ArrayList<Grupo> Lista() throws SQLException {
            Connection conexion = Conexion_BD.getConnection();
            PreparedStatement sentencia_mostrar = null;
            ArrayList<Grupo> lista = new ArrayList<Grupo>();

            sentencia_mostrar = conexion.prepareStatement("select * from grupos order by codigo");
            ResultSet resultado = sentencia_mostrar.executeQuery();
            while (resultado.next()) {
                Grupo grupo = new Grupo() {
                };
                grupo.setCodigo(resultado.getInt("codigo"));
                grupo.setDescripcion(resultado.getString("descripcion"));
                lista.add(grupo);
            }
            conexion.close();
            sentencia_mostrar.close();
            return lista;
        }
    
}
