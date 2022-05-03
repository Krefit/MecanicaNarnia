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
public class Funcionario extends PessoaFisica {

// * O campo telefone secundáriio é o único que não é obrigatório ser preenchido quando se adiciona um novo fornecedor
    private String telefoneSecundario = "";
    private String especialidade = "";

    public Funcionario(String nome, String cpf, Date dataNascimento, String telefone, String email, Endereco endereco, String especialidade) {
        super(nome, cpf, dataNascimento, telefone, email, endereco);
        this.especialidade = especialidade;
    }

    public Funcionario(String nome, String cpf, Date dataNascimento, String telefone, String telefoneSecundario, String email, Endereco endereco, String especialidade) {
        super(nome, cpf, dataNascimento, telefone, email, endereco);
        this.telefoneSecundario = telefoneSecundario;
        this.especialidade = especialidade;
    }

    public String getTelefoneSecundario() {
        return telefoneSecundario;
    }

    public void setTelefoneSecundario(String telefoneSecundario) {
        this.telefoneSecundario = telefoneSecundario;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + telefoneSecundario + ";" + especialidade;
    }

}
