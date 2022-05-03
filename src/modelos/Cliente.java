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
public class Cliente extends Pessoa {

    public Cliente(int id, String nome, String cpf, String endereco, ArrayList<String> telefone, String email) throws Exception {
        super(id, nome, cpf, telefone, email, endereco);

    }

}
