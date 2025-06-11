package gui.componentes;

import gui.BackgroundPanel;
import gui.UIUtils;
import util.WindowUtils;
import java.awt.*;
import javax.swing.*;

public class ChaveCBTC extends JFrame {

    public ChaveCBTC() {
        WindowUtils.configurarJanelaBasica(this, "Chave CBTC", 1200, 800);
        initComponents();
    }

    private void initComponents() {
        Image backgroundImage = UIUtils.loadImage("imagens/chave CBTC AM.jpg");
        if (backgroundImage == null) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao carregar imagem de fundo", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        BackgroundPanel mainPanel = new BackgroundPanel(backgroundImage);
        mainPanel.setLayout(null);

        // Botão de seta para retornar
        ImageIcon setaIcon = new ImageIcon("imagens/adesivo_seta_amarela2.png");
        if (setaIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            JButton setaRetorno = WindowUtils.criarBotaoSeta(
                "imagens/adesivo_seta_amarela2.png",
                new Rectangle(20, 300, 80, 60),
                evt -> {
                    dispose();
                    new DIC().setVisible(true);
                }
            );
            mainPanel.add(setaRetorno);
        }

        // Botão de seta para avançar
        ImageIcon setaIcon2 = new ImageIcon("imagens/adesivo_seta_amarela2.png");
        if (setaIcon2.getImageLoadStatus() == MediaTracker.COMPLETE) {
            JButton setaAvancar = WindowUtils.criarBotaoSeta(
                "imagens/adesivo_seta_amarela2.png",
                new Rectangle(1100, 300, 80, 60),
                evt -> {
                    dispose();
                    new ColunaDireita().setVisible(true);
                }
            );
            mainPanel.add(setaAvancar);
        }

        setContentPane(mainPanel);
    }
} 