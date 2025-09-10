package application;

import service.ArvoreFamilia;
import util.ArquivoUtils;
import util.FileUtils;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
<<<<<<< HEAD
        FileUtils fileUtils = new FileUtils();
        fileUtils.readFile();

        Scanner sc = new Scanner(System.in);
        String input = "";

        while (!input.equals("exit")) {
            input = sc.next();
        }
=======
        ArquivoUtils arquivoUtils = new ArquivoUtils();
        ArvoreFamilia arvore = arquivoUtils.leArquivo();

>>>>>>> 9551fb4 (metodos da arvore)


    }
}
