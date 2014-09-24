package BaseDeDatos;

import Entidades.Entrevista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDEntrevistas {
    

    //método que recibe objeto usuario y lo inserta en la base de datos
    public static boolean insertar(Entrevista entrevista) throws SQLException  {
        boolean insertar=true;
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_insertar= null;
        sentencia_insertar = conexion.prepareStatement("insert into entrevistas (codigo, nombre, descripcion) VALUES (?,?,?)");
        sentencia_insertar.setInt(1, entrevista.getCodigo());
        sentencia_insertar.setString(2, entrevista.getNombre());
        sentencia_insertar.setString(3, entrevista.getDescripcion());
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
        sentencia_eliminar = conexion.prepareStatement("delete from entrevistas where codigo=?");
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
    public static boolean actualizar(Entrevista entrevista) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_actualizar = null;

        sentencia_actualizar = conexion.prepareStatement("update entrevistas set nombre=?, descripcion=? where codigo=" + entrevista.getCodigo());
        sentencia_actualizar.setString(1, entrevista.getNombre());
        sentencia_actualizar.setString(2, entrevista.getDescripcion());
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
    public static Entrevista buscarId(int codigo) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        if(conexion != null)
        {
           PreparedStatement sentencia_buscar = null;
           sentencia_buscar = conexion.prepareStatement("select * from entrevistas where codigo=?");
           sentencia_buscar.setInt(1, codigo);
           Entrevista entrevista=null;
           ResultSet resultado = sentencia_buscar.executeQuery();
            if (resultado.next()) {
                if (entrevista == null) {
                    entrevista = new Entrevista() {
                    };
                }
                entrevista.setCodigo(codigo);
                entrevista.setNombre(resultado.getString("nombre"));
                entrevista.setDescripcion(resultado.getString("descripcion"));
            }
            conexion.close();
            sentencia_buscar.close();
            return entrevista; 
        }
        return null;
        
    }
    
    
    //método que devuelve una lista de todas los usuarios 
    public static ArrayList<Entrevista> Lista() throws SQLException {
            Connection conexion = Conexion_BD.getConnection();
            PreparedStatement sentencia_mostrar = null;
            ArrayList<Entrevista> lista = new ArrayList<Entrevista>();

            sentencia_mostrar = conexion.prepareStatement("select * from entrevistas");
            ResultSet resultado = sentencia_mostrar.executeQuery();
            while (resultado.next()) {
                Entrevista entrevista = new Entrevista() {
                };
                entrevista.setCodigo(resultado.getInt("codigo"));
                entrevista.setNombre(resultado.getString("nombre"));
                entrevista.setDescripcion(resultado.getString("descripcion"));
                lista.add(entrevista);
            }
            conexion.close();
            sentencia_mostrar.close();
            return lista;
        }
    
}
