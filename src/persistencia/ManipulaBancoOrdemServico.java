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
                OrdemDeServico os = parse(linha);
                if (os.equals(obj) && os.isCadastroAtivo()) {// * achou
                    int id = Integer.parseInt(linha.substring(0, linha.indexOf(";")));
                    return id;//  * retornando o id
                }
                linha = br.readLine();
            }
            return 0;//  * objeto não encontrado
        }
    }

    @Override
    public OrdemDeServico buscar(int id) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(OrdemDeServico.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                OrdemDeServico os = parse(linha);
                if (linha.substring(0, linha.indexOf(";")).equals(id)// * achou o id igual
                        && os.isCadastroAtivo()) {//    * o cadastro está ativo
                    return os;
                }
                linha = br.readLine();
            }
        }
        return null;//  * objeto não encontrado
    }

    @Override
    public ArrayList<OrdemDeServico> buscarTodos() throws Exception {
        ArrayList<OrdemDeServico> listaOSs = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(OrdemDeServico.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                OrdemDeServico os = parse(linha);
                if (os.isCadastroAtivo()) {
                    listaOSs.add(os);
                }
                linha = br.readLine();
            }
        }
        if (listaOSs.isEmpty()) {
            return null;//  * o banco não tem nenhum cadastro ativo
        }
        return listaOSs;//  * retornando lista com todas as OSs ativas
    }

    public ArrayList<OrdemDeServico> buscarTodos(int idVeiculo) throws Exception {
        ArrayList<OrdemDeServico> listaOSs = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(OrdemDeServico.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                OrdemDeServico os = parse(linha);
                if (os.isCadastroAtivo() && os.getIdVeiculo() == idVeiculo) {// * achou uma correspondencia
                    listaOSs.add(os);// * adicionando os na lista que será retornada
                }

                linha = br.readLine();
            }
        }
        return listaOSs;//  * retornando lista com todas as OSs ativas deste veiculo
    }

    @Override
    public void remover(OrdemDeServico obj) throws Exception {
        int id = buscar(obj);// * pegando o id do iobjeto que será excluido
        remover(id);//  * usando a remoção por id
    }

    @Override
    public void remover(int id) throws Exception {
        OrdemDeServico os = buscar(id);
        if (os == null) {
            throw new Exception("Ordem de serviço não encontrada");
        }

        StringBuilder bancoCompleto = new StringBuilder();
        try ( BufferedReader br = new BufferedReader(new FileReader(OrdemDeServico.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                OrdemDeServico osAtual = parse(linha);
                int idObjAtual = Integer.parseInt(linha.substring(0, linha.indexOf(";")));
                if (osAtual.equals(os)) {// * achou
                    osAtual.setCadastroAtivo(false);//  * desativando antes de salvar
                }
                bancoCompleto.append(idObjAtual).append(";").append(osAtual).append("\n");//    * salvando dados da linha, que será reescrita no banco

                linha = br.readLine();
            }
        }

        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(OrdemDeServico.getNomeArquivoDisco(), false))) {
            bw.write(bancoCompleto.toString());//   * reescrevendo todos os dados do banco
        }
    }

    @Override
    public void editar(OrdemDeServico objParaRemover, OrdemDeServico objParaAdicionar) throws Exception {
        remover(objParaRemover);
        incluir(objParaRemover);
    }

    @Override
    public void editar(int idObjParaRemover, OrdemDeServico objParaAdicionar) throws Exception {
        remover(idObjParaRemover);
        incluir(objParaAdicionar);
    }

    private OrdemDeServico parse(String dadosCompletos) throws Exception {
        String[] dados = dadosCompletos.split(";");
//  * id, defeito relatado, servico feito, Valor mao de obra, 
//  * data de criacao da OS (dd/MM/yyyy), data de finalizacao da OS(dd/MM/yyyy), situacao da OS, id do funcionario responsável, 
//  * iD da peca usada, quantidade de pecas usadas, valor unitario da peca, id do veiculo, cadastro esta ativo

        if (dados.length != 13) {
            System.out.println(dadosCompletos);
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

        OrdemDeServico os = new OrdemDeServico(dados[1], //  * defeito relatado
                Integer.parseInt(dados[2]), //  * id do serviço que será executado
                Double.parseDouble(dados[3]), //  * valor da mao de obra
                DataAbertura, //  * data de abertura da OS
                Integer.parseInt(dados[7]), //  * id do fincionario responsável
                Integer.parseInt(dados[8]), //  * id da peça que será usada(0 caso não tenha nenhuma)
                Integer.parseInt(dados[9]), //  * quantidade desta peça que serão usadas no veículo
                Double.parseDouble(dados[10]), //  * valor unitário da peça
                Integer.parseInt(dados[11])); //  * id do veiculo   

        if (dados[12].equals(String.valueOf(false))) {//    * caso o cadastro estivesse inativo
            os.setCadastroAtivo(false);//   * inativando o cadastro que será retornado
        }

        return os;
    }
}
