/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Analisis;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
/**
 *
 * @author alejandroalen
 */
public class Linea {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                System.loadLibrary("opencv_java249");

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
        Rostro AnalisisRostro=new Rostro();
        Cejas AnalisisCejas=new Cejas();
        Ojos AnalisisOjos=new Ojos();
        Nariz AnalisisNariz=new Nariz();
        Boca AnalisisBoca=new Boca();
        String Directorio="D:\\Proyectos\\Reconocedor\\Codigo\\Reconocimiento1\\imagen\\Muestras\\1\\0";
        String Archivo="D:\\Proyectos\\Reconocedor\\Codigo\\Reconocimiento1\\imagen\\Muestras\\1\\0\\5.png";
        AnalisisRostro.Buscar(Archivo, Directorio, true);
        AnalisisOjos.Analizar("D:\\Proyectos\\Reconocedor\\Codigo\\Reconocimiento1\\imagen\\Muestras\\1\\0Rostro.png", "D:\\Proyectos\\Reconocedor\\Codigo\\Reconocimiento1\\imagen\\Muestras\\1\\");
        ojo1=AnalisisOjos.getOjo1();
        ojo2=AnalisisOjos.getOjo2();
        Point tamañoojo = AnalisisOjos.getTamaño1();
        AnalisisNariz.Analizar("D:\\Proyectos\\Reconocedor\\Codigo\\Reconocimiento1\\imagen\\Muestras\\1\\0Rostro.png", "D:\\Proyectos\\Reconocedor\\Codigo\\Reconocimiento1\\imagen\\Muestras\\1\\",ojo1,ojo2);
        nariz=AnalisisNariz.getNariz();
        AnalisisCejas.Analizar("D:\\Proyectos\\Reconocedor\\Codigo\\Reconocimiento1\\imagen\\Muestras\\1\\0Rostro.png", "D:\\Proyectos\\Reconocedor\\Codigo\\Reconocimiento1\\imagen\\Muestras\\1\\",ojo1,ojo2,tamañoojo);
        ceja1=AnalisisCejas.getCeja1();
        ceja2=AnalisisCejas.getCeja2();
        AnalisisBoca.Analizar("D:\\Proyectos\\Reconocedor\\Codigo\\Reconocimiento1\\imagen\\Muestras\\1\\0Rostro.png", "D:\\Proyectos\\Reconocedor\\Codigo\\Reconocimiento1\\imagen\\Muestras\\1\\",nariz,ojo1,ojo2,tamañoojo);
        boca=AnalisisBoca.getBoca();
        tamañoboca=AnalisisBoca.getTamaño();
        Mat image;
        image = Highgui.imread("D:\\Proyectos\\Reconocedor\\Codigo\\Reconocimiento1\\imagen\\Muestras\\1\\0Rostro.png");
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
        //Core.rectangle(image, boca, new Point(boca.x+tamañoboca.x,boca.y+tamañoboca.y), new Scalar(0,240,0));
        Core.rectangle(image, new Point(boca.x-tamañoboca.x/2 , boca.y-tamañoboca.y/2) , new Point(boca.x+tamañoboca.x/2, boca.y+tamañoboca.y/2),
        new Scalar(0, 240, 0));
        Highgui.imwrite("imagen/Muestras/1/Linea.png", image);
        System.out.println(tamañoboca.x);
        System.out.println(Distancia(ojo1,ojo2));
    }
    private static double Distancia(Point x1, Point x2){
        double Distancia = Math.sqrt( Math.pow((x1.x-x2.x),2)+ Math.pow((x1.y-x2.y),2));
        return Distancia;
    }
    
}
