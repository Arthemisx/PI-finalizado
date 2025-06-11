package com.metro;

import javax.swing.*;
import java.awt.*;
import gui.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new ProjetoIntegrado().setVisible(true);
        });
    }
} 