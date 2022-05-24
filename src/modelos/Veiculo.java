/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.ArrayList;

/**
 *
 * @author ALUNO
 */
public class Veiculo {

    private final static String NomeArquivoDisco = "veiculo.txt";
    private final static String arquivoID = "idGeradoVeiculo.txt";

    private int idModelo;//talvez criar uma classe separada
    private int idMarca;
    private String chassi;
    private String renavam;
    private String tipoVeiculo;
    private String placa;
    private int anoFabricacao;
    private int anoModelo;
    private int quilometragem;

    private int idDonoVeiculo;

    public Veiculo() {
    }

    public Veiculo(int idModelo, int idMarca, String chassi, String renavam,
            String tipoVeiculo, String placa, int anoFabricacao, int anoModelo, int quilometragem, int idDonoVeiculo) {
        this.idModelo = idModelo;
        this.idMarca = idMarca;
        this.chassi = chassi;
        this.renavam = renavam;
        this.tipoVeiculo = tipoVeiculo;
        this.placa = placa;
        this.anoFabricacao = anoFabricacao;
        this.anoModelo = anoModelo;
        this.quilometragem = quilometragem;
        this.idDonoVeiculo = idDonoVeiculo;
    }

    public static String getNomeArquivoDisco() {
        return NomeArquivoDisco;
    }

    public static String getArquivoID() {
        return arquivoID;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public int getIdDonoVeiculo() {
        return idDonoVeiculo;
    }

    public void setIdDonoVeiculo(int idDonoVeiculo) {
        this.idDonoVeiculo = idDonoVeiculo;
    }

    @Override
    public String toString() {
        return idModelo + ";" + idMarca + ";" + chassi + ";" + renavam + ";" + tipoVeiculo + ";" + placa + ";" + anoFabricacao
                + ";" + anoModelo + ";" + quilometragem + ";" + idDonoVeiculo;
    }

}
