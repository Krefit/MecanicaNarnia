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
public class Funcionario extends Pessoa {

// * O campo telefone secundáriio é o único que não é obrigatório ser preenchido quando se adiciona um novo fornecedor
    private String telefoneSecundario = "";

// *A variável produtos, armazena os nomes e os respectivos preços de cada produto fornecido por esse fornecedor     
    private ArrayList<String> produtos[][];

    public Funcionario(String nome, String cpf, String endereco, String telefone, String email) {

        super(nome, cpf, telefone, email, endereco);

    }

    public Funcionario(String nome, String cpf, String endereco, String telefone, String telefoneSecundario, String email) {
        super(nome, cpf, telefone, email, endereco);
        this.telefoneSecundario = telefoneSecundario;
    }

    public String getTelefoneSecundario() {
        return telefoneSecundario;
    }

    public void setTelefoneSecundario(String telefoneSecundario) {
        this.telefoneSecundario = telefoneSecundario;
    }

    
    public ArrayList<String>[][] getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<String>[][] produtos) {
        this.produtos = produtos;
    }

}
