/**
 * Representa um nó da árvore Morse.
 * Ponto (.) leva ao filho da esquerda; traço (-) leva ao filho da direita.
 */
public class NoMorse {

    private char caractere;
    private NoMorse esquerda;
    private NoMorse direita;

    // Cria um nó vazio (sem letra), usado para a raiz e para caminhos intermediários
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
