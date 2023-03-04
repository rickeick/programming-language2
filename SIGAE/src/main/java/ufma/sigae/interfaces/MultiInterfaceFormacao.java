package ufma.sigae.interfaces;

import ufma.sigae.excecoes.CadastroException;
import ufma.sigae.entidades.Formacao;
import ufma.sigae.entidades.Egresso;
import ufma.sigae.servicos.Sigae;

import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import javax.swing.*;

/**
 * Classe que constrói interfaces gráficas para cadastro, visualização e edição de formações
 */
public class MultiInterfaceFormacao extends InterfaceGenerica implements MultiInterface {
    /** Egresso onde será cadastrado as formações */
    private final Egresso egresso;
    /** Campo para entrada da matriz curricular do curso */
    private final JTextField campoCurso;
    /** Campo para entrada do nível do curso */
    private final JTextField campoNivel;
    /** Campo para entrada da data de conclusão do curso */
    private final JTextField campoConclusao;
    /** Campo para entrada da carga horária total feita pelo egrsso no curso */
    private final JTextField campoCargaHoraria;

    /**
     * Cria uma interface gráfica base
     * @param sigae estado atual dos dados do SIGAE
     * @param egresso egresso onde será cadastrado as formações
     */
    public MultiInterfaceFormacao(Sigae sigae, Egresso egresso) {
        super(sigae);
        this.egresso = egresso;

        Box linha1 = Box.createHorizontalBox();
        JLabel labelCurso = new JLabel("Curso:");
        labelCurso.setVisible(true);
        linha1.add(labelCurso);
        linha1.add(Box.createRigidArea(tamanho));
        campoCurso = new JTextField(15);
        campoCurso.setToolTipText("Matriz curricular");
        campoCurso.setVisible(true);
        linha1.add(campoCurso);
        janela.add(linha1);

        Box linha2 = Box.createHorizontalBox();
        JLabel labelNivel = new JLabel("Nível:");
        labelNivel.setVisible(true);
        linha2.add(labelNivel);
        linha2.add(Box.createRigidArea(tamanho));
        campoNivel = new JTextField(15);
        campoNivel.setToolTipText("Nível do curso");
        campoNivel.setVisible(true);
        linha2.add(campoNivel);
        janela.add(linha2);

        Box linha3 = Box.createHorizontalBox();
        JLabel labelConclusao = new JLabel("Conclusão:");
        labelConclusao.setVisible(true);
        linha3.add(labelConclusao);
        linha3.add(Box.createRigidArea(tamanho));
        campoConclusao = new JTextField(15);
        campoConclusao.setToolTipText("Data de conclusão do curso [dd/MM/yyyy]");
        campoConclusao.setVisible(true);
        linha3.add(campoConclusao);
        janela.add(linha3);

        Box linha4 = Box.createHorizontalBox();
        JLabel labelCargaHoraria = new JLabel("Carga Horária:");
        labelCargaHoraria.setVisible(true);
        linha4.add(labelCargaHoraria);
        linha4.add(Box.createRigidArea(tamanho));
        campoCargaHoraria = new JTextField(15);
        campoCargaHoraria.setToolTipText("Carga horária feita no curso");
        campoCargaHoraria.setVisible(true);
        linha4.add(campoCargaHoraria);
        janela.add(linha4);
    }

