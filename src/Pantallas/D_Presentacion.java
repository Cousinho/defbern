package Pantallas;

import BaseDeDatos.BDEntrevistas;
import BaseDeDatos.BDLaminas;
import Entidades.Entrevista;
import Entidades.Lamina;
import Entidades.Perfil;
import Util.Directorios;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

public class D_Presentacion extends javax.swing.JDialog {
    private int listamuestras[]=new int[20];
    int angle=0;
    Image img;
    double alto;
    double ancho;
    int angulo;
    Entrevista entrevista;
    ArrayList<Lamina> Lista ;
    int TamañoLista=0;
    int IndiceLista=0;
    int Id_perfil=0;
    VideoCapture cap = new VideoCapture(0);
    Mat imagen=new Mat();
    int IndiceMuestra=0;
    int NumeroMuestras=15;
    boolean primero=true;
    public D_Presentacion(java.awt.Frame parent, boolean modal,Perfil perfil) throws SQLException {
        super(parent, modal);
        initComponents();
        Class1();
        Id_perfil=perfil.getCodigo();
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        entrevista=BDEntrevistas.buscarId(1);
        Lista= BDLaminas.Lista(entrevista.getCodigo());
        TamañoLista=Lista.size();
        CrearDirectorio();    
        angulo=90;
        Image imagen = null;
        if(TamañoLista!=0){
             imagen= Lista.get(IndiceLista).getImagen();
        }
        CargarImagen(imagen);
        if(primero){
            Grabar();
        }
        IndiceLista++;
    }
    
    public void Class1() {
        this.setSize(this.getToolkit().getScreenSize());    
    }

