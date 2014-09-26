package BaseDeDatos;

import Entidades.Rol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class BDRoles {
    
//método que recibe objeto usuario y lo inserta en la base de datos
    public static boolean insertar(Rol rol) throws SQLException  {
        boolean insertar=true;
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_insertar= null;
        sentencia_insertar = conexion.prepareStatement("insert into roles (codigo, descripcion) VALUES (?,?)");
        sentencia_insertar.setInt(1, rol.getCodigo());
        sentencia_insertar.setString(2, rol.getDescripcion());
        try {
            sentencia_insertar.executeUpdate();
        } catch (SQLException ex) {
            insertar=false;
        }
        conexion.close();
        sentencia_insertar.close();
        return insertar;
    }
    
    //metodo que recibe le identificador de la usuario a ser eliminado
    public static boolean eliminar(int codigo) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_eliminar = null;
        sentencia_eliminar = conexion.prepareStatement("delete from roles where codigo=?");
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
    public static boolean actualizar(Rol rol) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_actualizar = null;

        sentencia_actualizar = conexion.prepareStatement("update roles set descripcion=? where codigo=" + rol.getCodigo());
        sentencia_actualizar.setString(1, rol.getDescripcion());
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
    public static Rol buscarId(int codigo) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        if(conexion != null)
        {
           PreparedStatement sentencia_buscar = null;
           sentencia_buscar = conexion.prepareStatement("select * from roles where codigo=?");
           sentencia_buscar.setInt(1, codigo);
           Rol rol=null;
           ResultSet resultado = sentencia_buscar.executeQuery();
            if (resultado.next()) {
                if (rol == null) {
                    rol = new Rol() {
                    };
                }
                rol.setCodigo(codigo);
                rol.setDescripcion(resultado.getString("descripcion"));
            }
            conexion.close();
            sentencia_buscar.close();
            return rol; 
        }
        return null;
        
    }
    
    public static Rol buscarNombre(String descripcion) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        if(conexion != null)
        {
           PreparedStatement sentencia_buscar = null;
           sentencia_buscar = conexion.prepareStatement("select * from roles where descripcion=?");
           sentencia_buscar.setString(1, descripcion);
           Rol rol=null;
           ResultSet resultado = sentencia_buscar.executeQuery();
            if (resultado.next()) {
                if (rol == null) {
                    rol = new Rol() {
                    };
                }
                rol.setCodigo(resultado.getInt("codigo"));
                rol.setDescripcion(descripcion);
            }
            conexion.close();
            sentencia_buscar.close();
            return rol; 
        }
        return null;
        
    }
    
    
    //método que devuelve una lista de todas los usuarios 
    public static ArrayList<Rol> Lista() throws SQLException {
            Connection conexion = Conexion_BD.getConnection();
            PreparedStatement sentencia_mostrar = null;
            ArrayList<Rol> lista = new ArrayList<Rol>();

            sentencia_mostrar = conexion.prepareStatement("select * from roles");
            ResultSet resultado = sentencia_mostrar.executeQuery();
            while (resultado.next()) {
                Rol rol = new Rol() {
                };
                rol.setCodigo(resultado.getInt("codigo"));
                rol.setDescripcion(resultado.getString("descripcion"));
                lista.add(rol);
            }
            conexion.close();
            sentencia_mostrar.close();
            return lista;
        }
}
