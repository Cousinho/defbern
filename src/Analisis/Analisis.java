package Analisis;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;

public class Analisis {
    double distancia11, distancia12;
    double distancia21, distancia22;
    double distancia31, distancia32;
    double distancia41, distancia42;
    double distancia51, distancia52;
    double distancia61, distancia62;
    double distancia71, distancia72;
    boolean primero=true;
                            
    private String nombre;
    Point nariz = new Point();
    Point ojo1 = new Point();
    Point tamañoojo1 = new Point();
    Point ojo2 = new Point();
    Point tamañoojo2 = new Point();
    Point boca = new Point();
    Point tamañoboca = new Point();
    Point ceja1 = new Point();
    Point ceja2 = new Point();
    Point tamañoceja1 = new Point();
    Point tamañoceja2 = new Point();
    int ojox1 = 0;
    int listaojos[][] = new int[20][20];
    int listabocas[][] = new int[20][20];
    int ojox2 = 0;
    int ojoy1 = 0;
    int ojoy2 = 0;
    int bocay = 0;
    int alto = 0;
    int ancho = 0;
    String NombreArchivo = "";      
    String NombreMuestra="";    
    public void run() {
        for (int x = 1; x <= 8;x++) {
            NombreArchivo = "Rostro"+String.valueOf(x);
            NombreMuestra=String.valueOf(x);
            Rostro SelectorRostro = new Rostro();
            SelectorRostro.Buscar(NombreMuestra);
            Ojos selectorojos   = new Ojos();
            selectorojos.Analizar(NombreArchivo);
            ojo1 = selectorojos.getOjo1(    );
            ojo2 = selectorojos.getOjo2();
            tamañoojo1 = selectorojos.getTamaño1();
            tamañoojo2 = selectorojos.getTamaño2();
            Cejas selectorcejas = new Cejas();
            selectorcejas.Analizar(NombreArchivo,ojo1,ojo2,tamañoojo1);
            ceja1=selectorcejas.getCeja1();
            ceja2=selectorcejas.getCeja2();
            tamañoceja1=selectorcejas.getTamaño1();
            tamañoceja2=selectorcejas.getTamaño2();
            Nariz selectornariz = new Nariz();
            nariz = selectornariz.Analizar(ojo1, ojo2, NombreArchivo);
            Boca selectorboca = new Boca();
            selectorboca.Analizar(nariz, ojo1, ojo2, NombreArchivo,tamañoojo1);
            boca = selectorboca.Boca();
            tamañoboca = selectorboca.Tamaño();
            if(ojo1.x!=0 && ojo2.x!=0 && ceja1.x!=0 && ceja2.x!=0 && boca.x!=0){
                linea();
                   CalcularDistancia();
                //System.out.println(x);
            }else{
                linea2();
                CalcularDistancia();
            }
        }

    }

    public void linea() {
        Mat image;
        image = Highgui.imread("imagen/Rostros/" + NombreArchivo + ".png");
        Core.line(image, ceja1 ,ceja2, new Scalar(0, 240, 0));
        Core.line(image, ceja1 ,nariz, new Scalar(0, 240, 0));
        Core.line(image, ceja2, nariz, new Scalar(0, 240, 0));
        Core.line(image, ojo1, ojo2, new Scalar(0, 240, 0));
        Core.line(image, ojo1, boca, new Scalar(0, 240, 0));
        Core.line(image, ojo1, nariz, new Scalar(0, 240, 0));
        Core.line(image, ojo2, boca, new Scalar(0, 240, 0));
        Core.line(image, ojo2, nariz, new Scalar(0, 240, 0));
        Core.line(image, ojo1, ceja1, new Scalar(0, 240, 0));
        Core.line(image, ojo2, ceja2, new Scalar(0, 240, 0));
        Core.line(image, nariz, boca, new Scalar(0, 240, 0));
        Core.rectangle(image, new Point(boca.x-tamañoboca.x/2 , boca.y-tamañoboca.y) , new Point(boca.x+tamañoboca.x/2, boca.y), new Scalar(0, 240, 0)); 
        Core.rectangle(image, new Point (ojo1.x-(tamañoojo1.x/2), ojo1.y-(tamañoojo1.y/2)),new Point (ojo1.x+(tamañoojo1.x/2), ojo1.y+(tamañoojo1.y/2)) , new Scalar(0, 240, 0));
        Core.rectangle(image, new Point (ojo2.x-(tamañoojo2.x/2), ojo2.y-(tamañoojo2.y/2)),new Point (ojo2.x+(tamañoojo2.x/2), ojo2.y+(tamañoojo2.y/2)) , new Scalar(0, 240, 0));
        Highgui.imwrite("imagen/linea/" + NombreArchivo + ".png", image);
    }
    
