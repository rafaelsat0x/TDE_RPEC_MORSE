public class ArvoreMorse {

    private static final char CARACTERE_DESCONHECIDO = '?';

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

    /**
     * Decodifica o código de UM caractere (ex.: ".-" -> 'A').
     * Retorna '?' se o caminho não existir ou terminar num nó sem letra.
     */
    public char decodificarCaractere(String codigo) {
        NoMorse atual = raiz;

        for (char simbolo : codigo.toCharArray()) {
            if (simbolo == '.') {
                atual = atual.getEsquerda();
            } else if (simbolo == '-') {
                atual = atual.getDireita();
            } else {
                return CARACTERE_DESCONHECIDO;
            }

            if (atual == null) {
                return CARACTERE_DESCONHECIDO;
            }
        }

        return atual.temCaractere() ? atual.getCaractere() : CARACTERE_DESCONHECIDO;
    }

    /**
     * Decodifica uma mensagem inteira.
     * Letras são separadas por espaço e palavras por " / " (ex.: "... --- ... / .-" -> "SOS A").
     */
    public String decodificarMensagem(String mensagemMorse) {
        StringBuilder resultado = new StringBuilder();
        String[] palavras = mensagemMorse.trim().split("\\s*/\\s*");

        for (String palavra : palavras) {
            if (resultado.length() > 0) {
                resultado.append(' ');
            }
            for (String codigoLetra : palavra.trim().split("\\s+")) {
                if (!codigoLetra.isEmpty()) {
                    resultado.append(decodificarCaractere(codigoLetra));
                }
            }
        }

        return resultado.toString();
    }

    /**
     * Codifica um texto para Morse (ex.: "SOS A" -> "... --- ... / .-").
     * Caracteres que não existem na árvore viram '?'.
     */
    public String codificarMensagem(String texto) {
        StringBuilder resultado = new StringBuilder();
        String[] palavras = texto.trim().toUpperCase().split("\\s+");

        for (String palavra : palavras) {
            if (resultado.length() > 0) {
                resultado.append(" / ");
            }
            StringBuilder palavraMorse = new StringBuilder();
            for (char caractere : palavra.toCharArray()) {
                String codigo = buscarCodigo(raiz, caractere, "");
                if (palavraMorse.length() > 0) {
                    palavraMorse.append(' ');
                }
                palavraMorse.append(codigo != null ? codigo : String.valueOf(CARACTERE_DESCONHECIDO));
            }
            resultado.append(palavraMorse);
        }

        return resultado.toString();
    }

    /**
     * Procura o caractere na árvore e devolve o caminho até ele (o próprio código Morse).
     * Cada passo à esquerda acrescenta "." ao caminho e cada passo à direita acrescenta "-".
     */
    private String buscarCodigo(NoMorse no, char caractere, String caminho) {
        if (no == null) {
            return null;
        }
        if (no.temCaractere() && no.getCaractere() == caractere) {
            return caminho;
        }

        String codigoEsquerda = buscarCodigo(no.getEsquerda(), caractere, caminho + ".");
        if (codigoEsquerda != null) {
            return codigoEsquerda;
        }
        return buscarCodigo(no.getDireita(), caractere, caminho + "-");
    }

    public NoMorse getRaiz() {
        return raiz;
    }
}
