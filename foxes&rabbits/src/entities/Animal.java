package entities;

public abstract class Animal {
    protected int idade;
    protected boolean vivo;
    protected Posicao posicao;

    public Animal(Posicao p) {
        vivo = true;
        posicao = p;
    }

    public void morrer() {
        vivo = false;
    }

    public boolean estaVivo() {
        return vivo;
    }

    public void envelhecer() {
        idade++;
    }

    public void mover(Posicao posicao) {
        this.posicao = posicao;
    }

    public abstract Posicao reproduzir();
}
