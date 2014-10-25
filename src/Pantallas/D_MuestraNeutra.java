package Pantallas;

import Prueba.Rostro;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

public class D_MuestraNeutra extends javax.swing.JDialog {
    Mat imagen = new Mat();
    private boolean primero=true;
    private int IndiceMuestra=0;
    private int NumeroMuestras=5;
    public D_MuestraNeutra(java.awt.Frame parent, boolean modal,int Id_perfil, VideoCapture cap) {
        super(parent, modal);
        initComponents();
        b_iniciar.setVisible(false);
        Grabar(Id_perfil,cap);
        this.dispose();
    }
    
    private void Grabar(final int Id_perfil, final VideoCapture cap) {
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                String archivo;
                String directorio;
                if (IndiceMuestra < NumeroMuestras) {
                    if (cap.isOpened()) {
                        try {
                            if (primero) {
                                Thread.sleep(2000);
                                primero = false;
                            }
                            cap.read(imagen);
                            Highgui.imwrite("imagen/Muestras/" + Id_perfil + "/" + (0) + "/" + IndiceMuestra + ".png", imagen);
                            archivo = IndiceMuestra+".png";
                            directorio = "imagen/Muestras/" + Id_perfil + "/" + (0) + "/";
                            if (!imagen.empty()) {
                                IndiceMuestra++;
                                VerificarPosicion(archivo, directorio);
                            }
                        } catch (Exception ex) {
                        }

                    }

                } else {
                    this.cancel();
                    b_iniciar.setVisible(true);
                }

            }
        };
        t.schedule(tt, 1, 200);

    }
    
    private void VerificarPosicion(String archivo, String directorio){
        Rostro rostro = new Rostro();
        if(!rostro.Buscar(archivo, directorio)){
            JOptionPane.showMessageDialog(null, "Por favor mantenga su rostro en dirección a la cámara.");
            IndiceMuestra = IndiceMuestra - 1;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        b_iniciar = new javax.swing.JButton();
        ayuda_1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        b_iniciar.setText("Iniciar Entrevista");
        b_iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_iniciarActionPerformed(evt);
            }
        });

        ayuda_1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        ayuda_1.setText("Por favor mire fijamente a la pantalla  permanezca serio");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(b_iniciar)
                .addGap(457, 457, 457))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(213, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ayuda_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(213, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ayuda_1)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(b_iniciar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_iniciarActionPerformed
        dispose();
    }//GEN-LAST:event_b_iniciarActionPerformed

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
            java.util.logging.Logger.getLogger(D_MuestraNeutra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(D_MuestraNeutra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(D_MuestraNeutra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(D_MuestraNeutra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                int id_perfil=0;
                VideoCapture video = null;
                D_MuestraNeutra dialog = new D_MuestraNeutra(new javax.swing.JFrame(), true, id_perfil,video);
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
    private javax.swing.JLabel ayuda_1;
    private javax.swing.JButton b_iniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
