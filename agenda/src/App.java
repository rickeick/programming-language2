import java.util.Scanner;
import java.util.LinkedList;

public class App {
    public static Agenda agenda = new Agenda();
    public static Scanner entrada = new Scanner(System.in);
    public static void main(String[] args) {
        int opcao = 0;
        String nome, email, telefone;
        do {
            System.out.println("[1] Adicionar Contato");
            System.out.println("[2] Listar Contatos");
            System.out.println("[3] Alterar Contato");
            System.out.println("[4] Buscar Contato");
            System.out.println("[5] Fechar Agenda");
            System.out.print("Escolha uma Opção: ");
            try {
                opcao = Integer.parseInt(entrada.nextLine());
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Nome: ");
                        nome = entrada.nextLine();
                        System.out.print("E-mail: ");
                        email = entrada.nextLine();
                        System.out.print("Telefone: ");
                        telefone = entrada.nextLine();
                        agenda.inserir(new Contato(nome, email, telefone));
                    }
                    case 2 -> {
                        for (String key : agenda.getAgenda().keySet()) {
                            for (Contato c : agenda.getAgenda().get(key)) {
                                System.out.println(c);
                            }
                        }
                    }
                    case 3 -> {
                        System.out.print("Nome para buscar: ");
                        nome = entrada.nextLine();
                        LinkedList<Contato> contatos = agenda.buscar(nome);
                        if (contatos != null) {
                            menuAlterarContato(contatos);
                        } else {
                            System.out.println("Contato(s) não encontrado!");
                        }
                    }
                    case 4 -> {
                        System.out.print("Nome para buscar: ");
                        nome = entrada.nextLine();
                        LinkedList<Contato> contatos = agenda.buscar(nome);
                        if (contatos != null) {
                            for (Contato c : contatos) {
                                System.out.println(c);
                            }
                        } else {
                            System.out.println("Contato(s) não encontrado!");
                        }
                    }
                    case 5 -> {
                        System.out.println("Agenda Fechada!");
                    }
                    default -> {
                        System.out.println("Opção Inválida!");
                    }
                }
            } catch (NumberFormatException exception) {
                System.out.println("Valor Inválido!");
            }
            System.out.println();
        } while(opcao != 5);
        entrada.close();
    }

    private static void menuAlterarContato(LinkedList<Contato> contatos) {
        int opcao = 0, posicao = 0;
        String novoNome, novoEmail, novoTelefone;
        for (int i=0; i<contatos.size(); i++) {
            System.out.printf("[%d] ", i);
            System.out.println(contatos.get(i));
        }
        try {
            System.out.print("Escolha um Contato: ");
            posicao = Integer.parseInt(entrada.nextLine());
            if (posicao < contatos.size()) {
                System.out.println("[1] Alterar Nome");
                System.out.println("[2] Alterar E-mail");
                System.out.println("[3] Alterar Telefone");
                System.out.println("[4] Cancelar");
                System.out.print("Escolha uma Opção: ");
                opcao = Integer.parseInt(entrada.nextLine());
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Novo Nome: ");
                        novoNome = entrada.nextLine();
                        Contato contato = contatos.remove(posicao);
                        contato.setNome(novoNome);
                        agenda.inserir(contato);
                    }
                    case 2 -> {
                        System.out.print("Novo E-mail: ");
                        novoEmail = entrada.nextLine();
                        contatos.get(posicao).setEmail(novoEmail);
                    }
                    case 3 -> {
                        System.out.print("Novo Telefone: ");
                        novoTelefone = entrada.nextLine();
                        contatos.get(posicao).setTelefone(novoTelefone);
                    }
                    case 4 -> {
                        System.out.println("Operação Cancelada!");
                    }
                    default -> {
                        System.out.println("Opção Inválida!");
                        System.out.println("Operação Cancelada!");
                    }
                }
            } else {
                System.out.println("Valor Inválido!");
                System.out.println("Operação Cancelada!");
            }
        } catch (NumberFormatException exception) {
            System.out.println("Valor Inválido!");
            System.out.println("Operação Cancelada!");
        }
    }
}
