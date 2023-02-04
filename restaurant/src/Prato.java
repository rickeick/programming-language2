public class Prato {
    String salada;
    String principal;
    String acompanhamento;

    public Prato(String principal, String acompanhamento, String salada) {
        this.salada = salada;
        this.principal = principal;
        this.acompanhamento = acompanhamento;
    }

    public String toString() {
        return  principal+", "+acompanhamento+", "+salada;
    }
}
