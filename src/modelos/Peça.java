/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.Arrays;

/**
 *
 * @author ALUNO
 */
public class Peça {

    private final int id;
    private String nome;
    String fornecedor;
    String[] veiculosCompativeis;
    int quantidadeNoEstoque;

    public Peça() {
        this.id = 0;
    }

    public Peça(int id, String nome, int quantidadeNoEstoque, String fornecedor, String... veiculosCompativeis) {
        this.id = id;
        this.nome = nome;
        this.quantidadeNoEstoque = quantidadeNoEstoque;
        this.fornecedor = fornecedor;
        this.veiculosCompativeis = veiculosCompativeis;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String[] getVeiculosCompativeis() {
        return veiculosCompativeis;
    }

    public void setVeiculosCompativeis(String[] veiculosCompativeis) {
        this.veiculosCompativeis = veiculosCompativeis;
    }

    public int getQuantidadeNoEstoque() {
        return quantidadeNoEstoque;
    }

    public void setQuantidadeNoEstoque(int quantidadeNoEstoque) {
        this.quantidadeNoEstoque = quantidadeNoEstoque;
    }

    @Override
    public String toString() {
        StringBuilder veiculos = new StringBuilder();

        veiculos.append(veiculosCompativeis[0]);
        for (int i = 1; i < veiculosCompativeis.length; i++) {

            veiculos.append(",").append(veiculosCompativeis[i]);
        }
        return id + ";" + nome + ";" + fornecedor + ";" + quantidadeNoEstoque
                + ";" + veiculos;
    }

}
