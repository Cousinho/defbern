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
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
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
        tabla_laminas.setRowSorter(sorter);
        actualizartabla();
        CargarBox();
        Buscar();
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
        String titulos[] = {"Código","Descripcion","Entrevista"};
        LTabla.setColumnIdentifiers(titulos);
        try {
            for (Iterator<Lamina> it = BDLaminas.Lista(entrevista_actual.getCodigo()).iterator(); it.hasNext();) {
                Lamina lamina = it.next();
                String Datos[] = {String.valueOf(lamina.getCodigo()),lamina.getDescripcion(),
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
        jLabel1 = new javax.swing.JLabel();
        combo_buscar = new javax.swing.JComboBox();
        texto_buscar = new javax.swing.JTextField();
        check_mayusculas = new javax.swing.JCheckBox();

        tabla.setModel(LTabla);
        jScrollPane1.setViewportView(tabla);

        setClosable(true);
        setTitle("Laminas");

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

        jLabel1.setText("Buscar Por:");

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
                    .addComponent(b_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(b_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(b_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(texto_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(check_mayusculas)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(combo_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(check_mayusculas))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_nuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(b_eliminar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla_laminas;
    private javax.swing.JTextField texto_buscar;
    // End of variables declaration//GEN-END:variables
}
