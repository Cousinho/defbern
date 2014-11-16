package Pantallas;

import BaseDeDatos.BDRegistros;
import BaseDeDatos.Conexion_BD;
import Entidades.Registro;
import Util.Bloqueo;
import Util.FormatoFecha;
import Util.TablaModelo;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class P_Registro extends javax.swing.JInternalFrame {

    public P_Registro() {
        initComponents();
    }
    TablaModelo LRegistros = new TablaModelo();
    TableRowSorter sorter = new TableRowSorter(LRegistros);
    private Registro registro_actual=new Registro();
    boolean seleccion=false;
    java.awt.Frame pantalla_padre;
    private ArrayList<String> permisos_actuales = new ArrayList();
    
    P_Registro(java.awt.Frame Pantalla_padre, ArrayList<String> permisos) {
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
        b_modificar.setEnabled(false);
        b_eliminar.setEnabled(false);
    }

    private void HabilitarComponentes() {
        if(permisos_actuales.indexOf("modificar registros")!=-1){
            b_modificar.setEnabled(true);
        }
        if(permisos_actuales.indexOf("eliminar registros")!=-1){
            b_eliminar.setEnabled(true);
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
        b_modificar = new javax.swing.JButton();
        b_eliminar = new javax.swing.JButton();
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
        b_reportes = new javax.swing.JButton();

        setClosable(true);
        setTitle("Registro de Entrevistas");

        tabla_registros.setModel(LRegistros);
        jScrollPane1.setViewportView(tabla_registros);

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
                .addGap(1, 1, 1)
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
                    .addComponent(texto_grupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panel_filtroLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {texto_apellido, texto_grupo, texto_usuario});

        panel_filtroLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {texto_cedula, texto_codigo, texto_nombre});

        b_reportes.setText("Generar Reporte");
        b_reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_reportesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(check_filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(check_mayusculas))
                    .addComponent(panel_filtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(fecha_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(fecha_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(check_fecha)
                        .addGap(209, 209, 209))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b_reportes, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(b_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1176, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(check_fecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fecha_inicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fecha_fin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(check_mayusculas)
                            .addComponent(check_filtro))
                        .addGap(6, 6, 6)
                        .addComponent(panel_filtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_eliminar)
                        .addGap(11, 11, 11)
                        .addComponent(b_modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(b_reportes)
                        .addGap(0, 122, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {b_eliminar, b_modificar});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_modificarActionPerformed
        int columna = tabla_registros.getSelectedRow();
        if (columna!=-1){
            try {
                Registro p_envia=new Registro() {};
                Object valor =tabla_registros.getValueAt(columna, 0);
                p_envia=BDRegistros.buscarId(Integer.parseInt(valor.toString()));
                D_Registro editar= new D_Registro(pantalla_padre,true,p_envia);
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
        int fila=tabla_registros.getSelectedRow();

        //verifica que este seleccionada una fila
        if (fila!=-1){
            int seleccion = JOptionPane.showOptionDialog(
                this, // Componente padre
                "¿Desea eliminar este registro?",
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
                    Object valor = tabla_registros.getValueAt(fila, 0);
                    try {
                        //solicita eliminar fila selecionada
                        BDRegistros.eliminar(Integer.parseInt(valor.toString()));
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "No se puede eliminar registro");

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

    private void b_reportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_reportesActionPerformed
        //recibe numero de fila seleccionada
        Connection conexion = Conexion_BD.getConnection();
        int fila=tabla_registros.getSelectedRow();
        File archivo=new File("reportes/ReporteIndividual.jasper");
        JasperReport reporte = null;
        try {
            reporte = (JasperReport) JRLoader.loadObject(archivo);
        } catch (JRException ex) {
            Logger.getLogger(P_Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Map parametros = new HashMap();
            parametros.put("id_registro",Integer.valueOf(tabla_registros.getValueAt(fila, 0).toString()));
            JasperPrint reporte_view = JasperFillManager.fillReport( reporte, parametros, conexion);
            JasperViewer.viewReport( reporte_view,false);
            byte[] bytes = JasperRunManager.runReportToPdf(archivo.getPath(), parametros, conexion);
        } catch (JRException ex) {
            Logger.getLogger(P_Registro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_b_reportesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_eliminar;
    private javax.swing.JButton b_modificar;
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
