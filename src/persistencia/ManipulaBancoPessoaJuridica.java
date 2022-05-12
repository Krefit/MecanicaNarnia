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
import java.text.SimpleDateFormat;
import java.util.Date;
import modelos.PessoaJuridica;
import modelos.auxiliares.Endereco;

/**
 *
 * @author ALUNO
 */
public class ManipulaBancoPessoaJuridica implements IManipulaBanco<PessoaJuridica> {

    @Override
    public void incluir(PessoaJuridica obj) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PessoaJuridica.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(PessoaJuridica.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
        }//fecha arquivo
    }

    @Override
    public PessoaJuridica buscar(PessoaJuridica obj) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(PessoaJuridica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.endsWith(obj.toString())) {//ignorando o iD, pois isso não fica salvo no objeto
                    String[] dados = linha.split(";");
                    if (dados.length != 7) {
                        throw new Exception("Dados incorretos");
                    }

                    String[] dadosEndereco = dados[6].split(",");
                    if (dadosEndereco.length != 8) {
                        throw new Exception("Dados incorretos");
                    }

                    Endereco endereco = new Endereco(dadosEndereco[0], dadosEndereco[1], dadosEndereco[2], dadosEndereco[3], dadosEndereco[4], dadosEndereco[5], Enum.valueOf(EstadosBrazil.class, dadosEndereco[6]), dadosEndereco[7]);

                    return new PessoaJuridica(dados[2], dados[3], dados[1], dados[4], dados[5], endereco);
                }

                linha = br.readLine();
            }
        }
        throw new Exception("Cliente não encontrado");

    }

    @Override
    public PessoaJuridica buscar(int id) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(PessoaJuridica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.startsWith(String.valueOf(id))) {//ignorando o iD, pois isso não fica salvo no objeto
                    String[] dados = linha.split(";");
                    if (dados.length != 7) {
                        throw new Exception("Dados incorretos");
                    }

                    String[] dadosEndereco = dados[6].split(",");
                    if (dadosEndereco.length != 8) {
                        throw new Exception("Dados incorretos");
                    }

                    Endereco endereco = new Endereco(dadosEndereco[0], dadosEndereco[1], dadosEndereco[2], dadosEndereco[3], dadosEndereco[4], dadosEndereco[5], Enum.valueOf(EstadosBrazil.class, dadosEndereco[6]), dadosEndereco[7]);

                    return new PessoaJuridica(dados[2], dados[3], dados[1], dados[4], dados[5], endereco);
                }

                linha = br.readLine();
            }
        }
        throw new Exception("Cliente não encontrado");
    }

    @Override
    public void remover(PessoaJuridica obj) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(PessoaJuridica.getNomeArquivoDisco()))) {
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
                throw new Exception("Cliente não encontrado");
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(PessoaJuridica.getNomeArquivoDisco(), false))) {
                if (lista.toString() != null) {
                    bw.write(lista.toString());
                }
            }
        }
    }

    @Override
    public void remover(int id) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(PessoaJuridica.getNomeArquivoDisco()))) {
            boolean achou = false;
            String linha = br.readLine();
            StringBuilder lista = new StringBuilder();

            while (linha != null) {
                if (!linha.startsWith(String.valueOf(id))) {//ignorando o ID, pois o obj não tem id
                    lista.append(linha).append("\n");//salvando dados que serão reescritos no banco
                } else {
                    achou = true;
                }
                linha = br.readLine();
            }

            if (!achou) {
                throw new Exception("Cliente não encontrado");
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(PessoaJuridica.getNomeArquivoDisco(), false))) {
                if (lista.toString() != null) {
                    bw.write(lista.toString());
                }
            }
        }
    }

    @Override
    public void editar(PessoaJuridica objParaRemover, PessoaJuridica objParaAdicionar) throws Exception {
        remover(objParaRemover);
        incluir(objParaAdicionar);
    }

    @Override
    public void editar(int idObjParaRemover, PessoaJuridica objParaAdicionar) throws Exception {
        remover(idObjParaRemover);
        incluir(objParaAdicionar);
    }

}
