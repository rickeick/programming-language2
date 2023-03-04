package ufma.sigae.entidades;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * Representa uma formação de qualquer curso concluído por um egresso
 */
public class Formacao implements Serializable {
    /** Matriz curricular do curso da formacao */
    private String curso;
    /** Nível do curso da formação */
    private String nivel;
    /** Data de conclusão do curso da formação */
    private LocalDate conclusao;
    /** Carga horária total feita pelo egresso no curso da formação */
    private int cargaHoraria;

    /**
     * Cria uma formação em um curso, com nivel, carga horaria e data de conclusão
     * @param curso Nome ou identificação do curso da formacao
     * @param nivel Nível do curso da formação
     * @param conclusao Data de conclusão do curso da formação
     * @param cargaHoraria Carga horária total feita pelo egresso no curso da formação
     */
    public Formacao(String curso, String nivel, LocalDate conclusao, int cargaHoraria) {
        this.curso = curso;
        this.nivel = nivel;
        this.conclusao = conclusao;
        this.cargaHoraria = cargaHoraria;
    }

    /**
     * Recupera o curso da formação
     * @return o curso da formação
     */
    public String getCurso() {
        return curso;
    }

    /**
     * Recupera o nível do curso da formação
     * @return o nível do curso da formação
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * Recupera a data de conclusão do curso da formação
     * @return o curso de uma formação
     */
    public LocalDate getConclusao() {
        return conclusao;
    }

    /**
     * Recupera a carga horaria total do curso da formação
     * @return a carga horaria total do curso da formação
     */
    public int getCargaHoraria() {
        return cargaHoraria;
    }

    /**
     * Altera o curso da formação
     * @param curso novo curso
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * Altera o nível do curso da formação
     * @param nivel novo nível do cursp
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * Altera a data de conclusão do curso da formação
     * @param conclusao nova dada de conclusão do curso
     */
    public void setConclusao(LocalDate conclusao) {
        this.conclusao = conclusao;
    }

    /**
     * Altera a carga horaria do curso da formação
     * @param cargaHoraria nova carga horaria
     */
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    /**
     * Converte os principais dados da formação numa 'string'
     * @return uma 'string' com os principais dados da formação
     */
    @Override
    public String toString() {
        return nivel+" | "+curso+" | "+conclusao.getYear();
    }
}
