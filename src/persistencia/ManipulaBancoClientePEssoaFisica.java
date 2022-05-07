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
import java.util.Calendar;
import java.util.Date;
import modelos.Cliente_PessoaFisica;
import modelos.auxiliares.Endereco;

/**
 *
 * @author tanak
 */
public class ManipulaBancoClientePEssoaFisica implements IManipulaBanco<Cliente_PessoaFisica> {

    @Override
    public void incluir(Cliente_PessoaFisica obj) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(obj.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(obj.getarquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
            //fecha arquivo
        }
    }

    @Override
    public Cliente_PessoaFisica buscar(Cliente_PessoaFisica obj) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(obj.getNomeArquivoDisco()))) {
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

                    Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dados[3]);
                    return new Cliente_PessoaFisica(dados[1], dados[2], data, dados[4], dados[5], endereco);
                }

                linha = br.readLine();
            }
        }
        throw new Exception("Cliente não encontrado");

    }

    @Override
    public void remover(Cliente_PessoaFisica obj) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(obj.getNomeArquivoDisco()))) {
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

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(obj.getNomeArquivoDisco(), false))) {
                if (lista.toString() != null) {
                    bw.write(lista.toString());
                }
            }
        }
    }

    @Override
    public void editar(Cliente_PessoaFisica objParaRemover, Cliente_PessoaFisica objParaAdicionar) throws Exception {

        remover(objParaRemover);//caso o objeto não exista, vai dar um erro nesse método, esta é a validacao
        incluir(objParaAdicionar);

    }

}
