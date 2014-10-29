package Pantallas;

import Analisis.Rostro;
import BaseDeDatos.BDLaminas;
import BaseDeDatos.BDOpciones;
import Entidades.Entrevista;
import Entidades.Lamina;
import Entidades.Opciones;
import Util.Directorios;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

public class P_Presentacion extends javax.swing.JFrame {
    private int listamuestras[] = new int[20];
    boolean primero=true;
    int movimiento=0;
    int angle = 0;
    Image img;
    double alto;
    double ancho;
    int angulo;
    int tiempo_total;
    int tiempo_mitad;
    Entrevista entrevista;
    ArrayList<Lamina> Lista;
    int TamañoLista = 0;
    int IndiceLista = 0;
    int Id_registro = 0;
    VideoCapture cap= new VideoCapture(0);
    Mat imagen = new Mat();
    int IndiceMuestra = 0;
    int NumeroMuestras = 5;
    private String[] Respuestas;
    P_IniciarEntrevista pantalla_padre;
    DefaultTableModel ModeloTabla = new DefaultTableModel();
    private int tamaño_lista=0;
    private boolean Insertar=false;
    public P_Presentacion(Entrevista entrevista,int registro, P_IniciarEntrevista padre) throws SQLException {
        initComponents();
        //this.setAlwaysOnTop(true);
        setDefaultCloseOperation(0);
        pantalla_padre = padre;
        Visible(false);
        b_finalizar.setVisible(false);
        Id_registro = registro;
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        Lista = BDLaminas.ListaLaminas(entrevista.getCodigo());
        TamañoLista = Lista.size();
        CrearDirectorio();
        MuestraInicial();
        this.setExtendedState(MAXIMIZED_BOTH);
        t = new javax.swing.Timer(1000, acciones);
        t.start();
        angulo = 90;
        Image imagen = null;
        if (TamañoLista != 0) {
            imagen = Lista.get(IndiceLista).getImagen();
            Respuestas = new String[TamañoLista];
        }
        CargarImagen(imagen);
        if (primero) {
            Grabar();
            primero=false;
        }
        IndiceLista++;
        Respuestas[IndiceLista-1]="";
        Buscar();
        actualizartabla();
    }
    
    private void MuestraInicial(){
        D_MuestraNeutra muestra=new D_MuestraNeutra(this,true,Id_registro,cap);
        muestra.setLocationRelativeTo(null);
        muestra.setVisible(true);
    }

