package util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SomUtils {
    private static final List<Clip> clipsAtivos = new ArrayList<>();

    public static void tocarSom(String caminhoArquivo) {
        File arquivoSom = new File(caminhoArquivo);
        
        System.out.println("\n=== Iniciando reprodução de som ===");
        System.out.println("Caminho do arquivo: " + arquivoSom.getAbsolutePath());
        System.out.println("Arquivo existe? " + arquivoSom.exists());
        if (arquivoSom.exists()) {
            System.out.println("Tamanho do arquivo: " + arquivoSom.length() + " bytes");
        }
        
        // Listar dispositivos de áudio disponíveis
        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        System.out.println("\nDispositivos de áudio disponíveis:");
        for (Mixer.Info info : mixerInfos) {
            System.out.println("- " + info.getName() + " - " + info.getDescription());
        }
        
        // Verificar se o arquivo existe
        if (!arquivoSom.exists()) {
            System.err.println("Erro: Arquivo de som não encontrado: " + arquivoSom.getAbsolutePath());
            System.out.println("Tentando tocar som de teste...");
            tocarSomDeTeste();
            return;
        }

        try {
            System.out.println("\nTentando abrir o arquivo de áudio...");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(arquivoSom);
            
            AudioFormat format = audioIn.getFormat();
            System.out.println("Formato do áudio:");
            System.out.println("- Taxa de amostragem: " + format.getSampleRate() + " Hz");
            System.out.println("- Tamanho da amostra: " + format.getSampleSizeInBits() + " bits");
            System.out.println("- Canais: " + format.getChannels());
            System.out.println("- Codificação: " + format.getEncoding());
            System.out.println("- Frame Size: " + format.getFrameSize() + " bytes");
            System.out.println("- Frame Rate: " + format.getFrameRate() + " frames/segundo");
            System.out.println("- Big Endian: " + format.isBigEndian());
            
            // Obter a linha principal de áudio
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("\nLinha de áudio não suportada, tentando converter o formato...");
                AudioFormat targetFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    44100,
                    16,
                    format.getChannels(),
                    format.getChannels() * 2,
                    44100,
                    false
                );
                System.out.println("Convertendo para formato:");
                System.out.println("- Taxa de amostragem: 44100 Hz");
                System.out.println("- Tamanho da amostra: 16 bits");
                System.out.println("- Canais: " + format.getChannels());
                audioIn = AudioSystem.getAudioInputStream(targetFormat, audioIn);
                info = new DataLine.Info(Clip.class, targetFormat);
            }

            System.out.println("\nAbrindo clip de áudio...");
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            
            // Adicionar o clip à lista de clips ativos
            clipsAtivos.add(clip);
            
            // Configurar volume máximo
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(gainControl.getMaximum());
                System.out.println("Volume configurado para o máximo: " + gainControl.getValue() + "dB");
            } else {
                System.out.println("Controle de volume não suportado para este clip");
            }
            
            System.out.println("Áudio aberto com sucesso, duração: " + clip.getMicrosecondLength()/1000000.0 + " segundos");
            
            // Adicionar um listener para saber quando o som termina
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    System.out.println("Som terminou de tocar");
                    clipsAtivos.remove(clip);
                    clip.close();
                } else if (event.getType() == LineEvent.Type.START) {
                    System.out.println("Som começou a tocar");
                }
            });
            
            clip.start();
            System.out.println("Som iniciado com sucesso!");
            
        } catch (UnsupportedAudioFileException e) {
            System.err.println("\nERRO: Formato de áudio não suportado");
            System.err.println("Detalhes do erro: " + e.getMessage());
            System.err.println("Stack trace:");
            e.printStackTrace();
            System.out.println("\nTentando tocar som de teste como fallback...");
            tocarSomDeTeste();
        } catch (IOException e) {
            System.err.println("\nERRO: Falha ao ler arquivo de som");
            System.err.println("Caminho do arquivo: " + arquivoSom.getAbsolutePath());
            System.err.println("Detalhes do erro: " + e.getMessage());
            System.err.println("Stack trace:");
            e.printStackTrace();
            System.out.println("\nTentando tocar som de teste como fallback...");
            tocarSomDeTeste();
        } catch (LineUnavailableException e) {
            System.err.println("\nERRO: Sistema de áudio não disponível");
            System.err.println("Detalhes do erro: " + e.getMessage());
            System.err.println("Stack trace:");
            e.printStackTrace();
        }
    }
    
    public static void pararTodosSons() {
        for (Clip clip : new ArrayList<>(clipsAtivos)) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.close();
            clipsAtivos.remove(clip);
        }
    }
    
    // Método para tocar um som de teste simples
    private static void tocarSomDeTeste() {
        try {
            // Gerar um tom de teste mais alto
            AudioFormat format = new AudioFormat(44100, 16, 1, true, true);
            byte[] data = new byte[44100];
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte)(Math.sin(i / 10.0) * 127); // Volume máximo
            }
            
            AudioInputStream ais = new AudioInputStream(
                new java.io.ByteArrayInputStream(data),
                format,
                data.length
            );
            
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            
            // Adicionar o clip de teste à lista de clips ativos
            clipsAtivos.add(clip);
            
            // Configurar volume máximo para o som de teste
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(gainControl.getMaximum());
            
            System.out.println("Tocando som de teste...");
            clip.start();
            Thread.sleep(500); // Tocar por meio segundo
            
            // Remover o clip de teste da lista quando terminar
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clipsAtivos.remove(clip);
                    clip.close();
                }
            });
        } catch (Exception e) {
            System.err.println("Erro ao tocar som de teste: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 