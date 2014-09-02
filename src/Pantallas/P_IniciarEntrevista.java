package Pantallas;

import BaseDeDatos.BDPerfiles;
import Entidades.Perfil;
import Entidades.Registro;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class P_IniciarEntrevista extends javax.swing.JInternalFrame {
    boolean seleccion;
    java.awt.Frame pantalla_padre;
    Registro registro_actual=new Registro();
    private int codigo_grupo=0;
    private Perfil perfil_actual=new Perfil();
    public P_IniciarEntrevista(java.awt.Frame padre,boolean grupal) throws SQLException {
        System.loadLibrary("opencv_java249");
        initComponents();
        b_entrevista.disable();
        pantalla_padre = padre;
        if(grupal){
            Seleccionar();
        }
        texto_grupo.setText(String.valueOf(codigo_grupo));
        perfil_actual.setDescripcion("");
        perfil_actual.setCodigo(BDPerfiles.insertar(perfil_actual));
    }
    
    public void SetCodigo(int codigo){
        codigo_grupo=codigo;
    }
    public void Seleccionar(){
        D_Codigo seleccionar=new D_Codigo(pantalla_padre,true,this);
        seleccionar.setLocationRelativeTo(null);
        seleccionar.setVisible(true);
        seleccionar.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                if(codigo_grupo==0){
                    dispose();
                }                
        } 
        });
        
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
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        texto_codigo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        texto_ci = new javax.swing.JTextField();
        b_cancelar = new javax.swing.JButton();
        texto_nombre = new javax.swing.JTextField();
        lista_usuarios = new javax.swing.JComboBox();
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

        jLabel6.setText("Usuario");

        jLabel4.setText("Apellido");

        jLabel5.setText("Descripcion");

        b_cancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b_cancelar.setText("Cancelar");
        b_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cancelarActionPerformed(evt);
            }
        });

        lista_usuarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(b_aceptar))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(texto_grupo)
                        .addComponent(texto_codigo)
                        .addComponent(texto_nombre)
                        .addComponent(texto_ci)
                        .addComponent(texto_apellido)
                        .addComponent(texto_descripcion)
                        .addComponent(texto_perfil, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(lista_usuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(b_cancelar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_entrevista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b_analizar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b_informe)))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lista_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(texto_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(texto_grupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(b_cancelar)
                                    .addComponent(b_entrevista)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(b_aceptar)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b_informe)
                            .addComponent(b_analizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_b_cancelarActionPerformed

    private void b_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_aceptarActionPerformed
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JComboBox lista_usuarios;
    private javax.swing.JTextField texto_apellido;
    private javax.swing.JTextField texto_ci;
    private javax.swing.JTextField texto_codigo;
    private javax.swing.JTextField texto_descripcion;
    private javax.swing.JTextField texto_grupo;
    private javax.swing.JTextField texto_nombre;
    private javax.swing.JTextField texto_perfil;
    // End of variables declaration//GEN-END:variables
}
