package Pantallas;

import BaseDeDatos.BDGrupos;
import Entidades.Grupo;
import Util.TablaModelo;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;

public class P_Grupos extends javax.swing.JInternalFrame {
    TablaModelo LTabla = new TablaModelo();
    TableRowSorter sorter = new TableRowSorter(LTabla);
    public static java.awt.Frame  pantalla_padre;
    private Grupo grupo_actual=new Grupo();
    public P_Grupos(java.awt.Frame padre) {
        initComponents();
        pantalla_padre=padre;
        tabla.setRowSorter(sorter);
        //actualizada datos de tablas
        actualizartabla();
    }
    
    public void actualizartabla(){
        limpiartabla();
        String titulos[] = {"Idenficador","Descripción"};
        LTabla.setColumnIdentifiers(titulos); 
        try {
            for (Iterator<Grupo> it = BDGrupos.Lista().iterator(); it.hasNext();) {
                Grupo grupo= it.next();
                String Datos[] = {String.valueOf(grupo.getCodigo()),
                        grupo.getDescripcion()};
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
        b_eliminar = new javax.swing.JButton();
        b_editar = new javax.swing.JButton();
        b_nuevo = new javax.swing.JButton();

        setClosable(true);

        tabla.setModel(LTabla);
        jScrollPane1.setViewportView(tabla);

        b_eliminar.setText("Eliminar");
        b_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_eliminarActionPerformed(evt);
            }
        });

        b_editar.setText("Editar");
        b_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_editarActionPerformed(evt);
            }
        });

        b_nuevo.setText("Nuevo");
        b_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_nuevoActionPerformed(evt);
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
                    .addComponent(b_editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
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
                        .addComponent(b_eliminar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_nuevoActionPerformed
        D_Grupo insertar = null;
        insertar = new D_Grupo(pantalla_padre,true);
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

    private void b_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_editarActionPerformed
         //recibe el número de fila selecionada
        int fila=tabla.getSelectedRow();
        if (fila!=-1){
            Grupo g_envia=new Grupo();
            Object valor = tabla.getValueAt(fila, 0);
            try {
                g_envia=BDGrupos.buscarId(Integer.parseInt(valor.toString()));
            } catch (SQLException ex) {
                Logger.getLogger(P_Perfil.class.getName()).log(Level.SEVERE, null, ex);
            }
            D_Grupo editar= new D_Grupo(pantalla_padre,true,g_envia);
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
    }//GEN-LAST:event_b_editarActionPerformed

    private void b_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_eliminarActionPerformed
        //recibe numero de fila seleccionada
        int fila=tabla.getSelectedRow();
        
        //verifica que este seleccionada una fila
        if (fila!=-1){
            int seleccion = JOptionPane.showOptionDialog(
                this, // Componente padre
                "¿Desea eliminar este grupo?",
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
                    //recupera el objeto fila de la tabla a modificar
                    Object valor = tabla.getValueAt(fila, 0);
                    try {
                        //solicita eliminar fila selecionada
                        BDGrupos.eliminar(Integer.parseInt(valor.toString()));
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "No se puede eliminar grupo");

                    }

                    //actualiza tabla despues de eliminar fila
                    actualizartabla();
                }
              }
        }
        else{
            JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla");
        }


    }//GEN-LAST:event_b_eliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_editar;
    private javax.swing.JButton b_eliminar;
    private javax.swing.JButton b_nuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
