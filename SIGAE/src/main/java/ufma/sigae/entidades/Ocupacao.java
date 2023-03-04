package ufma.sigae.entidades;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * Representa uma ocupação de um egresso numa empresa
 */
public class Ocupacao implements Serializable, Comparable<Ocupacao> {
    /** Nome da empresa */
    private String empresa;
    /** Cargo ocupado pelo egresso */
    private String cargo;
    /** Salario mensal do egresso */
    private float salario;
    /** Descrição do cargo na empresa */
    private String descricao;
    /** Data de início da ocupação */
    private LocalDate inicio;
    /** Cidade onde se locacaliza a empresa */
    private String cidade;
    /** País onde se locacaliza a empresa */
    private String pais;

    /**
     * Cria uma ocupação de um egresso numa empresa
     * @param empresa nome da empresa
     * @param cargo cargo ocupado pelo egresso
     * @param salario salario mensal do egresso
     * @param descricao descrição do cargo na empresa
     * @param inicio data de inicio na ocupação
     * @param cidade cidade onde se localiza a empresa
     * @param pais país onde se localiza a empresa
     */
    public Ocupacao(String empresa, String cargo, float salario,
                    String descricao, LocalDate inicio, String cidade, String pais) {
        this.empresa = empresa;
        this.cargo = cargo;
        this.salario = salario;
        this.descricao = descricao;
        this.inicio = inicio;
        this.cidade = cidade;
        this.pais = pais;
    }

    /**
     * Recupera o nome da empresa da ocupação
     * @return o nome da empresa da ocupação
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Recupera o cargo assumindo pelo egresso
     * @return o cargo assumindo pelo egresso
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Recupera o salário mensal do egresso
     * @return o salário mensal do egresso
     */
    public float getSalario() {
        return salario;
    }

    /**
     * Recupera a descrição do cargo na empresa
     * @return a descrição do cargo na empresa
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Recupera a data de início da ocupação
     * @return a data de início da ocupação
     */
    public LocalDate getInicio() {
        return inicio;
    }

    /**
     * Recupera a cidade onde se localiza a empresa
     * @return a cidade onde se localiza a empresa
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Recupera o país onde se localiza a empresa
     * @return o país onde se localiza a empresa
     */
    public String getPais() {
        return pais;
    }

    /**
     * Altera o nome da empresa da ocupação
     * @param empresa novo nome da empresa
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * Altera o cargo assumido pelo egresso
     * @param cargo novo cargo
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * Altera o salario mensal do egresso
     * @param salario novo salario mensal
     */
    public void setSalario(float salario) {
        this.salario = salario;
    }

    /**
     * Altera a descrição do cargo na empresa
     * @param descricao nova descrição do cargo
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Altera a data de início da ocupação
     * @param inicio nova data de inicio
     */
    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    /**
     * Altera a cidade onde se localiza a empresa
     * @param cidade nova cidade
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Altere o país onde se localiza a empresa
     * @param pais novo pais
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Converte os principais dados da ocupação numa 'string'
     * @return uma 'string' com os principais dados da ocupação
     */
    @Override
    public String toString() {
        return empresa+" | "+cargo+" | R$ "+salario;
    }

    /**
     * Compara duas ocupações pelo salário
     * @param o a ocupação para ser comparado
     * @return o resutado da comparação
     */
    @Override
    public int compareTo(Ocupacao o) {
        return Float.compare(salario, o.getSalario());
    }
}
