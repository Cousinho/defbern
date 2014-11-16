package Analisis;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

public class Ceja2 {
    String NombreArchivo="";
    int[][] listacejas=new int[30][30];
    boolean vacio=true;
    int nu = 0;
    int tamaño = 0;
    Point Ceja2=new Point();
    
    public void Analizar(String Nombre,String Directorio,Point OjoIzquierdo,Point Nariz) {
            NombreArchivo = Nombre;
            BuscarCejas1(Directorio,OjoIzquierdo,Nariz);
    }
    
    public void BuscarCejas1(String Directorio,Point OjoIzquierdo,Point Nariz){
        CascadeClassifier faceDetector = new CascadeClassifier("xml/OjoCejaIzquierda.xml");
        String nombre="Cejas";
        Mat image;
        MatOfRect faceDetections;
        image = Highgui.imread(NombreArchivo);
        int DistanciaMinima = image.width()/15;
        faceDetections = new MatOfRect();    
        faceDetector.detectMultiScale(image, faceDetections);

        
        // Busca las cejas que se encuentren por encima de los ojos y a una distancia minima de ellos
            for (Rect rect : faceDetections.toArray()) {
                   if(Math.abs((rect.x+(rect.width/2))-OjoIzquierdo.x)<image.size().height/20){
                        if((rect.y+(rect.height/2))<Nariz.y){
                            vacio=false;
                            Ceja2.x=rect.x+(rect.width/2);
                            Ceja2.y=rect.y+(rect.height/2);
                            listacejas[nu][4]=rect.height;
                            tamaño++;
                            Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x +(rect.width), rect.y +(rect.height)), new Scalar(0, 240, 0));
                            Highgui.imwrite(Directorio+"Ceja2.png", image);
                            nu++;
                        }
                        
                    }
                    
          
            }
            
            
           
           
}
    public int getTamaño(){
         return listacejas[1][4];
    }
    public Point getCeja2(){
        return Ceja2;
    }
}