package ufma.sigae.interfaces;

import ufma.sigae.servicos.Coordenador;
import ufma.sigae.servicos.Usuario;
import ufma.sigae.servicos.Sigae;

import javax.swing.*;

public class ListaCoordenador extends InterfaceGenerica {
    public ListaCoordenador(Sigae sigae) {
        super(sigae);

        DefaultListModel<String> coordenadores = new DefaultListModel<>();
        for (String key : sigae.usuarios.keySet()) {
            Usuario usuario = sigae.usuarios.get(key);
            if (usuario instanceof Coordenador) {
                coordenadores.addElement(usuario.toString());
            }
        }
        JLabel rotuloCoordenadores = new JLabel("Lista de Coordenadores:");
        rotuloCoordenadores.setVisible(true);
        janela.add(rotuloCoordenadores);
        JLabel rotuloCabecalho = new JLabel("Nome | Matriz Curricular | Centro");
        rotuloCabecalho.setVisible(true);
        janela.add(rotuloCabecalho);
        JList<String> listaCoordenadores = new JList<>(coordenadores);
        listaCoordenadores.setVisible(true);
        janela.add(listaCoordenadores);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(action -> {
            new MenuAdministrador(sigae);
            janela.dispose();
        });
        botaoVoltar.setVisible(true);
        janela.add(botaoVoltar);

        janela.setTitle("SIGAE - Lista de Coordenadores");
        janela.setSize(400, 150);
        janela.setVisible(true);
    }
}
