package gui;

import java.awt.*;
import java.io.File;
import java.net.URL;
import javax.swing.*;

public class PortaAberta extends JFrame {
    private BackgroundPanel panel;
    private static final int LARGURA_JANELA = 1024;
    private static final int ALTURA_JANELA = 768;

    public PortaAberta() {
        setTitle("Porta Aberta");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(LARGURA_JANELA, ALTURA_JANELA);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        try {
            // Carregar imagem de fundo
            ImageIcon backgroundIcon = new ImageIcon("imagens/02_porta_aberta.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            
            panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Seta para baixo para ir à soleira
            File setaBaixoFile = new File("imagens/seta pra baixo.png");
            URL setaBaixoURL = setaBaixoFile.toURI().toURL();
            System.out.println("URL da seta para baixo: " + setaBaixoURL);
            
            ImageIcon setaBaixoIcon = new ImageIcon(setaBaixoURL);
            if (setaBaixoIcon.getIconWidth() <= 0) {
                System.out.println("Erro: Imagem da seta para baixo não foi carregada");
                throw new Exception("Não foi possível carregar a imagem da seta para baixo");
            }
            
            JLabel setaBaixo = new JLabel(setaBaixoIcon);
            setaBaixo.setBounds(437, 500, 150, 150);
            setaBaixo.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaBaixo.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    dispose();
                    new Soleira().setVisible(true);
                }
            });
            panel.add(setaBaixo);

            // Botão de seta para cima para ir ao dispositivo de emergência
            ImageIcon setaCimaIcon = new ImageIcon("imagens/seta pra cima.png");
            JButton setaCima = new JButton(setaCimaIcon);
            setaCima.setBounds(462, 150, 100, 100);
            setaCima.setContentAreaFilled(false);
            setaCima.setBorderPainted(false);
            setaCima.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaCima.addActionListener(e -> {
                dispose();
                new DispositivoEmergencia().setVisible(true);
            });
            panel.add(setaCima);

            // Botão de seta para a esquerda para voltar à Visão Geral
            JButton setaEsquerda = new JButton(new ImageIcon("imagens/seta esquerda.png"));
            setaEsquerda.setBounds(50, 384, 100, 100);
            setaEsquerda.setContentAreaFilled(false);
            setaEsquerda.setBorderPainted(false);
            setaEsquerda.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaEsquerda.addActionListener(e -> {
                dispose();
                new VisaoGeral().setVisible(true);
            });
            panel.add(setaEsquerda);

            // Botão de seta para a direita para ir ao Painel Fechado
            JButton setaDireita = new JButton(new ImageIcon("imagens/seta direita.png"));
            setaDireita.setBounds(900, 384, 100, 100);
            setaDireita.setContentAreaFilled(false);
            setaDireita.setBorderPainted(false);
            setaDireita.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaDireita.addActionListener(e -> {
                dispose();
                abrirPainelFechado();
            });
            panel.add(setaDireita);

            // Área clicável para a porta da esquerda
            JButton portaEsquerdaBtn = new JButton();
            portaEsquerdaBtn.setBounds(150, 100, 300, 600);
            portaEsquerdaBtn.setContentAreaFilled(false);
            portaEsquerdaBtn.setBorderPainted(false);
            portaEsquerdaBtn.setOpaque(false);
            portaEsquerdaBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            portaEsquerdaBtn.addActionListener(e -> {
                dispose();
                new VisaoGeral().setVisible(true);
            });
            panel.add(portaEsquerdaBtn);

            // Ícone para abrir a porta fechada
            ImageIcon portaFechadaIcon = new ImageIcon("imagens/simbolo fechar porta.jpg");
            Image img = portaFechadaIcon.getImage();
            Image imgResized = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            JButton portaFechadaBtn = new JButton(new ImageIcon(imgResized));
            portaFechadaBtn.setBounds(700, 384, 50, 50);
            portaFechadaBtn.setContentAreaFilled(false);
            portaFechadaBtn.setBorderPainted(false);
            portaFechadaBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            portaFechadaBtn.addActionListener(e -> abrirPortaFechada());
            panel.add(portaFechadaBtn);

            setContentPane(panel);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erro ao carregar componentes: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirPainelFechado() {
        JFrame framePainelFechado = new JFrame("Painel Fechado");
        framePainelFechado.setSize(LARGURA_JANELA, ALTURA_JANELA);
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
            botaoPainel.setBounds(0, 0, LARGURA_JANELA, ALTURA_JANELA);
            botaoPainel.setContentAreaFilled(false);
            botaoPainel.setBorderPainted(false);
            botaoPainel.setFocusPainted(false);
            botaoPainel.setOpaque(false);
            botaoPainel.addActionListener(e -> {
                framePainelFechado.dispose();
                abrirPainelAberto();
            });
            panel.add(botaoPainel);

            framePainelFechado.setContentPane(panel);
            framePainelFechado.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + e.getMessage());
        }
    }

    private void abrirPainelAberto() {
        JFrame framePainelAberto = new JFrame("Painel Aberto");
        framePainelAberto.setSize(LARGURA_JANELA, ALTURA_JANELA);
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
            botaoPainel.setBounds(0, 0, LARGURA_JANELA, ALTURA_JANELA);
            botaoPainel.setContentAreaFilled(false);
            botaoPainel.setBorderPainted(false);
            botaoPainel.setFocusPainted(false);
            botaoPainel.setOpaque(false);
            botaoPainel.addActionListener(e -> {
                framePainelAberto.dispose();
                criarTelaPainelIsolado();
            });
            panel.add(botaoPainel);

            framePainelAberto.setContentPane(panel);
            framePainelAberto.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + e.getMessage());
        }
    }

    private void criarTelaPainelIsolado() {
        JFrame framePainelIsolado = new JFrame("Painel Isolado");
        framePainelIsolado.setSize(LARGURA_JANELA, ALTURA_JANELA);
        framePainelIsolado.setLocationRelativeTo(null);
        framePainelIsolado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        try {
            ImageIcon backgroundIcon = new ImageIcon("imagens/19 - Painel externo porta 3 isolada.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            
            BackgroundPanel panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Botão de seta para a esquerda para voltar para a tela Porta Aberta
            JButton setaEsquerda = new JButton(new ImageIcon("imagens/seta esquerda.png"));
            setaEsquerda.setBounds(50, 384, 100, 100);
            setaEsquerda.setContentAreaFilled(false);
            setaEsquerda.setBorderPainted(false);
            setaEsquerda.setCursor(new Cursor(Cursor.HAND_CURSOR));
            setaEsquerda.addActionListener(e -> {
                framePainelIsolado.dispose();
                new PortaAberta().setVisible(true);
            });
            panel.add(setaEsquerda);

            framePainelIsolado.setContentPane(panel);
            framePainelIsolado.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + e.getMessage());
        }
    }

    private void abrirPortaFechada() {
        JFrame framePortaFechada = new JFrame("Porta Fechada");
        framePortaFechada.setSize(LARGURA_JANELA, ALTURA_JANELA);
        framePortaFechada.setLocationRelativeTo(null);
        framePortaFechada.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        try {
            ImageIcon backgroundIcon = new ImageIcon("imagens/20 - Porta fechada.jpg");
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
                framePortaFechada.dispose();
                new PortaAberta().setVisible(true);
            });
            panel.add(setaEsquerda);

            framePortaFechada.setContentPane(panel);
            framePortaFechada.setVisible(true);
            dispose(); // Fecha a janela atual
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PortaAberta().setVisible(true);
        });
    }
} 