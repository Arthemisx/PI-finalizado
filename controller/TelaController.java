package controller;

import gui.BackgroundPanel;
import gui.TelaAlavanca;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import javax.sound.sampled.*;
import javax.swing.*;
import util.ImagemUtils;

public class TelaController {

    private static final List<String> ordemTelas = List.of(
        "Tela Esquerda",
        "Tela Auxiliar 1",
        "Tela Central",
        "Tela Auxiliar 2",
        "Tela Câmeras"
    );

    public static void abrirTela(String nomeTela, JFrame parent) {
        if (nomeTela.equals("Alavanca")) {
            TelaAlavanca telaAlavanca = new TelaAlavanca();
            telaAlavanca.setVisible(true);
            return;
        }

        if (nomeTela.equals("Tela Auxiliar 1")) {
            JFrame frame = new JFrame(nomeTela);
            frame.setSize(1024, 768);
            frame.setLocationRelativeTo(parent);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            try {
                String caminhoImagem = "imagens/05 - Módulo de Comunicação - tela de início.jpg";
                ImageIcon backgroundIcon = new ImageIcon(caminhoImagem);
                if (backgroundIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                    throw new Exception("Erro ao carregar a imagem: " + caminhoImagem);
                }
                Image backgroundImage = backgroundIcon.getImage();
                
                JPanel mainPanel = new JPanel(null);
                mainPanel.setPreferredSize(new Dimension(1024, 768));
                mainPanel.setOpaque(true);
                mainPanel.setBackground(Color.WHITE);

                BackgroundPanel panel = new BackgroundPanel(backgroundImage);
                panel.setLayout(null);
                panel.setBounds(0, 0, 1024, 768);
                mainPanel.add(panel);

                // Botão invisível para frame_60
                JButton botaoFrame60 = new JButton();
                botaoFrame60.setBounds(765, 412, 100, 70);
                botaoFrame60.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                botaoFrame60.addActionListener(e -> {
                    frame.dispose();
                    abrirFrame60(parent);
                });
                panel.add(botaoFrame60);

                // Adiciona a seta amarela clicável no lado direito
                ImageIcon setaIcon = new ImageIcon("imagens/adesivo_seta_amarela.png");
                if (setaIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                    throw new Exception("Erro ao carregar a imagem da seta");
                }
                JButton setaAmarela = new JButton(setaIcon);
                setaAmarela.setBounds(900, 384, 100, 100);
                setaAmarela.setContentAreaFilled(false);
                setaAmarela.setBorderPainted(false);
                setaAmarela.setCursor(new Cursor(Cursor.HAND_CURSOR));
                setaAmarela.addActionListener(e -> {
                    frame.dispose();
                    abrirPortaCabine(parent);
                });
                panel.add(setaAmarela);

                frame.setContentPane(mainPanel);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parent, "Erro ao carregar imagem: " + e.getMessage());
            }

            frame.setVisible(true);
            return;
        }

        String caminho = switch (nomeTela) {
            case "Tela Central" -> "imagens/tela_central.png";
            case "Tela Auxiliar 2" -> "imagens/botoes do painel editados.png";
            case "Tela Câmeras" -> "imagens/tela_cameras.jpg";
            case "DDU Aberta" -> "imagens/03 - DDU com fachada aberta.jpg";
            case "DDU Fechada" -> "imagens/DDU- porta fechada.jpg";
            case "DDU Falha" -> "imagens/DDU - porta com falha.jpg";
            case "DDU Sem Comunicacao" -> "imagens/DDU - porta sem comunicação.jpg";
            case "DDU Uma Porta Nao Fecha" -> "imagens/DDU uma porta não fecha.jpg";
            default -> null;
        };

        if (caminho == null) {
            JOptionPane.showMessageDialog(parent, "Imagem não encontrada: " + nomeTela);
            return;
        }

        JFrame novaJanela = new JFrame(nomeTela);
        novaJanela.setSize(1024, 768);
        novaJanela.setLocationRelativeTo(parent);
        novaJanela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        novaJanela.setLayout(new BorderLayout());

        JLabel labelImagem = new JLabel();
        labelImagem.setHorizontalAlignment(JLabel.CENTER);
        ImagemUtils.atualizarImagem(labelImagem, caminho, parent);

        JButton esquerda = new JButton("←");
        JButton direita = new JButton("→");
        esquerda.setFont(new Font("Arial", Font.BOLD, 20));
        direita.setFont(new Font("Arial", Font.BOLD, 20));

        esquerda.addActionListener(e -> {
            int idx = ordemTelas.indexOf(nomeTela);
            if (idx > 0) {
                novaJanela.dispose();
                abrirTela(ordemTelas.get(idx - 1), parent);
            }
        });

        direita.addActionListener(e -> {
            int idx = ordemTelas.indexOf(nomeTela);
            if (idx < ordemTelas.size() - 1) {
                novaJanela.dispose();
                abrirTela(ordemTelas.get(idx + 1), parent);
            }
        });

        JPanel botoes = new JPanel(new BorderLayout());
        botoes.add(esquerda, BorderLayout.WEST);
        botoes.add(direita, BorderLayout.EAST);

