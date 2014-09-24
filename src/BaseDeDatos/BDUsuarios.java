package BaseDeDatos;

import Entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDUsuarios {
    

    //método que recibe objeto usuario y lo inserta en la base de datos
    public static boolean insertar(Usuario usuario) throws SQLException  {
        boolean insertar=true;
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_insertar= null;
        sentencia_insertar = conexion.prepareStatement("insert into usuarios (codigo, nombre, contrasenha, rol) VALUES (?,?,?,?)");
        sentencia_insertar.setInt(1, usuario.getCodigo());
        sentencia_insertar.setString(2, usuario.getNombre());
        sentencia_insertar.setString(3, usuario.getContrasenha());
        sentencia_insertar.setString(4, usuario.getRol());
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
        sentencia_eliminar = conexion.prepareStatement("delete from usuarios where codigo=?");
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
    public static boolean actualizar(Usuario usuario) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_actualizar = null;

        sentencia_actualizar = conexion.prepareStatement("update usuarios set nombre=?, contrasenha=?, rol=? where codigo=" + usuario.getCodigo());
        sentencia_actualizar.setString(1, usuario.getNombre());
        sentencia_actualizar.setString(2, usuario.getContrasenha());
        sentencia_actualizar.setString(3, usuario.getRol());
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
    public static Usuario buscarId(int codigo) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        if(conexion != null)
        {
           PreparedStatement sentencia_buscar = null;
           sentencia_buscar = conexion.prepareStatement("select * from usuarios where codigo=?");
           sentencia_buscar.setInt(1, codigo);
           Usuario usuario=null;
           ResultSet resultado = sentencia_buscar.executeQuery();
            if (resultado.next()) {
                if (usuario == null) {
                    usuario = new Usuario() {
                    };
                }
                usuario.setCodigo(codigo);
                usuario.setContrasenha(resultado.getString("contrasenha"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setRol(resultado.getString("rol"));
            }
            conexion.close();
            sentencia_buscar.close();
            return usuario; 
        }
        return null;
        
    }
    
    
    public static Usuario buscarUsuario(String nombre) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        if(conexion != null)
        {
          PreparedStatement sentencia_buscar = null;
          Usuario usuario=null;
          sentencia_buscar = conexion.prepareStatement("select * from usuarios where nombre=?");
          sentencia_buscar.setString(1, nombre);
          ResultSet resultado = sentencia_buscar.executeQuery();
            if (resultado.next()) {
                if (usuario == null) {
                    usuario = new Usuario() {
                    };
                }
                usuario.setNombre(nombre);
                usuario.setContrasenha(resultado.getString("contrasenha"));
                usuario.setCodigo(resultado.getInt("codigo"));
                usuario.setRol(resultado.getString("rol"));
            }
            conexion.close();
            sentencia_buscar.close();
            return usuario; 
        }
        return null;
        
    }

    
    //método que devuelve una lista de todas los usuarios 
    public static ArrayList<Usuario> Lista() throws SQLException {
            Connection conexion = Conexion_BD.getConnection();
            PreparedStatement sentencia_mostrar = null;
            ArrayList<Usuario> lista = new ArrayList<Usuario>();

            sentencia_mostrar = conexion.prepareStatement("select * from usuarios order by codigo");
            ResultSet resultado = sentencia_mostrar.executeQuery();
            while (resultado.next()) {
                Usuario usuario = new Usuario() {
                };
                usuario.setCodigo(resultado.getInt("codigo"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setContrasenha(resultado.getString("contrasenha"));
                usuario.setRol(resultado.getString("rol"));
                lista.add(usuario);
            }
            conexion.close();
            sentencia_mostrar.close();
            return lista;
        }
    
}
