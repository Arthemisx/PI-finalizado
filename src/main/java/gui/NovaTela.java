package gui;

import javax.swing.*;
import java.awt.*;

public class NovaTela extends JFrame {
    
    public NovaTela() {
        // Configurações básicas da janela
        setTitle("Nova Tela");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Criando o painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        // Exemplo de componente
        JLabel label = new JLabel("Conteúdo da Nova Tela");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        
        mainPanel.add(label, BorderLayout.CENTER);
        
        // Adicionando o painel à janela
        setContentPane(mainPanel);
    }
} 