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
        situacao = SituacaoOrdemServico.EM_ABERTO;
        this.cadastroAtivo = true;
    }

    public OrdemDeServico(int codigo, String defeitoRelatado, int idServico, double valorMaoDeObra, Date dataEntrada,
            int idFuncionarioResponsavel, int idPeca, int quantidadePeca, double valorUnitarioDaPeca, int idVeiculo) throws Exception {
        Peca p = new ManipulaBancoPecas().buscar(idPeca);
        if (p == null) {
            System.out.println(idPeca);
            throw new Exception("A peça informada não existe no sistema!");
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
        p.reservarPecas(quantidadePeca);//  * reservando peças, no estoque
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

    public SituacaoOrdemServico getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoOrdemServico situacao) throws Exception {
        Peca p = new ManipulaBancoPecas().buscar(this.getIdPeca());
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
                            if (p != null) {//  * caso será usada alguma peça
                                p.retirarDoEstoque(this.quantidadePeca);
                            }
                            this.situacao = SituacaoOrdemServico.EM_EXECUCAO;
                        case CONCLUIDA:
                            //  * concluir
                            throw new Exception("O orçamento não foi aprovado, por isso não é possivel já ter sido concluido!");
                        case CANCELADA:
                            //  * cancelar
                            if (p != null) {//  * caso tenha alguma peça reservada
                                p.cancelarReservarPecas(quantidadePeca);
                            }
                            this.situacao = SituacaoOrdemServico.CANCELADA;
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
                        case CANCELADA:
                            //  * cancelar
                            if (p != null) {//    * alguuma peça será usada
                                p.cancelarReservarPecas(this.quantidadePeca);
                            }
                            this.situacao = SituacaoOrdemServico.CANCELADA;
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
