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

        // Pai/Filho imediato
        if (p1.getPai() == p2) {
            return "filho";
        }
        if (p2.getPai() == p1) {
            return "pai";
        }

        // Irmãos
        if (p1.getPai() != null && p1.getPai() == p2.getPai()) {
            return "irmao";
        }

        // Verificar se p1 é descendente de p2
        Pessoa atual = p1;
        int dist = 0;
        while (atual != null) {
            if (atual == p2) {
                if (dist == 1) {
                    return "filho";
                }
                if (dist == 2) {
                    return "neto";
                }
                if (dist == 3) {
                    return "bisneto";
                }
                if (dist == 4) {
                    return "tataraneto";
                }
                return "descendente-" + dist;
            }
            atual = atual.getPai();
            dist++;
        }

        // Verificar se p2 é descendente de p1
        atual = p2;
        dist = 0;
        while (atual != null) {
            if (atual == p1) {
                if (dist == 1) {
                    return "pai";
                }
                if (dist == 2) {
                    return "avo";
                }
                if (dist == 3) {
                    return "bisavo";
                }
                if (dist == 4) {
                    return "tataravo";
                }
                return "ascendente-" + dist;
            }
            atual = atual.getPai();
            dist++;
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
                atualP2 = atualP2.getPai();
            }
            atualP1 = atualP1.getPai();
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
