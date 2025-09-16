package entities;

import java.util.ArrayList;
import java.util.List;

public class Pessoa {
    private final String nome;
    private Pessoa pai;
    private Pessoa esq, dir;
    private List<Pessoa> filhos = new ArrayList<>();

    public Pessoa(String nome) {
        this.nome = nome;
        this.pai = null;
        this.esq = null;
        this.dir = null;
    }

    public String getNome() {
        return nome;
    }

    public Pessoa getPai() {
        return pai;
    }

    public void setPai(Pessoa pai) {
        this.pai = pai;
    }

    public Pessoa getEsq() {
        return esq;
    }

    public void setEsq(Pessoa esq) {
        this.esq = esq;
    }

    public Pessoa getDir() {
        return dir;
    }

    public void setDir(Pessoa dir) {
        this.dir = dir;
    }

    public List<Pessoa> getFilhos() {
        return filhos;
    }

    public void addFilho(Pessoa filho) {
        this.filhos.add(filho);
    }
}
