package Pantallas;

import BaseDeDatos.BDUsuarios;
import Entidades.Usuario;
import Util.TablaModelo;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class P_Usuario extends javax.swing.JInternalFrame {

    public P_Usuario() {
        initComponents();
    }

    TablaModelo LUsuarios = new TablaModelo();
    TableRowSorter sorter = new TableRowSorter(LUsuarios);
    private Usuario usuario_actual=new Usuario();
    boolean seleccion=false;
    java.awt.Frame pantalla_padre;
    private ArrayList<String> permisos_actuales = new ArrayList();
  
    
    P_Usuario(java.awt.Frame Pantalla_padre, ArrayList<String> permisos) {
        Pantalla_padre=pantalla_padre;
        permisos_actuales=permisos;
        initComponents();
        BloquearComponentes();
        HabilitarComponentes();
        tabla_usuarios.setRowSorter(sorter);
        actualizartabla();
        Buscar();
        CargarBox();
     }
     
    //Metodo que actualiza la tabla de productos
    public void actualizartabla(){
        limpiartabla();
        String titulos[] = {"Código","Nombre","Rol"};
        LUsuarios.setColumnIdentifiers(titulos);
        try {
            for (Iterator<Usuario> it = BDUsuarios.Lista() .iterator(); it.hasNext();) {
                Usuario usuario = it.next();
                String Datos[] = {String.valueOf(usuario.getCodigo()),
                                  usuario.getNombre(),usuario.getRol().getDescripcion(),
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
    
    private void BloquearComponentes() {
        b_nuevo.setEnabled(false);
        b_modificar.setEnabled(false);
        b_eliminar.setEnabled(false);
    }

    private void HabilitarComponentes() {
        if(permisos_actuales.indexOf("agregar usuarios")!=-1){
            b_nuevo.setEnabled(true);
        }
        if(permisos_actuales.indexOf("modificar usuarios")!=-1){
            b_modificar.setEnabled(true);
        }
        if(permisos_actuales.indexOf("eliminar usuarios")!=-1){
            b_eliminar.setEnabled(true);
        }
    }
         private void filtrar() {
        RowFilter<TableModel, Object> rf = null;
        int indiceColumnaTabla = 0;
        switch (combo_buscar.getSelectedIndex()) {
        case 0: indiceColumnaTabla = 0;break;
        case 1: indiceColumnaTabla = 1;break;
        case 2: indiceColumnaTabla = 2;break;
        }
        try {
        if(check_mayusculas.isSelected()!=true){
            rf = RowFilter.regexFilter( "(?i)"+texto_buscar.getText(), indiceColumnaTabla);
        }else{
            rf = RowFilter.regexFilter( texto_buscar.getText(), indiceColumnaTabla);
        }
        } catch (java.util.regex.PatternSyntaxException e) {
        }
        sorter.setRowFilter(rf);
    }
    
    private void CargarBox(){
       combo_buscar.removeAllItems();
       combo_buscar.addItem("Código");
       combo_buscar.addItem("Nombre");
       combo_buscar.addItem("Rol");
    }
    
      private void Buscar(){
        texto_buscar.getDocument().addDocumentListener(
            new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
            filtrar();
            }
            public void insertUpdate(DocumentEvent e) {
            filtrar();
            }
            public void removeUpdate(DocumentEvent e) {
            filtrar();
            }
            });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_usuarios = new javax.swing.JTable();
        b_nuevo = new javax.swing.JButton();
        b_modificar = new javax.swing.JButton();
        b_eliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        combo_buscar = new javax.swing.JComboBox();
        texto_buscar = new javax.swing.JTextField();
        check_mayusculas = new javax.swing.JCheckBox();

        setClosable(true);
        setTitle("Usuarios");

        tabla_usuarios.setModel(LUsuarios);
        jScrollPane1.setViewportView(tabla_usuarios);

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

        jLabel2.setText("Buscar Por:");

        combo_buscar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        texto_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texto_buscarActionPerformed(evt);
            }
        });
        texto_buscar.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                texto_buscarInputMethodTextChanged(evt);
            }
        });
        texto_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                texto_buscarKeyPressed(evt);
            }
        });

        check_mayusculas.setText("Diferenciar mayúsculas y minúsculas");
        check_mayusculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_mayusculasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(b_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(combo_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(texto_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(check_mayusculas)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(texto_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(check_mayusculas))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(combo_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_nuevo)
                        .addGap(18, 18, 18)
                        .addComponent(b_modificar)
                        .addGap(18, 18, 18)
                        .addComponent(b_eliminar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_nuevoActionPerformed
        D_Usuario insertar = null;
        try {
            insertar = new D_Usuario(pantalla_padre,true);
        } catch (SQLException ex) {
            Logger.getLogger(P_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void b_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_eliminarActionPerformed
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
    }//GEN-LAST:event_b_eliminarActionPerformed

    private void texto_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texto_buscarActionPerformed

    }//GEN-LAST:event_texto_buscarActionPerformed

    private void texto_buscarInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_texto_buscarInputMethodTextChanged

    }//GEN-LAST:event_texto_buscarInputMethodTextChanged

    private void texto_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_texto_buscarKeyPressed

    }//GEN-LAST:event_texto_buscarKeyPressed

    private void check_mayusculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_mayusculasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_mayusculasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_eliminar;
    private javax.swing.JButton b_modificar;
    private javax.swing.JButton b_nuevo;
    private javax.swing.JCheckBox check_mayusculas;
    private javax.swing.JComboBox combo_buscar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_usuarios;
    private javax.swing.JTextField texto_buscar;
    // End of variables declaration//GEN-END:variables
}
