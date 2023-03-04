package ufma.sigae.servicos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Hashtable;

/**
 * Classe responsável por salvar e carregar os dados do hashtable usuarios no arquivo usuarios.bin
 */
public class Parser {
    /** Constante para o caminho relativo do arquivo */
    private static final String CAMINHO = "src/main/resources/usuarios.bin";

    /**
     * Salva os dados do hashtable usuarios no arquivo usuarios.bin
     * @param usuarios hashtable usuarios com os dados a ser salvo
     * @throws IOException se não for possivel escrever no arquivo
     */
    public static void salvar(Hashtable<String,Usuario> usuarios) throws IOException {
        ObjectOutputStream arquivo = new ObjectOutputStream(new FileOutputStream(CAMINHO));
        arquivo.writeObject(usuarios);
        arquivo.close();
    }

    /**
     * Carrega os dados do arquivo usuarios.bin e o retorna como hashtable usuarios
     * @return o hashtable usuarios com os dados do arquivo usuarios.bin
     * @throws IOException se não for possivel ler o arquivo
     * @throws ClassNotFoundException se a classe do objeto serializado não for encontrada
     */
    @SuppressWarnings("unchecked")
    public static Hashtable<String,Usuario> carregar() throws IOException, ClassNotFoundException {
        ObjectInputStream arquivo = new ObjectInputStream(new FileInputStream(CAMINHO));
        Hashtable<String,Usuario> usuarios = (Hashtable<String,Usuario>)arquivo.readObject();
        arquivo.close();
        return usuarios;
    }
}