    public void CalcularDistancia() {
       if(primero){
           double P=Distancia(ojo1,ojo2);
           distancia11=P/tamañoboca.x;
           distancia21=P/tamañoojo1.x;
           distancia31=P/((Distancia(ojo1,boca)+Distancia(ojo1,boca))/2);
           distancia41=P/((Distancia(ceja1,boca)+Distancia(ceja2,boca))/2);
           distancia51=P/((Distancia(ojo1,ceja1)+Distancia(ojo2,ceja2))/2);
           distancia61=P/((Distancia(ceja1,nariz)+Distancia(ceja2,nariz))/2);
           primero=false;
       }else{
           double P=Distancia(ojo1,ojo2);
           distancia12=P/tamañoboca.x;
           distancia22=P/tamañoojo1.x;
           distancia32=P/((Distancia(ojo1,boca)+Distancia(ojo1,boca))/2);
           distancia42=P/((Distancia(ceja1,boca)+Distancia(ceja2,boca))/2);
           distancia52=P/((Distancia(ojo1,ceja1)+Distancia(ojo2,ceja2))/2);
           distancia62=P/((Distancia(ceja1,nariz)+Distancia(ceja2,nariz))/2);
           primero=true;
           Imprimir();
       }
       
        
    }
    public void Imprimir(){
       System.out.print(" "+(distancia11/distancia12));
       System.out.print(" "+(distancia21/distancia22));
       System.out.print(" "+(distancia31/distancia32));
       System.out.print(" "+(distancia41/distancia42));
       System.out.print(" "+(distancia51/distancia52));
       System.out.print(" "+(distancia61/distancia62));
       System.out.println();
         
    }
    public double Distancia(Point x1, Point x2){
        double Distancia = Math.sqrt( Math.pow((x1.x-x2.x),2)+ Math.pow((x1.y-x2.y),2));
        return Distancia;
    }
    
    public void linea2() {
        Mat image;
        image = Highgui.imread("imagen/Rostros/" + NombreArchivo + ".png");
        Core.line(image, ceja1 ,ceja2, new Scalar(0, 240, 0));
        Core.line(image, ceja1 ,nariz, new Scalar(0, 240, 0));
        Core.line(image, ceja2, nariz, new Scalar(0, 240, 0));
        Core.line(image, ojo1, ojo2, new Scalar(0, 240, 0));
        Core.line(image, ojo1, boca, new Scalar(0, 240, 0));
        Core.line(image, ojo1, nariz, new Scalar(0, 240, 0));
        Core.line(image, ojo2, boca, new Scalar(0, 240, 0));
        Core.line(image, ojo2, nariz, new Scalar(0, 240, 0));
        Core.line(image, ojo1, ceja1, new Scalar(0, 240, 0));
        Core.line(image, ojo2, ceja2, new Scalar(0, 240, 0));
        Core.line(image, nariz, boca, new Scalar(0, 240, 0));
        Core.rectangle(image, new Point(boca.x-tamañoboca.x/2 , boca.y-tamañoboca.y/2) , new Point(boca.x+tamañoboca.x/2, boca.y+tamañoboca.y/2), 
        new Scalar(0, 240, 0));
        Highgui.imwrite("imagen/error/" + NombreArchivo + ".png", image);

    }

    public static void main(String[] args) {
        System.loadLibrary("opencv_java249");
        new Analisis().run();

    }
}
