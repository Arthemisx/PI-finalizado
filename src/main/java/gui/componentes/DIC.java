package gui.componentes;

import javax.swing.*;
import java.awt.*;
import util.UIUtils;

public class DIC extends JPanel {
    private Image backgroundImage;

    public DIC() {
        setLayout(null);
        Image backgroundImage = UIUtils.loadImage("imagens/10 - DIC sem chave20.jpg");
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