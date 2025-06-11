package util;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ImagemUtils {
    private static final int LARGURA_PADRAO = 1024;
    private static final int ALTURA_PADRAO = 768;

    public static Image carregarImagem(String caminho, Component parent) {
        try {
            File arquivo = new File(caminho);
            if (!arquivo.exists()) {
                throw new Exception("Arquivo não encontrado: " + caminho);
            }

            ImageIcon icon = new ImageIcon(caminho);
            if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                throw new Exception("Falha ao carregar imagem: " + caminho);
            }

            return icon.getImage();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, 
                "Erro ao carregar imagem: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static void atualizarImagem(JLabel label, String caminho, Component parent) {
        try {
            Image originalImage = carregarImagem(caminho, parent);
            if (originalImage == null) return;

            double originalRatio = (double) originalImage.getWidth(null) / originalImage.getHeight(null);
            double windowRatio = (double) LARGURA_PADRAO / ALTURA_PADRAO;
            
            int scaledWidth, scaledHeight;
            if (originalRatio > windowRatio) {
                scaledWidth = LARGURA_PADRAO;
                scaledHeight = (int) (LARGURA_PADRAO / originalRatio);
            } else {
                scaledHeight = ALTURA_PADRAO;
                scaledWidth = (int) (ALTURA_PADRAO * originalRatio);
            }

            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(scaledImage));
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, 
                "Erro ao atualizar imagem: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Image redimensionarImagem(Image imagem, int largura, int altura) {
        if (imagem == null) return null;
        return imagem.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
    }

    public static boolean verificarImagem(Image imagem) {
        return imagem != null && imagem.getWidth(null) > 0 && imagem.getHeight(null) > 0;
    }
}