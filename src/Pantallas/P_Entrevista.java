package Pantallas;

import BaseDeDatos.BDEntrevistas;
import Util.TablaModelo;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;

public class P_Entrevista extends javax.swing.JInternalFrame {
    TablaModelo LTabla = new TablaModelo();
    TableRowSorter sorter = new TableRowSorter(LTabla);
    public static java.awt.Frame  pantalla;
    public P_Entrevista(java.awt.Frame padre) {
        initComponents();
        pantalla=padre;
        tabla.setRowSorter(sorter);
        //actualizada datos de tablas
        actualizartabla();
    }
    
    public void actualizartabla(){
        limpiartabla();
        String titulos[] = {"Idenficador","Nombre","Descripción"};
        LTabla.setColumnIdentifiers(titulos);
        try {
            for (Iterator<Entidades.Entrevista> it = BDEntrevistas.Lista().iterator(); it.hasNext();) {
                Entidades.Entrevista entrevista = it.next();
                String Datos[] = {String.valueOf(entrevista.getCodigo()),entrevista.getNombre(),
                        entrevista.getDescripcion()};
                LTabla.addRow(Datos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
        
    }
     
    public void limpiartabla(){
        int tamaño =LTabla.getRowCount()-1;
        for(int i=tamaño; i>=0;i--){
              LTabla.removeRow(i );
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        b_nuevo = new javax.swing.JButton();
        b_editar = new javax.swing.JButton();
        b_eliminar = new javax.swing.JButton();
        b_laminas = new javax.swing.JButton();

        setClosable(true);

        tabla.setModel(LTabla);
        jScrollPane1.setViewportView(tabla);

        b_nuevo.setText("Nuevo");

        b_editar.setText("Editar");

        b_eliminar.setText("Eliminar");

        b_laminas.setText("Editar Laminas");
        b_laminas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_laminasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(b_laminas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_nuevo)
                        .addGap(18, 18, 18)
                        .addComponent(b_editar)
                        .addGap(18, 18, 18)
                        .addComponent(b_eliminar)
                        .addGap(18, 18, 18)
                        .addComponent(b_laminas))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_laminasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_laminasActionPerformed
        
    }//GEN-LAST:event_b_laminasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_editar;
    private javax.swing.JButton b_eliminar;
    private javax.swing.JButton b_laminas;
    private javax.swing.JButton b_nuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}

