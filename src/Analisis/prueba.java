package Analisis;

import java.io.File;

public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.loadLibrary("opencv_java249");
        //File f=new File("imagen/Muestras/9/");
        //System.outprintln(f.length());
        
        Analisis a=new Analisis();
            int[] s = a.Analizar(9);
            if(s==null){
                System.out.println("error");
            }else {
                System.out.println(s.length);
                System.out.println(s[2]);
                System.out.println(s[1]);
            }
            
    }
    
}
