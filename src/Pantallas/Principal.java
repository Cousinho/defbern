package Pantallas;

import BaseDeDatos.BDUsuarios;
import Entidades.Usuario;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.sql.SQLException;

public class Principal {

    public static void main(String[] args) throws SQLException {
        Usuario usuario= new Usuario();
        usuario=BDUsuarios.buscarId(1);
        P_Principal principal=new P_Principal(usuario);
        principal.setSize(600, 600);
        principal.setExtendedState(MAXIMIZED_BOTH);
        principal.setVisible(true);
        
    }
    
}
