package gui;

import java.awt.*;
import javax.swing.*;
import java.io.File;

public class ChaveCBTC extends JFrame {
    private boolean imagemAtual = true; // true para AM, false para RM
    private BackgroundPanel panel;
    private static final String IMAGEM_AM = "imagens/cbtcAM.jpg";
    private static final String IMAGEM_RM = "imagens/cbtc_rm.png";

    public ChaveCBTC() {
        configurarJanela();
        initComponents();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Chave CBTC");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void verificarExistenciaImagem(String caminho) throws Exception {
        File arquivo = new File(caminho);
        if (!arquivo.exists()) {
            throw new Exception("Arquivo não encontrado: " + caminho + "\nDiretório atual: " + new File(".").getAbsolutePath());
        }
        System.out.println("Arquivo encontrado: " + caminho);
    }

    private Image carregarImagem(String caminho) throws Exception {
        verificarExistenciaImagem(caminho);
        ImageIcon icon = new ImageIcon(caminho);
        if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            throw new Exception("Erro ao carregar a imagem: " + caminho);
        }
        return icon.getImage();
    }

    private void initComponents() {
        try {
            System.out.println("Iniciando carregamento dos componentes...");
            
            // Carrega a primeira imagem
            Image imagemInicial = carregarImagem(IMAGEM_AM);
            System.out.println("Imagem inicial carregada com sucesso");
            
            panel = new BackgroundPanel(imagemInicial);
            panel.setLayout(null);

            // Botão invisível para alternar imagens
            JButton botaoAlternar = new JButton();
            botaoAlternar.setBounds(0, 0, 1024, 768);
            botaoAlternar.setContentAreaFilled(false);
            botaoAlternar.setBorderPainted(false);
            botaoAlternar.setOpaque(false);
            botaoAlternar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            botaoAlternar.addActionListener(e -> {
                System.out.println("Botão de alternar clicado");
                alternarImagem();
            });
            panel.add(botaoAlternar);

            // Botão de seta para a esquerda
            JButton setaEsquerda = new JButton();
            ImageIcon setaIcon = new ImageIcon("imagens/seta esquerda.png");
            if (setaIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                setaEsquerda.setIcon(setaIcon);
                System.out.println("Imagem da seta carregada com sucesso");
            } else {
                System.out.println("Erro ao carregar imagem da seta");
                setaEsquerda.setText("←"); // Fallback para texto caso a imagem não carregue
            }
            
            setaEsquerda.setBounds(20, 334, 100, 100); // Ajustei a posição e tamanho
            setaEsquerda.setContentAreaFilled(false);
            setaEsquerda.setBorderPainted(false);
            setaEsquerda.setOpaque(false);
            setaEsquerda.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaEsquerda.setFocusPainted(false);
            
            // Adiciona um hover effect
            setaEsquerda.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    setaEsquerda.setContentAreaFilled(true);
                    setaEsquerda.setBackground(new Color(200, 200, 200, 100));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    setaEsquerda.setContentAreaFilled(false);
                }
            });

            setaEsquerda.addActionListener(e -> {
                System.out.println("Seta clicada - voltando para ColunaDireita");
                dispose();
                new ColunaDireita().setVisible(true);
            });
            
            // Garante que o botão da seta fique na frente do botão de alternar imagem
            panel.add(setaEsquerda);
            panel.setComponentZOrder(setaEsquerda, 0);
            panel.setComponentZOrder(botaoAlternar, 1);

            setContentPane(panel);
            System.out.println("Componentes inicializados com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Erro ao inicializar a tela: " + e.getMessage() + "\n" +
                "Por favor, verifique se todas as imagens necessárias estão presentes.",
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alternarImagem() {
        try {
            System.out.println("Alternando imagem. Estado atual: " + (imagemAtual ? "AM" : "RM"));
            
            // Primeiro carrega a nova imagem
            String caminhoImagem = imagemAtual ? IMAGEM_RM : IMAGEM_AM;
            System.out.println("Tentando carregar: " + caminhoImagem);
            
            Image novaImagem = carregarImagem(caminhoImagem);
            
            // Se chegou aqui, a imagem foi carregada com sucesso
            imagemAtual = !imagemAtual;
            panel.setImagem(novaImagem);
            System.out.println("Imagem alternada com sucesso para: " + caminhoImagem);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Erro ao trocar imagem: " + ex.getMessage() + "\n" +
                "Por favor, verifique se a imagem existe no diretório correto.",
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
} 