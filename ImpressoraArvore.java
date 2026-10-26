import java.util.Arrays;

/**
 * Desenha uma árvore Morse no console, de cima para baixo, com a raiz no topo.
 */
public class ImpressoraArvore {

    private static final char SIMBOLO_NO_VAZIO = '*';

    public void imprimir(NoMorse raiz) {
        int altura = calcularAltura(raiz);
        int largura = (int) Math.pow(2, altura);
        int linhas = altura * 2 - 1;

        char[][] tela = new char[linhas][largura];
        for (char[] linha : tela) {
            Arrays.fill(linha, ' ');
        }

        int colunaRaiz = largura / 2;
        int deslocamentoInicial = largura / 4;
        desenharNo(tela, raiz, 0, colunaRaiz, deslocamentoInicial);

        System.out.println("Legenda: '.' = esquerda (/), '-' = direita (\\), '*' = nó sem letra");
        System.out.println();
        for (char[] linha : tela) {
            System.out.println(new String(linha).stripTrailing());
        }
    }

    /**
     * Desenha um nó e seus filhos na matriz "tela".
     * Os filhos ficam a "deslocamento" colunas do pai, e esse valor cai pela metade a cada nível.
     */
    private void desenharNo(char[][] tela, NoMorse no, int nivel, int coluna, int deslocamento) {
        int linhaNo = nivel * 2;
        int linhaLigacao = linhaNo + 1;

        tela[linhaNo][coluna] = no.temCaractere() ? no.getCaractere() : SIMBOLO_NO_VAZIO;

        if (no.getEsquerda() != null) {
            int colunaFilho = coluna - deslocamento;
            for (int c = colunaFilho + 1; c < coluna; c++) {
                tela[linhaNo][c] = '_';
            }
            tela[linhaLigacao][colunaFilho] = '/';
            desenharNo(tela, no.getEsquerda(), nivel + 1, colunaFilho, deslocamento / 2);
        }

        if (no.getDireita() != null) {
            int colunaFilho = coluna + deslocamento;
            for (int c = coluna + 1; c < colunaFilho; c++) {
                tela[linhaNo][c] = '_';
            }
            tela[linhaLigacao][colunaFilho] = '\\';
            desenharNo(tela, no.getDireita(), nivel + 1, colunaFilho, deslocamento / 2);
        }
    }

    /**
     * Altura = número de níveis da árvore (a raiz sozinha tem altura 1).
     */
    private int calcularAltura(NoMorse no) {
        if (no == null) {
            return 0;
        }
        return 1 + Math.max(calcularAltura(no.getEsquerda()), calcularAltura(no.getDireita()));
    }
}
