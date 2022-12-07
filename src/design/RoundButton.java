package design;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class RoundButton extends JButton {
    int dim = 125;
    public RoundButton(String label) {
        super(label);
//        Dimension size = getPreferredSize();
        Dimension size = new Dimension(dim, dim);
        size.width = size.height = Math.max(size.width,size.height);
//        size.width = size.height = dim;
        setPreferredSize(size);

        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
//        g.fillOval(0, 0, getSize().width-1,getSize().height-1);
        g.fillOval(0, 0, dim-1,dim-1);

        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
//        g.drawOval(0, 0, getSize().width-1,     getSize().height-1);
        g.drawOval(0, 0, dim-1,dim-1);
    }

    Shape shape;
    public boolean contains(int x, int y) {
        if (shape == null ||
                !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
}