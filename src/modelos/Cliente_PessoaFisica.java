/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.Date;
import javax.swing.JOptionPane;
import persistencia.ManipulaBanco;

/**
 *
 * @author ALUNO
 */
public class Cliente_PessoaFisica extends PessoaFisica{

    String nomeArquivoDisco = "clientePessoaFisica.txt";
    String arquivoID = "idGeradoClientePessoaFisica.txt";
    public Cliente_PessoaFisica(String telefone, String email, Endereco endereco) {
        super(telefone, email, endereco);
    }

    public Cliente_PessoaFisica(String nome, String cpf, Date dataNascimento, String telefone, String email, Endereco endereco) {
        super(nome, cpf, dataNascimento, telefone, email, endereco);
    }

    
    public void incluir(Cliente_PessoaFisica objeto) throws Exception {
        ManipulaBanco.incluir(objeto); 
    }

    public String getNomeArquivoDisco() {
        return nomeArquivoDisco;
    }
    
    public String getarquivoID(){
        return arquivoID;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    
    
    
    
   

}
