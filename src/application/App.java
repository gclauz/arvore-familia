package application;

import entities.Pessoa;
import java.util.Scanner;
import service.ArvoreFamilia;
import util.ArquivoUtils;

public class App {
    public static void main(String[] args) {
        ArquivoUtils leitorDeArquivo = new ArquivoUtils();
        ArvoreFamilia arvore = leitorDeArquivo.leArquivo();

        Pessoa raiz = arvore.getRaiz();
        arvore.imprimirArvore(raiz, "");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String linha = sc.nextLine().trim();

            if (linha.equalsIgnoreCase("exit")) {
                break;
            }

            if (linha.isEmpty()) {
                continue;
            }

            String[] nomes = linha.split(" ");
            if (nomes.length != 2) {
                System.out.println("Entrada inválida, digite: <nome1> <nome2>");
                continue;
            }

            String nome1 = nomes[0];
            String nome2 = nomes[1];

            String resultado = arvore.relacao(nome1, nome2);
            System.out.println(resultado);
        }

        sc.close();
    }
}
