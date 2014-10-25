package Pantallas;

import Analisis.AnalisisRorschach;
import BaseDeDatos.BDEntrevistas;
import BaseDeDatos.BDGrupos;
import BaseDeDatos.BDRegistros;
import Entidades.Entrevista;
import Entidades.Grupo;
import Entidades.Registro;
import Entidades.Usuario;
import Util.AlfanumericoEspacio;
import Util.Bloqueo;
import Util.Caracteres;
import Util.Numerico;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.opencv.highgui.VideoCapture;

public class P_IniciarEntrevista extends javax.swing.JInternalFrame {
    private Usuario usuario_actual=new Usuario();
    private java.awt.Frame pantalla_padre;
    private Registro registro_actual=new Registro();
    private Grupo grupo_actual=BDGrupos.buscarId(0);
    private java.util.Date fecha_actual = new java.util.Date();
    private String[] Respuestas;
    public P_IniciarEntrevista(java.awt.Frame padre,boolean grupal,Usuario usuario) throws SQLException {
        System.loadLibrary("opencv_java249");
        initComponents();
        label_analizando.setVisible(false);
        usuario_actual=usuario;
        pantalla_padre = padre;
        if(grupal){
            Seleccionar();
        }
        FormatoCampos();
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
        texto_nombre.setDocument(new Caracteres(texto_nombre, 50));
        texto_apellido.setDocument(new Caracteres(texto_apellido,50));
        texto_descripcion.setDocument(new AlfanumericoEspacio(texto_descripcion,100));
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
    
    private void Actualizar(){
        try {
            BDRegistros.actualizar(registro_actual);
        } catch (SQLException ex) {
            Logger.getLogger(P_IniciarEntrevista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setRespuestas(String[] respuestas,int tiempo_mitad,int tiempo_total){
        registro_actual.setRespuestas(respuestas);
        registro_actual.setTiempo_total(tiempo_total);
        Actualizar();
        texto_codigo.setText(String.valueOf(registro_actual.getCodigo()));
    }
    
    
    public void ProcesosAnalizar()
    {
    Runnable miRunnable = new Runnable()
    {
    public void run()
    {
    try
    {
        Analizar();
    }
    catch (Exception e)
    {
    e.printStackTrace();
    }
    }
    };
    Thread hilo = new Thread (miRunnable);
    hilo.start();
    }
    

   
    private void Analizar(){
        label_analizando.setVisible(true);
        Bloqueo.setEnableContainer(panel_principal, false);
        setDefaultCloseOperation(0);
        String perfil = "";
        AnalisisRorschach analisis=new AnalisisRorschach();
        try {
            perfil=analisis.AnalizarRegistro(registro_actual);
        } catch (SQLException ex) {
            Logger.getLogger(P_IniciarEntrevista.class.getName()).log(Level.SEVERE, null, ex);  
        }
        
        registro_actual.setPerfil(perfil);
        Actualizar();
        setDefaultCloseOperation(1);
        label_analizando.setVisible(false);
        this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        label_analizando = new javax.swing.JLabel();
        panel_principal = new javax.swing.JPanel();
        texto_codigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        texto_ci = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        texto_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        texto_apellido = new javax.swing.JTextField();
        texto_descripcion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        b_cancelar = new javax.swing.JButton();
        b_aceptar = new javax.swing.JButton();
        lista_entrevistas = new javax.swing.JComboBox();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(81, 106, 138));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 41));

        jLabel8.setForeground(new java.awt.Color(254, 254, 254));
        jLabel8.setText("                                         Entrevistas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addGap(400, 400, 400))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        label_analizando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Util/gif-load.gif"))); // NOI18N
        label_analizando.setText("Analizando");

        texto_codigo.setEditable(false);
        texto_codigo.setEnabled(false);

        jLabel1.setText("Código");

        jLabel2.setText("CI");

        jLabel3.setText("Nombre");

        jLabel4.setText("Apellido");

        texto_apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texto_apellidoActionPerformed(evt);
            }
        });

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

        lista_entrevistas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout panel_principalLayout = new javax.swing.GroupLayout(panel_principal);
        panel_principal.setLayout(panel_principalLayout);
        panel_principalLayout.setHorizontalGroup(
            panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_principalLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lista_entrevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_principalLayout.createSequentialGroup()
                        .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_principalLayout.createSequentialGroup()
                                .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_principalLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)))
                        .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panel_principalLayout.createSequentialGroup()
                                .addComponent(b_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(texto_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(texto_codigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(texto_ci, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(texto_nombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(texto_apellido)))))
                .addGap(20, 20, 20))
        );

        panel_principalLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {texto_apellido, texto_ci, texto_codigo, texto_descripcion, texto_nombre});

        panel_principalLayout.setVerticalGroup(
            panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_principalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(27, 27, 27)
                .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto_ci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(32, 32, 32)
                .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(texto_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_cancelar)
                    .addComponent(b_aceptar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lista_entrevistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_principalLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {b_aceptar, b_cancelar, lista_entrevistas});

        panel_principalLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {texto_apellido, texto_ci, texto_codigo, texto_nombre});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1287, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(537, 537, 537)
                        .addComponent(label_analizando))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(397, 397, 397)
                        .addComponent(panel_principal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(panel_principal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(label_analizando)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_aceptarActionPerformed
        VideoCapture cap= new VideoCapture(0);
        if(cap!=null){
            Guardar();
            P_Presentacion entrevista = null;
            try {
                entrevista = new P_Presentacion(BDEntrevistas.buscarNombre((String) lista_entrevistas.getSelectedItem()),registro_actual.getCodigo(),this);
            } catch (SQLException ex) {
                Logger.getLogger(P_IniciarEntrevista.class.getName()).log(Level.SEVERE, null, ex);
            }
            entrevista.setLocationRelativeTo(null);
            entrevista.setVisible(true);
            entrevista.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
               ProcesosAnalizar();
            }
        });

        }else{
            
        }
        

    }//GEN-LAST:event_b_aceptarActionPerformed

    private void texto_apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texto_apellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texto_apellidoActionPerformed
    
    
    private void b_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_b_cancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_aceptar;
    private javax.swing.JButton b_cancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel label_analizando;
    private javax.swing.JComboBox lista_entrevistas;
    private javax.swing.JPanel panel_principal;
    private javax.swing.JTextField texto_apellido;
    private javax.swing.JTextField texto_ci;
    private javax.swing.JTextField texto_codigo;
    private javax.swing.JTextField texto_descripcion;
    private javax.swing.JTextField texto_nombre;
    // End of variables declaration//GEN-END:variables
}
