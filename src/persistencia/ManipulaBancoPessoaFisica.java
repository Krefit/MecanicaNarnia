/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import enumerations.EstadosBrazil;
import geradorId.GeradorId;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import modelos.PessoaFisica;
import modelos.auxiliares.Endereco;

/**
 *
 * @author tanak
 */
public class ManipulaBancoPessoaFisica implements IManipulaBanco<PessoaFisica> {

    @Override
    public PessoaFisica parse(String dadosCompletos) throws Exception {
        String[] dados = dadosCompletos.split(";");
//  * id, nome, cpf, data de nascimento (dd/MM/yyyy),
//  * array de telefones, email, endereco, cadastro está ativo
        if (dados.length != 8) {
            System.out.println(dadosCompletos);
            throw new Exception("Dados incorretos" + dadosCompletos);
        }
        String[] dadosEndereco = dados[6].split(",");
        if (dadosEndereco.length != 8) {
            throw new Exception("Dados incorretos, de endereço");

        }
        Endereco endereco = new Endereco(dadosEndereco[0],//    * tipo de logradouro
                dadosEndereco[1],//    * logradouro
                dadosEndereco[2],//    * numero
                dadosEndereco[3],//    * complemento
                dadosEndereco[4],//    * bairro
                dadosEndereco[5],//    * cidade
                Enum.valueOf(EstadosBrazil.class,
                        dadosEndereco[6]),//    * estado, seguindo o Enum
                dadosEndereco[7]);//    * cep

        Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dados[3]);

        String[] telefones = dados[4].substring(dados[4].indexOf("[") + 1, dados[4].lastIndexOf("]")).split(",");

        PessoaFisica p = new PessoaFisica(dados[1],// * nome
                dados[2],// * CPF
                dataNascimento,// * data de nascimento
                dados[5],// * email
                endereco,// * endereco
                telefones);

        if (dados[7].equals(String.valueOf(false))) {
            p.setCadastroAtivo(false);
        }
        return p;
    }

    @Override
    public String getNomeDoArquivoNoDisco() {
        return PessoaFisica.getNomeArquivoDisco();
    }

    @Override
    public int getID(PessoaFisica obj) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(PessoaFisica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                PessoaFisica v = parse(linha);// * parsing linha
                if (v.equals(obj) && v.isCadastroAtivo()) {//  * encontrou
                    return Integer.parseInt(linha.split(";")[0]);// * retornando o id
                }
                linha = br.readLine();
            }
        }
        return 0;// * objeto não encontrado
    }

    @Override
    public String getNomeArquivoID() {
        return PessoaFisica.getArquivoID();
    }

    @Override
    public boolean isCadastroAtivo(PessoaFisica obj) {
        return obj.isCadastroAtivo();
    }

    @Override
    public PessoaFisica setCadastroAtivo(PessoaFisica obj, boolean flag) {
        obj.setCadastroAtivo(flag);
        return obj;
    }

    @Override
    public int buscar(String dado) throws Exception {
        ArrayList<PessoaFisica> listaPessoas = buscarTodos();
        for (PessoaFisica p : listaPessoas) {
            if (p.getCpf().equals(dado) && p.isCadastroAtivo()) {//    * encontrou
                return getID(p);//  * retornando o id
            }
        }
        return 0;// * objetoo não encontrado
    }

}
