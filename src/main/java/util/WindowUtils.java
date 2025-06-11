package util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WindowUtils {
    public static void configurarJanelaBasica(JFrame frame, String titulo, int largura, int altura) {
        frame.setTitle(titulo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(largura, altura);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public static JButton criarBotaoSeta(String caminhoImagem, Rectangle bounds, ActionListener action) {
        JButton botao = new JButton(new ImageIcon(caminhoImagem));
        botao.setBounds(bounds);
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.addActionListener(action);
        return botao;
    }

    public static JButton criarBotaoInvisivel(Rectangle bounds, ActionListener action) {
        JButton botao = new JButton();
        botao.setBounds(bounds);
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
        botao.setOpaque(false);
        botao.addActionListener(action);
        return botao;
    }
} 