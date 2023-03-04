package ufma.sigae.excecoes;

/**
 * Representa uma exceção em tempo de execução que pode ocorrer quando um usuário tenta fazer ‘login’
 */
public class LoginException extends RuntimeException {
    /**
     * Criar uma exceção de ‘login’
     * @param mensagem mensagem com detalhes
     */
    public LoginException(String mensagem) {
        super(mensagem);
    }
}
