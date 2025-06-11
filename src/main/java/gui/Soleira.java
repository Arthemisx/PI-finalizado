package gui;

import java.awt.*;
import javax.swing.*;

public class Soleira extends JFrame {
    private BackgroundPanel panel;

    public Soleira() {
        setTitle("Soleira");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        try {
            // Carregar imagem de fundo
            ImageIcon backgroundIcon = new ImageIcon("imagens/05_soleira_2.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            
            panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Botão de seta para cima para voltar à porta aberta
            ImageIcon setaCimaIcon = new ImageIcon("imagens/seta pra cima.png");
            JButton setaCima = new JButton(setaCimaIcon);
            setaCima.setBounds(462, 150, 100, 100);
            setaCima.setContentAreaFilled(false);
            setaCima.setBorderPainted(false);
            setaCima.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaCima.addActionListener(e -> {
                dispose();
                new PortaAberta().setVisible(true);
            });
            panel.add(setaCima);

            setContentPane(panel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + e.getMessage());
        }
    }
} 