/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import geradorId.GeradorId;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import modelos.OrdemDeServico;

/**
 *
 * @author tanak
 */
public class ManipulaBancoOrdemServico implements IManipulaBanco<OrdemDeServico> {

    @Override
    public void incluir(OrdemDeServico obj) throws Exception {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(OrdemDeServico.getNomeArquivoDisco(), true))) {
            bw.write(GeradorId.getID(OrdemDeServico.getArquivoID()) + ";" + obj.toString() + "\n");
        }//  * fechando o arquivo
    }

    @Override
    public int buscar(OrdemDeServico obj) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(OrdemDeServico.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.endsWith(obj.toString())) {//não usar o ID na comparacao, pq esse é um atributo do banco e não da classe
                    String[] dados = linha.split(";");
//  * id, defeito relatado, servico feito, Valor mao de obra, 
//  * data de criacao da OS (dd/MM/yyyy), data de finalizacao da OS(dd/MM/yyyy), situacao da OS, id do funcionario responsável, 
//  * iD da peca usada, quantidade de pecas usadas, valor unitario da peca, id do veiculo, cadastro esta ativo

                    if (dados.length != 13) {
                        System.out.println(linha);
                        System.out.println(dados.length);
                        throw new Exception("Dados incompletos da ordem de serviço");
                    }
                    return Integer.parseInt(dados[0]);//  * retornando o id
                }
            }
            return 0;//  * objeto não encontrado
        }
    }

    @Override
    public OrdemDeServico buscar(int id) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(OrdemDeServico.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.startsWith(String.valueOf(id))) {//não usar o ID na comparacao, pq esse é um atributo do banco e não da classe
                    String[] dados = linha.split(";");
//  * id, defeito relatado, servico feito, Valor mao de obra, 
//  * data de criacao da OS (dd/MM/yyyy), data de finalizacao da OS(dd/MM/yyyy), situacao da OS, id do funcionario responsável, 
//  * iD da peca usada, quantidade de pecas usadas, valor unitario da peca, id do veiculo, cadastro esta ativo

                    if (dados.length != 13) {
                        System.out.println(linha);
                        System.out.println(dados.length);
                        throw new Exception("Dados incompletos da ordem de serviço");
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date DataAbertura = sdf.parse(dados[4]);
                    Date dataFechamento = null;
                    if (!dados[5].equals("null")) {//não tentar se o valor não for uma data
                        dataFechamento = sdf.parse(dados[5]);
                    } else {
//  * pass
                    }

                    return new OrdemDeServico(dados[1], //  * defeito relatado
                            Integer.parseInt(dados[2]), //  * id do serviço que será executado
                            Double.parseDouble(dados[3]), //  * valor da mao de obra
                            DataAbertura, //  * data de abertura da OS
                            Integer.parseInt(dados[7]), //  * id do fincionario responsável
                            Integer.parseInt(dados[8]), //  * id da peça que será usada(0 caso não tenha nenhuma)
                            Integer.parseInt(dados[9]), //  * quantidade desta peça que serão usadas no veículo
                            Double.parseDouble(dados[10]), //  * valor unitário da peça
                            Integer.parseInt(dados[11])); //  * id do veiculo
                }
            }
        }
        return null; //  * objeto não encontrado
    }

    @Override
    public ArrayList<OrdemDeServico> buscarTodos() throws Exception {
        ArrayList<OrdemDeServico> listaOSs = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(OrdemDeServico.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
//  * id, defeito relatado, servico feito, Valor mao de obra, 
//  * data de criacao da OS (dd/MM/yyyy), data de finalizacao da OS(dd/MM/yyyy), situacao da OS, id do funcionario responsável, 
//  * iD da peca usada, quantidade de pecas usadas, valor unitario da peca, id do veiculo, cadastro esta ativo

                if (dados.length != 13) {
                    System.out.println(linha);
                    System.out.println(dados.length);
                    throw new Exception("Dados incompletos da ordem de serviço");
                }

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date DataAbertura = sdf.parse(dados[4]);
                Date dataFechamento = null;
                if (!dados[5].equals("null")) {//não tentar se o valor não for uma data
                    dataFechamento = sdf.parse(dados[5]);
                }
                if (dados[12].equals("true")) {
                    listaOSs.add(new OrdemDeServico(dados[1], //  * defeito relatado
                            Integer.parseInt(dados[2]), //  * id do serviço que será executado
                            Double.parseDouble(dados[3]), //  * valor da mao de obra
                            DataAbertura, //  * data de abertura da OS
                            Integer.parseInt(dados[7]), //  * id do fincionario responsável
                            Integer.parseInt(dados[8]), //  * id da peça que será usada(0 caso não tenha nenhuma)
                            Integer.parseInt(dados[9]), //  * quantidade desta peça que serão usadas no veículo
                            Double.parseDouble(dados[10]), //  * valor unitário da peça
                            Integer.parseInt(dados[11]))); //  * id do veiculo
                } else {//  * foi apagado do banco de dados
//  * pass
                }
                linha = br.readLine();
            }
        }
        return listaOSs;//  * retornando lista com todas as OSs ativas
    }

    public ArrayList<OrdemDeServico> buscarTodos(int idVeiculo) throws Exception {
        ArrayList<OrdemDeServico> listaOSs = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(OrdemDeServico.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
                if (dados[11].equals(String.valueOf(idVeiculo))) {
//  * id, defeito relatado, servico feito, Valor mao de obra, 
//  * data de criacao da OS (dd/MM/yyyy), data de finalizacao da OS(dd/MM/yyyy), situacao da OS, id do funcionario responsável, 
//  * iD da peca usada, quantidade de pecas usadas, valor unitario da peca, id do veiculo, cadastro esta ativo

                    if (dados.length != 13) {
                        System.out.println(linha);
                        System.out.println(dados.length);
                        throw new Exception("Dados incompletos da ordem de serviço");
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date DataAbertura = sdf.parse(dados[4]);
                    Date dataFechamento = null;
                    if (!dados[5].equals("null")) {//não tentar se o valor não for uma data
                        dataFechamento = sdf.parse(dados[5]);
                    }

                    listaOSs.add(new OrdemDeServico(dados[1], //  * defeito relatado
                            Integer.parseInt(dados[2]), //  * id do serviço que será executado
                            Double.parseDouble(dados[3]), //  * valor da mao de obra
                            DataAbertura, //  * data de abertura da OS
                            Integer.parseInt(dados[7]), //  * id do fincionario responsável
                            Integer.parseInt(dados[8]), //  * id da peça que será usada(0 caso não tenha nenhuma)
                            Integer.parseInt(dados[9]), //  * quantidade desta peça que serão usadas no veículo
                            Double.parseDouble(dados[10]), //  * valor unitário da peça
                            Integer.parseInt(dados[11]))); //  * id do veiculo
                }
                linha = br.readLine();
            }
        }
        return listaOSs;//  * retornando lista com todas as OSs ativas deste veiculo
    }

    @Override
    public void remover(OrdemDeServico obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void remover(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editar(OrdemDeServico objParaRemover, OrdemDeServico objParaAdicionar) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editar(int idObjParaRemover, OrdemDeServico objParaAdicionar) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
