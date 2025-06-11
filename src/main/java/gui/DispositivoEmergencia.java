package gui;

import java.awt.*;
import javax.swing.*;

public class DispositivoEmergencia extends JFrame {

    public DispositivoEmergencia() {
        configurarJanela();
        initComponents();
    }

    private void configurarJanela() {
        setTitle("Dispositivo de Emergência");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setResizable(false);

        // Adiciona um listener para mostrar/esconder imagens flutuantes
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
            ImageIcon backgroundIcon = new ImageIcon("imagens/03_dispositivo_emergencia.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            
            // Verifica se a imagem foi carregada corretamente
            if (backgroundImage.getWidth(null) <= 0 || backgroundImage.getHeight(null) <= 0) {
                throw new Exception("Erro ao carregar a imagem de fundo");
            }
            
            BackgroundPanel panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Botão de seta para baixo para voltar à porta aberta
            JButton setaBaixo = new JButton(new ImageIcon("imagens/seta pra baixo.png"));
            setaBaixo.setBounds(462, 600, 100, 100);
            setaBaixo.setContentAreaFilled(false);
            setaBaixo.setBorderPainted(false);
            setaBaixo.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaBaixo.addActionListener(e -> {
                dispose();
                new PortaAberta().setVisible(true);
            });
            panel.add(setaBaixo);

            setContentPane(panel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + e.getMessage());
        }
    }
} 