package Pantallas;

import Analisis.AnalisisRorschach;
import BaseDeDatos.BDEntrevistas;
import BaseDeDatos.BDGrupos;
import BaseDeDatos.BDRegistros;
import Entidades.Grupo;
import Entidades.Registro;
import Entidades.Usuario;
import Util.Bloqueo;
import Util.Caracteres;
import Util.Directorios;
import Util.Numerico;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
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
    private boolean movimiento=false;
    private boolean nuevasrespuestas=false;
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
    }
    
        
    private void Guardar(){
                registro_actual.setCi(Integer.valueOf(texto_ci.getText()));
                registro_actual.setNombre(texto_nombre.getText());
                registro_actual.setApellido(texto_apellido.getText());
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
    }
    
    private void Actualizar(){
        try {
            BDRegistros.actualizar(registro_actual);
        } catch (SQLException ex) {
            Logger.getLogger(P_IniciarEntrevista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setRespuestas(String[] respuestas,int tiempo_total,boolean movimiento,boolean nuevo){
        registro_actual.setRespuestas(respuestas);
        registro_actual.setTiempo_total(tiempo_total);
        nuevasrespuestas=nuevo;
        this.movimiento=movimiento;
        Actualizar();
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
        if(!nuevasrespuestas){
            AnalisisRorschach analisis=new AnalisisRorschach();
            try {
                perfil=analisis.AnalizarRegistro(registro_actual);
            } catch (SQLException ex) {
                Logger.getLogger(P_IniciarEntrevista.class.getName()).log(Level.SEVERE, null, ex);  
            }
            registro_actual.setAnalizado(true);
            registro_actual.setPerfil(perfil);
        }
        if(movimiento){
            registro_actual.setPerfil(perfil+"Observación: El entrevistado ha salido mucho del foco de la camara esto podria alterar la calidad del resultado de la entrevista");
        }
        Actualizar();
        setDefaultCloseOperation(1);
        label_analizando.setVisible(false);
        Directorios directorio=new Directorios();
        directorio.Borrar(registro_actual.getCodigo());
        this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        label_analizando = new javax.swing.JLabel();
        panel_principal = new javax.swing.JPanel();
        texto_ci = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        texto_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        texto_apellido = new javax.swing.JTextField();
        b_cancelar = new javax.swing.JButton();
        b_aceptar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Datos Personales Entrevista");

        jPanel1.setBackground(new java.awt.Color(81, 106, 138));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 41));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(254, 254, 254));
        jLabel8.setText("                         Datos Personales");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(482, 482, 482)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addGap(318, 318, 318))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        label_analizando.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        label_analizando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Util/gif-load.gif"))); // NOI18N
        label_analizando.setText("Analizando");

        texto_ci.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("CI");

        texto_nombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Nombre");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Apellido");

        texto_apellido.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        texto_apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texto_apellidoActionPerformed(evt);
            }
        });
        texto_apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                texto_apellidoKeyPressed(evt);
            }
        });

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
        b_aceptar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b_aceptarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panel_principalLayout = new javax.swing.GroupLayout(panel_principal);
        panel_principal.setLayout(panel_principalLayout);
        panel_principalLayout.setHorizontalGroup(
            panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_principalLayout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_principalLayout.createSequentialGroup()
                        .addComponent(b_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                        .addComponent(b_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(texto_ci)
                    .addComponent(texto_nombre)
                    .addComponent(texto_apellido))
                .addContainerGap())
        );
        panel_principalLayout.setVerticalGroup(
            panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_principalLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto_ci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(40, 40, 40)
                .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(40, 40, 40)
                .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(50, 50, 50)
                .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_aceptar)
                    .addComponent(b_cancelar))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        panel_principalLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {b_aceptar, b_cancelar});

        panel_principalLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {texto_apellido, texto_ci, texto_nombre});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1287, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(440, 440, 440)
                .addComponent(panel_principal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_analizando)
                .addGap(524, 524, 524))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(panel_principal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(label_analizando)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_aceptarActionPerformed
        if(texto_nombre.getText().equals("")||texto_apellido.getText().equals("")||
            texto_ci.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Complete todos los campos necesarios");
        }else{
            VideoCapture cap= new VideoCapture(0);
            if(cap.isOpened()){
                cap.release();
                Guardar();
                P_Presentacion entrevista = null;
                try {
                    entrevista = new P_Presentacion(BDEntrevistas.buscarId(1),registro_actual.getCodigo(),this);
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
                JOptionPane.showMessageDialog(null, "Camara web no disponible");

            }
        }

    }//GEN-LAST:event_b_aceptarActionPerformed

    private void texto_apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texto_apellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texto_apellidoActionPerformed
    
    
    private void b_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_b_cancelarActionPerformed

    private void texto_apellidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_texto_apellidoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
              b_aceptarActionPerformed(null);
        }
    }//GEN-LAST:event_texto_apellidoKeyPressed

    private void b_aceptarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_aceptarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
              b_aceptarActionPerformed(null);
        }
    }//GEN-LAST:event_b_aceptarKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_aceptar;
    private javax.swing.JButton b_cancelar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel label_analizando;
    private javax.swing.JPanel panel_principal;
    private javax.swing.JTextField texto_apellido;
    private javax.swing.JTextField texto_ci;
    private javax.swing.JTextField texto_nombre;
    // End of variables declaration//GEN-END:variables
}
