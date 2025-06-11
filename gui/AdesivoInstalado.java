package gui;

import java.awt.*;
import javax.swing.*;

public class AdesivoInstalado extends JFrame {
    private BackgroundPanel panel;

    public AdesivoInstalado() {
        configurarJanela();
        initComponents();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Adesivo Instalado");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        try {
            // Carrega a imagem de fundo
            ImageIcon backgroundIcon = new ImageIcon("imagens/Adesivo Instalado.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            
            panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Botão de seta para a esquerda (volta para a tela inicial)
            JButton setaEsquerda = new JButton();
            ImageIcon setaIcon = new ImageIcon("imagens/seta esquerda.png");
            if (setaIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                setaEsquerda.setIcon(setaIcon);
                System.out.println("Imagem da seta carregada com sucesso");
            } else {
                System.out.println("Erro ao carregar imagem da seta");
                setaEsquerda.setText("←"); // Fallback para texto caso a imagem não carregue
            }
            
            setaEsquerda.setBounds(20, 334, 100, 100);
            setaEsquerda.setContentAreaFilled(false);
            setaEsquerda.setBorderPainted(false);
            setaEsquerda.setOpaque(false);
            setaEsquerda.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaEsquerda.setFocusPainted(false);
            
            // Adiciona um hover effect
            setaEsquerda.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    setaEsquerda.setContentAreaFilled(true);
                    setaEsquerda.setBackground(new Color(200, 200, 200, 100));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    setaEsquerda.setContentAreaFilled(false);
                }
            });

            setaEsquerda.addActionListener(e -> {
                dispose();
                new PainelInterativo().setVisible(true);
            });
            
            panel.add(setaEsquerda);

            setContentPane(panel);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Erro ao inicializar a tela: " + e.getMessage() + "\n" +
                "Por favor, verifique se todas as imagens necessárias estão presentes.",
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
} 