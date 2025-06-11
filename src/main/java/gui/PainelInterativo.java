package gui;

import controller.TelaController;
import java.awt.*;
import java.io.File;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class PainelInterativo extends JFrame {
    private final Map<String, String> mapaImagens = new HashMap<>();
    private final Instant startTime = Instant.now();

    public PainelInterativo() {
        inicializarMapaImagens();
        configurarJanela();
        inicializarComponentes();
    }

    private void inicializarMapaImagens() {
        mapaImagens.put("DDU Aberta", "imagens/03 - DDU com fachada aberta.jpg");
        mapaImagens.put("DDU Fechada", "imagens/DDU- porta fechada.jpg");
        mapaImagens.put("DDU Falha", "imagens/DDU - porta com falha.jpg");
        mapaImagens.put("DDU Sem Comunicacao", "imagens/DDU - porta sem comunicação.jpg");
        mapaImagens.put("DDU Uma Porta Nao Fecha", "imagens/DDU uma porta não fecha.jpg");
        mapaImagens.put("Tela Central", "imagens/tela_central.png");
        mapaImagens.put("Tela Auxiliar 1", "imagens/05 - Módulo de Comunicação - tela de início.jpg");
        mapaImagens.put("Tela Auxiliar 2", "imagens/botoes do painel editados.jpg");
        mapaImagens.put("Tela Câmeras", "imagens/tela_cameras.jpg");
        mapaImagens.put("Alavanca", "imagens/alavanca_controle.jpg");
        mapaImagens.put("Seta Amarela", "imagens/adesivo_seta_amarela.png");
    }

    private void configurarJanela() {
        setTitle("Painel Interativo do Trem");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                mostrarTempoExecucao();
                dispose();
            }
        });
    }

    private void mostrarTempoExecucao() {
        long segundos = java.time.Duration.between(startTime, Instant.now()).getSeconds();
        JOptionPane.showMessageDialog(this,
            String.format("Tempo de execução: %d minutos e %d segundos",
                segundos / 60, segundos % 60),
            "Tempo de Execução",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void inicializarComponentes() {
        try {
            ImageIcon backgroundIcon = new ImageIcon("imagens/painel alterado.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            
            // Verifica se a imagem foi carregada corretamente
            if (backgroundImage.getWidth(null) <= 0 || backgroundImage.getHeight(null) <= 0) {
                throw new Exception("Erro ao carregar a imagem de fundo");
            }
            
            BackgroundPanel panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);
            panel.setPreferredSize(new Dimension(1024, 768));

            // Adiciona a seta amarela clicável no meio do lado direito
            try {
                String caminhoSeta = "imagens/seta direita.png";
                File arquivoSeta = new File(caminhoSeta);
                if (!arquivoSeta.exists()) {
                    throw new Exception("Arquivo da seta não encontrado: " + caminhoSeta);
                }
                
                ImageIcon setaIcon = new ImageIcon(caminhoSeta);
                if (setaIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                    throw new Exception("Erro ao carregar a imagem da seta");
                }
                
                JButton setaAmarela = new JButton(setaIcon);
                setaAmarela.setBounds(900, 384, 100, 100);
                setaAmarela.setContentAreaFilled(false);
                setaAmarela.setBorderPainted(false);
                setaAmarela.setCursor(new Cursor(Cursor.HAND_CURSOR));
                setaAmarela.addActionListener(e -> {
                    try {
                        ColunaDireita colunaDireita = new ColunaDireita();
                        colunaDireita.setVisible(true);
                        this.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this,
                            "Erro ao abrir Coluna Direita: " + ex.getMessage(),
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    }
                });
                panel.add(setaAmarela);

                // Adiciona a seta esquerda
                String caminhoSetaEsquerda = "imagens/seta esquerda.png";
                File arquivoSetaEsquerda = new File(caminhoSetaEsquerda);
                if (!arquivoSetaEsquerda.exists()) {
                    throw new Exception("Arquivo da seta esquerda não encontrado: " + caminhoSetaEsquerda);
                }
                
                ImageIcon setaEsquerdaIcon = new ImageIcon(caminhoSetaEsquerda);
                if (setaEsquerdaIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                    throw new Exception("Erro ao carregar a imagem da seta esquerda");
                }
                
                JButton setaEsquerda = new JButton(setaEsquerdaIcon);
                setaEsquerda.setBounds(50, 384, 100, 100);
                setaEsquerda.setContentAreaFilled(false);
                setaEsquerda.setBorderPainted(false);
                setaEsquerda.setCursor(new Cursor(Cursor.HAND_CURSOR));
                setaEsquerda.addActionListener(e -> {
                    try {
                        PortaCabineEsquerda portaCabineEsquerda = new PortaCabineEsquerda();
                        portaCabineEsquerda.setVisible(true);
                        this.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this,
                            "Erro ao abrir Porta Cabine Esquerda: " + ex.getMessage(),
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    }
                });
                panel.add(setaEsquerda);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Erro ao carregar imagem da seta: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }

            adicionarBotoesPrincipais(panel);

            setContentPane(panel);
            pack();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar a imagem de fundo: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adicionarBotoesPrincipais(JPanel panel) {
        adicionarBotao(panel, 75, 300, 140, 110, "DDU Aberta");
        adicionarBotao(panel, 390, 225, 160, 120, "Tela Central");
        adicionarBotao(panel, 620, 260, 85, 110, "Tela Auxiliar 2");
        adicionarBotao(panel, 770, 300, 140, 90, "Tela Câmeras");
        adicionarBotao(panel, 303, 385, 70, 35, "Alavanca");
        adicionarBotao(panel, 240, 225, 140, 120, "Tela Auxiliar 1");
    }

    private void adicionarBotao(JPanel panel, int x, int y, int w, int h, String nomeTela) {
        JButton botao = new JButton();
        botao.setBounds(x, y, w, h);
        botao.setOpaque(false);
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.setName(nomeTela);
        botao.setToolTipText("Abrir: " + nomeTela);
        botao.addActionListener(e -> abrirTelaEspecifica(nomeTela, botao));
        panel.add(botao);
    }

    private void abrirTelaEspecifica(String nomeTela, JButton botao) {
        try {
            if (nomeTela.equals("Alavanca")) {
                new TelaAlavanca().setVisible(true);
                return;
            }
            
            if (nomeTela.equals("Tela Auxiliar 1")) {
                TelaController.abrirTelaAuxiliar1(this);
                return;
            }
            
            if (nomeTela.startsWith("DDU")) {
                new DDU().setVisible(true);
                return;
            }
            
            String caminhoImagem = mapaImagens.get(nomeTela);
            if (caminhoImagem == null) {
                JOptionPane.showMessageDialog(this, 
                    "Imagem não encontrada para a tela: " + nomeTela,
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            File arquivo = new File(caminhoImagem);
            if (!arquivo.exists()) {
                JOptionPane.showMessageDialog(this, 
                    "Arquivo de imagem não encontrado: " + caminhoImagem,
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            ImageIcon imagem = new ImageIcon(caminhoImagem);
            if (imagem.getImageLoadStatus() != MediaTracker.COMPLETE) {
                JOptionPane.showMessageDialog(this, 
                    "Erro ao carregar a imagem: " + caminhoImagem,
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFrame frame = new JFrame(nomeTela);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(new JLabel(imagem));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao abrir tela: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}