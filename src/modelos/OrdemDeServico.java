/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.text.SimpleDateFormat;
import java.util.Date;
import persistencia.ManipulaBancoPecas;
import persistencia.ManipulaBancoServicos;

/**
 *
 * @author ALUNO
 */
public class OrdemDeServico {

    public enum SitucaoOrdemServico {
        EM_ABERTO, EM_EXECUCAO, CONCLUIDA, CANCELADA;
    }

    private final static String nomeArquivoDisco = "ordensDeServicos.txt";
    private final static String arquivoID = "idGeradoOrdensDeServicos.txt";
    private final static String arquivoCodigo = "codigoGeradoOrdensDeServico.txt";

    private int codigo;
    private String defeitoRelatado;
    private int idServico;
    private double valorMaoDeObra;
    private Date dataEntrada;
    private Date dataSaida = null;//só colocar depois de o serviço ser concluido
    private SitucaoOrdemServico situacao;
    private int idFuncionarioResponsavel;
    private int idPeca; //opcional
    private int quantidadePeca; //opciona
    private double valorUnitarioPeca;//apenas se usar peca
    private int idVeiculo;

    private boolean cadastroAtivo;//    * não foi excluido

    public OrdemDeServico() {
    }

    public OrdemDeServico(int codigo, String defeitoRelatado, int idServico, double valorMaoDeObra, Date dataEntrada,
            int idFuncionarioResponsavel, int idVeiculo) throws Exception {
        this.codigo = codigo;
        this.defeitoRelatado = defeitoRelatado;
        this.idServico = idServico;
        this.valorMaoDeObra = valorMaoDeObra;
        this.dataEntrada = dataEntrada;
        this.idFuncionarioResponsavel = idFuncionarioResponsavel;
        this.idVeiculo = idVeiculo;
        situacao = SitucaoOrdemServico.EM_ABERTO;
        this.cadastroAtivo = true;
    }

    public OrdemDeServico(int codigo, String defeitoRelatado, int idServico, double valorMaoDeObra, Date dataEntrada,
            int idFuncionarioResponsavel, int idPeca, int quantidadePeca, double valorUnitarioDaPeca, int idVeiculo) throws Exception {
        this.codigo = codigo;
        this.defeitoRelatado = defeitoRelatado;
        this.idServico = idServico;
        this.valorMaoDeObra = valorMaoDeObra;
        this.dataEntrada = dataEntrada;
        this.idFuncionarioResponsavel = idFuncionarioResponsavel;
        this.idPeca = idPeca;
        this.quantidadePeca = quantidadePeca;
        this.idVeiculo = idVeiculo;
        this.valorUnitarioPeca = valorUnitarioDaPeca;
        situacao = SitucaoOrdemServico.EM_ABERTO;
        this.cadastroAtivo = true;
    }

    public static String getArquivoCodigo() {
        return arquivoCodigo;
    }

    public static String getNomeArquivoDisco() {
        return nomeArquivoDisco;
    }

    public static String getArquivoID() {
        return arquivoID;
    }

    public boolean isCadastroAtivo() {
        return cadastroAtivo;
    }

    public void setCadastroAtivo(boolean cadastroAtivo) {
        this.cadastroAtivo = cadastroAtivo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDefeitoRelatado() {
        return defeitoRelatado;
    }

    public void setDefeitoRelatado(String defeitoRelatado) {
        this.defeitoRelatado = defeitoRelatado;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setServico(int idServico) {
        this.idServico = idServico;
    }

    public int getIdFuncionarioResponsavel() {
        return idFuncionarioResponsavel;
    }

    public void setIdFuncionarioResponsavel(int idFuncionarioResponsavel) {
        this.idFuncionarioResponsavel = idFuncionarioResponsavel;
    }

    public double getValorMaoDeObra() {
        return valorMaoDeObra;
    }

    public void setValorMaoDeObra(double valorMaoDeObra) {
        this.valorMaoDeObra = valorMaoDeObra;
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

    public int getQuantidadePeca() {
        return quantidadePeca;
    }

    public void setQuantidadePeca(int quantidadePeca) {
        this.quantidadePeca = quantidadePeca;
    }

    public double getValorUnitarioPeca() {
        return valorUnitarioPeca;
    }

    public void setValorUnitarioPeca(double valorUnitarioPeca) {
        this.valorUnitarioPeca = valorUnitarioPeca;
    }

    public double calcularValorTotal() {
        return this.valorMaoDeObra + (this.quantidadePeca * this.valorUnitarioPeca);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (dataSaida != null) {
            return codigo + ";" + defeitoRelatado + ";" + idServico + ";" + String.format("%.2f", valorMaoDeObra).replace(",", ".") + ";" + sdf.format(dataEntrada) + ";"
                    + sdf.format(dataSaida) + ";" + situacao + ";" + idFuncionarioResponsavel + ";" + idPeca + ";"
                    + quantidadePeca + ";" + String.format("%.2f", valorUnitarioPeca).replace(",", ".") + ";" + idVeiculo + ";" + cadastroAtivo;
        } else {
            return codigo + ";" + defeitoRelatado + ";" + idServico + ";" + String.format("%.2f", valorMaoDeObra).replace(",", ".") + ";" + sdf.format(dataEntrada) + ";"
                    + null + ";" + situacao + ";" + idFuncionarioResponsavel + ";" + idPeca + ";"
                    + quantidadePeca + ";" + String.format("%.2f", valorUnitarioPeca).replace(",", ".") + ";" + idVeiculo + ";" + cadastroAtivo;
        }
    }
}
