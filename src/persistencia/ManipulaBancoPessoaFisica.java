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
import java.util.Calendar;
import java.util.Date;
import modelos.PessoaFisica;
import modelos.auxiliares.Endereco;

/**
 *
 * @author tanak
 */
public class ManipulaBancoPessoaFisica implements IManipulaBanco<PessoaFisica> {

    @Override
    public void incluir(PessoaFisica obj) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PessoaFisica.getNomeArquivoDisco(), true))) {
            if (buscar(obj.getCpf()) != 0) {//  * caso já exista alguém com esse CPF no banco
                throw new IllegalArgumentException("este CPF já está cadastrado no banco");
            }
            int id = GeradorId.getID(PessoaFisica.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
            //fecha arquivo
        }
    }

    @Override
    public int buscar(PessoaFisica obj) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(PessoaFisica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                PessoaFisica p = parse(linha);
                if (p.equals(obj) && p.isCadastroAtivo()) {//   * achou
                    return Integer.parseInt(linha.substring(0, linha.indexOf(";")));//  * retornando o id do objeto
                }
                linha = br.readLine();
            }
        }
        return 0;//  * objeto não encontrado
    }

    public int buscar(String cpf) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(PessoaFisica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                PessoaFisica p = parse(linha);
                if (p.getCpf().equals(cpf) && p.isCadastroAtivo()) {// * achou
                    return Integer.parseInt(linha.substring(0, linha.indexOf(";")));//  * retornando o id do objeto
                }
                linha = br.readLine();
            }
        }
        return 0;
    }

    @Override
    public PessoaFisica buscar(int id) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(PessoaFisica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                PessoaFisica p = parse(linha);
                if (linha.startsWith(String.valueOf(id)) && p.isCadastroAtivo()) {
                    return p;
                }

                linha = br.readLine();
            }
        }
        return null;// * objeto não encontrado
    }

    @Override
    public ArrayList<PessoaFisica> buscarTodos() throws Exception {
        ArrayList<PessoaFisica> listaPessoasFisicas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PessoaFisica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                PessoaFisica p = parse(linha);
                if (p.isCadastroAtivo()) {
                    listaPessoasFisicas.add(p);//   * adicionando na lista
                }
                linha = br.readLine();
            }
        }
        return listaPessoasFisicas;//   * retornando lista com todas as pessoas fisicas com cadastro ativo
    }

    @Override
    public void remover(PessoaFisica obj) throws Exception {//  * não está conseguindo remover do banco, está passando pelo método sem fazer nada
        StringBuilder bancoCompleto = new StringBuilder();//  * vai guardar todos os dados do banco
        try (BufferedReader br = new BufferedReader(new FileReader(PessoaFisica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {//  * enquanto existir uma linha para ser escrita
                int id = Integer.parseInt(linha.substring(0, linha.indexOf(";")));
                PessoaFisica ClienteAtual = parse(linha);//    * pegando cliente do banco
                if (ClienteAtual.equals(obj) && ClienteAtual.isCadastroAtivo()) {//   * caso tenha encontrado uma correspondencia
                    ClienteAtual.setCadastroAtivo(false);//    * desativando cadastro
                }
                bancoCompleto.append(id).append(ClienteAtual.toString()).append("\n");//  * adicionando nova linha do banco
                linha = br.readLine();//    * lendo nova linha
            }
        }
//  * leu todos os dados do banco

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PessoaFisica.getNomeArquivoDisco()))) {
            bw.write(bancoCompleto.toString());//   * reescrevendo banco completo
        }

    }

    @Override
    public void remover(int id) throws Exception {//    * não tá validando, caso tenha tentado excluir algo que não existe
        StringBuilder bancoCompleto = new StringBuilder();//  * vai guardar todos os dados do banco
        try (BufferedReader br = new BufferedReader(new FileReader(PessoaFisica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {//  * enquanto existir uma linha para ser escrita
                int idObjAtual = Integer.parseInt(linha.substring(0, linha.indexOf(";")));
                if (linha.startsWith("" + id)) {//    * caso tenha encontrado o objeto
                    linha = linha.replace("true", "false");
                }
                bancoCompleto.append(idObjAtual).append(linha).append("\n");//  * adicionando nova linha do banco
                linha = br.readLine();//    * lendo nova linha
            }
//  * leu todos os dados do banco

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(PessoaFisica.getNomeArquivoDisco()))) {
                bw.write(bancoCompleto.toString());//   * reescrevendo banco completo
            }
        }

    }

    @Override
    public void editar(PessoaFisica objParaRemover, PessoaFisica objParaAdicionar) throws Exception {

        remover(objParaRemover);
        incluir(objParaAdicionar);
    }

    @Override
    public void editar(int idObjParaRemover, PessoaFisica objParaAdicionar) throws Exception {
        remover(idObjParaRemover);
        incluir(objParaAdicionar);

    }

    private PessoaFisica parse(String dadosCompletos) throws Exception {
        String[] dados = dadosCompletos.split(";");
//  * id, nome, cpf, data de nascimento (dd/MM/yyyy),
//  * array de telefones, email, endereco, cadastro está ativo
        if (dados.length != 8) {
            throw new Exception("Dados incorretos");
        }
        String[] dadosEndereco = dados[6].split(",");
        if (dadosEndereco.length != 8) {
            throw new Exception("Dados incorretos, de endereço");

        }
        Endereco endereco = new Endereco(dadosEndereco[0],//    * tipo de logradouro
                dadosEndereco[1],//    * logradouro
                dadosEndereco[2],//    * numero
                dadosEndereco[3],//    * complemento
                dadosEndereco[4],//    * bairro
                dadosEndereco[5],//    * cidade
                Enum.valueOf(EstadosBrazil.class,
                        dadosEndereco[6]),//    * estado, seguindo o Enum
                dadosEndereco[7]);//    * cep

        Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dados[3]);

        String[] telefones = dados[4].substring(dados[4].indexOf("[") + 1, dados[4].lastIndexOf("]")).split(",");

        PessoaFisica p = new PessoaFisica(dados[1],// * nome
                dados[2],// * CPF
                dataNascimento,// * data de nascimento
                dados[5],// * email
                endereco,// * endereco
                telefones);

        if (dados[7].equals(String.valueOf(false))) {
            p.setCadastroAtivo(false);
        }
        return p;
    }
}
