package Pantallas;

import Entidades.Usuario;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alejandroalen
 */
public class P_Principal extends javax.swing.JFrame {
    Usuario usuario;

    /**
     *
     * @param parametro_usuario
     */
    public P_Principal(Usuario parametro_usuario) {
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
        Menu_Principal = new javax.swing.JMenuBar();
        M_Archivo = new javax.swing.JMenu();
        S_Cerrar = new javax.swing.JMenuItem();
        S_Salir = new javax.swing.JMenuItem();
        M_Administrar = new javax.swing.JMenu();
        SM_Entrevista = new javax.swing.JMenuItem();
        SM_Lamina = new javax.swing.JMenuItem();
        SM_Perfil = new javax.swing.JMenuItem();
        SM_Usuario = new javax.swing.JMenuItem();
        SM_Registro = new javax.swing.JMenuItem();
        M_Entrevista = new javax.swing.JMenu();
        SM_Iniciar_Entrevista = new javax.swing.JMenuItem();
        S_EntrevistaGrupal = new javax.swing.JMenuItem();
        S_EntrevistaIndividual = new javax.swing.JMenuItem();
        M_Informes = new javax.swing.JMenu();
        SM_Informes = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setTitle("DEFBERN  ");

        Panel_Principal.add(Panel_Principal1);
        Panel_Principal1.setBounds(0, 0, 0, 0);

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

        M_Administrar.setText("Administrar");

        SM_Entrevista.setText("Entrevista");
        SM_Entrevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_EntrevistaActionPerformed(evt);
            }
        });
        M_Administrar.add(SM_Entrevista);

        SM_Lamina.setText("Lamina");
        SM_Lamina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_LaminaActionPerformed(evt);
            }
        });
        M_Administrar.add(SM_Lamina);

        SM_Perfil.setText("Perfil");
        SM_Perfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_PerfilActionPerformed(evt);
            }
        });
        M_Administrar.add(SM_Perfil);

        SM_Usuario.setText("Usuario");
        SM_Usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_UsuarioActionPerformed(evt);
            }
        });
        M_Administrar.add(SM_Usuario);

        SM_Registro.setText("Registro");
        SM_Registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_RegistroActionPerformed(evt);
            }
        });
        M_Administrar.add(SM_Registro);

        Menu_Principal.add(M_Administrar);

        M_Entrevista.setText("Entrevista");

        SM_Iniciar_Entrevista.setText("Iniciar Entrevista");
        SM_Iniciar_Entrevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_Iniciar_EntrevistaActionPerformed(evt);
            }
        });
        M_Entrevista.add(SM_Iniciar_Entrevista);

        S_EntrevistaGrupal.setText("Entrevista Grupal");
        S_EntrevistaGrupal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S_EntrevistaGrupalActionPerformed(evt);
            }
        });
        M_Entrevista.add(S_EntrevistaGrupal);

        S_EntrevistaIndividual.setText("Entrevista Individual");
        S_EntrevistaIndividual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S_EntrevistaIndividualActionPerformed(evt);
            }
        });
        M_Entrevista.add(S_EntrevistaIndividual);

        Menu_Principal.add(M_Entrevista);

        M_Informes.setText("Informes");

        SM_Informes.setText("Crear Informes");
        M_Informes.add(SM_Informes);

        Menu_Principal.add(M_Informes);

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
         dispose();
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

    private void SM_LaminaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SM_LaminaActionPerformed
        P_Lamina pantalla_lamina=new P_Lamina(this);
        Panel_Principal.add(pantalla_lamina);
        try {
            pantalla_lamina.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        pantalla_lamina.show();
    }//GEN-LAST:event_SM_LaminaActionPerformed

    private void SM_EntrevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SM_EntrevistaActionPerformed
        P_Entrevista pantalla_entrevista=new P_Entrevista(this);
        Panel_Principal.add(pantalla_entrevista);
        try {
            pantalla_entrevista.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        pantalla_entrevista.show();

    }//GEN-LAST:event_SM_EntrevistaActionPerformed

    private void SM_Iniciar_EntrevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SM_Iniciar_EntrevistaActionPerformed
        P_TipoEntrevista principal = null;
        principal = new P_TipoEntrevista(this);
        Panel_Principal.add(principal);
        try {
            principal.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        principal.setVisible(true);
    }//GEN-LAST:event_SM_Iniciar_EntrevistaActionPerformed

    private void SM_PerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SM_PerfilActionPerformed
        P_Perfil principal = null;
        principal = new P_Perfil(this);
        Panel_Principal.add(principal);
        try {
            principal.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        principal.show();
    }//GEN-LAST:event_SM_PerfilActionPerformed

    private void SM_UsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SM_UsuarioActionPerformed
        P_Usuario principal = null;
        principal = new P_Usuario(this);
        Panel_Principal.add(principal);
        try {
            principal.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        principal.show();
    }//GEN-LAST:event_SM_UsuarioActionPerformed

    private void S_EntrevistaIndividualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S_EntrevistaIndividualActionPerformed
        P_IniciarEntrevista entrevista = null;
        try {
            entrevista = new P_IniciarEntrevista(this,false);
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
    }//GEN-LAST:event_S_EntrevistaIndividualActionPerformed

    private void S_EntrevistaGrupalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S_EntrevistaGrupalActionPerformed
        P_IniciarEntrevista entrevista = null;
        try {
            entrevista = new P_IniciarEntrevista(this,true);
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
    }//GEN-LAST:event_S_EntrevistaGrupalActionPerformed

    private void SM_RegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SM_RegistroActionPerformed
        P_Registro principal = null;
        principal = new P_Registro(this);
        Panel_Principal.add(principal);
        try {
            principal.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        principal.show();
    }//GEN-LAST:event_SM_RegistroActionPerformed

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
            java.util.logging.Logger.getLogger(P_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(P_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(P_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(P_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.loadLibrary("opencv_java249");
                Usuario user = null;
                new P_Principal(user).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu M_Administrar;
    private javax.swing.JMenu M_Archivo;
    private javax.swing.JMenu M_Entrevista;
    private javax.swing.JMenu M_Informes;
    private javax.swing.JMenuBar Menu_Principal;
    private javax.swing.JLayeredPane Panel_Principal;
    private javax.swing.JLayeredPane Panel_Principal1;
    private javax.swing.JMenuItem SM_Entrevista;
    private javax.swing.JMenuItem SM_Informes;
    private javax.swing.JMenuItem SM_Iniciar_Entrevista;
    private javax.swing.JMenuItem SM_Lamina;
    private javax.swing.JMenuItem SM_Perfil;
    private javax.swing.JMenuItem SM_Registro;
    private javax.swing.JMenuItem SM_Usuario;
    private javax.swing.JMenuItem S_Cerrar;
    private javax.swing.JMenuItem S_EntrevistaGrupal;
    private javax.swing.JMenuItem S_EntrevistaIndividual;
    private javax.swing.JMenuItem S_Salir;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    // End of variables declaration//GEN-END:variables
}
