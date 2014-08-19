/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pantallas;

import BaseDeDatos.BDUsuarios;
import Entidades.Perfil;
import Entidades.Usuario;
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
 * @author Cousiño
 */
public class P_Usuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form P_Usuario
     */
    public P_Usuario() {
        initComponents();
    }

    TablaModelo LUsuarios = new TablaModelo();
    TableRowSorter sorter = new TableRowSorter(LUsuarios);
    private Usuario usuario_actual=new Usuario();
    boolean seleccion=false;
    java.awt.Frame pantalla_padre;
    
    P_Usuario(java.awt.Frame Pantalla_padre) {
        Pantalla_padre=pantalla_padre;
        initComponents();
        tabla_usuarios.setRowSorter(sorter);
        actualizartabla();
     }
     
    //Metodo que actualiza la tabla de productos
    public void actualizartabla(){
        limpiartabla();
        String titulos[] = {"Codigo","Nombre","Rol"};
        LUsuarios.setColumnIdentifiers(titulos);
        try {
            for (Iterator<Usuario> it = BDUsuarios.Lista() .iterator(); it.hasNext();) {
                Usuario usuario = it.next();
                String Datos[] = {String.valueOf(usuario.getCodigo()),
                                  usuario.getNombre(),usuario.getRol(),
                                  };
                LUsuarios.addRow(Datos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
    }
     
    //Metodo que limpia la tabla de Productos
    public void limpiartabla(){
        int tamaño =LUsuarios.getRowCount()-1;
        for(int i=tamaño; i>=0;i--){
              LUsuarios.removeRow(i );
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
        tabla_usuarios = new javax.swing.JTable();
        b_agregar = new javax.swing.JButton();
        b_modificar = new javax.swing.JButton();
        b_borrar = new javax.swing.JButton();

        tabla_usuarios.setModel(LUsuarios);
        jScrollPane1.setViewportView(tabla_usuarios);

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(b_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_borrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(57, 57, 57)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_agregar)
                        .addGap(18, 18, 18)
                        .addComponent(b_modificar)
                        .addGap(18, 18, 18)
                        .addComponent(b_borrar)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_agregarActionPerformed
        D_Usuario insertar = null;
        insertar = new D_Usuario(pantalla_padre,true,usuario_actual);
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
        int columna=tabla_usuarios.getSelectedRow();
        if (columna!=-1){
            try {
                Usuario p_envia=new Usuario() {};
                Object valor = tabla_usuarios.getValueAt(columna, 0);
                p_envia=BDUsuarios.buscarId(Integer.parseInt(valor.toString()));
                D_Usuario editar= new D_Usuario(pantalla_padre,true,p_envia);
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

    private void b_borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_borrarActionPerformed
        //recibe numero de fila seleccionada
        int fila=tabla_usuarios.getSelectedRow();
        
        //verifica que este seleccionada una fila
        if (fila!=-1){
            int seleccion = JOptionPane.showOptionDialog(
                this, // Componente padre
                "¿Desea eliminar este producto?",
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
                    Object valor = tabla_usuarios.getValueAt(fila, 0);
                    try {
                        //solicita eliminar fila selecionada
                        BDUsuarios.eliminar(Integer.parseInt(valor.toString()));
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "No se puede eliminar producto");

                    }

                    //actualiza tabla despues de eliminar fila
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
    private javax.swing.JTable tabla_usuarios;
    // End of variables declaration//GEN-END:variables
}
