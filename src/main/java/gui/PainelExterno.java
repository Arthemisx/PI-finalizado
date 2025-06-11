package gui;

import util.WindowUtils;
import java.awt.*;
import javax.swing.*;

public class PainelExterno extends JFrame {
    private BackgroundPanel panel;

    public PainelExterno() {
        WindowUtils.configurarJanelaBasica(this, "Painel Externo", 1024, 768);
        initComponents();
    }

    private void initComponents() {
        try {
            // Carrega a imagem do painel externo
            ImageIcon backgroundIcon = new ImageIcon("imagens/19 - Painel externo porta 3 isolada.jpg");
            if (backgroundIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                throw new Exception("Erro ao carregar a imagem inicial");
            }
            
            panel = new BackgroundPanel(backgroundIcon.getImage());
            panel.setLayout(null);

            // Botão de seta para a esquerda para voltar
            JButton setaEsquerda = WindowUtils.criarBotaoSeta(
                "imagens/seta esquerda.png",
                new Rectangle(50, 384, 100, 100),
                e -> {
                    dispose();
                    new PainelAberto().setVisible(true);
                }
            );
            panel.add(setaEsquerda);

            setContentPane(panel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + e.getMessage());
        }
    }
} 