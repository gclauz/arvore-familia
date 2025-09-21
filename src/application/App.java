// Arthur Roldan Slikta - 10353847
// Guilherme Clauz Morlina da Silva - 10436477
// Gabriel Hideaquy Kondo - 10436238

package application;

import java.util.Scanner;
import service.ArvoreFamilia;
import util.ArquivoUtils;

public class App {
    public static void main(String[] args) {
        ArquivoUtils leitorDeArquivo = new ArquivoUtils();
        ArvoreFamilia arvore = leitorDeArquivo.leArquivo();

       arvore.imprimirArvore(arvore.getRaiz(), "");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Digite <nome1> <nome2> para consulta ou 'exit' para sair:");
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
