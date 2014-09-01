/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos;

import Entidades.Lamina;
import Entidades.Opciones2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class BDOpciones2 {
    //método que recibe objeto usuario y lo inserta en la base de datos
    public static boolean insertar(Opciones2 opciones) throws SQLException  {
        boolean insertar=true;
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_insertar= null;
        sentencia_insertar = conexion.prepareStatement("insert into opciones2 (id_lamina, codigo, descripcion, nomenclatura) VALUES (?,?,?,?)");
        System.out.println(opciones.getCodigo());
        sentencia_insertar.setInt(1, opciones.getLamina().getCodigo());
        sentencia_insertar.setInt(2, mayor(opciones.getLamina())+1);
        sentencia_insertar.setString(3, opciones.getDescripcion());
        sentencia_insertar.setString(4, opciones.getNomenclatura());
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
        sentencia_eliminar = conexion.prepareStatement("delete from opciones2 where codigo=?");
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
    public static boolean actualizar(Opciones2 opciones) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_actualizar = null;

        sentencia_actualizar = conexion.prepareStatement("update opciones2 set descripcion=?, nomenclatura=? where codigo=" + opciones.getCodigo()+"and id_lamina="+opciones.getLamina().getCodigo());
        sentencia_actualizar.setString(1, opciones.getDescripcion());
        sentencia_actualizar.setString(2, opciones.getNomenclatura());
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
    public static Opciones2 buscarId(int codigo,int id_lamina) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        if(conexion != null)
        {
           PreparedStatement sentencia_buscar = null;
           sentencia_buscar = conexion.prepareStatement("select * from opciones2 where codigo=? and id_lamina=?");
           sentencia_buscar.setInt(1, codigo);
           sentencia_buscar.setInt(2, id_lamina);
           Opciones2 opciones=null;
           ResultSet resultado = sentencia_buscar.executeQuery();
            if (resultado.next()) {
                if (opciones == null) {
                    opciones = new Opciones2() {
                    };
                }
                opciones.setCodigo(codigo);
                opciones.setLamina(BDLaminas.buscarId(resultado.getInt("id_lamina")));
                opciones.setDescripcion(resultado.getString("descripcion"));
                opciones.setNomenclatura(resultado.getString("nomenclatura"));
            }
            conexion.close();
            sentencia_buscar.close();
            return opciones; 
        }
        return null;
        
    }
    
    
    //método que devuelve una lista de todas las opciones 
    public static ArrayList<Opciones2> Lista(int id_lamina) throws SQLException {
            Connection conexion = Conexion_BD.getConnection();
            PreparedStatement sentencia_mostrar = null;
            ArrayList<Opciones2> lista = new ArrayList<Opciones2>();

            sentencia_mostrar = conexion.prepareStatement("select * from opciones2 where id_lamina="+id_lamina);
            ResultSet resultado = sentencia_mostrar.executeQuery();
            while (resultado.next()) {
                Opciones2 opciones = new Opciones2() {
                };
                opciones.setCodigo(resultado.getInt("codigo"));
                opciones.setLamina(BDLaminas.buscarId(resultado.getInt("id_lamina")));
                opciones.setDescripcion(resultado.getString("descripcion"));
                opciones.setNomenclatura(resultado.getString("nomenclatura"));
                lista.add(opciones);
            }
            conexion.close();
            sentencia_mostrar.close();
            return lista;
    }
    
    private static int mayor(Lamina id_lamina) throws SQLException{
        int mayor;
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_mayor = null;
        sentencia_mayor = conexion.prepareStatement("select max(codigo) as maximo from opciones2 where id_lamina="+id_lamina.getCodigo());
        ResultSet resultado = sentencia_mayor.executeQuery();
        if (!resultado.next()){
           mayor=0; 
        }else{
            mayor=resultado.getInt("maximo");
            
        }   
            return mayor;
    }
}
