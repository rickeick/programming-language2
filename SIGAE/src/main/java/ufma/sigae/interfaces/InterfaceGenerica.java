package ufma.sigae.interfaces;

import ufma.sigae.servicos.Parser;
import ufma.sigae.servicos.Sigae;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.*;

/**
 * Classe que representa uma interface gráfica genérica do SIGAE
 */
public abstract class InterfaceGenerica {
    /** Estado atual dos dados do SIGAE */
    protected final Sigae sigae;
    /** Janela principal da interface gráfica */
    protected final JFrame janela;
    /** Determina o tamanho do separador de widgets numa linha */
    protected final Dimension tamanho = new Dimension(10,0);

    /**
     * Cria uma interface gráfica genérica do SIGAE
     * @param sigae estado atual dos dados do SIGAE
     */
    public InterfaceGenerica(Sigae sigae) {
        this.sigae = sigae;
        janela = new JFrame();
        janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        janela.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                int opcao = JOptionPane.showConfirmDialog(janela, "Deseja sair?");
                if (opcao == JOptionPane.OK_OPTION) {
                    try {
                        Parser.salvar(sigae.usuarios);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(janela, "Não foi possível salvar os dados!");
                    } finally {
                        janela.dispose();
                    }
                }
            }
        });
        janela.setLocationRelativeTo(null);
        janela.setLayout(new FlowLayout());
    }
}
