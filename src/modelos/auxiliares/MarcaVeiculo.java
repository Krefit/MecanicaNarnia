package modelos.auxiliares;

import java.util.Objects;

public class MarcaVeiculo {

    private static String NomeArquivoDisco = "Marcas.txt";
    private static String arquivoID = "idGeradoMarcas.txt";

    private String nomeMarca;
    private boolean cadastroAtivo = true;

    public MarcaVeiculo() {
    }

    public MarcaVeiculo(String nomeMarca) {
        this.nomeMarca = nomeMarca;
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

    public boolean isCadastroAtivo() {
        return cadastroAtivo;
    }

    public void setCadastroAtivo(boolean cadastroAtivo) {
        this.cadastroAtivo = cadastroAtivo;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.nomeMarca);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MarcaVeiculo other = (MarcaVeiculo) obj;
        return Objects.equals(this.nomeMarca, other.nomeMarca);
    }

    @Override
    public String toString() {
        return nomeMarca + ";" + isCadastroAtivo();
    }

}
