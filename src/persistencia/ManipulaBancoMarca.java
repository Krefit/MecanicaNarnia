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
        return buscar(obj.getNomeMarca());//    * chamando a busca por nome
    }

    @Override
    public MarcaVeiculo buscar(int id) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(MarcaVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                MarcaVeiculo marca = parse(linha);
                if (linha.substring(0, linha.indexOf(";")).equals(id)//  * caso o id seja igual
                        && marca.isCadastroAtivo()) {//  * e o cadastro esteja ativo
                    return marca;
                }
                linha = br.readLine();
            }
        }
        return null;//  * objeto não encontrado

    }

    public int buscar(String nome) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(MarcaVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                MarcaVeiculo marca = parse(linha);
                if (marca.getNomeMarca().equals(nome) && marca.isCadastroAtivo()) {//   * caso tenha encontrado o objeto
                    return Integer.parseInt(linha.split(";")[0]);//   * retornando o id
                }
                linha = br.readLine();
            }
        }
        return 0;//   * objeto não encontrado
    }

    @Override
    public ArrayList<MarcaVeiculo> buscarTodos() throws Exception {

        ArrayList<MarcaVeiculo> listaMarcas = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(MarcaVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                MarcaVeiculo marca = parse(linha);
                if (marca.isCadastroAtivo()) {//    * só adicionar se o cadastro estiver ativo
                    listaMarcas.add(marca);
                }
                linha = br.readLine();
            }
        }
        if (listaMarcas.isEmpty()) {
            return null;//  * banco vazio
        }
        return listaMarcas;//   * retornando lista com todas as marcas
    }

    @Override
    public void remover(MarcaVeiculo obj) throws Exception {
        int id = buscar(obj);
        remover(id);//  * usando a remoção por id
    }

    @Override
    public void remover(int id) throws Exception {
        MarcaVeiculo marcaParaRemover = buscar(id);//  * para saber se existe no banco de dados
        if (marcaParaRemover == null) {//  * não achou a marca
            throw new Exception("Marca não encontrada");
        }

        StringBuilder banco = new StringBuilder();//    * todas as informações do banco
        try ( BufferedReader br = new BufferedReader(new FileReader(MarcaVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                int idObjAtual = Integer.parseInt(linha.substring(0, linha.indexOf(";")));
                MarcaVeiculo marca = parse(linha);
                if (marca.equals(marcaParaRemover)) {//  * desativar antes de salvar
                    marca.setCadastroAtivo(false);
                }
                banco.append(idObjAtual).append(";")//  * adicionando o id
                        .append(marca.toString()).append("\n");//    * adicionando nova linha de dados, que serão salvas no banco de dados

            }
        }
        try ( BufferedWriter br = new BufferedWriter(new FileWriter(MarcaVeiculo.getNomeArquivoDisco(), false))) {
            br.write(banco.toString());//   * reescrevendo todo o banco
        }
    }

    @Override
    public void editar(MarcaVeiculo objParaRemover, MarcaVeiculo objParaAdicionar) throws Exception {
        remover(objParaRemover);
        incluir(objParaAdicionar);
    }

    @Override
    public void editar(int idObjParaRemover, MarcaVeiculo objParaAdicionar) throws Exception {
        remover(idObjParaRemover);
        incluir(objParaAdicionar);
    }

    private MarcaVeiculo parse(String dadosCompletos) throws Exception {
        String[] dados = dadosCompletos.split(";");
//  * id, nome da marca, cadastro está ativo

        if (dados.length != 3) {
            System.out.println(dados.length);
            System.out.println(dadosCompletos);
            throw new Exception("Dados incorretos");
        }
        MarcaVeiculo marca = new MarcaVeiculo(dados[1]);
        if (dados[2].equals("false")) {
            marca.setCadastroAtivo(false);
        }
        return marca;
    }
}
