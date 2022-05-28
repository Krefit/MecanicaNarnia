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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Peca.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(Peca.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
        }//fecha arquivo
    }

    @Override
    public int buscar(Peca obj) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(Peca.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.endsWith(obj.toString()) && linha.split(";")[7].equals("true")) {//ignorando o iD, pois isso não fica salvo no objeto
                    return Integer.parseInt(linha.substring(0, linha.indexOf(";")));//  * retornando o Id do objeto
                }

                linha = br.readLine();
            }
        }
        return 0;// * objeto não encontrado
    }

    public int buscar(String dado) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(Peca.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.contains(dado) && linha.split(";")[7].equals("true")) {
                    return Integer.parseInt(linha.substring(0, linha.indexOf(";")));// * retornando o id doobjeto
                }

                linha = br.readLine();
            }
        }
        return 0;// * objeto não encontrado
    }

    @Override
    public Peca buscar(int id) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(Peca.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
// * id, código da peça, nome da peça, valor unitário, quantidade no estoque, quantidade de peças reservadas, estoque minimo, cadastro está ativo;

                if (linha.startsWith(String.valueOf(id)) && dados[7].equals("true")) {
                    // * ignorando o iD, pois isso não fica salvo no objeto
                    // * lendo apenas os dados que estão ativos

                    if (dados.length != 8) {
                        throw new Exception("Dados incorretos, de peças");
                    }

                    return new Peca(dados[1],// * codigo da peça
                            dados[2],// * nome da peça
                            Float.parseFloat(dados[3]),// * valor unitario
                            Integer.parseInt(dados[4]),// * quantidade no estoque
                            Integer.parseInt(dados[6]));// * estoque minimo
                }

                linha = br.readLine();
            }
        }
        return null;// * objeto não encontrado

    }

    @Override
    public ArrayList<Peca> buscarTodos() throws Exception {

        ArrayList<Peca> listaPecas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(Peca.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
// * id, código da peça, nome da peça, valor unitário, quantidade no estoque, quantidade de peças reservadas, estoque minimo, cadastro está ativo;

                if (dados.length != 8) {
                    throw new Exception("Dados incorretos");
                }

                if (dados[7].equals("true")) {
                    listaPecas.add(new Peca(dados[1],// * codigo da peça
                            dados[2], // * nome da peça
                            Float.parseFloat(dados[3]),// * valor unitario
                            Integer.parseInt(dados[4]),// * quantidade no estoque
                            Integer.parseInt(dados[6])));// * estoque minimo
                }
                linha = br.readLine();
            }
        }
        return listaPecas;//    * retornando lista contendo todas as peças com cadastro ativo
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
