import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Hashtable;

public class Relacao {
    private final Hashtable<String, Integer> relacao;

    public Relacao(Hashtable<String, Integer> hashtable) {
        relacao = hashtable;
    }

    public Relacao(String pathFile, String campoKey, String campoValue, int initID) throws IOException {
        relacao = new Hashtable<>();
        String linha; String[] campos, valores;
        FileReader arquivo = new FileReader(pathFile);
        BufferedReader leitor = new BufferedReader(arquivo);
        Hashtable<String, String> dados = new Hashtable<>();
        linha = leitor.readLine();
        campos = linha.replaceAll("\"", "").split(",");
        while ((linha = leitor.readLine()) != null) {
            valores = linha.replaceAll("\"", "").split(",");
            int id = Integer.parseInt(valores[0]);
            if (id >= initID) {
                for (int i=0; i<valores.length; i++) {
                    dados.put(campos[i], valores[i]);
                }
                String key = dados.get(campoKey); int value;
                try {
                    value = Integer.parseInt(dados.get(campoValue).replaceAll("%",""));
                } catch (NumberFormatException e) {
                    value = 0;
                }
                if (relacao.get(key) != null) {
                    value += relacao.get(key);
                    relacao.put(key, value);
                } else {
                    relacao.put(key, value);
                }
            }
        }
        leitor.close();
    }

    public String pegarChaveMaiorValor() {
        String selecionado = null; float maior = 0;
        for (String key : relacao.keySet()) {
            if (relacao.get(key) > maior) {
                maior = relacao.get(key);
                selecionado = key;
            }
        }
        return selecionado;
    }

    public Hashtable<String, Integer> relacao() {
        return relacao;
    }
}
