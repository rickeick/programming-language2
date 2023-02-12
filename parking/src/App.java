import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        boolean stat; int opcao, ticket;
        Maquina maquina = new Maquina(5);
        Scanner entrada = new Scanner(System.in);
        do {
            System.out.println("[1] Emitir Ticket");
            System.out.println("[2] Liberar Ticket");
            System.out.println("[3] Sair");
            System.out.print("Escolha uma Opção: ");
            opcao = Integer.parseInt(entrada.next());
            switch (opcao) {
                case 1 -> {
                    ticket = maquina.emitir();
                    if (ticket != -1) {
                        System.out.printf("Ticket [%d] emitido!\n", ticket);
                    } else {
                        System.out.println("Não há vagas disponíveis!");
                    }
                }
                case 2 -> {
                    System.out.print("Número do Ticket: ");
                    ticket = Integer.parseInt(entrada.next());
                    stat = maquina.liberar(ticket);
                    if (stat) {
                        System.out.printf("Vaga [%d] Liberada!\n", ticket);
                        System.out.println("Volte Sempre!");
                    } else {
                        System.out.println("Erro ao Liberar a Vaga!");
                        System.out.println("Tente Novamente!");
                    }
                }
                case 3 -> System.out.println("Sistema Encerrado!");
                default -> System.out.println("Opção Inválida!");
            }
            System.out.println();
        } while (opcao != 3);
        entrada.close();
    }
}
