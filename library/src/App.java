import entidades.*;
import controladores.*;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String[] autores = new String[1];
        ArrayList<Material> acervo = new ArrayList<>();
        autores[0] = "Allen B. Downey";
        acervo.add(new Livro("Pense em Python", autores, "2002", 2));
        autores[0] = "Paul Deitel";
        acervo.add(new Livro("C - Como Programar", autores, "1992", 1));
        acervo.add(new Livro("Java - Como Programar", autores, "1996", 1));
        autores[0] = "James Stewart";
        acervo.add(new Livro("Cálculo Volume II", autores, "2009", 6));
        autores[0] = "Paulo Veloso";
        acervo.add(new Livro("Estruturas de Dados Usando C", autores, "1995", 2));
        autores[0] = "Halliday";
        acervo.add(new Livro("Fundamentos de Fisica III", autores, "2014", 5));
        autores[0] = "Editora Abril";
        acervo.add(new Revista("Superinteressante Nº 440", autores, "2022", "Mensal"));
        acervo.add(new Revista("Superinteressante Nº 441", autores, "2022", "Mensal"));
        acervo.add(new Revista("Superinteressante Nº 442", autores, "2022", "Mensal"));
        acervo.add(new Revista("Superinteressante Nº 443", autores, "2022", "Mensal"));

        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("admin", "13786"));
        usuarios.add(new Usuario("pedro", "03349"));
        usuarios.add(new Usuario("tiago", "58159"));
        usuarios.add(new Usuario("joao", "123456"));

        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
        Biblioteca ccet = new Biblioteca(acervo, usuarios, emprestimos);

        int opcao, cont, indice;
        String login, senha; Usuario usuario;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Biblioteca CCET");

        do {
            System.out.println();
            System.out.println("[1] Fazer Login");
            System.out.println("[2] Registra-se");
            System.out.println("[3] Encerrar");
            System.out.print("Escolha uma Opção:");
            opcao = entrada.nextInt(); entrada.nextLine();

            if (opcao < 1 || opcao > 3) {
                System.out.println("Opção Inválida!");
                continue;
            }
            else if (opcao == 1) {
                System.out.print("Login:");
                login = entrada.nextLine();
                System.out.print("Senha:");
                senha = entrada.nextLine();
                usuario = ccet.entrar(login, senha);
                if (usuario != null) {System.out.printf("\nBem Vindo! %s\n", login);}
                if (usuario == null) {System.out.println("Login ou Senha Incorreta!"); continue;}
            }
            else if (opcao == 2) {
                System.out.print("Login:");
                login = entrada.nextLine();
                System.out.print("Senha:");
                senha = entrada.nextLine();
                usuario = ccet.registrar(login, senha);
                if (usuario != null) {System.out.printf("\nBem Vindo! %s\n", login);}
                if (usuario == null) {System.out.println("Usuário Já Existe!"); continue;}
            }
            else {
                System.out.println("\nVolte Sempre!");
                System.out.println("Não Esqueça de Devolver os Materiais!");
                break;
            }

            do {
                System.out.println();
                System.out.println("[1] Pegar Material");
                System.out.println("[2] Devolver Material");
                System.out.println("[4] Logout");
                System.out.print("Escolha uma Opção:");
                opcao = entrada.nextInt(); entrada.nextLine();

                if (opcao < 1 || opcao > 3) {
                    System.out.println("Opção Inválida!");
                }
                else if (opcao == 1) {
                    cont = 0;
                    for (Material item : ccet.getAcervo()) {
                        System.out.printf("[%d] %s\n", cont, item.getTitulo());
                        cont++;
                    }
                    System.out.print("Escolha uma opção:");
                    indice = entrada.nextInt(); entrada.nextLine();
                    ccet.emprestar(ccet.getAcervo().get(indice), usuario);
                    System.out.println("Material Emprestado!");
                }
                else if (opcao == 2) {
                    cont = 0;
                    for (Emprestimo e : ccet.getEmprestimos()) {
                        if (e.getUsuario().getLogin().equals(usuario.getLogin())) {
                            System.out.printf("[%d] %s\n", cont, e.getMaterial().getTitulo());
                        }
                        cont++;
                    }
                    System.out.print("Escolha uma opção:");
                    indice = entrada.nextInt(); entrada.nextLine();
                    ccet.devolver(ccet.getAcervo().get(indice), usuario);
                    System.out.println("Material Devolvido!");
                }
                else {
                    System.out.printf("Logout %s!\n", usuario.getLogin());
                    break;
                } 
            } while (opcao != 3);
        } while (opcao != 3);
        entrada.close();
    }
}
