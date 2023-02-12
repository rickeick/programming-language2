package entidades;

public class Material {
    protected String titulo;
    protected String[] autores;
    protected String ano;

    public Material(String titulo, String[] autores, String ano) {
        this.titulo = titulo;
        this.autores = autores;
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public String[] getAutores() {
        return autores;
    }

    public String getAno() {
        return ano;
    }
}
