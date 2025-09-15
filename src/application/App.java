package application;

import java.util.Scanner;
import service.ArvoreFamilia;
import util.ArquivoUtils;

public class App {
    public static void main(String[] args) {
        ArquivoUtils arquivoUtils = new ArquivoUtils();
        ArvoreFamilia arvore = arquivoUtils.leArquivo();


        arvore.imprimirArvore(arvore.getRaiz(), 0);

        Scanner sc = new Scanner(System.in);
        String input = "";

        while (!input.equals("exit")) {
            input = sc.next();
        }

        sc.close();
    }
}
