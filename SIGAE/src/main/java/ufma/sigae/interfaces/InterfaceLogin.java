package ufma.sigae.interfaces;

import ufma.sigae.excecoes.LoginException;
import ufma.sigae.servicos.*;

import java.awt.*;
import javax.swing.*;

/**
 * Classe que constrói a interface gráfica de login
 */
public class InterfaceLogin {
    /**
     * Cria uma interface gráfica de login
     * @param sigae estado atual dos dados do SIGAE
     */
    public InterfaceLogin(Sigae sigae) {
        JFrame janela = new JFrame("SIGAE - Login");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setLayout(new FlowLayout());

        Dimension tamanho = new Dimension(10, 0);

        Box linha1 = Box.createHorizontalBox();
        JLabel rotuloLogin = new JLabel("Login:");
        rotuloLogin.setVisible(true);
        linha1.add(rotuloLogin);
        linha1.add(Box.createRigidArea(tamanho));
        JTextField campoLogin = new JTextField(15);
        campoLogin.setToolTipText("Entre com seu login");
        campoLogin.setVisible(true);
        linha1.add(campoLogin);
        janela.add(linha1);

        Box linha2 = Box.createHorizontalBox();
        JLabel rotuloSenha = new JLabel("Senha:");
        rotuloSenha.setVisible(true);
        linha2.add(rotuloSenha);
        linha2.add(Box.createRigidArea(tamanho));
        JPasswordField campoSenha = new JPasswordField(15);
        campoSenha.setToolTipText("Entre com sua senha");
        campoSenha.setVisible(true);
        linha2.add(campoSenha);
        janela.add(linha2);

        Box linha3 = Box.createHorizontalBox();
        JButton botaoEntrar = new JButton("Entrar");
        botaoEntrar.addActionListener(action -> {
            String login = campoLogin.getText();
            String senha = String.valueOf(campoSenha.getPassword());
            try {
                sigae.usuario = Usuario.entrar(login, senha, sigae.usuarios);
                if (sigae.usuario instanceof Administrador) {
                    new MenuAdministrador(sigae);
                } else if (sigae.usuario instanceof Coordenador) {
                    new MenuCoordenador(sigae);
                }
                janela.dispose();
            } catch (LoginException e) {
                JOptionPane.showMessageDialog(janela, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoEntrar.setVisible(true);
        linha3.add(botaoEntrar);
        linha3.add(Box.createRigidArea(tamanho));
        JButton botaoCreditos = new JButton("Créditos");
        botaoCreditos.addActionListener(action -> {
            new Creditos();
        });
        botaoCreditos.setVisible(true);
        linha3.add(botaoCreditos);
        janela.add(linha3);

        janela.setSize(250, 120);
        janela.setVisible(true);
    }
}
