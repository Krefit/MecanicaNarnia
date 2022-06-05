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
import modelos.Veiculo;

/**
 *
 * @author tanak
 */
public class ManipulaBancoOrdemServico implements IManipulaBanco<OrdemDeServico> {

    @Override
    public OrdemDeServico parse(String dadosCompletos) throws Exception {
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

    @Override
    public String getNomeDoArquivoNoDisco() {
        return OrdemDeServico.getNomeArquivoDisco();
    }

    @Override
    public int getID(OrdemDeServico obj) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(OrdemDeServico.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                OrdemDeServico os = parse(linha);// * parsing linha
                if (os.equals(obj) && os.isCadastroAtivo()) {//  * encontrou
                    return Integer.parseInt(linha.split(";")[0]);// * retornando o id
                }
                linha = br.readLine();
            }
        }
        return 0;// * objeto não encontrado
    }

    @Override
    public String getNomeArquivoID() {
        return OrdemDeServico.getArquivoID();
    }

    @Override
    public boolean isCadastroAtivo(OrdemDeServico obj) {
        return obj.isCadastroAtivo();
    }

    @Override
    public OrdemDeServico setCadastroAtivo(OrdemDeServico obj, boolean flag) {
        obj.setCadastroAtivo(flag);
        return obj;
    }

    @Override
    public int buscar(String dado) throws Exception {
        ArrayList<OrdemDeServico> listaOSs = buscarTodos();
        for (OrdemDeServico os : listaOSs) {
            if (os.getDefeitoRelatado().equals(dado)) {//   * encontrou
                return getID(os);// * retornando o id
            }
        }
        return 0;// * objeto não encontrado
    }

    public ArrayList<OrdemDeServico> buscarTodos(int idVeiculo) throws Exception {
        ArrayList<OrdemDeServico> listaOSsCompleta = buscarTodos();//   * todas as OSs ativas no sistema
        ArrayList<OrdemDeServico> listaOSsDesteDono = new ArrayList<>();//  *OSs do cliente que está sendo buscado
        for (OrdemDeServico osAtual : listaOSsCompleta) {
            if (osAtual.getIdVeiculo() == idVeiculo) {// * encontrou uma OS deste dono
                listaOSsDesteDono.add(osAtual);//   * addicionando na lista
            }
        }

        if (listaOSsDesteDono.isEmpty()) {//    * não existe nenhuma OS deste veiculo
            return null;
        } else {//  * existe alguma OS deste veiculo
            return listaOSsDesteDono;
        }
    }
}
