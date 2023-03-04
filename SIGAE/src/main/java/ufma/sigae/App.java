package ufma.sigae;

import ufma.sigae.interfaces.InterfaceLogin;
import ufma.sigae.servicos.Administrador;
import ufma.sigae.servicos.Usuario;
import ufma.sigae.servicos.Parser;
import ufma.sigae.servicos.Sigae;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import javax.swing.*;

/**
 * Classe principal com a main para executar o SIGAE
 * Sistema Integrado de Gestão e Acompanhamento de Egressos
 * @author Rick Eick Vieira Dos Santos
 * @author Pedro Lima De Assunção Filho
 */
public class App {
    // Administrador — Login: admin & Senha: admin
    public static void main(String[] args) {
        try {
            new InterfaceLogin(new Sigae());
        } catch (Exception exception) {
            String mensagem = "Erro ao ler usuarios.bin! Deseja restaurá-lo?";
            int opcao = JOptionPane.showConfirmDialog(null, mensagem);
            if (opcao == JOptionPane.YES_OPTION) {
                Hashtable<String,Usuario> usuarios = new Hashtable<>();
                usuarios.put("admin", new Administrador("admin", "admin"));
                try {
                    File pasta = new File("src/main/resources/");
                    if (!pasta.exists()) {
                        boolean criou = pasta.mkdir();
                        if (!criou) {
                            throw new IOException();
                        }
                    }
                    Parser.salvar(usuarios);
                    mensagem = "Arquivo usuarios.bin restaurado!";
                    JOptionPane.showMessageDialog(null, mensagem);
                } catch (IOException ioException) {
                    mensagem = "Não foi possível restaurar o arquivo!";
                    JOptionPane.showMessageDialog(null, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
