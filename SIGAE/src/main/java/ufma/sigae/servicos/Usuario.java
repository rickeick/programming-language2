package ufma.sigae.servicos;

import ufma.sigae.excecoes.LoginException;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Representa um usuário genérico dentro do SIGAE
 */
public abstract class Usuario implements Serializable {
    /** Login do usuário */
    protected String login;
    /** Senha do usuário */
    protected String senha;

    /**
     * Cria um usuário genérico com login e senha
     * @param login login do usuário
     * @param senha senha do usuário
     */
    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    /**
     * Busca e retorna o usuário com login e senha no hashtable usuarios
     * @param login login do usuario a ser buscado no hashtable
     * @param senha senha do usuario a ser buscado no hashtable
     * @param usuarios hashtable para buscar o usuario
     * @return o usuario se ele for encotrado e a senha conferir
     * @throws LoginException se o login não for encotrado ou se a senha não conferir
     */
    public static Usuario entrar(String login, String senha, Hashtable<String,Usuario> usuarios) {
        Usuario usuario = usuarios.get(login);
        if (usuario != null) {
            if (usuario.senha.equals(senha)) {
                return usuario;
            } else {
                throw new LoginException("Senha Incorreta!");
            }
        } else {
            throw new LoginException("Usuário Não existe!");
        }
    }

    /**
     * Recupera o login do usuario
     * @return o login do usuario
     */
    public String getLogin() {
        return login;
    }

    /**
     * Recupera a senha do usuario
     * @return a senha do usuario
     */
    public String getSenha() {
        return senha;
    }
}
