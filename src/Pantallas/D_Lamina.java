package Pantallas;

import BaseDeDatos.BDLaminas;
import BaseDeDatos.BDOpciones2;
import BaseDeDatos.BDUsuarios;
import Entidades.Entrevista;
import Entidades.Lamina;
import Entidades.Opciones2;
import Entidades.Usuario;
import Util.TablaModelo;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    java.awt.Frame pantalla_padre;
    
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
    
    public void actualizartabla(){
        limpiartabla();
        String titulos[] = {"Codigo","Descripción","Nomenclatura"};
        LOpciones.setColumnIdentifiers(titulos);
        try {
            for (Iterator<Opciones2> it = BDOpciones2.Lista() .iterator(); it.hasNext();) {
                Opciones2 opciones = it.next();
                String Datos[] = {String.valueOf(opciones.getCodigo()),
                                  opciones.getDescripcion(),opciones.getNomenclatura()
                                  };
                LOpciones.addRow(Datos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
    }
    
    public void limpiartabla(){
        int tamaño =LOpciones.getRowCount()-1;
        for(int i=tamaño; i>=0;i--){
              LOpciones.removeRow(i );
        }
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
        tabla_opciones = new javax.swing.JTable();
        b_agregar = new javax.swing.JButton();
        b_modificar = new javax.swing.JButton();
        b_borrar = new javax.swing.JButton();

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
                .addGap(436, 436, 436)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jLabel8.setText("Imagen");

        b_imagen.setText("Seleccionar Imagen...");
        b_imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_imagenActionPerformed(evt);
            }
        });

        lamina.setText("Lamina");

        tabla_opciones.setModel(LOpciones);
        jScrollPane1.setViewportView(tabla_opciones);

        b_agregar.setText("Agregar");
        b_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_agregarActionPerformed(evt);
            }
        });

        b_modificar.setText("Modificar");
        b_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_modificarActionPerformed(evt);
            }
        });

        b_borrar.setText("Borrar");
        b_borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_borrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel2))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(texto_peso, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                        .addComponent(b_imagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(b_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b_cancelar)))
                    .addComponent(lamina, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(b_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_borrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(b_agregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b_modificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b_borrar)))
                        .addContainerGap())))
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

    private void b_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_agregarActionPerformed
        D_Opciones2 insertar = null;
        insertar = new D_Opciones2(pantalla_padre,true);
        insertar.setLocationRelativeTo(null);
        insertar.setResizable(false);
        insertar.setVisible(true);
        //Actualiza tabla despues de cerrar ventana insertar
        insertar.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                actualizartabla();
            }
        });
    }//GEN-LAST:event_b_agregarActionPerformed

    private void b_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_modificarActionPerformed
        int columna=tabla_opciones.getSelectedRow();
        if (columna!=-1){
            try {
                Opciones2 p_envia=new Opciones2() {};
                Object valor = tabla_opciones.getValueAt(columna, 0);
                p_envia=BDOpciones2.buscarId(Integer.parseInt(valor.toString()));
                D_Opciones2 editar= new D_Opciones2(pantalla_padre,true,p_envia);
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

    private void b_borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_borrarActionPerformed
        //recibe numero de fila seleccionada
        int fila=tabla_opciones.getSelectedRow();
        
        //verifica que este seleccionada una fila
        if (fila!=-1){
            int seleccion = JOptionPane.showOptionDialog(
                this, // Componente padre
                "¿Desea eliminar esta opción?",
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
                    Object valor = tabla_opciones.getValueAt(fila, 0);
                    try {
                        //solicita eliminar fila selecionada
                        BDOpciones2.eliminar(Integer.parseInt(valor.toString()));
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "No se puede eliminar opción");

                    }

                    //actualiza tabla despues de eliminar fila
                    actualizartabla();
                }
              }
        }
        else{
            JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla");
        }
    }//GEN-LAST:event_b_borrarActionPerformed
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
    private javax.swing.JButton b_agregar;
    private javax.swing.JButton b_borrar;
    private javax.swing.JButton b_cancelar;
    private javax.swing.JButton b_imagen;
    private javax.swing.JButton b_modificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lamina;
    private javax.swing.JTable tabla_opciones;
    private javax.swing.JTextField texto_peso;
    // End of variables declaration//GEN-END:variables
}
