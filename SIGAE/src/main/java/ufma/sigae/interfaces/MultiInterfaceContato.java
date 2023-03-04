package ufma.sigae.interfaces;

import ufma.sigae.excecoes.CadastroException;
import ufma.sigae.entidades.Egresso;
import ufma.sigae.entidades.Contato;
import ufma.sigae.servicos.Sigae;

import javax.swing.*;

/**
 * Classe que constrói interfaces gráficas para cadastro, visualização e edição de contatos
 */
public class MultiInterfaceContato extends InterfaceGenerica implements MultiInterface {
    /** Egresso onde será cadastrado os contatos */
    private final Egresso egresso;
    /** Campo para entrada do dado do contato */
    private final JTextField campoContato;
    /** Campo para entrada do tipo do contato */
    private final JTextField campoTipo;

    /**
     * Cria uma interface gráfica base
     * @param sigae estado atual dos dados do SIGAE
     * @param egresso egresso onde será cadastrado os contatos
     */
    public MultiInterfaceContato(Sigae sigae, Egresso egresso) {
        super(sigae);
        this.egresso = egresso;

        Box linha1 = Box.createHorizontalBox();
        JLabel rotuloContato = new JLabel("Contato:");
        rotuloContato.setVisible(true);
        linha1.add(rotuloContato);
        linha1.add(Box.createRigidArea(tamanho));
        campoContato = new JTextField(15);
        campoContato.setToolTipText("Dados do contato");
        campoContato.setVisible(true);
        linha1.add(campoContato);
        janela.add(linha1);

        Box linha2 = Box.createHorizontalBox();
        JLabel rotuloTipo = new JLabel("Tipo de Contato:");
        rotuloTipo.setVisible(true);
        linha2.add(rotuloTipo);
        linha2.add(Box.createRigidArea(tamanho));
        campoTipo = new JTextField(15);
        campoTipo.setToolTipText("email ou telefone ou instagram e.t.c");
        campoTipo.setVisible(true);
        linha2.add(campoTipo);
        janela.add(linha2);
    }

    /**
     * Cria uma interface gráfica a partir da base para cadastro de contatos
     */
    @Override
    public void paraCadastro() {
        JButton botaoAdicionar = new JButton("Adicionar Contato");
        botaoAdicionar.addActionListener(action -> {
            try {
                String tipo = campoTipo.getText();
                String dado = campoContato.getText();
                if (tipo.equals("") || dado.equals("")) {
                    throw new CadastroException("Campo(s) obrigatório(s) não preenchido(s)!");
                }
                Contato contato = new Contato(dado, tipo);
                egresso.getContatos().add(contato);
                campoContato.setText("");
                campoTipo.setText("");
            } catch (CadastroException e) {
                JOptionPane.showMessageDialog(janela, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoAdicionar.setVisible(true);
        janela.add(botaoAdicionar);

        Box linha3 = Box.createHorizontalBox();
        JButton botaoContinuar = new JButton("Continuar");
        botaoContinuar.addActionListener(action -> {
            new MultiInterfaceFormacao(sigae, egresso).paraCadastro();
            janela.dispose();
        });
        botaoContinuar.setVisible(true);
        linha3.add(botaoContinuar);
        linha3.add(Box.createRigidArea(tamanho));
        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(action -> {
            new MenuCoordenador(sigae);
            janela.dispose();
        });
        botaoCancelar.setVisible(true);
        linha3.add(botaoCancelar);
        janela.add(linha3);

        janela.setTitle("SIGAE - Cadastrar Contatos");
        janela.setSize(320, 160);
        janela.setVisible(true);
    }

    /**
     * Cria uma interface gráfica a partir da base para visualização de contatos
     * @param objeto contato para ser visualizado
     */
    @Override
    public void paraVisualizacao(Object objeto) {
        Contato contato = (Contato)objeto;

        campoContato.setText(contato.getDado());
        campoTipo.setText(contato.getTipo());

        campoContato.setEditable(false);
        campoTipo.setEditable(false);

        JButton botaoFechar = new JButton("Fechar");
        botaoFechar.addActionListener(action -> {
            janela.dispose();
        });
        botaoFechar.setVisible(true);
        janela.add(botaoFechar);

        janela.setTitle("SIGAE - Visualizar Contato");
        janela.setSize(320, 120);
        janela.setVisible(true);
    }

    /**
     * Cria uma interface gráfica a partir da base para edição de contatos
     * @param objeto contato para ser editado
     */
    @Override
    public void paraEdicao(Object objeto) {
        Contato contato = (Contato)objeto;

        campoContato.setText(contato.getDado());
        campoTipo.setText(contato.getTipo());

        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(action -> {
            try {
                String dado = campoContato.getText();
                String tipo = campoTipo.getText();
                if (tipo.equals("") || dado.equals("")) {
                    throw new CadastroException("Campo(s) obrigatório(s) não preenchido(s)!");
                }
                int indice = egresso.getContatos().indexOf(contato);
                egresso.getContatos().get(indice).setDado(dado);
                egresso.getContatos().get(indice).setTipo(tipo);
                new MultiInterfaceEgresso(sigae).paraEdicao(egresso);
                janela.dispose();
            } catch (CadastroException e) {
                JOptionPane.showMessageDialog(janela, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoSalvar.setVisible(true);
        janela.add(botaoSalvar);

        JButton botaoApagar = new JButton("Apagar");
        botaoApagar.addActionListener(action -> {
            egresso.getContatos().remove(contato);
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

        janela.setTitle("SIGAE - Editar Contato");
        janela.setSize(320, 150);
        janela.setVisible(true);
    }

    public void paraAdicionar() {
        JButton botaoAdicionar = new JButton("Adicionar Contato");
        botaoAdicionar.addActionListener(action -> {
            try {
                String tipo = campoTipo.getText();
                String dado = campoContato.getText();
                if (tipo.equals("") || dado.equals("")) {
                    throw new CadastroException("Campo(s) obrigatório(s) não preenchido(s)!");
                }
                Contato contato = new Contato(dado, tipo);
                egresso.getContatos().add(contato);
                new MultiInterfaceEgresso(sigae).paraEdicao(egresso);
                janela.dispose();
            } catch (CadastroException e) {
                JOptionPane.showMessageDialog(janela, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoAdicionar.setVisible(true);
        janela.add(botaoAdicionar);

        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(action -> {
            janela.dispose();
        });
        botaoCancelar.setVisible(true);
        janela.add(botaoCancelar);

        janela.setTitle("SIGAE - Adicionar Contato");
        janela.setSize(320, 120);
        janela.setVisible(true);
    }
}
