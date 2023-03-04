package ufma.sigae.relatorios;

/**
 * Define os métodos que uma Classe que gera um relatório deve implementar
 */
public interface Relatorio {
    /**
     * Gera o cabeçalho do relatório
     */
    void gerarCabecalho();

    /**
     * Gera o corpo do relatório
     */
    void gerarCorpo();

    /**
     * Salva e gera o arquivo pdf
     */
    void imprimir();
}
