package Pantallas;

import BaseDeDatos.BDEntrevistas;
import Entidades.Entrevista;
import Util.TablaModelo;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;

public class P_Entrevista extends javax.swing.JInternalFrame {
    TablaModelo LEntrevistas = new TablaModelo();
    TableRowSorter sorter = new TableRowSorter(LEntrevistas);
    private Entrevista entrevista_actual=new Entrevista();
    private ArrayList<String> permisos_actuales = new ArrayList();
    boolean seleccion=false;
    java.awt.Frame pantalla_padre;
    
    
    public P_Entrevista(java.awt.Frame padre, ArrayList<String> permisos) {
        padre=pantalla_padre;
        permisos_actuales = permisos;
        initComponents();
        tabla_entrevistas.setRowSorter(sorter);
        //BloquearComponentes();
        HabilitarComponentes();
        actualizartabla();
    }
    
    private void BloquearComponentes() {
        b_nuevo.setEnabled(false);
        b_modificar.setEnabled(false);
        b_eliminar.setEnabled(false);
    }

    private void HabilitarComponentes() {
        if(permisos_actuales.indexOf("agregar entrevistas")!=-1){
            b_nuevo.setEnabled(true);
        }
        if(permisos_actuales.indexOf("modificar entrevistas")!=-1){
            b_modificar.setEnabled(true);
        }
        if(permisos_actuales.indexOf("eliminar entrevistas")!=-1){
            b_eliminar.setEnabled(true);
        }
    }
    
    public void actualizartabla(){
        limpiartabla();
        String titulos[] = {"Código","Nombre","Descripción"};
        LEntrevistas.setColumnIdentifiers(titulos);
        try {
            for (Iterator<Entidades.Entrevista> it = BDEntrevistas.Lista().iterator(); it.hasNext();) {
                Entidades.Entrevista entrevista = it.next();
                String Datos[] = {String.valueOf(entrevista.getCodigo()),entrevista.getNombre(),
                        entrevista.getDescripcion()};
                LEntrevistas.addRow(Datos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
        
    }
     
    public void limpiartabla(){
        int tamaño =LEntrevistas.getRowCount()-1;
        for(int i=tamaño; i>=0;i--){
              LEntrevistas.removeRow(i);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_entrevistas = new javax.swing.JTable();
        b_nuevo = new javax.swing.JButton();
        b_modificar = new javax.swing.JButton();
        b_eliminar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Entrevistas");

        tabla_entrevistas.setModel(LEntrevistas);
        jScrollPane1.setViewportView(tabla_entrevistas);

        b_nuevo.setText("Nuevo");
        b_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_nuevoActionPerformed(evt);
            }
        });

        b_modificar.setText("Modificar");
        b_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_modificarActionPerformed(evt);
            }
        });

        b_eliminar.setText("Eliminar");
        b_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(b_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(b_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(b_modificar)
                        .addGap(18, 18, 18)
                        .addComponent(b_eliminar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_nuevoActionPerformed
        D_Entrevista insertar = null;
        insertar = new D_Entrevista(pantalla_padre,true);
        insertar.setLocationRelativeTo(null);
        insertar.setResizable(false);
        insertar.setVisible(true);
        //Actualiza tabla despues de cerrar ventana insertar
        insertar.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                actualizartabla();
            }
        });
    }//GEN-LAST:event_b_nuevoActionPerformed

    private void b_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_modificarActionPerformed
        int columna=tabla_entrevistas.getSelectedRow();
        if (columna!=-1){
            try {
                Entrevista p_envia=new Entrevista() {};
                Object valor = tabla_entrevistas.getValueAt(columna, 0);
                p_envia=BDEntrevistas.buscarId(Integer.parseInt(valor.toString()));
                D_Entrevista editar= new D_Entrevista(pantalla_padre,true,p_envia);
                editar.setLocationRelativeTo(null);
                editar.setResizable(false);
                editar.setVisible(true);
                //Actualiza tabla despues de cerrar ventana modificar
                editar.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent e) {
                        actualizartabla();
                    }
                });
            } catch (SQLException ex) {
                Logger.getLogger(P_Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla");
        }
    }//GEN-LAST:event_b_modificarActionPerformed

    private void b_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_eliminarActionPerformed
        //obtiene el número de fila seleccionada
        int fila=tabla_entrevistas.getSelectedRow();

        if (fila!=-1){
            //solita confirmación de acción
            int seleccion = JOptionPane.showOptionDialog(
                this, // Componente padre
                "¿Desea eliminar esta entrevista?",
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
                    Object valor = tabla_entrevistas.getValueAt(fila, 0);
                    try {
                        BDEntrevistas.eliminar(Integer.parseInt(valor.toString()));
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "No se puede eliminar entrevista");
                    }
                actualizartabla();
                }
                           }
        }
        else{
            JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla");
        }
    }//GEN-LAST:event_b_eliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_eliminar;
    private javax.swing.JButton b_modificar;
    private javax.swing.JButton b_nuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_entrevistas;
    // End of variables declaration//GEN-END:variables


}

