package Pantallas;

import BaseDeDatos.BDLaminas;
import Entidades.Entrevista;
import Entidades.Lamina;
import Util.TablaModelo;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;

public class P_Lamina extends javax.swing.JInternalFrame {
    TablaModelo LTabla = new TablaModelo();
    TableRowSorter sorter = new TableRowSorter(LTabla);
    private Entrevista entrevista_actual=new Entrevista();
    boolean seleccion=false;
    java.awt.Frame pantalla_padre;
    public P_Lamina(java.awt.Frame padre) {
        pantalla_padre=padre;
        initComponents();
        Seleccionar();
        tabla.setRowSorter(sorter);
        actualizartabla();
    }
    
    public void Seleccionar(){
        seleccion=false;
        D_SeleccionaEntrevista seleccionar=new D_SeleccionaEntrevista(pantalla_padre,this,true);
        seleccionar.setLocationRelativeTo(null);
        seleccionar.setVisible(true);
        seleccionar.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                if(!seleccion){
                    dispose();
                }else{
                    actualizartabla();
                }
                
        } 
        });
        
    }
    
    public void setEntrevista(Entrevista entrevista){
        this.entrevista_actual=entrevista;
        seleccion=true;
    }
    public void actualizartabla(){
        limpiartabla();
        String titulos[] = {"Idenficador","Nombre","Descripción"};
        LTabla.setColumnIdentifiers(titulos);
        try {
            for (Iterator<Lamina> it = BDLaminas.Lista(entrevista_actual.getCodigo()).iterator(); it.hasNext();) {
                Lamina lamina = it.next();
                String Datos[] = {String.valueOf(lamina.getCodigo()),lamina.getEntrevista().getNombre(),
                        lamina.getEntrevista().getDescripcion()};
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_laminas = new javax.swing.JTable();
        b_nuevo = new javax.swing.JButton();
        b_modificar = new javax.swing.JButton();
        b_eliminar = new javax.swing.JButton();

        tabla.setModel(LTabla);
        jScrollPane1.setViewportView(tabla);

        setClosable(true);

        tabla_laminas.setModel(LTabla);
        jScrollPane2.setViewportView(tabla_laminas);

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(b_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(b_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(b_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(b_nuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_eliminar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_nuevoActionPerformed
        D_Lamina insertar = null;
        insertar = new D_Lamina(pantalla_padre,true,entrevista_actual);
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
        int columna=tabla_laminas.getSelectedRow();
        if (columna!=-1){
            try {
                Lamina l_envia=new Lamina() {};
                Object valor = tabla_laminas.getValueAt(columna, 0);
                l_envia=BDLaminas.buscarId(Integer.parseInt(valor.toString()));
                D_Lamina editar= new D_Lamina(pantalla_padre,true,l_envia);
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
                //recibe numero de fila seleccionada
        int fila=tabla_laminas.getSelectedRow();
        
        //verifica que este seleccionada una fila
        if (fila!=-1){
            int seleccion = JOptionPane.showOptionDialog(
                this, // Componente padre
                "¿Desea eliminar este lamina?",
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
                    Object valor = tabla_laminas.getValueAt(fila, 0);
                    try {
                        //solicita eliminar fila selecionada
                        BDLaminas.eliminar(Integer.parseInt(valor.toString()));
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "No se puede eliminar lamina");

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
    private javax.swing.JButton b_eliminar;
    private javax.swing.JButton b_modificar;
    private javax.swing.JButton b_nuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla_laminas;
    // End of variables declaration//GEN-END:variables
}
