package ufma.sigae.servicos;

import java.io.IOException;
import java.util.Hashtable;

/**
 * Classe que representa o estado atual dos dados do SIGAE
 */
public class Sigae {
    /** Usuário público atualmente logado no sistema */
    public Usuario usuario;
    /** Hashtable público (login, usuário) com todos os usuários cadastrados no SIGAE */
    public Hashtable<String, Usuario> usuarios;

    /**
     * Inicia o SIGAE carregando os dados dos usuários e sem nenhum usuário logado
     * @throws IOException se não for possível acessar os dados dos usuários
     * @throws ClassNotFoundException se as classes não forem encontradas
     */
    public Sigae() throws IOException, ClassNotFoundException {
        usuarios = Parser.carregar();
        usuario = null;
    }
}
