package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaImagensFlutuantes extends JFrame {
    private static final int LARGURA_JANELA = 1024;
    private static final int ALTURA_JANELA = 768;
    private JPanel mainPanel;
    private JLabel adesivoLabel;
    private JLabel cinturaoLabel;
    private JLabel telasLabel;
    private JButton painelComunicacaoBtn;

    public TelaImagensFlutuantes() {
        configurarJanela();
        initComponents();
    }

    private void configurarJanela() {
        setTitle("Tela de Imagens");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(LARGURA_JANELA, ALTURA_JANELA);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        mainPanel = new JPanel(null);
        mainPanel.setPreferredSize(new Dimension(LARGURA_JANELA, ALTURA_JANELA));

        // Usar uma imagem que existe
        ImageIcon backgroundIcon = new ImageIcon("imagens/02_porta_aberta.jpg");
        if (backgroundIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            JLabel backgroundLabel = new JLabel(backgroundIcon);
            backgroundLabel.setBounds(0, 0, LARGURA_JANELA, ALTURA_JANELA);
            mainPanel.add(backgroundLabel);
        }

        // Adiciona o botão do painel de comunicação
        painelComunicacaoBtn = new JButton("Abrir Painel de Comunicação");
        painelComunicacaoBtn.setBounds(250, 300, 250, 40);
        painelComunicacaoBtn.setFont(new Font("Arial", Font.BOLD, 14));
        painelComunicacaoBtn.setBackground(new Color(0, 123, 255));
        painelComunicacaoBtn.setForeground(Color.WHITE);
        painelComunicacaoBtn.setFocusPainted(false);
        painelComunicacaoBtn.setBorder(BorderFactory.createRaisedBevelBorder());
        painelComunicacaoBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        painelComunicacaoBtn.addActionListener(e -> {
            mainPanel.remove(painelComunicacaoBtn);
            painelComunicacaoBtn = null;
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        mainPanel.add(painelComunicacaoBtn);

        // Adiciona o adesivo
        ImageIcon adesivoIcon = new ImageIcon("imagens/22 - Adesivo de porta isolada - 2 terços instalado.jpg");
        adesivoLabel = new JLabel(adesivoIcon);
        adesivoLabel.setBounds(50, 50, 200, 200);
        adesivoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        adesivoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPanel.remove(adesivoLabel);
                adesivoLabel = null;
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        mainPanel.add(adesivoLabel);

        // Adiciona o cinturão
        ImageIcon cinturaoIcon = new ImageIcon("imagens/imagem do cinturao.jpeg");
        cinturaoLabel = new JLabel(cinturaoIcon);
        cinturaoLabel.setBounds(300, 50, 200, 200);
        cinturaoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cinturaoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPanel.remove(cinturaoLabel);
                cinturaoLabel = null;
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        mainPanel.add(cinturaoLabel);

        // Adiciona as telas
        ImageIcon telasIcon = new ImageIcon("imagens/telas.jpg");
        telasLabel = new JLabel(telasIcon);
        telasLabel.setBounds(550, 50, 200, 200);
        telasLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        telasLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPanel.remove(telasLabel);
                telasLabel = null;
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        mainPanel.add(telasLabel);

        // Botão de retorno
        JButton setaEsquerda = new JButton(new ImageIcon("imagens/seta esquerda.png"));
        setaEsquerda.setBounds(20, 300, 80, 60);
        setaEsquerda.setContentAreaFilled(false);
        setaEsquerda.setBorderPainted(false);
        setaEsquerda.setCursor(new Cursor(Cursor.HAND_CURSOR));
        setaEsquerda.addActionListener(e -> dispose());
        mainPanel.add(setaEsquerda);

        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaImagensFlutuantes().setVisible(true);
        });
    }
} 