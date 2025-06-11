package gui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

public class ColunaDireita extends JFrame {
    private final String[] imagensAlternativas = {"imagens/frame_10.jpg", "imagens/frame_60.jpg"};
    private final int[] clickCounts = {0, 0, 0};
    private JLabel imagemLabel;
    private BackgroundPanel panel;
    private boolean mostrarPrimeiraImagem = true;
    private JFrame frameCinturao;
    private JFrame frameAdesivo;
    private JButton botaoCBTC;

    public ColunaDireita() {
        setTitle("Coluna Direita");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        try {
            ImageIcon backgroundIcon = new ImageIcon("imagens/Coluna lateral.jpg");
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
                dispose();
                new PainelInterativo().setVisible(true);
            });
            panel.add(setaEsquerda);

            // Botão de seta para a direita
            JButton setaDireita = new JButton(new ImageIcon("imagens/seta direita.png"));
            setaDireita.setBounds(900, 384, 100, 100);
            setaDireita.setContentAreaFilled(false);
            setaDireita.setBorderPainted(false);
            setaDireita.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaDireita.addActionListener(e -> {
                dispose();
                abrirPortaCabine();
            });
            panel.add(setaDireita);
            

            // Botão CBTC
            botaoCBTC = new JButton(new ImageIcon("imagens/cbtc_rm.png"));
            botaoCBTC.setBounds(430, 580, 70, 60);
            botaoCBTC.setContentAreaFilled(false);
            botaoCBTC.setBorderPainted(false);
            botaoCBTC.setOpaque(false);
            botaoCBTC.addActionListener(e -> {
                dispose();
                new ChaveCBTC().setVisible(true);
            });
            panel.add(botaoCBTC);

