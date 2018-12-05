package c.miguelalvim.fichaacademia;

import java.util.ArrayList;

public class Ficha {
    String nome;
    int vezes, id;
    ArrayList<Atividade> atividades = new ArrayList<>();

    public Ficha(String nome, int vezes, int id) {
        this.nome = nome;
        this.vezes = vezes;
        this.id = id;
    }

    public void addAtividade(Atividade a) {
        atividades.add(a);
    }

    public void removeAtividade(Atividade a) {
        atividades.remove(a);
    }
}
