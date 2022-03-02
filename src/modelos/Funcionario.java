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
public class Funcionario {

    private String nome = "";
    private String endereco = "";
    private String telefone = "";

// * O campo telefone secundáriio é o único que não é obrigatório ser preenchido quando se adiciona um novo fornecedor
    private String telefoneSecundario = "";
    private String email = "";

// *A variável produtos, armazena os nomes e os respectivos preços de cada produto fornecido por esse fornecedor     
    private ArrayList<String> produtos[][];

    public Funcionario(String nome, String endereco, String telefone, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public Funcionario(String nome, String endereco, String telefone, String telefoneSecundario, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.telefoneSecundario = telefoneSecundario;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereço() {
        return endereco;
    }

    public void setEndereço(String endereço) {
        this.endereco = endereço;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefoneSecundario() {
        return telefoneSecundario;
    }

    public void setTelefoneSecundario(String telefoneSecundario) {
        this.telefoneSecundario = telefoneSecundario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String>[][] getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<String>[][] produtos) {
        this.produtos = produtos;
    }

}
