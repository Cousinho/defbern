/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Analisis;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author alejandroalen
 */
public class Ceja1 {
    private String NombreArchivo="";
    private int[][] listacejas=new int[30][30];
    private boolean vacio=true;
    private int nu = 0;
    private int tamaño = 0;
    private Point Ceja1=new Point();
    private int tamañoceja=0;
    public void Analizar(String Nombre,String Directorio,Point OjoDerecho,Point Nariz) {
            NombreArchivo = Nombre;
            BuscarCejas1(Directorio,OjoDerecho,Nariz);
    }
    
    public void BuscarCejas1(String Directorio,Point OjoIzquierdo,Point Nariz){
        CascadeClassifier faceDetector = new CascadeClassifier("xml/OjoCejaDerecha.xml");
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
                            Ceja1.x=rect.x+(rect.width/2);
                            Ceja1.y=rect.y+(rect.height/2);
                            tamañoceja=rect.height;
                            tamaño++;
                            Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x +(rect.width), rect.y +(rect.height)), new Scalar(0, 240, 0));
                            Highgui.imwrite(Directorio+"Ceja1.png", image);
                            nu++;
                        }
                    }
                    
          
            }
            
            
           
           
}
    public int getTamaño(){
         return tamañoceja;
    }
    
    public Point getCeja1(){
        return Ceja1;
    }
}