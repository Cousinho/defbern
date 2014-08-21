package BaseDeDatos;

import Entidades.Perfil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDPerfiles {

    //método que recibe objeto usuario y lo inserta en la base de datos
    public static boolean insertar(Perfil perfil) throws SQLException  {
        boolean insertar=true;
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_insertar= null;
        sentencia_insertar = conexion.prepareStatement("insert into perfiles (codigo, descripcion) VALUES (?,?)");
        sentencia_insertar.setInt(1, mayor()+1);
        sentencia_insertar.setString(2, perfil.getDescripcion());
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
        sentencia_eliminar = conexion.prepareStatement("delete from perfiles where codigo=?");
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
    public static boolean actualizar(Perfil perfil) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_actualizar = null;

        sentencia_actualizar = conexion.prepareStatement("update perfiles set descripcion=? where codigo=" + perfil.getCodigo());
        sentencia_actualizar.setString(1, perfil.getDescripcion());
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
    public static Perfil buscarId(int codigo) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        if(conexion != null)
        {
           PreparedStatement sentencia_buscar = null;
           sentencia_buscar = conexion.prepareStatement("select * from perfiles where codigo=?");
           sentencia_buscar.setInt(1, codigo);
           Perfil perfil=null;
           ResultSet resultado = sentencia_buscar.executeQuery();
            if (resultado.next()) {
                if (perfil == null) {
                    perfil = new Perfil() {
                    };
                }
                perfil.setCodigo(codigo);
                perfil.setDescripcion(resultado.getString("descripcion"));
            }
            conexion.close();
            sentencia_buscar.close();
            return perfil; 
        }
        return null;
        
    }
    
    
    //método que devuelve una lista de todas los usuarios 
    public static ArrayList<Perfil> Lista() throws SQLException {
            Connection conexion = Conexion_BD.getConnection();
            PreparedStatement sentencia_mostrar = null;
            ArrayList<Perfil> lista = new ArrayList<Perfil>();

            sentencia_mostrar = conexion.prepareStatement("select * from perfiles");
            ResultSet resultado = sentencia_mostrar.executeQuery();
            while (resultado.next()) {
                Perfil perfil = new Perfil() {
                };
                perfil.setCodigo(resultado.getInt("codigo"));
                perfil.setDescripcion(resultado.getString("descripcion"));
                lista.add(perfil);
            }
            conexion.close();
            sentencia_mostrar.close();
            return lista;
        }
    
    
      private static int mayor() throws SQLException{
        int mayor;
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_mayor = null;
        sentencia_mayor = conexion.prepareStatement("select max(codigo) as maximo from perfiles");
        ResultSet resultado = sentencia_mayor.executeQuery();
        if (!resultado.next()){
           mayor=0; 
        }else{
            mayor=resultado.getInt("maximo");
            
        }   
            return mayor;
        }
}
