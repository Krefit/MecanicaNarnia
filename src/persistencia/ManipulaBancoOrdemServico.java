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
import modelos.OrdemDeServico;
import modelos.auxiliares.MarcaVeiculo;

/**
 *
 * @author ALUNO
 */
public class ManipulaBancoOrdemServico implements IManipulaBanco<OrdemDeServico> {

    @Override
    public void incluir(OrdemDeServico obj) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(OrdemDeServico.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(MarcaVeiculo.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
            //fecha arquivo
        }
    }

    @Override
    public int buscar(OrdemDeServico obj) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(OrdemDeServico.getNomeArquivoDisco()))) {
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

    public int buscar(String dado) throws Exception {
        if (dado.equals("")) {
            throw new Exception("Modelo inválido");
        }
        try (BufferedReader br = new BufferedReader(new FileReader(OrdemDeServico.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.contains(dado)) {
                    return Integer.parseInt(linha.substring(0, linha.indexOf(";")));
                }
                linha = br.readLine();
            }
        }
        throw new Exception("Modelo não encontrado");
    }

    @Override
    public OrdemDeServico buscar(int id) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(OrdemDeServico.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.startsWith(String.valueOf(id))) {
                    String[] dados = linha.split(";");
                    if (dados.length != 3) {
                        throw new Exception("Dados incorretos");
                    }
//17;defeitoRelatado;servico;2;23.5;11/12/2002;null;EM_ABERTO;51;0
//                    return new OrdemDeServico(linha, linha, id, id, dataEntrada, OrdemDeServico.SitucaoOrdemServico.EM_ABERTO, id)
                }
                linha = br.readLine();
            }
        }
        throw new Exception("Modelo não encontrado");
    }

    @Override
    public ArrayList<OrdemDeServico> buscarTodos() throws Exception {
        ArrayList<OrdemDeServico> listaModelos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(OrdemDeServico.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
                if (dados.length != 3) {
                    throw new Exception("Dados incorretos");
                }

//                listaModelos.add(new OrdemDeServico(dados[1], Integer.parseInt(dados[2])));
                linha = br.readLine();
            }
        }

        return listaModelos;
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
