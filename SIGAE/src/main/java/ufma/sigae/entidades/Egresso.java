package ufma.sigae.entidades;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Representa um egresso e seus dados
 */
public class Egresso implements Serializable, Comparable<Egresso> {
    /** CPF do egresso */
    private String cpf;
    /** Nome do egresso */
    private String nome;
    /** Data de conclusão do curso do egresso */
    private LocalDate conclusao;
    /** Lista de contatos do egresso */
    private final List<Contato> contatos;
    /** Lista de formacões acadêmicas do egresso */
    private final List<Formacao> formacoes;
    /** Lista de ocupações em empresas do egresso */
    private final List<Ocupacao> ocupacoes;
    /** Depoimento livre sobre o curso e/ou a vida profissional */
    private String depoimento;

    /**
     * Cria um egresso com seus dados
     * @param cpf CPF do egresso
     * @param nome nome do egresso
     * @param conclusao data de conclusão do curso do egresso
     * @param depoimento depoimento livre sobre o curso e/ou a vida profissional
     */
    public Egresso(String cpf, String nome, LocalDate conclusao, String depoimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.conclusao = conclusao;
        this.depoimento = depoimento;
        this.contatos = new LinkedList<>();
        this.formacoes = new LinkedList<>();
        this.ocupacoes = new LinkedList<>();
    }

    /**
     * Recupera o CPF do egresso
     * @return o CPF do egresso
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Recupera o nome do egresso
     * @return o nome do egresso
     */
    public String getNome() {
        return nome;
    }

    /**
     * Recupera a data de conclusão do curso do egresso
     * @return a data de conclusão do curso do egresso
     */
    public LocalDate getConclusao() {
        return conclusao;
    }

    /**
     * Recupera a lista de contatos do egresso
     * @return a lista de contatos do egresso
     */
    public List<Contato> getContatos() {
        return contatos;
    }

    /**
     * Recupera a lista de formações acadêmicas do egresso
     * @return a lista de formações acadêmicas do egresso
     */
    public List<Formacao> getFormacoes() {
        return formacoes;
    }

    /**
     * Recupera a lista de ocupações em empresas do egresso
     * @return a lista de ocupações em empresas do egresso
     */
    public List<Ocupacao> getOcupacoes() {
        return ocupacoes;
    }

    /**
     * Recupera o depoimento livro do egresso
     * @return o depoimento livro do egresso
     */
    public String getDepoimento() {
        return depoimento;
    }

    /**
     * Altera o cpf do egresso
     * @param cpf novo cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Altera o nome do egresso
     * @param nome novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Altera a data de conclusão do curso do egresso
     * @param conclusao nova data de conclusão do curso
     */
    public void setConclusao(LocalDate conclusao) {
        this.conclusao = conclusao;
    }

    /**
     * Altera o depoimento livre do egresso
     * @param depoimento novo depoimento livre
     */
    public void setDepoimento(String depoimento) {
        this.depoimento = depoimento;
    }

    /**
     * Compara dois egressos pelo nome
     * @param o o egresso para ser comparado
     * @return o resutado da comparação
     */
    @Override
    public int compareTo(Egresso o) {
        return nome.compareTo(o.getNome());
    }
}
