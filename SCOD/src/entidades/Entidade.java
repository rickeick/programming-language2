package entidades;

public class Entidade {
    private String nome;

    public Entidade(String nome) {
        if (nome.equals("")) {
            throw new IllegalArgumentException("Valores Não Podem Ser Vazios");
        }
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
