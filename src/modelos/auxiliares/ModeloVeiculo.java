
package modelos.auxiliares;

public class ModeloVeiculo {

    private static String NomeArquivoDisco = "Modelos.txt";
    private static String arquivoID = "idGeradoModelos.txt";

    private String NomeModelo;
    private int idMarca;

    public ModeloVeiculo() {
    }

    public ModeloVeiculo(String NomeModelo, int idMarca) {
        this.NomeModelo = NomeModelo;
        this.idMarca = idMarca;
    }

    public static String getNomeArquivoDisco() {
        return NomeArquivoDisco;
    }

    public static void setNomeArquivoDisco(String NomeArquivoDisco) {
        ModeloVeiculo.NomeArquivoDisco = NomeArquivoDisco;
    }

    public static String getArquivoID() {
        return arquivoID;
    }

    public static void setArquivoID(String arquivoID) {
        ModeloVeiculo.arquivoID = arquivoID;
    }

    public String getNomeModelo() {
        return NomeModelo;
    }

    public void setNomeModelo(String NomeModelo) {
        this.NomeModelo = NomeModelo;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    @Override
    public String toString() {
        return NomeModelo + ";" + idMarca;
    }

}
