package ufma.sigae.excecoes;

/**
 * Representa exceções em tempo de execução que pode ocorrer ao tentar cadastrar uma entidade qualquer
 */
public class CadastroException extends RuntimeException {
    /**
     * Criar uma exceção de cadastro
     * @param mensagem mensagem com detalhes
     */
    public CadastroException(String mensagem) {
        super(mensagem);
    }
}
