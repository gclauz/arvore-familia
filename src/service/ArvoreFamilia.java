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
        for (Pessoa pessoa : pessoas.values()) {
            if (pessoa.getPai() == null) {
                this.raiz = pessoa;
                return;
            }
        }
    }

    public String relacao(String nome1, String nome2) {
        Pessoa p1 = buscar(this.raiz, nome1);
        Pessoa p2 = buscar(this.raiz, nome2);

        if (p1 == null || p2 == null) return "sem relacao";

        // Pai/Filho
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
                if (distancia > 4) {
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
                if (distancia > 4) {
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

        return primos(p1, p2);
    }

    private String primos(Pessoa p1, Pessoa p2) {
        Pessoa atualP1 = p1;
        // para cada ancestral de p1, percorre todos os ancestrais de p2 para ver se algum eh igual
        while (atualP1 != null) {
            Pessoa atualP2 = p2;

            while (atualP2 != null) {
                if (atualP1 == atualP2) {
                    int m = 0;
                    Pessoa aux = p1;
                    while (aux != atualP1) {
                        aux = aux.getPai();
                        m++;
                    }

                    int n = 0;
                    aux = p2;
                    while (aux != atualP1) {
                        aux = aux.getPai();
                        n++;
                    }

                    /*
                    Primo-k°
                    k = min(m,n)
                    geração mais próxima ao ancestral comum

                    Primo em grau j
                    j = |m - n|
                    diferença de gerações entre os dois primos
                     */
                    int k = Math.min(m, n) - 1;
                    int j = Math.abs(m - n);

                    return "primo-" + k + " em grau " + j;
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
