import java.util.Arrays;

public class Maquina {
    boolean[] vagas;

    public Maquina(int qtdVagas) {
        vagas = new boolean[qtdVagas];
        Arrays.fill(vagas, true);
    }

    public int emitir() {
        int ticket = 0;
        while ((ticket < vagas.length) && (!vagas[ticket])) {
            ticket++;
        }
        if (ticket < vagas.length) {
            vagas[ticket] = false;
            return ticket;
        }
        return -1;
    }

    public boolean liberar(int numero) {
        if (numero >= vagas.length) {
            return false;
        }
        if (vagas[numero]) {
            return false;
        }
        vagas[numero] = true;
        return true;
    }
}
