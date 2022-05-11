/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import modelos.auxiliares.Endereco;
import java.util.Date;
import javax.swing.JOptionPane;
import persistencia.ManipulaBancoClientePEssoaFisica;

/**
 *
 * @author ALUNO
 */
public class Cliente_PessoaFisica extends PessoaFisica {

    private static String nomeArquivoDisco = "clientePessoaFisica.txt";
    private static String arquivoID = "idGeradoClientePessoaFisica.txt";

    public Cliente_PessoaFisica(String telefone, String email, Endereco endereco) {
        super(telefone, email, endereco);
    }

    public Cliente_PessoaFisica(String nome, String cpf, Date dataNascimento, String telefone, String email, Endereco endereco) {
        super(nome, cpf, dataNascimento, telefone, email, endereco);
    }

    public void incluir(Cliente_PessoaFisica objeto) throws Exception {
        new ManipulaBancoClientePEssoaFisica().incluir(objeto);
    }

    public static String getNomeArquivoDisco() {
        return nomeArquivoDisco;
    }

    public static String getarquivoID() {
        return arquivoID;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
