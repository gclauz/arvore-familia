package application;

import java.util.Scanner;

import entities.Pessoa;
import service.ArvoreFamilia;
import util.ArquivoUtils;

public class App {
    public static void main(String[] args) {
        ArquivoUtils leitorDeArquivo = new ArquivoUtils();

        ArvoreFamilia arvore = leitorDeArquivo.leArquivo();

        Pessoa raiz = arvore.getRaiz();


        arvore.imprimirArvore(raiz, "");


        // Scanner sc = new Scanner(System.in);
        // String input = "";

        // while (!input.equals("exit")) {
        //     input = sc.next();
        // }

        // sc.close();
    }
}
