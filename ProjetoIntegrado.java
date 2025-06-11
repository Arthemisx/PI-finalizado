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
import gui.*;

public class ProjetoIntegrado extends JFrame {
    public static Cursor customCursor;

    public static void main(String[] args) {
        System.out.println("Iniciando ProjetoIntegrado...");
        SwingUtilities.invokeLater(() -> {
            try {
                // Por enquanto, vamos usar o cursor padrão já que não encontrei o ícone da mão
                customCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
            } catch (Exception e) {
                customCursor = Cursor.getDefaultCursor();
            }
            new ProjetoIntegrado().setVisible(true);
        });
    }

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
        Image backgroundImage = UIUtils.loadImage("imagens/imagem de entradaatt.jpg");
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
        Image backgroundImage = UIUtils.loadImage("imagens/imagem cadastro.jpg");
        BackgroundPanel mainPanel = new BackgroundPanel(backgroundImage);
        mainPanel.setLayout(null);

        adicionarCampoNomeUsuario(mainPanel);
        adicionarCampoEmailAdministrador(mainPanel);
        adicionarCampoSenha(mainPanel);
        adicionarBotaoEntrar(mainPanel);

        add(mainPanel);
    }

    private void adicionarCampoNomeUsuario(JPanel panel) {
        nomeUsuarioField = new JTextField();
        nomeUsuarioField.setBounds(550, 250, 500, 40);
        estilizarCampoInvisivel(nomeUsuarioField, "Digite seu nome");
        panel.add(nomeUsuarioField);
    }

    private void adicionarCampoEmailAdministrador(JPanel panel) {
        nomeAdministradorField = new JTextField();
        nomeAdministradorField.setBounds(550, 350, 500, 40);
        estilizarCampoInvisivel(nomeAdministradorField, "Digite seu e-mail");
        panel.add(nomeAdministradorField);
    }

    private void adicionarCampoSenha(JPanel panel) {
        passwordField = new JPasswordField();
        passwordField.setBounds(550, 450, 500, 40);
        estilizarCampoInvisivel(passwordField, "Digite sua senha");
        passwordField.setEchoChar((char)0);
        panel.add(passwordField);
    }

    private void estilizarCampoInvisivel(JTextField campo, String placeholder) {
        campo.setOpaque(false);
        campo.setBackground(new Color(0, 0, 0, 0));
        campo.setBorder(null);
        campo.setForeground(Color.BLACK);
        campo.setCaretColor(Color.BLACK);
        campo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        campo.setFont(new Font("Arial", Font.PLAIN, 22));
        
        campo.setText(placeholder);
        campo.setFont(new Font("Arial", Font.ITALIC, 22));
        
        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (campo.getText().equals(placeholder)) {
                    campo.setText("");
                    campo.setFont(new Font("Arial", Font.PLAIN, 22));
                    if (campo instanceof JPasswordField) {
                        ((JPasswordField)campo).setEchoChar((char)0);
                    }
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (campo.getText().isEmpty()) {
                    campo.setText(placeholder);
                    campo.setFont(new Font("Arial", Font.ITALIC, 22));
                    if (campo instanceof JPasswordField) {
                        ((JPasswordField)campo).setEchoChar((char)0);
                    }
                }
            }
        });
    }

    private void adicionarBotaoEntrar(JPanel panel) {
        JButton entrarButton = new JButton();
        entrarButton.setBounds(640, 520, 500, 70);
        entrarButton.setOpaque(false);
        entrarButton.setContentAreaFilled(false);
        entrarButton.setBorderPainted(false);
        entrarButton.setForeground(new Color(0,0,0,0));
        entrarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        entrarButton.setFont(new Font("Arial", Font.BOLD, 20));

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

        JLabel textoInfo = new JLabel("<html><center>Aqui é seu menu principal, você pode ver seu <br> progresso geral ou começar o jogo!</center></html>");
        textoInfo.setBounds(600, 255, 595, 60);
        textoInfo.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        textoInfo.setForeground(Color.BLACK);
        panel.add(textoInfo);

        JButton btnRanking = UIUtils.createInvisibleButton(
            new Rectangle(390, 341, 595, 80),
            evt -> abrirTelaDeRegras()
        );
        panel.add(btnRanking);

        JButton btnJogar = UIUtils.createInvisibleButton(
            new Rectangle(390, 442, 595, 80),
            evt -> abrirTelaDeRegras()
        );
        panel.add(btnJogar);

        add(panel);
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
        setTitle("Tela de Regras");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1800, 730);
        setLocationRelativeTo(null);
        setResizable(false);
        setCursor(ProjetoIntegrado.customCursor);
    }

    private void initComponents() {
        Image backgroundImage = UIUtils.loadImage("imagens/Tela de regras.png");
        BackgroundPanel panel = new BackgroundPanel(backgroundImage);
        panel.setLayout(null);

        JButton btnIniciar = new JButton("Iniciar");
        btnIniciar.setBounds(940, 550, 570, 62);
        btnIniciar.setOpaque(false);
        btnIniciar.setContentAreaFilled(false);
        btnIniciar.setBorderPainted(false);
        btnIniciar.setForeground(new Color(0,0,0,0));
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 20));
        btnIniciar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnIniciar.addActionListener(evt -> abrirPainelInterativo());
        panel.add(btnIniciar);
        add(panel);
    }

    private void abrirPainelInterativo() {
        dispose();
        try {
            Class<?> painelClass = Class.forName("gui.PainelInterativo");
            JFrame painel = (JFrame) painelClass.getDeclaredConstructor().newInstance();
            painel.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao abrir o painel interativo: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
} 