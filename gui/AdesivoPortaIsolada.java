package gui;

import java.awt.*;
import javax.swing.*;

public class AdesivoPortaIsolada extends JFrame {

    public AdesivoPortaIsolada() {
        configurarJanela();
        initComponents();
    }

    private void configurarJanela() {
        setTitle("Adesivo de Porta Isolada");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1024, 768);
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
        try {
            ImageIcon backgroundIcon = new ImageIcon("imagens/22 - Adesivo de porta isolada - 2 terços instalado.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            
            if (backgroundImage.getWidth(null) <= 0 || backgroundImage.getHeight(null) <= 0) {
                throw new Exception("Erro ao carregar a imagem de fundo");
            }
            
            BackgroundPanel panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

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
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + e.getMessage());
        }
    }
} 