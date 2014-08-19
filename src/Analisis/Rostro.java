package Analisis;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

public class Rostro {
    
    //Metodo que utiliza xml
    public void Buscar (String numero){
    CascadeClassifier faceDetector = new CascadeClassifier("xml/Rostro.xml");
        Mat image;
        image = Highgui.imread("imagen/Muestras/"+numero+".png");
        MatOfRect faceDetections;
        String nombre="Rostro";
        faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);   
        int tamaño = faceDetections.toArray().length;
        for (Rect rect : faceDetections.toArray()) {
            Mat region = new Mat(image, rect);
            int nu = 1;
            while (nu <= tamaño) {
                //Genera una imagen de muestra
                Highgui.imwrite("imagen/Rostros/"+nombre+numero+".png", region.adjustROI(nu, tamaño+rect.height/5, nu, nu));
                nu++;
            }
        }
    }    
}
