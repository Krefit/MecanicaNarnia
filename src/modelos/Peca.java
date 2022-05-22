/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author ALUNO
 */
public class Peca {

    private final static String nomeArquivoDisco = "pecas.txt";
    private final static String arquivoID = "idGeradoPecas.txt";

    private String codigoPeca;
    private String descricao;
    private float valorPeca;
    private int quantidadeNoEstoque;
    private int quantidadeReservadas;
    private int estoquequantidadeMinima;

    public Peca() {
    }

    public Peca(String codigoPeca, String descricao, float valorPeca) {
        this.codigoPeca = codigoPeca;
        this.descricao = descricao;
        this.valorPeca = valorPeca;
    }

    public Peca(String codigoPeca, String descricao, float valorPeca, int quantidadeNoEstoque, int estoquequantidadeMinima) {
        this.codigoPeca = codigoPeca;
        this.descricao = descricao;
        this.valorPeca = valorPeca;
        this.quantidadeNoEstoque = quantidadeNoEstoque;
        this.estoquequantidadeMinima = estoquequantidadeMinima;
    }

    public Peca(String codigoPeca, String descricao, float valorPeca, int estoquequantidadeMinima) {
        this.codigoPeca = codigoPeca;
        this.descricao = descricao;
        this.valorPeca = valorPeca;
        this.estoquequantidadeMinima = estoquequantidadeMinima;
    }

    public static String getNomeArquivoDisco() {
        return nomeArquivoDisco;
    }

    public static String getArquivoID() {
        return arquivoID;
    }

    public String getCodigoPeca() {
        return codigoPeca;
    }

    public void setCodigoPeca(String codigoPeca) {
        this.codigoPeca = codigoPeca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValorPeca() {
        return valorPeca;
    }

    public void setValorPeca(float valorPeca) {
        this.valorPeca = valorPeca;
    }

    public int getQuantidadeNoEstoque() {
        return quantidadeNoEstoque;
    }

    public void setQuantidadeNoEstoque(int quantidadeNoEstoque) {
        this.quantidadeNoEstoque = quantidadeNoEstoque;
    }

    public int getQuantidadeReservadas() {
        return quantidadeReservadas;
    }

    public void setQuantidadeReservadas(int quantidadeReservadas) {
        this.quantidadeReservadas = quantidadeReservadas;
    }

    public int getEstoquequantidadeMinima() {
        return estoquequantidadeMinima;
    }

    public void setEstoquequantidadeMinima(int estoquequantidadeMinima) {
        this.estoquequantidadeMinima = estoquequantidadeMinima;
    }

    @Override
    public String toString() {
        return codigoPeca + ";" + descricao + ";" + String.format("%.2f", valorPeca) + ";" + quantidadeNoEstoque + ";" + quantidadeReservadas + ";" + estoquequantidadeMinima;
    }

}
