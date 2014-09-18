package Pantallas;

import BaseDeDatos.BDGrupos;
import BaseDeDatos.BDPerfiles;
import Entidades.Grupo;
import Entidades.Perfil;
import Entidades.Registro;
import Util.AlfanumericoEspacio;
import Util.Numerico;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class P_IniciarEntrevista extends javax.swing.JInternalFrame {
    boolean seleccion;
    java.awt.Frame pantalla_padre;
    Registro registro_actual=new Registro();
    private Grupo grupo_actual=BDGrupos.buscarId(0);
    private Perfil perfil_actual=new Perfil();
    public P_IniciarEntrevista(java.awt.Frame padre,boolean grupal) throws SQLException {
        System.loadLibrary("opencv_java249");
        initComponents();
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
        texto_ci.setDocument(new Numerico(texto_ci,10));
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
    
    private void Guardar(){
        if(texto_nombre.getText().equals("")||texto_apellido.getText().equals("")||
           texto_ci.getText().equals("")||texto_descripcion.getText().equals("")){
             JOptionPane.showMessageDialog(null, "Complete todos los campos necesarios");
        }else{
            registro_actual.setCi(Integer.valueOf(texto_ci.getText()));
            registro_actual.setNombre(texto_nombre.getText());
            registro_actual.setApellido(texto_apellido.getText());
            registro_actual.setDescripcion(texto_descripcion.getText());
            texto_codigo.setEnabled(false);
            texto_ci.setEnabled(false);
            texto_nombre.setEnabled(false);
            texto_apellido.setEnabled(false);
            texto_descripcion.setEnabled(false);
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        texto_perfil = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        texto_codigo = new javax.swing.JTextField();
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
                .addContainerGap(502, Short.MAX_VALUE))
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

        jLabel1.setText("Código");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(texto_grupo)
                            .addComponent(texto_codigo)
                            .addComponent(texto_nombre)
                            .addComponent(texto_ci)
                            .addComponent(texto_apellido)
                            .addComponent(texto_descripcion)
                            .addComponent(texto_perfil, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(texto_descripciongrupo)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_aceptar)
                        .addGap(61, 61, 61)
                        .addComponent(b_cancelar)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(b_entrevista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b_analizar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b_informe)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(texto_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
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
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_cancelar)
                    .addComponent(b_aceptar)
                    .addComponent(b_entrevista, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_analizar)
                    .addComponent(b_informe))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelarActionPerformed
        try {
            BDPerfiles.eliminar(perfil_actual.getCodigo());
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(P_IniciarEntrevista.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_b_cancelarActionPerformed

    private void b_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_aceptarActionPerformed
        Guardar();
        b_entrevista.enable();
    }//GEN-LAST:event_b_aceptarActionPerformed

    private void texto_apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texto_apellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texto_apellidoActionPerformed

    private void b_informeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_informeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_informeActionPerformed
    
    
    private void b_entrevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_entrevistaActionPerformed
        D_Presentacion entrevista = null;
        try {
            entrevista = new D_Presentacion(pantalla_padre,true,perfil_actual);
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
