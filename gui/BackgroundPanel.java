package gui;

import java.awt.*;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        setOpaque(true);
        setBackground(Color.WHITE);
    }

    public void setImagem(Image novaImagem) {
        this.backgroundImage = novaImagem;
        repaint(); // Força o repaint do painel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
} 