/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pantallas;

import Analisis.AnalisisRorschach;
import BaseDeDatos.BDRegistros;
import Entidades.Registro;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class D_Analizando extends javax.swing.JDialog {

    public D_Analizando(java.awt.Frame parent, boolean modal,Registro registro) {
        super(parent, modal);
        System.loadLibrary("opencv_java249");
        initComponents();
        setDefaultCloseOperation(0);
        ProcesosAnalizar(registro);
    }
    
    public void ProcesosAnalizar(final Registro registro)
    {
    Runnable miRunnable = new Runnable()
    {
    public void run()
    {
    try
    {
        Analizar(registro);
    }
    catch (Exception e)
    {
    e.printStackTrace();
    }
    }
    };
    Thread hilo = new Thread (miRunnable);
    hilo.start();
    }
    
    private void Analizar(Registro registro_actual){
        String perfil = "";
        AnalisisRorschach analisis=new AnalisisRorschach();
        try {
            perfil=analisis.AnalizarRegistro(registro_actual);
        } catch (SQLException ex) {
            Logger.getLogger(P_IniciarEntrevista.class.getName()).log(Level.SEVERE, null, ex);  
        }
        registro_actual.setAnalizado(true);
        registro_actual.setPerfil(perfil);
        setDefaultCloseOperation(1);
        try {
            BDRegistros.actualizar(registro_actual);
        } catch (SQLException ex) {
            Logger.getLogger(D_Analizando.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Util/gif-load.gif"))); // NOI18N
        jLabel1.setText("Analizando");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(D_Analizando.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(D_Analizando.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(D_Analizando.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(D_Analizando.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Registro registro = null;
                D_Analizando dialog = new D_Analizando(new javax.swing.JFrame(), true,registro);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
