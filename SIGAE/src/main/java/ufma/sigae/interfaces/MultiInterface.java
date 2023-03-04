package ufma.sigae.interfaces;

/**
 * Define os métodos para uma Classe multi interface gráfica para cadastro, visualização e edição
 */
public interface MultiInterface {
    /**
     * Implementa uma interface gráfica para cadastro de um dado
     */
    void paraCadastro();

    /**
     * Implementa uma interface gráfica para visualização de um dado
     * @param objeto dado para ser visualizado
     */
    void paraVisualizacao(Object objeto);

    /**
     * Implementa uma interface gráfica para edição de um dado
     * @param objeto dado para ser editado
     */
    void paraEdicao(Object objeto);
}
