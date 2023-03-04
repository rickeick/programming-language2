package ufma.sigae.interfaces;

import ufma.sigae.entidades.Egresso;
import ufma.sigae.servicos.Coordenador;
import ufma.sigae.servicos.Parser;
import ufma.sigae.servicos.Sigae;

import java.io.IOException;
import javax.swing.*;

/**
 * Classe que constrói uma interface gráfica com o menu do coordenador
 */
public class MenuCoordenador extends InterfaceGenerica {
    /**
     * Cria uma interface gráfica com o menu do coordenador
     * @param sigae estado atual dos dados do SIGAE
     */
    public MenuCoordenador(Sigae sigae) {
        super(sigae);

        String nome = ((Coordenador)sigae.usuario).getNome();
        JLabel rotuloUsuario = new JLabel("Usuário: "+nome);
        rotuloUsuario.setVisible(true);
        janela.add(rotuloUsuario);

        String matriz;
        if (((Coordenador)sigae.usuario).getCurso() != null) {
            matriz = ((Coordenador)sigae.usuario).getCurso().getMatriz();
        } else {
            matriz = "Não cadastrado";
        }
        JLabel rotuloMatriz = new JLabel("Curso: "+matriz);
        rotuloMatriz.setVisible(true);
        janela.add(rotuloMatriz);

        JButton botaoCadastrarCurso = new JButton("Cadastrar Curso");
        botaoCadastrarCurso.addActionListener(action -> {
            if (((Coordenador)sigae.usuario).getCurso() != null) {
                JOptionPane.showMessageDialog(janela, "Curso já está cadastrado!");
            } else {
                new CadastroCurso(sigae);
                janela.dispose();
            }
        });
        botaoCadastrarCurso.setVisible(true);
        janela.add(botaoCadastrarCurso);

        JButton botaoCadastrarEgresso = new JButton("Cadastrar Egresso");
        botaoCadastrarEgresso.addActionListener(action -> {
            if (((Coordenador)sigae.usuario).getCurso() != null) {
                new MultiInterfaceEgresso(sigae).paraCadastro();
                janela.dispose();
            } else {
                String mensagem = "Curso ainda não foi cadastrado!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoCadastrarEgresso.setVisible(true);
        janela.add(botaoCadastrarEgresso);

        JButton botaoBuscarEgresso = new JButton("Buscar Egresso");
        botaoBuscarEgresso.addActionListener(action -> {
            if (((Coordenador)sigae.usuario).getCurso() != null) {
                String chave = JOptionPane.showInputDialog(janela, "CPF/Nome do egresso:");
                if (chave != null) {
                    chave = chave.replaceAll("\\.","").replaceAll("-","");
                    Egresso egresso = ((Coordenador)sigae.usuario).buscarEgresso(chave);
                    if (egresso != null) {
                        new MultiInterfaceEgresso(sigae).paraVisualizacao(egresso);
                        janela.dispose();
                    } else {
                        JOptionPane.showMessageDialog(janela, "Egresso não Encontrado!");
                    }
                }
            } else {
                String mensagem = "Curso ainda não foi cadastrado!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoBuscarEgresso.setVisible(true);
        janela.add(botaoBuscarEgresso);

        JButton botaoEditarEgresso = new JButton("Editar Egresso");
        botaoEditarEgresso.addActionListener(action -> {
            if (((Coordenador)sigae.usuario).getCurso() != null) {
                String chave = JOptionPane.showInputDialog(janela, "CPF/Nome do egresso:");
                if (chave != null) {
                    chave = chave.replaceAll("\\.","").replaceAll("-","");
                    Egresso egresso = ((Coordenador)sigae.usuario).buscarEgresso(chave);
                    if (egresso != null) {
                        new MultiInterfaceEgresso(sigae).paraEdicao(egresso);
                        janela.dispose();
                    } else {
                        JOptionPane.showMessageDialog(janela, "Egresso não Encontrado!");
                    }
                }
            } else {
                String mensagem = "Curso ainda não foi cadastrado!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoEditarEgresso.setVisible(true);
        janela.add(botaoEditarEgresso);

        JButton botaoGerarRelatorio1 = new JButton("Gerar Relatorio 1");
        botaoGerarRelatorio1.addActionListener(action -> {
            if (((Coordenador)sigae.usuario).getCurso() != null) {
                try {
                    int ano = Integer.parseInt(JOptionPane.showInputDialog(janela, "Ano:"));
                    ((Coordenador)sigae.usuario).gerarRelatorio1(ano);
                    JOptionPane.showMessageDialog(janela, "Relatório gerado com sucesso!");
                } catch (NumberFormatException e) {
                    String mensagem = "Valor digitado é inválido!";
                    JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalStateException e) {
                    String mensagem = "Nenhum egresso passou no filtro!";
                    JOptionPane.showMessageDialog(janela, mensagem);
                } catch (IOException e) {
                    String mensagem = "Não foi possível criar as pastas ou o arquivo pdf";
                    JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                String mensagem = "Curso ainda não foi cadastrado!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoGerarRelatorio1.setVisible(true);
        janela.add(botaoGerarRelatorio1);

        JButton botaoGerarRelatorio2 = new JButton("Gerar Relatorio 2");
        botaoGerarRelatorio2.addActionListener(action -> {
            if (((Coordenador)sigae.usuario).getCurso() != null) {
                try {
                    int anoInicial = Integer.parseInt(JOptionPane.showInputDialog(janela, "Ano Inicial:"));
                    int anoFinal = Integer.parseInt(JOptionPane.showInputDialog(janela, "Ano Final:"));
                    ((Coordenador)sigae.usuario).gerarRelatorio2(anoInicial, anoFinal);
                    JOptionPane.showMessageDialog(janela, "Relatório gerado com sucesso!");
                } catch (NumberFormatException e) {
                    String mensagem = "Valor digitado é inválido!";
                    JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalStateException e) {
                    String mensagem = "Nenhum egresso passou no filtro!";
                    JOptionPane.showMessageDialog(janela, mensagem);
                } catch (IOException e) {
                    String mensagem = "Não foi possível criar as pastas ou o arquivo pdf";
                    JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                String mensagem = "Curso ainda não foi cadastrado!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoGerarRelatorio2.setVisible(true);
        janela.add(botaoGerarRelatorio2);

        JButton botaoGerarRelatorio3 = new JButton("Gerar Relatorio 3");
        botaoGerarRelatorio3.addActionListener(action -> {
            if (((Coordenador)sigae.usuario).getCurso() != null) {
                try {
                    int anoInicial = Integer.parseInt(JOptionPane.showInputDialog(janela, "Ano Inicial:"));
                    int anoFinal = Integer.parseInt(JOptionPane.showInputDialog(janela, "Ano Final:"));
                    JOptionPane.showMessageDialog(janela, "Relatório gerado com sucesso!");
                    ((Coordenador)sigae.usuario).gerarRelatorio3(anoInicial, anoFinal);
                } catch (NumberFormatException e) {
                    String mensagem = "Valor digitado é inválido!";
                    JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalStateException e) {
                    String mensagem = "Nenhum egresso passou no filtro!";
                    JOptionPane.showMessageDialog(janela, mensagem);
                } catch (IOException e) {
                    String mensagem = "Não foi possível criar as pastas ou o arquivo pdf";
                    JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                String mensagem = "Curso ainda não foi cadastrado!";
                JOptionPane.showMessageDialog(janela, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoGerarRelatorio3.setVisible(true);
        janela.add(botaoGerarRelatorio3);

        JButton botaoLogout = new JButton("Logout do Sistema");
        botaoLogout.addActionListener(action -> {
            try {
                Parser.salvar(sigae.usuarios);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(janela, "Não foi possível salvar os dados dessa sessão!");
            } finally {
                new InterfaceLogin(sigae);
                janela.dispose();
            }
        });
        botaoLogout.setVisible(true);
        janela.add(botaoLogout);

        janela.setTitle("SIGAE - Menu do Coordenador");
        janela.setSize(250, 330);
        janela.setVisible(true);
    }
}
