package ufma.sigae.servicos;

import ufma.sigae.excecoes.CadastroException;

import java.util.Hashtable;

/**
 * Representa um usuário coma administrador dentro do SIGAE
 */
public class Administrador extends Usuario {
    /**
     * Cria um usuário como administrador com login e senha
     * @param login login do administrador
     * @param senha senha do administrador
     */
    public Administrador(String login, String senha) {
        super(login, senha);
    }

    /**
     * Cadastra um coordenador no hashtable usuarios
     * @param coordenador coordenador para ser cadastrado
     * @param usuarios hashtable usuarios
     * @throws CadastroException se o login já estive em uso por outro coordenador
     */
    public void cadCoordenador(Coordenador coordenador, Hashtable<String,Usuario> usuarios) {
        if (usuarios.get(coordenador.getLogin()) != null) {
            throw new CadastroException("Login já está em uso!");
        }
        if (!coordenador.getLogin().equals("")) {
            if (!coordenador.getSenha().equals("")) {
                if (!coordenador.getNome().equals("")) {
                    usuarios.put(coordenador.getLogin(), coordenador); return;
                }
            }
        }
        throw new CadastroException("Campo(s) obrigatório(s) não preenchido(s)!");
    }
}
