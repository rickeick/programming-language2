package entidades;

import entidades.itens.*;

import java.util.ArrayList;
import java.time.LocalDate;

public class Campanha {
    private String titulo;
    private Entidade entidade;
    private ArrayList<Item> itens;
    private ArrayList<Doacao> arecadacoes;
    private LocalDate prazo;
    private double andamento;

    public Campanha(String titulo, ArrayList<Item> itens, Entidade entidade, LocalDate prazo) {
        this.titulo = titulo;
        this.itens = itens;
        this.entidade = entidade;
        this.prazo = prazo;
        arecadacoes = new ArrayList<>();
        andamento = 0.0;
    }

    public boolean isTypeOf(Item item) {
        if (item instanceof Alimento && itens.get(0) instanceof Alimento) {return true;}
        else if (item instanceof Construcao && itens.get(0) instanceof Construcao) {return true;}
        else if (item instanceof Limpeza && itens.get(0) instanceof Limpeza) {return true;}
        else if (item instanceof Roupa && itens.get(0) instanceof Roupa) {return true;}
        else {return false;}
    }

    public void updtAndamento() {
        double totalDoado = 0, totalCamp = 0;
        for (Doacao doacao : arecadacoes) {
            totalDoado += doacao.getItem().getQuantidade();
        }
        for (Item item : itens) {
            totalCamp += item.getQuantidade();
        }
        andamento = (totalDoado/totalCamp)*100;
    }

    public int totalArrecado(Item item) {
        int total = 0;
        for (Doacao doacao : arecadacoes) {
            if (doacao.getItem().getNome().equals(item.getNome())) {
                total += doacao.getItem().getQuantidade();
            }
        }
        return total;
    }

    public String getTitulo() {
        return titulo;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public ArrayList<Doacao> getArecadacoes() {
        return arecadacoes;
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public double getAndamento() {
        return andamento;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }
}
