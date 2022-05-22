/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import enumerations.EstadosBrazil;
import geradorId.GeradorId;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import modelos.Peca;
import modelos.Peca;
import modelos.auxiliares.Endereco;

/**
 *
 * @author ALUNO
 */
public class ManipulaBancoPecas implements IManipulaBanco<Peca> {

    @Override
    public void incluir(Peca obj) throws Exception {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(Peca.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(Peca.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
        }//fecha arquivo
    }

    @Override
    public int buscar(Peca obj) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(Peca.getNomeArquivoDisco()))) {
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
        try ( BufferedReader br = new BufferedReader(new FileReader(Peca.getNomeArquivoDisco()))) {
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
    public Peca buscar(int id) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(Peca.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.startsWith(String.valueOf(id))) {//ignorando o iD, pois isso não fica salvo no objeto
                    String[] dados = linha.split(";");
                    if (dados.length != 7) {
                        throw new Exception("Dados incorretos");
                    }

                    return new Peca(dados[1], dados[2], Float.parseFloat(dados[3]), Integer.parseInt(dados[4]), Integer.parseInt(dados[6]));
                }

                linha = br.readLine();
            }
        }
        return null;

    }

    @Override
    public ArrayList<Peca> buscarTodos() throws Exception {

        ArrayList<Peca> listaPecas = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(Peca.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
                if (dados.length != 7) {
                    throw new Exception("Dados incorretos");
                }

                listaPecas.add(new Peca(dados[1], dados[2], Float.parseFloat(dados[3]), Integer.parseInt(dados[4]), Integer.parseInt(dados[6])));
                linha = br.readLine();
            }
        }
        return listaPecas;
    }

    @Override
    public void remover(Peca obj) throws Exception {
//        try ( BufferedReader br = new BufferedReader(new FileReader(Peca.getNomeArquivoDisco()))) {
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
//            try ( BufferedWriter bw = new BufferedWriter(new FileWriter(Peca.getNomeArquivoDisco(), false))) {
//                if (lista.toString() != null) {
//                    bw.write(lista.toString());
//                }
//            }
//        }
        throw new UnsupportedOperationException("Não implementado ainda");

    }

    @Override
    public void remover(int id) throws Exception {
//        try ( BufferedReader br = new BufferedReader(new FileReader(Peca.getNomeArquivoDisco()))) {
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
//            try ( BufferedWriter bw = new BufferedWriter(new FileWriter(Peca.getNomeArquivoDisco(), false))) {
//                if (lista.toString() != null) {
//                    bw.write(lista.toString());
//                }
//            }
//        }
        throw new UnsupportedOperationException("Não implementado ainda");

    }

    @Override
    public void editar(Peca objParaRemover, Peca objParaAdicionar) throws Exception {
//        remover(objParaRemover);
//        incluir(objParaAdicionar);
        throw new UnsupportedOperationException("Não implementado ainda");

    }

    @Override
    public void editar(int idObjParaRemover, Peca objParaAdicionar) throws Exception {
//        remover(idObjParaRemover);
//        incluir(objParaAdicionar);
        throw new UnsupportedOperationException("Não implementado ainda");

    }

}
