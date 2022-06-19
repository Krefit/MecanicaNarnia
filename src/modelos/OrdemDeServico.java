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

    public enum SituacaoOrdemServico {
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
    private SituacaoOrdemServico situacao;
    private int idFuncionarioResponsavel;
    private int idPeca; //opcional
    private int quantidadePeca; //opciona
    private double valorUnitarioPeca;//apenas se usar peca
    private int idVeiculo;
    private double porcentagemDesconto;

    private boolean cadastroAtivo;//    * não foi excluido

    public OrdemDeServico() {
    }

    public OrdemDeServico(int codigo, String defeitoRelatado, int idServico, double valorMaoDeObra, Date dataEntrada,
            int idFuncionarioResponsavel, int idVeiculo, double porcentagemDesconto) throws Exception {
        if (porcentagemDesconto < 0 || porcentagemDesconto > 100) {
            throw new Exception("Tentou usar uma porcentagem de desconto de: " + porcentagemDesconto + "%, informe um valor válido");
        }
        this.codigo = codigo;
        this.defeitoRelatado = defeitoRelatado;
        this.idServico = idServico;
        this.valorMaoDeObra = valorMaoDeObra;
        this.dataEntrada = dataEntrada;
        this.idFuncionarioResponsavel = idFuncionarioResponsavel;
        this.idVeiculo = idVeiculo;
        situacao = SituacaoOrdemServico.EM_ABERTO;
        this.cadastroAtivo = true;
        this.porcentagemDesconto = porcentagemDesconto;
    }

    public OrdemDeServico(int codigo, String defeitoRelatado, int idServico, double valorMaoDeObra, Date dataEntrada,
            int idFuncionarioResponsavel, int idPeca, int quantidadePeca, double valorUnitarioDaPeca, int idVeiculo, double porcentagemDesconto) throws Exception {
        if (porcentagemDesconto < 0 || porcentagemDesconto > 100) {
            throw new Exception("Tentou usar uma porcentagem de desconto de: " + porcentagemDesconto + "%, informe um valor válido");
        }
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
        situacao = SituacaoOrdemServico.EM_ABERTO;
        this.cadastroAtivo = true;
        this.porcentagemDesconto = porcentagemDesconto;
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

    public double getPorcentagemDesconto() {
        return porcentagemDesconto;
    }

    public void setPorcentagemDesconto(double porcentagemDesconto) throws Exception {
        if (porcentagemDesconto < 0 || porcentagemDesconto > 100) {
            throw new Exception("Tentou usar uma porcentagem de desconto de: " + porcentagemDesconto + "%, informe um valor válido");
        }
        this.porcentagemDesconto = porcentagemDesconto;
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

    public SituacaoOrdemServico getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoOrdemServico situacao) throws Exception {
        OUTER:
        switch (this.situacao) {//  * situação em que a OS está
            case EM_ABERTO:
                // * orçamento 
                if (situacao != null) {
                    switch (situacao) {//   * para qual estado ela será mudada
                        case EM_ABERTO:
                            //   * transformar em orçamento
                            throw new Exception("Já é um orçamento!");
                        case EM_EXECUCAO:
                            //    * tranformar em OS
                            this.situacao = SituacaoOrdemServico.EM_EXECUCAO;
                            break;
                        case CONCLUIDA:
                            //  * concluir
                            throw new Exception("O orçamento não foi aprovado, por isso não é possivel já ter sido concluido!");
                        case CANCELADA:
                            //  * cancelar
                            this.situacao = SituacaoOrdemServico.CANCELADA;
                            break OUTER;
                        default:
                            break OUTER;
                    }
                }
                break;
            case EM_EXECUCAO:
                // * orçamento aprovado
                if (situacao != null) {
                    switch (situacao) {//   * para qual estado ela será mudada
                        case EM_ABERTO:
                            //   * transformar em orçamento
                            throw new Exception("Uma ordem de serviço não pode ser transformada em orçamento novamente!");
                        case EM_EXECUCAO:
                            //    * tranformar em OS
                            throw new Exception("Esta ordem de serviço já está sendo executada");
                        case CONCLUIDA:
                            //  * concluir
                            this.situacao = SituacaoOrdemServico.CONCLUIDA;
                            this.dataSaida = new Date();
                            break OUTER;
                        case CANCELADA:
                            //  * cancelar
                            this.situacao = SituacaoOrdemServico.CANCELADA;
                            break OUTER;
                        default:
                            break OUTER;
                    }
                }
            case CONCLUIDA:
                // * orçamento aprovado
                throw new Exception("Esta ordem de serviço já foi concluida!");
            case CANCELADA:
                throw new Exception("Este orçamento não foi aprovado!");
            default:
                throw new AssertionError("falha ao mudar status do Orçamento/Ordem de serviço/nota fiscal");
        }
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

    public double calcularValorTotalSemDesconto() {
        return this.valorMaoDeObra + (this.quantidadePeca * this.valorUnitarioPeca);
    }

    public double calcularValorTotalComDesconto() {
        return calcularValorTotalSemDesconto() - (calcularValorTotalSemDesconto() * (porcentagemDesconto / 100.0));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.codigo;
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
        final OrdemDeServico other = (OrdemDeServico) obj;
        return this.codigo == other.codigo;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (dataSaida != null) {
            return codigo + ";" + defeitoRelatado + ";" + idServico + ";" + String.format("%.2f", valorMaoDeObra).replace(",", ".") + ";" + sdf.format(dataEntrada) + ";"
                    + sdf.format(dataSaida) + ";" + situacao + ";" + idFuncionarioResponsavel + ";" + idPeca + ";"
                    + quantidadePeca + ";" + String.format("%.2f", valorUnitarioPeca).replace(",", ".") + ";" + idVeiculo + ";" + cadastroAtivo + ";" + porcentagemDesconto;
        } else {
            return codigo + ";" + defeitoRelatado + ";" + idServico + ";" + String.format("%.2f", valorMaoDeObra).replace(",", ".") + ";" + sdf.format(dataEntrada) + ";"
                    + null + ";" + situacao + ";" + idFuncionarioResponsavel + ";" + idPeca + ";"
                    + quantidadePeca + ";" + String.format("%.2f", valorUnitarioPeca).replace(",", ".") + ";" + idVeiculo + ";" + cadastroAtivo + ";" + porcentagemDesconto;
        }
    }
}
