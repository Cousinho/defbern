package Reportes;

import BaseDeDatos.BDRegistros;
import BaseDeDatos.Conexion_BD;
import Entidades.Registro;
import Util.Bloqueo;
import Util.FormatoFecha;
import Util.TablaModelo;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class P_Reportes extends javax.swing.JInternalFrame {

    public P_Reportes() {
        initComponents();
        actualizartabla();
    }
    TablaModelo LRegistros = new TablaModelo();
    TableRowSorter sorter = new TableRowSorter(LRegistros);
    private Registro registro_actual=new Registro();
    boolean seleccion=false;
    java.awt.Frame pantalla_padre;
    private ArrayList<String> permisos_actuales = new ArrayList();
    
    P_Reportes(java.awt.Frame Pantalla_padre, ArrayList<String> permisos) {
        Pantalla_padre=pantalla_padre;
        permisos_actuales = permisos;
        initComponents();
        BloquearComponentes();
        HabilitarComponentes();
        tabla_registros.setRowSorter(sorter);
        actualizartabla();
        Buscar();
    }
    
    private void BloquearComponentes() {
        b_reportes.setEnabled(false);
    }

    private void HabilitarComponentes() {
        if(permisos_actuales.indexOf("eliminar registros")!=-1){
            b_reportes.setEnabled(true);
        }
    }
    
    //Metodo que actualiza la tabla de productos
    public void actualizartabla(){
        limpiartabla();
        String titulos[] = {"Código","CI","Nombre","Apellido","Descripción","Fecha","Usuario","Grupo"};
        LRegistros.setColumnIdentifiers(titulos);
        TableColumnModel m = tabla_registros.getColumnModel();
        FormatoFecha formato=new FormatoFecha();
        m.getColumn(5).setCellRenderer(formato);
        try {
            for (Iterator<Registro> it = BDRegistros.Lista() .iterator(); it.hasNext();) {
                Registro registro = it.next();
                Object Datos[] = {String.valueOf(registro.getCodigo()),
                                  String.valueOf(registro.getCi()),
                                  registro.getNombre(),
                                  registro.getApellido(),
                                  registro.getDescripcion(),
                                  registro.getFecha(),
                                  String.valueOf(registro.getUsuario().getNombre()),
                                  String.valueOf(registro.getCodigo_grupo())
                                  };
                LRegistros.addRow(Datos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
    }
     
    //Metodo que limpia la tabla de Productos
    public void limpiartabla(){
        int tamaño =LRegistros.getRowCount()-1;
        for(int i=tamaño; i>=0;i--){
              LRegistros.removeRow(i );
        }
    }
    
    private void Filtro() {
        RowFilter<TableModel, Object> rf = null;
        List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
        filters.add(RowFilter.regexFilter(texto_codigo.getText(),0));
        filters.add(RowFilter.regexFilter(texto_cedula.getText() ,1));
        if(check_mayusculas.isSelected()==true){
            filters.add(RowFilter.regexFilter(texto_nombre.getText(),2));
            filters.add(RowFilter.regexFilter(texto_apellido.getText(),3));
            filters.add(RowFilter.regexFilter(texto_usuario.getText(),6));
        }else{
            filters.add(RowFilter.regexFilter(("(?i)")+texto_nombre.getText(),2));
            filters.add(RowFilter.regexFilter(("(?i)")+texto_apellido.getText(),3));
            filters.add(RowFilter.regexFilter(("(?i)")+texto_usuario.getText(),6));
        }
       filters.add(RowFilter.regexFilter(texto_grupo.getText() ,7));
       if(check_fecha.isSelected()==true){
            java.util.Date FechaInicio=fecha_inicio.getCurrent().getTime();
            java.sql.Date FechaInicioSql = new java.sql.Date(FechaInicio.getTime());
            filters.add(RowFilter.dateFilter(RowFilter.ComparisonType.AFTER, FechaInicioSql,5));
            java.util.Date FechaFin=fecha_fin.getCurrent().getTime();
            java.sql.Date FechaFinSql = new java.sql.Date(FechaFin.getTime());
            filters.add(RowFilter.dateFilter(RowFilter.ComparisonType.BEFORE, FechaFinSql, 5));
        }
        RowFilter<Object,Object> fooBarFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(fooBarFilter);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_registros = new javax.swing.JTable();
        b_reportes = new javax.swing.JButton();
        check_mayusculas = new javax.swing.JCheckBox();
        check_fecha = new javax.swing.JCheckBox();
        fecha_inicio = new datechooser.beans.DateChooserCombo();
        jLabel6 = new javax.swing.JLabel();
        fecha_fin = new datechooser.beans.DateChooserCombo();
        jLabel7 = new javax.swing.JLabel();
        check_filtro = new javax.swing.JCheckBox();
        panel_filtro = new javax.swing.JPanel();
        texto_nombre = new javax.swing.JTextField();
        texto_apellido = new javax.swing.JTextField();
        texto_usuario = new javax.swing.JTextField();
        texto_grupo = new javax.swing.JTextField();
        texto_cedula = new javax.swing.JTextField();
        texto_codigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        b_cedula = new javax.swing.JButton();

        setClosable(true);
        setTitle("Registro de Entrevistas");

        tabla_registros.setModel(LRegistros);
        jScrollPane1.setViewportView(tabla_registros);

        b_reportes.setText("Generar Reporte");
        b_reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_reportesActionPerformed(evt);
            }
        });

        check_mayusculas.setText("Diferenciar mayúsculas y minúsculas");
        check_mayusculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_mayusculasActionPerformed(evt);
            }
        });

        check_fecha.setText("Busqueda por fecha");
        check_fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_fechaActionPerformed(evt);
            }
        });

        fecha_inicio.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                fecha_inicioOnSelectionChange(evt);
            }
        });

        jLabel6.setText("Fecha Inicio");

        fecha_fin.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                fecha_finOnSelectionChange(evt);
            }
        });

        jLabel7.setText("Fecha Fin");

        check_filtro.setText("Filtrar");
        check_filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_filtroActionPerformed(evt);
            }
        });

        texto_nombre.setEnabled(false);

        texto_apellido.setEnabled(false);

        texto_usuario.setEnabled(false);

        texto_grupo.setEnabled(false);
        texto_grupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texto_grupoActionPerformed(evt);
            }
        });

        texto_cedula.setEnabled(false);
        texto_cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texto_cedulaActionPerformed(evt);
            }
        });

        texto_codigo.setEnabled(false);
        texto_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texto_codigoActionPerformed(evt);
            }
        });

        jLabel2.setText("Codigo");

        jLabel3.setText("Cédula");

        jLabel4.setText("Nombre");

        jLabel5.setText("Apellido");

        jLabel9.setText("Usuario");

        jLabel8.setText("Código Grupo");

        javax.swing.GroupLayout panel_filtroLayout = new javax.swing.GroupLayout(panel_filtro);
        panel_filtro.setLayout(panel_filtroLayout);
        panel_filtroLayout.setHorizontalGroup(
            panel_filtroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_filtroLayout.createSequentialGroup()
                .addGroup(panel_filtroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(texto_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(15, 15, 15)
                .addGroup(panel_filtroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(texto_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(15, 15, 15)
                .addGroup(panel_filtroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(texto_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(15, 15, 15)
                .addGroup(panel_filtroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(texto_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(15, 15, 15)
                .addGroup(panel_filtroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(texto_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(panel_filtroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(texto_grupo)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1))
        );

        panel_filtroLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {texto_apellido, texto_cedula, texto_codigo, texto_grupo, texto_nombre, texto_usuario});

        panel_filtroLayout.setVerticalGroup(
            panel_filtroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_filtroLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(panel_filtroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_filtroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto_grupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_filtroLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {texto_apellido, texto_grupo, texto_usuario});

        panel_filtroLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {texto_cedula, texto_codigo, texto_nombre});

        b_cedula.setText("Reporte por Cedula");
        b_cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cedulaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(check_filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(check_mayusculas)
                        .addGap(526, 526, 526)
                        .addComponent(check_fecha))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(b_cedula, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(b_reportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panel_filtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(fecha_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(fecha_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1))))
                .addGap(13, 13, 13))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(check_fecha))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(check_mayusculas)
                            .addComponent(check_filtro))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fecha_inicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fecha_fin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(panel_filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_reportes, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(b_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 161, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_reportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_reportesActionPerformed
        //recibe numero de fila seleccionada
        Connection conexion = Conexion_BD.getConnection();
        Object fila= tabla_registros.getValueAt(tabla_registros.getSelectedRow(),0);
        File archivo=new File("reportes/ReporteIndividual.jasper");
        JasperReport reporte = null;
        try {
            reporte = (JasperReport) JRLoader.loadObject(archivo);
        } catch (JRException ex) {
            Logger.getLogger(P_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
             Map parametros = new HashMap();
             parametros.put("id_registro",Integer.valueOf(fila.toString()));
            JasperPrint reporte_view = JasperFillManager.fillReport( reporte, parametros, conexion);
            JasperViewer.viewReport( reporte_view,false ); 
        } catch (JRException ex) {
              Logger.getLogger(P_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_b_reportesActionPerformed
   
    
    private void Buscar(){
        texto_cedula.getDocument().addDocumentListener(
            new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    Filtro();
                }
                public void insertUpdate(DocumentEvent e) {
                    Filtro();
                }
                public void removeUpdate(DocumentEvent e) {
                    Filtro();
                }
            });
        texto_codigo.getDocument().addDocumentListener(
            new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    Filtro();
                }
                public void insertUpdate(DocumentEvent e) {
                   Filtro();
                }
                public void removeUpdate(DocumentEvent e) {
                    Filtro();
                }
            });
        texto_nombre.getDocument().addDocumentListener(
            new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    Filtro();
                }
                public void insertUpdate(DocumentEvent e) {
                    Filtro();
                }
                public void removeUpdate(DocumentEvent e) {
                    Filtro();
                }
            });
        texto_apellido.getDocument().addDocumentListener(
            new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    Filtro();
                }
                public void insertUpdate(DocumentEvent e) {
                    Filtro();
                }
                public void removeUpdate(DocumentEvent e) {
                    Filtro();
                }
            });
            texto_usuario.getDocument().addDocumentListener(
            new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    Filtro();
                }
                public void insertUpdate(DocumentEvent e) {
                    Filtro();
                }
                public void removeUpdate(DocumentEvent e) {
                    Filtro();
                }
            });
            
            texto_grupo.getDocument().addDocumentListener(
            new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    Filtro();
                }
                public void insertUpdate(DocumentEvent e) {
                    Filtro();
                }
                public void removeUpdate(DocumentEvent e) {
                    Filtro();
                }
            });
    }
    private void texto_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texto_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texto_codigoActionPerformed

    private void check_mayusculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_mayusculasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_mayusculasActionPerformed

    private void fecha_inicioOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_fecha_inicioOnSelectionChange
        Filtro();
    }//GEN-LAST:event_fecha_inicioOnSelectionChange

    private void fecha_finOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_fecha_finOnSelectionChange
        Filtro();
    }//GEN-LAST:event_fecha_finOnSelectionChange

    private void check_fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_fechaActionPerformed
        if(check_fecha.isSelected()==false){
            Filtro();
        }
    }//GEN-LAST:event_check_fechaActionPerformed

    private void texto_cedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texto_cedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texto_cedulaActionPerformed

    private void texto_grupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texto_grupoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texto_grupoActionPerformed

    private void check_filtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_filtroActionPerformed
        if(check_filtro.isSelected()==true){
            Bloqueo.setEnableContainer(panel_filtro, true);
        }else{
            Bloqueo.setEnableContainer(panel_filtro, false);
            texto_codigo.setText("");
            texto_cedula.setText("");
            texto_nombre.setText("");
            texto_apellido.setText("");
            texto_usuario.setText("");
            texto_grupo.setText("");
        }
    }//GEN-LAST:event_check_filtroActionPerformed

    private void b_cedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cedulaActionPerformed
         //recibe numero de fila seleccionada
        Connection conexion = Conexion_BD.getConnection();
        Object fila= tabla_registros.getValueAt(tabla_registros.getSelectedRow(),1);
        File archivo=new File("reportes/ReporteCedula.jasper");
        JasperReport reporte = null;
        try {
            reporte = (JasperReport) JRLoader.loadObject(archivo);
        } catch (JRException ex) {
            Logger.getLogger(P_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
             Map parametros = new HashMap();
             parametros.put("cedula",Integer.valueOf(fila.toString()));
            JasperPrint reporte_view = JasperFillManager.fillReport( reporte, parametros, conexion);
            JasperViewer.viewReport( reporte_view,false ); 
        } catch (JRException ex) {
              Logger.getLogger(P_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_b_cedulaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_cedula;
    private javax.swing.JButton b_reportes;
    private javax.swing.JCheckBox check_fecha;
    private javax.swing.JCheckBox check_filtro;
    private javax.swing.JCheckBox check_mayusculas;
    private datechooser.beans.DateChooserCombo fecha_fin;
    private datechooser.beans.DateChooserCombo fecha_inicio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel_filtro;
    private javax.swing.JTable tabla_registros;
    private javax.swing.JTextField texto_apellido;
    private javax.swing.JTextField texto_cedula;
    private javax.swing.JTextField texto_codigo;
    private javax.swing.JTextField texto_grupo;
    private javax.swing.JTextField texto_nombre;
    private javax.swing.JTextField texto_usuario;
    // End of variables declaration//GEN-END:variables
}
