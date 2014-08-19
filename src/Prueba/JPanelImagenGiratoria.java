package Prueba;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
 
public class JPanelImagenGiratoria extends javax.swing.JPanel {
 
    private int grados = 0;
 
    public int getGrados() {
        return grados;
    }
 
    public void setGrados(int grados) {
        this.grados = grados;
        repaint();
    }
 
    @Override
    public void paint(Graphics g) {
        super.paint(g); //se borra el contenido anterior
 
        double r = Math.toRadians(grados); //se convierte a radianes lo grados
 
        AffineTransform at = new AffineTransform();
        at.rotate(r, 100, 100); //se asigna el angulo y centro de rotacion
        ((Graphics2D) g).setTransform(at);
 
        //se dibuja
        g.setColor(Color.BLUE);
        g.drawRect(50, 50, 100, 100);
 
    }
}