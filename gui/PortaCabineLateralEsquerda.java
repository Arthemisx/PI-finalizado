package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PortaCabineLateralEsquerda extends JFrame {
    private static final int LARGURA_JANELA = 1024;
    private static final int ALTURA_JANELA = 768;
    private BackgroundPanel panel;

    public PortaCabineLateralEsquerda() {
        setTitle("Porta de Cabine Lateral Esquerda");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(LARGURA_JANELA, ALTURA_JANELA);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        try {
            // Carregar imagem de fundo
            ImageIcon backgroundIcon = new ImageIcon("imagens/porta de cabine lateral esquerda.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Área clicável que cobre toda a porta
            JButton portaBtn = new JButton();
            portaBtn.setBounds(0, 0, LARGURA_JANELA, ALTURA_JANELA);
            portaBtn.setContentAreaFilled(false);
            portaBtn.setBorderPainted(false);
            portaBtn.setOpaque(false);
            portaBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            portaBtn.addActionListener(e -> {
                dispose();
                new VisaoGeral().setVisible(true);
            });
            panel.add(portaBtn);

            setContentPane(panel);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Erro ao carregar imagem: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PortaCabineLateralEsquerda().setVisible(true);
        });
    }
} 