package Analisis;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

public class Rostro {
    
    //Metodo que utiliza xml
    public boolean Buscar (String Archivo,String Directorio){
    CascadeClassifier faceDetector = new CascadeClassifier("xml/Rostro.xml");
        Mat image;
        image = Highgui.imread(Archivo);
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
                Highgui.imwrite(Directorio+"Rostro.png", region.adjustROI(nu, tamaño+rect.height/8, nu, nu));
                nu++;
            }
            if(tamaño==0){
                return false;
            }else{
                return true;
            }
        }
        return false;
    }
}

