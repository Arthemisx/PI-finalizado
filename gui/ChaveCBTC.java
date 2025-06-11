package gui;

import java.awt.*;
import javax.swing.*;

public class ChaveCBTC extends JFrame {
    private boolean imagemAtual = true; // true para AM, false para RM
    private BackgroundPanel panel;

    public ChaveCBTC() {
        configurarJanela();
        initComponents();
    }

    private void configurarJanela() {
        setTitle("Chave CBTC");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        try {
            // Carrega a primeira imagem
            ImageIcon backgroundIcon = new ImageIcon("imagens/cbtcAM.jpg");
            if (backgroundIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                throw new Exception("Erro ao carregar a imagem inicial");
            }
            
            panel = new BackgroundPanel(backgroundIcon.getImage());
            panel.setLayout(null);

            // Botão invisível para alternar imagens
            JButton botaoAlternar = new JButton();
            botaoAlternar.setBounds(150, 0, 874, 768);
            botaoAlternar.setContentAreaFilled(false);
            botaoAlternar.setBorderPainted(false);
            botaoAlternar.setOpaque(false);
            botaoAlternar.addActionListener(e -> {
                try {
                    imagemAtual = !imagemAtual;
                    String caminhoImagem = imagemAtual ?
                        "imagens/cbtcAM.jpg" :
                        "imagens/cbtc_rm.png";
                    
                    ImageIcon novaIcon = new ImageIcon(caminhoImagem);
                    if (novaIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                        throw new Exception("Erro ao carregar a imagem: " + caminhoImagem);
                    }
                    
                    panel.setImagem(novaIcon.getImage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                        "Erro ao trocar imagem: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                }
            });
            panel.add(botaoAlternar);

            JButton setaEsquerda = new JButton(new ImageIcon("imagens/seta esquerda.png"));
            setaEsquerda.setBounds(50, 384, 100, 100);
            setaEsquerda.setContentAreaFilled(false);
            setaEsquerda.setBorderPainted(false);
            setaEsquerda.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaEsquerda.addActionListener(e -> {
                dispose();
                new ColunaDireita().setVisible(true);
            });
            panel.add(setaEsquerda);

            setContentPane(panel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao carregar imagem: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
} 