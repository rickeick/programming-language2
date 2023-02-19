package entidades;

import entidades.itens.Item;

public class Doacao {
    private Item item;
    private Doador doador;
    private boolean status;

    public Doacao(Doador doador, Item item) {
        this.item = item;
        this.doador = doador;
        status = false;
    }

    public Item getItem() {
        return item;
    }

    public Doador getDoador() {
        return doador;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return item + " | Doador: " + doador + " | EmUso: " + status;
    }
}
