package ufma.sigae.interfaces;

import ufma.sigae.entidades.Egresso;
import ufma.sigae.entidades.Ocupacao;
import ufma.sigae.excecoes.CadastroException;
import ufma.sigae.servicos.Coordenador;
import ufma.sigae.servicos.Sigae;

import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import javax.swing.*;

/**
 * Classe que constrói interfaces gráficas para cadastro, visualização e edição de ocupações
 */
public class MultiInterfaceOcupacao extends InterfaceGenerica implements MultiInterface {
    private final Egresso egresso;
    /** Campo para entrada do nome da empresa */
    private final JTextField campoEmpresa;
    /** Campo para entrada do cargo ocupado */
    private final JTextField campoCargo;
    /** Campo para entrada do salário mensal recebido */
    private final JTextField campoSalario;
    /** Campo de texto para entrada da descrição do cargo */
    private final JTextArea textoDescricao;
    /** Campo para entrada da data de início da ocupação */
    private final JTextField campoInicio;
    /** Campo para entrada da cidade onde fica a empresa */
    private final JTextField campoCidade;
    /** Campo para entrada do país onde fica a empresa */
    private final JTextField campoPais;

    /**
     * Cria uma interface gráfica base
     * @param sigae estado atual dos dados do SIGAE
     * @param egresso egresso onde será cadastrado as formações
     */
    public MultiInterfaceOcupacao(Sigae sigae, Egresso egresso) {
        super(sigae);
        this.egresso = egresso;

        Box linha1 = Box.createHorizontalBox();
        JLabel rotuloEmpresa = new JLabel("Empresa:");
        rotuloEmpresa.setVisible(true);
        linha1.add(rotuloEmpresa);
        linha1.add(Box.createRigidArea(tamanho));
        campoEmpresa = new JTextField(15);
        campoEmpresa.setToolTipText("Nome da empresa");
        campoEmpresa.setVisible(true);
        linha1.add(campoEmpresa);
        janela.add(linha1);

        Box linha2 = Box.createHorizontalBox();
        JLabel rotuloCargo = new JLabel("Cargo:");
        rotuloCargo.setVisible(true);
        linha2.add(rotuloCargo);
        linha2.add(Box.createRigidArea(tamanho));
        campoCargo = new JTextField(15);
        campoCargo.setToolTipText("Cargo ocupado pelo Egresso");
        campoCargo.setVisible(true);
        linha2.add(campoCargo);
        janela.add(linha2);

        Box linha3 = Box.createHorizontalBox();
        JLabel rotuloSalario = new JLabel("Salário:");
        rotuloSalario.setVisible(true);
        linha3.add(rotuloSalario);
        linha3.add(Box.createRigidArea(tamanho));
        campoSalario = new JTextField(15);
        campoSalario.setToolTipText("Sálario mensal do Egresso");
        campoSalario.setVisible(true);
        linha3.add(campoSalario);
        janela.add(linha3);

        Box linha4 = Box.createHorizontalBox();
        JLabel rotuloInicio = new JLabel("Início:");
        rotuloInicio.setVisible(true);
        linha4.add(rotuloInicio);
        linha4.add(Box.createRigidArea(tamanho));
        campoInicio = new JTextField(15);
        campoInicio.setToolTipText("Data de início no cargo [dd/MM/yyyy]");
        campoInicio.setVisible(true);
        linha4.add(campoInicio);
        janela.add(linha4);

        Box linha5 = Box.createHorizontalBox();
        JLabel rotuloCidade = new JLabel("Cidade:");
        rotuloCidade.setVisible(true);
        linha5.add(rotuloCidade);
        linha5.add(Box.createRigidArea(tamanho));
        campoCidade = new JTextField(15);
        campoCidade.setToolTipText("Cidade onde se localiza a empresa");
        campoCidade.setVisible(true);
        linha5.add(campoCidade);
        janela.add(linha5);

        Box linha6 = Box.createHorizontalBox();
        JLabel rotuloPais = new JLabel("País:");
        rotuloPais.setVisible(true);
        linha6.add(rotuloPais);
        linha6.add(Box.createRigidArea(tamanho));
        campoPais = new JTextField(15);
        campoPais.setToolTipText("País onde se localiza a empresa");
        campoPais.setVisible(true);
        linha6.add(campoPais);
        janela.add(linha6);

        JLabel labelDescricao = new JLabel("Descrição sobre o Cargo:");
        labelDescricao.setVisible(true);
        janela.add(labelDescricao);

        textoDescricao = new JTextArea(10, 30);
        textoDescricao.setLineWrap(true);
        textoDescricao.setVisible(true);
        janela.add(textoDescricao);
    }

