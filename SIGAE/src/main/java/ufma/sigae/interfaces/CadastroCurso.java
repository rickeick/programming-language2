package ufma.sigae.interfaces;

import ufma.sigae.entidades.Curso;
import ufma.sigae.excecoes.CadastroException;
import ufma.sigae.servicos.Coordenador;
import ufma.sigae.servicos.Sigae;

import javax.swing.*;

/**
 * Classe que constrói uma interface gráfica para cadastro de cursos
 */
public class CadastroCurso extends InterfaceGenerica {
    /**
     * Cria uma interface gráfica para cadastro de cursos
     * @param sigae estado atual dos dados do SIGAE
     */
    public CadastroCurso(Sigae sigae) {
        super(sigae);

        Box linha1 = Box.createHorizontalBox();
        JLabel rotuloMatriz = new JLabel("Matriz:");
        rotuloMatriz.setVisible(true);
        linha1.add(rotuloMatriz);
        linha1.add(Box.createRigidArea(tamanho));
        JTextField campoMatriz = new JTextField(15);
        campoMatriz.setToolTipText("Matriz curricular do curso");
        campoMatriz.setVisible(true);
        linha1.add(campoMatriz);
        janela.add(linha1);

        Box linha2 = Box.createHorizontalBox();
        JLabel rotuloCentro = new JLabel("Centro:");
        rotuloCentro.setVisible(true);
        linha2.add(rotuloCentro);
        linha2.add(Box.createRigidArea(tamanho));
        JTextField campoCentro = new JTextField(15);
        campoCentro.setToolTipText("Centro local do curso");
        campoCentro.setVisible(true);
        linha2.add(campoCentro);
        janela.add(linha2);

        Box linha3 = Box.createHorizontalBox();
        JLabel rotuloCargaHoraria = new JLabel("Carga Horária:");
        rotuloCargaHoraria.setVisible(true);
        linha3.add(rotuloCargaHoraria);
        linha3.add(Box.createRigidArea(tamanho));
        JTextField campoCargaHoraria = new JTextField(15);
        campoCargaHoraria.setToolTipText("Carga horária total");
        campoCargaHoraria.setVisible(true);
        linha3.add(campoCargaHoraria);
        janela.add(linha3);

        Box linha4 = Box.createHorizontalBox();
        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(action -> {
            try {
                String matriz = campoMatriz.getText();
                String centro = campoCentro.getText();
                int cargaHoraria = Integer.parseInt(campoCargaHoraria.getText());
                Curso curso = new Curso(matriz, centro, cargaHoraria);
                ((Coordenador)sigae.usuario).cadastrarCurso(curso);
                new MenuCoordenador(sigae);
                janela.dispose();
            } catch (NumberFormatException e) {
                String mensagem = "Valor inválido no campo carga horária";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (CadastroException e) {
                JOptionPane.showMessageDialog(janela, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoCadastrar.setVisible(true);
        linha4.add(botaoCadastrar);
        linha4.add(Box.createRigidArea(tamanho));
        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(action -> {
            new MenuCoordenador(sigae);
            janela.dispose();
        });
        botaoCancelar.setVisible(true);
        linha4.add(botaoCancelar);
        janela.add(linha4);

        janela.setTitle("SIGAE - Cadastrar Curso");
        janela.setSize(300, 150);
        janela.setVisible(true);
    }
}