    //Metodo que actualiza la tabla
    public void actualizartabla() {
        Insertar=false;
        BuscarRespuesta();
        panel_opciones.removeAll();
        try {
            ArrayList<Opciones> ListaResultado = BDOpciones.ListaOpciones(texto_buscar.getText(),  Lista.get(IndiceLista-1).getCodigo());
            tamaño_lista=ListaResultado.size();
            int columnas= (int) Math.ceil((double)tamaño_lista/3);
            panel_opciones.setLayout( new GridLayout( columnas, 3 ) );
            for (Iterator<Opciones> it = ListaResultado.iterator(); it.hasNext();) {
                Opciones opcion = it.next();
                panel_opciones.add( new JCheckBox(opcion.getDescripcion()));
                
            }
            panel_opciones.updateUI();
             
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
    }

    //Metodo que limpia la tabla d
    public void limpiartabla() {
        int tamaño = ModeloTabla.getRowCount() - 1;
        for (int i = tamaño; i >= 0; i--) {
            ModeloTabla.removeRow(i);
        }
    }
    

    private void Buscar() {
        texto_buscar.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        actualizartabla();
                    }

                    public void insertUpdate(DocumentEvent e) {
                        actualizartabla();
                    }

                    public void removeUpdate(DocumentEvent e) {
                        actualizartabla();
                    }
                });
    }
    
    private void BuscarRespuesta(){
        if(Insertar==false){
            String Nomenclatura="";
            if(tamaño_lista!=0){
                    for(int x=0;x<tamaño_lista;x++){
                        JCheckBox componente = (JCheckBox) panel_opciones.getComponent(x);
                        if(componente.isSelected()==true){
                            try {
                                Nomenclatura=BDOpciones.buscarDesc(componente.getText(), Lista.get(IndiceLista-1).getCodigo());
                            } catch (SQLException ex) {
                                Logger.getLogger(P_Presentacion.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if(Respuestas[IndiceLista-1].equals("")){
                                Respuestas[IndiceLista-1]=Nomenclatura;
                                b_siguiente.setVisible(true);
                            }else{
                                Respuestas[IndiceLista-1]=Respuestas[IndiceLista-1]+" "+Nomenclatura;
                            }
                        }

                    }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lamina = new javax.swing.JLabel();
        panel_respuestas = new javax.swing.JPanel();
        ayuda1 = new javax.swing.JLabel();
        ayuda2 = new javax.swing.JLabel();
        panel_opciones = new javax.swing.JPanel();
        texto_buscar = new javax.swing.JTextField();
        b_nada = new javax.swing.JButton();
        b_otrarespuesta = new javax.swing.JButton();
        b_siguiente = new javax.swing.JButton();
        b_finalizar = new javax.swing.JButton();
        b_rotar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lamina.setAlignmentY(0.0F);
        lamina.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lamina.setMinimumSize(new java.awt.Dimension(1200, 500));
        getContentPane().add(lamina, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 31, 930, 490));

        panel_respuestas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ayuda1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ayuda1.setText("Escriba su respuesta aquí");
        panel_respuestas.add(ayuda1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 180, -1));

        ayuda2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ayuda2.setText("  Marque lo que ve en la lista ");
        panel_respuestas.add(ayuda2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 20));

        panel_opciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel_opciones.setMaximumSize(new java.awt.Dimension(300, 500));
        panel_opciones.setMinimumSize(new java.awt.Dimension(300, 500));
        panel_opciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_opcionesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel_opcionesLayout = new javax.swing.GroupLayout(panel_opciones);
        panel_opciones.setLayout(panel_opcionesLayout);
        panel_opcionesLayout.setHorizontalGroup(
            panel_opcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 836, Short.MAX_VALUE)
        );
        panel_opcionesLayout.setVerticalGroup(
            panel_opcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 496, Short.MAX_VALUE)
        );

        panel_respuestas.add(panel_opciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 840, 100));

        texto_buscar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        panel_respuestas.add(texto_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, 260, 51));

        b_nada.setText("No veo nada");
        b_nada.setMaximumSize(new java.awt.Dimension(100, 40));
        b_nada.setMinimumSize(new java.awt.Dimension(100, 40));
        b_nada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_nadaActionPerformed(evt);
            }
        });
        panel_respuestas.add(b_nada, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 80, 120, 40));

        b_otrarespuesta.setText("Otra respuesta");
        b_otrarespuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_otrarespuestaActionPerformed(evt);
            }
        });
        panel_respuestas.add(b_otrarespuesta, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 80, 140, 40));

        getContentPane().add(panel_respuestas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 544, 1130, 120));

        b_siguiente.setText("Siguiente");
        b_siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_siguienteActionPerformed(evt);
            }
        });
        getContentPane().add(b_siguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 550, 100, 40));

        b_finalizar.setText("Finalizar");
        b_finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_finalizarActionPerformed(evt);
            }
        });
        getContentPane().add(b_finalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 620, 100, 40));

        b_rotar.setText("Rotar");
        b_rotar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_rotarActionPerformed(evt);
            }
        });
        getContentPane().add(b_rotar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 470, 100, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Qué puede ser esto?");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void Visible(boolean estado) {
        panel_respuestas.setVisible(estado);
        b_nada.setVisible(estado);
        b_rotar.setVisible(estado);
        texto_buscar.setVisible(estado);
        ayuda1.setVisible(estado);
        ayuda2.setVisible(estado);
        b_siguiente.setVisible(estado);
    }

    private void CargarImagen(Image imagen) {
        if(IndiceLista==TamañoLista){
            b_siguiente.setVisible(false);
            b_siguiente.setEnabled(false);
            
        }
        lamina.setFont(new Font("Monospaced", Font.PLAIN, 24));
        lamina.setBorder(BorderFactory.createEtchedBorder());
        img = imagen;
        ImageIcon fot = new ImageIcon(img);
        ancho = img.getWidth(null);
        alto = img.getHeight(null);
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lamina.getWidth(), lamina.getHeight(), Image.SCALE_DEFAULT));
        lamina.setIcon(icono);
        this.repaint();
    }

    private void Grabar() {
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                String directorio="";
                String archivo="";
                if (IndiceMuestra < NumeroMuestras) {
                    if (cap.isOpened()) {
                        try {
                            cap.read(imagen);
                            Highgui.imwrite("imagen/Muestras/" + Id_registro + "/" + (IndiceLista) + "/" + IndiceMuestra + ".png", imagen);
                            archivo="imagen/Muestras/" + Id_registro + "/" + (IndiceLista) + "/" + IndiceMuestra + ".png";
                            directorio="imagen/Muestras/" + Id_registro + "/" + (IndiceLista) + "/";
                            if (!imagen.empty()) {
                                if(VerificarPosicion(archivo,directorio)){
                                    IndiceMuestra++;
                                }
                                
                            }

                        } catch (Exception ex) {
                        }

                    }

                } else {
            
                    this.cancel();
                    Visible(true);
                }

            }
        };
        t.schedule(tt, 1, 200);

    }
    
    private boolean VerificarPosicion(String archivo, String directorio){
        Rostro rostro = new Rostro();
        if(!rostro.Buscar(archivo, directorio,false)){
            JOptionPane.showMessageDialog(null, "Por favor mantenga su rostro en dirección a la cámara.");
            movimiento++;
            return false;
        }
        return true;
    }

    private void b_rotarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_rotarActionPerformed
        CambiarAngulo(angulo);
        switch (angulo) {
            case 0:
                angulo = 90;
                break;
            case 90:
                angulo = angulo + 90;
                ;
                break;
            case 180:
                angulo = angulo + 90;
                ;
                break;
            case 270:
                angulo = 0;
                ;
                break;
        }
    }//GEN-LAST:event_b_rotarActionPerformed

    private void b_siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_siguienteActionPerformed
       BuscarRespuesta();
       if(Respuestas[IndiceLista-1].equals("")){
            JOptionPane.showMessageDialog(null, "Por pavor elija alguna repuesta");
       }else{ 
            if(IndiceLista==5){
                tiempo_mitad=s;
            }
            IndiceMuestra = 0;
            texto_buscar.setText("");
            actualizartabla();
            Visible(false);
            angulo = 90;
            lamina.setFont(new Font("Monospaced", Font.PLAIN, 24));
            lamina.setBorder(BorderFactory.createEtchedBorder());
            Image img2 = Lista.get(IndiceLista).getImagen();
            IndiceLista++;
            if(IndiceLista==TamañoLista){
                b_finalizar.setVisible(true);
            }
            Respuestas[IndiceLista-1]="";
            img = img2;
            CargarImagen(img2);
            Grabar();
           }
    }//GEN-LAST:event_b_siguienteActionPerformed

    private void b_finalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_finalizarActionPerformed
        BuscarRespuesta();
        if(Respuestas[IndiceLista-1].equals("")){
            JOptionPane.showMessageDialog(null, "Por pavor elija alguna repuesta");
        }else{
            boolean movi=false;
            if(movimiento>5){
                movi=true;
            }
            pantalla_padre.setRespuestas(Respuestas,tiempo_mitad,s,movi);
            t.stop();
            cap.release();
            this.dispose();
        }    
    }//GEN-LAST:event_b_finalizarActionPerformed

    private void b_nadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_nadaActionPerformed
        Respuestas[IndiceLista-1] = "Nada";
        if(IndiceLista==TamañoLista){
            b_finalizarActionPerformed(evt);
        }else{
            b_siguienteActionPerformed(evt);
        }
        
    }//GEN-LAST:event_b_nadaActionPerformed

    private void panel_opcionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_opcionesMouseClicked

    }//GEN-LAST:event_panel_opcionesMouseClicked

    private void b_otrarespuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_otrarespuestaActionPerformed
        D_NuevaRespuesta insertar = null;
        try {
            insertar = new D_NuevaRespuesta(this,true,BDLaminas.buscarId(IndiceLista));
            insertar.setLocationRelativeTo(null);
            insertar.setResizable(false);
            insertar.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(P_Presentacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_b_otrarespuestaActionPerformed
    public void setRespuesta(Opciones opcion){
        System.out.println(opcion.getNomenclatura());
        Respuestas[IndiceLista-1]=opcion.getNomenclatura();
    }
    private void CambiarAngulo(int ang) {
        BufferedImage image = null;
        image = (BufferedImage) img;
        double pcx = 0;
        double pcy = 0;
        int numColumnas = 0;
        int numFilas = 0;
        pcx = ancho / 2;
        pcy = alto / 2;
        numColumnas = (int) ancho;
        numFilas = (int) alto;

        // centro de la imagen (punto caliente)
        int[] ex = new int[4];
        int[] ey = new int[4];
        int[] exp = new int[4];
        int[] eyp = new int[4];
        double angulo = Math.toRadians(ang);
        double ct = Math.cos(angulo);
        double st = Math.sin(angulo);
        double xp, yp;
        double xr, yr;

        //asignamos valores a las esquinas de la imagen
        ex[0] = 0;
        ey[0] = 0;
        ex[1] = numColumnas;
        ey[1] = 0;
        ex[2] = numColumnas;
        ey[2] = numFilas;
        ex[3] = 0;
        ey[3] = numFilas;

        for (int i = 0; i < 4; i++) {
            // trasladando esquinas al punto caliente
            xp = ex[i] - pcx;
            yp = ey[i] - pcy;

            // rotando esquinas
            xr = (xp * ct) - (yp * st);
            yr = (yp * ct) + (xp * st);

            // trasladando esquinas rotadas
            exp[i] = (int) (xr + pcx);
            eyp[i] = (int) (yr + pcy);
        }

        // Calculando tamaño de la imagen rotada
        int xmin = exp[0];
        int xmax = xmin;
        int ymin = eyp[0];
        int ymax = ymin;

        for (int i = 1; i < 4; i++) {
            xmin = Math.min(xmin, exp[i]);
            xmax = Math.max(xmax, exp[i]);
            ymin = Math.min(ymin, eyp[i]);
            ymax = Math.max(ymax, eyp[i]);

        }

        int widthFinal = xmax - xmin;
        int heightFinal = ymax - ymin;
        BufferedImage bufferRotado = new BufferedImage(widthFinal, heightFinal, BufferedImage.TYPE_INT_ARGB);
        int x0 = (int) ((widthFinal / 2) - pcx);
        int y0 = (int) ((heightFinal / 2) - pcy);

        Graphics g = bufferRotado.createGraphics();
        g.drawImage(image, x0, y0, null);
        g.dispose();
        int xcentro = widthFinal / 2;
        int ycentro = heightFinal / 2;

        AffineTransform tx = new AffineTransform();
        tx.rotate(angulo, xcentro, ycentro);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);
        BufferedImage aux = op.filter(bufferRotado, null);
        ImageIcon fot = new ImageIcon(aux);
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lamina.getWidth(), lamina.getHeight(), Image.SCALE_DEFAULT));
        lamina.setIcon(icono);
        this.repaint();
        Image salida = aux.getSubimage(0, 0, widthFinal, heightFinal);
        Icon icon = new ImageIcon(salida.getScaledInstance((int) lamina.getWidth(), (int) lamina.getHeight(), Image.SCALE_DEFAULT));
        lamina.setIcon(icon);
    }

    private void CrearDirectorio() {
        Directorios carpeta = new Directorios();
        carpeta.Crear(TamañoLista,Id_registro);
    }
    
    javax.swing.Timer t;
    int s = 0;
     ActionListener acciones = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent ae) {
            s++;
        }
        
    };

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
            java.util.logging.Logger.getLogger(P_Presentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(P_Presentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(P_Presentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(P_Presentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.loadLibrary("opencv_java249");
                try {
                    Entrevista entre = null;
                    P_IniciarEntrevista pantalla = null;
                    int id_perfil = 0;
                    new P_Presentacion(entre,id_perfil, pantalla).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(P_Presentacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ayuda1;
    private javax.swing.JLabel ayuda2;
    private javax.swing.JButton b_finalizar;
    private javax.swing.JButton b_nada;
    private javax.swing.JButton b_otrarespuesta;
    private javax.swing.JButton b_rotar;
    private javax.swing.JButton b_siguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lamina;
    private javax.swing.JPanel panel_opciones;
    private javax.swing.JPanel panel_respuestas;
    private javax.swing.JTextField texto_buscar;
    // End of variables declaration//GEN-END:variables
}
