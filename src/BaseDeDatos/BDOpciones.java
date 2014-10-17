package BaseDeDatos;

import Entidades.Lamina;
import Entidades.Opciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDOpciones {
    //método que recibe objeto usuario y lo inserta en la base de datos
    public static boolean insertar(Opciones opciones) throws SQLException  {
        boolean insertar=true;
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_insertar= null;
        sentencia_insertar = conexion.prepareStatement("insert into opciones (id_lamina, codigo, descripcion, nomenclatura) VALUES (?,?,?,?)");
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
        sentencia_eliminar = conexion.prepareStatement("delete from opciones where codigo=?");
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
    public static boolean actualizar(Opciones opciones) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_actualizar = null;

        sentencia_actualizar = conexion.prepareStatement("update opciones set descripcion=?, nomenclatura=? where codigo=" + opciones.getCodigo()+"and id_lamina="+opciones.getLamina().getCodigo());
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

    //método que busca 
    public static Opciones buscarId(int codigo,int id_lamina) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        if(conexion != null)
        {
           PreparedStatement sentencia_buscar = null;
           sentencia_buscar = conexion.prepareStatement("select * from opciones where codigo=? and id_lamina=?");
           sentencia_buscar.setInt(1, codigo);
           sentencia_buscar.setInt(2, id_lamina);
           Opciones opciones=null;
           ResultSet resultado = sentencia_buscar.executeQuery();
            if (resultado.next()) {
                if (opciones == null) {
                    opciones = new Opciones() {
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
    
    public static String buscarDesc(String descripcion,int id_lamina) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        if(conexion != null)
        {
           PreparedStatement sentencia_buscar = null;
           sentencia_buscar = conexion.prepareStatement("select * from opciones where descripcion=? and id_lamina=?");
           sentencia_buscar.setString(1, descripcion);
           sentencia_buscar.setInt(2, id_lamina);
           ResultSet resultado = sentencia_buscar.executeQuery();
           String nomenclatura="";
           if(resultado.next()){
               nomenclatura=(resultado.getString("nomenclatura"));
           }
           conexion.close();
           sentencia_buscar.close();
           return nomenclatura; 
        }
        return null;
        
    }
    
    
    //método que devuelve una lista de todas las opciones 
    public static ArrayList<Opciones> Lista(int id_lamina) throws SQLException {
            Connection conexion = Conexion_BD.getConnection();
            PreparedStatement sentencia_mostrar = null;
            ArrayList<Opciones> lista = new ArrayList<Opciones>();

            sentencia_mostrar = conexion.prepareStatement("select * from opciones where id_lamina="+id_lamina+" order by codigo");
            ResultSet resultado = sentencia_mostrar.executeQuery();
            while (resultado.next()) {
                Opciones opciones = new Opciones() {
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
    
    
        //método que devuelve una lista de todas las entrevistas 
        public static ArrayList<Opciones> ListaOpciones(String buscar,int id_lamina) throws SQLException {
           ArrayList<Opciones> lista = new ArrayList<Opciones>();
           if(buscar.equals("")){
                return lista;
            }
            Connection conexion = Conexion_BD.getConnection();
            PreparedStatement sentencia_mostrar = null;
            sentencia_mostrar=conexion.prepareStatement("select * from opciones where descripcion ilike'%"+buscar+"%' and id_lamina="+id_lamina);
            ResultSet resultado = sentencia_mostrar.executeQuery();
            while (resultado.next()) {
                Opciones opcion = new Opciones() {
                };
                opcion.setCodigo(resultado.getInt("codigo"));
                opcion.setDescripcion(resultado.getString("descripcion"));
                opcion.setNomenclatura(resultado.getString("nomenclatura"));
                lista.add(opcion);
            }
            conexion.close();
            sentencia_mostrar.close();
            return lista;
        }
    
    private static int mayor(Lamina id_lamina) throws SQLException{
        int mayor;
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_mayor = null;
        sentencia_mayor = conexion.prepareStatement("select max(codigo) as maximo from opciones where id_lamina="+id_lamina.getCodigo());
        ResultSet resultado = sentencia_mayor.executeQuery();
        if (!resultado.next()){
           mayor=0; 
        }else{
            mayor=resultado.getInt("maximo");
            
        }   
            return mayor;
    }
}
