package Pantallas;


import BaseDeDatos.BDUsuarios;
import Entidades.Usuario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class P_Login extends javax.swing.JFrame {

    public P_Login() {
        initComponents();
        b_aceptar.addKeyListener(new PresionarTecla());
        //coloca ventana en valor por defecto, en el centro
        this.setLocationRelativeTo(null);
    }

     public static void main (String[] args) {
       
     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        t_contraseña = new javax.swing.JPasswordField();
        t_usuario = new javax.swing.JTextField();
        b_cancelar = new javax.swing.JButton();
        b_aceptar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        b_cancelar.setText("Cancelar");
        b_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cancelarActionPerformed(evt);
            }
        });

        b_aceptar.setText("Aceptar");
        b_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_aceptarActionPerformed(evt);
            }
        });

        jLabel2.setText("Contraseña");

        jLabel1.setText("Usuario");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_aceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addComponent(b_cancelar))
                    .addComponent(t_usuario, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t_contraseña, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t_usuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(t_contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_cancelar)
                    .addComponent(b_aceptar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public class PresionarTecla extends KeyAdapter {

      //al presionar enter presiona boton aceptar  
      public void keyPressed(KeyEvent ke) {
          if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
              b_aceptarActionPerformed(null);
          }
      }
      
      
}
     
    private void b_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_aceptarActionPerformed
        //obtiene los valores de campos usuario y contraseña
        String nombre_usuario=t_usuario.getText();
        String contraseña=t_contraseña.getText();
        //verifica que no esten vacios
        if((nombre_usuario.isEmpty())||(contraseña.isEmpty()))
        {
            JOptionPane.showMessageDialog(null, "Ingrese su nombre de usuario y contraseña");
        }
        else
        {
            //crea un nuevo usuario
            Usuario usuario=new Usuario() {};
            try {
                //busca en la base de datos si existe un usuario con ese nombre
                usuario=BDUsuarios.buscarUsuario(nombre_usuario);
            } catch (SQLException ex) {
                Logger.getLogger(P_Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            //si encontro algo en la base de datos
            if(usuario!=null){
                //si datos coinciden inicia pantalla principal
                if (nombre_usuario.equals(usuario.getNombre()) && contraseña.equals(usuario.getContrasenha())) {
                    P_Principal Principal = new P_Principal(usuario) {};
                    Principal.setSize(600, 600);
                    Principal.setExtendedState(MAXIMIZED_BOTH);
                    Principal.setVisible(true);
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "La contraseña ingresada es incorrecta");
                }

            }
            else{
                JOptionPane.showMessageDialog(null, "El Usuario ingresado es incorrecto");
            }
    }//GEN-LAST:event_b_aceptarActionPerformed
} 
    private void b_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelarActionPerformed
        dispose();
    }//GEN-LAST:event_b_cancelarActionPerformed

    private void b_aceptarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_aceptarKeyPressed
    }//GEN-LAST:event_b_aceptarKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_aceptar;
    private javax.swing.JButton b_cancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField t_contraseña;
    private javax.swing.JTextField t_usuario;
    // End of variables declaration//GEN-END:variables
}
