public class Refeicao {
    Prato prato;
    String data;
    String turno;

    public Refeicao(Prato prato, String data, String turno) {
        this.prato = prato;
        this.turno = turno;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Refeição: | "+prato+" | "+turno+"";
    }
}
