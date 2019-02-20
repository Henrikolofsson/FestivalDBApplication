package GUI;

import javax.swing.*;
import java.awt.*;

public class PanelBackground extends JComponent {
    private Image image;

    public PanelBackground(Image image){
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 800, 800, this);
    }
}
