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
        }
    }

    @Override
    public int buscar(OrdemDeServico obj) throws Exception {
        int id = 0;
        try ( BufferedReader br = new BufferedReader(new FileReader(OrdemDeServico.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.endsWith(obj.toString())) {//não usar o ID na comparacao, pq esse é um atributo do banco e não da classe
                    String[] dados = linha.split(";");
//  * id, defeito relatado, servico feito, Valor mao de obra, data de criacao da OS (dd/MM/yyyy), data de finalizacao da OS(dd/MM/yyyy), 
//  * situacao da OS, id do funcionario responsável, iD da peca usada, quantidade de pecas usadas, valor unitario da peca, id do veiculo
                    if (dados.length != 12) {
                        //linnha com defeito
                    }
                    id = Integer.parseInt(dados[0]);
                }
            }
            return id;
        }
    }

    @Override
    public OrdemDeServico buscar(int id) throws Exception {
        OrdemDeServico os = null;
        try ( BufferedReader br = new BufferedReader(new FileReader(OrdemDeServico.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.startsWith(String.valueOf(id))) {//não usar o ID na comparacao, pq esse é um atributo do banco e não da classe
                    String[] dados = linha.split(";");
//  * id, defeito relatado, servico feito, Valor mao de obra, data de criacao da OS (dd/MM/yyyy), data de finalizacao da OS(dd/MM/yyyy), 
//  * situacao da OS, id do funcionario responsável, iD da peca usada, quantidade de pecas usadas, valor unitario da peca, id do veiculo
                    if (dados.length != 12) {
                        //linnha com defeito
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date DataAbertura = sdf.parse(dados[4]);
                    Date dataFechamento = null;
                    if (!dados[5].equals("null")) {//não tentar se o valor não for uma data
                        dataFechamento = sdf.parse(dados[5]);
                    }

                    os = new OrdemDeServico(dados[1], dados[2], Double.parseDouble(dados[3]), DataAbertura, Integer.parseInt(dados[7]),
                            Integer.parseInt(dados[8]), Integer.parseInt(dados[9]), Integer.parseInt(dados[11]));
                }
            }
        }
        return os;
    }

    @Override
    public ArrayList<OrdemDeServico> buscarTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
