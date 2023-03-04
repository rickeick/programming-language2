package ufma.sigae.entidades;

import java.io.Serializable;

/**
 * Representa qualquer forma de contato de um egresso
 */
public class Contato implements Serializable {
    /** Dado do contato, isto é, o email, o número, e.t.c */
    private String dado;
    /** Tipo do dado de contato, isto é, se é um email, um número, e.t.c */
    private String tipo;

    /**
     * Criar um contato com um dado e tipo
     * @param dado dado do contato
     * @param tipo tipo do dado do contato
     */
    public Contato(String dado, String tipo) {
        this.dado = dado;
        this.tipo = tipo;
    }

    /**
     * Recupera o dado do contato
     * @return o dado do contato
     */
    public String getDado() {
        return dado;
    }

    /**
     * Recupera o tipo de dado do contato
     * @return o tipo de dado do contato
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Altera o dado do contato
     * @param dado novo dado
     */
    public void setDado(String dado) {
        this.dado = dado;
    }

    /**
     * Altera o tipo de dado do contato
     * @param tipo novo tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Converte os dados do contato numa 'string'
     * @return uma 'string' com os dados da conta
     */
    @Override
    public String toString() {
        return tipo+":"+dado;
    }
}
