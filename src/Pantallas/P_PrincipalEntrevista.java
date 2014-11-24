package Pantallas;

import Entidades.Usuario;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class P_PrincipalEntrevista extends javax.swing.JFrame {
    Usuario usuario;
    ArrayList<String> permisos = new ArrayList();
    private int CodigoGrupo=0;
    public P_PrincipalEntrevista(Usuario parametro_usuario) throws SQLException {
        this.setTitle("Sistema de Analisis");
        usuario=parametro_usuario;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        Panel_Principal = new javax.swing.JLayeredPane();
        Panel_Principal1 = new javax.swing.JLayeredPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Menu_Principal = new javax.swing.JMenuBar();
        M_Archivo = new javax.swing.JMenu();
        S_Cerrar = new javax.swing.JMenuItem();
        S_Salir = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setTitle("DEFBERN  ");

        Panel_Principal.add(Panel_Principal1);
        Panel_Principal1.setBounds(0, 0, 0, 0);

        jButton1.setText("Entrevista Individual");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Panel_Principal.add(jButton1);
        jButton1.setBounds(420, 140, 200, 110);

        jButton2.setText("Entrevista Grupal");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Panel_Principal.add(jButton2);
        jButton2.setBounds(750, 140, 210, 110);

        Menu_Principal.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                Menu_PrincipalAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        M_Archivo.setText("Archivo");

        S_Cerrar.setText("Cerrar Sesion");
        S_Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S_CerrarActionPerformed(evt);
            }
        });
        M_Archivo.add(S_Cerrar);

        S_Salir.setText("Salir");
        S_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S_SalirActionPerformed(evt);
            }
        });
        M_Archivo.add(S_Salir);

        Menu_Principal.add(M_Archivo);

        setJMenuBar(Menu_Principal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Principal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Panel_Principal, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void S_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S_SalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_S_SalirActionPerformed

    private void S_CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S_CerrarActionPerformed
         usuario=null;
         P_Login login=new P_Login();
         login.setVisible(true);
         dispose();
    }//GEN-LAST:event_S_CerrarActionPerformed

    private void Menu_PrincipalAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_Menu_PrincipalAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_Menu_PrincipalAncestorAdded

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        P_IniciarEntrevista entrevista = null;
        try {
            entrevista = new P_IniciarEntrevista(this,true,usuario);
        } catch (SQLException ex) {
            Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        Panel_Principal.add(entrevista);
        try {
            entrevista.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        entrevista.show();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                P_IniciarEntrevista entrevista = null;
        try {
            entrevista = new P_IniciarEntrevista(this,false,usuario);
        } catch (SQLException ex) {
            Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        Panel_Principal.add(entrevista);
        try {
            entrevista.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        entrevista.show();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    public void CodigoGrupo(int codigo){
        CodigoGrupo=codigo;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(P_PrincipalEntrevista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(P_PrincipalEntrevista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(P_PrincipalEntrevista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(P_PrincipalEntrevista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.loadLibrary("opencv_java249");
                Usuario user = null;
                try {
                    new P_PrincipalEntrevista(user).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(P_PrincipalEntrevista.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu M_Archivo;
    private javax.swing.JMenuBar Menu_Principal;
    private javax.swing.JLayeredPane Panel_Principal;
    private javax.swing.JLayeredPane Panel_Principal1;
    private javax.swing.JMenuItem S_Cerrar;
    private javax.swing.JMenuItem S_Salir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    // End of variables declaration//GEN-END:variables
}
