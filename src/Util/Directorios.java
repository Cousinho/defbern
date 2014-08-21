package Util;

import java.io.File;

public class Directorios {
    public void Crear(int Numero, int id){
        File local= new File("");
        File principal=new File(local.getAbsolutePath()+"//imagen//Muestras//"+id);
        principal.mkdir();
        for(int contador=1; contador<=Numero;contador++){
            File aux=new File(principal.getAbsolutePath()+"//"+contador);
            aux.mkdir();
        }
    }
}
