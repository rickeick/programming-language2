package entities;

import java.util.Random;

public class Raposa extends Animal {
    private int fome;

    public Raposa(Posicao p, int idade) {
        super(p); this.idade = idade; fome = 0;
    }

    public Posicao[] buscar() {
        int count = 0;
        int x = posicao.x;
        int y = posicao.y;
        Posicao[] posicoes = new Posicao[8];
        for (int i=-1; i<2; i++, count++) {
            for (int j=-1; j<2; j++) {
                if (i == 0 && j == 0) {continue;}
                posicoes[count] = new Posicao(x+i, y+j);
            }
        }
        return posicoes;
    }

    @Override
    public Posicao reproduzir() {
        if (idade == 48) {
            this.morrer();
            return null;
        }
        if (idade != 0 && idade % 12 == 0) {
            int i, j; int[] vetor = {-1, 0, 1};
            i = vetor[(new Random()).nextInt(3)];
            j = vetor[(new Random()).nextInt(3)];
            return new Posicao(posicao.x+i, posicao.y+j);
        }
        return null;
    }

    public void setFome(boolean comeu) {
        if (comeu) {
            fome++;
        } else {
            fome--;
        }
        if (fome < -1) {
            this.morrer();
        }
    }
}
