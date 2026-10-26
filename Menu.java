import java.util.Scanner;

public class Menu {

    private final ArvoreMorse arvore;
    private final ImpressoraArvore impressora;
    private final SomMorse som;
    private final Scanner entrada;

    public Menu(ArvoreMorse arvore, ImpressoraArvore impressora, SomMorse som) {
        this.arvore = arvore;
        this.impressora = impressora;
        this.som = som;
        this.entrada = new Scanner(System.in);
    }

    public void executar() {
        int opcao;
        do {
            exibirOpcoes();
            opcao = lerOpcao();

            switch (opcao) {
                case 1 -> decodificar();
                case 2 -> codificar();
                case 3 -> impressora.imprimir(arvore.getRaiz());
                case 4 -> tocarSom();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        entrada.close();
    }

    private void exibirOpcoes() {
        System.out.println();
        System.out.println("CÓDIGO MORSE");
        System.out.println("1 - Decodificar (Morse -> texto)");
        System.out.println("2 - Codificar (texto -> Morse)");
        System.out.println("3 - Imprimir árvore");
        System.out.println("4 - Tocar som de um texto");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private int lerOpcao() {
        String linha = entrada.nextLine().trim();
        try {
            return Integer.parseInt(linha);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void decodificar() {
        System.out.println("Digite o código (letras separadas por espaço, palavras por ' / '):");
        String mensagemMorse = entrada.nextLine();
        System.out.println("Texto: " + arvore.decodificarMensagem(mensagemMorse));
    }

    private void codificar() {
        System.out.println("Digite o texto:");
        String texto = entrada.nextLine();
        System.out.println("Morse: " + arvore.codificarMensagem(texto));
    }

    private void tocarSom() {
        System.out.println("Digite o texto:");
        String mensagemMorse = arvore.codificarMensagem(entrada.nextLine());
        System.out.println("Tocando: " + mensagemMorse);

        if (!som.tocar(mensagemMorse)) {
            System.out.println("Não foi possível acessar a saída de áudio deste computador.");
        }
    }
}
