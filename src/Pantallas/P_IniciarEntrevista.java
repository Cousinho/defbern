package Pantallas;

import BaseDeDatos.BDEntrevistas;
import BaseDeDatos.BDGrupos;
import BaseDeDatos.BDPerfiles;
import BaseDeDatos.BDRegistros;
import BaseDeDatos.BDUsuarios;
import Entidades.Entrevista;
import Entidades.Grupo;
import Entidades.Perfil;
import Entidades.Registro;
import Entidades.Usuario;
import Util.AlfanumericoEspacio;
import Util.Numerico;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class P_IniciarEntrevista extends javax.swing.JInternalFrame {
    private Usuario usuario_actual=new Usuario();
    private java.awt.Frame pantalla_padre;
    private Registro registro_actual=new Registro();
    private Grupo grupo_actual=BDGrupos.buscarId(0);
    private Perfil perfil_actual=new Perfil();
    private java.util.Date fecha_actual = new java.util.Date();
    private String[] Respuestas;
    public P_IniciarEntrevista(java.awt.Frame padre,boolean grupal,Usuario usuario) throws SQLException {
        System.loadLibrary("opencv_java249");
        initComponents();
        usuario_actual=usuario;
        b_entrevista.setEnabled(false);
        b_analizar.setEnabled(false);
        b_informe.setEnabled(false);
        texto_perfil.setEnabled(false);
        pantalla_padre = padre;
        if(grupal){
            Seleccionar();
        }
        FormatoCampos();
        CargarDatos();
        CargarBox();
    }
    
    
    public void SetCodigo(Grupo grupo){
        grupo_actual=grupo;
    }
    public void Seleccionar(){
        D_Codigo seleccionar=new D_Codigo(pantalla_padre,true,this);
        seleccionar.setLocationRelativeTo(null);
        seleccionar.setVisible(true);
        seleccionar.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                if(grupo_actual.getCodigo()==0){
                    dispose();
                }                
        } 
        });
        
    }
    
    private void FormatoCampos(){
        texto_ci.setDocument(new Numerico(texto_ci,15));
        texto_nombre.setDocument(new AlfanumericoEspacio(texto_nombre, 50));
        texto_apellido.setDocument(new AlfanumericoEspacio(texto_apellido,50));
        texto_descripcion.setDocument(new AlfanumericoEspacio(texto_descripcion,100));
    }
    
    private void CargarDatos() throws SQLException{
        perfil_actual.setDescripcion("");
        perfil_actual.setCodigo(BDPerfiles.insertar(perfil_actual));
        texto_grupo.setText(String.valueOf(grupo_actual.getCodigo()));
        texto_descripciongrupo.setText(grupo_actual.getDescripcion());
        texto_perfil.setText(String.valueOf(perfil_actual.getCodigo()));

    }
    
    private void CargarBox(){
        lista_entrevistas.removeAllItems();
        try {
            for (Iterator<Entrevista> it = BDEntrevistas.Lista().iterator(); it.hasNext();) {
                Entrevista distrito = it.next();
                lista_entrevistas.addItem(distrito.getNombre());
            }
        } catch (SQLException ex) {
            Logger.getLogger(P_IniciarEntrevista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Guardar(){
        if(texto_nombre.getText().equals("")||texto_apellido.getText().equals("")||
           texto_ci.getText().equals("")||texto_descripcion.getText().equals("")){
             JOptionPane.showMessageDialog(null, "Complete todos los campos necesarios");
        }else{
                registro_actual.setCi(Integer.valueOf(texto_ci.getText()));
                registro_actual.setNombre(texto_nombre.getText());
                registro_actual.setApellido(texto_apellido.getText());
                registro_actual.setDescripcion(texto_descripcion.getText());
                registro_actual.setPerfil(perfil_actual);
                registro_actual.setCodigo_grupo(grupo_actual.getCodigo());
                registro_actual.setUsuario(usuario_actual);
                registro_actual.setRespuestas(Respuestas);
                java.sql.Date fecha=new java.sql.Date(fecha_actual.getTime());
                registro_actual.setFecha(fecha);
                try {
                    registro_actual.setCodigo(BDRegistros.insertar(registro_actual));
                } catch (SQLException ex) {
                    Logger.getLogger(P_IniciarEntrevista.class.getName()).log(Level.SEVERE, null, ex);
                }
                texto_ci.setEnabled(false);
                texto_nombre.setEnabled(false);
                texto_apellido.setEnabled(false);
                texto_descripcion.setEnabled(false);
                
            
            
        }
        
    }
    
    public void setRespuestas(String[] respuestas){
        Respuestas=respuestas;
        Guardar();
        texto_codigo.setText(String.valueOf(registro_actual.getCodigo()));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        texto_perfil = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        texto_ci = new javax.swing.JTextField();
        b_cancelar = new javax.swing.JButton();
        texto_nombre = new javax.swing.JTextField();
        b_aceptar = new javax.swing.JButton();
        texto_descripcion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        texto_apellido = new javax.swing.JTextField();
        b_analizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        b_informe = new javax.swing.JButton();
        texto_grupo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        b_entrevista = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        texto_descripciongrupo = new javax.swing.JTextField();
        lista_entrevistas = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        texto_codigo = new javax.swing.JTextField();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(81, 106, 138));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 41));

        jLabel8.setForeground(new java.awt.Color(254, 254, 254));
        jLabel8.setText("Entrevistas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        jLabel2.setText("CI");

        jLabel3.setText("Nombre");

        jLabel4.setText("Apellido");

        jLabel5.setText("Descripcion");

        b_cancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b_cancelar.setText("Cancelar");
        b_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cancelarActionPerformed(evt);
            }
        });

        b_aceptar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b_aceptar.setText("Aceptar");
        b_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_aceptarActionPerformed(evt);
            }
        });

        jLabel7.setText("Perfil");

        texto_apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texto_apellidoActionPerformed(evt);
            }
        });

        b_analizar.setText("Analizar");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        b_informe.setText("Generar Informe");
        b_informe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_informeActionPerformed(evt);
            }
        });

        texto_grupo.setEditable(false);

        jLabel9.setText("Código Grupo");

        b_entrevista.setText("Inicar Entrevista");
        b_entrevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_entrevistaActionPerformed(evt);
            }
        });

        jLabel10.setText("Descripción Grupo");

        texto_descripciongrupo.setEditable(false);

        lista_entrevistas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Código");

        texto_codigo.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel1))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(texto_grupo)
                            .addComponent(texto_nombre)
                            .addComponent(texto_ci)
                            .addComponent(texto_apellido)
                            .addComponent(texto_descripcion)
                            .addComponent(texto_perfil, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(texto_descripciongrupo)
                            .addComponent(texto_codigo)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_aceptar)
                        .addGap(61, 61, 61)
                        .addComponent(b_cancelar)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_entrevista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lista_entrevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b_analizar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b_informe))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {b_aceptar, b_analizar, b_cancelar, b_entrevista, b_informe, lista_entrevistas});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(texto_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(texto_ci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(texto_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(texto_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(texto_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(texto_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(texto_grupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(texto_descripciongrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(b_entrevista, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lista_entrevistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(b_analizar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(b_informe, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(394, 394, 394)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b_aceptar)
                            .addComponent(b_cancelar))))
                .addGap(39, 39, 39))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {b_aceptar, b_analizar, b_cancelar, b_entrevista, b_informe, lista_entrevistas});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelarActionPerformed
        if(registro_actual == null){
            try {
                BDPerfiles.eliminar(perfil_actual.getCodigo());
                this.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(P_IniciarEntrevista.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }else{
            this.dispose();
        }
        
        
    }//GEN-LAST:event_b_cancelarActionPerformed

    private void b_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_aceptarActionPerformed
        b_entrevista.setEnabled(true);
    }//GEN-LAST:event_b_aceptarActionPerformed

    private void texto_apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texto_apellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texto_apellidoActionPerformed

    private void b_informeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_informeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_informeActionPerformed
    
    
    private void b_entrevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_entrevistaActionPerformed
        P_Presentacion1 entrevista = null;
        try {
            entrevista = new P_Presentacion1(BDEntrevistas.buscarNombre((String) lista_entrevistas.getSelectedItem()),perfil_actual.getCodigo(),this);
        } catch (SQLException ex) {
            Logger.getLogger(P_IniciarEntrevista.class.getName()).log(Level.SEVERE, null, ex);
        }
        entrevista.setLocationRelativeTo(null);
        entrevista.setVisible(true);
    }//GEN-LAST:event_b_entrevistaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_aceptar;
    private javax.swing.JButton b_analizar;
    private javax.swing.JButton b_cancelar;
    private javax.swing.JButton b_entrevista;
    private javax.swing.JButton b_informe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JComboBox lista_entrevistas;
    private javax.swing.JTextField texto_apellido;
    private javax.swing.JTextField texto_ci;
    private javax.swing.JTextField texto_codigo;
    private javax.swing.JTextField texto_descripcion;
    private javax.swing.JTextField texto_descripciongrupo;
    private javax.swing.JTextField texto_grupo;
    private javax.swing.JTextField texto_nombre;
    private javax.swing.JTextField texto_perfil;
    // End of variables declaration//GEN-END:variables
}
