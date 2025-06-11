package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaImagensFlutuantes extends JFrame {
    private static final int LARGURA_JANELA = 1024;
    private static final int ALTURA_JANELA = 768;
    private BackgroundPanel panel;
    private JLabel adesivoLabel;
    private JLabel cinturaoLabel;
    private JLabel telasLabel;
    private JButton painelComunicacaoBtn;

    public TelaImagensFlutuantes() {
        setTitle("Imagens Flutuantes");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(LARGURA_JANELA, ALTURA_JANELA);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        try {
            // Carrega a imagem de fundo
            ImageIcon backgroundIcon = new ImageIcon("imagens/02_porta_aberta.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

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
                panel.remove(painelComunicacaoBtn);
                painelComunicacaoBtn = null;
                panel.revalidate();
                panel.repaint();
            });
            panel.add(painelComunicacaoBtn);

            // Adiciona o adesivo
            ImageIcon adesivoIcon = new ImageIcon("imagens/22 - Adesivo de porta isolada - 2 terços instalado.jpg");
            adesivoLabel = new JLabel(adesivoIcon);
            adesivoLabel.setBounds(50, 50, 200, 200);
            adesivoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            adesivoLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    panel.remove(adesivoLabel);
                    adesivoLabel = null;
                    panel.revalidate();
                    panel.repaint();
                }
            });
            panel.add(adesivoLabel);

            // Adiciona o cinturão
            ImageIcon cinturaoIcon = new ImageIcon("imagens/imagem do cinturao.jpeg");
            cinturaoLabel = new JLabel(cinturaoIcon);
            cinturaoLabel.setBounds(300, 50, 200, 200);
            cinturaoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            cinturaoLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    panel.remove(cinturaoLabel);
                    cinturaoLabel = null;
                    panel.revalidate();
                    panel.repaint();
                }
            });
            panel.add(cinturaoLabel);

            // Adiciona as telas
            ImageIcon telasIcon = new ImageIcon("imagens/telas.jpg");
            telasLabel = new JLabel(telasIcon);
            telasLabel.setBounds(550, 50, 200, 200);
            telasLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            telasLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    panel.remove(telasLabel);
                    telasLabel = null;
                    panel.revalidate();
                    panel.repaint();
                }
            });
            panel.add(telasLabel);

            // Botão de seta para a esquerda para voltar
            JButton setaEsquerda = new JButton(new ImageIcon("imagens/seta esquerda.png"));
            setaEsquerda.setBounds(50, 384, 100, 100);
            setaEsquerda.setContentAreaFilled(false);
            setaEsquerda.setBorderPainted(false);
            setaEsquerda.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaEsquerda.addActionListener(e -> {
                dispose();
                new PortaAberta().setVisible(true);
            });
            panel.add(setaEsquerda);

            setContentPane(panel);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Erro ao carregar imagens: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaImagensFlutuantes().setVisible(true);
        });
    }
} 