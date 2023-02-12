public class PessoaJuridica extends Pessoa {
    public PessoaJuridica(String id, String nome, String data, double rendimentos, double pago) {
        super(id, nome, data, rendimentos, pago);
    }

    @Override
    public void calcularImposto() {
        aliquota = 0.1;
        faixa = "Única";
        imposto = (rendimentos * aliquota) - pago;
    }
}
