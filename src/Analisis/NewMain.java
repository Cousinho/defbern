/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analisis;

import BaseDeDatos.BDRegistros;
import java.sql.SQLException;

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
        //double ss=0.9999997934462066;
        //double sss=7.232670718317902E-7;
        //System.out.println(sss<ss);
        /*Analisis s = new Analisis();
        int[] ss = s.Analizar(5);
        System.out.println(ss[1]);
        System.out.println(ss[2]);
        System.out.println(ss[3]);
        System.out.println(ss[4]);
    System.out.println(ss[5]);
        System.out.println(ss[6]);
        System.out.println(ss[7]);
        System.out.println(ss[8]);
        System.out.println(ss[9]);
        System.out.println(ss[10]);
        System.out.println(ss[0]);
        */
        AnalisisRorschach ro=new AnalisisRorschach();
        String s=ro.AnalizarRegistro(BDRegistros.buscarId(5));
        System.out.println(s);
        
    }
}
