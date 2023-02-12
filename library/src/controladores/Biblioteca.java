package controladores;

import entidades.Usuario;
import entidades.Material;

import java.time.LocalDate;
import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Material> acervo;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Emprestimo> emprestimos;

    public Biblioteca(ArrayList<Material> a, ArrayList<Usuario> u, ArrayList<Emprestimo> e) {
        this.acervo = a;
        this.usuarios = u;
        this.emprestimos = e;
    }

    public Usuario entrar(String login, String senha) {
        for (Usuario u : usuarios) {
            if (login.equals(u.getLogin())) {
                if (senha.equals(u.getSenha())) {
                    return new Usuario(login, senha);
                }
            }
        }
        return null;
    }

    public Usuario registrar(String login, String senha) {
        boolean existe = false;
        for (Usuario u : usuarios) {
            if (login.equals(u.getLogin())) {
                existe = true;
                break;
            }
        }
        if (!existe) {
            Usuario usuario = new Usuario(login, senha);
            usuarios.add(usuario);
            return usuario;
        }
        else {
            return null;
        }
    }

    public void emprestar(Material material, Usuario usuario) {
        acervo.remove(material);
        LocalDate data = LocalDate.now();
        LocalDate devolucao = data.plusMonths(1);
        Emprestimo emprestimo = new Emprestimo(material, usuario, data, devolucao);
        emprestimos.add(emprestimo);
    }

    public void devolver(Material material, Usuario usuario) {
        acervo.add(material);
        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().getLogin().equals(usuario.getLogin())) {
                if (e.getMaterial().getTitulo().equals(material.getTitulo())) {
                    LocalDate data = e.getData();
                    LocalDate devolucao = e.getDevolucao();
                    emprestimos.remove(new Emprestimo(material, usuario, data, devolucao));
                }
            }
        }
    }

    public Material consultar(String titulo) {
        for (Material material : acervo) {
            if (material.getTitulo().equals(titulo)) {
                return material;
            }
        }
        return null;
    }

    public ArrayList<Material> getAcervo() {
        return acervo;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
}
