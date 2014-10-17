package Pantallas;

import BaseDeDatos.BDLaminas;
import BaseDeDatos.BDOpciones;
import Entidades.Entrevista;
import Entidades.Lamina;
import Entidades.Opciones;
import Pantallas.D_MuestraNeutra;
import Pantallas.P_IniciarEntrevista;
import Util.Directorios;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
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
    int angle = 0;
    Image img;
    double alto;
    double ancho;
    int angulo;
    Entrevista entrevista;
    ArrayList<Lamina> Lista;
    int TamañoLista = 0;
    int IndiceLista = 0;
    int Id_perfil = 0;
    VideoCapture cap= new VideoCapture(0);
    Mat imagen = new Mat();
    int IndiceMuestra = 0;
    int NumeroMuestras = 2;
    boolean primero = true;
    private String[] Respuestas;
    P_IniciarEntrevista pantalla_padre;
    DefaultTableModel ModeloTabla = new DefaultTableModel();
    private int tamaño_lista=0;
    private boolean Insertar=false;
    public P_Presentacion(Entrevista entrevista,int perfil, P_IniciarEntrevista padre) throws SQLException {
        initComponents();
//        this.setAlwaysOnTop(true);
        setDefaultCloseOperation(0);
        pantalla_padre = padre;
        Visible(false);
        b_finalizar.setVisible(false);
        Id_perfil = perfil;
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        Lista = BDLaminas.Lista(entrevista.getCodigo());
        TamañoLista = Lista.size();
        CrearDirectorio();
        MuestraInicial();
        this.setExtendedState(MAXIMIZED_BOTH);
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
        D_MuestraNeutra muestra=new D_MuestraNeutra(this,true,Id_perfil,cap);
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
            int columnas= (int) Math.ceil((double)tamaño_lista/5);
            panel_opciones.setLayout( new GridLayout( columnas, 5 ) );
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
        b_rotar = new javax.swing.JButton();
        b_siguiente = new javax.swing.JButton();
        b_finalizar = new javax.swing.JButton();
        panel_respuestas = new javax.swing.JPanel();
        b_nada = new javax.swing.JButton();
        texto_buscar = new javax.swing.JTextField();
        ayuda1 = new javax.swing.JLabel();
        ayuda2 = new javax.swing.JLabel();
        panel_opciones = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lamina.setAlignmentY(0.0F);
        lamina.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lamina.setMinimumSize(new java.awt.Dimension(1200, 500));

        b_rotar.setText("Rotar");
        b_rotar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_rotarActionPerformed(evt);
            }
        });

        b_siguiente.setText("Siguiente");
        b_siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_siguienteActionPerformed(evt);
            }
        });

        b_finalizar.setText("Finalizar");
        b_finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_finalizarActionPerformed(evt);
            }
        });

        b_nada.setText("No veo nada");
        b_nada.setMaximumSize(new java.awt.Dimension(100, 40));
        b_nada.setMinimumSize(new java.awt.Dimension(100, 40));
        b_nada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_nadaActionPerformed(evt);
            }
        });

        texto_buscar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        ayuda1.setText("Escriba su respuesta aquí");

        ayuda2.setText("Marque lo que ve en la lista");

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
            .addGap(0, 522, Short.MAX_VALUE)
        );
        panel_opcionesLayout.setVerticalGroup(
            panel_opcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel_respuestasLayout = new javax.swing.GroupLayout(panel_respuestas);
        panel_respuestas.setLayout(panel_respuestasLayout);
        panel_respuestasLayout.setHorizontalGroup(
            panel_respuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_respuestasLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panel_respuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ayuda1)
                    .addComponent(texto_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_nada, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panel_respuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_respuestasLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(ayuda2))
                    .addGroup(panel_respuestasLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(panel_opciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_respuestasLayout.setVerticalGroup(
            panel_respuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_respuestasLayout.createSequentialGroup()
                .addGroup(panel_respuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ayuda2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ayuda1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_respuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_respuestasLayout.createSequentialGroup()
                        .addComponent(panel_opciones, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(panel_respuestasLayout.createSequentialGroup()
                        .addComponent(texto_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(b_nada, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lamina, javax.swing.GroupLayout.PREFERRED_SIZE, 1015, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(b_rotar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b_siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(b_finalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addComponent(panel_respuestas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(209, 209, 209))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {b_finalizar, b_rotar, b_siguiente});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lamina, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_respuestas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b_siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_rotar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(b_finalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {b_rotar, b_siguiente});

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void Visible(boolean estado) {
        panel_respuestas.setVisible(estado);
        b_nada.setVisible(estado);
        b_rotar.setVisible(estado);
        texto_buscar.setVisible(estado);
        ayuda1.setVisible(estado);
        ayuda2.setVisible(estado);
    }

    private void CargarImagen(Image imagen) {
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
                if (IndiceMuestra < NumeroMuestras) {
                    if (cap.isOpened()) {
                        try {
                            if (primero) {
                                Thread.sleep(2000);
                                primero = false;
                            }
                            cap.read(imagen);
                            Highgui.imwrite("imagen/Muestras/" + Id_perfil + "/" + (IndiceLista) + "/" + IndiceMuestra + ".png", imagen);
                            if (!imagen.empty()) {
                                IndiceMuestra++;
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
                b_siguiente.setVisible(false);
                b_finalizar.setVisible(true);
            }
            Respuestas[IndiceLista-1]="";
            img = img2;
            CargarImagen(img2);
            Grabar();
           }
    }//GEN-LAST:event_b_siguienteActionPerformed

    private void b_finalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_finalizarActionPerformed
        pantalla_padre.setRespuestas(Respuestas);
        cap.release();
        this.dispose();
    }//GEN-LAST:event_b_finalizarActionPerformed

    private void b_nadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_nadaActionPerformed
        Respuestas[IndiceLista-1] = "nada";
        if(IndiceLista==TamañoLista){
            b_finalizarActionPerformed(evt);
        }else{
            b_siguienteActionPerformed(evt);
        }
        
    }//GEN-LAST:event_b_nadaActionPerformed

    private void panel_opcionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_opcionesMouseClicked

    }//GEN-LAST:event_panel_opcionesMouseClicked

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
        carpeta.Crear(TamañoLista, Id_perfil);
    }

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
    private javax.swing.JButton b_rotar;
    private javax.swing.JButton b_siguiente;
    private javax.swing.JLabel lamina;
    private javax.swing.JPanel panel_opciones;
    private javax.swing.JPanel panel_respuestas;
    private javax.swing.JTextField texto_buscar;
    // End of variables declaration//GEN-END:variables
}
