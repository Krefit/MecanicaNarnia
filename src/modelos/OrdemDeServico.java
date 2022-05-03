/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import enumerations.ServicosOferecidos;
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
    private ServicosOferecidos servico;
    private Funcionario responsavel;
    private Peca peca = null; //opcional
    private float valorServico;
    private float valorPecas = 0;
    private Date dataEntrada;
    private Date dataSaida = null;//só colocar depois de o serviço ser concluido
    private SitucaoOrdemServico situacao;
    private Cliente_PessoaFisica donoVeiculo;
    private Veiculo veiculo;

    public OrdemDeServico() {
    }

    public OrdemDeServico(String defeitoRelatado, ServicosOferecidos servico,
            Funcionario responsavel, float valorServico, Date dataEntrada,
            SitucaoOrdemServico situacao, Cliente_PessoaFisica donoVeiculo, Veiculo veiculo) {
        this.defeitoRelatado = defeitoRelatado;
        this.servico = servico;
        this.responsavel = responsavel;
        this.valorServico = valorServico;
        this.dataEntrada = dataEntrada;
        this.situacao = situacao;
        this.donoVeiculo = donoVeiculo;
        this.veiculo = veiculo;
    }

    public OrdemDeServico(String defeitoRelatado, ServicosOferecidos servico,
            Funcionario responsavel, float valorServico, Date dataEntrada, SitucaoOrdemServico situacao,
            Cliente_PessoaFisica donoVeiculo, Veiculo veiculo, Peca peca, float valorPecas) {
        this.defeitoRelatado = defeitoRelatado;
        this.servico = servico;
        this.responsavel = responsavel;
        this.valorServico = valorServico;
        this.dataEntrada = dataEntrada;
        this.situacao = situacao;
        this.donoVeiculo = donoVeiculo;
        this.veiculo = veiculo;
        this.peca = peca;
        this.valorPecas = valorPecas;
    }

    public String getDefeitoRelatado() {
        return defeitoRelatado;
    }

    public void setDefeitoRelatado(String defeitoRelatado) {
        this.defeitoRelatado = defeitoRelatado;
    }

    public ServicosOferecidos getServico() {
        return servico;
    }

    public void setServico(ServicosOferecidos servico) {
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

    public float getvalorPecas() {
        return valorPecas;
    }

    public void setvalorPecas(float valor) {
        this.valorPecas = valor;
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

    public Cliente_PessoaFisica getDonoVeiculo() {
        return donoVeiculo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public float valorTotal() {
        return this.valorServico + this.valorPecas;
    }
}
