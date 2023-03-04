package ufma.sigae.interfaces;

import ufma.sigae.excecoes.CadastroException;
import ufma.sigae.servicos.Administrador;
import ufma.sigae.servicos.Coordenador;
import ufma.sigae.servicos.Sigae;

import javax.swing.*;

/**
 * Classe que constrói uma interface gráfica para cadastro de coordenadores
 */
public class CadastroCoordenador extends InterfaceGenerica {
    /**
     * Cria uma interface gráfica para cadastro de coordenadores
     * @param sigae estado atual dos dados do SIGAE
     */
    public CadastroCoordenador(Sigae sigae) {
        super(sigae);

        Box linha1 = Box.createHorizontalBox();
        JLabel rotuloLogin = new JLabel("Login:");
        rotuloLogin.setVisible(true);
        linha1.add(rotuloLogin);
        linha1.add(Box.createRigidArea(tamanho));
        JTextField campoLogin = new JTextField(15);
        campoLogin.setToolTipText("Novo login");
        campoLogin.setVisible(true);
        linha1.add(campoLogin);
        janela.add(linha1);

        Box linha2 = Box.createHorizontalBox();
        JLabel rotuloSenha = new JLabel("Senha:");
        rotuloSenha.setVisible(true);
        linha2.add(rotuloSenha);
        linha2.add(Box.createRigidArea(tamanho));
        JPasswordField campoSenha = new JPasswordField(15);
        campoSenha.setToolTipText("Nova senha");
        campoSenha.setVisible(true);
        linha2.add(campoSenha);
        janela.add(linha2);

        Box linha3 = Box.createHorizontalBox();
        JLabel rotuloNome = new JLabel("Nome:");
        rotuloNome.setVisible(true);
        linha3.add(rotuloNome);
        linha3.add(Box.createRigidArea(tamanho));
        JTextField campoNome = new JTextField(15);
        campoNome.setToolTipText("Nome do coordenador");
        campoNome.setVisible(true);
        linha3.add(campoNome);
        janela.add(linha3);

        Box linha4 = Box.createHorizontalBox();
        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(action -> {
            String login = campoLogin.getText();
            String senha = String.valueOf(campoSenha.getPassword());
            String nome = campoNome.getText();
            try {
                Coordenador coordenador = new Coordenador(login, senha, nome);
                ((Administrador)sigae.usuario).cadCoordenador(coordenador, sigae.usuarios);
                new MenuAdministrador(sigae);
                janela.dispose();
            } catch (CadastroException e) {
                JOptionPane.showMessageDialog(janela, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoCadastrar.setVisible(true);
        linha4.add(botaoCadastrar);
        linha4.add(Box.createRigidArea(tamanho));
        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(action -> {
            new MenuAdministrador(sigae);
            janela.dispose();
        });
        botaoCancelar.setVisible(true);
        linha4.add(botaoCancelar);
        janela.add(linha4);

        janela.setTitle("SIGAE - Cadastrar Coordenador");
        janela.setSize(250, 150);
        janela.setVisible(true);
    }
}
