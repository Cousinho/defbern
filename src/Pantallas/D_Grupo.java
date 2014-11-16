package Pantallas;

import BaseDeDatos.BDGrupos;
import Entidades.Grupo;
import Util.Numerico;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class D_Grupo extends javax.swing.JDialog {
    boolean nuevo;
    Grupo grupo_actual=new Grupo();
    public D_Grupo(java.awt.Frame parent, boolean modal) {
        //bloque la ventana padre de jdialog
        super(parent, modal);
        //varible que se usa para indicar que el es una edición de datos o una inserción
        nuevo=true;
        initComponents();
        //llama a metodo que formatea metodo de ingreso de campos
        FormatoCampos();
    }
    
    public D_Grupo(java.awt.Frame parent, boolean modal, Grupo grupo){
        //bloque la ventana padre de jdialog
        super(parent,modal);
        //varible que se usa para indicar que el es una edición de datos o una inserción
        nuevo=false;
        grupo_actual=grupo;
        initComponents();
        //llama a metodo que formatea metodo de ingreso de campos
        FormatoCampos();
        //carga datos del perfil recibido
        CargarDatos();
    }
    
     //método que carga datos recibidos
    public void CargarDatos(){
        texto_codigo.disable();
        texto_codigo.setText(String.valueOf(grupo_actual.getCodigo()));
        textoa_descripcion.setText(grupo_actual.getDescripcion());
    }
    
    //método que define el formato de inserción 
     public void FormatoCampos(){
        texto_codigo.setDocument(new Numerico(texto_codigo, 11));
        //textoa_descripcion.setDocument(new Alfanumerico(textoa_descripcion,50));
    }
     
    //método que carga guardo datos nuevos
    private boolean Guardar(){
        boolean guardar;
        grupo_actual.setCodigo(Integer.parseInt(texto_codigo.getText()));
        grupo_actual.setDescripcion(textoa_descripcion.getText());
        try {
            guardar=BDGrupos.insertar(grupo_actual);
        } catch (SQLException ex) {
            return false;
        }
         return guardar;
    }
    
    //método que actualiza datos si esto es una edición
    private boolean Actualizar(){
        grupo_actual.setCodigo(Integer.parseInt(texto_codigo.getText()));
        grupo_actual.setDescripcion(textoa_descripcion.getText());
        try {
            BDGrupos.actualizar(grupo_actual);
        } catch (SQLException ex) {
            return false;
        }
        return true;
    } 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        texto_codigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        b_aceptar = new javax.swing.JButton();
        b_cancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textoa_descripcion = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Descripción");

        jLabel1.setText("Codigo");

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

        jPanel1.setBackground(new java.awt.Color(81, 106, 138));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 41));

        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setText("Grupo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(186, 186, 186))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        textoa_descripcion.setColumns(20);
        textoa_descripcion.setRows(5);
        textoa_descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoa_descripcionKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(textoa_descripcion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_aceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b_cancelar))
                    .addComponent(texto_codigo)
                    .addComponent(jScrollPane1))
                .addGap(20, 20, 20))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(texto_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b_cancelar)
                    .addComponent(b_aceptar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_aceptarActionPerformed
        boolean estado;
        if (nuevo==true){
            estado=Guardar();
        }
        else{
            estado=Actualizar();
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

    private void textoa_descripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoa_descripcionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
              b_aceptarActionPerformed(null);
        }
    }//GEN-LAST:event_textoa_descripcionKeyPressed

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
            java.util.logging.Logger.getLogger(D_Grupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(D_Grupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(D_Grupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(D_Grupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                D_Grupo dialog = new D_Grupo(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField texto_codigo;
    private javax.swing.JTextArea textoa_descripcion;
    // End of variables declaration//GEN-END:variables
}
