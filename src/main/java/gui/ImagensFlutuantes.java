package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class ImagensFlutuantes {
    private static JLabel adesivoLabel;
    private static JLabel cinturaoLabel;
    private static JLabel telasLabel;
    private static JButton painelComunicacaoBtn;
    private static JFrame currentWindow;
    private static boolean isVisible = false;

    public static boolean isVisible() {
        return isVisible;
    }

    private static void abrirNovaJanela(String imagePath) {
        if (currentWindow != null) {
            // Fecha a janela atual
            currentWindow.dispose();
            
            // Cria uma nova janela
            JFrame novaJanela = new JFrame();
            novaJanela.setSize(1024, 768);
            novaJanela.setLocationRelativeTo(null);
            novaJanela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
            try {
                // Carrega a nova imagem
                ImageIcon novaImagem = new ImageIcon(imagePath);
                if (novaImagem.getImageLoadStatus() != MediaTracker.COMPLETE) {
                    throw new Exception("Erro ao carregar a imagem");
                }
                
                // Cria o painel com a nova imagem
                BackgroundPanel novoPanel = new BackgroundPanel(novaImagem.getImage());
                novoPanel.setLayout(null);
                
                // Adiciona o botão de voltar
                JButton setaEsquerda = new JButton(new ImageIcon("imagens/seta esquerda.png"));
                setaEsquerda.setBounds(50, 384, 100, 100);
                setaEsquerda.setContentAreaFilled(false);
                setaEsquerda.setBorderPainted(false);
                setaEsquerda.setCursor(new Cursor(Cursor.HAND_CURSOR));
                setaEsquerda.addActionListener(e -> {
                    novaJanela.dispose();
                    new PortaAberta().setVisible(true);
                });
                novoPanel.add(setaEsquerda);
                
                novaJanela.setContentPane(novoPanel);
                novaJanela.setVisible(true);
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(novaJanela, 
                    "Erro ao abrir imagem: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void mostrarImagens() {
        // Encontra a janela atual
        Window[] windows = Window.getWindows();
        for (Window w : windows) {
            if (w instanceof JFrame && w.isVisible()) {
                currentWindow = (JFrame) w;
                break;
            }
        }

        if (currentWindow != null && !isVisible) {
            // Garante que o layout seja null para posicionamento absoluto
            currentWindow.getContentPane().setLayout(null);
            
            // Adiciona o botão do painel de comunicação primeiro
            painelComunicacaoBtn = new JButton("Abrir Painel de Comunicação");
            painelComunicacaoBtn.setBounds(250, 300, 250, 40);
            painelComunicacaoBtn.setFont(new Font("Arial", Font.BOLD, 14));
            painelComunicacaoBtn.setBackground(new Color(0, 123, 255));
            painelComunicacaoBtn.setForeground(Color.WHITE);
            painelComunicacaoBtn.setFocusPainted(false);
            painelComunicacaoBtn.setBorder(BorderFactory.createRaisedBevelBorder());
            painelComunicacaoBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            painelComunicacaoBtn.addActionListener(e -> {
                abrirNovaJanela("imagens/painel_botoes.jpg");
            });
            currentWindow.getContentPane().add(painelComunicacaoBtn);
            painelComunicacaoBtn.setVisible(true);

            // Adiciona o adesivo
            ImageIcon adesivoIcon = new ImageIcon("imagens/22 - Adesivo de porta isolada - 2 terços instalado.jpg");
            adesivoLabel = new JLabel(adesivoIcon);
            adesivoLabel.setBounds(50, 50, 200, 200);
            adesivoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            adesivoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    currentWindow.getContentPane().remove(adesivoLabel);
                    adesivoLabel = null;
                    currentWindow.getContentPane().revalidate();
                    currentWindow.getContentPane().repaint();
                }
            });
            currentWindow.getContentPane().add(adesivoLabel);

            // Adiciona o cinturão
            ImageIcon cinturaoIcon = new ImageIcon("imagens/imagem do cinturao.jpeg");
            cinturaoLabel = new JLabel(cinturaoIcon);
            cinturaoLabel.setBounds(300, 50, 200, 200);
            cinturaoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            cinturaoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    currentWindow.getContentPane().remove(cinturaoLabel);
                    cinturaoLabel = null;
                    currentWindow.getContentPane().revalidate();
                    currentWindow.getContentPane().repaint();
                }
            });
            currentWindow.getContentPane().add(cinturaoLabel);

            // Adiciona as telas
            ImageIcon telasIcon = new ImageIcon("imagens/telas.jpg");
            telasLabel = new JLabel(telasIcon);
            telasLabel.setBounds(550, 50, 200, 200);
            telasLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            telasLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    currentWindow.getContentPane().remove(telasLabel);
                    telasLabel = null;
                    currentWindow.getContentPane().revalidate();
                    currentWindow.getContentPane().repaint();
                }
            });
            currentWindow.getContentPane().add(telasLabel);

            // Força a atualização da interface
            currentWindow.getContentPane().revalidate();
            currentWindow.getContentPane().repaint();
            isVisible = true;
        }
    }

    public static void esconderImagens() {
        if (currentWindow != null && isVisible) {
            SwingUtilities.invokeLater(() -> {
                if (adesivoLabel != null) {
                    currentWindow.getContentPane().remove(adesivoLabel);
                    adesivoLabel = null;
                }
                if (cinturaoLabel != null) {
                    currentWindow.getContentPane().remove(cinturaoLabel);
                    cinturaoLabel = null;
                }
                if (telasLabel != null) {
                    currentWindow.getContentPane().remove(telasLabel);
                    telasLabel = null;
                }
                if (painelComunicacaoBtn != null) {
                    currentWindow.getContentPane().remove(painelComunicacaoBtn);
                    painelComunicacaoBtn = null;
                }
                currentWindow.getContentPane().revalidate();
                currentWindow.getContentPane().repaint();
                isVisible = false;
            });
        }
    }
} 