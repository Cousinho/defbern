/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos;

import Entidades.Registro;
import java.sql.SQLException;

/**
 *
 * @author alejandroalen
 */
public class Registros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Registro re=new Registro();
        re=BDRegistros.buscarId(2);
        System.out.println(re.getRespuestas().length);
        System.out.println(re.getRespuestas()[0]);
        System.out.println(re.getRespuestas()[1]);
        System.out.println(re.getRespuestas()[2]);
        
       }
    
}
