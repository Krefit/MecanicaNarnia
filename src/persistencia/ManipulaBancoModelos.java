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
import java.util.ArrayList;
import modelos.auxiliares.MarcaVeiculo;
import modelos.auxiliares.ModeloVeiculo;

/**
 *
 * @author tanak
 */
public class ManipulaBancoModelos implements IManipulaBanco<ModeloVeiculo> {

    @Override
    public void incluir(ModeloVeiculo obj) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ModeloVeiculo.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(MarcaVeiculo.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
            //fecha arquivo
        }
    }

    @Override
    public int buscar(ModeloVeiculo obj) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(ModeloVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.endsWith(obj.toString())) {
                    return Integer.parseInt(linha.substring(0, linha.indexOf(";")));
                }
                linha = br.readLine();
            }
        }
        throw new Exception("Modelo não encontrado");
    }

    @Override
    public ModeloVeiculo buscar(int id) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(ModeloVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.startsWith(String.valueOf(id))) {
                    String[] dados = linha.split(";");
                    if (dados.length != 3) {
                        throw new Exception("Dados incorretos");
                    }

                    return new ModeloVeiculo(dados[1], Integer.parseInt(dados[2]));
                }
                linha = br.readLine();
            }
        }
        throw new Exception("Modelo não encontrado");
    }

    @Override
    public ArrayList<ModeloVeiculo> buscarTodos() throws Exception {
        ArrayList<ModeloVeiculo> listaModelos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ModeloVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
                if (dados.length != 3) {
                    throw new Exception("Dados incorretos");
                }

                listaModelos.add(new ModeloVeiculo(dados[1], Integer.parseInt(dados[2])));
                linha = br.readLine();
            }
        }

        return listaModelos;
    }

    @Override
    public void remover(ModeloVeiculo obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void remover(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editar(ModeloVeiculo objParaRemover, ModeloVeiculo objParaAdicionar) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editar(int idObjParaRemover, ModeloVeiculo objParaAdicionar) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
