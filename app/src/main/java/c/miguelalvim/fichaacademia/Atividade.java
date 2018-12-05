package c.miguelalvim.fichaacademia;

public class Atividade {
    String nome;
    int numero, id;

    public Atividade(String n, int i, int id) {
        nome = n;
        numero = i;
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
