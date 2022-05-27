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
import modelos.Funcionario;
import modelos.PessoaFisica;
import modelos.auxiliares.Endereco;
import modelos.auxiliares.MarcaVeiculo;

/**
 *
 * @author tanak
 */
public class ManipulaBancoMarca implements IManipulaBanco<MarcaVeiculo> {

    @Override
    public void incluir(MarcaVeiculo obj) throws Exception {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(MarcaVeiculo.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(MarcaVeiculo.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
            //fecha arquivo
        }
    }

    @Override
    public int buscar(MarcaVeiculo obj) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(MarcaVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.endsWith(obj.toString())) {
                    return Integer.parseInt(linha.substring(0, linha.indexOf(";")));//  * retornando o id 
                }
                linha = br.readLine();
            }
        }
        return 0;
    }

    @Override
    public MarcaVeiculo buscar(int id) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(MarcaVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.startsWith(String.valueOf(id))) {//  * achou o objeto
                    String[] dados = linha.split(";");
//  * id, nome da marca, cadastro está ativo

                    if (dados.length != 3) {
                        throw new Exception("Dados incorretos");
                    }
                    if (dados[2].equals("true")) {
                        return new MarcaVeiculo(dados[1]);
                    }
                }
                linha = br.readLine();
            }
        }
        return null;

    }

    public int buscar(String nome) throws Exception {
        if (nome.equals("")) {
            throw new Exception("Marca inválida");
        }
        try ( BufferedReader br = new BufferedReader(new FileReader(MarcaVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.contains(nome)) {//   * caso tenha encontrado o objeto
                    String[] dados = linha.split(";");
//  * id, nome da marca, cadastro está ativo
                    if (dados[2].equals("true")) {
                        return Integer.parseInt(dados[0]);//   * retornando o id
                    } else {
//  * pass
                    }
                }
                linha = br.readLine();
            }
        }
        return 0;//   * não encontrado
    }

    @Override
    public ArrayList<MarcaVeiculo> buscarTodos() throws Exception {

        ArrayList<MarcaVeiculo> listaMarcas = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(MarcaVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
//  * id, nome da marca, cadastro está ativo

                if (dados.length != 3) {
                    System.out.println(linha);
                    System.out.println(dados.length);
                    throw new Exception("Dados incorretos");
                }

                if (dados[2].equals("true")) {//    * só adicionar se o cadastro estiver ativo
                    MarcaVeiculo m = new MarcaVeiculo(dados[1]);
                    listaMarcas.add(m);
                }
                linha = br.readLine();
            }
        }
        return listaMarcas;//   * retornando lista com todas as marcas
    }

    @Override
    public void remover(MarcaVeiculo obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void remover(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editar(MarcaVeiculo objParaRemover, MarcaVeiculo objParaAdicionar) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editar(int idObjParaRemover, MarcaVeiculo objParaAdicionar) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
