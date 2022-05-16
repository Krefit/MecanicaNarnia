/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import enumerations.EstadosBrazil;
import geradorId.GeradorId;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import modelos.Funcionario;
import modelos.OrdemDeServico;
import modelos.Funcionario;
import modelos.auxiliares.Endereco;

/**
 *
 * @author tanak
 */
public class ManipulaBancoFuncionario implements IManipulaBanco<Funcionario> {

    @Override
    public void incluir(Funcionario obj) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Funcionario.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(Funcionario.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
            //fecha arquivo
        }
    }

    @Override
    public Funcionario buscar(Funcionario obj) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(Funcionario.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.endsWith(obj.toString())) {//ignorando o iD, pois isso não fica salvo no objeto
                    String[] dadosFuncionario = linha.split(";");
                    if (dadosFuncionario.length != 11) {
                        throw new Exception("Dados incorretos");
                    }
                    String[] dadosEndereco = dadosFuncionario[6].split(",");
                    if (dadosEndereco.length != 8) {
                        throw new Exception("Dados incorretos");
                    }

                    Endereco endereco = new Endereco(dadosEndereco[0], dadosEndereco[1], dadosEndereco[2], dadosEndereco[3],
                            dadosEndereco[4], dadosEndereco[5], Enum.valueOf(EstadosBrazil.class, dadosEndereco[6]), dadosEndereco[7]);

                    return new Funcionario(dadosFuncionario[7], Double.parseDouble(dadosFuncionario[8]),
                            Double.parseDouble(dadosFuncionario[9]), Integer.parseInt(dadosFuncionario[10]), dadosFuncionario[1], dadosFuncionario[2],
                            new SimpleDateFormat("dd/MM/yyyy").parse(dadosFuncionario[3]), dadosFuncionario[4], dadosFuncionario[5], endereco);

                }
                linha = br.readLine();
            }

        }
        throw new Exception("Cliente não encontrado");
    }

    @Override
    public Funcionario buscar(int id) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(Funcionario.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.startsWith(String.valueOf(id))) {
                    String[] dadosFuncionario = linha.split(";");
                    if (dadosFuncionario.length != 11) {
                        throw new Exception("Dados incorretos");
                    }
                    String[] dadosEndereco = dadosFuncionario[6].split(",");
                    if (dadosEndereco.length != 8) {
                        throw new Exception("Dados incorretos");
                    }

                    Endereco endereco = new Endereco(dadosEndereco[0], dadosEndereco[1], dadosEndereco[2], dadosEndereco[3],
                            dadosEndereco[4], dadosEndereco[5], Enum.valueOf(EstadosBrazil.class, dadosEndereco[6]), dadosEndereco[7]);

                    return new Funcionario(dadosFuncionario[7], Double.parseDouble(dadosFuncionario[8]),
                            Double.parseDouble(dadosFuncionario[9]), Integer.parseInt(dadosFuncionario[10]), dadosFuncionario[1], dadosFuncionario[2],
                            new SimpleDateFormat("dd/MM/yyyy").parse(dadosFuncionario[3]), dadosFuncionario[4], dadosFuncionario[5], endereco);

                }
                linha = br.readLine();
            }

        }
        throw new Exception("Cliente não encontrado");
    }

    @Override
    public ArrayList<Funcionario> buscarTodos() throws Exception {
        ArrayList<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();

        try (BufferedReader br = new BufferedReader(new FileReader(Funcionario.getNomeArquivoDisco()))) {

            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
                if (dados.length != 11) {
                    throw new Exception("Dados incorretos");
                }

                String[] dadosEndereco = dados[6].split(",");
                if (dadosEndereco.length != 8) {
                    throw new Exception("Dados incorretos");
                }

                Endereco endereco = new Endereco(dadosEndereco[0], dadosEndereco[1], dadosEndereco[2], dadosEndereco[3],
                        dadosEndereco[4], dadosEndereco[5], Enum.valueOf(EstadosBrazil.class, dadosEndereco[6]), dadosEndereco[7]);

                Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dados[3]);
                Funcionario f = new Funcionario(dados[7], Double.parseDouble(dados[8]), Double.parseDouble(dados[9]),
                        Integer.parseInt(dados[10]), dados[1], dados[2], dataNascimento, dados[4], dados[5], endereco);
                listaFuncionarios.add(f);
                linha = br.readLine();
            }
        }
        return listaFuncionarios;
    }

    @Override
    public void remover(Funcionario obj) throws Exception {
        throw new UnsupportedOperationException("Não implementado ainda");
    }

    @Override
    public void remover(int id) throws Exception {
        throw new UnsupportedOperationException("Não implementado ainda");

    }

    @Override
    public void editar(Funcionario objParaRemover, Funcionario objParaAdicionar) throws Exception {
        remover(objParaRemover);
        incluir(objParaAdicionar);
    }

    @Override
    public void editar(int idObjParaRemover, Funcionario objParaAdicionar) throws Exception {
        remover(idObjParaRemover);
        incluir(objParaAdicionar);
    }

}
