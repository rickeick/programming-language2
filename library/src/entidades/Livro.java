package entidades;

public class Livro extends Material {
    private int edicao;

    public Livro(String titulo, String[] autores, String ano, int edicao) {
        super(titulo, autores, ano);
        this.edicao = edicao;
    }

    public int getEdicao() {
        return edicao;
    }
}