    /**
     * Cria uma interface gráfica a partir da base para cadastro de formações
     */
    @Override
    public void paraCadastro() {
        JButton botaoAdicionar = new JButton("Adicionar Formação");
        botaoAdicionar.addActionListener(action -> {
            try {
                String curso = campoCurso.getText();
                String nivel = campoNivel.getText();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate conclusao = LocalDate.parse(campoConclusao.getText(), formato);
                int cargaHoraria = Integer.parseInt(campoCargaHoraria.getText());
                if (curso.equals("") || nivel.equals("") || cargaHoraria == 0) {
                    throw new CadastroException("Campo(s) obrigatório(s) não preenchido(s)!");
                }
                Formacao formacao = new Formacao(curso, nivel, conclusao, cargaHoraria);
                egresso.getFormacoes().add(formacao);
                campoCargaHoraria.setText("");
                campoConclusao.setText("");
                campoNivel.setText("");
                campoCurso.setText("");
            } catch (CadastroException e) {
                JOptionPane.showMessageDialog(janela, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                String mensagem = "Valor inválido no campo carga horária!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (DateTimeParseException e) {
                String mensagem = "Formato de data inválido no campo conclusão!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoAdicionar.setVisible(true);
        janela.add(botaoAdicionar);

        Box linha5 = Box.createHorizontalBox();
        JButton botaoContinuar = new JButton("Continuar");
        botaoContinuar.addActionListener(action -> {
            new MultiInterfaceOcupacao(sigae, egresso).paraCadastro();
            janela.dispose();
        });
        botaoContinuar.setVisible(true);
        linha5.add(botaoContinuar);
        linha5.add(Box.createRigidArea(tamanho));
        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(action -> {
            new MenuCoordenador(sigae);
            janela.dispose();
        });
        botaoCancelar.setVisible(true);
        linha5.add(botaoCancelar);
        janela.add(linha5);

        janela.setTitle("SIGAE - Cadastrar Formações");
        janela.setSize(320, 200);
        janela.setVisible(true);
    }

    /**
     * Cria uma interface gráfica a partir da base para visualização de formações
     * @param objeto formação para ser visualizada
     */
    @Override
    public void paraVisualizacao(Object objeto) {
        Formacao formacao = (Formacao)objeto;

        campoCurso.setText(formacao.getCurso());
        campoNivel.setText(formacao.getNivel());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        campoConclusao.setText(formacao.getConclusao().format(formato));
        campoCargaHoraria.setText(formacao.getCargaHoraria()+"");

        campoCurso.setEditable(false);
        campoNivel.setEditable(false);
        campoConclusao.setEditable(false);
        campoCargaHoraria.setEditable(false);

        JButton botaoFechar = new JButton("Fechar");
        botaoFechar.addActionListener(action -> {
            janela.dispose();
        });
        botaoFechar.setVisible(true);
        janela.add(botaoFechar);

        janela.setTitle("SIGAE - Visualizar Formação");
        janela.setSize(320, 170);
        janela.setVisible(true);
    }

    /**
     * Cria uma interface gráfica a partir da base para edição de formações
     * @param objeto formação para ser editado
     */
    @Override
    public void paraEdicao(Object objeto) {
        Formacao formacao = (Formacao)objeto;

        campoCurso.setText(formacao.getCurso());
        campoNivel.setText(formacao.getNivel());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (formacao.getConclusao() != null) {
            campoConclusao.setText(formacao.getConclusao().format(formato));
        } else {
            campoConclusao.setText("");
        }
        campoCargaHoraria.setText(formacao.getCargaHoraria()+"");

        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(action -> {
            try {
                String curso = campoCurso.getText();
                String nivel = campoNivel.getText();

                LocalDate conclusao = LocalDate.parse(campoConclusao.getText(), formato);
                int cargaHoraria = Integer.parseInt(campoCargaHoraria.getText());
                if (curso.equals("") || nivel.equals("") || cargaHoraria == 0) {
                    throw new CadastroException("Campo(s) obrigatório(s) não preenchido(s)!");
                }
                int indice = egresso.getFormacoes().indexOf(formacao);
                egresso.getFormacoes().get(indice).setCurso(curso);
                egresso.getFormacoes().get(indice).setNivel(nivel);
                egresso.getFormacoes().get(indice).setConclusao(conclusao);
                egresso.getFormacoes().get(indice).setCargaHoraria(cargaHoraria);
                new MultiInterfaceEgresso(sigae).paraEdicao(egresso);
                janela.dispose();
            } catch (CadastroException e) {
                JOptionPane.showMessageDialog(janela, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                String mensagem = "Valor inválido no campo carga horária!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (DateTimeParseException e) {
                String mensagem = "Formato de data inválido no campo conclusão!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoSalvar.setVisible(true);
        janela.add(botaoSalvar);

        JButton botaoApagar = new JButton("Apagar");
        botaoApagar.addActionListener(action -> {
            egresso.getFormacoes().remove(formacao);
            new MultiInterfaceEgresso(sigae).paraEdicao(egresso);
            janela.dispose();
        });
        botaoApagar.setVisible(true);
        janela.add(botaoApagar);

        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(action -> {
            new MultiInterfaceEgresso(sigae).paraEdicao(egresso);
            janela.dispose();
        });
        botaoCancelar.setVisible(true);
        janela.add(botaoCancelar);

        janela.setTitle("SIGAE - Editar Formação");
        janela.setSize(320, 200);
        janela.setVisible(true);
    }

    public void paraAdicionar() {
        JButton botaoAdicionar = new JButton("Adicionar Formação");
        botaoAdicionar.addActionListener(action -> {
            try {
                String curso = campoCurso.getText();
                String nivel = campoNivel.getText();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate conclusao = LocalDate.parse(campoConclusao.getText(), formato);
                int cargaHoraria = Integer.parseInt(campoCargaHoraria.getText());
                if (curso.equals("") || nivel.equals("") || cargaHoraria == 0) {
                    throw new CadastroException("Campo(s) obrigatório(s) não preenchido(s)!");
                }
                Formacao formacao = new Formacao(curso, nivel, conclusao, cargaHoraria);
                egresso.getFormacoes().add(formacao);
                new MultiInterfaceEgresso(sigae).paraEdicao(egresso);
                janela.dispose();
            } catch (CadastroException e) {
                JOptionPane.showMessageDialog(janela, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                String mensagem = "Valor inválido no campo carga horária!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (DateTimeParseException e) {
                String mensagem = "Formato de data inválido no campo conclusão!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoAdicionar.setVisible(true);
        janela.add(botaoAdicionar);

        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(action -> {
            new MenuCoordenador(sigae);
            janela.dispose();
        });
        botaoCancelar.setVisible(true);
        janela.add(botaoCancelar);

        janela.setTitle("SIGAE - Cadastrar Formações");
        janela.setSize(320, 170);
        janela.setVisible(true);
    }
}
