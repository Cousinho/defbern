package Prueba;

import java.io.File;
import java.io.IOException;

public class prue {

    public static void main(String[] args) throws IOException {
        File miDir2= new File("");
        System.out.println(miDir2.getAbsolutePath());
        String s=miDir2.getAbsoluteFile()+"\\imagen\\Muestras\\ss";
        File miDir=new File(s);
        miDir.mkdir();
        System.out.println(s);
    } 
}
    

