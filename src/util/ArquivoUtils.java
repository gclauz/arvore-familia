package util;

import service.ArvoreFamilia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ArquivoUtils {

    private ArvoreFamilia arvore;

    public ArquivoUtils() {
        this.arvore = new ArvoreFamilia();
    }

    public ArvoreFamilia  leArquivo() {
        String arquivo = "data/input.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(" ");
                String nomeFilho = partes[0];
                String nomePai = partes[1];

                arvore.inserir(nomeFilho, nomePai);
                arvore.definirRaiz();

            }
        } catch(IOException e){
            System.out.println("Erro: " + e.getMessage());
        }

        return arvore;
    }
}
