package Pantallas;

import BaseDeDatos.BDLaminas;
import Entidades.Entrevista;
import Entidades.Lamina;
import Util.TablaModelo;
import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableRowSorter;


public class D_Lamina extends javax.swing.JDialog {
    boolean nuevo;
    Lamina lamina_actual =new Lamina() ;
    Entrevista entrevista_actual=new Entrevista();
    TablaModelo LOpciones = new TablaModelo();
    TableRowSorter sorter = new TableRowSorter(LOpciones);
    
    public D_Lamina(java.awt.Frame parent, boolean modal,Entrevista entrevista) {
        super(parent, modal);
        nuevo=true;
        entrevista_actual=entrevista;
        initComponents();
    }
    

    
    public D_Lamina(java.awt.Frame parent, boolean modal, Lamina lamina){
        //bloque la ventana padre de jdialog
        super(parent,modal);
        //varible que se usa para indicar que el es una edición de datos o una inserción
        nuevo=false;
        lamina_actual=lamina;
        initComponents();
        CargarDatos();
    }
    
      
    public void CargarDatos(){
        texto_peso.disable();
        texto_peso.setText(String.valueOf(lamina_actual.getPeso()));
        Icon icono = new ImageIcon(lamina_actual.getImagen().getScaledInstance(lamina.getWidth(), lamina.getHeight(), Image.SCALE_DEFAULT));
        lamina.setIcon(icono);
    }
    
     
    //método que carga guardo datos nuevos
    private boolean Guardar(){
        boolean guardar=true;
        setDatos();
        try {
            if(lamina_actual.getRuta()!=null){
                guardar=BDLaminas.insertar(lamina_actual);
            }
        } catch (SQLException ex) {
            return false;
        }
         return guardar;
    }
    
    private boolean Actualizar(){
        setDatos();
        return true;
    } 
    
    private void setDatos(){
        lamina_actual.setPeso(Integer.valueOf(texto_peso.getText()));
        lamina_actual.setEntrevista(entrevista_actual);
    }
              
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        b_aceptar = new javax.swing.JButton();
        b_cancelar = new javax.swing.JButton();
        texto_peso = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        b_imagen = new javax.swing.JButton();
        lamina = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Peso");

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

        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setText("Lamina");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setText("Imagen");

        b_imagen.setText("Seleccionar Imagen...");
        b_imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_imagenActionPerformed(evt);
            }
        });

        lamina.setText("Lamina");

        jTable1.setModel(LOpciones);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lamina, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel2))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(texto_peso, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(b_imagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(b_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b_cancelar)))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(texto_peso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(b_imagen))
                        .addGap(18, 18, 18)
                        .addComponent(lamina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b_cancelar)
                            .addComponent(b_aceptar)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {b_aceptar, b_cancelar});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_aceptarActionPerformed
        boolean estado = false;
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
            JOptionPane.showMessageDialog(null,"No se guardaron datos"
                    + "\nVerifique que datos sean correcto");
        }
    }//GEN-LAST:event_b_aceptarActionPerformed

    private void b_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_b_cancelarActionPerformed

    private void b_imagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_imagenActionPerformed
        String ruta;
        ruta=Seleccion();
        System.out.println(ruta);
        if(ruta!=null){
            lamina_actual.setRuta(ruta);
        }

    }//GEN-LAST:event_b_imagenActionPerformed
    private String Seleccion(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter( new FileNameExtensionFilter("Archivo de Imagen","jpg") );
        fileChooser.setCurrentDirectory(new java.io.File("e:/imagenes_tmp/"));
        int result = fileChooser.showOpenDialog(null);
        if ( result == JFileChooser.APPROVE_OPTION ){
            //obtiene ruta y nombre del archivo
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            String name = fileChooser.getSelectedFile().getName();
            File file = new File(ruta);
             return ruta;
        }
        return null;
    }
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
            java.util.logging.Logger.getLogger(D_Lamina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(D_Lamina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(D_Lamina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(D_Lamina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Entrevista lamina = null;
                D_Lamina dialog = new D_Lamina(new javax.swing.JFrame(), true,lamina);
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
    private javax.swing.JButton b_imagen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lamina;
    private javax.swing.JTextField texto_peso;
    // End of variables declaration//GEN-END:variables
}
