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
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        t_contraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_contraseñaKeyPressed(evt);
            }
        });

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

        jButton1.setText("Sin Usuario");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
                        .addGap(58, 58, 58)
                        .addComponent(b_cancelar)
                        .addGap(50, 50, 50)
                        .addComponent(jButton1)
                        .addGap(6, 6, 6))
                    .addComponent(t_usuario, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t_contraseña, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_aceptar)
                    .addComponent(b_cancelar)
                    .addComponent(jButton1))
                .addContainerGap(23, Short.MAX_VALUE))
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
                if(usuario.getCambio()==1){
                        P_CambioContrasenha cambiar = null;
                        cambiar = new P_CambioContrasenha(usuario);
                        cambiar.setLocationRelativeTo(null);
                        cambiar.setResizable(false);
                        cambiar.setVisible(true);
                        this.dispose();
                }else{
                    if (nombre_usuario.equals(usuario.getNombre()) && contraseña.equals(usuario.getContrasenha())) {
                        try {
                            P_Principal Principal = new P_Principal(usuario) ;
                            Principal.setSize(600, 600);
                            Principal.setExtendedState(MAXIMIZED_BOTH);
                            Principal.setVisible(true);
                            this.dispose();
                        } catch (SQLException ex) {
                            Logger.getLogger(P_Login.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "El Usuario o la contraseña son incorrectos");
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "El Usuario o la contraseña son incorrectos");
            }
    }//GEN-LAST:event_b_aceptarActionPerformed
} 
    private void b_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_b_cancelarActionPerformed

    private void b_aceptarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_aceptarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
              b_aceptarActionPerformed(null);
        }
    }//GEN-LAST:event_b_aceptarKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        P_PrincipalEntrevista Principal = null ;
        try {
            Principal = new P_PrincipalEntrevista(BDUsuarios.buscarId(0));
        } catch (SQLException ex) {
            Logger.getLogger(P_Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        Principal.setExtendedState(MAXIMIZED_BOTH);
        Principal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void t_contraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_contraseñaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
              b_aceptarActionPerformed(null);
        }

    }//GEN-LAST:event_t_contraseñaKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_aceptar;
    private javax.swing.JButton b_cancelar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField t_contraseña;
    private javax.swing.JTextField t_usuario;
    // End of variables declaration//GEN-END:variables
}
