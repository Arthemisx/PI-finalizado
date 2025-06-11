package gui;

import java.awt.*;
import javax.swing.*;

public class ColunaCinturaoEAdesivo extends JFrame {

    public ColunaCinturaoEAdesivo() {
        configurarJanela();
        initComponents();
    }

    private void configurarJanela() {
        setTitle("Coluna Cinturão e Adesivo");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(false);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                ImagensFlutuantes.mostrarImagens();
            }

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                ImagensFlutuantes.esconderImagens();
            }
        });
    }

    private void initComponents() {
        Image backgroundImage = UIUtils.loadImage("imagens/cinturão e adesivo.jpg");
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

        ImageIcon setaIcon = new ImageIcon("imagens/adesivo_seta_amarela2.png");
        if (setaIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            JButton setaRetorno = UIUtils.BotaoDeSeta(
                "imagens/adesivo_seta_amarela2.png",
                new Rectangle(20, 300, 80, 60),
                evt -> {
                    dispose();
                    new ColunaDireita().setVisible(true);
                }
            );
            mainPanel.add(setaRetorno);
        }

        setContentPane(mainPanel);
    }
} 