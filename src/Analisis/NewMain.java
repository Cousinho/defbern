/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analisis;

import BaseDeDatos.BDRegistros;
import Entidades.Registro;
import java.sql.SQLException;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;

/**
 *
 * @author alejandroalen
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        System.loadLibrary("opencv_java249");
        Analisis nuevo = new Analisis();
        int[] ss = nuevo.Analizar(7);
        for(int x=1;x<ss.length;x++){
            System.out.println(ss[x]);
        }
        

        //System.out.println(ss[2]);
        // System.out.println(ss[3]);
        // System.out.println(ss[4]);
        // System.out.println(ss[5]);
        // System.out.println(ss[6]);
        //System.out.println(ss[7]);
        //System.out.println(ss[8]);
        //System.out.println(ss[9]);
        //System.out.println(ss[10]);

    }
}
