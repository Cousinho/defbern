package Analisis;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

public class Boca {
    //lista de bocas y diferencias entre boca nariz
    private int listabocas[][] = new int[20][20];
    private int diferencias[]=new int[20];
    private Point boca = new Point(); 
    private Point tamañoboca = new Point();
    private String NombreArchivo;
    private int tamaño=0;
    private int DistanciaMinima;
    public void Analizar(String Nombre, String Directorio,Point nariz, Point ojo1, Point ojo2,Point tamañoojo){
                    NombreArchivo=Nombre;
                    //Busca las bocas con el xml de y si no encuetra busca con otro
                    Buscar(ojo1,ojo2,tamañoojo,nariz,"1",Directorio);
                    System.out.println(tamaño);
                    if(tamaño==0){
                        Buscar(ojo1,ojo2,tamañoojo,nariz,"2",Directorio);
                    }
                    SeleccionarBoca(nariz,ojo1,ojo2);
                          
    }
    
    private void SeleccionarBoca(Point nariz, Point ojo1,Point ojo2){
            int diferenciaactual = 0;
            int diferenciacentro=0;
            boolean primero=true;
            //Busca diferencias entre distancias y de cejas
            for(int x2=1;x2<=tamaño;x2++){
                diferencias[x2]=(int) Math.abs((listabocas[x2][1]-ojo1.x)-(ojo2.x-listabocas[x2][1]));
            }
            for(int x=1;x<=tamaño;x++){
                    if(!primero){
                        /*si no es el primero verifica la distancia mínima y 
                        ademas de la alineacion y distancia nariz    */
                        if(DistanciaMinima<Math.abs(nariz.y-listabocas[x][2])&&diferencias[x]<diferenciacentro){
                            diferenciaactual=(int) Math.abs(nariz.y-listabocas[x][2]);
                            boca.x=listabocas[x][1];
                            boca.y=listabocas[x][2];
                            tamañoboca.x = listabocas[x][3];
                            tamañoboca.y = listabocas[x][4];
                            diferenciacentro=diferencias[x];
                            
                        }
                    }else{
                        // si es el primero solo verifica la distancia minima 
                        if(DistanciaMinima<Math.abs(nariz.y-listabocas[x][2])){
                            primero=false;       boca.x=listabocas[x][1];
                            boca.y=listabocas[x][2];
                            tamañoboca.x = listabocas[x][3];
                            tamañoboca.y = listabocas[x][4];
                            diferenciaactual=(int) Math.abs(boca.y-nariz.y);  
                            diferenciacentro=diferencias[x];
                        }
                        }
                    }

             }
    
    private void Buscar(Point ojo1, Point ojo2,Point tamañoojo, Point nariz,String numero,String Directorio){
        CascadeClassifier faceDetector = new CascadeClassifier("xml/Boca"+numero+".xml");
                    String nombre="boca";
                    Mat image;
                    MatOfRect faceDetections;
                    image = Highgui.imread(NombreArchivo);
                    faceDetections = new MatOfRect();    
                    faceDetector.detectMultiScale(image, faceDetections);
                    //Genera la distancia mínima a partir de la imagen
                    DistanciaMinima=image.width()/10;
                    int nu=1;
                    for (Rect rect : faceDetections.toArray()) {
                            if(ojo1.x<rect.x+(rect.width/2)&&ojo2.x>rect.x+(rect.width/2)&&rect.y+(rect.width/2)>nariz.y){
                                double minimo=2.7;
                                if(faceDetections.size().height==1){
                                    minimo=3;
                                }
                                if(nariz.y<rect.y+(rect.height/2)&&rect.width>tamañoojo.x*1.6&&rect.width<tamañoojo.x*minimo){
                                    
                                    listabocas[nu][1]=rect.x+(rect.width/2);
                                    listabocas[nu][2]=rect.y+(rect.height/2);
                                    listabocas[nu][3]=rect.width;
                                    listabocas[nu][4]=rect.height;
                                    Core.rectangle(image, new Point(rect.x ,rect.y), new Point(rect.x +rect.width, rect.y + rect.height), new Scalar(0, 240, 0));
                                    Highgui.imwrite(Directorio+"Boca.png", image);
                                    nu++;
                                    tamaño++;
                                }
                            }
                        
                     }
    }
    
    public Point getBoca(){
        return boca;
    }
    public Point getTamaño(){
        return tamañoboca;
    }
}
