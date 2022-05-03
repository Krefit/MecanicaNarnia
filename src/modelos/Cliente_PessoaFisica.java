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
public class Cliente_PessoaFisica extends PessoaFisica {

    
    public Cliente_PessoaFisica(String telefone, String email, Endereco endereco) {
        super(telefone, email, endereco);
    }

    public Cliente_PessoaFisica(String nome, String cpf, Date dataNascimento, String telefone, String email, Endereco endereco) {
        super(nome, cpf, dataNascimento, telefone, email, endereco);
    }

}
