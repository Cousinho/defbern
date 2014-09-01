/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pantallas;

import BaseDeDatos.BDRegistros;
import BaseDeDatos.BDUsuarios;
import Entidades.Registro;
import Entidades.Usuario;
import Util.Alfanumerico;
import Util.AlfanumericoEspacio;
import Util.Numerico;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Cousiño
 */
public class D_Entrevista extends javax.swing.JDialog {

    /**
     * Creates new form P_EntrevistaIndividual
     */
    private boolean nuevo;
    private Registro registro_actual = new Registro();
    private boolean individual = false;
    
    public D_Entrevista() {
        initComponents();
    }

    D_Entrevista(java.awt.Frame parent, boolean modal) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        super(parent, modal);
        initComponents();
        nuevo=true;
        individual=true;
        FormatoCampos();
    }

    D_Entrevista(java.awt.Frame parent, boolean modal,Registro registro) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        super(parent, modal);
        registro_actual = registro;
        initComponents();
        nuevo=false;
        FormatoCampos();
        CargarDatos();
    }
    
    private void FormatoCampos(){
        texto_codigo.setDocument(new Numerico(texto_codigo,11));
        texto_nombre.setDocument(new AlfanumericoEspacio(texto_nombre,45));
        texto_apellido.setDocument(new AlfanumericoEspacio(texto_apellido,45));
        texto_ci.setDocument(new Numerico(texto_ci,45));
        texto_descripcion.setDocument(new Alfanumerico(texto_descripcion,45));
        texto_perfil.setDocument(new Numerico(texto_perfil,11));
    }

    private void CargarDatos(){
        texto_codigo.disable();
        texto_codigo.setText(String.valueOf(registro_actual.getCodigo()));
        texto_nombre.setText(registro_actual.getNombre());
        texto_apellido.setText(registro_actual.getApellido());
        texto_ci.setText(String.valueOf(registro_actual.getCi()));
        texto_descripcion.setText(registro_actual.getDescripcion());
        lista_usuarios.setSelectedItem(registro_actual.getUsuario().getNombre());
        texto_perfil.setText(String.valueOf(registro_actual.getPerfil()));
    }
    
    private void CargarBox() {
        lista_usuarios.removeAllItems();
        try {
            for (Iterator<Usuario> it = BDUsuarios.Lista().iterator(); it.hasNext();) {
                Usuario usuario = it.next();
                lista_usuarios.addItem(usuario.getNombre());
            }
        } catch (SQLException ex) {
            Logger.getLogger(D_Entrevista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private boolean Guardar(){
        setDatos();
        try {
            return BDRegistros.insertar(registro_actual);
        } catch (SQLException ex) {
            return false;
        }
    }
    
    private boolean Actualizar(){
        setDatos();
        try {
           return BDRegistros.actualizar(registro_actual);
           
        } catch (SQLException ex) {
           return false;
        }
    }
    
    private void setDatos(){
        registro_actual.setCodigo(Integer.parseInt(texto_codigo.getText()));
        registro_actual.setNombre(texto_nombre.getText());
        registro_actual.setApellido(texto_apellido.getText());
        registro_actual.setCi(Integer.parseInt(texto_ci.getText()));
        registro_actual.setDescripcion(texto_descripcion.getText());
        try{
            registro_actual.setUsuario(BDUsuarios.buscarUsuario(String.valueOf(lista_usuarios.getSelectedItem())));
        } catch(SQLException ex){
            
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        texto_codigo = new javax.swing.JTextField();
        texto_ci = new javax.swing.JTextField();
        texto_nombre = new javax.swing.JTextField();
        texto_apellido = new javax.swing.JTextField();
        texto_descripcion = new javax.swing.JTextField();
        texto_perfil = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        b_aceptar = new javax.swing.JButton();
        lista_usuarios = new javax.swing.JComboBox();
        b_cancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        texto_apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texto_apellidoActionPerformed(evt);
            }
        });

        jLabel1.setText("Codigo");

        jLabel2.setText("CI");

        jLabel3.setText("Nombre");

        jLabel4.setText("Apellido");

        jLabel5.setText("Descripcion");

        jLabel6.setText("Usuario");

        jLabel7.setText("Perfil");

        b_aceptar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b_aceptar.setText("Aceptar");
        b_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_aceptarActionPerformed(evt);
            }
        });

        lista_usuarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        b_cancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b_cancelar.setText("Cancelar");
        b_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cancelarActionPerformed(evt);
            }
        });

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
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(texto_codigo)
                            .addComponent(texto_nombre)
                            .addComponent(texto_ci)
                            .addComponent(texto_apellido)
                            .addComponent(texto_descripcion)
                            .addComponent(texto_perfil, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(lista_usuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_aceptar)
                        .addGap(40, 40, 40)
                        .addComponent(b_cancelar)
                        .addGap(57, 57, 57)))
                .addContainerGap(46, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto_ci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
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
                    .addComponent(jLabel6)
                    .addComponent(lista_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_aceptar)
                    .addComponent(b_cancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void texto_apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texto_apellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texto_apellidoActionPerformed

    private void b_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_aceptarActionPerformed
        boolean estado;
        if (nuevo==true){
            estado=Guardar();
        }
        else{
            estado=Actualizar();
        }
        if(estado){
            if (individual){
                
            }
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "No se pudo guardar datos");
        }
    }//GEN-LAST:event_b_aceptarActionPerformed

    private void b_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_b_cancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(D_Entrevista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            private Registro registro;
            public void run() {
                    D_Entrevista dialog = new D_Entrevista(new javax.swing.JFrame(), true, registro);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_aceptar;
    private javax.swing.JButton b_cancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox lista_usuarios;
    private javax.swing.JTextField texto_apellido;
    private javax.swing.JTextField texto_ci;
    private javax.swing.JTextField texto_codigo;
    private javax.swing.JTextField texto_descripcion;
    private javax.swing.JTextField texto_nombre;
    private javax.swing.JTextField texto_perfil;
    // End of variables declaration//GEN-END:variables
}