        novaJanela.add(new JScrollPane(labelImagem), BorderLayout.CENTER);
        novaJanela.add(botoes, BorderLayout.SOUTH);
        novaJanela.setVisible(true);
    }

    public static void mostrarTempoExecucao(Instant startTime) {
        Duration duracao = Duration.between(startTime, Instant.now());
        long horas = duracao.toHours();
        long minutos = duracao.toMinutesPart();
        long segundos = duracao.toSecondsPart();

        String mensagem = String.format("Tempo de execução: %02d:%02d:%02d", horas, minutos, segundos);
        JFrame frameTempo = new JFrame("Tempo final de jogo:");
        frameTempo.setSize(800, 600);
        frameTempo.setLocationRelativeTo(null);
        frameTempo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            String imagePath = "imagens/fim de jogo.jpg";
            File imageFile = new File(imagePath);
            if (!imageFile.exists()) throw new FileNotFoundException("Arquivo não encontrado: " + imagePath);

            ImageIcon backgroundIcon = new ImageIcon(imagePath);
            Image img = backgroundIcon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);

            JPanel backgroundPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                }
            };

            JPanel centerPanel = new JPanel(new BorderLayout());
            centerPanel.setOpaque(true);
            centerPanel.setBackground(new Color(0, 0, 0, 180));
            JLabel label = new JLabel(mensagem, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 36));
            label.setForeground(Color.WHITE);
            label.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            centerPanel.add(label, BorderLayout.CENTER);
            backgroundPanel.add(centerPanel, BorderLayout.CENTER);

            frameTempo.add(backgroundPanel);
            frameTempo.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar imagem de fundo: " + e.getMessage());
        }
    }

    public static String getProximaImagemDDU(String atual, JFrame parent) {
        List<String> imagens = List.of(
            "DDU Sem Comunicacao",
            "DDU Falha",
            "DDU Uma Porta Nao Fecha"
        );
        return imagens.get((int)(Math.random() * imagens.size()));
    }

    public static void abrirFrame10(JFrame parent) {
        JFrame frame10 = new JFrame("Frame 10");
        frame10.setSize(1024, 768);
        frame10.setLocationRelativeTo(parent);
        frame10.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame10.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(null);
        mainPanel.setPreferredSize(new Dimension(1024, 768));

        // Carregar imagem de fundo
        try {
            ImageIcon backgroundIcon = new ImageIcon("imagens/frame_10.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            
            BackgroundPanel panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Botão para frame_60
            JButton botaoFrame60 = new JButton("Próximo Frame");
            botaoFrame60.setBounds(400, 300, 200, 50);
            botaoFrame60.setBackground(new Color(100, 149, 237));
            botaoFrame60.setForeground(Color.WHITE);
            botaoFrame60.setFont(new Font("Arial", Font.BOLD, 14));
            botaoFrame60.addActionListener(e -> {
                frame10.dispose();
                abrirFrame60(parent);
            });
            panel.add(botaoFrame60);

            frame10.setContentPane(panel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Erro ao carregar imagem: " + e.getMessage());
        }

        frame10.setVisible(true);
    }

    public static void abrirFrame60(JFrame parent) {
        JFrame frame60 = new JFrame("Frame 60");
        frame60.setSize(1024, 768);
        frame60.setLocationRelativeTo(parent);
        frame60.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame60.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(null);
        mainPanel.setPreferredSize(new Dimension(1024, 768));

        try {
            ImageIcon backgroundIcon = new ImageIcon("imagens/frame_60.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            
            BackgroundPanel panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Botão de som
            JButton botaoSom = new JButton("Tocar Som");
            botaoSom.setBounds(654, 628, 100, 50);
            botaoSom.setBackground(new Color(100, 149, 237));
            botaoSom.setForeground(Color.WHITE);
            botaoSom.setFont(new Font("Arial", Font.BOLD, 14));
            botaoSom.addActionListener(e -> {
                try {
                    File arquivoSom = new File("sons/som.wav");
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(arquivoSom);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame60, "Erro ao tocar som: " + ex.getMessage());
                }
            });
            panel.add(botaoSom);

            frame60.setContentPane(panel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Erro ao carregar imagem: " + e.getMessage());
        }

        frame60.setVisible(true);
    }

    public static void abrirPortaCabine(JFrame parent) {
        JFrame framePortaCabine = new JFrame("Porta de Cabine");
        framePortaCabine.setSize(1024, 768);
        framePortaCabine.setLocationRelativeTo(parent);
        framePortaCabine.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePortaCabine.setLayout(new BorderLayout());

        try {
            ImageIcon backgroundIcon = new ImageIcon("imagens/Porta de cabine lateral direita - Copia.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            
            BackgroundPanel panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Botão de seta para a esquerda para voltar
            JButton setaEsquerda = new JButton(new ImageIcon("imagens/seta esquerda.png"));
            setaEsquerda.setBounds(50, 384, 100, 100);
            setaEsquerda.setContentAreaFilled(false);
            setaEsquerda.setBorderPainted(false);
            setaEsquerda.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaEsquerda.addActionListener(e -> {
                framePortaCabine.dispose();
                abrirTela("Tela Auxiliar 1", parent);
            });
            panel.add(setaEsquerda);

            framePortaCabine.setContentPane(panel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Erro ao carregar imagem: " + e.getMessage());
        }

        framePortaCabine.setVisible(true);
    }

    public static void abrirColunaDireita(JFrame parent) {
        parent.dispose();
        new gui.ColunaDireita().setVisible(true);
    }
}