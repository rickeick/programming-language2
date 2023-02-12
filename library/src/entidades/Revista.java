package entidades;

public class Revista extends Material {
    private String periodicidade;

    public Revista(String titulo, String[] autores, String ano, String periodicidade) {
        super(titulo, autores, ano);
        this.periodicidade = periodicidade;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }
}
