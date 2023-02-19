package entidades;

public class Doador {
    private String nome;
    private String id;

    public Doador(String nome, String id) {
        if (nome.equals("") || id.equals("")) {
            throw new IllegalArgumentException("Valores Não Podem Ser Vazios");
        }
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return nome + " | CPF/CNPJ: " + id;
    }
}
