package gui;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class UIUtils {
    
    public static Image loadImage(String path) {
        try {
            ImageIcon icon = new ImageIcon(path);
            if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                throw new RuntimeException("Falha ao carregar imagem: " + path);
            }
            return icon.getImage();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JButton createInvisibleButton(Rectangle bounds, ActionListener action) {
        JButton button = new JButton();
        button.setBounds(bounds);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(action);
        return button;
    }

    public static JButton BotaoDeSeta(String imagePath, Rectangle bounds, ActionListener action) {
        JButton button = new JButton(new ImageIcon(imagePath));
        button.setBounds(bounds);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(action);
        return button;
    }

    public static Image rotateImage(Image image, double degrees) {
        if (image == null) return null;
        
        // Converter para BufferedImage
        BufferedImage buffered = new BufferedImage(
            image.getWidth(null), 
            image.getHeight(null), 
            BufferedImage.TYPE_INT_ARGB
        );
        
        Graphics2D g2d = buffered.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        
        // Criar transformação
        double rads = Math.toRadians(degrees);
        double sin = Math.abs(Math.sin(rads));
        double cos = Math.abs(Math.cos(rads));
        int w = buffered.getWidth();
        int h = buffered.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);
        
        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        g2d = rotated.createGraphics();
        
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);
        at.rotate(rads, w/2, h/2);
        g2d.setTransform(at);
        g2d.drawImage(buffered, 0, 0, null);
        g2d.dispose();
        
        return rotated;
    }
}

