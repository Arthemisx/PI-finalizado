package main;

import gui.PainelInterativo;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PainelInterativo().setVisible(true);
        });
    }
}