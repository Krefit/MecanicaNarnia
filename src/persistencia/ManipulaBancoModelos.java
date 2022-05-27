package persistencia;

import geradorId.GeradorId;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import modelos.auxiliares.MarcaVeiculo;
import modelos.auxiliares.ModeloVeiculo;

public class ManipulaBancoModelos implements IManipulaBanco<ModeloVeiculo> {

    @Override
    public void incluir(ModeloVeiculo obj) throws Exception {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(ModeloVeiculo.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(MarcaVeiculo.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
        }//fecha arquivo
    }

    @Override
    public int buscar(ModeloVeiculo obj) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(ModeloVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.endsWith(obj.toString())) {
                    return Integer.parseInt(linha.substring(0, linha.indexOf(";")));//  * retornando o id
                }
                linha = br.readLine();
            }
        }
        return 0;//  * objeto não encontrado
    }

    public int buscar(String dado) throws Exception {
        if (dado.equals("")) {
            throw new Exception("Modelo inválido");
        }
        try ( BufferedReader br = new BufferedReader(new FileReader(ModeloVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.contains(dado)) {//  * encontrou o objeto
                    if (linha.split(";")[3].equals("true")) {
                        return Integer.parseInt(linha.substring(0, linha.indexOf(";")));
                    } else {
                        //  * pass
                    }
                }
                linha = br.readLine();
            }
        }
        return 0;
    }

    @Override
    public ModeloVeiculo buscar(int id) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(ModeloVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.startsWith(String.valueOf(id))) {
                    String[] dados = linha.split(";");
//  * id, nome do modelo, id da marca, cadastro está ativo

                    if (dados.length != 4) {
                        throw new Exception("Dados incorretos");
                    }

                    if (dados[3].equals("true")) {//  * o cadastro está ativo
                        return new ModeloVeiculo(dados[1], Integer.parseInt(dados[2]));
                    } else {
//  * pass
                    }
                }
                linha = br.readLine();
            }
        }
        return null;//  * objeto não encontrado
    }

    @Override
    public ArrayList<ModeloVeiculo> buscarTodos() throws Exception {
        ArrayList<ModeloVeiculo> listaModelos = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(ModeloVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
//  * id, nome do modelo, id da marca, cadastro está ativo

                if (dados.length != 4) {
                    throw new Exception("Dados incorretos");
                }
                if (dados[3].equals("true")) {//encontrou o objeto
                    listaModelos.add(new ModeloVeiculo(dados[1], Integer.parseInt(dados[2])));
                } else {
//  * pass
                }
                linha = br.readLine();
            }
        }

        return listaModelos;//  * retornando lista com todos os modelos
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
