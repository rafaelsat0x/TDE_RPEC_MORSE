public class NoMorse {

    private char caractere;
    private NoMorse esquerda;
    private NoMorse direita;

    public NoMorse() {
        this.caractere = ' ';
    }

    public char getCaractere() {
        return caractere;
    }

    public void setCaractere(char caractere) {
        this.caractere = caractere;
    }

    public NoMorse getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoMorse esquerda) {
        this.esquerda = esquerda;
    }

    public NoMorse getDireita() {
        return direita;
    }

    public void setDireita(NoMorse direita) {
        this.direita = direita;
    }

    public boolean temCaractere() {
        return caractere != ' ';
    }
}
