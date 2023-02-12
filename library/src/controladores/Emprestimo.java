package controladores;

import entidades.Usuario;
import entidades.Material;

import java.time.LocalDate;

public class Emprestimo {
    private Material material;
    private Usuario usuario;
    private LocalDate data;
    private LocalDate devolucao;

    public Emprestimo(Material material, Usuario usuario, LocalDate data, LocalDate devolucao) {
        this.material = material;
        this.usuario = usuario;
        this.data = data;
        this.devolucao = devolucao;
    }

    public Material getMaterial() {
        return material;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalDate getDevolucao() {
        return devolucao;
    }
}
