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
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


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
        CargarBox();
        Buscar();
    }
   
    //Metodo que actualiza la tabla de Perfils
    public void actualizartabla(){
        limpiartabla();
        String titulos[] = {"Código","Descripción"};
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
        tabla_perfiles = new javax.swing.JTable();
        b_nuevo = new javax.swing.JButton();
        b_modificar = new javax.swing.JButton();
        b_borrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        combo_buscar = new javax.swing.JComboBox();
        texto_buscar = new javax.swing.JTextField();
        check_mayusculas = new javax.swing.JCheckBox();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Perfiles Psicológicos");

        tabla_perfiles.setModel(LPerfiles);
        jScrollPane1.setViewportView(tabla_perfiles);

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

        b_borrar.setText("Borrar");
        b_borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_borrarActionPerformed(evt);
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
                    .addComponent(b_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_borrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(combo_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(check_mayusculas))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_nuevo)
                        .addGap(18, 18, 18)
                        .addComponent(b_modificar)
                        .addGap(18, 18, 18)
                        .addComponent(b_borrar)
                        .addGap(0, 330, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_nuevoActionPerformed
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
    }//GEN-LAST:event_b_nuevoActionPerformed

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
                        JOptionPane.showMessageDialog(null, "No se puede eliminar categoria");
                    }
                actualizartabla();
                }
                           }
        }
        else{
            JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla");
        }
    }//GEN-LAST:event_b_borrarActionPerformed

    private void texto_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texto_buscarActionPerformed

    }//GEN-LAST:event_texto_buscarActionPerformed

    private void texto_buscarInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_texto_buscarInputMethodTextChanged

    }//GEN-LAST:event_texto_buscarInputMethodTextChanged

    private void texto_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_texto_buscarKeyPressed

    }//GEN-LAST:event_texto_buscarKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_borrar;
    private javax.swing.JButton b_modificar;
    private javax.swing.JButton b_nuevo;
    private javax.swing.JCheckBox check_mayusculas;
    private javax.swing.JComboBox combo_buscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_perfiles;
    private javax.swing.JTextField texto_buscar;
    // End of variables declaration//GEN-END:variables
}
