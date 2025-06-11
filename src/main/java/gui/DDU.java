package gui;

import util.ImagemUtils;
import util.WindowUtils;
import java.awt.*;
import javax.swing.*;

public class DDU extends JFrame {
    private JLabel imagemLabel;
    private Timer timer;
    private int etapa = 0;

    public DDU() {
        WindowUtils.configurarJanelaBasica(this, "DDU", 1024, 768);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Label para exibir as imagens
        imagemLabel = new JLabel();
        imagemLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(imagemLabel, BorderLayout.CENTER);

        // Carrega a primeira imagem
        carregarImagem("imagens/DDU- porta fechada.jpg");

        // Configura o timer para trocar as imagens
        timer = new Timer(3000, e -> {
            etapa++;
            switch (etapa) {
                case 1:
                    carregarImagem("imagens/DDU uma porta não fecha.jpg");
                    break;
                case 2:
                    carregarImagem("imagens/03 - DDU com fachada aberta.jpg");
                    timer.stop();
                    break;
            }
        });

        // Inicia o timer
        timer.start();

        setContentPane(panel);
    }

    private void carregarImagem(String caminho) {
        Image imagem = ImagemUtils.carregarImagem(caminho, this);
        if (imagem != null) {
            Image imagemRedimensionada = ImagemUtils.redimensionarImagem(imagem, 1024, 768);
            imagemLabel.setIcon(new ImageIcon(imagemRedimensionada));
        }
    }

    @Override
    public void dispose() {
        if (timer != null) {
            timer.stop();
        }
        super.dispose();
    }
} 