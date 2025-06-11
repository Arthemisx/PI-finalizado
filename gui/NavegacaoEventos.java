package gui;

import java.awt.Window;
import javax.swing.SwingUtilities;

public class NavegacaoEventos {
    public static void navegarParaAdesivoPortaIsolada() {
        SwingUtilities.invokeLater(() -> {
            // Esconde as imagens flutuantes
            ImagensFlutuantes.esconderImagens();
            
            // Fecha todas as janelas existentes
            for (Window window : Window.getWindows()) {
                if (window.isVisible()) {
                    window.dispose();
                }
            }
            
            // Cria e mostra a nova janela
            AdesivoPortaIsolada novaJanela = new AdesivoPortaIsolada();
            novaJanela.setVisible(true);
        });
    }
} 