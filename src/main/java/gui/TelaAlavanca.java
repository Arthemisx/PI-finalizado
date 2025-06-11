package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaAlavanca extends JFrame {

    private String[] imagensPlanoFundo = {
        "imagens/ChaveReversoraFrente.jpg",
        "imagens/ChaveReversoraNeutro.jpg"
    };

    private int imagemAtual = 0;
    private PainelComImagem painelImagem;
    private JButton botaoAlternarImagem;

    public TelaAlavanca() {
        // Configurações básicas da janela
        setTitle("Alavanca de Controle");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        painelImagem = new PainelComImagem();
        painelImagem.setLayout(null);
        setContentPane(painelImagem);

        botaoAlternarImagem = new JButton("Alternar Imagem");
        botaoAlternarImagem.setContentAreaFilled(false);
        botaoAlternarImagem.setBorderPainted(false);
        botaoAlternarImagem.setFocusPainted(false);
        botaoAlternarImagem.setForeground(new Color(0, 0, 0, 0));
        botaoAlternarImagem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        botaoAlternarImagem.addActionListener(e -> {
            imagemAtual = (imagemAtual + 1) % imagensPlanoFundo.length;
            painelImagem.setImagem(imagensPlanoFundo[imagemAtual]);
        });

        painelImagem.add(botaoAlternarImagem);
        painelImagem.setImagem(imagensPlanoFundo[imagemAtual]);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                painelImagem.repaint();
                redimensionarBotao();
            }
        });

        redimensionarBotao();
    }

    private void redimensionarBotao() {
        int largura = 200;
        int altura = 220;
        int posX = (getWidth() - largura) / 2;
        int posY = (getHeight() - altura) / 2 - 40;

        if (getWidth() > 800) {
            largura = 400;
            altura = 440;
            posX = (getWidth() - largura) / 2;
            posY = (getHeight() - altura) / 2 - 40;
        }

        botaoAlternarImagem.setBounds(posX, posY, largura, altura);
    }

    // Painel que desenha a imagem de fundo
    class PainelComImagem extends JPanel {
        private Image imagemFundo;

        public void setImagem(String caminho) {
            try {
                imagemFundo = new ImageIcon(caminho).getImage();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Erro ao carregar imagem: " + caminho,
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagemFundo != null) {
                g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
} 