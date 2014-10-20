package Reportes;

import BaseDeDatos.BDGrupos;
import BaseDeDatos.Conexion_BD;
import Entidades.Grupo;
import Util.TablaModelo;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class P_ReportesGrupos extends javax.swing.JInternalFrame {
    TablaModelo LTabla = new TablaModelo();
    TableRowSorter sorter = new TableRowSorter(LTabla);
    public static java.awt.Frame  pantalla_padre;
    private ArrayList<String> permisos_actuales = new ArrayList();
    private Grupo grupo_actual=new Grupo();
    
    
    public P_ReportesGrupos() {
        initComponents();
        tabla.setRowSorter(sorter);
        //actualizada datos de tablas
        actualizartabla();
        Buscar();
        CargarBox();
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

        b_nuevo.setText("Generar Reporte");
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
                .addGap(157, 157, 157)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combo_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(texto_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_mayusculas)
                .addContainerGap(240, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(b_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)))
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
        //recibe numero de fila seleccionada
        Connection conexion = Conexion_BD.getConnection();
        int fila=tabla.getSelectedRow();
        File archivo=new File("reportes/ReporteGrupal.jasper");
        JasperReport reporte = null;
        try {
            reporte = (JasperReport) JRLoader.loadObject(archivo);
        } catch (JRException ex) {
            Logger.getLogger(P_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
             Map parametros = new HashMap();
             parametros.put("id_grupo",fila);
            JasperPrint reporte_view = JasperFillManager.fillReport( reporte, parametros, conexion);
            JasperViewer.viewReport( reporte_view,false ); 
        } catch (JRException ex) {
              Logger.getLogger(P_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_b_nuevoActionPerformed
    
    private void texto_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texto_buscarActionPerformed

    }//GEN-LAST:event_texto_buscarActionPerformed

    private void texto_buscarInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_texto_buscarInputMethodTextChanged

    }//GEN-LAST:event_texto_buscarInputMethodTextChanged

    private void texto_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_texto_buscarKeyPressed

    }//GEN-LAST:event_texto_buscarKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
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
