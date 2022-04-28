/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author ALUNO
 */
public class Funcionario extends Pessoa {

// * O campo telefone secundáriio é o único que não é obrigatório ser preenchido quando se adiciona um novo fornecedor
    private String telefoneSecundario = "";

    public Funcionario(String nome, String cpf, String endereco, String telefone,
             String email) throws Exception {

        super(geradorId.GeradorId.getID(), nome, cpf, telefone, email, endereco);

    }

    public Funcionario(String nome, String cpf, String endereco, String telefone,
            String telefoneSecundario, String email) throws Exception {
        super(geradorId.GeradorId.getID(), nome, cpf, telefone, email, endereco);
        this.telefoneSecundario = telefoneSecundario;
    }

    public String getTelefoneSecundario() {
        return telefoneSecundario;
    }

    public void setTelefoneSecundario(String telefoneSecundario) {
        this.telefoneSecundario = telefoneSecundario;
    }
}
