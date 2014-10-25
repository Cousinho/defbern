package Util;

import java.io.File;

public class Directorios {
    public void Crear(int Numero, int id){
        File local= new File("");
        File principal=new File(local.getAbsolutePath()+"//imagen//Muestras//"+id);
        principal.mkdir();
        for(int contador=0; contador<=Numero;contador++){
            File aux=new File(principal.getAbsolutePath()+"//"+contador);
            aux.mkdir();
        }
    }
    public void Borrar(int id){
        File local= new File("");
        File principal=new File(local.getAbsolutePath()+"\\imagen\\Muestras\\"+id+"\\");
        BorrarDirectorio(principal);
        principal.delete();
        principal.deleteOnExit();
    }
    private void BorrarDirectorio(File directorio){
        File[] ficheros = directorio.listFiles();
        for (int x=0;x<ficheros.length;x++){
            if (ficheros[x].isDirectory()) {
                BorrarDirectorio(ficheros[x]);
                ficheros[x].delete();
            }else{
                ficheros[x].delete();
            }
        }
    }
    
}
