package application;

import service.ArvoreFamilia;
import util.ArquivoUtils;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArquivoUtils arquivoUtils = new ArquivoUtils();
        ArvoreFamilia arvore = arquivoUtils.leArquivo();

        Scanner sc = new Scanner(System.in);
        String input = "";

        while (!input.equals("exit")) {
            input = sc.next();
        }

    }
}
