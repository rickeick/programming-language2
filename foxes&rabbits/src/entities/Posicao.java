package entities;

public class Posicao {
    public int x, y;

    public Posicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isValido(int max) {
        return (x >= 0 && x < max) && (y >= 0 && y < max);
    }
}
