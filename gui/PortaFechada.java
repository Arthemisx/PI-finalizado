package gui;

import java.awt.*;
import javax.swing.*;
import java.io.File;

public class PortaFechada extends JFrame {
    private BackgroundPanel panel;
    private static final int LARGURA_JANELA = 1024;
    private static final int ALTURA_JANELA = 768;

    public PortaFechada() {
        setTitle("Porta Fechada");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(LARGURA_JANELA, ALTURA_JANELA);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void abrirPortaAdesivada() {
        try {
            System.out.println("Tentando abrir Porta Adesivada...");
            JFrame framePortaAdesivada = new JFrame("Porta Adesivada");
            framePortaAdesivada.setSize(LARGURA_JANELA, ALTURA_JANELA);
            framePortaAdesivada.setLocationRelativeTo(null);
            framePortaAdesivada.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
            File imageFile = new File("imagens/Porta adesivada.jpg");
            if (!imageFile.exists()) {
                throw new Exception("Arquivo de imagem não encontrado: " + imageFile.getAbsolutePath());
            }
            
            ImageIcon backgroundIcon = new ImageIcon(imageFile.getAbsolutePath());
            if (backgroundIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                throw new Exception("Erro ao carregar a imagem da porta adesivada");
            }
            
            Image backgroundImage = backgroundIcon.getImage();
            BackgroundPanel panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Botão de seta para a esquerda para voltar
            JButton setaEsquerda = new JButton(new ImageIcon("imagens/seta esquerda.png"));
            setaEsquerda.setBounds(50, 384, 100, 100);
            setaEsquerda.setContentAreaFilled(false);
            setaEsquerda.setBorderPainted(false);
            setaEsquerda.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaEsquerda.addActionListener(e -> {
                framePortaAdesivada.dispose();
                new PortaFechada().setVisible(true);
            });
            panel.add(setaEsquerda);

            framePortaAdesivada.setContentPane(panel);
            framePortaAdesivada.setVisible(true);
            System.out.println("Porta Adesivada aberta com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Erro ao abrir Porta Adesivada: " + e.getMessage() + "\n" +
                "Caminho atual: " + new File(".").getAbsolutePath(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        try {
            // Carregar imagem de fundo
            ImageIcon backgroundIcon = new ImageIcon("imagens/20 - Porta fechada.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            
            panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Área clicável MUITO grande para a porta da esquerda
            JButton portaEsquerdaBtn = new JButton();
            portaEsquerdaBtn.setBounds(50, 50, 400, 700); // Área bem grande para ser fácil de clicar
            portaEsquerdaBtn.setContentAreaFilled(false);
            portaEsquerdaBtn.setBorderPainted(false);
            portaEsquerdaBtn.setOpaque(false);
            portaEsquerdaBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            portaEsquerdaBtn.addActionListener(e -> {
                System.out.println("Clicou na porta esquerda");
                abrirPortaAdesivada();
            });
            panel.add(portaEsquerdaBtn);

            // Botão de seta para a esquerda para voltar à Visão Geral
            JButton setaEsquerda = new JButton(new ImageIcon("imagens/seta esquerda.png"));
            setaEsquerda.setBounds(50, 384, 100, 100);
            setaEsquerda.setContentAreaFilled(false);
            setaEsquerda.setBorderPainted(false);
            setaEsquerda.setCursor(new Cursor(Cursor.HAND_CURSOR));
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PortaFechada().setVisible(true);
        });
    }
} 