    private void CargarImagen(Image imagen){
        lamina.setFont(new Font("Monospaced", Font.PLAIN, 24));
        lamina.setBorder(BorderFactory.createEtchedBorder());
        img=imagen;
        ImageIcon fot = new ImageIcon(img);
        ancho =img.getWidth(null)  ;
        alto = img.getHeight(null)  ;
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lamina.getWidth(), lamina.getHeight(), Image.SCALE_DEFAULT));
        lamina.setIcon(icono);
        this.repaint();
    }
    
    private void Grabar(){
        
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                if(IndiceMuestra<NumeroMuestras){
                    if(cap.isOpened()){
                            try {
                                if(primero){
                                   Thread.sleep(2000);
                                   primero=false;
                                }
                                cap.read(imagen);
                                if(imagen.empty()){
                                    System.out.println("vacia");
                                }
                                Highgui.imwrite("imagen/Muestras/"+Id_perfil+"/"+IndiceLista+"/"+IndiceMuestra+".png", imagen);
                                System.out.println("imagen/Muestras/"+Id_perfil+"/"+IndiceLista+"/"+IndiceMuestra+".png");
                                
                                if(!imagen.empty()){
                                    IndiceMuestra++;
                                }
                                
                            } catch (Exception ex) {
                            }
                        
                    }
        
                }else{
                    this.cancel();
                  
                } 
                    
            }
        };
        t.schedule(tt,1,200);
     } 
     
    private void CambiarAngulo( int ang){
        BufferedImage image = null;
        image = (BufferedImage) img;
        double pcx=0;
        double pcy=0;
        int numColumnas=0;
        int numFilas=0;
        pcx =ancho/2;
        pcy = alto/2;   
        numColumnas = (int) ancho;
        numFilas =(int) alto;
        
        // centro de la imagen (punto caliente)

        int [] ex = new int[4];
        int [] ey = new int[4];
        int [] exp = new int[4];
        int [] eyp = new int[4];
        double angulo = Math.toRadians(ang);
        double ct = Math.cos(angulo);
        double st = Math.sin(angulo);
        double xp, yp;
        double xr, yr;
        
        //asignamos valores a las esquinas de la imagen
        ex[0] = 0;            ey[0] = 0;
        ex[1] = numColumnas;  ey[1] = 0;
        ex[2] = numColumnas;  ey[2] = numFilas;
        ex[3] = 0;            ey[3] = numFilas;
        
        for( int i = 0; i < 4 ; i++ ){
            // trasladando esquinas al punto caliente
            xp = ex[i] - pcx;
            yp = ey[i] - pcy;
            
            // rotando esquinas
            xr = (xp * ct) - (yp * st);
            yr = (yp * ct) + (xp * st);
            
            // trasladando esquinas rotadas
            exp[i] = (int)(xr + pcx);
            eyp[i] = (int)(yr + pcy);             
        }
        
        // Calculando tamaño de la imagen rotada
        int xmin = exp[0];
        int xmax = xmin;
        int ymin = eyp[0];
        int ymax = ymin;
        
        for( int i = 1; i < 4; i++ ){
            xmin = Math.min(xmin, exp[i]);
            xmax = Math.max(xmax, exp[i]);
            ymin = Math.min(ymin, eyp[i]);
            ymax = Math.max(ymax, eyp[i]);
        
        }
        
        int widthFinal = xmax - xmin;
        int heightFinal = ymax - ymin;
        BufferedImage bufferRotado = new BufferedImage(widthFinal, heightFinal, BufferedImage.TYPE_INT_ARGB);
        int x0 = (int)((widthFinal/2) - pcx);
        int y0 = (int)((heightFinal/2)-pcy);

        Graphics g = bufferRotado.createGraphics();
        g.drawImage(image, x0, y0, null);
        g.dispose();
        int xcentro = widthFinal/2;
        int ycentro = heightFinal/2;

        AffineTransform tx = new AffineTransform();
        tx.rotate(angulo, xcentro, ycentro); // ¡rotando!
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);
        BufferedImage aux = op.filter(bufferRotado, null);
        ImageIcon fot = new ImageIcon(aux);
               Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lamina.getWidth(), lamina.getHeight(), Image.SCALE_DEFAULT));
        lamina.setIcon(icono);
        this.repaint();
        Image salida = aux.getSubimage(0, 0, widthFinal, heightFinal);
        Icon icon = new ImageIcon( salida.getScaledInstance((int)lamina.getWidth(),(int)lamina.getHeight(), Image.SCALE_DEFAULT) );
        lamina.setIcon(icon);
    }
    
    private void CrearDirectorio(){
       Directorios carpeta=new Directorios();
       carpeta.Crear(TamañoLista,Id_perfil);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lamina = new javax.swing.JLabel();
        b_siguiente = new javax.swing.JButton();
        b_nada = new javax.swing.JButton();
        b_rotar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lamina.setText("                                                                       Lamina");
        lamina.setAlignmentY(0.0F);
        lamina.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        b_siguiente.setText("Siguiente");
        b_siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_siguienteActionPerformed(evt);
            }
        });

        b_nada.setText("Nada");

        b_rotar.setText("Rotar");
        b_rotar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_rotarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1270, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lamina, javax.swing.GroupLayout.PREFERRED_SIZE, 1270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(b_rotar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(b_nada, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(b_siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 692, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lamina, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(b_rotar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b_nada, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b_siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_siguienteActionPerformed
        IndiceMuestra=0;
        if(IndiceLista>=TamañoLista){
            b_siguiente.disable();
        }else{
            angulo=90;
            lamina.setFont(new Font("Monospaced", Font.PLAIN, 24));
            lamina.setBorder(BorderFactory.createEtchedBorder());
            Image img2 =Lista.get(IndiceLista).getImagen();
            IndiceLista++;
            img=img2;
            CargarImagen(img2);
            Grabar();
        }

    }//GEN-LAST:event_b_siguienteActionPerformed

    private void b_rotarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_rotarActionPerformed
        CambiarAngulo(angulo);
        switch (angulo) {
            case 0: angulo=90;
            break;
            case 90: angulo=angulo+90;;
            break;
            case 180: angulo=angulo+90;;
            break;
            case 270:  angulo=0;;
            break;
        }
    }//GEN-LAST:event_b_rotarActionPerformed

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
            java.util.logging.Logger.getLogger(D_Presentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(D_Presentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(D_Presentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(D_Presentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    System.loadLibrary("opencv_java249");
                    Perfil perfil = null;
                    D_Presentacion dialog = new D_Presentacion(new javax.swing.JFrame(), true,perfil);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(D_Presentacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_nada;
    private javax.swing.JButton b_rotar;
    private javax.swing.JButton b_siguiente;
    private javax.swing.JLabel lamina;
    // End of variables declaration//GEN-END:variables
}
