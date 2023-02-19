package controles;

import entidades.Campanha;
import entidades.Doacao;
import entidades.Doador;
import entidades.Entidade;
import entidades.itens.Item;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ONG {
    private ArrayList<Doador> doadores;
    private ArrayList<Doacao> doacoes;
    private ArrayList<Campanha> campanhas;

    public ONG(ArrayList<Doador> doadores, ArrayList<Doacao> doacoes, ArrayList<Campanha> campanhas) {
        this.doadores = doadores;
        this.doacoes = doacoes;
        this.campanhas = campanhas;
    }

    public void cadDoador(String nome, String id) {
        doadores.add(new Doador(nome, id));
    }

    public void cadDoacao(Doador doador, Item item) {
        int indice = -1; long diasAntes, diasMenor = 366;
        Doacao doacao = new Doacao(doador, item);
        for (int i=0; i<campanhas.size(); i++) {
            if (campanhas.get(i).isTypeOf(item)) {
                diasAntes = ChronoUnit.DAYS.between(LocalDate.now(), campanhas.get(i).getPrazo());
                if (diasAntes < diasMenor) {diasMenor = diasAntes; indice = i;}
            }
        }
        if (indice > -1) {
            Campanha escolhida = campanhas.get(indice);
            for (Item i : escolhida.getItens()) {
                if (i.getNome().equals(item.getNome())) {
                    if (escolhida.totalArrecado(item) < i.getQuantidade()) {
                        if (escolhida.getAndamento() < 100) {
                            escolhida.getArecadacoes().add(doacao);
                            doacao.setStatus(true);
                        }
                    }
                }
            }
        }
        doacoes.add(doacao);
    }

    public void cadCampanha(String titulo, ArrayList<Item> itens, Entidade entidade, LocalDate prazo) {
        campanhas.add(new Campanha(titulo, itens, entidade, prazo));
    }

    public void fecharCampanha(Campanha campanha) {
        ArrayList<Doacao> lista = new ArrayList<>(doacoes);
        for (Doacao d1 : lista) {
            for (Doacao d2 : campanha.getArecadacoes()) {
                if (d1.getItem().getNome().equals(d2.getItem().getNome())) {
                    if (d1.getItem().getQuantidade() == d2.getItem().getQuantidade()) {
                        doacoes.remove(d1);
                    }
                }
            }
        }
        campanhas.remove(campanha);
    }

    public void atualizarDoacoes() {
        int indice = -1; long diasAntes, diasMenor = 366;
        for (Doacao doacao : doacoes) {
            if (!doacao.getStatus()) {
                for (int i=0; i<campanhas.size(); i++) {
                    if (campanhas.get(i).isTypeOf(doacao.getItem())) {
                        diasAntes = ChronoUnit.DAYS.between(LocalDate.now(), campanhas.get(i).getPrazo());
                        if (diasAntes < diasMenor) {diasMenor = diasAntes; indice = i;}
                    }
                }
                if (indice > -1) {
                    Campanha escolhida = campanhas.get(indice);
                    for (Item i : escolhida.getItens()) {
                        if (i.getNome().equals(doacao.getItem().getNome())) {
                            if (escolhida.totalArrecado(doacao.getItem()) < i.getQuantidade()) {
                                if (escolhida.getAndamento() < 100) {
                                    escolhida.getArecadacoes().add(doacao);
                                    doacao.setStatus(true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Doador> getDoadores() {
        return doadores;
    }

    public ArrayList<Doacao> getDoacoes() {
        return doacoes;
    }

    public ArrayList<Campanha> getCampanhas() {
        return campanhas;
    }
}
