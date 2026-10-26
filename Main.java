public class Main {

    public static void main(String[] args) {
        ArvoreMorse arvore = new ArvoreMorse();
        arvore.carregarAlfabeto();

        ImpressoraArvore impressora = new ImpressoraArvore();
        impressora.imprimir(arvore.getRaiz());
    }
}
