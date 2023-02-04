import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int opcao;
        String turno, data;
        String salada, principal, acompanhamento;
        ArrayList<Refeicao> menu = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Prato prato;
        do {
            System.out.println("[1] Cadastrar Refeição no Menu");
            System.out.println("[2] Visualizar Menu");
            System.out.println("[3] Sair");
            System.out.print("Escolha uma Opção: ");
            opcao = Integer.parseInt(sc.nextLine());
            switch (opcao) {
                case 1 -> {
                    System.out.print("Principal: "); principal = sc.nextLine();
                    System.out.print("Acompanhamento: "); acompanhamento = sc.nextLine();
                    System.out.print("Salada: "); salada = sc.nextLine();
                    System.out.print("Data: "); data = sc.nextLine();
                    System.out.print("Turno: "); turno = sc.nextLine();
                    prato = new Prato(principal, acompanhamento, salada);
                    menu.add(new Refeicao(prato, data, turno));
                }
                case 2 -> {
                    System.out.print("Data do Menu: "); data = sc.nextLine();
                    for (Refeicao refeicao : menu) {
                        if (data.equals(refeicao.data)) {
                            System.out.println(refeicao);
                        }
                    }
                }
                case 3 -> System.out.println("Sistema Encerrado!");
                default -> System.out.println("Opção Inválida!");
            }
            System.out.println();
        } while (opcao != 3);
    }
}
