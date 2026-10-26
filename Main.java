public class Main {

    public static void main(String[] args) {
        ArvoreMorse arvore = new ArvoreMorse();
        arvore.carregarAlfabeto();

        Menu menu = new Menu(arvore, new ImpressoraArvore());
        menu.executar();
    }
}
