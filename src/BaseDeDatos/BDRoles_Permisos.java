/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos;

import Entidades.Rol_Permiso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class BDRoles_Permisos {
       
//método que recibe objeto usuario y lo inserta en la base de datos
    public static boolean insertar(Rol_Permiso rol_permiso) throws SQLException  {
        boolean insertar=true;
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_insertar= null;
        sentencia_insertar = conexion.prepareStatement("insert into roles_permisos (codigo_rol, codigo_permiso) VALUES (?,?)");
        sentencia_insertar.setInt(1, rol_permiso.getRol().getCodigo());
        sentencia_insertar.setInt(2, rol_permiso.getPermiso().getCodigo());
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
    public static boolean eliminar(int codigo_rol, int codigo_permiso) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_eliminar = null;
        sentencia_eliminar = conexion.prepareStatement("delete from roles_permisos where codigo_rol=? and codigo_permiso=?");
        sentencia_eliminar.setInt(1, codigo_rol);
        sentencia_eliminar.setInt(2, codigo_permiso);
        int rowsUpdated = sentencia_eliminar.executeUpdate();
        conexion.close();
        sentencia_eliminar.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean eliminar(int codigo_rol) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_eliminar = null;
        sentencia_eliminar = conexion.prepareStatement("delete from roles_permisos where codigo_rol=?");
        sentencia_eliminar.setInt(1, codigo_rol);
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
    public static boolean actualizar(Rol_Permiso rol_permiso) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_actualizar = null;
        sentencia_actualizar = conexion.prepareStatement("update roles_permisos set codigo_rol=?, codigo_permiso=? where codigo_rol=" + rol_permiso.getRol() +"and codigo_permiso=" + rol_permiso.getPermiso());
        sentencia_actualizar.setInt(1, rol_permiso.getRol().getCodigo());
        sentencia_actualizar.setInt(2, rol_permiso.getPermiso().getCodigo());
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
    public static Rol_Permiso buscarIdRol(int codigo) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        if(conexion != null)
        {
           PreparedStatement sentencia_buscar = null;
           sentencia_buscar = conexion.prepareStatement("select * from roles_permisos where codigo_rol=?");
           sentencia_buscar.setInt(1, codigo);
           Rol_Permiso rol_permiso=null;
           ResultSet resultado = sentencia_buscar.executeQuery();
            if (resultado.next()) {
                if (rol_permiso == null) {
                    rol_permiso = new Rol_Permiso() {
                    };
                }
                rol_permiso.setRol(BDRoles.buscarId(resultado.getInt("codigo_rol")));
                rol_permiso.setPermiso(BDPermisos.buscarId(resultado.getInt("codigo_permiso")));
            }
            conexion.close();
            sentencia_buscar.close();
            return rol_permiso; 
        }
        return null;
    }
    
    public static Rol_Permiso buscar(int codigo_rol, int codigo_permiso) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        if(conexion != null)
        {
           PreparedStatement sentencia_buscar = null;
           sentencia_buscar = conexion.prepareStatement("select * from roles_permisos where codigo_rol=? and codigo_permiso=?");
           sentencia_buscar.setInt(1, codigo_rol);
           sentencia_buscar.setInt(2, codigo_permiso);
           Rol_Permiso rol_permiso=null;
           ResultSet resultado = sentencia_buscar.executeQuery();
            if (resultado.next()) {
                if (rol_permiso == null) {
                    rol_permiso = new Rol_Permiso() {
                    };
                }
                rol_permiso.setRol(BDRoles.buscarId(resultado.getInt("codigo_rol")));
                rol_permiso.setPermiso(BDPermisos.buscarId(resultado.getInt("codigo_permiso")));
            }
            conexion.close();
            sentencia_buscar.close();
            return rol_permiso;
        }
        return null;
    }
    
    //método que devuelve una lista de todos los roles y permisos  
    public static ArrayList<Rol_Permiso> Lista(int codigo_rol) throws SQLException {
            Connection conexion = Conexion_BD.getConnection();
            PreparedStatement sentencia_mostrar = null;
            ArrayList<Rol_Permiso> lista = new ArrayList<Rol_Permiso>();

            sentencia_mostrar = conexion.prepareStatement("select from roles_permisos, permisos where codigo_rol=" + codigo_rol + "and codigo_permiso=codigo");
            ResultSet resultado = sentencia_mostrar.executeQuery();
            while (resultado.next()) {
                Rol_Permiso rol_permiso = new Rol_Permiso() {
                };
                rol_permiso.setRol(BDRoles.buscarId(resultado.getInt("codigo_rol")));
                rol_permiso.setPermiso(BDPermisos.buscarId(resultado.getInt("codigo_permiso")));
                lista.add(rol_permiso);
            }
            conexion.close();
            sentencia_mostrar.close();
            return lista;
        }
}
