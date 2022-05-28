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
import modelos.Servico;

/**
 *
 * @author tanak
 */
public class ManipulaBancoServicos implements IManipulaBanco<Servico> {

    @Override
    public void incluir(Servico obj) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Servico.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(Servico.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
        }//fecha arquivo
    }

    @Override
    public int buscar(Servico obj) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(Servico.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
//  * id, nome do serviço, valor da mão de obra,cadastro está ativo 
                if (dados.length != 4) {
                    if (linha.endsWith(obj.toString()) && dados[3].equals("true")) {
                        return Integer.parseInt(linha.substring(0, linha.indexOf(";")));
                    } else {
//  * pass
                    }
                }
                linha = br.readLine();
            }
        }
        return 0;// * objeto não encontrado
    }

    @Override
    public Servico buscar(int id) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(Servico.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
//  * id, nome do serviço, valor da mão de obra,cadastro está ativo 

                if (dados.length != 4) {
                    throw new Exception("Dados incorretos, no serviço");
                }
                if (linha.startsWith(String.valueOf(id)) && dados[3].equals("true")) {
                    return new Servico(dados[1], Double.parseDouble(dados[2]));
                }
                linha = br.readLine();
            }
        }
        return null;//  * objeto não encontrado

    }

    public int buscar(String nome) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(Servico.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
// * id, nomeServico, valorMaoDeObra, cadastro está ativo
                if (dados.length != 4) {
                    throw new Exception("Dados incorretos");
                }
                if (linha.contains(nome) && dados[3].equals("true")) {
                    return Integer.parseInt(dados[0]);//    * retornando o id do objeto
                }
                linha = br.readLine();
            }
        }
        return 0;// * objeto não encontrado
    }

    @Override
    public ArrayList<Servico> buscarTodos() throws Exception {

        ArrayList<Servico> listaServicos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(Servico.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
// * id, nomeServico, valorMaoDeObra, cadastro está ativo
                if (dados.length != 4) {
                    throw new Exception("Dados incorretos");
                }
                if (dados[3].equals("true")) {
                    listaServicos.add(new Servico(dados[1], Double.parseDouble(dados[2])));
                }
                linha = br.readLine();
            }
        }
        return listaServicos;//   * retornando a lista com todos os serviços com cadastro ativo
    }

    @Override
    public void remover(Servico obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void remover(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editar(Servico objParaRemover, Servico objParaAdicionar) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editar(int idObjParaRemover, Servico objParaAdicionar) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
