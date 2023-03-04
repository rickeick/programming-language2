package ufma.sigae.entidades;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Representa um curso superior da UFMA
 */
public class Curso implements Serializable {
    /** Matriz curricular do curso */
    private final String matriz;
    /** Centro local do curso */
    private final String centro;
    /** Carga horária total do curso */
    private final int cargaHoraria;
    /** Mapa (cpf, egresso) de egressos do curso */
    private final Hashtable<String,Egresso> egressos;

    /**
     * Cria um curso com nome oferecido num centro e com carga horária
     * @param matriz matriz curricular do curso
     * @param centro centro local do curso
     * @param cargaHoraria carga horária total do curso
     */
    public Curso(String matriz, String centro, int cargaHoraria) {
        this.matriz = matriz;
        this.centro = centro;
        this.cargaHoraria = cargaHoraria;
        this.egressos = new Hashtable<>();
    }

    /**
     * Recupera a matriz curricular do curso
     * @return a matriz curricular do curso
     */
    public String getMatriz() {
        return matriz;
    }

    /**
     * Recupera o centro local do curso
     * @return o centro local do curso
     */
    public String getCentro() {
        return centro;
    }

    /**
     * Recupera a carga horaria do curso
     * @return a carga horaria do curso
     */
    public int getCargaHoraria() {
        return cargaHoraria;
    }

    /**
     * Recupera o mapa (cpf, egresso) do curso
     * @return o mapa (cpf, egresso) do curso
     */
    public Hashtable<String, Egresso> getEgressos() {
        return egressos;
    }
}
