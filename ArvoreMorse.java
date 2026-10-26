public class ArvoreMorse {

    private NoMorse raiz;

    public ArvoreMorse() {
        this.raiz = new NoMorse();
    }

    /**
     * Insere na árvore todas as letras (A-Z) e números (0-9) da tabela Morse.
     */
    public void carregarAlfabeto() {
        for (String[] linha : TabelaMorse.CODIGOS) {
            char caractere = linha[0].charAt(0);
            String codigo = linha[1];
            inserir(caractere, codigo);
        }
    }

    public void inserir(char caractere, String codigo) {
        NoMorse atual = raiz;

        for (char simbolo : codigo.toCharArray()) {
            if (simbolo == '.') {
                if (atual.getEsquerda() == null) {
                    atual.setEsquerda(new NoMorse());
                }
                atual = atual.getEsquerda();
            } else if (simbolo == '-') {
                if (atual.getDireita() == null) {
                    atual.setDireita(new NoMorse());
                }
                atual = atual.getDireita();
            } else {
                throw new IllegalArgumentException("Símbolo inválido no código Morse: " + simbolo);
            }
        }

        atual.setCaractere(caractere);
    }

    public NoMorse getRaiz() {
        return raiz;
    }
}
