import java.util.Hashtable;
import java.util.LinkedList;

public class Agenda {
    private final Hashtable<String, LinkedList<Contato>> agenda;

    public Agenda() {
        agenda = new Hashtable<>();
    }

    public void inserir(Contato contato) {
        LinkedList<Contato> contatos = agenda.get(contato.getNome());
        if (contatos != null) {
            contatos.add(contato);
        } else {
            LinkedList<Contato> lista = new LinkedList<>();
            lista.add(contato);
            agenda.put(contato.getNome(), lista);
        }
    }

    public LinkedList<Contato> buscar(String nome) {
        return agenda.get(nome);
    }

    public Hashtable<String, LinkedList<Contato>> getAgenda() {
        return agenda;
    }
}
