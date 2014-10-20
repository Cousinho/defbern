package Prueba;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

public class Nariz {
   //Lista de ojos
   private int listanariz[][]=new int[20][20];
   //Lista de diferencias entre y ojos e y nariz
   private int diferencias[]=new int[20];
   private Point nariz=new Point(); 
   private String NombreArchivo="";
   private int tamaño=0;
   //Distancia Mínima y Máxima entre el y de los ojos y nariz
   private int DistanciaMaxima=0;
   private int DistanciaMinima=0;
   public Point Analizar(String Nombre,String Directorio,Point ojo1, Point ojo2){
       Buscar(ojo1 ,ojo2 ,Nombre,Directorio);
       SeleccionarNariz( ojo1, ojo2);
       return nariz;
    }
    
    
    private void Buscar(Point ojo1, Point ojo2, String Nombre,String Directorio){
        CascadeClassifier faceDetector = new CascadeClassifier("xml/Nariz.xml");
        String nombre="nariz";
        Mat image;
        MatOfRect faceDetections;
        image = Highgui.imread(Nombre);
        //Genera un valores mínimos y máximos a partir de el tamaño de la muestra
        DistanciaMaxima=image.width()/3;
        DistanciaMinima=image.width()/15;
        faceDetections = new MatOfRect();    
        faceDetector.detectMultiScale(image, faceDetections);
        int nu=1;
        for (Rect rect : faceDetections.toArray()) {
            Mat region = new Mat(image,rect);
            //Verifica que la nariz se encuentre entre los ojos
            if((rect.x+(rect.width/2))>ojo1.x&&(rect.x+(rect.width/2))<ojo2.x){
                if((rect.y+(rect.height/2))>ojo2.y){
                    listanariz[nu][1]=rect.x+(rect.width/2);
                    listanariz[nu][2]=rect.y+(rect.height/2);
                    Core.rectangle(image, new Point(rect.x+rect.width, rect.y+rect.height), new Point(rect.x  ,rect.y), new Scalar(0, 240, 0));
                    Highgui.imwrite(Directorio+"Nariz.png", image);
                    nu++;
                    tamaño++;
                }
            }
        }
    }
    
    private void SeleccionarNariz(Point ojo1, Point ojo2){
        //Diferencia entre en alineación con el centro de los ojos
       int diferenciaactual = 0;
       int diferenciacentro=0;
       boolean primero=true;
       //Buscar las diferencia de alineación de la nariz con los ojos
       for(int x2=1;x2<=tamaño;x2++){
            diferencias[x2]=(int) Math.abs((listanariz[x2][1]-ojo1.x)-(ojo2.x-listanariz[x2][1]));
       }
       //Inicia el ciclo para analizar la lista nariz
       for(int x=1;x<=tamaño;x++){
           /*Si no es el primero los asigna como nariz si cumple con la condición
           de distancia mínima máxima y distanciacentro*/
           if(!primero){
                if(DistanciaMinima<Math.abs(listanariz[x][2]-ojo1.y)&&Math.abs(listanariz[x][2]-ojo1.y)<DistanciaMaxima
                  &&diferencias[x]<diferenciacentro){
                    nariz.x=listanariz[x][1];
                    nariz.y=listanariz[x][2];
                    diferenciacentro=diferencias[x];
                }
            }else{
               //Verifica solo la distancia mínima y máxima
               if(DistanciaMinima<Math.abs(listanariz[x][2]-ojo1.y)&&Math.abs(listanariz[x][2]-ojo1.y)<DistanciaMaxima){
                    primero=false;  
                    nariz.x=listanariz[x][1];
                    nariz.y=listanariz[x][2];
                    diferenciaactual=(int) Math.abs(ojo1.y-nariz.y);  
                    diferenciacentro=diferencias[x];
                }
            }
        }
    }
    public Point getNariz(){
        return nariz;
    }
}


 