package ufma.sigae.interfaces;

import ufma.sigae.entidades.Contato;
import ufma.sigae.entidades.Formacao;
import ufma.sigae.entidades.Ocupacao;
import ufma.sigae.excecoes.CadastroException;
import ufma.sigae.entidades.Egresso;
import ufma.sigae.servicos.Coordenador;
import ufma.sigae.servicos.Sigae;

import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.awt.event.*;
import java.util.Hashtable;
import javax.swing.*;

/**
 * Classe que constrói interfaces gráficas para cadastro, visualização e edição de egressos
 */
public class MultiInterfaceEgresso extends InterfaceGenerica implements MultiInterface {
    /** Campo para entrada do cpf do egresso */
    private final JTextField campoCpf;
    /** Campo para entrada do nome do egresso */
    private final JTextField campoNome;
    /** Campo para entrada da data de conclusão do curso */
    private final JTextField campoConclusao;
    /** Campo de texto para entrada do depoimento do egresso */
    private final JTextArea textoDepoimento;

    /**
     * Cria uma interface gráfica base
     * @param sigae estado atual dos dados do SIGAE
     */
    public MultiInterfaceEgresso(Sigae sigae) {
        super(sigae);

        Box linha1 = Box.createHorizontalBox();
        JLabel rotuloCpf = new JLabel("CPF:");
        rotuloCpf.setVisible(true);
        linha1.add(rotuloCpf);
        linha1.add(Box.createRigidArea(tamanho));
        campoCpf = new JTextField(15);
        campoCpf.setToolTipText("CPF do egresso");
        campoCpf.setVisible(true);
        linha1.add(campoCpf);
        janela.add(linha1);

        Box linha2 = Box.createHorizontalBox();
        JLabel rotuloNome = new JLabel("Nome:");
        rotuloNome.setVisible(true);
        linha2.add(rotuloNome);
        linha2.add(Box.createRigidArea(tamanho));
        campoNome = new JTextField(15);
        campoNome.setToolTipText("Nome completo do Egresso");
        campoNome.setVisible(true);
        linha2.add(campoNome);
        janela.add(linha2);

        Box linha3 = Box.createHorizontalBox();
        JLabel rotuloConclusao = new JLabel("Conclusão:");
        rotuloConclusao.setVisible(true);
        linha3.add(rotuloConclusao);
        linha3.add(Box.createRigidArea(tamanho));
        campoConclusao = new JTextField(15);
        campoConclusao.setToolTipText("Data de conclusão do curso [dd/MM/yyyy]");
        campoConclusao.setVisible(true);
        linha3.add(campoConclusao);
        janela.add(linha3);

        JLabel rotuloDepoimento = new JLabel("Depoimento:");
        rotuloDepoimento.setVisible(true);
        janela.add(rotuloDepoimento);

        textoDepoimento = new JTextArea(10,25);
        textoDepoimento.setLineWrap(true);
        textoDepoimento.setVisible(true);
        janela.add(textoDepoimento);
    }

