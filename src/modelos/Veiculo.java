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

    private static String NomeArquivoDisco = "veiculo.txt";
    private static String arquivoID = "idGeradoVeiculo.txt";

    private int idModelo;//talvez criar uma classe separada
    private int idMarca;
    private String chassi;
    private String renavam;
    private String tipoVeiculo;
    private String placa;
    private int anoFabricacao;
    private int anoModelo;

    private ArrayList<OrdemDeServico> listaOS;

    private int idDonoVeiculo;

    public Veiculo() {
    }

    public Veiculo(int idModelo, int idMarca, String chassi, String renavam, String tipoVeiculo, String placa, int anoFabricacao, int anoModelo, int idDonoVeiculo) {
        this.idModelo = idModelo;
        this.idMarca = idMarca;
        this.chassi = chassi;
        this.renavam = renavam;
        this.tipoVeiculo = tipoVeiculo;
        this.placa = placa;
        this.anoFabricacao = anoFabricacao;
        this.anoModelo = anoModelo;
        this.idDonoVeiculo = idDonoVeiculo;

        this.listaOS = new ArrayList<>();//a lista de OS inicia sempre vazia
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

    public ArrayList<OrdemDeServico> getListaOS() {
        return listaOS;
    }

    public int getIdDonoVeiculo() {
        return idDonoVeiculo;
    }

    public void setIdDonoVeiculo(int idDonoVeiculo) {
        this.idDonoVeiculo = idDonoVeiculo;
    }

    public void adicionaItemListaOS(OrdemDeServico os) throws Exception {
        if (os == null) {
            throw new Exception("Não se pode adicionar uma OS vazia!!");
        }
        listaOS.add(os);
    }

    public void removeItemListaOS(OrdemDeServico os) throws Exception {
        if (os == null) {
            throw new Exception("Não se pode remover uma OS vazia!!");
        }
        if (!listaOS.remove(os)) {
            throw new Exception("OS não encontrada");
        }
    }

    public OrdemDeServico buscarItemListaOS(OrdemDeServico os) throws Exception {
        if (os == null) {
            throw new Exception("Não se pode remover uma OS vazia!!");
        }
        if (!listaOS.contains(os)) {
            throw new Exception("OS não encontrada");
        }
        return listaOS.get(listaOS.indexOf(os));
    }

    @Override
    public String toString() {
        return idModelo + ";" + idMarca + ";" + chassi + ";" + renavam + ";" + tipoVeiculo + ";" + placa + ";" + anoFabricacao + ";" + anoModelo + ";" + idDonoVeiculo + ";" + listaOS.toString();
    }

}
