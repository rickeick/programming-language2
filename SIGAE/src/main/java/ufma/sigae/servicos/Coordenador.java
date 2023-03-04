package ufma.sigae.servicos;

import ufma.sigae.entidades.Curso;
import ufma.sigae.entidades.Egresso;
import ufma.sigae.excecoes.CadastroException;
import ufma.sigae.relatorios.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Representa um usuário como um coordenador de curso dentro do SIGAE
 */
public class Coordenador extends Usuario {
    /** Nome do coordenador */
    private final String nome;
    /** Curso que o coordenador coordena */
    private Curso curso;

    /**
     * Criar um coordenador com login, nome e senha
     * @param login login do coordenador
     * @param senha senha do coordenador
     * @param nome nome do coordenador
     */
    public Coordenador(String login, String senha, String nome) {
        super(login, senha);
        this.nome = nome;
        this.curso = null;
    }

    /**
     * Cadastra o curso que o coordenador coordena
     * @param curso curso para ser cadastrado
     * @throws CadastroException se o curso já foi cadastrado
     */
    public void cadastrarCurso(Curso curso) {
        if (this.curso != null) {
            throw new CadastroException("Curso já está cadastrado!");
        }
        if (!curso.getMatriz().equals("")) {
            if (!curso.getCentro().equals("")) {
                if (curso.getCargaHoraria() != 0) {
                    this.curso = curso; return;
                }
            }
        }
        throw new CadastroException("Campo(s) obrigatório(s) não preenchido(s)!");
    }

    /**
     * Cadastra um egresso dentro do hashtable egressos
     * @param egresso egresso para ser cadastrado
     * @throws CadastroException se o CPF do novo egresso já está cadastrado
     */
    public void cadastrarEgresso(Egresso egresso) {
        if (curso.getEgressos().get(egresso.getCpf()) != null) {
            throw new CadastroException("CPF já está em uso!");
        }
        curso.getEgressos().put(egresso.getCpf(), egresso);
    }

    /**
     * Busca um egresso no hashtable egresso usando o cpf ou nome
     * @param chave cpf ou nome do egresso para buscar
     * @return o egresso caso seja encontrado, se não retorna null
     */
    public Egresso buscarEgresso(String chave) {
        Egresso egresso = curso.getEgressos().get(chave);
        if (egresso == null) {
            for (String key : curso.getEgressos().keySet()) {
                egresso = curso.getEgressos().get(key);
                if (egresso.getNome().contains(chave)) {
                    return egresso;
                }
            }
            return null;
        }
        return egresso;
    }

    /**
     * Gera um relatório do tipo 1 para o coordenador
     * <p>[Nome do Egresso | Data de Conclusão do Curso]</p>
     * Ordenado por nome e filtrado pelo ano de conclusão dos egressos
     * @param ano ano que filtra os egressos
     * @throws IOException se não for possível criar as pastas ou o arquivo pdf
     * @throws IllegalStateException se nenhum egresso passar no filtro
     */
    public void gerarRelatorio1(int ano) throws IOException {
        ArrayList<Egresso> lista = new ArrayList<>();
        for (String key : curso.getEgressos().keySet()) {
            Egresso egresso = curso.getEgressos().get(key);
            if (egresso.getConclusao().getYear() == ano) {
                lista.add(egresso);
            }
        }
        if (lista.size() == 0) {
            throw new IllegalStateException();
        }
        Collections.sort(lista);
        String matriz = curso.getMatriz();
        RelatorioPDF1 relatorioPDF1 = new RelatorioPDF1(lista, matriz);
        relatorioPDF1.gerarCabecalho();
        relatorioPDF1.gerarCorpo();
        relatorioPDF1.imprimir();
    }

    /**
     * Gera um relatório do tipo 2 para o coordenador
     * <p>[Nome do Egresso] [Posição | Salário | Descrição]</p>
     * Ordenado por nome e filtrado por um intervalo de ano de conclusão dos egressos
     * @param anoInicial ano incial do intervalo que filtra os egressos
     * @param anoFinal ano final do intervalo que filtra os egressos
     * @throws IOException se não for possível criar as pastas ou o arquivo pdf
     * @throws IllegalStateException se nenhum egresso passar no filtro
     */
    public void gerarRelatorio2(int anoInicial, int anoFinal) throws IOException {
        ArrayList<Egresso> lista = new ArrayList<>();
        for (String key : curso.getEgressos().keySet()) {
            Egresso egresso = curso.getEgressos().get(key);
            int ano = egresso.getConclusao().getYear();
            if ((ano >= anoInicial) && (ano <= anoFinal)) {
                lista.add(egresso);
            }
        }
        if (lista.size() == 0) {
            throw new IllegalStateException();
        }
        Collections.sort(lista);
        String matriz = curso.getMatriz();
        RelatorioPDF2 relatorioPDF2 = new RelatorioPDF2(lista, matriz);
        relatorioPDF2.gerarCabecalho();
        relatorioPDF2.gerarCorpo();
        relatorioPDF2.imprimir();
    }

    /**
     * Gera um relatório do tipo 3 para o coordenador
     * <p>[Posição | Salário | Descrição]</p>
     * Ordenado por salário e filtrado por um intervalo de ano de conclusão dos egressos
     * @param anoInicial ano incial do intervalo que filtra os egressos
     * @param anoFinal ano final do intervalo que filtra os egressos
     * @throws IOException se não for possível criar as pastas ou o arquivo pdf
     * @throws IllegalStateException se nenhum egresso passar no filtro
     */
    public void gerarRelatorio3(int anoInicial, int anoFinal) throws IOException {
        ArrayList<Egresso> lista = new ArrayList<>();
        for (String key : curso.getEgressos().keySet()) {
            Egresso egresso = curso.getEgressos().get(key);
            int ano = egresso.getConclusao().getYear();
            if ((ano >= anoInicial) && (ano <= anoFinal)) {
                lista.add(egresso);
            }
        }
        if (lista.size() == 0) {
            throw new IllegalStateException();
        }
        String matriz = curso.getMatriz();
        RelatorioPDF3 relatorioPDF3 = new RelatorioPDF3(lista, matriz);
        relatorioPDF3.gerarCabecalho();
        relatorioPDF3.gerarCorpo();
        relatorioPDF3.imprimir();
    }

    /**
     * Recupera o nome do coordendador
     * @return o nome do coordendador
     */
    public String getNome() {
        return nome;
    }

    /**
     * Recupera o curso do coordenador
     * @return o curso do coordenador
     */
    public Curso getCurso() {
        return curso;
    }

    @Override
    public String toString() {
        String matriz, centro;
        if (curso != null) {
            matriz = curso.getMatriz();
            centro = curso.getCentro();
        } else {
            matriz = "Não cadastrado";
            centro = "Não cadastrado";
        }
        return nome+" | "+matriz+" | "+centro;
    }
}
