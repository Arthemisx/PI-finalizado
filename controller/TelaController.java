package controller;

import gui.*;
import util.SomUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class TelaController {
    public static void abrirTela(String nomeTela, JFrame parent) {
        try {
            switch (nomeTela) {
                case "Alavanca":
                    new TelaAlavanca().setVisible(true);
                    break;
                case "Tela Auxiliar 1":
                    abrirTelaAuxiliar1(parent);
                    break;
                default:
                    JOptionPane.showMessageDialog(parent, "Tela não implementada: " + nomeTela);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Erro ao abrir tela: " + e.getMessage());
        }
    }

    public static void abrirTelaAuxiliar1(JFrame parent) {
        JFrame frame = new JFrame("Tela Auxiliar 1");
        frame.setSize(1024, 768);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        try {
            ImageIcon backgroundIcon = new ImageIcon("imagens/05 - Módulo de Comunicação - tela de início.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            
            BackgroundPanel panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Botão invisível para frame_60
            JButton botaoFrame60 = new JButton();
            botaoFrame60.setBounds(765, 412, 150, 150);
            botaoFrame60.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            botaoFrame60.setOpaque(false);
            botaoFrame60.setContentAreaFilled(false);
            botaoFrame60.setBorderPainted(false);
            botaoFrame60.addActionListener(e -> {
                System.out.println("Botão Frame 60 pressionado");
                frame.dispose();
                abrirFrame60(parent);
            });
            panel.add(botaoFrame60);

            frame.setContentPane(panel);
            frame.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Erro ao carregar imagem: " + e.getMessage());
        }
    }

    public static void abrirFrame60(JFrame parent) {
        try {
            System.out.println("Abrindo Frame 60...");
            JFrame frame = new JFrame("Frame 60");
            frame.setSize(1024, 768);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            ImageIcon backgroundIcon = new ImageIcon("imagens/frame_60.jpg");
            Image backgroundImage = backgroundIcon.getImage();
            
            BackgroundPanel panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            // Botão de voltar no canto inferior esquerdo
            JButton botaoVoltar = new JButton();
            botaoVoltar.setBounds(300, 550, 65, 130);
            botaoVoltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            botaoVoltar.setOpaque(false);
            botaoVoltar.setContentAreaFilled(false);
            botaoVoltar.setBorderPainted(false);
            botaoVoltar.setFocusPainted(false);
            botaoVoltar.addActionListener(e -> {
                try {
                    System.out.println("Botão voltar pressionado - retornando ao painel interativo");
                    frame.dispose();
                    if (parent != null) {
                        parent.dispose();
                    }
                    PainelInterativo painelInterativo = new PainelInterativo();
                    painelInterativo.setVisible(true);
                } catch (Exception ex) {
                    System.err.println("Erro ao voltar para o painel interativo: " + ex.getMessage());
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, 
                        "Erro ao voltar para o painel interativo: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                }
            });
            panel.add(botaoVoltar);

            // Botão de som no canto inferior direito
            JButton botaoSom = new JButton();
            botaoSom.setBounds(654, 628, 100, 50);
            botaoSom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            botaoSom.setOpaque(false);
            botaoSom.setContentAreaFilled(false);
            botaoSom.setBorderPainted(false);
            botaoSom.setFocusPainted(false);
            botaoSom.addActionListener(e -> {
                System.out.println("Botão de som pressionado no Frame 60");
                File arquivoSom = new File("sons/gongo.wav");
                System.out.println("Verificando arquivo: " + arquivoSom.getAbsolutePath());
                if (arquivoSom.exists()) {
                    System.out.println("Arquivo encontrado, tamanho: " + arquivoSom.length() + " bytes");
                    SomUtils.tocarSom("sons/gongo.wav");
                } else {
                    System.err.println("ERRO: Arquivo gongo.wav não encontrado em: " + arquivoSom.getAbsolutePath());
                    JOptionPane.showMessageDialog(frame, 
                        "Erro: Arquivo de som não encontrado em: " + arquivoSom.getAbsolutePath(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                }
            });
            panel.add(botaoSom);

            frame.setContentPane(panel);
            frame.setVisible(true);

            // Adiciona listener para parar os sons quando a janela for fechada
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    SomUtils.pararTodosSons();
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Erro ao carregar imagem: " + e.getMessage());
        }
    }
} 