package entidades.itens;

public abstract class Item {
    protected String nome;
    protected int quantidade;

    public Item(String nome, int quantidade) {
        if (nome.equals("") || quantidade == 0) {
            throw new IllegalArgumentException("Valores Não Podem Ser Vazios");
        }
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public boolean isInstanceOf(Item item) {
        if (this instanceof Alimento && item instanceof Alimento) {
            return true;
        }
        else if (this instanceof Construcao && item instanceof Construcao) {
            return true;
        }
        else if (this instanceof Limpeza && item instanceof Limpeza) {
            return true;
        }
        else if (this instanceof Roupa && item instanceof Roupa) {
            return true;
        }
        else {
            return false;
        }
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return "Nome:" + nome + " | Quantidade:" + quantidade;
    }
}
