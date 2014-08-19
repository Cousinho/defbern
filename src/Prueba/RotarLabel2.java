package Prueba;

import BaseDeDatos.BDLaminas;
import Entidades.Lamina;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.*;
 
public class RotarLabel2 {
    int angle = 0;
 
    JPanel getContent() throws SQLException {
        label.setFont(new Font("Monospaced", Font.PLAIN, 24));
        label.setBorder(BorderFactory.createEtchedBorder());
        Lamina s=new Lamina();
        s=BDLaminas.buscarId(6);
        label.setIcon(new javax.swing.ImageIcon(s.getImagen()));
        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(label, new GridBagConstraints());
        return panel;
    }
 
    private JLabel label = new JLabel("") {
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
            AffineTransform aT = g2.getTransform();
            Shape oldshape = g2.getClip();
            double x = getWidth()/2.0;
            double y = getHeight()/2.0;
            aT.rotate(Math.toRadians(angle), x, y);
//            g2.transform(aT);
            g2.setTransform(aT);
            g2.setClip(oldshape);
            super.paintComponent(g);
        }
    };
    
 
    JSlider getSlider() {
        final JSlider slider = new JSlider(-180, 180, angle);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                angle = slider.getValue();
                label.getParent().repaint();
            }
        });
        return slider;
    }
 
    public static void main(String[] args) throws SQLException {
        RotarLabel2 rotarLabel = new RotarLabel2();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(rotarLabel.getContent());
        f.add(rotarLabel.getSlider(), "Last");
        f.setSize(400,400);
        f.setLocation(100,100);
        f.setVisible(true);
    }
}