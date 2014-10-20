package Prueba;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

public class Cejas {
    private int listacejas[][]=new int[20][20];
    private Point ceja1=new Point();   
    private Point ceja2=new Point();
    private Point tamañoceja1=new Point();
    private Point tamañoceja2=new Point();
    private String NombreArchivo="";
    private int nu=1;
    private int DistanciaMinima;
    private int tamaño=0;
           
    public void Analizar(String Nombre,String Directorio,Point ojo1, Point ojo2,Point tamañoojo) {
            NombreArchivo=Nombre;
            BuscarCejas(ojo1,ojo2,tamañoojo,Directorio);
            SelecionarCejas(ojo1, ojo2); 
    }
    
    //Copia y genera valores si solo se encuentra 1 ceja
    private void CopiarValores(Point ojo1,Point ojo2){
       int Distancia=Math.abs((int)(listacejas[1][1]-ojo1.x));
               if(Distancia>DistanciaMinima){
                   ceja2.x=listacejas[1][1]+Math.abs(listacejas[1][1]-ojo2.x);
                   ceja2.y=listacejas[1][2];
                   ceja1.x=ojo1.x;
                   ceja1.y=ojo1.y+(ceja2.y-ojo2.y);   
               }else{
                   ceja1.x=listacejas[1][1];
                   ceja1.y=listacejas[1][2];
                   ceja2.x=ojo2.x+Distancia;
                   ceja2.y=ojo2.y+(ceja1.y-ojo1.y);
               } 
    }
            
