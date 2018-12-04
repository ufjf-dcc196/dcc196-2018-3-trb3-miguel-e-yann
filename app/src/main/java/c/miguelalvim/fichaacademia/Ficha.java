package c.miguelalvim.fichaacademia;

import java.util.ArrayList;

public class Ficha {
    String nome;
    int vezes;
    ArrayList<Atividade> atividades = new ArrayList<>();

    public Ficha(String nome, int vezes) {
        this.nome = nome;
        this.vezes = vezes;
    }

    public void addAtividade(Atividade a) {
        atividades.add(a);
    }

    public void removeAtividade(Atividade a) {
        atividades.remove(a);
    }
}
