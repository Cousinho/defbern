package Pantallas;

import BaseDeDatos.BDRoles;
import Entidades.Rol;
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

public class P_Rol extends javax.swing.JInternalFrame {
    private ArrayList<String> permisos_actuales = new ArrayList();
    public P_Rol() {
        initComponents();
    }

    TablaModelo LRoles = new TablaModelo();
    TableRowSorter sorter = new TableRowSorter(LRoles);
    private Rol rol_actual=new Rol();
    boolean seleccion=false;
    java.awt.Frame pantalla_padre;

    
    P_Rol(java.awt.Frame Pantalla_padre, ArrayList<String> permisos) {
        Pantalla_padre=pantalla_padre;
        permisos_actuales=permisos;
        initComponents();
        BloquearComponentes();
        HabilitarComponentes();
        tabla_roles.setRowSorter(sorter);
        actualizartabla();
     }
     
    //Metodo que actualiza la tabla de productos
    public void actualizartabla(){
        limpiartabla();
        String titulos[] = {"Codigo","Nombre"};
        LRoles.setColumnIdentifiers(titulos);
        try {
            for (Iterator<Rol> it = BDRoles.Lista() .iterator(); it.hasNext();) {
                Rol rol = it.next();
                String Datos[] = {String.valueOf(rol.getCodigo()),
                                  rol.getDescripcion()
                                  };
                LRoles.addRow(Datos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
    }
     
    //Metodo que limpia la tabla de Productos
    public void limpiartabla(){
        int tamaño =LRoles.getRowCount()-1;
        for(int i=tamaño; i>=0;i--){
              LRoles.removeRow(i );
        }
    }
    
    private void BloquearComponentes() {
        b_nuevo.setEnabled(false);
        b_modificar.setEnabled(false);
        b_eliminar.setEnabled(false);
    }

    private void HabilitarComponentes() {
        if(permisos_actuales.indexOf("agregar roles")!=-1){
            b_nuevo.setEnabled(true);
        }
        if(permisos_actuales.indexOf("modificar roles")!=-1){
            b_modificar.setEnabled(true);
        }
        if(permisos_actuales.indexOf("eliminar roles")!=-1){
            b_eliminar.setEnabled(true);
        }
    }
    
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_roles = new javax.swing.JTable();
        b_nuevo = new javax.swing.JButton();
        b_modificar = new javax.swing.JButton();
        b_eliminar = new javax.swing.JButton();
        b_permisos = new javax.swing.JButton();

        setClosable(true);

        tabla_roles.setModel(LRoles);
        jScrollPane1.setViewportView(tabla_roles);

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

        b_permisos.setText("Permisos");
        b_permisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_permisosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(b_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(b_permisos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_nuevo)
                        .addGap(18, 18, 18)
                        .addComponent(b_modificar)
                        .addGap(18, 18, 18)
                        .addComponent(b_eliminar)
                        .addGap(18, 18, 18)
                        .addComponent(b_permisos)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_nuevoActionPerformed
        D_Rol insertar = null;
        insertar = new D_Rol(pantalla_padre,true);
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
        int columna=tabla_roles.getSelectedRow();
        if (columna!=-1){
            try {
                Rol p_envia=new Rol() {};
                Object valor = tabla_roles.getValueAt(columna, 0);
                p_envia=BDRoles.buscarId(Integer.parseInt(valor.toString()));
                D_Rol editar= new D_Rol(pantalla_padre,true,p_envia);
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
                Logger.getLogger(P_Rol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla");
        }
    }//GEN-LAST:event_b_modificarActionPerformed

    private void b_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_eliminarActionPerformed
        //recibe numero de fila seleccionada
        int fila=tabla_roles.getSelectedRow();
        
        //verifica que este seleccionada una fila
        if (fila!=-1){
            int seleccion = JOptionPane.showOptionDialog(
                this, // Componente padre
                "¿Desea eliminar este rol?",
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
                    Object valor = tabla_roles.getValueAt(fila, 0);
                    try {
                        //solicita eliminar fila selecionada
                        BDRoles.eliminar(Integer.parseInt(valor.toString()));
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "No se puede eliminar rol");

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

    private void b_permisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_permisosActionPerformed
        int columna=tabla_roles.getSelectedRow();
        if (columna!=-1){
            try {
                Rol p_envia=new Rol() {};
                Object valor = tabla_roles.getValueAt(columna, 0);
                p_envia=BDRoles.buscarId(Integer.parseInt(valor.toString()));
                D_AsignacionPermisos editar= new D_AsignacionPermisos(pantalla_padre,true,p_envia);
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
                Logger.getLogger(P_Rol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla");
        }
    }//GEN-LAST:event_b_permisosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_eliminar;
    private javax.swing.JButton b_modificar;
    private javax.swing.JButton b_nuevo;
    private javax.swing.JButton b_permisos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_roles;
    // End of variables declaration//GEN-END:variables
}
