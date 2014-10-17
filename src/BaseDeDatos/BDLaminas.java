package BaseDeDatos;

import Entidades.Lamina;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class BDLaminas {
    //método que recibe objeto usuario y lo inserta en la base de datos
    public static int insertar(Lamina lamina) throws SQLException  {
        try {
            FileInputStream fis = null;
            Connection conexion = Conexion_BD.getConnection();
            PreparedStatement sentencia_insertar= null;
            File file = new File(lamina.getRuta());
            fis = new FileInputStream(file);
            sentencia_insertar = conexion.prepareStatement("insert into laminas (codigo,imagen,descripcion, id_entrevista) VALUES (?,?,?,?)");
            int codigo=0;
            codigo=mayor();
            if(codigo==0){
                sentencia_insertar.setInt(1,codigo+1);
            }else{
                sentencia_insertar.setInt(1,codigo+1);
            }
            sentencia_insertar.setBinaryStream(2, fis,(int) file.length());
            sentencia_insertar.setString(3, lamina.getDescripcion());
            sentencia_insertar.setInt(4, lamina.getEntrevista().getCodigo());
            try {
                sentencia_insertar.executeUpdate();
            } catch (SQLException ex) {
                return 0;
            }
            conexion.close();
            sentencia_insertar.close();
            return codigo+1;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BDLaminas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
         //metodo que recibe le identificador de la usuario a ser eliminado
    public static boolean eliminar(int codigo) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_eliminar = null;
        sentencia_eliminar = conexion.prepareStatement("delete from laminas where codigo=?");
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
    public static boolean actualizar(Lamina lamina) throws SQLException {
        try {
            Connection conexion = Conexion_BD.getConnection();
            PreparedStatement sentencia_actualizar = null;
            if(lamina.getRuta()!=null){
                FileInputStream fis = null;
                File file = new File(lamina.getRuta());
                fis = new FileInputStream(file);
                sentencia_actualizar = conexion.prepareStatement("update laminas set imagen=?,descripcion=? where codigo=" + lamina.getCodigo());
                sentencia_actualizar.setBinaryStream(1, fis,(int) file.length());
                sentencia_actualizar.setString(2, lamina.getDescripcion());
      
            }else{
                sentencia_actualizar = conexion.prepareStatement("update laminas set descripcion=? where codigo=" + lamina.getCodigo());
                sentencia_actualizar.setString(1, lamina.getDescripcion());
            }
            int rowsUpdated = sentencia_actualizar.executeUpdate();
               conexion.close();
               sentencia_actualizar.close();
            if (rowsUpdated > 0) {
                return true;
            } else {
                return false;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BDLaminas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static Lamina buscarId(int codigo) throws SQLException {
        Connection conexion = Conexion_BD.getConnection();
        if(conexion != null)
        {
          PreparedStatement sentencia_buscar = null;
          Lamina lamina=null;
          sentencia_buscar = conexion.prepareStatement("select * from laminas where codigo=?");
          sentencia_buscar.setInt(1, codigo);
          ResultSet resultado = sentencia_buscar.executeQuery();
            if (resultado.next()) {
                if (lamina == null) {
                    lamina = new Lamina() {
                    };
                }
                lamina.setCodigo(codigo);
                try {
                   lamina.setImagen(ConvertirImagen(resultado.getBytes("imagen")));
                } catch (IOException ex) {
                    Logger.getLogger(BDLaminas.class.getName()).log(Level.SEVERE, null, ex);
                }
                lamina.setDescripcion(resultado.getString("descripcion"));
                lamina.setEntrevista(BDEntrevistas.buscarId(resultado.getInt("id_entrevista")));
            }
            conexion.close();
            sentencia_buscar.close();
            return lamina; 
        }
        return null;
        
    }

    
    //método que devuelve una lista de todas los usuarios 
    public static ArrayList<Lamina> Lista(int id_entrevista) throws SQLException {
            Connection conexion = Conexion_BD.getConnection();
            PreparedStatement sentencia_mostrar = null;
            ArrayList<Lamina> lista = new ArrayList<Lamina>();

            sentencia_mostrar = conexion.prepareStatement("select * from laminas where id_entrevista="+id_entrevista+" order by codigo");
            ResultSet resultado = sentencia_mostrar.executeQuery();
            while (resultado.next()) {
                Lamina lamina = new Lamina() {
                };
                lamina.setCodigo(resultado.getInt("codigo"));
                try {
                   lamina.setImagen(ConvertirImagen(resultado.getBytes("imagen")));
                } catch (IOException ex) {
                    Logger.getLogger(BDLaminas.class.getName()).log(Level.SEVERE, null, ex);
                }
                lamina.setDescripcion(resultado.getString("descripcion"));
                lamina.setEntrevista(BDEntrevistas.buscarId(resultado.getInt("id_entrevista")));
                lista.add(lamina);
            }
            conexion.close();
            sentencia_mostrar.close();
            return lista;
        }
     
    private static Image ConvertirImagen(byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator readers = ImageIO.getImageReadersByFormatName("jpeg");    
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis;
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        return reader.read(0, param);
    }
    
     public static int mayor() throws SQLException{
     int mayor;
      Connection conexion = Conexion_BD.getConnection();
        PreparedStatement sentencia_mayor = null;
        sentencia_mayor = conexion.prepareStatement("select max(codigo) as maximo from laminas");
        ResultSet resultado = sentencia_mayor.executeQuery();
        if (!resultado.next()){
           mayor=0; 
        }else{
            mayor=resultado.getInt("maximo");
            
        }   
            return mayor;
        }

    
}
    

