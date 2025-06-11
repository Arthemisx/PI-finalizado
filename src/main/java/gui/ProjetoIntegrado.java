package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalTime;
import javax.imageio.ImageIO;
import javax.swing.*;
import util.UIUtils;

public class ProjetoIntegrado extends JFrame {
    public static Cursor customCursor;

    public ProjetoIntegrado() {
        configurarJanela();
        initComponents();
    }

    private void configurarJanela() {
        setTitle("Bem-vindo!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1800, 735);
        setLocationRelativeTo(null);
        setResizable(false);
        setCursor(customCursor);
    }

    private void initComponents() {
        Image backgroundImage = UIUtils.loadImage("imagens/tela de entrada.png");
        BackgroundPanel panel = new BackgroundPanel(backgroundImage);
        panel.setLayout(null);

        JButton iniciarButton = UIUtils.createInvisibleButton(
            new Rectangle(400, 445, 570, 70),
            evt -> abrirTelaLogin()
        );
        panel.add(iniciarButton);
        add(panel);
    }

    private void abrirTelaLogin() {
        dispose();
        new TelaLogin().setVisible(true);
    }
}

class TelaLogin extends JFrame {
    private JTextField nomeUsuarioField;
    private JTextField nomeAdministradorField;
    private JPasswordField passwordField;

    public TelaLogin() {
        configurarJanela();
        initComponents();
    }

    private void configurarJanela() {
        setTitle("Metrô day - Cadastro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1800, 730);
        setLocationRelativeTo(null);
        setResizable(false);
        setCursor(ProjetoIntegrado.customCursor);
    }

    private void initComponents() {
        Image backgroundImage = UIUtils.loadImage("imagens/tela de cadastro.png");
        BackgroundPanel mainPanel = new BackgroundPanel(backgroundImage);
        mainPanel.setLayout(null);

        adicionarCampoNomeUsuario(mainPanel);
        adicionarCampoEmailAdministrador(mainPanel);
        adicionarCampoSenha(mainPanel);
        adicionarBotaoEntrar(mainPanel);

        add(mainPanel);
    }

    private void adicionarCampoNomeUsuario(JPanel panel) {
        nomeUsuarioField = new JTextField(20);
        nomeUsuarioField.setBounds(600, 250, 300, 40);
        estilizarCampoTexto(nomeUsuarioField, "Digite seu nome");
        panel.add(nomeUsuarioField);
    }

    private void adicionarCampoEmailAdministrador(JPanel panel) {
        nomeAdministradorField = new JTextField(20);
        nomeAdministradorField.setBounds(600, 350, 300, 40);
        estilizarCampoTexto(nomeAdministradorField, "Digite seu e-mail");
        panel.add(nomeAdministradorField);
    }

    private void adicionarCampoSenha(JPanel panel) {
        passwordField = new JPasswordField(20);
        passwordField.setBounds(600, 450, 300, 40);
        estilizarCampoTexto(passwordField, "Digite sua senha");
        panel.add(passwordField);
    }

    private void estilizarCampoTexto(JTextField campo, String placeholder) {
        campo.setOpaque(false);
        campo.setBackground(new Color(255, 255, 255, 30));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 255, 255, 150)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        campo.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        
        campo.setText(placeholder);
        campo.setForeground(new Color(200, 200, 200, 180));
        
        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (campo.getText().equals(placeholder)) {
                    campo.setText("");
                    campo.setForeground(Color.WHITE);
                    if (campo instanceof JPasswordField) {
                        ((JPasswordField)campo).setEchoChar('•');
                    }
                }
                campo.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (campo.getText().isEmpty()) {
                    campo.setText(placeholder);
                    campo.setForeground(new Color(200, 200, 200, 180));
                    if (campo instanceof JPasswordField) {
                        ((JPasswordField)campo).setEchoChar((char)0);
                    }
                }
                campo.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 255, 255, 150)),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }
        });

        // Adiciona um mouse listener para mudar o cursor
        campo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                campo.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            }
        });
    }

    private void adicionarBotaoEntrar(JPanel panel) {
        JButton entrarButton = new JButton();
        entrarButton.setBounds(500, 550, 300, 50);
        entrarButton.setOpaque(false);
        entrarButton.setContentAreaFilled(false);
        entrarButton.setBorderPainted(false);
        entrarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        entrarButton.addActionListener(e -> {
            String nomeUsuario = nomeUsuarioField.getText();
            String email = nomeAdministradorField.getText();
            String senha = new String(passwordField.getPassword());

            // Verificar se os campos não estão com o texto placeholder
            if (nomeUsuario.equals("Digite seu nome") || 
                email.equals("Digite seu e-mail") || 
                senha.equals("Digite sua senha") ||
                nomeUsuario.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Por favor, preencha todos os campos!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            dispose();
            new TelaDeOpcoes().setVisible(true);
        });

        panel.add(entrarButton);
    }
}

class TelaDeOpcoes extends JFrame {
    public TelaDeOpcoes() {
        configurarJanela();
        initComponents();
    }

    private void configurarJanela() {
        setTitle("Opções");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1800, 730);
        setLocationRelativeTo(null);
        setResizable(false);
        setCursor(ProjetoIntegrado.customCursor);
    }

    private void initComponents() {
        Image backgroundImage = UIUtils.loadImage("imagens/tela de opções.jpg");
        BackgroundPanel panel = new BackgroundPanel(backgroundImage);
        panel.setLayout(null);

        JButton iniciarButton = UIUtils.createInvisibleButton(
            new Rectangle(400, 445, 570, 70),
            evt -> abrirPainelInterativo()
        );
        panel.add(iniciarButton);

        JButton regrasButton = UIUtils.createInvisibleButton(
            new Rectangle(400, 545, 570, 70),
            evt -> abrirTelaDeRegras()
        );
        panel.add(regrasButton);

        add(panel);
    }

    private void abrirPainelInterativo() {
        dispose();
        new PainelInterativo().setVisible(true);
    }

    private void abrirTelaDeRegras() {
        dispose();
        new TelaDeRegras().setVisible(true);
    }
}

class TelaDeRegras extends JFrame {
    public TelaDeRegras() {
        configurarJanela();
        initComponents();
    }

    private void configurarJanela() {
        setTitle("Regras");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1800, 730);
        setLocationRelativeTo(null);
        setResizable(false);
        setCursor(ProjetoIntegrado.customCursor);
    }

    private void initComponents() {
        Image backgroundImage = UIUtils.loadImage("imagens/tela de regras.jpg");
        BackgroundPanel panel = new BackgroundPanel(backgroundImage);
        panel.setLayout(null);

        JButton voltarButton = UIUtils.createInvisibleButton(
            new Rectangle(400, 545, 570, 70),
            evt -> abrirPainelInterativo()
        );
        panel.add(voltarButton);

        add(panel);
    }

    private void abrirPainelInterativo() {
        dispose();
        new PainelInterativo().setVisible(true);
    }
} 