package gui;

import javax.swing.*;
import java.awt.*;
import util.UIUtils;

public class ColunaCinturaoEAdesivo extends JPanel {
    private Image backgroundImage;

    public ColunaCinturaoEAdesivo() {
        setLayout(null);
        Image backgroundImage = UIUtils.loadImage("imagens/cinturão e adesivo.jpg");
        if (backgroundImage != null) {
            this.backgroundImage = backgroundImage;
            setPreferredSize(new Dimension(backgroundImage.getWidth(null), backgroundImage.getHeight(null)));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }
} 