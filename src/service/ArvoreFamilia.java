package service;

import entities.Pessoa;

import java.util.HashMap;
import java.util.Map;

public class ArvoreFamilia {
    private Pessoa raiz;
    private Map<String, Pessoa> pessoas = new HashMap<>();

    public Pessoa getRaiz() {
        return raiz;
    }

    public Pessoa buscar(String nome) {
        return pessoas.get(nome);
    }

    public void inserir(String nomeFilho, String nomePai) {
        Pessoa pai = pessoas.get(nomePai);
        if (pai == null) {
            pai = new Pessoa(nomePai);
            pessoas.put(nomePai, pai);
        }

        Pessoa filho = pessoas.get(nomeFilho);
        if (filho == null) {
            filho = new Pessoa(nomeFilho);
            pessoas.put(nomeFilho, filho);
        }

        filho.setPai(pai);
        if (pai.getEsq() == null) {
            pai.setEsq(filho);
        } else if (pai.getDir() == null) {
            pai.setDir(filho);
        }
    }

    public void definirRaiz() {
        for (Pessoa pessoa : pessoas.values()) {
            if (pessoa.getPai() == null) {
                this.raiz = pessoa;
                return;
            }
        }
    }

    public String relacao(String nome1, String nome2) {
        Pessoa p1 = buscar(nome1);
        Pessoa p2 = buscar(nome2);

        if (p1 == null || p2 == null) {
            return "sem relacao";
        }

        System.out.println("vsf essa merda tmnc");

        return "sem relacao";
    }

    public void imprimirArvore(Pessoa pessoa, String espaco) {
        if (pessoa == null) return;

        imprimirArvore(pessoa.getEsq(), espaco + "            ");

        System.out.println(espaco + pessoa.getNome());

        imprimirArvore(pessoa.getDir(), espaco + "            ");
    }
}
