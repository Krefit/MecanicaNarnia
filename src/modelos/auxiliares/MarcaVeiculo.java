/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.auxiliares;

/**
 *
 * @author tanak
 */
public class MarcaVeiculo {

    private static String NomeArquivoDisco = "Marcas.txt";
    private static String arquivoID = "idGeradoMarcas.txt";

    private String nomeMarca;

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
