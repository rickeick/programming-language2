package ufma.sigae.interfaces;

import javax.swing.*;

public class Creditos {
    public Creditos() {
        JFrame janela = new JFrame("SIGAE - Créditos");
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setSize(240, 210);

        JPanel painel = new JPanel();

        JLabel rotulo1 = new JLabel("UFMA - SIGAE");
        rotulo1.setVisible(true);
        painel.add(rotulo1);

        JLabel rotulo2 = new JLabel("Linguagem de Programação II");
        rotulo2.setVisible(true);
        painel.add(rotulo2);

        JLabel rotulo3 = new JLabel("Professor: Geraldo Braz Júnior");
        rotulo3.setVisible(true);
        painel.add(rotulo3);

        JLabel rotulo4 = new JLabel("Desenvolvido por:");
        rotulo4.setVisible(true);
        painel.add(rotulo4);

        JLabel rotulo5 = new JLabel("Rick Eick Vieira Dos Santos");
        rotulo5.setVisible(true);
        painel.add(rotulo5);

        JLabel rotulo6 = new JLabel("Pedro Lima de Assunção Filho");
        rotulo6.setVisible(true);
        painel.add(rotulo6);

        JLabel rotulo7 = new JLabel("Version: 3.0 of 2022.2");
        rotulo7.setVisible(true);
        painel.add(rotulo7);

        JLabel rotulo8 = new JLabel("Powered: Java | Swing | OpenPDF");
        rotulo8.setVisible(true);
        painel.add(rotulo8);

        janela.add(painel);
        janela.setVisible(true);
    }
}
