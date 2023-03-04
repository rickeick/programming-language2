package ufma.sigae.interfaces;

import ufma.sigae.servicos.Parser;
import ufma.sigae.servicos.Sigae;

import java.io.IOException;
import javax.swing.*;

/**
 * Classe que constrói uma interface gráfica com o menu do administrador
 */
public class MenuAdministrador extends InterfaceGenerica {
    /**
     * Cria uma interface gráfica com o menu do administrador
     * @param sigae estado atual dos dados do SIGAE
     */
    public MenuAdministrador(Sigae sigae) {
        super(sigae);

        JButton botaoCadCoordenador = new JButton("Cadastrar Coordenador");
        botaoCadCoordenador.addActionListener(action -> {
            new CadastroCoordenador(sigae);
            janela.dispose();
        });
        botaoCadCoordenador.setVisible(true);
        janela.add(botaoCadCoordenador);

        JButton botaoListarCoordenadores = new JButton("Listar Coordenadores");
        botaoListarCoordenadores.addActionListener(action -> {
            if (sigae.usuarios.size() > 1) {
                new ListaCoordenador(sigae);
                janela.dispose();
            } else {
                JOptionPane.showMessageDialog(janela, "Nenhum coordenador cadastrado!");
            }
        });
        botaoListarCoordenadores.setVisible(true);
        janela.add(botaoListarCoordenadores);

        JButton botaoLogout = new JButton("Logout do Sistema");
        botaoLogout.addActionListener(action -> {
            try {
                Parser.salvar(sigae.usuarios);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(janela, "Não foi possível salvar os dados!");
            } finally {
                new InterfaceLogin(sigae);
                janela.dispose();
            }
        });
        botaoLogout.setVisible(true);
        janela.add(botaoLogout);

        janela.setTitle("SIGAE - Menu do Administrador");
        janela.setSize(250, 140);
        janela.setVisible(true);
    }
}
