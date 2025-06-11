package util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UIUtils {
    public static Image loadImage(String path) {
        try {
            return new ImageIcon(path).getImage();
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem: " + path);
            e.printStackTrace();
            return null;
        }
    }

    public static JButton createInvisibleButton(Rectangle bounds, ActionListener action) {
        JButton button = new JButton();
        button.setBounds(bounds);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(action);
        return button;
    }
} 