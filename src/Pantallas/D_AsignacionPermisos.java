/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pantallas;

import BaseDeDatos.BDPermisos;
import BaseDeDatos.BDRoles_Permisos;
import Entidades.Permiso;
import Entidades.Rol;
import Entidades.Rol_Permiso;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author jose
 */
public class D_AsignacionPermisos extends javax.swing.JDialog {

    /**
     * Creates new form D_AsignacionPermisos
     */
    
    private DefaultListModel listapermisos = new DefaultListModel();
    private DefaultListModel listaasignar = new DefaultListModel();
    private ArrayList<String> auxiliar = new ArrayList();
    private ArrayList<String> asignar = new ArrayList();
    private Rol rol_actual = new Rol();
    
    public D_AsignacionPermisos(java.awt.Frame parent, boolean modal, Rol rol) {
        super(parent, modal);
        initComponents();
        rol_actual=rol;
        LlenarListas();
        if (listaasignar.isEmpty()){
                b_revocar.setEnabled(false);
                b_ninguno.setEnabled(false);
        }
    }
    
    public void LlenarListas(){
        try {
            for (Iterator<Permiso> it = BDPermisos.Lista() .iterator(); it.hasNext();) {
                Permiso permiso = it.next();
                listapermisos.addElement(permiso.getDescripcion());
                auxiliar.add(permiso.getDescripcion());
            }
            lista_permisos.setModel(listapermisos);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
        try {
            for (Iterator<Permiso> it = BDPermisos.Lista(rol_actual.getCodigo()) .iterator(); it.hasNext();) {
                Permiso permiso = it.next();
                listaasignar.addElement(permiso.getDescripcion());
                listapermisos.removeElement(permiso.getDescripcion());
                asignar.add(permiso.getDescripcion());
            }
            lista_asignar.setModel(listaasignar);
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
    }
    
    
    private boolean Guardar() throws SQLException{
        boolean guardar = false;
        if (asignar.isEmpty()){
            BDRoles_Permisos.eliminar(rol_actual.getCodigo());
            guardar = true;
        } else {
            BDRoles_Permisos.eliminar(rol_actual.getCodigo());
            for(Iterator<String> it = asignar.iterator(); it.hasNext();) {
                try {
                    Permiso permiso = BDPermisos.buscarNombre(it.next());
                    Rol_Permiso rol_permiso = new Rol_Permiso();
                    rol_permiso.setRol(rol_actual);
                    if(BDRoles_Permisos.buscar(rol_actual.getCodigo(), permiso.getCodigo())==null){
                        rol_permiso.setPermiso(permiso);
                        BDRoles_Permisos.insertar(rol_permiso);
                        guardar = true;   
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(D_AsignacionPermisos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return guardar;
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        b_asignar = new javax.swing.JButton();
        b_todos = new javax.swing.JButton();
        b_revocar = new javax.swing.JButton();
        b_ninguno = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_permisos = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista_asignar = new javax.swing.JList();
        b_aceptar = new javax.swing.JButton();
        b_cancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        b_asignar.setText("Asignar");
        b_asignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_asignarActionPerformed(evt);
            }
        });

        b_todos.setText("Todos");
        b_todos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_todosActionPerformed(evt);
            }
        });

        b_revocar.setText("Revocar");
        b_revocar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_revocarActionPerformed(evt);
            }
        });

        b_ninguno.setText("Ninguno");
        b_ninguno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_ningunoActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(lista_permisos);

        jScrollPane2.setViewportView(lista_asignar);

        b_aceptar.setText("Aceptar");
        b_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_aceptarActionPerformed(evt);
            }
        });

        b_cancelar.setText("Cancelar");
        b_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cancelarActionPerformed(evt);
            }
        });

        jLabel1.setText("Lista de permisos disponibles:");

        jLabel2.setText("Permisos actuales:");

        jPanel1.setBackground(new java.awt.Color(81, 106, 138));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 41));

        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setText("Asignación de Permisos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(226, 226, 226))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(b_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b_asignar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_todos, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_revocar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_ninguno, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(b_asignar)
                        .addGap(20, 20, 20)
                        .addComponent(b_todos)
                        .addGap(20, 20, 20)
                        .addComponent(b_revocar)
                        .addGap(20, 20, 20)
                        .addComponent(b_ninguno)
                        .addGap(20, 20, 20)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_cancelar)
                    .addComponent(b_aceptar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_asignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_asignarActionPerformed
        if(lista_permisos.isSelectionEmpty()){ 
            JOptionPane.showMessageDialog(null, "Seleccione un ítem de la lista");
        } else { 
            listaasignar.addElement(lista_permisos.getSelectedValue());
            listapermisos.removeElement(lista_permisos.getSelectedValue());
            if(listaasignar.isEmpty()){
                b_revocar.setEnabled(false);
                b_ninguno.setEnabled(false);
                b_asignar.setEnabled(true);
                b_todos.setEnabled(true);
            }
            if (listapermisos.isEmpty()){
                b_asignar.setEnabled(false);
                b_todos.setEnabled(false);
                b_revocar.setEnabled(true);
                b_ninguno.setEnabled(true);
            }
        }
    }//GEN-LAST:event_b_asignarActionPerformed

    private void b_todosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_todosActionPerformed
        for (Iterator<String> it = auxiliar.iterator(); it.hasNext();) {
            String permiso = it.next();
            if(listaasignar.indexOf(permiso)==-1 && asignar.indexOf(permiso)==-1){
                listaasignar.addElement(permiso);
                asignar.add(permiso);
            }
            listapermisos.removeElement(permiso);
        }
        lista_asignar.setModel(listaasignar);
        lista_permisos.setModel(listapermisos);
        b_todos.setEnabled(false);
        b_asignar.setEnabled(false);
        b_revocar.setEnabled(true);
        b_ninguno.setEnabled(true);
    }//GEN-LAST:event_b_todosActionPerformed

    private void b_revocarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_revocarActionPerformed
        if(lista_asignar.isSelectionEmpty()){ 
            JOptionPane.showMessageDialog(null, "Seleccione un ítem de la lista");
        } else { 
            listapermisos.addElement(lista_asignar.getSelectedValue());
            listaasignar.removeElement(lista_asignar.getSelectedValue());
            if(listapermisos.isEmpty()){
                b_asignar.setEnabled(false);
                b_todos.setEnabled(false);
                b_revocar.setEnabled(true);
                b_ninguno.setEnabled(true);
            }else{
                b_asignar.setEnabled(true);
                b_todos.setEnabled(true);
            }
            if (listaasignar.isEmpty()){
                b_revocar.setEnabled(false);
                b_ninguno.setEnabled(false);
                b_asignar.setEnabled(true);
                b_todos.setEnabled(true);
            }else{
                b_revocar.setEnabled(true);
                b_ninguno.setEnabled(true);
            }
        }
    }//GEN-LAST:event_b_revocarActionPerformed

    private void b_ningunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_ningunoActionPerformed
        for (Iterator<String> it = auxiliar.iterator(); it.hasNext();) {
            String permiso = it.next();
            if(listapermisos.indexOf(permiso)==-1){
                listapermisos.addElement(permiso);
            }
            listaasignar.removeElement(permiso);
            asignar.remove(permiso);
        }
        lista_asignar.setModel(listaasignar);
        lista_permisos.setModel(listapermisos);
        b_ninguno.setEnabled(false);
        b_revocar.setEnabled(false);
        b_todos.setEnabled(true);
        b_asignar.setEnabled(true);
    }//GEN-LAST:event_b_ningunoActionPerformed

    private void b_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_aceptarActionPerformed
        boolean estado = false;
        try {
            estado = Guardar();
        } catch (SQLException ex) {
            Logger.getLogger(D_AsignacionPermisos.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(estado){
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "No se pudo guardar datos");
        }
    }//GEN-LAST:event_b_aceptarActionPerformed

    private void b_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_b_cancelarActionPerformed

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
            java.util.logging.Logger.getLogger(D_AsignacionPermisos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(D_AsignacionPermisos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(D_AsignacionPermisos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(D_AsignacionPermisos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Rol rol = null;
                D_AsignacionPermisos dialog = new D_AsignacionPermisos(new javax.swing.JFrame(), true, rol);
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
    private javax.swing.JButton b_aceptar;
    private javax.swing.JButton b_asignar;
    private javax.swing.JButton b_cancelar;
    private javax.swing.JButton b_ninguno;
    private javax.swing.JButton b_revocar;
    private javax.swing.JButton b_todos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lista_asignar;
    private javax.swing.JList lista_permisos;
    // End of variables declaration//GEN-END:variables
}
