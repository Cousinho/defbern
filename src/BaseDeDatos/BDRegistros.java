package BaseDeDatos;

import Entidades.Registro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDRegistros {

    //método que recibe objeto usuario y lo inserta en la base de datos
    public static boolean insertar(Registro registro) throws SQLException  {
        boolean insertar=true;  
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_insertar= null;
        sentencia_insertar = conexion.prepareStatement("insert into registros (codigo, ci, nombre, apellido, descripcion, id_usuario, id_perfil) VALUES (?,?,?,?,?,?,?)");
        sentencia_insertar.setInt(1, registro.getCodigo());
        sentencia_insertar.setInt(2, registro.getCi());
        sentencia_insertar.setString(3, registro.getNombre());
        sentencia_insertar.setString(4, registro.getApellido());
        sentencia_insertar.setString(5, registro.getDescripcion());
        sentencia_insertar.setInt(6, registro.getUsuario().getCodigo());
        sentencia_insertar.setInt(7, registro.getPerfil().getCodigo());
        try {
            sentencia_insertar.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
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
        sentencia_eliminar = conexion.prepareStatement("delete from registros where codigo=?");
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
    public static boolean actualizar(Registro registro) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_actualizar = null;

        sentencia_actualizar = conexion.prepareStatement("update registros set ci=?, nombre=?, apellido=?, descripcion=?, id_usuario=? where codigo=" + registro.getCodigo());
        sentencia_actualizar.setInt(1, registro.getCi());
        sentencia_actualizar.setString(2, registro.getNombre());
        sentencia_actualizar.setString(3, registro.getApellido());
        sentencia_actualizar.setString(4, registro.getDescripcion());
        sentencia_actualizar.setInt(5, registro.getUsuario().getCodigo());
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
    public static Registro buscarId(int codigo) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        if(conexion != null)
        {
           PreparedStatement sentencia_buscar = null;
           sentencia_buscar = conexion.prepareStatement("select * from registros where codigo=?");
           sentencia_buscar.setInt(1, codigo);
           Registro registro=null;
           ResultSet resultado = sentencia_buscar.executeQuery();
            if (resultado.next()) {
                if (registro == null) {
                    registro = new Registro() {
                    };
                }
                registro.setCodigo(codigo);
                registro.setCi(resultado.getInt("ci"));
                registro.setNombre(resultado.getString("nombre"));
                registro.setApellido(resultado.getString("apellido"));
                registro.setDescripcion(resultado.getString("descripcion"));
                registro.setUsuario(BDUsuarios.buscarId(resultado.getInt("id_usuario")));
                registro.setPerfil(BDPerfiles.buscarId(resultado.getInt("id_perfil")));
            }
            conexion.close();
            sentencia_buscar.close();
            return registro; 
        }
        return null;
        
    }
    
    
    public static Registro buscarCi(int ci) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        if(conexion != null)
        {
           PreparedStatement sentencia_buscar = null;
           sentencia_buscar = conexion.prepareStatement("select * from registros where ci=?");
           sentencia_buscar.setInt(1, ci);
           Registro registro=null;
           ResultSet resultado = sentencia_buscar.executeQuery();
            if (resultado.next()) {
                if (registro == null) {
                    registro = new Registro() {
                    };
                }
                registro.setCodigo(resultado.getInt("codigo"));
                registro.setCi(ci);
                registro.setNombre(resultado.getString("nombre"));
                registro.setApellido(resultado.getString("apellido"));
                registro.setDescripcion(resultado.getString("descripcion"));
                registro.setUsuario(BDUsuarios.buscarId(resultado.getInt("id_usuario")));
                registro.setPerfil(BDPerfiles.buscarId(resultado.getInt("id_perfil")));
            }
            conexion.close();
            sentencia_buscar.close();
            return registro; 
        }
        return null;
        
    }
 
    
    //método que devuelve una lista de todas los usuarios 
    public static ArrayList<Registro> Lista() throws SQLException {
            Connection conexion = Conexion_BD.getConnection();
            PreparedStatement sentencia_mostrar = null;
            ArrayList<Registro> lista = new ArrayList<Registro>();

            sentencia_mostrar = conexion.prepareStatement("select * from usuarios");
            ResultSet resultado = sentencia_mostrar.executeQuery();
            while (resultado.next()) {
                Registro registro = new Registro() {
                };
                registro.setCodigo(resultado.getInt("codigo"));
                registro.setCi(resultado.getInt("ci"));
                registro.setNombre(resultado.getString("nombre"));
                registro.setApellido(resultado.getString("apellido"));
                registro.setDescripcion(resultado.getString("descripcion"));
                registro.setUsuario(BDUsuarios.buscarId(resultado.getInt("id_usuario")));
                registro.setPerfil(BDPerfiles.buscarId(resultado.getInt("id_perfil")));
                lista.add(registro);
            }
            conexion.close();
            sentencia_mostrar.close();
            return lista;
        }

}
