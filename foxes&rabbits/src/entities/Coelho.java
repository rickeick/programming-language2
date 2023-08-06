package entities;

import java.util.Random;

public class Coelho extends Animal {
    public Coelho(Posicao p, int idade) {
        super(p); this.idade = idade;
    }

    public Posicao correr() {
        int[] vetor = {-2, -1, 0, 1, 2};
        int i = vetor[(new Random()).nextInt(5)];
        int j = vetor[(new Random()).nextInt(5)];
        return new Posicao(posicao.x+i, posicao.y+j);
    }

    @Override
    public Posicao reproduzir() {
        if (idade == 24) {
            this.morrer();
            return null;
        }
        if (idade >= 12) {
            int[] vetor = {-1, 0, 1};
            int i = vetor[(new Random()).nextInt(3)];
            int j = vetor[(new Random()).nextInt(3)];
            return new Posicao(posicao.x+i, posicao.y+j);
        }
        return null;
    }
}
