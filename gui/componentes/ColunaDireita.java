package gui.componentes;

import gui.BackgroundPanel;
import gui.UIUtils;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

public class ColunaDireita extends JFrame {
    private final String[] imagensAlternativas = {"imagens/frame_10.jpg", "imagens/frame_60.jpg"};
    private final int[] clickCounts = {0, 0, 0};
    private JLabel imagemLabel;
    private JDialog painelBotoesDialog;

    public ColunaDireita() {
        configurarJanela();
        initComponents();
    }

    private void configurarJanela() {
        setTitle("Coluna Direita");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        Image backgroundImage = UIUtils.loadImage("imagens/07 - Coluna lateral direita - DIC, Derivação de Portas e Chave do CBTC.jpg");
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

        // Configurar a imagem inicial
        ImageIcon imageIcon = new ImageIcon(imagensAlternativas[0]);
        Image image = imageIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        imagemLabel = new JLabel(new ImageIcon(image));
        imagemLabel.setBounds(100, 100, 300, 200);
        imagemLabel.setLayout(null);
        mainPanel.add(imagemLabel);

        // Adicionar os botões interativos
        adicionarBotaoInterativo(0, 200, 120, 60, 40, mainPanel);
        adicionarBotaoInterativo(1, 50, 150, 60, 40, mainPanel);
        adicionarBotaoInterativo(2, 200, 120, 60, 40, mainPanel);
        
        // Botão para DIC
        JButton DICButton = new JButton("DIC");
        DICButton.setBounds(520, 445, 100, 40);
        DICButton.setBackground(new Color(100, 149, 237));
        DICButton.setForeground(Color.WHITE);
        DICButton.setFont(new Font("Arial", Font.BOLD, 14));
        DICButton.setFocusPainted(false);
        DICButton.addActionListener(e -> {
            dispose();
            new DIC().setVisible(true);
        });
        mainPanel.add(DICButton);

        // Botão para CBTC
        JButton CBTCButton = new JButton("CBTC");
        CBTCButton.setBounds(520, 520, 100, 40);
        CBTCButton.setBackground(new Color(100, 149, 237));
        CBTCButton.setForeground(Color.WHITE);
        CBTCButton.setFont(new Font("Arial", Font.BOLD, 14));
        CBTCButton.setFocusPainted(false);
        CBTCButton.addActionListener(e -> {
            dispose();
            new ChaveCBTC().setVisible(true);
        });
        mainPanel.add(CBTCButton);

        // Botão para mostrar Painel de Botões
        JButton painelBotoesButton = new JButton("Painel de Botões");
        painelBotoesButton.setBounds(520, 595, 150, 40);
        painelBotoesButton.setBackground(new Color(100, 149, 237));
        painelBotoesButton.setForeground(Color.WHITE);
        painelBotoesButton.setFont(new Font("Arial", Font.BOLD, 14));
        painelBotoesButton.setFocusPainted(false);
        painelBotoesButton.addActionListener(e -> mostrarPainelBotoes());
        mainPanel.add(painelBotoesButton);

        // Botão de seta para retornar
        ImageIcon setaIcon = new ImageIcon("imagens/adesivo_seta_amarela2.png");
        if (setaIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            JButton setaRetorno = UIUtils.BotaoDeSeta(
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
            JButton setaAvancar = UIUtils.BotaoDeSeta(
                "imagens/adesivo_seta_amarela2.png",
                new Rectangle(1100, 300, 80, 60),
                evt -> {
                    dispose();
                    new ColunaCinturaoEAdesivo().setVisible(true);
                }
            );
            setaAvancar.setIcon(new ImageIcon(UIUtils.rotateImage(setaIcon2.getImage(), 180)));
            mainPanel.add(setaAvancar);
        }

        setContentPane(mainPanel);
    }

    private void adicionarBotaoInterativo(int index, int x, int y, int largura, int altura, JPanel panel) {
        JButton botao = new JButton("Botão " + (index + 1));
        botao.setBounds(x, y, largura, altura);
        botao.setOpaque(true);
        botao.setContentAreaFilled(true);
        botao.setBorderPainted(true);
        botao.setBackground(new Color(100, 149, 237));
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("Arial", Font.BOLD, 12));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (index == 0 || index == 1) {
            botao.setVisible(false);
        }

        botao.addActionListener(e -> {
            clickCounts[index]++;
            
            if (index == 2) {
                ImageIcon newIcon = new ImageIcon(imagensAlternativas[1]);
                Image newImage = newIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                imagemLabel.setIcon(new ImageIcon(newImage));
                
                for (Component comp : panel.getComponents()) {
                    if (comp instanceof JButton) {
                        JButton btn = (JButton) comp;
                        if (btn.getText().startsWith("Botão")) {
                            boolean isButton1or2 = btn.getText().equals("Botão 1") || btn.getText().equals("Botão 2");
                            btn.setVisible(isButton1or2);
                            btn.setEnabled(!btn.getText().equals("Botão 3"));
                        }
                    }
                }
            } else if (index == 1) {
                tocarSom("sons/som.wav");
            } else if (clickCounts[index] >= 2) {
                tocarSom("sons/som.wav");
                clickCounts[index] = 0;
            }
        });

        panel.add(botao);
    }

    private void tocarSom(String caminho) {
        try {
            File arquivoSom = new File(caminho);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(arquivoSom);
            try (Clip clip = AudioSystem.getClip()) {
                clip.open(audioIn);
                clip.start();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void mostrarPainelBotoes() {
        if (painelBotoesDialog != null && painelBotoesDialog.isVisible()) {
            painelBotoesDialog.dispose();
            return;
        }

        ImageIcon painelBotoesIcon = new ImageIcon("imagens/painel_botoes.jpg");
        if (painelBotoesIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar imagem do painel de botões",
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Criar um JDialog para mostrar a imagem
        painelBotoesDialog = new JDialog(this, "Painel de Botões", false);
        painelBotoesDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Redimensionar a imagem mantendo a proporção
        Image painelBotoesImage = painelBotoesIcon.getImage();
        int maxWidth = 800;  // Largura máxima da imagem
        int maxHeight = 600; // Altura máxima da imagem
        
        double scale = Math.min(
            (double) maxWidth / painelBotoesIcon.getIconWidth(),
            (double) maxHeight / painelBotoesIcon.getIconHeight()
        );
        
        int width = (int) (painelBotoesIcon.getIconWidth() * scale);
        int height = (int) (painelBotoesIcon.getIconHeight() * scale);
        
        Image scaledImage = painelBotoesImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        
        painelBotoesDialog.add(imageLabel);
        painelBotoesDialog.pack();
        painelBotoesDialog.setLocationRelativeTo(this);
        painelBotoesDialog.setVisible(true);
    }
} 