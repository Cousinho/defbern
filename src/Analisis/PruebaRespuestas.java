package Analisis;

import BaseDeDatos.BDRegistros;
import java.sql.SQLException;

public class PruebaRespuestas {

    public static void main(String[] args) throws SQLException {
        System.loadLibrary("opencv_java249");
        AnalisisRorschach prueba=new AnalisisRorschach();
        String perfil = prueba.AnalizarRegistro(BDRegistros.buscarId(7));
        System.out.println(perfil);
    }
    
}