            setContentPane(panel);
            setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + e.getMessage());
        }
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
                tocarSom("sons/gongo.wav");
            } else if (clickCounts[index] >= 2) {
                tocarSom("sons/gongo.wav");
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

    private void abrirPortaCabine() {
        JFrame framePortaCabine = new JFrame("Porta de Cabine");
        framePortaCabine.setSize(1024, 768);
        framePortaCabine.setLocationRelativeTo(null);
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
                new ColunaDireita().setVisible(true);
            });
            panel.add(setaEsquerda);

            // Botão de seta para a direita para ir para a parte de trás
            JButton setaDireita = new JButton(new ImageIcon("imagens/seta direita.png"));
            setaDireita.setBounds(900, 384, 100, 100);
            setaDireita.setContentAreaFilled(false);
            setaDireita.setBorderPainted(false);
            setaDireita.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaDireita.addActionListener(e -> {
                framePortaCabine.dispose();
                abrirCabineTras();
            });
            panel.add(setaDireita);

            // Botão para Visão Geral
            JButton botaoVisaoGeral = new JButton("Visão Geral");
            botaoVisaoGeral.setBounds(132, 84, 500, 600);
            botaoVisaoGeral.setBackground(new Color(100, 149, 237));
            botaoVisaoGeral.setForeground(Color.WHITE);
            botaoVisaoGeral.setFont(new Font("Arial", Font.BOLD, 16));
            botaoVisaoGeral.setFocusPainted(false);
            botaoVisaoGeral.setContentAreaFilled(false);
            botaoVisaoGeral.setBorderPainted(false);
            botaoVisaoGeral.setOpaque(false);
            botaoVisaoGeral.setText("");
            botaoVisaoGeral.addActionListener(e -> {
                framePortaCabine.dispose();
                abrirVisaoGeral();
            });
            panel.add(botaoVisaoGeral);

            framePortaCabine.setContentPane(panel);
            framePortaCabine.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + e.getMessage());
        }
    }

    private void abrirVisaoGeral() {
        JFrame frameVisaoGeral = new JFrame("Visão Geral");
        frameVisaoGeral.setSize(1024, 768);
        frameVisaoGeral.setLocationRelativeTo(null);
        frameVisaoGeral.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameVisaoGeral.setLayout(new BorderLayout());

        try {
            ImageIcon backgroundIcon = new ImageIcon("imagens/01_visao_geral.jpg");
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
                frameVisaoGeral.dispose();
                abrirPortaCabine();
            });
            panel.add(setaEsquerda);

            // Botão de seta para a direita
            JButton setaDireita = new JButton(new ImageIcon("imagens/seta direita.png"));
            setaDireita.setBounds(900, 384, 100, 100);
            setaDireita.setContentAreaFilled(false);
            setaDireita.setBorderPainted(false);
            setaDireita.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaDireita.addActionListener(e -> {
                frameVisaoGeral.dispose();
                abrirPortaAberta();
            });
            panel.add(setaDireita);

            // Label com texto em cima da seta direita
            JLabel labelSetaDireita = new JLabel("Atuar na Porta Aberta");
            labelSetaDireita.setBounds(900, 350, 100, 30);
            labelSetaDireita.setHorizontalAlignment(SwingConstants.CENTER);
            labelSetaDireita.setForeground(Color.WHITE);
            labelSetaDireita.setFont(new Font("Arial", Font.BOLD, 14));
            panel.add(labelSetaDireita);

            frameVisaoGeral.setContentPane(panel);
            frameVisaoGeral.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + e.getMessage());
        }
    }

    private void abrirPortaAberta() {
        JFrame framePortaAberta = new JFrame("Porta Aberta");
        framePortaAberta.setSize(1024, 768);
        framePortaAberta.setLocationRelativeTo(null);
        framePortaAberta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePortaAberta.setLayout(new BorderLayout());

        try {
            ImageIcon backgroundIcon = new ImageIcon("imagens/02_porta_aberta.jpg");
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
                framePortaAberta.dispose();
                abrirVisaoGeral();
            });
            panel.add(setaEsquerda);

            // Botão de seta para a direita
            JButton setaDireita = new JButton(new ImageIcon("imagens/seta direita.png"));
            setaDireita.setBounds(900, 384, 100, 100);
            setaDireita.setContentAreaFilled(false);
            setaDireita.setBorderPainted(false);
            setaDireita.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaDireita.addActionListener(e -> {
                framePortaAberta.dispose();
                abrirPainelFechado();
            });
            panel.add(setaDireita);

            // Botão de seta para cima
            JButton setaCima = new JButton(new ImageIcon("imagens/seta pra cima.png"));
            setaCima.setBounds(475, 150, 100, 100);
            setaCima.setContentAreaFilled(false);
            setaCima.setBorderPainted(false);
            setaCima.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaCima.addActionListener(e -> {
                framePortaAberta.dispose();
                abrirDispositivoEmergencia();
            });
            panel.add(setaCima);

            framePortaAberta.setContentPane(panel);
            framePortaAberta.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + e.getMessage());
        }
    }

    private void abrirPainelFechado() {
        JFrame framePainelFechado = new JFrame("Painel Fechado");
        framePainelFechado.setSize(1024, 768);
        framePainelFechado.setLocationRelativeTo(null);
        framePainelFechado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePainelFechado.setLayout(new BorderLayout());

        try {
            ImageIcon backgroundIcon = new ImageIcon("imagens/07_painel_fechado.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            
            BackgroundPanel panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Botão invisível que cobre toda a tela para detectar cliques
            JButton botaoPainel = new JButton();
            botaoPainel.setBounds(0, 0, 1024, 768);
            botaoPainel.setContentAreaFilled(false);
            botaoPainel.setBorderPainted(false);
            botaoPainel.setFocusPainted(false);
            botaoPainel.setOpaque(false);
            botaoPainel.addActionListener(e -> {
                framePainelFechado.dispose();
                abrirPainelAberto();
            });
            panel.add(botaoPainel);

            // Botão de seta para a esquerda para voltar
            JButton setaEsquerda = new JButton(new ImageIcon("imagens/seta esquerda.png"));
            setaEsquerda.setBounds(50, 384, 100, 100);
            setaEsquerda.setContentAreaFilled(false);
            setaEsquerda.setBorderPainted(false);
            setaEsquerda.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaEsquerda.addActionListener(e -> {
                framePainelFechado.dispose();
                abrirPortaAberta();
            });
            panel.add(setaEsquerda);

            framePainelFechado.setContentPane(panel);
            framePainelFechado.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + e.getMessage());
        }
    }

    private void abrirPainelAberto() {
        JFrame framePainelAberto = new JFrame("Painel Aberto");
        framePainelAberto.setSize(1024, 768);
        framePainelAberto.setLocationRelativeTo(null);
        framePainelAberto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePainelAberto.setLayout(new BorderLayout());

        try {
            ImageIcon backgroundIcon = new ImageIcon("imagens/08_painel_aberto.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            
            BackgroundPanel panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Botão invisível que cobre toda a tela para detectar cliques
            JButton botaoPainel = new JButton();
            botaoPainel.setBounds(0, 0, 1024, 768);
            botaoPainel.setContentAreaFilled(false);
            botaoPainel.setBorderPainted(false);
            botaoPainel.setFocusPainted(false);
            botaoPainel.setOpaque(false);
            botaoPainel.addActionListener(e -> {
                framePainelAberto.dispose();
                abrirPainelExternoPorta3();
            });
            panel.add(botaoPainel);

            // Botão de seta para a esquerda para voltar
            JButton setaEsquerda = new JButton(new ImageIcon("imagens/seta esquerda.png"));
            setaEsquerda.setBounds(50, 384, 100, 100);
            setaEsquerda.setContentAreaFilled(false);
            setaEsquerda.setBorderPainted(false);
            setaEsquerda.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaEsquerda.addActionListener(e -> {
                framePainelAberto.dispose();
                abrirPainelFechado();
            });
            panel.add(setaEsquerda);

            framePainelAberto.setContentPane(panel);
            framePainelAberto.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + e.getMessage());
        }
    }

    private void abrirPainelExternoPorta3() {
        JFrame framePainelExterno = new JFrame("Painel Externo Porta 3");
        framePainelExterno.setSize(1024, 768);
        framePainelExterno.setLocationRelativeTo(null);
        framePainelExterno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePainelExterno.setLayout(new BorderLayout());

        try {
            ImageIcon backgroundIcon = new ImageIcon("imagens/19 - Painel externo porta 3 isolada.jpg");
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
                framePainelExterno.dispose();
                abrirPortaAberta();
            });
            panel.add(setaEsquerda);

            framePainelExterno.setContentPane(panel);
            framePainelExterno.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + e.getMessage());
        }
    }

    private void abrirCabineTras() {
        JFrame frameCabineTras = new JFrame("Parte de Trás da Cabine");
        frameCabineTras.setSize(1024, 768);
        frameCabineTras.setLocationRelativeTo(null);
        frameCabineTras.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameCabineTras.setLayout(new BorderLayout());

        // Adicionar listener para fechar as imagens flutuantes quando a janela principal for fechada
        frameCabineTras.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (frameCinturao != null) {
                    frameCinturao.dispose();
                    frameCinturao = null;
                }
                if (frameAdesivo != null) {
                    frameAdesivo.dispose();
                    frameAdesivo = null;
                }
            }
        });

        try {
            ImageIcon backgroundIcon = new ImageIcon("imagens/cabine - parte de trás.jpg");
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
                frameCabineTras.dispose();
                new PainelInterativo().setVisible(true);
            });
            panel.add(setaEsquerda);

            // Botão para mostrar imagens flutuantes
            JButton botaoImagens = new JButton("Mostrar Imagens");
            botaoImagens.setBounds(400, 450, 200, 50);
            botaoImagens.setBackground(new Color(100, 149, 237));
            botaoImagens.setForeground(Color.WHITE);
            botaoImagens.setFont(new Font("Arial", Font.BOLD, 16));
            botaoImagens.setFocusPainted(false);
            botaoImagens.addActionListener(e -> mostrarImagensFlutuantes());
            panel.add(botaoImagens);

            frameCabineTras.setContentPane(panel);
            frameCabineTras.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + e.getMessage());
        }
    }

    private void mostrarImagensFlutuantes() {
        // Criar frame para o cinturão
        if (frameCinturao == null) {
            frameCinturao = new JFrame();
            frameCinturao.setSize(150, 100);
            frameCinturao.setLocation(350, 600);
            frameCinturao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frameCinturao.setUndecorated(true);
            frameCinturao.setAlwaysOnTop(true);

            try {
                ImageIcon icon = new ImageIcon("imagens/imagem do cinturao.jpeg");
                Image image = icon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
                JLabel label = new JLabel(new ImageIcon(image));
                
                // Adicionar clique para fechar
                label.setCursor(new Cursor(Cursor.HAND_CURSOR));
                label.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        frameCinturao.dispose();
                        frameCinturao = null;
                    }
                });
                
                frameCinturao.add(label);
                frameCinturao.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar imagem do cinturão: " + e.getMessage());
            }
        }

        // Criar frame para o adesivo
        if (frameAdesivo == null) {
            frameAdesivo = new JFrame();
            frameAdesivo.setSize(150, 100);
            frameAdesivo.setLocation(520, 600);
            frameAdesivo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frameAdesivo.setUndecorated(true);
            frameAdesivo.setAlwaysOnTop(true);

            try {
                ImageIcon icon = new ImageIcon("imagens/imagem do adesivo.jpeg");
                Image image = icon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
                JLabel label = new JLabel(new ImageIcon(image));
                
                // Adicionar clique para fechar
                label.setCursor(new Cursor(Cursor.HAND_CURSOR));
                label.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        frameAdesivo.dispose();
                        frameAdesivo = null;
                    }
                });
                
                frameAdesivo.add(label);
                frameAdesivo.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar imagem do adesivo: " + e.getMessage());
            }
        }
    }

    private void abrirDispositivoEmergencia() {
        JFrame frameDispositivoEmergencia = new JFrame("Dispositivo de Emergência");
        frameDispositivoEmergencia.setSize(1024, 768);
        frameDispositivoEmergencia.setLocationRelativeTo(null);
        frameDispositivoEmergencia.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameDispositivoEmergencia.setLayout(new BorderLayout());

        try {
            ImageIcon backgroundIcon = new ImageIcon("imagens/03_dispositivo_emergencia.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            
            BackgroundPanel panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Botão de seta para baixo para voltar
            JButton setaBaixo = new JButton(new ImageIcon("imagens/seta pra baixo.png"));
            setaBaixo.setBounds(475, 650, 100, 100);
            setaBaixo.setContentAreaFilled(false);
            setaBaixo.setBorderPainted(false);
            setaBaixo.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaBaixo.addActionListener(e -> {
                frameDispositivoEmergencia.dispose();
                abrirPortaAberta();
            });
            panel.add(setaBaixo);

            // Botão de seta para a esquerda para voltar
            JButton setaEsquerda = new JButton(new ImageIcon("imagens/seta esquerda.png"));
            setaEsquerda.setBounds(50, 384, 100, 100);
            setaEsquerda.setContentAreaFilled(false);
            setaEsquerda.setBorderPainted(false);
            setaEsquerda.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaEsquerda.addActionListener(e -> {
                frameDispositivoEmergencia.dispose();
                abrirPortaAberta();
            });
            panel.add(setaEsquerda);

            frameDispositivoEmergencia.setContentPane(panel);
            frameDispositivoEmergencia.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + e.getMessage());
        }
    }

    private void alternarImagemCBTC() {
        if (mostrarPrimeiraImagem) {
            botaoCBTC.setIcon(new ImageIcon("imagens/cbtcAM.jpg"));
        } else {
            botaoCBTC.setIcon(new ImageIcon("imagens/cbtc_rm.jpg"));
        }
        mostrarPrimeiraImagem = !mostrarPrimeiraImagem;
    }
} 