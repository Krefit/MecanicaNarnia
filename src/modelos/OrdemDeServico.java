/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.Date;

/**
 *
 * @author ALUNO
 */
public class OrdemDeServico {

    public enum SitucaoOrdemServico {
        EM_ABERTO, EM_EXECUCAO, CONCLUIDA, CANCELADA;
    }
    private final int id;

    private String defeitoRelatado;
    private Servico servico;

    private Funcionario responsavel;
    private Peca peca = null; //opcional

    private float valor;

    private Date dataEntrada;
    private Date dataSaida = null;//só colocar depois de o serviço ser concluido

    private SitucaoOrdemServico situacao;

    private Cliente donoVeiculo;
    private Veiculo veiculo;

    public OrdemDeServico() {
        this.id = 0;
    }

    public OrdemDeServico(int id, String defeitoRelatado, Servico servico,
             Funcionario responsavel, float valor, Date dataEntrada,
            SitucaoOrdemServico situacao, Cliente donoVeiculo, Veiculo veiculo) {
        this.id = id;
        this.defeitoRelatado = defeitoRelatado;
        this.servico = servico;
        this.responsavel = responsavel;
        this.valor = valor;
        this.dataEntrada = dataEntrada;
        this.situacao = situacao;
        this.donoVeiculo = donoVeiculo;
        this.veiculo = veiculo;
    }

    public OrdemDeServico(int id, String defeitoRelatado, Servico servico,
             Funcionario responsavel, float valor, Date dataEntrada, SitucaoOrdemServico situacao,
            Cliente donoVeiculo, Veiculo veiculo, Peca peca) {
        this.id = id;
        this.defeitoRelatado = defeitoRelatado;
        this.servico = servico;
        this.responsavel = responsavel;
        this.valor = valor;
        this.dataEntrada = dataEntrada;
        this.situacao = situacao;
        this.donoVeiculo = donoVeiculo;
        this.veiculo = veiculo;
        this.peca = peca;
    }

    public String getDefeitoRelatado() {
        return defeitoRelatado;
    }

    public void setDefeitoRelatado(String defeitoRelatado) {
        this.defeitoRelatado = defeitoRelatado;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
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

    public void setSituacao(SitucaoOrdemServico situacao) {//tem q fazer um mooooonte de validacoes
        this.situacao = situacao;
    }

    public int getId() {
        return id;
    }

    public Cliente getDonoVeiculo() {
        return donoVeiculo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
}
