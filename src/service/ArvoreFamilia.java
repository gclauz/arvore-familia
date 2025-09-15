package service;

import entities.Pessoa;

public class ArvoreFamilia {
    private Pessoa raiz;

    public Pessoa getRaiz() {
        return raiz;
    }

    public Pessoa buscar(Pessoa pessoa, String nome) {
        if (pessoa == null) {
            return null;
        }
        
        if (pessoa.getNome().equals(nome)) {
            return pessoa;
        }

        Pessoa esq = buscar(pessoa.getEsq(), nome);
        if (esq != null) {
            return esq;
        }

        return buscar(pessoa.getDir(), nome);
    }

    public void inserir(String nomeFilho, String nomePai) {
        Pessoa pai = buscar(raiz, nomePai);
        Pessoa filho = buscar(raiz, nomeFilho);

        if (pai == null) {
            pai = new Pessoa(nomePai);
            if (raiz == null) {
                raiz = pai;
            }
        }

        if (filho == null) {
            filho = new Pessoa(nomeFilho);
        }


        filho.setPai(pai);

        if (pai.getEsq() == null) {
            pai.setEsq(filho);
        } else if (pai.getDir() == null) {
            pai.setDir(filho);
        }

        if (raiz == filho){
            raiz = pai;
        }
    }


    public String relacao(String nome1, String nome2) {
        Pessoa p1 = buscar(raiz, nome1);
        Pessoa p2 = buscar(raiz, nome2);

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
