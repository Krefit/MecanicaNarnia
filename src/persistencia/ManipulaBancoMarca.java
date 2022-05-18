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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(MarcaVeiculo.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(MarcaVeiculo.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
            //fecha arquivo
        }
    }

    @Override
    public int buscar(MarcaVeiculo obj) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(MarcaVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.endsWith(obj.toString())) {
                    return Integer.parseInt(linha.substring(0, linha.indexOf(";")));
                }
                linha = br.readLine();
            }
        }
        throw new Exception("Marca não encontrada");
    }

    @Override
    public MarcaVeiculo buscar(int id) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(MarcaVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.startsWith(String.valueOf(id))) {
                    String[] dados = linha.split(";");
                    if (dados.length != 2) {
                        throw new Exception("Dados incorretos");
                    }

                    return new MarcaVeiculo(dados[1]);
                }
                linha = br.readLine();
            }
        }
        throw new Exception("Marca não encontrada");

    }

    public int buscar(String nome) throws Exception {
        if (nome.equals("")) {
            throw new Exception("Modelo inválido");
        }
        try (BufferedReader br = new BufferedReader(new FileReader(MarcaVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.contains(nome)) {
                    String[] dados = linha.split(";");
                    return Integer.parseInt(dados[0]);
                }
                linha = br.readLine();
            }
        }
        throw new Exception("Cliente não encontrado");

    }

    @Override
    public ArrayList<MarcaVeiculo> buscarTodos() throws Exception {

        ArrayList<MarcaVeiculo> listaMarcas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(MarcaVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
                if (dados.length != 2) {
                    throw new Exception("Dados incorretos");
                }

                MarcaVeiculo m = new MarcaVeiculo(dados[1]);
                listaMarcas.add(m);
                linha = br.readLine();
            }
        }
        return listaMarcas;
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
