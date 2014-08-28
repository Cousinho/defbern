/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Prueba;

import BaseDeDatos.BDOpciones;
import Entidades.Opciones;
import java.sql.SQLException;

/**
 *
 * @author jose
 */



public class pruebaopciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        int i = 1;
        Opciones op = new Opciones();
        BDOpciones opciones = new BDOpciones();
        System.out.println(opciones.buscarId(i));
        System.out.println(op.getLista());
    }
    
}
