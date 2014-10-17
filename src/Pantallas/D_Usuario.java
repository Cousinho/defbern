package Pantallas;

import BaseDeDatos.BDRoles;
import BaseDeDatos.BDUsuarios;
import Entidades.Rol;
import Entidades.Usuario;
import Util.AlfanumericoEspacio;
import Util.Numerico;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class D_Usuario extends javax.swing.JDialog {

    boolean nuevo;
    Usuario usuario_actual=new Usuario() {};
    public D_Usuario(java.awt.Frame parent, boolean modal) throws SQLException{
        //bloque la ventana padre de jdialog
        super(parent, modal);
        //varible que se usa para indicar que el es una edición de datos o una inserción
        nuevo=true;
        initComponents();
        //llama a metodo que formatea metodo de ingreso de campos
        FormatoCampos();
        CargarBox();
    }
    
    
    public D_Usuario(java.awt.Frame parent, boolean modal,Usuario usuario) throws SQLException{
        super(parent,modal);
        //varible que se usa para indicar que el es una edición de datos o una inserción
        nuevo=false;
        usuario_actual=usuario;
        initComponents();
        //llama a metodo que formatea metodo de ingreso de campos
        FormatoCampos();
        //carga datos del usuario recibido
        CargarBox();
        CargarDatos();
    }

    private void CargarDatos(){
        texto_codigo.disable();
        texto_codigo.setText(String.valueOf(usuario_actual.getCodigo()));
        texto_nombre.setText(usuario_actual.getNombre());
        lista_roles.setSelectedItem(usuario_actual.getRol().getDescripcion());
        texto_contrasenha.setText(usuario_actual.getContrasenha());
    }
    
    private void FormatoCampos(){
        texto_codigo.setDocument(new Numerico(texto_codigo,11));
        texto_nombre.setDocument(new AlfanumericoEspacio(texto_nombre,20));
        texto_contrasenha.setDocument(new Numerico(texto_contrasenha,11));
    }
    
      
    private boolean Guardar() throws SQLException{
        setDatos();
        try {
            return BDUsuarios.insertar(usuario_actual);
        } catch (SQLException ex) {
            return false;
        }
    }
    
    private boolean Actualizar() throws SQLException{
        setDatos();
        try {
           return BDUsuarios.actualizar(usuario_actual);
           
        } catch (SQLException ex) {
           return false;
        }
    }
    
    
    private void setDatos() throws SQLException{
        usuario_actual.setCodigo(Integer.parseInt(texto_codigo.getText()));
        usuario_actual.setNombre(texto_nombre.getText());
        usuario_actual.setRol(BDRoles.buscarNombre((String) lista_roles.getSelectedItem()));
        usuario_actual.setContrasenha(String.valueOf(texto_contrasenha.getPassword()));
    }

    private void CargarBox() throws SQLException{
        lista_roles.removeAllItems();
        for (Iterator<Rol> it = BDRoles.Lista() .iterator(); it.hasNext();) {
            Rol rol = it.next();
            lista_roles.addItem(rol.getDescripcion());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        texto_codigo = new javax.swing.JTextField();
        texto_nombre = new javax.swing.JTextField();
        b_aceptar = new javax.swing.JButton();
        b_cancelar = new javax.swing.JButton();
        texto_contrasenha = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lista_roles = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Codigo");

        jLabel2.setText("Nombre");

        jLabel3.setText("Rol");

        jLabel4.setText("Contraseña");

        b_aceptar.setText("Aceptar");
        b_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_aceptarActionPerformed(evt);
            }
        });

        b_cancelar.setText("Cancelar");
        b_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cancelarActionPerformed(evt);
            }
        });

        texto_contrasenha.setText("jPasswordField1");

        jPanel1.setBackground(new java.awt.Color(81, 106, 138));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 41));

        jLabel5.setForeground(new java.awt.Color(254, 254, 254));
        jLabel5.setText("Perfiles");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        lista_roles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(b_aceptar)
                        .addGap(57, 57, 57)
                        .addComponent(b_cancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(texto_codigo)
                            .addComponent(texto_nombre)
                            .addComponent(texto_contrasenha, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(lista_roles, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(texto_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(texto_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lista_roles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(texto_contrasenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_aceptar)
                    .addComponent(b_cancelar))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_aceptarActionPerformed
        boolean estado = false;
        if (nuevo==true){
            try {
                estado=Guardar();
            } catch (SQLException ex) {
                Logger.getLogger(D_Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                estado=Actualizar();
            } catch (SQLException ex) {
                Logger.getLogger(D_Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(estado){
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(D_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(D_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(D_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(D_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            private Usuario usuario;
            public void run() {
                    D_Usuario dialog = null;
                try {
                    dialog = new D_Usuario(new javax.swing.JFrame(), true, usuario);
                } catch (SQLException ex) {
                    Logger.getLogger(D_Usuario.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox lista_roles;
    private javax.swing.JTextField texto_codigo;
    private javax.swing.JPasswordField texto_contrasenha;
    private javax.swing.JTextField texto_nombre;
    // End of variables declaration//GEN-END:variables
}
