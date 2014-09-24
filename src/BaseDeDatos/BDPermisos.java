package BaseDeDatos;

import Entidades.Permiso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDPermisos {
       
//método que recibe objeto usuario y lo inserta en la base de datos
    public static boolean insertar(Permiso permiso) throws SQLException  {
        boolean insertar=true;
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_insertar= null;
        sentencia_insertar = conexion.prepareStatement("insert into permisos (codigo, descripcion) VALUES (?,?)");
        sentencia_insertar.setInt(1, permiso.getCodigo());
        sentencia_insertar.setString(2, permiso.getDescripcion());
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
        sentencia_eliminar = conexion.prepareStatement("delete from permisos where codigo=?");
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
    public static boolean actualizar(Permiso permiso) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_actualizar = null;

        sentencia_actualizar = conexion.prepareStatement("update permisos set descripcion=? where codigo=" + permiso.getCodigo());
        sentencia_actualizar.setString(1, permiso.getDescripcion());
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
    public static Permiso buscarId(int codigo) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        if(conexion != null)
        {
           PreparedStatement sentencia_buscar = null;
           sentencia_buscar = conexion.prepareStatement("select * from permisos where codigo=?");
           sentencia_buscar.setInt(1, codigo);
           Permiso permiso=null;
           ResultSet resultado = sentencia_buscar.executeQuery();
            if (resultado.next()) {
                if (permiso == null) {
                    permiso = new Permiso() {
                    };
                }
                permiso.setCodigo(codigo);
                permiso.setDescripcion(resultado.getString("descripcion"));
            }
            conexion.close();
            sentencia_buscar.close();
            return permiso; 
        }
        return null;
        
    }
    
    public static Permiso buscarNombre(String descripcion) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        if(conexion != null)
        {
           PreparedStatement sentencia_buscar = null;
           sentencia_buscar = conexion.prepareStatement("select * from permisos where descripcion=?");
           sentencia_buscar.setString(1, descripcion);
           Permiso permiso=null;
           ResultSet resultado = sentencia_buscar.executeQuery();
            if (resultado.next()) {
                if (permiso == null) {
                    permiso = new Permiso() {
                    };
                }
                permiso.setCodigo(resultado.getInt("codigo"));
                permiso.setDescripcion(descripcion);
            }
            conexion.close();
            sentencia_buscar.close();
            return permiso; 
        }
        return null;
        
    }
    
    //método que devuelve una lista de todas los usuarios 
    public static ArrayList<Permiso> Lista() throws SQLException {
            Connection conexion = Conexion_BD.getConnection();
            PreparedStatement sentencia_mostrar = null;
            ArrayList<Permiso> lista = new ArrayList<Permiso>();

            sentencia_mostrar = conexion.prepareStatement("select * from permisos");
            ResultSet resultado = sentencia_mostrar.executeQuery();
            while (resultado.next()) {
                Permiso permiso = new Permiso() {
                };
                permiso.setCodigo(resultado.getInt("codigo"));
                permiso.setDescripcion(resultado.getString("descripcion"));
                lista.add(permiso);
            }
            conexion.close();
            sentencia_mostrar.close();
            return lista;
        }
    
    public static ArrayList<Permiso> Lista(int codigo_rol) throws SQLException {
            Connection conexion = Conexion_BD.getConnection();
            PreparedStatement sentencia_mostrar = null;
            ArrayList<Permiso> lista = new ArrayList<Permiso>();

            sentencia_mostrar = conexion.prepareStatement("select codigo, descripcion from roles_permisos, permisos where codigo_rol=" + codigo_rol + "and codigo_permiso=codigo");
            ResultSet resultado = sentencia_mostrar.executeQuery();
            while (resultado.next()) {
                Permiso permiso = new Permiso() {
                };
                permiso.setCodigo(resultado.getInt("codigo"));
                permiso.setDescripcion(resultado.getString("descripcion"));
                lista.add(permiso);
                System.out.println(resultado.getString("descripcion"));
            }
            conexion.close();
            sentencia_mostrar.close();
            return lista;
        }
}
