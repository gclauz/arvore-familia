package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public void readFile() {
        String arquivo = "data/input.txt";
        List<String> sobrenomes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(" ");
                for (String nome : partes) {
                    String sobrenome = nome.substring(nome.lastIndexOf('.') + 1);
                    if (!sobrenomes.contains(sobrenome)) {
                        sobrenomes.add(sobrenome);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        for (String s : sobrenomes) {
            System.out.println("Sobrenome: " + s);
        }
    }
}
