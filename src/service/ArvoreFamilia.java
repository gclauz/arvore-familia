package service;

import entities.Pessoa;
import java.util.HashMap;
import java.util.Map;

public class ArvoreFamilia {

    private Pessoa raiz;
    private Map<String, Pessoa> pessoas = new HashMap<>(); // Dicionário para construir a árvore

    public Pessoa getRaiz() {
        return raiz;
    }

    private Pessoa buscar(Pessoa atual, String nome) {
        if (atual == null) {
            return null;
        }
        if (atual.getNome().equals(nome)) {
            return atual;
        }

        Pessoa encontrado = buscar(atual.getEsq(), nome);
        if (encontrado != null) {
            return encontrado;
        }

        return buscar(atual.getDir(), nome);
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
        for (Pessoa pessoa : pessoas.values()) { // percorre por todas as pessoas no map
            if (pessoa.getPai() == null) { // para cada pessoa ele verifica se o pai da pessoa é null
                this.raiz = pessoa; // define a pessoa como raiz, já que não encontrou o pai dela
                return;
            }
        }
    }

    public String relacao(String nome1, String nome2) {
        Pessoa p1 = buscar(this.raiz ,nome1);
        Pessoa p2 = buscar(this.raiz ,nome2);

        if (p1 == null || p2 == null) return "sem relacao";

        // Pai/Filho imediato
        if (p1.getPai() == p2) return "filho";
        if (p2.getPai() == p1) return "pai";

        // Irmãos
        if (p1.getPai() != null && p1.getPai() == p2.getPai()) return "irmao";

        // Verificar se p1 é descendente de p2
        Pessoa atual = p1;
        int distancia = 0;
        while (atual != null) {
            if (atual == p2) {
                if (distancia == 2) return "neto";
                if (distancia == 3) return "bisneto";
                if (distancia == 4) return "tataraneto";
                if (distancia > 4){
                    StringBuilder sb = new StringBuilder();
                    String s1 = "tataraneto";
                    String s2 = "ta";

                    for (int i = 0; i < distancia - 4; i++) {
                        sb.append(s2);
                    }
                    sb.append(s1);

                    String descendente = sb.toString();
                    return descendente;
                }
            }
            atual = atual.getPai();
            distancia++;
        }

        // Verificar se p2 é descendente de p1
        atual = p2;
        distancia = 0;
        while (atual != null) {
            if (atual == p1) {
                if (distancia == 2) return "avo";
                if (distancia == 3) return "bisavo";
                if (distancia == 4) return "tataravo";
                if (distancia > 4){
                    StringBuilder sb = new StringBuilder();
                    String s1 = "tataravo";
                    String s2 = "ta";

                    for (int i = 0; i < distancia - 4; i++) {
                        sb.append(s2);
                    }
                    sb.append(s1);

                    String descendente = sb.toString();
                    return descendente;
                }
            }
            atual = atual.getPai();
            distancia++;
        }

        // Procurar ancestral comum mais próximo
        Pessoa atualP1 = p1;

        while (atualP1 != null) {
            Pessoa atualP2 = p2;
            int distanciaP1 = 0;

            while (atualP2 != null) {
                if (atualP1 == atualP2) {
                    // Encontrou ancestral comum
                    int distanciaP2 = 0;
                    Pessoa aux = p1;

                    while (aux != atualP1) {
                        aux = aux.getPai();
                        distanciaP1++;
                    }

                    aux = p2;

                    while (aux != atualP1) {
                        aux = aux.getPai();
                        distanciaP2++;
                    }

                    int grauParentesco; // grau do primo
                    if (distanciaP1 < distanciaP2) {
                        grauParentesco = distanciaP1 - 1;
                    } else {
                        grauParentesco = distanciaP2 - 1;
                    }

                    int diferencaGrau; // diferença de níveis
                    if (distanciaP1 > distanciaP2) {
                        diferencaGrau = distanciaP1 - distanciaP2;
                    } else {
                        diferencaGrau = distanciaP2 - distanciaP1;
                    }

                    if (grauParentesco < 0) {
                        grauParentesco = 0;
                    }

                    return "primo-" + grauParentesco + " em grau " + diferencaGrau;
                }
                atualP2 = atualP2.getPai(); // vai subindo p2
            }
            atualP1 = atualP1.getPai(); // vai subindo p1 
        }

        return "sem relacao";
    }

    public void imprimirArvore(Pessoa pessoa, String espaco) {
        if (pessoa == null) {
            return;
        }

        imprimirArvore(pessoa.getEsq(), espaco + "            ");

        System.out.println(espaco + pessoa.getNome());

        imprimirArvore(pessoa.getDir(), espaco + "            ");
    }
}
