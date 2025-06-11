package gui;

import java.awt.*;
import javax.swing.*;

public class VisaoGeral extends JFrame {
    private final String origem;

    public VisaoGeral() {
        this("direita"); // Por padrão, assume que veio da direita
    }

    public VisaoGeral(String origem) {
        this.origem = origem;
        configurarJanela();
        initComponents();
    }

    private void configurarJanela() {
        setTitle("Visão Geral");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        try {
            ImageIcon backgroundIcon = new ImageIcon("imagens/01_visao_geral.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            
            // Verifica se a imagem foi carregada corretamente
            if (backgroundImage.getWidth(null) <= 0 || backgroundImage.getHeight(null) <= 0) {
                throw new Exception("Erro ao carregar a imagem de fundo");
            }
            
            BackgroundPanel panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Botão de seta para a esquerda para voltar
            ImageIcon setaEsquerdaIcon = new ImageIcon("imagens/seta esquerda.png");
            Image setaEsquerdaImg = setaEsquerdaIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JButton setaEsquerda = new JButton(new ImageIcon(setaEsquerdaImg));
            setaEsquerda.setBounds(50, 384, 100, 100);
            setaEsquerda.setContentAreaFilled(false);
            setaEsquerda.setBorderPainted(false);
            setaEsquerda.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaEsquerda.addActionListener(e -> {
                dispose();
                new PainelInterativo().setVisible(true);
            });
            panel.add(setaEsquerda);

            // Painel para conter a seta e o texto
            JPanel painelSeta = new JPanel();
            painelSeta.setLayout(new BoxLayout(painelSeta, BoxLayout.Y_AXIS));
            painelSeta.setOpaque(false);
            painelSeta.setBounds(850, 384, 200, 150);

            // Texto acima da seta
            JLabel texto = new JLabel("<html><center>Ir atuar na porta<br>com falha</center></html>");
            texto.setFont(new Font("Arial", Font.BOLD, 14));
            texto.setForeground(Color.WHITE);
            texto.setAlignmentX(Component.CENTER_ALIGNMENT);
            texto.setHorizontalAlignment(SwingConstants.CENTER);
            painelSeta.add(texto);

            // Espaçamento entre o texto e a seta
            painelSeta.add(Box.createVerticalStrut(10));

            // Seta para a direita
            ImageIcon setaDireitaIcon = new ImageIcon("imagens/seta direita.png");
            Image setaDireitaImg = setaDireitaIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JButton setaDireita = new JButton(new ImageIcon(setaDireitaImg));
            setaDireita.setPreferredSize(new Dimension(100, 100));
            setaDireita.setContentAreaFilled(false);
            setaDireita.setBorderPainted(false);
            setaDireita.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaDireita.setAlignmentX(Component.CENTER_ALIGNMENT);
            setaDireita.addActionListener(e -> {
                dispose();
                new PortaAberta().setVisible(true);
            });
            painelSeta.add(setaDireita);

            panel.add(painelSeta);

            setContentPane(panel);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Erro ao carregar imagens: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaImagensFlutuantes().setVisible(true);
        });
    }
} 