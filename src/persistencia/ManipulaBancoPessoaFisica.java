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
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(PessoaFisica.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(PessoaFisica.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
            //fecha arquivo
        }
    }

    @Override
    public int buscar(PessoaFisica obj) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(PessoaFisica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.endsWith(obj.toString())) {//ignorando o iD, pois isso não fica salvo no objeto

                    System.out.println(linha);
                    return Integer.parseInt(linha.substring(0, linha.indexOf(";")));
                }

                linha = br.readLine();
            }
        }
        return 0;
    }

    public int buscar(String cpf) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(PessoaFisica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.contains(cpf)) {
                    String[] dados = linha.split(";");
                    return Integer.parseInt(dados[0]);
                }

                linha = br.readLine();
            }
        }
        return 0;
    }

    public PessoaFisica buscar(int id) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(PessoaFisica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.startsWith(String.valueOf(id))) {
                    String[] dados = linha.split(";");
                    if (dados.length != 7) {
                        throw new Exception("Dados incorretos");
                    }

                    String[] dadosEndereco = dados[6].split(",");
                    if (dadosEndereco.length != 8) {
                        throw new Exception("Dados incorretos");
                    }

                    Endereco endereco = new Endereco(dadosEndereco[0], dadosEndereco[1], dadosEndereco[2], dadosEndereco[3], dadosEndereco[4], dadosEndereco[5], Enum.valueOf(EstadosBrazil.class, dadosEndereco[6]), dadosEndereco[7]);

                    Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dados[3]);
                    return new PessoaFisica(dados[1], dados[2], data, dados[5], endereco, dados[4].split(","));
                }

                linha = br.readLine();
            }
        }
        return null;
    }

    @Override
    public ArrayList<PessoaFisica> buscarTodos() throws Exception {
        ArrayList<PessoaFisica> listaPessoasFisicas = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(PessoaFisica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
                if (dados.length != 7) {
                    throw new Exception("Dados incorretos");
                }

                String[] dadosEndereco = dados[6].split(",");
                if (dadosEndereco.length != 8) {
                    throw new Exception("Dados incorretos");
                }

                Endereco endereco = new Endereco(dadosEndereco[0], dadosEndereco[1], dadosEndereco[2], dadosEndereco[3], dadosEndereco[4], dadosEndereco[5], Enum.valueOf(EstadosBrazil.class, dadosEndereco[6]), dadosEndereco[7]);

                Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dados[3]);
                listaPessoasFisicas.add(new PessoaFisica(dados[1]/*NomePessoa*/,
                        dados[2]/*CPF*/,
                        data/*DataNascimento*/,
                        dados[5]/*Email*/,
                        endereco/*Endereco*/,
                        dados[4].split(",")/*Telefone*/));

                linha = br.readLine();
            }
        }
        return listaPessoasFisicas;
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
