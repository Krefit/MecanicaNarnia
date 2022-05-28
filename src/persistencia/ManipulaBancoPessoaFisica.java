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
    public void incluir(PessoaFisica obj) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PessoaFisica.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(PessoaFisica.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
            //fecha arquivo
        }
    }

    @Override
    public int buscar(PessoaFisica obj) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(PessoaFisica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.endsWith(obj.toString())) {//ignorando o iD, pois isso não fica salvo no objeto

                    System.out.println(linha);
                    return Integer.parseInt(linha.substring(0, linha.indexOf(";")));//  * retornando o id do objeto
                }

                linha = br.readLine();
            }
        }
        return 0;//  * objeto não encontrado
    }

    public int buscar(String cpf) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(PessoaFisica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
                if (linha.contains(cpf) && dados[7].equals("true")) {
//  * id, nome, cpf, data de nascimento (dd/MM/yyyy),
//  * array de telefones, email, endereco, cadastro está ativo

                    return Integer.parseInt(dados[0]);//  * retornando o id do objeto
                }

                linha = br.readLine();
            }
        }
        return 0;//  * objeto não encontrado
    }

    public PessoaFisica buscar(int id) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(PessoaFisica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
                if (linha.startsWith(String.valueOf(id)) && dados[7].equals("true")) {
//  * id, nome, cpf, data de nascimento (dd/MM/yyyy),
//  * array de telefones, email, endereco, cadastro está ativo

                    if (dados.length != 8) {
                        throw new Exception("Dados incorretos");
                    }

                    String[] dadosEndereco = dados[6].split(",");
                    if (dadosEndereco.length != 8) {
                        throw new Exception("Dados incorretos");
                    }

                    Endereco endereco = new Endereco(dadosEndereco[0],//    * tipo de logradouro
                            dadosEndereco[1],//    * logradouro
                            dadosEndereco[2],//    * numero
                            dadosEndereco[3],//    * complemento
                            dadosEndereco[4],//    * bairro
                            dadosEndereco[5],//    * cidade
                            Enum.valueOf(EstadosBrazil.class, dadosEndereco[6]),//    * estado, seguindo o Enum
                            dadosEndereco[7]);//    * cep

                    Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dados[3]);
                    String[] telefones = dados[4].substring(dados[4].indexOf("[") + 1, dados[4].lastIndexOf("]")).split(",");

                    return new PessoaFisica(dados[1],// * nome
                            dados[2],// * CPF
                            dataNascimento,// * data de nascimento
                            dados[5],// * email
                            endereco,// * endereco
                            telefones);// * telefones
                }

                linha = br.readLine();
            }
        }
        return null;// * objeto não encontrado
    }

    @Override
    public ArrayList<PessoaFisica> buscarTodos() throws Exception {
        ArrayList<PessoaFisica> listaPessoasFisicas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PessoaFisica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
//  * id, nome, cpf, data de nascimento (dd/MM/yyyy),
//  * array de telefones, email, endereco, cadastro está ativo

                if (dados.length != 8) {
                    throw new Exception("Dados incorretos");
                }

                String[] dadosEndereco = dados[6].split(",");
                if (dadosEndereco.length != 8) {
                    throw new Exception("Dados incorretos");
                }
                Endereco endereco = new Endereco(dadosEndereco[0],//    * tipo de logradouro
                        dadosEndereco[1],//    * logradouro
                        dadosEndereco[2],//    * numero
                        dadosEndereco[3],//    * complemento
                        dadosEndereco[4],//    * bairro
                        dadosEndereco[5],//    * cidade
                        Enum.valueOf(EstadosBrazil.class, dadosEndereco[6]),//    * estado, seguindo o Enum
                        dadosEndereco[7]);//    * cep

                Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dados[3]);

                String[] telefones = dados[4].substring(dados[4].indexOf("[") + 1, dados[4].lastIndexOf("]")).split(",");

                listaPessoasFisicas.add(new PessoaFisica(dados[1],// * nome
                        dados[2],// * CPF
                        dataNascimento,// * data de nascimento
                        dados[5],// * email
                        endereco,// * endereco
                        telefones));// * telefones

                linha = br.readLine();
            }
        }
        return listaPessoasFisicas;//   * retornando lista com todas as pessoas fisicas com cadastro ativo
    }

    @Override
    public void remover(PessoaFisica obj) throws Exception {

//        try ( BufferedReader br = new BufferedReader(new FileReader(PessoaFisica.getNomeArquivoDisco()))) {
//            boolean achou = false;
//            String linha = br.readLine();
//            StringBuilder lista = new StringBuilder();
//
//            while (linha != null) {
//                if (!linha.endsWith(obj.toString())) {//ignorando o ID, pois o obj não tem id
//                    lista.append(linha).append("\n");//salvando dados que serão reescritos no banco
//                } else {
//                    achou = true;
//                }
//                linha = br.readLine();
//            }
//
//            if (!achou) {
//                throw new Exception("Cliente não encontrado");
//            }
//
//            try ( BufferedWriter bw = new BufferedWriter(new FileWriter(PessoaFisica.getNomeArquivoDisco(), false))) {
//                if (lista.toString() != null) {
//                    bw.write(lista.toString());
//                }
//            }
//        }
        throw new UnsupportedOperationException("Não implementado ainda");

    }

    @Override
    public void remover(int id) throws Exception {
//        try ( BufferedReader br = new BufferedReader(new FileReader(PessoaFisica.getNomeArquivoDisco()))) {
//            boolean achou = false;
//            String linha = br.readLine();
//            StringBuilder lista = new StringBuilder();
//
//            while (linha != null) {
//                if (!linha.startsWith(String.valueOf(id))) {
//                    lista.append(linha).append("\n");//salvando dados que serão reescritos no banco
//                } else {
//                    achou = true;
//                }
//                linha = br.readLine();
//            }
//
//            if (!achou) {
//                throw new Exception("Cliente não encontrado");
//            }
//
//            try ( BufferedWriter bw = new BufferedWriter(new FileWriter(PessoaFisica.getNomeArquivoDisco(), false))) {
//                if (lista.toString() != null) {
//                    bw.write(lista.toString());
//                }
//            }
//        }
        throw new UnsupportedOperationException("Não implementado ainda");

    }

    @Override
    public void editar(PessoaFisica objParaRemover, PessoaFisica objParaAdicionar) throws Exception {
//
//        remover(objParaRemover);//caso o objeto não exista, vai dar um erro nesse método, esta é a validacao
//        incluir(objParaAdicionar);
        throw new UnsupportedOperationException("Não implementado ainda");

    }

    @Override
    public void editar(int idObjParaRemover, PessoaFisica objParaAdicionar) throws Exception {
//        remover(idObjParaRemover);
//        incluir(objParaAdicionar);
        throw new UnsupportedOperationException("Não implementado ainda");

    }

}
