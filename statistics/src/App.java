import java.io.IOException;
import java.util.Hashtable;

public class App {
    public static void main(String[] args) {
        String selecionado;
        int ID2020 = 6886, ID2022 = 7646;

        try {
            Relacao clubeXcartoes = new Relacao("files/estatisticas-full.csv", "clube", "cartao_amarelo", ID2020);
            selecionado = clubeXcartoes.pegarChaveMaiorValor();
            int cartoes = clubeXcartoes.relacao().get(selecionado);
            System.out.println("Clube que teve mais cartões amarelos entre 2020 e 2022:");
            System.out.println(selecionado + " com " + cartoes + " cartões amarelos!\n");

            Relacao clubeXfaltas = new Relacao("files/estatisticas-full.csv", "clube", "faltas", ID2022);
            selecionado = clubeXfaltas.pegarChaveMaiorValor();
            int faltas = clubeXfaltas.relacao().get(selecionado);
            System.out.println("Clube com mais faltas de 2022:");
            System.out.println(selecionado + " com " + faltas + " faltas marcadas!\n");

            Relacao clubeXprecisao = new Relacao("files/estatisticas-full.csv", "clube", "precisao_passes", ID2022);
            selecionado = clubeXprecisao.pegarChaveMaiorValor();
            int precisao = clubeXprecisao.relacao().get(selecionado);
            System.out.println("Clube com mais precisão de 2022:");
            System.out.println(selecionado + " com " + precisao/38 + " porcento de média por partida!\n");

            Relacao arenaXplacar1 = new Relacao("files/full.csv", "arena", "mandante_Placar", ID2020);
            Relacao arenaXplacar2 = new Relacao("files/full.csv", "arena", "visitante_Placar", ID2020);
            Hashtable<String, Integer> golsTotal = new Hashtable<>();
            int valor1, valor2;
            for (String key : arenaXplacar1.relacao().keySet()) {
                valor1 = arenaXplacar1.relacao().get(key);
                valor2 = arenaXplacar2.relacao().get(key);
                golsTotal.put(key, valor1 + valor2);
            }
            Relacao arenaXgols = new Relacao(golsTotal);
            System.out.println("As 10 arenas que tiveram mais gols registrados entre 2020 e 2022:");
            for (int i=1; i<11; i++) {
                selecionado = arenaXgols.pegarChaveMaiorValor();
                int gols = arenaXgols.relacao().get(selecionado);
                System.out.println(i + "º: " + selecionado + " com " + gols + " gols!");
                arenaXgols.relacao().remove(selecionado, gols);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