    //Utiliza el xml para encontrar la lista de Cejas
    public void BuscarCejas(Point Ojo1, Point Ojo2, Point tamañoojo,String Directorio){
        CascadeClassifier faceDetector = new CascadeClassifier("xml/Cejas.xml");
        String nombre="Cejas";
        Mat image;
        MatOfRect faceDetections;
        image = Highgui.imread(NombreArchivo);
        DistanciaMinima=image.width()/15;
        faceDetections = new MatOfRect();    
        faceDetector.detectMultiScale(image, faceDetections);
        boolean vacio=true;
        // Busca las cejas que se encuentren por encima de los ojos y a una distancia minima de ellos
            for (Rect rect : faceDetections.toArray()) {
                if(rect.y+(rect.height/2)<Ojo1.y && DistanciaMinima<Math.abs((rect.y+(rect.height/2))-Ojo1.y)&&
                    tamañoojo.x>rect.x){
                    vacio=false;
                    listacejas[nu][1]=rect.x+(rect.width/2);
                    listacejas[nu][2]=rect.y+(rect.height/2);
                    listacejas[nu][3]=rect.width;
                    listacejas[nu][4]=rect.height;
                    tamaño++;
                    Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x +rect.width, rect.y +rect.height), new Scalar(0, 240, 0));
                    Highgui.imwrite(Directorio+"Cejas.png", image);
                    nu++;
                }
            }
            //Si la lista esta vacia crea de nuevo la misma sin restricción de distancia
            if(vacio){
                for (Rect rect : faceDetections.toArray()) {
                    if(rect.y+(rect.height/2)<Ojo1.y ){
                        vacio=false;
                        listacejas[nu][1]=rect.x+(rect.width/2);
                        listacejas[nu][2]=rect.y+(rect.height/2);
                        listacejas[nu][3]=rect.width;
                        listacejas[nu][4]=rect.height;
                        tamaño++;
                        //Core.rectangle(image, new Point(rect.x+(rect.width/2), rect.y+(rect.height/2)), new Point(rect.x+(rect.width/2)+5, rect.y+(rect.height/2)+5), new Scalar(0, 240, 0));
                        Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x +rect.width, rect.y +rect.height), new Scalar(0, 240, 0));
                        Highgui.imwrite(Directorio+"Cejas.png", image);
                        nu++;
                    }
                }
            }
       }
    
    
    
    public void SelecionarCejas(Point ojo1, Point ojo2){
        int diferencias[][]=new int[20][20];
            //si el numero de cejas encontrado es 1 copia caracteristicas a la otra ceja
            if(tamaño==1){
               CopiarValores(ojo1,ojo2);
            }else{
                    //Busca diferencias entre distancias y de cejas
                    for(int x=1;x<=tamaño;x++){
                      for(int y=1;y<=tamaño;y++){
                          if(x!=y){
                             if(DistanciaMinima<(Math.abs((listacejas[x][1]-listacejas[y][1])))){
                                diferencias[x][y]=Math.abs(listacejas[x][2]-listacejas[y][2]);
                            }else{
                                diferencias[x][y]=10000;
                            }
                          }
                        } 
                    }
                    //Encuentra las cejas con menor diferencia en y, verifica distancia x entre ojos
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
                    
                    
                //verifica que las cejas sean parecidas en tamaño
                if(Math.abs(listacejas[xmenor][3]-listacejas[ymenor][3])>(listacejas[xmenor][3]/3)){
                    //si no son parecidas identifica la ceja derecha y izquierda
                    if(listacejas[xmenor][1]>listacejas[ymenor][1]){
                        //Identifica la ceja menos alineada con el ojo
                        int DiferenciaCejaIzquerda=(int) (listacejas[xmenor][1]-ojo1.x);
                        int DiferenciaCejaDerecha=(int) (listacejas[ymenor][1]-ojo2.x);                        
                        //Busca la deja con menor diferencia y toma como referencia
                        if(DiferenciaCejaIzquerda>DiferenciaCejaDerecha){
                            ceja1.x=listacejas[ymenor][1];
                            ceja1.y=listacejas[ymenor][2];
                            ceja2.x=ojo2.x;//-Math.abs(listacejas[ymenor][1]-ojo2.x);
                            ceja2.y=ceja1.y;   
                        }else{
                             ceja1.x=listacejas[xmenor][1];
                             ceja1.y=listacejas[xmenor][2];
                             ceja2.x=ojo2.x+Math.abs(listacejas[xmenor][1]-ojo1.x);
                             ceja2.y=ceja1.y;
                        }
                    }else{
                         //Identifica la ceja menos alineada con el ojo
                        int DiferenciaCejaIzquerda=(int) (listacejas[ymenor][1]-ojo1.x);
                        int DiferenciaCejaDerecha=(int) (listacejas[xmenor][1]-ojo2.x);
                        //Busca la deja con menor diferencia y toma como referencia
                        if(DiferenciaCejaIzquerda>DiferenciaCejaDerecha){
                            ceja1.x=listacejas[xmenor][1];
                            ceja1.y=listacejas[xmenor][2];
                            ceja2.x=ojo2.x+Math.abs(listacejas[xmenor][1]-ojo1.x);
                            ceja2.y=ceja1.y;   
                        }else{
                             ceja1.x=listacejas[ymenor][1];
                             ceja1.y=listacejas[ymenor][2];
                             ceja2.x=ojo1.x+Math.abs(listacejas[ymenor][1]-ojo2.x);
                             ceja2.y=ceja1.y;
                        }
                    }
                    
                }else{
                    /*verifica que la menor distancia sea menor a la distancia
                        mínima*/
                    if(vmenor<DistanciaMinima){   
                    int Distancia=(int) Math.abs((listacejas[xmenor][1]-ojo1.x));
                    if(Distancia>DistanciaMinima){
                        ceja2.x=listacejas[xmenor][1];
                        ceja2.y=listacejas[xmenor][2];
                        ceja1.x=ojo1.x-Math.abs(listacejas[xmenor][1]-ojo2.x);
                        ceja1.y=ceja2.y;   
                    }else{
                        ceja1.x=listacejas[xmenor][1];
                        ceja1.y=listacejas[xmenor][2];
                        ceja2.x=ojo2.x+Distancia;
                        ceja2.y=ceja1.y;
                    }  
                }else{
                    /*identifica cual es la ceja derecha y cual la izquierda
                        y le asigna a la ceja correspondiente*/
                    if(listacejas[xmenor][1]<listacejas[ymenor][1]){
                        ceja1.x=listacejas[xmenor][1];
                        ceja1.y=listacejas[xmenor][2];
                        tamañoceja1.x=listacejas[xmenor][3];
                        tamañoceja1.y=listacejas[xmenor][4];
                        ceja2.x=listacejas[ymenor][1];
                        ceja2.y=listacejas[ymenor][2];
                        tamañoceja2.x=listacejas[ymenor][3];
                        tamañoceja2.y=listacejas[ymenor][4];
                    }else{
                        ceja2.x=listacejas[xmenor][1];
                        ceja2.y=listacejas[xmenor][2];
                        tamañoceja2.x=listacejas[ymenor][3];
                        tamañoceja2.y=listacejas[ymenor][4];
                        ceja1.x=listacejas[ymenor][1];
                        ceja1.y=listacejas[ymenor][2];
                        tamañoceja1.x=listacejas[xmenor][3];
                        tamañoceja1.y=listacejas[xmenor][4];
                    }   
                }
            }    
        }       
    }
        public Point getCeja1(){
           return ceja1;
        }
       
        public Point getCeja2(){
            return ceja2;
        }
       
        public Point getTamaño1(){
            return tamañoceja1;
        } 
       
        public Point getTamaño2(){
            return tamañoceja2;
        }     
}


