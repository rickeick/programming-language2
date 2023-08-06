import java.util.Random;

import entities.*;

public class Simulador {
    int tamanho;
    Animal[][] campo;
    int raposas, coelhos;

    public Simulador(int t, int r, int c) {
        int idade, x, y;
        Posicao posicao;
        Random gerador = new Random();
        campo = new Animal[t][t]; tamanho = t;
        while (c > 0) {
            idade = gerador.nextInt(13);
            x = gerador.nextInt(tamanho);
            y = gerador.nextInt(tamanho);
            posicao = new Posicao(x, y);
            if (campo[posicao.x][posicao.y] == null) {
                campo[posicao.x][posicao.y] = new Coelho(posicao, idade);
                c--;
            }
        }
        while (r > 0) {
            idade = gerador.nextInt(25);
            x = gerador.nextInt(tamanho);
            y = gerador.nextInt(tamanho);
            posicao = new Posicao(x, y);
            if (campo[posicao.x][posicao.y] == null) {
                campo[posicao.x][posicao.y] = new Raposa(posicao, idade);
                r--;
            }
        }
    }

    public void executar() {
        Posicao posicao;
        for (Animal[] linha : campo) {
            for (Animal animal : linha) {
                if (animal == null) {
                    continue;
                }
                if (!animal.estaVivo()) {
                    continue;
                }
                if (animal instanceof Coelho) {
                    for (int i=0; i<10; i++) {
                        posicao = ((Coelho)animal).correr();
                        if (posicao.isValido(tamanho)) {
                            if (campo[posicao.x][posicao.y] == null) {
                                campo[posicao.x][posicao.y] = animal;
                                animal.mover(posicao);
                                break;
                            }
                        }
                    }
                    for (int i=0; i<10; i++) {
                        posicao = animal.reproduzir();
                        if (posicao == null) {break;}
                        if (posicao.isValido(tamanho)) {
                            if (campo[posicao.x][posicao.y] == null) {
                                campo[posicao.x][posicao.y] = new Coelho(posicao, 1);
                                coelhos++;
                                break;
                            }
                        }
                    }
                }
                if (animal instanceof Raposa) {
                    Posicao[] posicoes; boolean comeu = false;
                    posicoes = ((Raposa)animal).buscar();
                    for (Posicao p : posicoes) {
                        if (p != null) {
                            if (p.isValido(tamanho)) {
                                if (campo[p.x][p.y] instanceof Coelho) {
                                    campo[p.x][p.y] = animal;
                                    animal.mover(p);
                                    comeu = true;
                                }
                            }
                        }
                    }
                    ((Raposa)animal).setFome(comeu);
                    for (int i=0; i<10; i++) {
                        posicao = animal.reproduzir();
                        if (posicao == null) {break;}
                        if (posicao.isValido(tamanho)) {
                            if (campo[posicao.x][posicao.y] == null) {
                                campo[posicao.x][posicao.y] = new Raposa(posicao, 1);
                                break;
                            }
                        }
                    }
                }
                animal.envelhecer();
            }
        }
    }

    @Override
    public String toString() {
        coelhos = 0;
        raposas = 0;
        String string = "";
        for (Animal[] linha : campo) {
            for (Animal animal : linha) {
                if (animal == null) {
                    string += "[ ]";
                } else if (!animal.estaVivo()) {
                    string += "[ ]";
                } else if (animal instanceof Coelho) {
                    string += "[C]";
                    coelhos++;
                } else if (animal instanceof Raposa) {
                    string += "[R]";
                    raposas++;
                }
            }
            string += "\n";
        }
        string += "| Coelhos: "+coelhos+" | "+"Raposas: "+raposas+" |\n";
        return string;
    }
}
