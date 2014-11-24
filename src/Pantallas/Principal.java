package Pantallas;

import BaseDeDatos.BDUsuarios;
import java.sql.SQLException;

public class Principal {

    public static void main(String[] args) throws SQLException {
        P_Login login =new P_Login();
        login.setLocationRelativeTo(null);
        login.setResizable(false);
        login.setVisible(true);
        
    }
    
}
