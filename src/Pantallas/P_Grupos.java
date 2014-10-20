package Pantallas;

import BaseDeDatos.BDGrupos;
import Entidades.Grupo;
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

public class P_Grupos extends javax.swing.JInternalFrame {
    TablaModelo LTabla = new TablaModelo();
    TableRowSorter sorter = new TableRowSorter(LTabla);
    public static java.awt.Frame  pantalla_padre;
    private ArrayList<String> permisos_actuales = new ArrayList();
    private Grupo grupo_actual=new Grupo();
    
    
    public P_Grupos(java.awt.Frame padre, ArrayList<String> permisos) {
        initComponents();
        pantalla_padre=padre;
        permisos_actuales = permisos;
        tabla.setRowSorter(sorter);
        BloquearComponentes();
        HabilitarComponentes();
        //actualizada datos de tablas
        actualizartabla();
        Buscar();
        CargarBox();
    }
    
    private void BloquearComponentes() {
        b_nuevo.setEnabled(false);
        b_modificar.setEnabled(false);
        b_eliminar.setEnabled(false);
    }

    private void HabilitarComponentes() {
        if(permisos_actuales.indexOf("agregar grupos")!=-1){
            b_nuevo.setEnabled(true);
        }
        if(permisos_actuales.indexOf("modificar grupos")!=-1){
            b_modificar.setEnabled(true);
        }
        if(permisos_actuales.indexOf("eliminar grupos")!=-1){
            b_eliminar.setEnabled(true);
        }
    }
    
    
    public void actualizartabla(){
        limpiartabla();
        String titulos[] = {"Código","Descripción"};
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
    
    
     private void filtrar() {
        RowFilter<TableModel, Object> rf = null;
        int indiceColumnaTabla = 0;
        switch (combo_buscar.getSelectedIndex()) {
        case 0: indiceColumnaTabla = 0;break;//por codigo
        case 1: indiceColumnaTabla = 1;break;//por cedula
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
       combo_buscar.addItem("Descripción");
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
        tabla = new javax.swing.JTable();
        b_eliminar = new javax.swing.JButton();
        b_modificar = new javax.swing.JButton();
        b_nuevo = new javax.swing.JButton();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_laminas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        combo_buscar = new javax.swing.JComboBox();
        texto_buscar = new javax.swing.JTextField();
        check_mayusculas = new javax.swing.JCheckBox();

        setClosable(true);

        tabla.setModel(LTabla);
        jScrollPane1.setViewportView(tabla);

        b_eliminar.setText("Eliminar");
        b_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_eliminarActionPerformed(evt);
            }
        });

        b_modificar.setText("Modificar");
        b_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_modificarActionPerformed(evt);
            }
        });

        b_nuevo.setText("Nuevo");
        b_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_nuevoActionPerformed(evt);
            }
        });

        jInternalFrame1.setClosable(true);
        jInternalFrame1.setTitle("Laminas");

        tabla_laminas.setModel(LTabla);
        jScrollPane2.setViewportView(tabla_laminas);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(b_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(b_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(texto_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(check_mayusculas)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(combo_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(check_mayusculas))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(b_nuevo)
                        .addGap(18, 18, 18)
                        .addComponent(b_modificar)
                        .addGap(18, 18, 18)
                        .addComponent(b_eliminar))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)))
                .addGap(27, 27, 27))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
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

    private void b_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_modificarActionPerformed
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
    }//GEN-LAST:event_b_modificarActionPerformed

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
                        if(Integer.parseInt(valor.toString())==0){
                            JOptionPane.showMessageDialog(null, "No se puede eliminar grupo");
                        }else{
                            //solicita eliminar fila selecionada
                            BDGrupos.eliminar(Integer.parseInt(valor.toString()));
                        }
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
    
    private void texto_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texto_buscarActionPerformed

    }//GEN-LAST:event_texto_buscarActionPerformed

    private void texto_buscarInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_texto_buscarInputMethodTextChanged

    }//GEN-LAST:event_texto_buscarInputMethodTextChanged

    private void texto_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_texto_buscarKeyPressed

    }//GEN-LAST:event_texto_buscarKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_eliminar;
    private javax.swing.JButton b_modificar;
    private javax.swing.JButton b_nuevo;
    private javax.swing.JCheckBox check_mayusculas;
    private javax.swing.JComboBox combo_buscar;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla_laminas;
    private javax.swing.JTextField texto_buscar;
    // End of variables declaration//GEN-END:variables
}
