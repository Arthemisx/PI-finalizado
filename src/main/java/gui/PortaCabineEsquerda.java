package gui;

import java.awt.*;
import javax.swing.*;

public class PortaCabineEsquerda extends JFrame {

    public PortaCabineEsquerda() {
        configurarJanela();
        initComponents();
    }

    private void configurarJanela() {
        setTitle("Porta de Cabine Lateral Esquerda");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        try {
            ImageIcon backgroundIcon = new ImageIcon("imagens/12 - Porta de cabine lateral esquerda.jpg");
            if (backgroundIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                throw new Exception("Erro ao carregar a imagem de fundo");
            }
            
            Image backgroundImage = backgroundIcon.getImage();
            BackgroundPanel panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Área clicável que cobre toda a porta
            JButton portaBtn = new JButton();
            portaBtn.setBounds(0, 0, 1024, 768);
            portaBtn.setContentAreaFilled(false);
            portaBtn.setBorderPainted(false);
            portaBtn.setOpaque(false);
            portaBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            portaBtn.addActionListener(e -> {
                dispose();
                new VisaoGeral().setVisible(true);
            });
            panel.add(portaBtn);

            // Botão de seta para a direita para voltar ao Painel Interativo
            JButton setaDireita = new JButton(new ImageIcon("imagens/seta direita.png"));
            setaDireita.setBounds(900, 384, 100, 100);
            setaDireita.setContentAreaFilled(false);
            setaDireita.setBorderPainted(false);
            setaDireita.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaDireita.addActionListener(e -> {
                dispose();
                new PainelInterativo().setVisible(true);
            });
            panel.add(setaDireita);

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
            new PortaCabineEsquerda().setVisible(true);
        });
    }
} 