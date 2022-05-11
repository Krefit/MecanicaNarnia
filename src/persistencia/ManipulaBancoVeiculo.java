/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import enumerations.EstadosBrazil;
import geradorId.GeradorId;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.print.DocFlavor;
import modelos.Cliente_PessoaFisica;
import modelos.OrdemDeServico;
import modelos.Veiculo;
import modelos.auxiliares.Endereco;

/**
 *
 * @author tanak
 */
public class ManipulaBancoVeiculo implements IManipulaBanco<Veiculo> {

    @Override
    public void incluir(Veiculo obj) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Veiculo.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(Veiculo.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
            //fecha arquivo
        }
    }

    @Override
    public Veiculo buscar(Veiculo obj) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(Veiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.endsWith(obj.toString())) {//ignorando o iD, pois isso não fica salvo no objeto
                    String[] dadosVeiculo = linha.substring(0, linha.indexOf("[")).split(";");//não ler dadosVeiculo das OSs
                    if (dadosVeiculo.length != 10) {
                        throw new Exception("Dados incorretos");
                    }

                    Veiculo veiculo = new Veiculo(Integer.parseInt(dadosVeiculo[1]), Integer.parseInt(dadosVeiculo[2]), dadosVeiculo[3], dadosVeiculo[4], dadosVeiculo[5], dadosVeiculo[6], Integer.parseInt(dadosVeiculo[7]), Integer.parseInt(dadosVeiculo[8]), Integer.parseInt(dadosVeiculo[9]));

                    String[] dadosTodasAsOSs = linha.substring(linha.indexOf("[") + 1, linha.length() - 1).split(",");//ignorar []
                    for (String dadosOSAtual : dadosTodasAsOSs) {//pegando cada OS
                        String[] dados = dadosOSAtual.split(";");
                        for (int i = 0; i < dados.length; i++) {
                            dados[i] = dados[i].trim();//tirando espaços em branco antes e depois de cada String
                        }
                        if (dados[5].equals("null")) {
                            Date dataEntrada = new SimpleDateFormat("dd/MM/yyyy").parse(dados[4]);
                            veiculo.adicionaItemListaOS(new OrdemDeServico(dados[0], dados[1],
                                    Integer.parseInt(dados[2]), Float.parseFloat(dados[3]), dataEntrada,
                                    Enum.valueOf(OrdemDeServico.SitucaoOrdemServico.class, dados[6]),
                                    Integer.parseInt(dados[7]), Integer.parseInt(dados[8])));
                        }
                    }
                    return veiculo;
                }
                linha = br.readLine();
            }

        }
        throw new Exception("Cliente não encontrado");
    }

    public Veiculo buscar(int id) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(Veiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.startsWith(String.valueOf(id))) {
                    String[] dadosVeiculo = linha.substring(0, linha.indexOf("[")).split(";");//não ler dadosVeiculo das OSs
                    if (dadosVeiculo.length != 10) {
                        throw new Exception("Dados incorretos");
                    }

                    Veiculo veiculo = new Veiculo(Integer.parseInt(dadosVeiculo[1]), Integer.parseInt(dadosVeiculo[2]), dadosVeiculo[3], dadosVeiculo[4], dadosVeiculo[5], dadosVeiculo[6], Integer.parseInt(dadosVeiculo[7]), Integer.parseInt(dadosVeiculo[8]), Integer.parseInt(dadosVeiculo[9]));

                    String[] dadosTodasAsOSs = linha.substring(linha.indexOf("[") + 1, linha.length() - 1).split(",");//ignorar []
                    for (String dadosOSAtual : dadosTodasAsOSs) {//pegando cada OS
                        String[] dados = dadosOSAtual.split(";");
                        for (int i = 0; i < dados.length; i++) {
                            dados[i] = dados[i].trim();//tirando espaços em branco antes e depois de cada String
                        }
                        if (dados[5].equals("null")) {
                            Date dataEntrada = new SimpleDateFormat("dd/MM/yyyy").parse(dados[4]);
                            veiculo.adicionaItemListaOS(new OrdemDeServico(dados[0], dados[1],
                                    Integer.parseInt(dados[2]), Float.parseFloat(dados[3]), dataEntrada,
                                    Enum.valueOf(OrdemDeServico.SitucaoOrdemServico.class, dados[6]),
                                    Integer.parseInt(dados[7]), Integer.parseInt(dados[8])));
                        }
                    }
                    return veiculo;
                }
                linha = br.readLine();
            }

        }
        throw new Exception("Cliente não encontrado");
    }

    @Override
    public void remover(Veiculo obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar(Veiculo objParaRemover, Veiculo objParaAdicionar) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
