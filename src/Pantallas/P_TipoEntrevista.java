package Pantallas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class P_TipoEntrevista extends javax.swing.JInternalFrame {

    java.awt.Frame Padre;
    
    P_TipoEntrevista(java.awt.Frame Pantalla_padre) {
        Padre=Pantalla_padre;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        b_individual = new javax.swing.JButton();
        b_grupal = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        b_individual.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b_individual.setText("Entrevista Individual");
        b_individual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_individualActionPerformed(evt);
            }
        });

        b_grupal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b_grupal.setText("Entrevista Grupal");
        b_grupal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_grupalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(b_individual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_grupal, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(b_individual)
                .addGap(57, 57, 57)
                .addComponent(b_grupal)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_individualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_individualActionPerformed
        D_IniciarEntrevista nuevo = null;
        
        nuevo = new D_IniciarEntrevista(Padre,true);
        nuevo.setLocationRelativeTo(null);
        nuevo.setResizable(false);
        nuevo.setVisible(true);

        //Actualiza tabla despues de cerrar ventana insertar
        nuevo.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                //actualizartabla();
            }
        });
        
    }//GEN-LAST:event_b_individualActionPerformed

    private void b_grupalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_grupalActionPerformed
        D_EntrevistaGrupal nuevo = null;
        
        nuevo = new D_EntrevistaGrupal(Padre);
        nuevo.setLocationRelativeTo(null);
        nuevo.setResizable(false);
        nuevo.setVisible(true);

        //Actualiza tabla despues de cerrar ventana insertar
        nuevo.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                //actualizartabla();
            }
        });
    }//GEN-LAST:event_b_grupalActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_grupal;
    private javax.swing.JButton b_individual;
    // End of variables declaration//GEN-END:variables
}
