package gui;

import java.awt.*;
import javax.swing.*;
import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;

public class PortaFechada extends JFrame {
    private JPanel panel;

    public PortaFechada() {
        configurarJanela();
        initComponents();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Porta Fechada");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        try {
            // Carregar imagem de fundo
            ImageIcon backgroundIcon = new ImageIcon("imagens/20 - Porta fechada.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            
            panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Botão invisível centralizado para abrir a porta adesivada
            JButton portaAdesivadaBtn = new JButton();
            portaAdesivadaBtn.setBounds(312, 84, 400, 600); // Centralizado na tela
            portaAdesivadaBtn.setContentAreaFilled(false);
            portaAdesivadaBtn.setBorderPainted(false);
            portaAdesivadaBtn.setOpaque(false);
            portaAdesivadaBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            portaAdesivadaBtn.addActionListener(e -> {
                tocarSom("sons/gongo.wav");
                dispose();
                abrirPortaAdesivada();
            });
            panel.add(portaAdesivadaBtn);

            // Botão de seta para a esquerda para voltar à Visão Geral
            JButton setaEsquerda = new JButton(new ImageIcon("imagens/seta esquerda.png"));
            setaEsquerda.setBounds(50, 384, 100, 100);
            setaEsquerda.setContentAreaFilled(false);
            setaEsquerda.setBorderPainted(false);
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
                new VisaoGeral().setVisible(true);
            });
            panel.add(setaEsquerda);

            setContentPane(panel);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Erro ao carregar imagem: " + e.getMessage() + "\n" +
                "Caminho atual: " + new File(".").getAbsolutePath(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tocarSom(String caminho) {
        try {
            File arquivoSom = new File(caminho);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(arquivoSom);
            try (Clip clip = AudioSystem.getClip()) {
                clip.open(audioIn);
                clip.start();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void abrirPortaAdesivada() {
        JFrame framePortaAdesivada = new JFrame("Porta Adesivada");
        framePortaAdesivada.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePortaAdesivada.setSize(1024, 768);
        framePortaAdesivada.setLocationRelativeTo(null);
        framePortaAdesivada.setResizable(false);

        try {
            // Carregar imagem de fundo
            ImageIcon backgroundIcon = new ImageIcon("imagens/Porta adesivada.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            
            // Criar painel com a imagem de fundo
            BackgroundPanel panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Botão de seta para a esquerda para voltar à Visão Geral
            JButton setaEsquerda = new JButton(new ImageIcon("imagens/seta esquerda.png"));
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
                framePortaAdesivada.dispose();
                new AdesivoInstalado().setVisible(true);
            });
            panel.add(setaEsquerda);

            // Botão invisível para ir para a tela do Adesivo Instalado
            JButton areaClicavel = new JButton();
            areaClicavel.setBounds(0, 0, 1024, 768);
            areaClicavel.setContentAreaFilled(false);
            areaClicavel.setBorderPainted(false);
            areaClicavel.setOpaque(false);
            areaClicavel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            areaClicavel.addActionListener(e -> {
                framePortaAdesivada.dispose();
                new AdesivoInstalado().setVisible(true);
            });
            panel.add(areaClicavel);
            
            // Garante que a seta fique na frente da área clicável
            panel.setComponentZOrder(setaEsquerda, 0);
            panel.setComponentZOrder(areaClicavel, 1);

            framePortaAdesivada.setContentPane(panel);
            framePortaAdesivada.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Erro ao abrir imagem da porta adesivada: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PortaFechada();
        });
    }
} 