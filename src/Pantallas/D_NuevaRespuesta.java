package Pantallas;

import BaseDeDatos.BDOpciones;
import Entidades.Lamina;
import Entidades.Opciones;
import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class D_NuevaRespuesta extends javax.swing.JDialog {
    P_Presentacion pantalla_padre;
    Opciones opcion_actual=new Opciones() {};
    Lamina lamina_actual=new Lamina();
    public D_NuevaRespuesta(P_Presentacion parent, boolean modal, Lamina lamina) throws SQLException{
        //bloque la ventana padre de jdialog
        super(parent, modal);
        pantalla_padre=parent;
        lamina_actual=lamina;
        initComponents();
        //llama a metodo que formatea metodo de ingreso de campos
        //FormatoCampos();
        CargarBox();
    }

    private boolean Guardar() throws SQLException{
        setDatos();
        try {
            int valor= BDOpciones.insertar(opcion_actual);
            if(valor!=0){
                opcion_actual.setCodigo(valor);
                return true;
            }
        } catch (SQLException ex) {
            return false;
        }
        return false;
    }
    
    private void setDatos() throws SQLException{
        opcion_actual.setLamina(lamina_actual);
        String nomenclatura="";
        opcion_actual.setDescripcion(textoa_descripcion.getText());
        if(lista_primera.getSelectedItem()=="Toda la lamina"){
            nomenclatura=nomenclatura + "G,";
        }else{
            nomenclatura=nomenclatura + "D,";
        }
        if(lista_segunda.getSelectedItem()=="Movimiento"){
            nomenclatura=nomenclatura + "B,";
        }else if(lista_segunda.getSelectedItem()=="Forma"){
            nomenclatura=nomenclatura + "F,";
        }else{
            nomenclatura=nomenclatura + "C,";
        }
        if(lista_tercera.getSelectedItem()=="Abstracta"){
            nomenclatura=nomenclatura + "Abstr";
        }else if(lista_tercera.getSelectedItem()=="Abismo"){
            nomenclatura=nomenclatura + "Abismo";
        }else if(lista_tercera.getSelectedItem()=="Anatomia"){
            nomenclatura=nomenclatura + "Anat";
        }else if(lista_tercera.getSelectedItem()=="Animal"){
            nomenclatura=nomenclatura + "T";
        }else if(lista_tercera.getSelectedItem()=="Partes de animales"){
            nomenclatura=nomenclatura + "Td";    
        }else if(lista_tercera.getSelectedItem()=="Arquitectura"){
            nomenclatura=nomenclatura + "Arq";
        }else if(lista_tercera.getSelectedItem()=="Fuego"){
            nomenclatura=nomenclatura + "Fuego";
        }else if(lista_tercera.getSelectedItem()=="Figura Humana"){
            nomenclatura=nomenclatura + "Fuego";
        }else if(lista_tercera.getSelectedItem()=="Destruccion"){
            nomenclatura=nomenclatura + "Destr";
        }else if(lista_tercera.getSelectedItem()=="Geografica"){
            nomenclatura=nomenclatura + "Geo";
        }else if(lista_tercera.getSelectedItem()=="Mascara"){
            nomenclatura=nomenclatura + "Mascara";
        }else if(lista_tercera.getSelectedItem()=="Objetos"){
            nomenclatura=nomenclatura + "Obj";
        }else if(lista_tercera.getSelectedItem()=="Paisaje"){
            nomenclatura=nomenclatura + "N";
        }else if(lista_tercera.getSelectedItem()=="Partes Humanas"){
            nomenclatura=nomenclatura + "Md";
        }else if(lista_tercera.getSelectedItem()=="Pinza"){
            nomenclatura=nomenclatura + "Pinza";
        }else if(lista_tercera.getSelectedItem()=="Planta"){
            nomenclatura=nomenclatura + "Pl";
        }else if(lista_tercera.getSelectedItem()=="Reflejo"){
            nomenclatura=nomenclatura + "Refl";
        }else if(lista_tercera.getSelectedItem()=="Sangre"){
            nomenclatura=nomenclatura + "Sangre";
        }else if(lista_tercera.getSelectedItem()=="Sexual"){
            nomenclatura=nomenclatura + "Sex";
        }else if(lista_tercera.getSelectedItem()=="Simetria"){
            nomenclatura=nomenclatura + "Simetria";
        }
        opcion_actual.setNomenclatura(nomenclatura);
    }

    private void CargarBox() throws SQLException{
        lista_primera.removeAllItems();
        lista_primera.addItem("Toda la lamina");
        lista_primera.addItem("Una parte de la lamina");
        lista_segunda.removeAllItems();
        lista_segunda.addItem("Movimiento");
        lista_segunda.addItem("Forma");
        lista_segunda.addItem("Color");
        lista_tercera.removeAllItems();
        lista_tercera.addItem("Abstracta");
        lista_tercera.addItem("Abismo");
        lista_tercera.addItem("Anatomia");
        lista_tercera.addItem("Animal");
        lista_tercera.addItem("Partes de animales");
        lista_tercera.addItem("Arquitectura");
        lista_tercera.addItem("Destruccion");
        lista_tercera.addItem("Figura Humana");
        lista_tercera.addItem("Fuego");
        lista_tercera.addItem("Geografica");
        lista_tercera.addItem("Mascara");
        lista_tercera.addItem("Objetos");
    lista_tercera.addItem("Paisaje");
        lista_tercera.addItem("Partes Humanas");
        lista_tercera.addItem("Pinza");
        lista_tercera.addItem("Planta");
        lista_tercera.addItem("Reflejo");
        lista_tercera.addItem("Sangre");
        lista_tercera.addItem("Sexual");
        lista_tercera.addItem("Simetria");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        b_aceptar = new javax.swing.JButton();
        b_cancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lista_segunda = new javax.swing.JComboBox();
        lista_primera = new javax.swing.JComboBox();
        lista_tercera = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        textoa_descripcion = new javax.swing.JTextArea();
        b_ayuda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Escriba su respuesta:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("En qué parte de la lámina está viendo eso?");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Su respuesta contiene:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Seleccione la categoría de su respuesta:");

        b_aceptar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b_aceptar.setText("Aceptar");
        b_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_aceptarActionPerformed(evt);
            }
        });

        b_cancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b_cancelar.setText("Cancelar");
        b_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cancelarActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(81, 106, 138));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 41));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(254, 254, 254));
        jLabel5.setText("Nueva Respuesta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(252, 252, 252))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        lista_segunda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lista_segunda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lista_primera.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lista_primera.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lista_tercera.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lista_tercera.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        textoa_descripcion.setColumns(20);
        textoa_descripcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textoa_descripcion.setRows(5);
        jScrollPane1.setViewportView(textoa_descripcion);

        b_ayuda.setText("AYUDA");
        b_ayuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_ayudaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(b_aceptar)
                .addGap(60, 60, 60)
                .addComponent(b_cancelar)
                .addGap(71, 71, 71)
                .addComponent(b_ayuda, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lista_tercera, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lista_segunda, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lista_primera, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lista_primera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lista_segunda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lista_tercera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_cancelar)
                    .addComponent(b_aceptar)
                    .addComponent(b_ayuda))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_aceptarActionPerformed
        boolean estado = false;
        try {
            estado=Guardar();
        } catch (SQLException ex) {
            Logger.getLogger(D_NuevaRespuesta.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(estado){
            pantalla_padre.setRespuesta(opcion_actual);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "No se pudo guardar datos");
        }
    }//GEN-LAST:event_b_aceptarActionPerformed

    private void b_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_b_cancelarActionPerformed

    private void b_ayudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_ayudaActionPerformed
        File local= new File("");
        File principal=new File("file:///"+local.getAbsolutePath()+"/ayuda/Defbern.html");
        try{
           Desktop.getDesktop().browse(new URL(principal.getPath()).toURI());
       }catch(Exception e){
           e.printStackTrace();
       }
    }//GEN-LAST:event_b_ayudaActionPerformed
 
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
            java.util.logging.Logger.getLogger(D_NuevaRespuesta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(D_NuevaRespuesta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(D_NuevaRespuesta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(D_NuevaRespuesta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            private Lamina lamina;
            public void run() {
                    D_NuevaRespuesta dialog = null;
                try {
                    dialog = new D_NuevaRespuesta((P_Presentacion) new javax.swing.JFrame(), true, lamina);
                } catch (SQLException ex) {
                    Logger.getLogger(D_NuevaRespuesta.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JButton b_ayuda;
    private javax.swing.JButton b_cancelar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox lista_primera;
    private javax.swing.JComboBox lista_segunda;
    private javax.swing.JComboBox lista_tercera;
    private javax.swing.JTextArea textoa_descripcion;
    // End of variables declaration//GEN-END:variables
}
