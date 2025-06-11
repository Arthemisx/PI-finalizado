package gui;

import util.WindowUtils;
import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class PainelAberto extends JFrame {
    private BackgroundPanel panel;
    private static final int LARGURA_JANELA = 1024;
    private static final int ALTURA_JANELA = 768;
    private Map<String, ImageIcon> imagens;

    public PainelAberto() {
        WindowUtils.configurarJanelaBasica(this, "Painel Aberto", LARGURA_JANELA, ALTURA_JANELA);
        carregarImagens();
        initComponents();
    }

    private void carregarImagens() {
        imagens = new HashMap<>();
        try {
            imagens.put("19_painel_isolado.jpg", new ImageIcon("imagens/19 - Painel externo porta 3 isolada.jpg"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagens: " + e.getMessage());
        }
    }

    private void criarTelaPainelIsolado() {
        JFrame framePainelIsolado = new JFrame("Painel Isolado");
        framePainelIsolado.setSize(LARGURA_JANELA, ALTURA_JANELA);
        framePainelIsolado.setLocationRelativeTo(null);
        framePainelIsolado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel(null);
        JLabel label = new JLabel(imagens.get("19_painel_isolado.jpg"));
        label.setBounds(0, 0, LARGURA_JANELA, ALTURA_JANELA);
        panel.add(label);

        // Botão de seta para a esquerda para voltar para a tela Porta Aberta
        JButton setaEsquerda = WindowUtils.criarBotaoSeta(
            "imagens/seta esquerda.png",
            new Rectangle(50, 384, 100, 100),
            e -> {
                framePainelIsolado.dispose();
                new PortaAberta().setVisible(true);
            }
        );
        panel.add(setaEsquerda);

        framePainelIsolado.setContentPane(panel);
        framePainelIsolado.setVisible(true);
        dispose(); // Fecha a janela atual
    }

    private void initComponents() {
        try {
            // Carrega a imagem do painel aberto
            ImageIcon backgroundIcon = new ImageIcon("imagens/08_painel_aberto.jpg");
            if (backgroundIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                throw new Exception("Erro ao carregar a imagem inicial");
            }
            
            panel = new BackgroundPanel(backgroundIcon.getImage());
            panel.setLayout(null);

            // Botão invisível que cobre toda a tela
            JButton botaoInvisivel = new JButton();
            botaoInvisivel.setBounds(0, 0, LARGURA_JANELA, ALTURA_JANELA);
            botaoInvisivel.setContentAreaFilled(false);
            botaoInvisivel.setBorderPainted(false);
            botaoInvisivel.setFocusPainted(false);
            botaoInvisivel.setOpaque(false);
            botaoInvisivel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            botaoInvisivel.addActionListener(e -> criarTelaPainelIsolado());
            panel.add(botaoInvisivel);

            setContentPane(panel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + e.getMessage());
        }
    }
} 