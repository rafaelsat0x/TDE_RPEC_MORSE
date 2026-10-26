import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SomMorse {

    private static final float TAXA_AMOSTRAGEM = 44100f;
    private static final int FREQUENCIA_HZ = 700;
    private static final int DURACAO_PONTO_MS = 100;
    private static final int DURACAO_TRACO_MS = DURACAO_PONTO_MS * 3;
    private static final double VOLUME = 0.5;

    public boolean tocar(String mensagemMorse) {
        AudioFormat formato = new AudioFormat(TAXA_AMOSTRAGEM, 8, 1, true, false);

        try (SourceDataLine saidaDeAudio = AudioSystem.getSourceDataLine(formato)) {
            saidaDeAudio.open(formato);
            saidaDeAudio.start();

            for (char simbolo : mensagemMorse.toCharArray()) {
                switch (simbolo) {
                    case '.' -> {
                        tocarTom(saidaDeAudio, DURACAO_PONTO_MS);
                        tocarSilencio(saidaDeAudio, DURACAO_PONTO_MS);
                    }
                    case '-' -> {
                        tocarTom(saidaDeAudio, DURACAO_TRACO_MS);
                        tocarSilencio(saidaDeAudio, DURACAO_PONTO_MS);
                    }
                    case ' ' -> tocarSilencio(saidaDeAudio, DURACAO_PONTO_MS * 2);
                    case '/' -> tocarSilencio(saidaDeAudio, DURACAO_PONTO_MS * 2);
                    default -> { }
                }
            }

            saidaDeAudio.drain();
            return true;
        } catch (LineUnavailableException | IllegalArgumentException e) {
            return false;
        }
    }
    private void tocarTom(SourceDataLine saidaDeAudio, int duracaoMs) {
        int totalAmostras = calcularTotalAmostras(duracaoMs);
        byte[] amostras = new byte[totalAmostras];

        for (int i = 0; i < totalAmostras; i++) {
            double angulo = 2.0 * Math.PI * FREQUENCIA_HZ * i / TAXA_AMOSTRAGEM;
            amostras[i] = (byte) (Math.sin(angulo) * 127 * VOLUME);
        }

        saidaDeAudio.write(amostras, 0, amostras.length);
    }

    private void tocarSilencio(SourceDataLine saidaDeAudio, int duracaoMs) {
        byte[] silencio = new byte[calcularTotalAmostras(duracaoMs)];
        saidaDeAudio.write(silencio, 0, silencio.length);
    }

    private int calcularTotalAmostras(int duracaoMs) {
        return (int) (TAXA_AMOSTRAGEM * duracaoMs / 1000);
    }
}
