/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import enumerations.ServicosOferecidos;
import java.text.SimpleDateFormat;
import java.util.Date;
import persistencia.ManipulaBancoMarca;
import persistencia.ManipulaBancoPecas;

/**
 *
 * @author ALUNO
 */
public class OrdemDeServico {

    private final static String nomeArquivoDisco = "ordensDeServicos.txt";
    private final static String arquivoID = "idGeradoOrdensDeServicos.txt";

    public enum SitucaoOrdemServico {
        EM_ABERTO, EM_EXECUCAO, CONCLUIDA, CANCELADA;
    }

    private String defeitoRelatado;
    private String servico;
    private double valorMaoDeObra;
    private double valorPecas;
    private Date dataEntrada;
    private Date dataSaida = null;//só colocar depois de o serviço ser concluido
    private SitucaoOrdemServico situacao;
    private int idFuncionarioResponsavel;
    private int idPeca; //opcional
    private double valorUnitarioPeca; //opcional
    private int quantidadePecas; //opcional
    private int idVeiculo;

    public OrdemDeServico() {
    }

    public OrdemDeServico(String defeitoRelatado, String servico, double valorMaoDeObra, Date dataEntrada, int idFuncionarioResponsavel, int idVeiculo) {
        this.defeitoRelatado = defeitoRelatado;
        this.servico = servico;
        this.valorMaoDeObra = valorMaoDeObra;
        this.dataEntrada = dataEntrada;
        this.idFuncionarioResponsavel = idFuncionarioResponsavel;
        this.idVeiculo = idVeiculo;
    }

    public OrdemDeServico(String defeitoRelatado, String servico, double valorMaoDeObra,
            Date dataEntrada, int idFuncionarioResponsavel, int idVeiculo, int idPeca, int quantidadePecas) throws Exception {
        this.defeitoRelatado = defeitoRelatado;
        this.servico = servico;
        this.valorMaoDeObra = valorMaoDeObra;
        this.dataEntrada = dataEntrada;
        this.situacao = SitucaoOrdemServico.EM_ABERTO;
        this.idFuncionarioResponsavel = idFuncionarioResponsavel;
        this.idPeca = idPeca;
        this.quantidadePecas = quantidadePecas;
        this.idVeiculo = idVeiculo;
        this.valorUnitarioPeca = new ManipulaBancoPecas().buscar(idPeca).getValorPeca();
        this.valorPecas = this.quantidadePecas * this.valorUnitarioPeca;
        //calcular depois o valor total do servico
    }

    public static String getNomeArquivoDisco() {
        return nomeArquivoDisco;
    }

    public static String getArquivoID() {
        return arquivoID;
    }

    public String getDefeitoRelatado() {
        return defeitoRelatado;
    }

    public void setDefeitoRelatado(String defeitoRelatado) {
        this.defeitoRelatado = defeitoRelatado;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public int getIdFuncionarioResponsavel() {
        return idFuncionarioResponsavel;
    }

    public void setIdFuncionarioResponsavel(int idFuncionarioResponsavel) {
        this.idFuncionarioResponsavel = idFuncionarioResponsavel;
    }

    public double getValorServico() {
        return valorMaoDeObra;
    }

    public void setValorServico(double valorServico) {
        this.valorMaoDeObra = valorServico;
    }

    public int getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(int idPeca) {
        this.idPeca = idPeca;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public SitucaoOrdemServico getSituacao() {
        return situacao;
    }

    public void setSituacao(SitucaoOrdemServico situacao) {//tem q fazer um monte de validacoes
        this.situacao = situacao;
    }

    public int getidVeiculo() {
        return idVeiculo;
    }

    @Override
    public String toString() {
        return defeitoRelatado + ";" + servico + ";" + valorMaoDeObra
                + ";" + valorPecas + ";" + dataEntrada + ";" + dataSaida
                + ";" + situacao + ";" + idFuncionarioResponsavel
                + ";" + idPeca + ";" + valorUnitarioPeca
                + ";" + quantidadePecas + ";" + idVeiculo;
    }

}
