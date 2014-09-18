/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pantallas;

import BaseDeDatos.BDPerfiles;
import Entidades.Perfil;
import Util.TablaModelo;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jose
 */
public class P_Perfil extends javax.swing.JInternalFrame {

    TablaModelo LPerfiles = new TablaModelo();
    TableRowSorter sorter = new TableRowSorter(LPerfiles);
    private Perfil perfil_actual=new Perfil();
    boolean seleccion=false;
    java.awt.Frame pantalla_padre;
    
    public P_Perfil(java.awt.Frame Pantalla_padre) {
        Pantalla_padre=pantalla_padre;
        initComponents();
        tabla_perfiles.setRowSorter(sorter);
        //actualizada datos de tablas
        actualizartabla();
    }
   
    //Metodo que actualiza la tabla de Perfils
    public void actualizartabla(){
        limpiartabla();
        String titulos[] = {"Codigo","Descripción"};
        LPerfiles.setColumnIdentifiers(titulos);
        try {
            for (Iterator<Perfil> it = BDPerfiles.Lista().iterator(); it.hasNext();) {
                Perfil perfil = it.next();
                String Datos[] = {String.valueOf(perfil.getCodigo()),perfil.getDescripcion()};
                LPerfiles.addRow(Datos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
        
    }
    
    //Metodo que limpia la tabla de Perfils
    public void limpiartabla(){
        int tamaño =LPerfiles.getRowCount()-1;
        for(int i=tamaño; i>=0;i--){
              LPerfiles.removeRow(i );
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_perfiles = new javax.swing.JTable();
        b_agregar = new javax.swing.JButton();
        b_modificar = new javax.swing.JButton();
        b_borrar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabla_perfiles.setModel(LPerfiles);
        jScrollPane1.setViewportView(tabla_perfiles);

        b_agregar.setText("Agregar");
        b_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_agregarActionPerformed(evt);
            }
        });

        b_modificar.setText("Modificar");
        b_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_modificarActionPerformed(evt);
            }
        });

        b_borrar.setText("Borrar");
        b_borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_borrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(b_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_borrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(b_agregar)
                .addGap(18, 18, 18)
                .addComponent(b_modificar)
                .addGap(18, 18, 18)
                .addComponent(b_borrar)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_agregarActionPerformed
        D_Perfil insertar = null;
        insertar = new D_Perfil(pantalla_padre,true);
        insertar.setLocationRelativeTo(null);
        insertar.setResizable(false);
        insertar.setVisible(true);
        //Actualiza tabla despues de cerrar ventana insertar
        insertar.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                actualizartabla();
            }
        });
    }//GEN-LAST:event_b_agregarActionPerformed

    private void b_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_modificarActionPerformed
        //recibe el número de fila selecionada
        int fila=tabla_perfiles.getSelectedRow();
        if (fila!=-1){
            Perfil c_envia=new Perfil() {};
            Object valor = tabla_perfiles.getValueAt(fila, 0);
            try {
                c_envia=BDPerfiles.buscarId(Integer.parseInt(valor.toString()));
            } catch (SQLException ex) {
                Logger.getLogger(P_Perfil.class.getName()).log(Level.SEVERE, null, ex);
            }
            D_Perfil editar= new D_Perfil(pantalla_padre,true,c_envia);
            editar.setLocationRelativeTo(null);
            editar.setResizable(false);
            editar.setVisible(true);
            //Actualiza tabla despues de cerrar ventana modificar
            editar.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e) {
                    actualizartabla();
                }
            });
        }
        else{
            JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla");
        }
    }//GEN-LAST:event_b_modificarActionPerformed

    private void b_borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_borrarActionPerformed
        //obtiene el número de fila seleccionada
        int fila=tabla_perfiles.getSelectedRow();

        if (fila!=-1){
            //solita confirmación de acción
            int seleccion = JOptionPane.showOptionDialog(
                this, // Componente padre
                "¿Desea eliminar este perfil?",
                "Seleccione una opción",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,    // null para icono por defecto.
                new Object[] { "Si", "No"},    // null para YES, NO y CANCEL
                "Si");
            if (seleccion != -1)
            {
                if((seleccion + 1)==1)
                {
                    Object valor = tabla_perfiles.getValueAt(fila, 0);
                    try {
                        BDPerfiles.eliminar(Integer.parseInt(valor.toString()));
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "No se puede eliminar perfil");
                    }
                actualizartabla();
                }
                           }
        }
        else{
            JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla");
        }
    }//GEN-LAST:event_b_borrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_agregar;
    private javax.swing.JButton b_borrar;
    private javax.swing.JButton b_modificar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_perfiles;
    // End of variables declaration//GEN-END:variables
}
