/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import enumerations.EstadosBrazil;
import geradorId.GeradorId;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import modelos.Funcionario;
import modelos.OrdemDeServico;
import modelos.Funcionario;
import modelos.auxiliares.Endereco;

/**
 *
 * @author tanak
 */
public class ManipulaBancoFuncionario implements IManipulaBanco<Funcionario> {

    @Override
    public void incluir(Funcionario obj) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Funcionario.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(Funcionario.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
            //fecha arquivo
        }
    }

    @Override
    public int buscar(Funcionario obj) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(Funcionario.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                Funcionario f = parse(linha);
                if (f.equals(obj) && f.isCadastroAtivo()) {//  * achou
                    return Integer.parseInt(linha.substring(0, linha.indexOf(";")));//    * retornando o ID
                }
                linha = br.readLine();
            }

        }
        return 0;//Funcionario não encontrado
    }

    public int buscar(String dado) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(Funcionario.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {

                if (linha.contains(dado)) {//   * conferindo se o dado está em qualquer parte do objeto
                    if (linha.split(";")[7].equals("true")) {//    * se o cadastro está ativo
                        return Integer.parseInt(linha.substring(0, linha.indexOf(";")));
                    } else {
                        //  * pass
                    }
                }
                linha = br.readLine();
            }

        }
        return 0;//Funcionario não encontrado
    }

    @Override
    public Funcionario buscar(int id) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(Funcionario.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                Funcionario f = parse(linha);
                if (f.isCadastroAtivo() && linha.substring(0, linha.indexOf(";")).equals(String.valueOf(id))) {//    * encontrou o objeto
                    return f;
                }
                linha = br.readLine();
            }

        }
        return null;
    }

    @Override
    public ArrayList<Funcionario> buscarTodos() throws Exception {
        ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(Funcionario.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                Funcionario f = parse(linha);
                if (f.isCadastroAtivo()) {//    * só adicionar cadastros ativos
                    listaFuncionarios.add(f);
                }
                linha = br.readLine();
            }
        }
        return listaFuncionarios;
    }

    @Override
    public void remover(Funcionario obj) throws Exception {
        int id = buscar(obj);
//        Funcionario f = buscar(id);
//        if (f == null) {
//            System.out.println(buscar(obj));
//            System.out.println(buscar(buscar(obj)));
//        } else {
//
//            String banco = "";//    * todas as informações do banco
//            try (BufferedReader br = new BufferedReader(new FileReader(Funcionario.getNomeArquivoDisco()))) {
//                String linha = br.readLine();
//                while (linha != null) {
//                    if (!linha.endsWith(obj.toString())) {//  * não salvar o dado que será excluido
//                        banco += linha + "\n";//    * adicionando nova linha de dados, que serão salvas no banco de dados
//                    } else {
////  *   pass
//                    }
//                    linha = br.readLine();
//                }
//            }
//            try (BufferedWriter br = new BufferedWriter(new FileWriter(Funcionario.getNomeArquivoDisco(), false))) {
//                f.setCadastroAtivo(false);
//                br.write(banco + "\n"
//                        + id + ";" + f.toString());
//            }
//        }
        remover(id);
    }

    @Override
    public void remover(int id) throws Exception {
        Funcionario f = buscar(id);
        if (f == null) {
            throw new Exception("Funcionario não encontrado");
        }
        StringBuffer banco = new StringBuffer();//    * todas as informações do banco
        try (BufferedReader br = new BufferedReader(new FileReader(Funcionario.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                f = parse(linha);
                int idObjAtual = Integer.parseInt(linha.substring(0, linha.indexOf(";")));
                if (idObjAtual == id) {//  * achou
                    f.setCadastroAtivo(false);//    * desativar cadastro antes de salvar
                }
                banco.append(idObjAtual).append(";").append(f.toString()).append("\n");
                linha = br.readLine();
            }
        }
        try (BufferedWriter br = new BufferedWriter(new FileWriter(Funcionario.getNomeArquivoDisco(), false))) {
            br.write(banco.toString());
        }
    }

    @Override
    public void editar(Funcionario objParaRemover, Funcionario objParaAdicionar) throws Exception {
        remover(objParaRemover);
        incluir(objParaAdicionar);
    }

    @Override
    public void editar(int idObjParaRemover, Funcionario objParaAdicionar) throws Exception {
        remover(idObjParaRemover);
        incluir(objParaAdicionar);
    }

    private Funcionario parse(String dadosCompletos) throws Exception {
        String[] dados = dadosCompletos.split(";");
//    * id, nome, cpf, dataNascimento,
//    * telefones, email, endereco,cadastroAtivo,
//    * especialidade, salarioMes, salarioHora, matriculaFuncionario

        if (dados.length != 12) {
            System.out.println(dadosCompletos);
            System.out.println(Arrays.toString(dados));
            System.out.println(dados.length);
            throw new Exception("Dados do funcionario incorretos");
        }

        String[] dadosEndereco = dados[6].split(",");
//  * tipo de logradouro, logradouro, numero da casa, complemento, bairo, cidade, estado, cep

        if (dadosEndereco.length != 8) {
            throw new Exception("Dados de endereço do funcionario incorretos");
        }
        String[] telefones = dados[4].substring(dados[4].indexOf("[") + 1, dados[4].lastIndexOf("]")).split(",");// * ignorando "[]"
        if (telefones.length != 3) {
            throw new Exception("Telefones incorretos");
        }
        Endereco endereco = new Endereco(dadosEndereco[0],//    * tipo de logradouro
                dadosEndereco[1],//    * logradouro
                dadosEndereco[2],//    * numero
                dadosEndereco[3],//    * complemento
                dadosEndereco[4],//    * bairro
                dadosEndereco[5],//    * cidade
                Enum.valueOf(EstadosBrazil.class, dadosEndereco[6]),//    * estado, seguindo o Enum
                dadosEndereco[7]);//    * cep

        Funcionario f = new Funcionario(dados[8],//    * especialidade
                Double.parseDouble(dados[9]),//    * salario mensal
                Double.parseDouble(dados[10]),//    * salario hora
                Integer.parseInt(dados[11]),//    * numero da matricula
                dados[1],//    * nome 
                dados[2],//    * CPF
                new SimpleDateFormat("dd/MM/yyyy").parse(dados[3]),//    * data de nascimento
                dados[5],//    * email
                endereco,//    * endereço
                dados[4].substring(dados[4].indexOf("[") + 1, dados[4].lastIndexOf("]")).split(","));//    * telefones, ignorando [] do array
        if (dados[7].equals(String.valueOf(false))) {//    * se tiver desativado, desativar
            f.setCadastroAtivo(false);
        }
        return f;
    }
}
