package persistencia;

import geradorId.GeradorId;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import modelos.auxiliares.MarcaVeiculo;

public class ManipulaBancoMarcaVeiculo implements IManipulaBanco<MarcaVeiculo> {

    @Override
    public void incluir(MarcaVeiculo obj) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(MarcaVeiculo.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(MarcaVeiculo.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
            //fecha arquivo
        }
    }

    @Override
    public ArrayList<MarcaVeiculo> buscarTodos() throws Exception {
        ArrayList<MarcaVeiculo> listaMarcas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(MarcaVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dadosMarcaVeiculoBusca = linha.substring(0, linha.indexOf("[")).split(";");//não ler dadosVeiculo das OSs
                if (dadosMarcaVeiculoBusca.length != 10) {
                    throw new Exception("Dados incorretos");
                }

                listaMarcas.add(new MarcaVeiculo(dadosMarcaVeiculoBusca[0]));
                linha = br.readLine();
            }
        }
        throw new Exception("Marca não encontrada");

    }

    @Override
    public int buscar(MarcaVeiculo obj) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(MarcaVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.endsWith(obj.toString())) {//ignorando o iD, pois isso não fica salvo no objeto
                    String[] dadosMarcaVeiculo = linha.substring(0, linha.indexOf("[")).split(";");//não ler dadosVeiculo das OSs
                    return Integer.parseInt(dadosMarcaVeiculo[0]);
                }
                linha = br.readLine();
            }
        }
        throw new Exception("Marca não encontrada");
    }

    @Override
    public void remover(MarcaVeiculo obj) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(MarcaVeiculo.getNomeArquivoDisco()))) {
            boolean achou = false;
            String linha = br.readLine();
            StringBuilder lista = new StringBuilder();

            while (linha != null) {
                if (!linha.endsWith(obj.toString())) {//ignorando o ID, pois o obj não tem id
                    lista.append(linha).append("\n");//salvando dados que serão reescritos no banco
                } else {
                    achou = true;
                }
                linha = br.readLine();
            }

            if (!achou) {
                throw new Exception("Marca não encontrada");
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(MarcaVeiculo.getNomeArquivoDisco(), false))) {
                if (lista.toString() != null) {
                    bw.write(lista.toString());
                }
            }
        }
    }

    @Override
    public void editar(MarcaVeiculo objParaRemover, MarcaVeiculo objParaAdicionar) throws Exception {
        remover(objParaRemover);
        incluir(objParaAdicionar);
    }

    @Override
    public MarcaVeiculo buscar(int id) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(MarcaVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.startsWith(String.valueOf(id))) {
                    String[] dadosMarcaVeiculoBusca = linha.substring(0, linha.indexOf("[")).split(";");//não ler dadosVeiculo das OSs
                    if (dadosMarcaVeiculoBusca.length != 10) {
                        throw new Exception("Dados incorretos");
                    }

                    MarcaVeiculo marcaVeiculoBusca = new MarcaVeiculo(dadosMarcaVeiculoBusca[0]);
                    return marcaVeiculoBusca;
                }
                linha = br.readLine();
            }
        }
        throw new Exception("Marca não encontrada");
    }

    @Override
    public void remover(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editar(int idObjParaRemover, MarcaVeiculo objParaAdicionar) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
