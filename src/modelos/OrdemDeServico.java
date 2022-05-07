/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import enumerations.ServicosOferecidos;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ALUNO
 */
public class OrdemDeServico {

    public enum SitucaoOrdemServico {
        EM_ABERTO, EM_EXECUCAO, CONCLUIDA, CANCELADA;
    }

    private String defeitoRelatado;
    private String servico;
    private float valorServico;
    private Date dataEntrada;
    private Date dataSaida = null;//só colocar depois de o serviço ser concluido
    private SitucaoOrdemServico situacao;
    private int idFuncionarioResponsavel;
    private int idPeca; //opcional
    private int idVeiculo;

    public OrdemDeServico() {
    }

    public OrdemDeServico(String defeitoRelatado, String servico,
            int idFuncionarioResponsavel, float valorServico, Date dataEntrada,
            SitucaoOrdemServico situacao, int idVeiculo) {
        this.defeitoRelatado = defeitoRelatado;
        this.servico = servico;
        this.idFuncionarioResponsavel = idFuncionarioResponsavel;
        this.valorServico = valorServico;
        this.dataEntrada = dataEntrada;
        this.situacao = situacao;
        this.idVeiculo = idVeiculo;
    }

    public OrdemDeServico(String defeitoRelatado, String servico,
            int idFuncionarioResponsavel, float valorServico, Date dataEntrada, SitucaoOrdemServico situacao,
            int idVeiculo, int idPeca) {
        this.defeitoRelatado = defeitoRelatado;
        this.servico = servico;
        this.idFuncionarioResponsavel = idFuncionarioResponsavel;
        this.valorServico = valorServico;
        this.dataEntrada = dataEntrada;
        this.situacao = situacao;
        this.idVeiculo = idVeiculo;
        this.idPeca = idPeca;
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

    public float getValorServico() {
        return valorServico;
    }

    public void setValorServico(float valorServico) {
        this.valorServico = valorServico;
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (dataSaida == null) {
            return defeitoRelatado + ";" + servico + ";" + idFuncionarioResponsavel + ";" + valorServico + ";"
                    + sdf.format(dataEntrada) + ";" + "null" + ";" + situacao + ";" + idVeiculo + ";" + idPeca;
        }
        return defeitoRelatado + ";" + servico + ";" + idFuncionarioResponsavel + ";" + valorServico + ";"
                + sdf.format(dataEntrada) + ";" + sdf.format(dataSaida) + ";" + situacao + ";" + idVeiculo + ";" + idPeca;
    }

}
