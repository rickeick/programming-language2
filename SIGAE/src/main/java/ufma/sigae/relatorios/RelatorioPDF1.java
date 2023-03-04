package ufma.sigae.relatorios;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import ufma.sigae.entidades.Egresso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Cria e salva um relatório em pdf do tipo 1
 * <p>[Nome do Egresso | Data de Conclusão do Curso]</p>
 * Ordenado por nome e filtrado pelo ano de conclusão dos egressos
 */
public class RelatorioPDF1 implements Relatorio {
    /** Documento com o relatorio pdf para ser gerado */
    private final Document documentoPDF;
    /** Lista de egressos já filtrado para gera o relatório */
    private final List<Egresso> egressos;

    /**
     * Inicializa os dados para gerar um relatório em pdf do tipo 1
     * @param egressos lista de egressos já filtrado para gera o relatório
     * @param curso curso do coordenador para determinar a pasta dos relatórios
     * @throws IOException se não for possível acessar as pastas ou o arquivo
     */
    public RelatorioPDF1(List<Egresso> egressos, String curso) throws IOException {
        this.egressos = egressos;
        this.documentoPDF = new Document();
        String caminho = "files/relatorios/"+curso+"/";
        File pastas = new File(caminho);
        if (!pastas.exists()) {
            boolean criou = pastas.mkdirs();
            if (!criou) {
                throw new IOException("Não foi possível criar as pastas");
            }
        }
        caminho = caminho+"Relatorio1.pdf";
        PdfWriter.getInstance(this.documentoPDF, new FileOutputStream(caminho));
        this.documentoPDF.open();
    }

    /**
     * Gera o cabeçalho do relatório
     */
    public void gerarCabecalho() {
        Paragraph paragrafoTitulo = new Paragraph();
        paragrafoTitulo.setAlignment(1);
        paragrafoTitulo.add(new Chunk("RELATORIO DE EGRESSOS 1", new Font(1, 24.0F)));
        this.documentoPDF.add(paragrafoTitulo);
        this.documentoPDF.add(new Paragraph("\n\n"));
    }

    /**
     * Gera o corpo do relatório
     */
    public void gerarCorpo() {
        for (Egresso egresso:egressos) {
            Paragraph paragrafoNomeEConclusao = new Paragraph();
            paragrafoNomeEConclusao.setAlignment(Element.ALIGN_LEFT);
            Chunk texto = new Chunk();
            texto.append("EGRESSO: " + egresso.getNome()+"    ");
            texto.append("DATA DE CONCLUSÃO: " + egresso.getConclusao());
            paragrafoNomeEConclusao.add(texto);
            this.documentoPDF.add(paragrafoNomeEConclusao);
        }
    }

    /**
     * Salva e gera o arquivo pdf
     */
    public void imprimir() {
        if (this.documentoPDF != null && this.documentoPDF.isOpen()) {
            this.documentoPDF.close();
        }
    }
}
