package Analisis;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

public class Ojos {
    //Distancia Mínima y Máxima entre distancia entre ojos
    private int DistanciaMinima;
    private int DistanciaMaxima;
    //Lista de Ojos
    private int listaojos[][]=new int[20][20];
    //Punto donde se encuentra el ojo y el tamaño
    private Point ojo1=new Point();   
    private Point ojo2=new Point();
    private Point tamañoojo1=new Point();
    private Point tamañoojo2=new Point();
    //Nombre de la muestra
    private String NombreArchivo="";
    //Tamaño de la lista de ojos
    private int tamaño=0;
    private int valor=1;
    public void Analizar(String Archivo,String Directorio) {
            NombreArchivo=Archivo;
            //si no encuentra nada con el primer clasificador busca con el segundo
            Buscar("1",Directorio);
            if(tamaño==0){
                Buscar("2",Directorio);
                valor=2;
            }
            SelecionarOjos();
            
        }
    
        //Metodo que detecta los ojos en la imagen
        public void Buscar(String numero,String Directorio){
                CascadeClassifier faceDetector = new CascadeClassifier("xml/Ojos"+numero+".xml");
                    Mat image;
                    MatOfRect faceDetections;
                    image = Highgui.imread(NombreArchivo);
                   
                    DistanciaMinima=(int) (image.width()/3.2);
                    DistanciaMaxima=(int)(image.width()/2.4);
                    faceDetections = new MatOfRect();    
                    faceDetector.detectMultiScale(image, faceDetections);
                    int nu=1;
                    for (Rect rect : faceDetections.toArray()) {
                            listaojos[nu][1]=rect.x+(rect.width/2);
                            listaojos[nu][2]=rect.y+(rect.height/2);
                            listaojos[nu][3]=rect.height;
                            listaojos[nu][4]=rect.width;
                            nu++;
                            tamaño++;
                            Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x +rect.width, rect.y +rect.height), new Scalar(0, 240, 0));
                            Highgui.imwrite(Directorio+numero+"Ojos.png", image);
                     }
                    if(tamaño==1){
                        
                    }
                    
       }
        
       public void SelecionarOjos(){
           int diferencias[][]=new int[20][20];
             //Genera un lista diferencias en y, verifica distancia x entre ojos
            for(int x=1;x<=tamaño;x++){
                for(int y=1;y<=tamaño;y++){
                    if(x!=y){
                        if(DistanciaMinima<(Math.abs((listaojos[x][1]-listaojos[y][1])))&&DistanciaMaxima>(Math.abs((listaojos[x][1]-listaojos[y][1])))){
                           diferencias[x][y]=Math.abs(listaojos[x][2]-listaojos[y][2]);
                        }else{
                            diferencias[x][y]=10000;
                        }
                        
                    }


            }
                
            //Encuentra la menor distancia entre y de ojos    
            int xmenor = 0,ymenor = 0,vmenor=1000;
            for(int tx=1;tx<=tamaño;tx++){
                for(int ty=1;ty<=tamaño;ty++){
                    if(tx!=ty){
                        if(diferencias[tx][ty]<vmenor){
                            xmenor=tx;
                            ymenor=ty;
                            vmenor=diferencias[tx][ty];
                          }
                    }
                }
            }
            
            //selecciona ojo derecho como ojo1 y ojo izquierdo como ojo2
            if(listaojos[xmenor][1]<listaojos[ymenor][1]){
                ojo1.x=listaojos[xmenor][1];
                ojo1.y=listaojos[xmenor][2];
                tamañoojo1.x=listaojos[xmenor][3];
                tamañoojo1.y=listaojos[xmenor][4];
                ojo2.x=listaojos[ymenor][1];
                ojo2.y=listaojos[ymenor][2];
                tamañoojo2.x=listaojos[ymenor][3];
                tamañoojo2.y=listaojos[ymenor][4];
            }else{
                ojo2.x=listaojos[xmenor][1];
                ojo2.y=listaojos[xmenor][2];
                tamañoojo2.x=listaojos[ymenor][3];
                tamañoojo2.y=listaojos[ymenor][4];
                ojo1.x=listaojos[ymenor][1];
                ojo1.y=listaojos[ymenor][2];
                tamañoojo1.x=listaojos[xmenor][3];
                tamañoojo1.y=listaojos[xmenor][4];
            }

        }
       } 
        
       //Metodo que devuelve el valor de Ojo1 
       public Point getOjo1(){
           return ojo1;
       }

       //Metodo que devuelve el valor de Ojo2 
       public Point getOjo2(){
           return ojo2;
       }
       
       //Metodo que devuelve el valor del Tamaño de Ojo1 
       public Point getTamaño1(){
           return tamañoojo1;
       }
       
       //Metodo que devuelve el valor del Tamaño Ojo1 
       public Point getTamaño2(){
           return tamañoojo2;
       }
       
       public int valor(){
           return valor;
       }
}
