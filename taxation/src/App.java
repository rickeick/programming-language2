import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int opcao;
        Pessoa contribuinte;
        String id, nome, data;
        double rendimentos, pago;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Calcular Imposto de Renda\n");
        do {
            System.out.println("[1] Pessoa Física");
            System.out.println("[2] Pessoa Juridica");
            System.out.println("[3] Sair");
            System.out.print("Escolha uma opção: ");
            opcao = entrada.nextInt(); entrada.nextLine();
            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    nome = entrada.nextLine();
                    System.out.print("CPF: ");
                    id = entrada.nextLine();
                    System.out.print("Data de Nascimento: ");
                    data = entrada.nextLine();
                    System.out.print("Rendimentos Anual: ");
                    rendimentos = entrada.nextDouble();
                    entrada.nextLine();
                    System.out.print("Impostos Já Pago: ");
                    pago = entrada.nextDouble();
                    entrada.nextLine();
                    contribuinte = new PessoaFisica(id, nome, data, rendimentos, pago);
                    contribuinte.calcularImposto();
                    System.out.println(contribuinte);
                }
                case 2 -> {
                    System.out.print("Nome: ");
                    nome = entrada.nextLine();
                    System.out.print("CNPJ: ");
                    id = entrada.nextLine();
                    System.out.print("Data de Criação: ");
                    data = entrada.nextLine();
                    System.out.print("Rendimentos Anual: ");
                    rendimentos = entrada.nextDouble();
                    entrada.nextLine();
                    System.out.print("Impostos Já Pago: ");
                    pago = entrada.nextDouble();
                    entrada.nextLine();
                    contribuinte = new PessoaJuridica(id, nome, data, rendimentos, pago);
                    contribuinte.calcularImposto();
                    System.out.println(contribuinte);
                }
                case 3 -> System.out.println("Sistema Encerrado!");
                default -> System.out.println("Opção Inválida!");
            }
        } while (opcao != 3);
        entrada.close();
    }
}
