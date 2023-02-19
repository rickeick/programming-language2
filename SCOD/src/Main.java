import controles.ONG;
import entidades.Doacao;
import entidades.Doador;
import entidades.Campanha;
import entidades.Entidade;
import entidades.itens.*;

import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ArrayList<Doacao> doacoes = new ArrayList<>();
        ArrayList<Doador> doadores = new ArrayList<>();
        ArrayList<Campanha> campanhas = new ArrayList<>();
        ONG ong = new ONG(doadores, doacoes, campanhas);
        int opcao = 0; Scanner entrada = new Scanner(System.in);
        do {
            try {
                System.out.println("[1] Cadastrar Doador");
                System.out.println("[2] Cadastrar Doação de Alimento");
                System.out.println("[3] Cadastrar Doação de Material de Construção");
                System.out.println("[4] Cadastrar Doação de Material de Limpeza");
                System.out.println("[5] Cadastrar Doação de Roupa");
                System.out.println("[6] Cadastrar Campanha de Alimentos");
                System.out.println("[7] Cadastrar Campanha de Materiais de Construção");
                System.out.println("[8] Cadastrar Campanha de Materiais de Limpeza");
                System.out.println("[9] Cadastrar Campanha de Roupas");
                System.out.println("[10] Listar Doadores");
                System.out.println("[11] Listar Doações");
                System.out.println("[12] Visualizar Campanha");
                System.out.println("[13] Fechar Campanha");
                System.out.println("[14] Sair do SCOD");
                System.out.print("Escolha uma Opção: ");
                opcao = Integer.parseInt(entrada.nextLine());
                switch (opcao) {
                    case 1 -> {
                        String id, nome;
                        System.out.print("Nome do Doador: ");
                        nome = entrada.nextLine();
                        System.out.print("CPF/CNPJ: ");
                        id = entrada.nextLine();
                        ong.cadDoador(nome, id);
                    }
                    case 2 -> {
                        int indice, quant; String nome;
                        System.out.println("Doadores Cadastrados:");
                        for (int i=0; i<ong.getDoadores().size(); i++) {
                            System.out.printf("[%d] %s\n", i, ong.getDoadores().get(i).getNome());
                        }
                        System.out.print("Selecione o Índice do Doador: ");
                        indice = Integer.parseInt(entrada.nextLine());
                        if (indice >= ong.getDoadores().size() || indice < 0) {
                            throw new IllegalArgumentException("Doador Selecionado Não Existe!");
                        }
                        System.out.print("Nome do Item: ");
                        nome = entrada.nextLine();
                        System.out.print("Quantidade: ");
                        quant = Integer.parseInt(entrada.nextLine());
                        Alimento item = new Alimento(nome, quant);
                        ong.cadDoacao(ong.getDoadores().get(indice), item);
                    }
                    case 3 -> {
                        int indice, quant; String nome;
                        System.out.println("Doadores Cadastrados:");
                        for (int i=0; i<ong.getDoadores().size(); i++) {
                            System.out.printf("[%d] %s\n", i, ong.getDoadores().get(i).getNome());
                        }
                        System.out.print("Selecione o Índice do Doador: ");
                        indice = Integer.parseInt(entrada.nextLine());
                        if (indice >= ong.getDoadores().size() || indice < 0) {
                            throw new IllegalArgumentException("Doador Selecionado Não Existe!");
                        }
                        System.out.print("Nome do Item: ");
                        nome = entrada.nextLine();
                        System.out.print("Quantidade: ");
                        quant = Integer.parseInt(entrada.nextLine());
                        Construcao item = new Construcao(nome, quant);
                        ong.cadDoacao(ong.getDoadores().get(indice), item);
                    }
                    case 4 -> {
                        int indice, quant; String nome;
                        System.out.println("Doadores Cadastrados:");
                        if (ong.getDoadores().size() < 1) {
                            System.out.println("Não Há Doadores Cadastrados!");
                        }
                        for (int i=0; i<ong.getDoadores().size(); i++) {
                            System.out.printf("[%d] %s\n", i, ong.getDoadores().get(i).getNome());
                        }
                        System.out.print("Selecione o Índice do Doador: ");
                        indice = Integer.parseInt(entrada.nextLine());
                        if (indice >= ong.getDoadores().size() || indice < 0) {
                            throw new IllegalArgumentException("Doador Selecionado Não Existe!");
                        }
                        System.out.print("Nome do Item: ");
                        nome = entrada.nextLine();
                        System.out.print("Quantidade: ");
                        quant = Integer.parseInt(entrada.nextLine());
                        Limpeza item = new Limpeza(nome, quant);
                        ong.cadDoacao(ong.getDoadores().get(indice), item);
                    }
                    case 5 -> {
                        int indice, quant; String nome;
                        System.out.println("Doadores Cadastrados:");
                        for (int i=0; i<ong.getDoadores().size(); i++) {
                            System.out.printf("[%d] %s\n", i, ong.getDoadores().get(i).getNome());
                        }
                        System.out.print("Selecione o Índice do Doador: ");
                        indice = Integer.parseInt(entrada.nextLine());
                        if (indice >= ong.getDoadores().size() || indice < 0) {
                            throw new IllegalArgumentException("Doador Selecionado Não Existe!");
                        }
                        System.out.print("Nome do Item: ");
                        nome = entrada.nextLine();
                        System.out.print("Quantidade: ");
                        quant = Integer.parseInt(entrada.nextLine());
                        Roupa item = new Roupa(nome, quant);
                        ong.cadDoacao(ong.getDoadores().get(indice), item);
                    }
                    case 6 -> {
                        int total, quant; Entidade entidade;
                        String titulo, nome, data; LocalDate prazo;
                        ArrayList<Item> itens = new ArrayList<>();
                        System.out.print("Título da Campanha: ");
                        titulo = entrada.nextLine();
                        System.out.print("Nome da Entidade Alvo: ");
                        nome = entrada.nextLine();
                        entidade = new Entidade(nome);
                        System.out.print("Prazo para Conlusão: ");
                        data = entrada.nextLine();
                        prazo = LocalDate.parse(data, formato);
                        System.out.print("Total de Alimentos para Cadastrar: ");
                        total = Integer.parseInt(entrada.nextLine());
                        for (int i=0; i<total; i++) {
                            System.out.print("  Nome: ");
                            nome = entrada.nextLine();
                            System.out.print("  Quantidade: ");
                            quant = Integer.parseInt(entrada.nextLine());
                            Alimento item = new Alimento(nome, quant);
                            itens.add(item);
                        }
                        ong.cadCampanha(titulo, itens, entidade, prazo);
                    }
                    case 7 -> {
                        int total, quant; Entidade entidade;
                        String titulo, nome, data; LocalDate prazo;
                        ArrayList<Item> itens = new ArrayList<>();
                        System.out.print("Título da Campanha: ");
                        titulo = entrada.nextLine();
                        System.out.print("Nome da Entidade Alvo: ");
                        nome = entrada.nextLine();
                        entidade = new Entidade(nome);
                        System.out.print("Prazo para Conlusão: ");
                        data = entrada.nextLine();
                        prazo = LocalDate.parse(data, formato);
                        System.out.print("Total de Materiais de Construção para Cadastrar: ");
                        total = Integer.parseInt(entrada.nextLine());
                        for (int i=0; i<total; i++) {
                            System.out.print("  Nome: ");
                            nome = entrada.nextLine();
                            System.out.print("  Quantidade: ");
                            quant = Integer.parseInt(entrada.nextLine());
                            Construcao item = new Construcao(nome, quant);
                            itens.add(item);
                        }
                        ong.cadCampanha(titulo, itens, entidade, prazo);
                    }
                    case 8 -> {
                        int total, quant; Entidade entidade;
                        String titulo, nome, data; LocalDate prazo;
                        ArrayList<Item> itens = new ArrayList<>();
                        System.out.print("Título da Campanha: ");
                        titulo = entrada.nextLine();
                        System.out.print("Nome da Entidade Alvo: ");
                        nome = entrada.nextLine();
                        entidade = new Entidade(nome);
                        System.out.print("Prazo para Conlusão: ");
                        data = entrada.nextLine();
                        prazo = LocalDate.parse(data, formato);
                        System.out.print("Total de Materiais de Limpeza para Cadastrar: ");
                        total = Integer.parseInt(entrada.nextLine());
                        for (int i=0; i<total; i++) {
                            System.out.print("  Nome: ");
                            nome = entrada.nextLine();
                            System.out.print("  Quantidade: ");
                            quant = Integer.parseInt(entrada.nextLine());
                            Limpeza item = new Limpeza(nome, quant);
                            itens.add(item);
                        }
                        ong.cadCampanha(titulo, itens, entidade, prazo);
                    }
                    case 9 -> {
                        int total, quant; Entidade entidade;
                        String titulo, nome, data; LocalDate prazo;
                        ArrayList<Item> itens = new ArrayList<>();
                        System.out.print("Título da Campanha: ");
                        titulo = entrada.nextLine();
                        System.out.print("Nome da Entidade Alvo: ");
                        nome = entrada.nextLine();
                        entidade = new Entidade(nome);
                        System.out.print("Prazo para Conlusão: ");
                        data = entrada.nextLine();
                        prazo = LocalDate.parse(data, formato);
                        System.out.print("Total de Roupas para Cadastrar: ");
                        total = Integer.parseInt(entrada.nextLine());
                        for (int i=0; i<total; i++) {
                            System.out.print("  Nome: ");
                            nome = entrada.nextLine();
                            System.out.print("  Quantidade: ");
                            quant = Integer.parseInt(entrada.nextLine());
                            Roupa item = new Roupa(nome, quant);
                            itens.add(item);
                        }
                        ong.cadCampanha(titulo, itens, entidade, prazo);
                    }
                    case 10 -> {
                        for (Doador doador : ong.getDoadores()) {
                            System.out.println(doador);
                        }
                    }
                    case 11 -> {
                        for (Doacao doacao : ong.getDoacoes()) {
                            System.out.println(doacao);
                        }
                    }
                    case 12 -> {
                        int indice; Campanha escolhida;
                        if (ong.getCampanhas().size() < 1) {
                            System.out.println("Não Há Campanhas Cadastradas"); break;
                        }
                        for (int i=0; i<ong.getCampanhas().size(); i++) {
                            System.out.printf("[%d] %s\n", i, ong.getCampanhas().get(i).getTitulo());
                        }
                        System.out.print("Selecione o Indice da Campanha: ");
                        indice = Integer.parseInt(entrada.nextLine());
                        if (indice > ong.getCampanhas().size() || indice < 0) {
                            throw new IllegalArgumentException("Campanha Selecionada Não Existe!");
                        }
                        escolhida = ong.getCampanhas().get(indice);
                        escolhida.updtAndamento();
                        System.out.printf("Título: %s\n", escolhida.getTitulo());
                        System.out.printf("Entidade: %s\n", escolhida.getEntidade().getNome());
                        System.out.printf("Andamento: %2f\n", escolhida.getAndamento());
                        System.out.printf("Prazo: %s\n", escolhida.getPrazo());
                        System.out.println("Itens para Arrecadar:");
                        for (Item item : escolhida.getItens()) {
                            System.out.println(item);
                        }
                        System.out.println("Itens Arrcadados:");
                        for (Doacao doacao : escolhida.getArecadacoes()) {
                            System.out.println(doacao.getItem());
                        }
                    }
                    case 13 -> {
                        int indice, acao; long dias; String data; LocalDate prazo;
                        System.out.println("Campanhas com Prazo Finalizado:");
                        for (int i=0; i<ong.getCampanhas().size(); i++) {
                            dias = ChronoUnit.DAYS.between(LocalDate.now(), ong.getCampanhas().get(i).getPrazo());
                            if (dias <= 0) {
                                System.out.printf("[%d] %s\n", i, ong.getCampanhas().get(i).getTitulo());
                            }
                        }
                        System.out.print("Selecione o Indice da Campanha: ");
                        indice = Integer.parseInt(entrada.nextLine());
                        if (ChronoUnit.DAYS.between(LocalDate.now(), ong.getCampanhas().get(indice).getPrazo()) > 0) {
                            throw new IllegalArgumentException("Campanha Selecionada Ainda Não Fechou o Prazo!");
                        }
                        if (indice > ong.getCampanhas().size() || indice < 0) {
                            throw new IllegalArgumentException("Campanha Selecionada Não Existe!");
                        }
                        ong.getCampanhas().get(indice).updtAndamento();
                        if (ong.getCampanhas().get(indice).getAndamento() < 100) {
                            System.out.println("A Campanha Selecionada Não Foi Concluída!");
                            System.out.println("[1] Fechar a Campanha");
                            System.out.println("[2] Alterar o Prazo da Campanha");
                            System.out.print("Escolha uma Ação:");
                            acao = Integer.parseInt(entrada.nextLine());
                            if (acao == 1) {
                                ong.fecharCampanha(ong.getCampanhas().get(indice));
                                System.out.println("Campanha Fechada!");
                                ong.atualizarDoacoes();
                            }
                            else if (acao == 2) {
                                System.out.print("Novo Prazo: ");
                                data = entrada.nextLine();
                                prazo = LocalDate.parse(data, formato);
                                ong.getCampanhas().get(indice).setPrazo(prazo);
                            }
                            else {
                                System.out.println("Ação Escolhida é Inválida!");
                            }
                        }
                        else {
                            ong.fecharCampanha(ong.getCampanhas().get(indice));
                            System.out.println("Campanha Fechada!");
                            ong.atualizarDoacoes();
                        }
                    }
                    case 14 -> {
                        System.out.println("SCOD Encerrado!");
                    }
                    default -> {
                        System.out.println("Número Inválido!");
                    }
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Digite um Número Válido!");
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            catch (DateTimeParseException e) {
                System.out.println("Formato de Data Inválido!");
                System.out.println("Formato Válido: [dd/MM/yyyy]");
            }
            System.out.println();
        } while (opcao != 14);
        entrada.close();
    }
}
