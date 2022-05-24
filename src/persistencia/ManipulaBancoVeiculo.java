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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.print.DocFlavor;
import modelos.OrdemDeServico;
import modelos.Veiculo;
import modelos.auxiliares.Endereco;

/**
 *
 * @author tanak
 */
public class ManipulaBancoVeiculo implements IManipulaBanco<Veiculo> {

    public ManipulaBancoVeiculo() {
    }

    @Override
    public void incluir(Veiculo obj) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Veiculo.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(Veiculo.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
            //fecha arquivo
        }
    }

    @Override
    public int buscar(Veiculo obj) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(Veiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.endsWith(obj.toString())) {//ignorando o iD, pois isso não fica salvo no objeto
                    return Integer.parseInt(linha.substring(0, linha.indexOf(";")));
                }
                linha = br.readLine();
            }

        }
        return 0;
    }

    public int buscar(String dado) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(Veiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.contains(dado)) {
                    return Integer.parseInt(linha.substring(0, linha.indexOf(";")));
                }
                linha = br.readLine();
            }

        }
        return 0;
    }

    @Override
    public Veiculo buscar(int id) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(Veiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.startsWith(String.valueOf(id))) {
                    String[] dadosVeiculo = linha.substring(0, linha.indexOf("[")).split(";");//não ler dadosVeiculo das OSs
                    if (dadosVeiculo.length != 11) {
                        throw new Exception("Dados incorretos");
                    }

                    Veiculo veiculo = new Veiculo(Integer.parseInt(dadosVeiculo[1]), Integer.parseInt(dadosVeiculo[2]),
                            dadosVeiculo[3], dadosVeiculo[4], dadosVeiculo[5], dadosVeiculo[6], Integer.parseInt(dadosVeiculo[7]),
                            Integer.parseInt(dadosVeiculo[8]), Integer.parseInt(dadosVeiculo[9]), Integer.parseInt(dadosVeiculo[10]));

                    String[] dadosTodasAsOSs = linha.substring(linha.indexOf("[") + 1, linha.length() - 1).split(",");//ignorar []
                    for (String dadosOSAtual : dadosTodasAsOSs) {//pegando cada OS
                        String[] dados = dadosOSAtual.split(";");
                        for (int i = 0; i < dados.length; i++) {
                            dados[i] = dados[i].trim();//tirando espaços em branco antes e depois de cada String
                        }
                        if (dados[5].equals("null")) {
                            Date dataEntrada = new SimpleDateFormat("dd/MM/yyyy").parse(dados[4]);
//                            veiculo.adicionaItemListaOS(new OrdemDeServico(dados[0], dados[1],
//                                    Integer.parseInt(dados[2]), Float.parseFloat(dados[3]), dataEntrada,
//                                    Enum.valueOf(OrdemDeServico.SitucaoOrdemServico.class, dados[6]),
//                                    Integer.parseInt(dados[7]), Integer.parseInt(dados[8])));
//o veiculo não vai mais armazenar os dados das OSs
                        }
                    }
                    return veiculo;
                }
                linha = br.readLine();
            }

        }
        return null;
    }

    @Override
    public ArrayList<Veiculo> buscarTodos() throws Exception {
        ArrayList<Veiculo> listaVeiculos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(Veiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dadosVeiculo = linha.split(";");
                if (dadosVeiculo.length != 11) {
                    throw new Exception("Dados incorretos");
                }

                Veiculo veiculo = new Veiculo(Integer.parseInt(dadosVeiculo[1]), Integer.parseInt(dadosVeiculo[2]),
                        dadosVeiculo[3], dadosVeiculo[4], dadosVeiculo[5], dadosVeiculo[6], Integer.parseInt(dadosVeiculo[7]),
                        Integer.parseInt(dadosVeiculo[8]), Integer.parseInt(dadosVeiculo[9]), Integer.parseInt(dadosVeiculo[10]));

                listaVeiculos.add(veiculo);
                linha = br.readLine();
            }

        }
        return listaVeiculos;
    }

    @Override
    public void remover(Veiculo obj) throws Exception {
//        try ( BufferedReader br = new BufferedReader(new FileReader(Veiculo.getNomeArquivoDisco()))) {
//            boolean achou = false;
//            String linha = br.readLine();
//            StringBuilder lista = new StringBuilder();
//
//            while (linha != null) {
//                if (!linha.endsWith(obj.toString())) {//ignorando o ID, pois o obj não tem id
//                    lista.append(linha).append("\n");//salvando dados que serão reescritos no banco
//                } else {
//                    achou = true;
//                }
//                linha = br.readLine();
//            }
//
//            if (!achou) {
//                throw new Exception("Cliente não encontrado");
//            }
//
//            try ( BufferedWriter bw = new BufferedWriter(new FileWriter(Veiculo.getNomeArquivoDisco(), false))) {
//                if (lista.toString() != null) {
//                    bw.write(lista.toString());
//                }
//            }
//        }
        throw new UnsupportedOperationException("Não implementado ainda");

    }

    @Override
    public void remover(int id) throws Exception {
//        try ( BufferedReader br = new BufferedReader(new FileReader(Veiculo.getNomeArquivoDisco()))) {
//            boolean achou = false;
//            String linha = br.readLine();
//            StringBuilder lista = new StringBuilder();
//
//            while (linha != null) {
//                if (!linha.startsWith(String.valueOf(id))) {//ignorando o ID, pois o obj não tem id
//                    lista.append(linha).append("\n");//salvando dados que serão reescritos no banco
//                } else {
//                    achou = true;
//                }
//                linha = br.readLine();
//            }
//
//            if (!achou) {
//                throw new Exception("Cliente não encontrado");
//            }
//
//            try ( BufferedWriter bw = new BufferedWriter(new FileWriter(Veiculo.getNomeArquivoDisco(), false))) {
//                if (lista.toString() != null) {
//                    bw.write(lista.toString());
//                }
//            }
//        }
        throw new UnsupportedOperationException("Não implementado ainda");

    }

    @Override
    public void editar(Veiculo objParaRemover, Veiculo objParaAdicionar) throws Exception {
//        remover(objParaRemover);
//        incluir(objParaAdicionar);
        throw new UnsupportedOperationException("Não implementado ainda");

    }

    @Override
    public void editar(int idObjParaRemover, Veiculo objParaAdicionar) throws Exception {
//        remover(idObjParaRemover);
//        incluir(objParaAdicionar);
        throw new UnsupportedOperationException("Não implementado ainda");

    }

//    private void setOSs(Veiculo veiculo, String dadosOSs) throws Exception {
//        String[] dadosTodasAsOSs = dadosOSs.substring(dadosOSs.indexOf("[") + 1, dadosOSs.length() - 1).split(",");//ignorar []
//
//        for (String dadosOSAtual : dadosTodasAsOSs) {//pegando cada OS
//            String[] dados = dadosOSAtual.split(";");
//            if (dados.length != 1) {//caso exista alguma OS
//                for (int i = 0; i < dados.length; i++) {
//                    dados[i] = dados[i].trim();//tirando espaços em branco antes e depois de cada String
//                }
//                if (dados[5].equals("null")) {
//                    Date dataEntrada = new SimpleDateFormat("dd/MM/yyyy").parse(dados[4]);
//                    veiculo.adicionaItemListaOS(new OrdemDeServico(dados[0], dados[1],
//                            Integer.parseInt(dados[2]), Float.parseFloat(dados[3]), dataEntrada,
//                            Enum.valueOf(OrdemDeServico.SitucaoOrdemServico.class, dados[6]),
//                            Integer.parseInt(dados[7]), Integer.parseInt(dados[8])));
//                }
//            }
//        }
//    }
}
