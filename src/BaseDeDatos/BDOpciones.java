package BaseDeDatos;

import Entidades.Opciones;
import Entidades.Registro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;


public class BDOpciones {
    //método que recibe objeto usuario y lo inserta en la base de datos
    public static boolean insertar(Opciones opciones) throws SQLException  {
        boolean insertar=true;
        String lista="'{";
        String comilla="\"";
        PreparedStatement sentencia_insertar= null;
        for (Iterator<String> it = opciones.getLista().iterator(); it.hasNext();) {
                String opcion = it.next();
                lista=lista+comilla+opcion+comilla+",";
        }
        
        lista = lista.substring(0, lista.length()-1); 
        lista=lista+"}'";
        int codigo=mayor(opciones.getId_lamina())+1;
        Connection conexion = Conexion_BD.getConnection();
        sentencia_insertar = conexion.prepareStatement("insert into opciones (codigo, id_lamina, lista) VALUES ("+codigo+","+opciones.getId_lamina()+","+lista+")");
        try {
            sentencia_insertar.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            insertar=false;
        }
        System.out.println("insert into opciones (codigo, id_lamina, lista) VALUES ("
                +codigo+","+opciones.getId_lamina()+","+"'"+lista+"'"+ ")");
        conexion.close();
        sentencia_insertar.close();
        return insertar;
    }
    
    //metodo que recibe le identificador de la usuario a ser eliminado
    public static boolean eliminar(int codigo,int id_lamina) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_eliminar = null;
        sentencia_eliminar = conexion.prepareStatement("delete from opciones where codigo=? and id_lamina=?");
        sentencia_eliminar.setInt(1, codigo);
        sentencia_eliminar.setInt(2, id_lamina);
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
    public static boolean actualizar(Opciones opciones) throws SQLException {
        String lista="'{";
        String comilla="\"";
        for (Iterator<String> it = opciones.getLista().iterator(); it.hasNext();) {
                String opcion = it.next();
                lista=lista+comilla+opcion+comilla+",";
        }   
        lista = lista.substring(0, lista.length()-1); 
        lista=lista+"}'";
        int codigo=mayor(opciones.getId_lamina())+1;
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_actualizar = conexion.prepareStatement("update opciones set lista="+lista+" where codigo=" + opciones.getCodigo()+"and id_lamina="+opciones.getId_lamina());
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
    public static Opciones buscarId(int id_lamina) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        String cadenalista;
        int tamañolista =0;
        if(conexion != null)
        {
           PreparedStatement sentencia_buscar = null;
           sentencia_buscar = conexion.prepareStatement("select * from opciones where id_lamina=?");
           sentencia_buscar.setInt(1, id_lamina);
           Opciones opciones=null;
           ResultSet resultado = sentencia_buscar.executeQuery();
            if (resultado.next()) {
                if (opciones == null) {
                    opciones = new Opciones() {
                    };
                }
            }
            tamañolista=tamaño(opciones.getId_lamina());
            opciones.setCodigo(resultado.getInt("codigo"));
            cadenalista=resultado.getString("lista");
            cadenalista=cadenalista.replace("{", "");
            cadenalista=cadenalista.replace("}", "");
            StringTokenizer token = new StringTokenizer(cadenalista,",");
            for(int x=0;x<=token.countTokens();x++){
                opciones.addLista(token.nextToken());
            }
            conexion.close();
            sentencia_buscar.close();
            return opciones; 
        }
        return null;
        
    }
    
    private static int mayor(int id_lamina) throws SQLException{
        int mayor;
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_mayor = null;
        sentencia_mayor = conexion.prepareStatement("select max(codigo) as maximo from opciones where id_lamina="+id_lamina);
        ResultSet resultado = sentencia_mayor.executeQuery();
        if (!resultado.next()){
           mayor=0; 
        }else{
            mayor=resultado.getInt("maximo");
            
        }   
            return mayor;
        }
     
    //retorna el tamaño del array en la base de datos 
    private static int tamaño(int id_lamina) throws SQLException{
        int mayor;
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_mayor = null;
        sentencia_mayor = conexion.prepareStatement("SELECT array_upper(lista,1) FROM opciones where id_lamina="+id_lamina);
        ResultSet resultado = sentencia_mayor.executeQuery();
        if (!resultado.next()){
           mayor=0; 
        }else{
            mayor=resultado.getInt("array_upper");
            
        }   
        return mayor; 
    }

}
