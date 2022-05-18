
package modelos.auxiliares;

public class MarcaVeiculo {

    private static String NomeArquivoDisco = "Marcas.txt";
    private static String arquivoID = "idGeradoMarcas.txt";

    private String nomeMarca;

    public MarcaVeiculo() {
    }

    public static String getNomeArquivoDisco() {
        return NomeArquivoDisco;
    }

    public static void setNomeArquivoDisco(String NomeArquivoDisco) {
        MarcaVeiculo.NomeArquivoDisco = NomeArquivoDisco;
    }

    public static String getArquivoID() {
        return arquivoID;
    }

    public static void setArquivoID(String arquivoID) {
        MarcaVeiculo.arquivoID = arquivoID;
    }

    public MarcaVeiculo(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

    @Override
    public String toString() {
        return nomeMarca;
    }

}