    /**
     * Cria uma interface gráfica a partir da base para cadastro de egressos
     */
    @Override
    public void paraCadastro() {
        Box linha4 = Box.createHorizontalBox();
        JButton botaoContinuar = new JButton("Continuar");
        botaoContinuar.addActionListener(action -> {
            try {
                String nome = campoNome.getText();
                String depoimento = textoDepoimento.getText();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate conclusao = LocalDate.parse(campoConclusao.getText(), formato);
                String cpf = campoCpf.getText().replaceAll("\\.","").replaceAll("-","");
                if (cpf.equals("") || nome.equals("")) {
                    throw new CadastroException("Campo(s) obrigatório(s) não preenchido(s)!");
                }
                Egresso egresso = new Egresso(cpf, nome, conclusao, depoimento);
                new MultiInterfaceContato(sigae, egresso).paraCadastro();
                janela.dispose();
            } catch (CadastroException e) {
                JOptionPane.showMessageDialog(janela, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (DateTimeParseException e) {
                String mensagem = "Formato de data inválido no campo conclusão!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoContinuar.setVisible(true);
        linha4.add(botaoContinuar);
        linha4.add(Box.createRigidArea(tamanho));
        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(action -> {
            new MenuCoordenador(sigae);
            janela.dispose();
        });
        botaoCancelar.setVisible(true);
        linha4.add(botaoCancelar);
        janela.add(linha4);

        janela.setTitle("SIGAE - Cadastrar Egresso");
        janela.setSize(320, 330);
        janela.setVisible(true);
    }

    /**
     * Cria uma interface gráfica a partir da base para visualização de egressos
     * @param objeto egresso para ser visualizado
     */
    @Override
    public void paraVisualizacao(Object objeto) {
        Egresso egresso = (Egresso)objeto;

        campoCpf.setText(egresso.getCpf());
        campoNome.setText(egresso.getNome());
        int dia = egresso.getConclusao().getDayOfMonth();
        int mes = egresso.getConclusao().getMonthValue();
        int ano = egresso.getConclusao().getYear();
        campoConclusao.setText(dia+"/"+mes+"/"+ano);
        textoDepoimento.setText(egresso.getDepoimento());

        campoCpf.setEditable(false);
        campoNome.setEditable(false);
        campoConclusao.setEditable(false);
        textoDepoimento.setEditable(false);

        DefaultListModel<String> contatos = new DefaultListModel<>();
        for (Contato contato : egresso.getContatos()) {
            contatos.addElement(contato.toString());
        }
        JLabel rotuloContatos = new JLabel("Lista de Contatos do Egresso:");
        rotuloContatos.setVisible(true);
        janela.add(rotuloContatos);
        JList<String> listaContatos = new JList<>(contatos);
        listaContatos.setToolTipText("Clique duas vezes num item para visualizá-lo");
        listaContatos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    int indice = listaContatos.locationToIndex(event.getPoint());
                    Contato contato = egresso.getContatos().get(indice);
                    new MultiInterfaceContato(sigae, egresso).paraVisualizacao(contato);
                }
            }
        });
        listaContatos.setVisible(true);
        janela.add(listaContatos);

        DefaultListModel<String> formacoes = new DefaultListModel<>();
        for (Formacao formacao : egresso.getFormacoes()) {
            formacoes.addElement(formacao.toString());
        }
        JLabel rotuloFormacoes = new JLabel("Lista de Formações do Egresso:");
        rotuloFormacoes.setVisible(true);
        janela.add(rotuloFormacoes);
        JList<String> listaFormacoes = new JList<>(formacoes);
        listaFormacoes.setToolTipText("Clique duas vezes num item para visualizá-lo");
        listaFormacoes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    int indice = listaFormacoes.locationToIndex(event.getPoint());
                    Formacao formacao = egresso.getFormacoes().get(indice);
                    new MultiInterfaceFormacao(sigae, egresso).paraVisualizacao(formacao);
                }
            }
        });
        listaFormacoes.setVisible(true);
        janela.add(listaFormacoes);

        DefaultListModel<String> ocupacoes = new DefaultListModel<>();
        for (Ocupacao ocupacao : egresso.getOcupacoes()) {
            ocupacoes.addElement(ocupacao.toString());
        }
        JLabel rotuloOcupacoes = new JLabel("Lista de Ocupações do Egresso:");
        rotuloOcupacoes.setVisible(true);
        janela.add(rotuloOcupacoes);
        JList<String> listaOcupacoes = new JList<>(ocupacoes);
        listaOcupacoes.setToolTipText("Clique duas vezes num item para visualizá-lo");
        listaOcupacoes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    int indice = listaOcupacoes.locationToIndex(event.getPoint());
                    Ocupacao ocupacao = egresso.getOcupacoes().get(indice);
                    new MultiInterfaceOcupacao(sigae, egresso).paraVisualizacao(ocupacao);
                }
            }
        });
        listaOcupacoes.setVisible(true);
        janela.add(listaOcupacoes);

        JButton botaoFechar = new JButton("Fechar");
        botaoFechar.addActionListener(action -> {
            new MenuCoordenador(sigae);
            janela.dispose();
        });
        botaoFechar.setVisible(true);
        janela.add(botaoFechar);

        janela.setTitle("SIGAE - Visualizar Egresso");
        janela.setSize(320, 440);
        janela.setVisible(true);
    }

    /**
     * Cria uma interface gráfica a partir da base para edição de egressos
     * @param objeto egresso para ser editado
     */
    @Override
    public void paraEdicao(Object objeto) {
        Egresso egresso = (Egresso)objeto;

        campoCpf.setText(egresso.getCpf());
        campoNome.setText(egresso.getNome());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        campoConclusao.setText(egresso.getConclusao().format(formato));
        textoDepoimento.setText(egresso.getDepoimento());

        DefaultListModel<String> contatos = new DefaultListModel<>();
        for (Contato contato : egresso.getContatos()) {
            contatos.addElement(contato.toString());
        }
        JLabel rotuloContatos = new JLabel("Lista de Contatos do Egresso:");
        rotuloContatos.setVisible(true);
        janela.add(rotuloContatos);
        JList<String> listaContatos = new JList<>(contatos);
        listaContatos.setToolTipText("Clique duas vezes num item para editá-lo");
        listaContatos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    int indice = listaContatos.locationToIndex(event.getPoint());
                    Contato contato = egresso.getContatos().get(indice);
                    new MultiInterfaceContato(sigae, egresso).paraEdicao(contato);
                    janela.dispose();
                }
            }
        });
        listaContatos.setVisible(true);
        janela.add(listaContatos);
        JButton botaoAdicionarContato = new JButton("Adicionar Contato");
        botaoAdicionarContato.addActionListener(action -> {
            new MultiInterfaceContato(sigae, egresso).paraAdicionar();
            janela.dispose();
        });
        botaoAdicionarContato.setVisible(true);
        janela.add(botaoAdicionarContato);

        DefaultListModel<String> formacoes = new DefaultListModel<>();
        for (Formacao formacao : egresso.getFormacoes()) {
            formacoes.addElement(formacao.toString());
        }
        JLabel rotuloFormacoes = new JLabel("Lista de Formações do Egresso:");
        rotuloFormacoes.setVisible(true);
        janela.add(rotuloFormacoes);
        JList<String> listaFormacoes = new JList<>(formacoes);
        listaFormacoes.setToolTipText("Clique duas vezes num item para editá-lo");
        listaFormacoes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    int indice = listaFormacoes.locationToIndex(event.getPoint());
                    Formacao formacao = egresso.getFormacoes().get(indice);
                    new MultiInterfaceFormacao(sigae, egresso).paraEdicao(formacao);
                    janela.dispose();
                }
            }
        });
        listaFormacoes.setVisible(true);
        janela.add(listaFormacoes);
        JButton botaoAdicionarFormacao = new JButton("Adicionar Formação");
        botaoAdicionarFormacao.addActionListener(action -> {
            new MultiInterfaceFormacao(sigae, egresso).paraAdicionar();
            janela.dispose();
        });
        botaoAdicionarFormacao.setVisible(true);
        janela.add(botaoAdicionarFormacao);

        DefaultListModel<String> ocupacoes = new DefaultListModel<>();
        for (Ocupacao ocupacao : egresso.getOcupacoes()) {
            ocupacoes.addElement(ocupacao.toString());
        }
        JLabel rotuloOcupacoes = new JLabel("Lista de Ocupações do Egresso:");
        rotuloOcupacoes.setVisible(true);
        janela.add(rotuloOcupacoes);
        JList<String> listaOcupacoes = new JList<>(ocupacoes);
        listaOcupacoes.setToolTipText("Clique duas vezes num item para editá-lo");
        listaOcupacoes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    int indice = listaOcupacoes.locationToIndex(event.getPoint());
                    Ocupacao ocupacao = egresso.getOcupacoes().get(indice);
                    new MultiInterfaceOcupacao(sigae, egresso).paraEdicao(ocupacao);
                    janela.dispose();
                }
            }
        });
        listaOcupacoes.setVisible(true);
        janela.add(listaOcupacoes);
        JButton botaoAdicionarOcupacao = new JButton("Adicionar Ocupação");
        botaoAdicionarOcupacao.addActionListener(action -> {
            new MultiInterfaceOcupacao(sigae, egresso).paraAdicionar();
            janela.dispose();
        });
        botaoAdicionarOcupacao.setVisible(true);
        janela.add(botaoAdicionarOcupacao);

        Box linha5 = Box.createHorizontalBox();
        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(action -> {
            try {
                String cpf = campoCpf.getText();
                String depoimento = textoDepoimento.getText();
                LocalDate conclusao = LocalDate.parse(campoConclusao.getText(), formato);
                String nome = campoNome.getText().replaceAll("\\.","").replaceAll("-","");
                if (cpf.equals("") || nome.equals("")) {
                    throw new CadastroException("Campo(s) obrigatório(s) não preenchido(s)!");
                }
                Hashtable<String, Egresso> egressos;
                egressos = ((Coordenador)sigae.usuario).getCurso().getEgressos();
                egressos.remove(egresso.getCpf());
                egresso.setCpf(cpf);
                egresso.setNome(nome);
                egresso.setConclusao(conclusao);
                egresso.setDepoimento(depoimento);
                egressos.put(egresso.getCpf(), egresso);
                new MenuCoordenador(sigae);
                janela.dispose();
            } catch (CadastroException e) {
                JOptionPane.showMessageDialog(janela, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (DateTimeParseException e) {
                String mensagem = "Formato de data inválido no campo conclusão!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
            janela.dispose();
        });
        botaoSalvar.setVisible(true);
        linha5.add(botaoSalvar);
        linha5.add(Box.createRigidArea(tamanho));
        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(action -> {
            new MenuCoordenador(sigae);
            janela.dispose();
        });
        botaoCancelar.setVisible(true);
        linha5.add(botaoCancelar);
        janela.add(linha5);

        janela.setTitle("SIGAE - Editar Egresso");
        janela.setSize(320, 530);
        janela.setVisible(true);
    }
}