    /**
     * Cria uma interface gráfica a partir da base para cadastro de ocupações
     */
    @Override
    public void paraCadastro() {
        JButton botaoAdicionarOcupacao = new JButton("Adicionar Ocupação");
        botaoAdicionarOcupacao.addActionListener(action -> {
            try {
                String cargo = campoCargo.getText();
                String empresa = campoEmpresa.getText();
                String descricao = textoDescricao.getText();
                float salario = Float.parseFloat(campoSalario.getText());
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate inicio = LocalDate.parse(campoInicio.getText(), formato);
                String cidade = campoCidade.getText();
                String pais = campoPais.getText();
                if (cargo.equals("") || empresa.equals("") || salario == 0) {
                    throw new CadastroException("Campo(s) obrigatório(s) não preenchido(s)!");
                }
                Ocupacao ocupacao = new Ocupacao(empresa, cargo, salario, descricao, inicio, cidade, pais);
                egresso.getOcupacoes().add(ocupacao);
                campoCargo.setText("");
                campoEmpresa.setText("");
                textoDescricao.setText("");
                campoSalario.setText("");
                campoInicio.setText("");
                campoCidade.setText("");
                campoPais.setText("");
            } catch (CadastroException e) {
                JOptionPane.showMessageDialog(janela, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                String mensagem = "Valor inválido no campo salário!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (DateTimeParseException e) {
                String mensagem = "Formato de data inválido no campo início!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoAdicionarOcupacao.setVisible(true);
        janela.add(botaoAdicionarOcupacao);

        Box linha7 = Box.createHorizontalBox();
        JButton botaoFinalizar = new JButton("Finalizar Cadastro");
        botaoFinalizar.addActionListener(action -> {
            try {
                ((Coordenador)sigae.usuario).cadastrarEgresso(egresso);
                new MenuCoordenador(sigae);
                janela.dispose();
            } catch (CadastroException e) {
                JOptionPane.showMessageDialog(janela, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoFinalizar.setVisible(true);
        linha7.add(botaoFinalizar);
        linha7.add(Box.createRigidArea(tamanho));
        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(action -> {
            new MenuCoordenador(sigae);
            janela.dispose();
        });
        botaoCancelar.setVisible(true);
        linha7.add(botaoCancelar);
        janela.add(linha7);

        janela.setTitle("SIGAE - Cadastrar Ocupações");
        janela.setSize(350, 430);
        janela.setVisible(true);
    }

    /**
     * Cria uma interface gráfica a partir da base para visualização de ocupações
     * @param objeto ocupação para ser visualizada
     */
    @Override
    public void paraVisualizacao(Object objeto) {
        Ocupacao ocupacao = (Ocupacao)objeto;

        campoCargo.setText(ocupacao.getCargo());
        campoEmpresa.setText(ocupacao.getEmpresa());
        textoDescricao.setText(ocupacao.getDescricao());
        campoSalario.setText(ocupacao.getSalario()+"");
        int dia = ocupacao.getInicio().getDayOfMonth();
        int mes = ocupacao.getInicio().getMonthValue();
        int ano = ocupacao.getInicio().getYear();
        campoInicio.setText(dia+"/"+mes+"/"+ano);
        campoCidade.setText(ocupacao.getCidade());
        campoPais.setText(ocupacao.getPais());

        campoCargo.setEditable(false);
        campoEmpresa.setEditable(false);
        textoDescricao.setEditable(false);
        campoSalario.setEditable(false);
        campoInicio.setEditable(false);
        campoCidade.setEditable(false);
        campoPais.setEditable(false);

        JButton botaoFechar = new JButton("Fechar");
        botaoFechar.addActionListener(action -> {
            janela.dispose();
        });
        botaoFechar.setVisible(true);
        janela.add(botaoFechar);

        janela.setTitle("SIGAE - Visualizar Ocupação");
        janela.setSize(350, 390);
        janela.setVisible(true);
    }

    /**
     * Cria uma interface gráfica a partir da base para edição de ocupações
     * @param objeto ocupação para ser editada
     */
    @Override
    public void paraEdicao(Object objeto) {
        Ocupacao ocupacao = (Ocupacao)objeto;

        campoCargo.setText(ocupacao.getCargo());
        campoEmpresa.setText(ocupacao.getEmpresa());
        textoDescricao.setText(ocupacao.getDescricao());
        campoSalario.setText(ocupacao.getSalario()+"");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (ocupacao.getInicio() != null) {
            campoInicio.setText(ocupacao.getInicio().format(formato));
        } else {
            campoInicio.setText("");
        }
        campoCidade.setText(ocupacao.getCidade());
        campoPais.setText(ocupacao.getPais());

        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(action -> {
            try {
                String cargo = campoCargo.getText();
                String empresa = campoEmpresa.getText();
                String descricao = textoDescricao.getText();
                float salario = Float.parseFloat(campoSalario.getText());
                LocalDate inicio = LocalDate.parse(campoInicio.getText(), formato);
                String cidade = campoCidade.getText();
                String pais = campoPais.getText();
                if (cargo.equals("") || empresa.equals("") || salario == 0) {
                    throw new CadastroException("Campo(s) obrigatório(s) não preenchido(s)!");
                }
                int indice = egresso.getOcupacoes().indexOf(ocupacao);
                egresso.getOcupacoes().get(indice).setCargo(cargo);
                egresso.getOcupacoes().get(indice).setEmpresa(empresa);
                egresso.getOcupacoes().get(indice).setDescricao(descricao);
                egresso.getOcupacoes().get(indice).setSalario(salario);
                egresso.getOcupacoes().get(indice).setInicio(inicio);
                egresso.getOcupacoes().get(indice).setCidade(cidade);
                egresso.getOcupacoes().get(indice).setPais(pais);
                new MultiInterfaceEgresso(sigae).paraEdicao(egresso);
                janela.dispose();
            } catch (CadastroException e) {
                JOptionPane.showMessageDialog(janela, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                String mensagem = "Valor inválido no campo salário!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (DateTimeParseException e) {
                String mensagem = "Formato de data inválido no campo início!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoSalvar.setVisible(true);
        janela.add(botaoSalvar);

        JButton botaoApagar = new JButton("Apagar");
        botaoApagar.addActionListener(action -> {
            egresso.getOcupacoes().remove(ocupacao);
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

        janela.setTitle("SIGAE - Visualizar Ocupação");
        janela.setSize(350, 420);
        janela.setVisible(true);
    }

    public void paraAdicionar() {
        JButton botaoAdicionarOcupacao = new JButton("Adicionar Ocupação");
        botaoAdicionarOcupacao.addActionListener(action -> {
            try {
                String cargo = campoCargo.getText();
                String empresa = campoEmpresa.getText();
                String descricao = textoDescricao.getText();
                float salario = Float.parseFloat(campoSalario.getText());
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate inicio = LocalDate.parse(campoInicio.getText(), formato);
                String cidade = campoCidade.getText();
                String pais = campoPais.getText();
                if (cargo.equals("") || empresa.equals("") || salario == 0) {
                    throw new CadastroException("Campo(s) obrigatório(s) não preenchido(s)!");
                }
                Ocupacao ocupacao = new Ocupacao(empresa, cargo, salario, descricao, inicio, cidade, pais);
                egresso.getOcupacoes().add(ocupacao);
                new MultiInterfaceEgresso(sigae).paraEdicao(egresso);
                janela.dispose();
            } catch (CadastroException e) {
                JOptionPane.showMessageDialog(janela, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                String mensagem = "Valor inválido no campo salário!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (DateTimeParseException e) {
                String mensagem = "Formato de data inválido no campo início!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoAdicionarOcupacao.setVisible(true);
        janela.add(botaoAdicionarOcupacao);

        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(action -> {
            new MenuCoordenador(sigae);
            janela.dispose();
        });
        botaoCancelar.setVisible(true);
        janela.add(botaoCancelar);

        janela.setTitle("SIGAE - Cadastrar Ocupações");
        janela.setSize(350, 390);
        janela.setVisible(true);
    }
}
