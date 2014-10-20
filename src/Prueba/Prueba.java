package Prueba;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;

public class Prueba {
    
    public static void main(String[] args) {
        System.loadLibrary("opencv_java249");
        Rostro rostro=new Rostro();
        rostro.Buscar("imagen/Muestras/45/1.png", "imagen/Muestras/45/");
        Ojos ojos=new Ojos();   
        ojos.Analizar("imagen/Muestras/45/Rostro.png", "imagen/Muestras/45/");
        Nariz nariz=new Nariz();
        nariz.Analizar("imagen/Muestras/45/Rostro.png", "imagen/Muestras/45/", ojos.getOjo1(), ojos.getOjo2());
        Cejas cejas=new Cejas();
        cejas.Analizar("imagen/Muestras/45/Rostro.png", "imagen/Muestras/45/", ojos.getOjo1(), ojos.getOjo2(),ojos.getTamaño1());
        Boca boca=new Boca();
        boca.Analizar("imagen/Muestras/45/Rostro.png", "imagen/Muestras/45/", nariz.getNariz(), ojos.getOjo1(), ojos.getOjo2(), ojos.getTamaño1());
        Mat image;
        MatOfRect faceDetections;
        image = Highgui.imread("imagen/Muestras/45/Rostro.png");
        Core.rectangle(image, nariz.getNariz(),new Point(nariz.getNariz().x+5  ,nariz.getNariz().y+5), new Scalar(0, 240, 0));
        Highgui.imwrite("imagen/Muestras/45/Resultado.png", image);

    }
    
    
}
