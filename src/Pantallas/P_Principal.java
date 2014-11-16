package Pantallas;

import BaseDeDatos.BDPermisos;
import Entidades.Permiso;
import Entidades.Usuario;
import Reportes.P_Reportes;
import Reportes.P_ReportesGrupos;
import java.awt.Desktop;
import java.beans.PropertyVetoException;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class P_Principal extends javax.swing.JFrame {
    Usuario usuario;
    ArrayList<String> permisos = new ArrayList();
    private int CodigoGrupo=0;
    public P_Principal(Usuario parametro_usuario) throws SQLException {
        System.loadLibrary("opencv_java249");
        this.setTitle("Sistema de Analisis");
        this.setExtendedState(MAXIMIZED_BOTH);
        usuario=parametro_usuario;
        initComponents();
        BloquearComponentes();
        HabilitarComponentes();
    }

    private void HabilitarComponentes() throws SQLException{
        for (Iterator<Permiso> it = BDPermisos.Lista(usuario.getRol().getCodigo()) .iterator(); it.hasNext();) {
                Permiso permiso = it.next();
                permisos.add(permiso.getDescripcion());
        }
        if(permisos.indexOf("configurar conexion")!=-1){
            M_Configuracion.setVisible(true);
        }
        if(permisos.indexOf("agregar entrevistas")!=-1 || permisos.indexOf("modificar entrevistas")!=-1 || permisos.indexOf("eliminar entrevistas")!=-1){
            SM_Entrevista.setVisible(true);
        }
        if(permisos.indexOf("agregar laminas")!=-1 || permisos.indexOf("modificar laminas")!=-1 || permisos.indexOf("eliminar laminas")!=-1){
            SM_Lamina.setVisible(true);
        }
        if(permisos.indexOf("agregar perfiles")!=-1 || permisos.indexOf("modificar perfiles")!=-1 || permisos.indexOf("eliminar perfiles")!=-1){
            SM_Perfil.setVisible(true);
        }
        if(permisos.indexOf("agregar usuarios")!=-1 || permisos.indexOf("modificar usuarios")!=-1 || permisos.indexOf("eliminar usuarios")!=-1){
            SM_Usuario.setVisible(true);
        }
        if(permisos.indexOf("modificar registros")!=-1 || permisos.indexOf("eliminar registros")!=-1){
            SM_Registro.setVisible(true);
        }
        if(permisos.indexOf("agregar grupos")!=-1 || permisos.indexOf("modificar grupos")!=-1 || permisos.indexOf("eliminar grupos")!=-1){
            SM_Grupos.setVisible(true);
        }
        if(permisos.indexOf("agregar roles")!=-1 || permisos.indexOf("modificar roles")!=-1 || permisos.indexOf("eliminar roles")!=-1){
            SM_Rol.setVisible(true);
        }
        if(permisos.indexOf("crear informes individuales")!=-1){
            SM_Informes.setVisible(true);
        }
        if(permisos.indexOf("crear informes grupales")!=-1){
            SM_Grupales.setVisible(true);
        }
        if(permisos.indexOf("realizar entrevista")!=-1){
            SM_EntrevistaIndividual.setVisible(true);
            SM_EntrevistaGrupal.setVisible(true);
        }
    }
    
    private void BloquearComponentes(){
        M_Configuracion.setVisible(false);
        SM_Entrevista.setVisible(false);
        SM_Lamina.setVisible(false);
        SM_Perfil.setVisible(false);
        SM_Usuario.setVisible(false);
        SM_Registro.setVisible(false);
        SM_Grupos.setVisible(false);
        SM_Rol.setVisible(false);
        SM_Grupales.setVisible(false);
        SM_Informes.setVisible(false);
        SM_EntrevistaIndividual.setVisible(false);
        SM_EntrevistaGrupal.setVisible(false);
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
        SM_Grupos = new javax.swing.JMenuItem();
        SM_Rol = new javax.swing.JMenuItem();
        SM_Registro = new javax.swing.JMenuItem();
        SM_RegistroSinAnalizar = new javax.swing.JMenuItem();
        M_Entrevista = new javax.swing.JMenu();
        SM_EntrevistaGrupal = new javax.swing.JMenuItem();
        SM_EntrevistaIndividual = new javax.swing.JMenuItem();
        M_Informes = new javax.swing.JMenu();
        SM_Grupales = new javax.swing.JMenuItem();
        SM_Informes = new javax.swing.JMenuItem();
        M_Configuracion = new javax.swing.JMenu();
        SM_Conexion = new javax.swing.JMenuItem();
        M_Ayuda = new javax.swing.JMenu();
        SM_Ayuda = new javax.swing.JMenuItem();

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

        SM_Entrevista.setText("Entrevistas");
        SM_Entrevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_EntrevistaActionPerformed(evt);
            }
        });
        M_Administrar.add(SM_Entrevista);

        SM_Lamina.setText("Laminas");
        SM_Lamina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_LaminaActionPerformed(evt);
            }
        });
        M_Administrar.add(SM_Lamina);

        SM_Perfil.setText("Perfiles");
        SM_Perfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_PerfilActionPerformed(evt);
            }
        });
        M_Administrar.add(SM_Perfil);

        SM_Usuario.setText("Usuarios");
        SM_Usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_UsuarioActionPerformed(evt);
            }
        });
        M_Administrar.add(SM_Usuario);

        SM_Grupos.setText("Grupos");
        SM_Grupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_GruposActionPerformed(evt);
            }
        });
        M_Administrar.add(SM_Grupos);

        SM_Rol.setText("Rol");
        SM_Rol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_RolActionPerformed(evt);
            }
        });
        M_Administrar.add(SM_Rol);

        SM_Registro.setText("Registros");
        SM_Registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_RegistroActionPerformed(evt);
            }
        });
        M_Administrar.add(SM_Registro);

        SM_RegistroSinAnalizar.setText("Registros sin Analizar");
        SM_RegistroSinAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_RegistroSinAnalizarActionPerformed(evt);
            }
        });
        M_Administrar.add(SM_RegistroSinAnalizar);

        Menu_Principal.add(M_Administrar);

        M_Entrevista.setText("Entrevista");

        SM_EntrevistaGrupal.setText("Entrevista Grupal");
        SM_EntrevistaGrupal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_EntrevistaGrupalActionPerformed(evt);
            }
        });
        M_Entrevista.add(SM_EntrevistaGrupal);

        SM_EntrevistaIndividual.setText("Entrevista Individual");
        SM_EntrevistaIndividual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_EntrevistaIndividualActionPerformed(evt);
            }
        });
        M_Entrevista.add(SM_EntrevistaIndividual);

        Menu_Principal.add(M_Entrevista);

        M_Informes.setText("Informes");

        SM_Grupales.setText("Crear Informes Grupales");
        SM_Grupales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_GrupalesActionPerformed(evt);
            }
        });
        M_Informes.add(SM_Grupales);

        SM_Informes.setText("Crear Informes Individuales");
        SM_Informes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_InformesActionPerformed(evt);
            }
        });
        M_Informes.add(SM_Informes);

        Menu_Principal.add(M_Informes);

        M_Configuracion.setText("Configuración");

        SM_Conexion.setText("Conexión");
        SM_Conexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_ConexionActionPerformed(evt);
            }
        });
        M_Configuracion.add(SM_Conexion);

        Menu_Principal.add(M_Configuracion);

        M_Ayuda.setText("Ayuda");
        M_Ayuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M_AyudaActionPerformed(evt);
            }
        });

        SM_Ayuda.setText("Contenido de Ayuda");
        SM_Ayuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SM_AyudaActionPerformed(evt);
            }
        });
        M_Ayuda.add(SM_Ayuda);

        Menu_Principal.add(M_Ayuda);

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
    }//GEN-LAST:event_Menu_PrincipalAncestorAdded

    private void SM_LaminaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SM_LaminaActionPerformed
        P_Lamina pantalla_lamina=new P_Lamina(this, permisos);
        Panel_Principal.add(pantalla_lamina);
        try {
            pantalla_lamina.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        pantalla_lamina.show();
    }//GEN-LAST:event_SM_LaminaActionPerformed

    private void SM_EntrevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SM_EntrevistaActionPerformed
        P_Entrevista pantalla_entrevista=new P_Entrevista(this, permisos);
        Panel_Principal.add(pantalla_entrevista);
        try {
            pantalla_entrevista.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        pantalla_entrevista.show();

    }//GEN-LAST:event_SM_EntrevistaActionPerformed

    private void SM_PerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SM_PerfilActionPerformed
        P_Perfil principal = null;
        principal = new P_Perfil(this, permisos);
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
        principal = new P_Usuario(this,permisos);
        Panel_Principal.add(principal);
        try {
            principal.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        principal.show();
    }//GEN-LAST:event_SM_UsuarioActionPerformed

    private void SM_EntrevistaIndividualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SM_EntrevistaIndividualActionPerformed
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
    }//GEN-LAST:event_SM_EntrevistaIndividualActionPerformed

    private void SM_EntrevistaGrupalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SM_EntrevistaGrupalActionPerformed
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
    }//GEN-LAST:event_SM_EntrevistaGrupalActionPerformed

    private void SM_RegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SM_RegistroActionPerformed
        P_Registro principal = null;
        principal = new P_Registro(this, permisos);
        Panel_Principal.add(principal);
        try {
            principal.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        principal.show();
    }//GEN-LAST:event_SM_RegistroActionPerformed

    private void SM_GruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SM_GruposActionPerformed
        P_Grupos grupo = null;
        grupo = new P_Grupos(this, permisos);
        Panel_Principal.add(grupo);
        try {
            grupo.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        grupo.show();

    }//GEN-LAST:event_SM_GruposActionPerformed

    private void SM_RolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SM_RolActionPerformed
        P_Rol principal = null;
        principal = new P_Rol(this,permisos);
        Panel_Principal.add(principal);
        try {
            principal.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        principal.show();
    }//GEN-LAST:event_SM_RolActionPerformed

    private void SM_InformesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SM_InformesActionPerformed
        P_Reportes principal = null;
        principal = new P_Reportes();
        Panel_Principal.add(principal);
        try {
            principal.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        principal.show();
    }//GEN-LAST:event_SM_InformesActionPerformed

    private void SM_GrupalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SM_GrupalesActionPerformed
        P_ReportesGrupos principal = null;
        principal = new P_ReportesGrupos();
        Panel_Principal.add(principal);
        try {
            principal.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        principal.show();
    }//GEN-LAST:event_SM_GrupalesActionPerformed

    private void SM_ConexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SM_ConexionActionPerformed
        D_Conexion conexion = new D_Conexion(this,true);
        conexion.setLocationRelativeTo(null);
        conexion.setResizable(false);
        conexion.setVisible(true);
    }//GEN-LAST:event_SM_ConexionActionPerformed

    private void M_AyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M_AyudaActionPerformed
       try{
//           Desktop.getDesktop().browse(new URL("ayuda/Defbern.html").toURI());
           Desktop.getDesktop().browse(new URL("http://www.google.com").toURI());

       }catch(Exception e){
           e.printStackTrace();
       }
         
    }//GEN-LAST:event_M_AyudaActionPerformed

    private void SM_AyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SM_AyudaActionPerformed
        File local= new File("");
        String direccion=local.getAbsolutePath();
        direccion=direccion.replaceAll(" ", "%20");
        File principal=new File("file:///"+direccion+"/ayuda/Defbern.html");
        try{
           Desktop.getDesktop().browse(new URL(principal.getPath()).toURI());
       }catch(Exception e){
           e.printStackTrace();
       }
    }//GEN-LAST:event_SM_AyudaActionPerformed

    private void SM_RegistroSinAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SM_RegistroSinAnalizarActionPerformed
        P_RegistroSinAnalizar principal = new P_RegistroSinAnalizar(this, permisos);
        Panel_Principal.add(principal);
        try {
            principal.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        principal.show();
    }//GEN-LAST:event_SM_RegistroSinAnalizarActionPerformed
    
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
                try {
                    new P_Principal(user).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(P_Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu M_Administrar;
    private javax.swing.JMenu M_Archivo;
    private javax.swing.JMenu M_Ayuda;
    private javax.swing.JMenu M_Configuracion;
    private javax.swing.JMenu M_Entrevista;
    private javax.swing.JMenu M_Informes;
    private javax.swing.JMenuBar Menu_Principal;
    private javax.swing.JLayeredPane Panel_Principal;
    private javax.swing.JLayeredPane Panel_Principal1;
    private javax.swing.JMenuItem SM_Ayuda;
    private javax.swing.JMenuItem SM_Conexion;
    private javax.swing.JMenuItem SM_Entrevista;
    private javax.swing.JMenuItem SM_EntrevistaGrupal;
    private javax.swing.JMenuItem SM_EntrevistaIndividual;
    private javax.swing.JMenuItem SM_Grupales;
    private javax.swing.JMenuItem SM_Grupos;
    private javax.swing.JMenuItem SM_Informes;
    private javax.swing.JMenuItem SM_Lamina;
    private javax.swing.JMenuItem SM_Perfil;
    private javax.swing.JMenuItem SM_Registro;
    private javax.swing.JMenuItem SM_RegistroSinAnalizar;
    private javax.swing.JMenuItem SM_Rol;
    private javax.swing.JMenuItem SM_Usuario;
    private javax.swing.JMenuItem S_Cerrar;
    private javax.swing.JMenuItem S_Salir;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    // End of variables declaration//GEN-END:variables
}
