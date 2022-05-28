/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import java.util.Date;
import modelos.PessoaJuridica;
import modelos.auxiliares.Endereco;

/**
 *
 * @author ALUNO
 */
public class ManipulaBancoPessoaJuridica implements IManipulaBanco<PessoaJuridica> {

    @Override
    public void incluir(PessoaJuridica obj) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PessoaJuridica.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(PessoaJuridica.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
        }//fecha arquivo
    }

    @Override
    public int buscar(PessoaJuridica obj) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(PessoaJuridica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.endsWith(obj.toString()) //ignorando o iD, pois isso não fica salvo no objeto
                        && linha.split(";")[7].equals("true")) {//  * lendo apenas cadastros que ainda estão ativos

                    return Integer.parseInt(linha.substring(0, linha.indexOf(";")));//  * retornando o id do objeto
                }

                linha = br.readLine();
            }
        }
        return 0;//  * objeto não encontrado
    }

    @Override
    public PessoaJuridica buscar(int id) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(PessoaJuridica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
//  * id, nome fantasia, CNPJ, razao social, 
//  * telefone, email, endereco, cadastro está ativo

                if (dados.length != 8) {
                    throw new Exception("Dados incorretos");
                }
                if (linha.startsWith(String.valueOf(id)) && dados[7].equals("true")) {//ignorando o iD, pois isso não fica salvo no objeto

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

                    return new PessoaJuridica(dados[2],//   * CNPJ
                            dados[3],//   * razao social
                            dados[1],//   * nome fantasia
                            dados[5],//   * email
                            endereco,//   * endereco
                            dados[4]);//   * telefone
                }

                linha = br.readLine();
            }
        }
        return null;//   * objeto não encontrado
    }

    @Override
    public ArrayList<PessoaJuridica> buscarTodos() throws Exception {
        ArrayList<PessoaJuridica> listaPessoasJuridicas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PessoaJuridica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
//  * id, nome fantasia, CNPJ, razao social, 
//  * telefone, email, endereco, cadastro está ativo

                if (dados.length != 8) {
                    throw new Exception("Dados incorretos");
                }
                if (dados[7].equals("true")) {//    * salvando apenas os cadastros que estão ativos

                    String[] dadosEndereco = dados[6].split(",");
                    if (dadosEndereco.length != 8) {
                        throw new Exception("Dados incorretos, do endereco de pessoa juridica");
                    }

                    Endereco endereco = new Endereco(dadosEndereco[0],//    * tipo de logradouro
                            dadosEndereco[1],//    * logradouro
                            dadosEndereco[2],//    * numero
                            dadosEndereco[3],//    * complemento
                            dadosEndereco[4],//    * bairro
                            dadosEndereco[5],//    * cidade
                            Enum.valueOf(EstadosBrazil.class, dadosEndereco[6]),//    * estado, seguindo o Enum
                            dadosEndereco[7]);//    * cep

                    listaPessoasJuridicas.add(new PessoaJuridica(dados[2],//   * CNPJ
                            dados[3],//   * razao social
                            dados[1],//   * nome fantasia
                            dados[5],//   * email
                            endereco,//   * endereco
                            dados[4]));//   * telefone
                }
                linha = br.readLine();
            }
        }
        return listaPessoasJuridicas;// * retornando a lista com todas as pessoas jurídicas com cadastro ativo
    }

    @Override
    public void remover(PessoaJuridica obj) throws Exception {
//        try ( BufferedReader br = new BufferedReader(new FileReader(PessoaJuridica.getNomeArquivoDisco()))) {
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
//            try ( BufferedWriter bw = new BufferedWriter(new FileWriter(PessoaJuridica.getNomeArquivoDisco(), false))) {
//                if (lista.toString() != null) {
//                    bw.write(lista.toString());
//                }
//            }
//        }
        throw new UnsupportedOperationException("Não implementado ainda");

    }

    @Override
    public void remover(int id) throws Exception {
//        try ( BufferedReader br = new BufferedReader(new FileReader(PessoaJuridica.getNomeArquivoDisco()))) {
//            boolean achou = false;
//            String linha = br.readLine();
//            StringBuilder lista = new StringBuilder();
//
//            while (linha != null) {
//                if (!linha.startsWith(String.valueOf(id))) {//ignorando o ID, pois o obj não tem id
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
//            try ( BufferedWriter bw = new BufferedWriter(new FileWriter(PessoaJuridica.getNomeArquivoDisco(), false))) {
//                if (lista.toString() != null) {
//                    bw.write(lista.toString());
//                }
//            }
//        }
        throw new UnsupportedOperationException("Não implementado ainda");

    }

    @Override
    public void editar(PessoaJuridica objParaRemover, PessoaJuridica objParaAdicionar) throws Exception {
//        remover(objParaRemover);
//        incluir(objParaAdicionar);
        throw new UnsupportedOperationException("Não implementado ainda");

    }

    @Override
    public void editar(int idObjParaRemover, PessoaJuridica objParaAdicionar) throws Exception {
//        remover(idObjParaRemover);
//        incluir(objParaAdicionar);
        throw new UnsupportedOperationException("Não implementado ainda");

    }

}
