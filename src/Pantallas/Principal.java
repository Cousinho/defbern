package Pantallas;

import BaseDeDatos.BDUsuarios;
import java.sql.SQLException;

public class Principal {

    public static void main(String[] args) throws SQLException {
        P_Principal principal=new P_Principal(BDUsuarios.buscarId(1));
        principal.setVisible(true);
        
    }
    
}
