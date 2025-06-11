package gui;

import util.ImagemUtils;
import util.WindowUtils;
import java.awt.*;
import javax.swing.*;

public class PainelFechado extends JFrame {
    private BackgroundPanel panel;
    private static final int LARGURA_JANELA = 1024;
    private static final int ALTURA_JANELA = 768;

    public PainelFechado() {
        WindowUtils.configurarJanelaBasica(this, "Painel Fechado", LARGURA_JANELA, ALTURA_JANELA);
        initComponents();
    }

    private void initComponents() {
        try {
            // Carrega a imagem do painel fechado
            Image backgroundImage = ImagemUtils.carregarImagem("imagens/07_painel_fechado.jpg", this);
            if (!ImagemUtils.verificarImagem(backgroundImage)) {
                throw new Exception("Erro ao carregar a imagem inicial");
            }
            
            panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Botão invisível para alternar imagens
            JButton botaoAlternar = WindowUtils.criarBotaoInvisivel(
                new Rectangle(0, 0, LARGURA_JANELA, ALTURA_JANELA),
                e -> {
                    dispose();
                    new PainelAberto().setVisible(true);
                }
            );
            panel.add(botaoAlternar);

            setContentPane(panel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao carregar imagem: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
} 