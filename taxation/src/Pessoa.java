public class Pessoa {
    protected String id;
    protected String nome;
    protected String data;
    protected String faixa;
    protected double imposto;
    protected double aliquota;
    protected double rendimentos;
    protected double deducoes;
    protected double pago;

    public Pessoa(String id, String nome, String data, double rendimentos, double pago) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.rendimentos = rendimentos;
        this.pago = pago;
    }

    public void calcularImposto() {
        if (rendimentos <= 22847.76) {
            faixa = "Até 22847.76";
            deducoes = 0;
            aliquota = 0;
            return;
        }
        else if (rendimentos >= 22847.77 && rendimentos <= 33919.80) {
            faixa = "22847.77 e 33919.80";
            deducoes = 142.8;
            aliquota = 0.075;
        }
        else if (rendimentos >= 33919.81 && rendimentos <= 45012.60) {
            faixa = "33919.81 e 45012.60";
            deducoes = 354.8;
            aliquota = 0.15;
        }
        else if (rendimentos >= 45012.61 && rendimentos >= 55976.16) {
            faixa = "45012.61 e 55976.16";
            deducoes = 636.13;
            aliquota = 0.225;
        }
        else {
            faixa = "Acima de 55976.17";
            deducoes = 869.36;
            aliquota = 0.275;
        }
        imposto = (rendimentos * aliquota) - deducoes - pago;
    }

    @Override
    public String toString() {
        return "Faixa: "+faixa+"\n"
                +"Aliquota da Faixa: "+aliquota+"\n"
                +"Deduções da Faixa: "+deducoes+"\n"
                +"Imposto à Pagar: "+imposto+"\n";
    }
}
