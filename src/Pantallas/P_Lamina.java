package Pantallas;

import BaseDeDatos.BDLaminas;
import Entidades.Entrevista;
import Entidades.Lamina;
import Util.TablaModelo;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;

public class P_Lamina extends javax.swing.JInternalFrame {
    TablaModelo LTabla = new TablaModelo();
    TableRowSorter sorter = new TableRowSorter(LTabla);
    private Entrevista entrevista_actual=new Entrevista();
    boolean seleccion=false;
    java.awt.Frame pantalla_padre;
    public P_Lamina(java.awt.Frame padre) {
        pantalla_padre=padre;
        initComponents();
        Seleccionar();
        tabla.setRowSorter(sorter);
        
        
    }
    
    public void Seleccionar(){
        seleccion=false;
        D_SeleccionaEntrevista seleccionar=new D_SeleccionaEntrevista(pantalla_padre,this,true);
        seleccionar.setVisible(true);
        seleccionar.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                if(!seleccion){
                    dispose();
                }else{
                    actualizartabla();
                }
                
        } 
        });
        
    }
    
    public void setEntrevista(Entrevista entrevista){
        this.entrevista_actual=entrevista;
        seleccion=true;
    }
    public void actualizartabla(){
        limpiartabla();
        String titulos[] = {"Idenficador","Nombre","Descripción"};
        LTabla.setColumnIdentifiers(titulos);
        try {
            for (Iterator<Lamina> it = BDLaminas.Lista(entrevista_actual.getCodigo()).iterator(); it.hasNext();) {
                Lamina lamina = it.next();
                String Datos[] = {String.valueOf(lamina.getCodigo()),lamina.getEntrevista().getNombre(),
                        lamina.getEntrevista().getDescripcion()};
                LTabla.addRow(Datos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
        
    }
     
    public void limpiartabla(){
        int tamaño =LTabla.getRowCount()-1;
        for(int i=tamaño; i>=0;i--){
              LTabla.removeRow(i );
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        b_nuevo = new javax.swing.JButton();

        tabla.setModel(LTabla);
        jScrollPane1.setViewportView(tabla);

        tabla1.setModel(LTabla);
        jScrollPane2.setViewportView(tabla1);

        b_nuevo.setText("Nuevo");
        b_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_nuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(b_nuevo)
                .addContainerGap(424, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(95, 95, 95)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(b_nuevo)
                .addContainerGap(428, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_nuevoActionPerformed
        D_Lamina insertar = null;
        insertar = new D_Lamina(pantalla_padre,true,entrevista_actual);
        insertar.setLocationRelativeTo(null);
        insertar.setResizable(false);
        insertar.setVisible(true);
        //Actualiza tabla despues de cerrar ventana insertar
        insertar.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                actualizartabla();
            }
        });

    }//GEN-LAST:event_b_nuevoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_nuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla1;
    // End of variables declaration//GEN-END:variables
}
