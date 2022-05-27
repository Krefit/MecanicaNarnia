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
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(Funcionario.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(Funcionario.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
            //fecha arquivo
        }
    }

    @Override
    public int buscar(Funcionario obj) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(Funcionario.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.endsWith(obj.toString())) {//ignorando o iD, pois isso não fica salvo no objeto
                    return Integer.parseInt(linha.substring(0, linha.indexOf(";")));//    * retornando o ID
                }
                linha = br.readLine();
            }

        }
        return 0;//Funcionario não encontrado
    }

    public int buscar(String dado) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(Funcionario.getNomeArquivoDisco()))) {
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
        Funcionario f = null;
        try ( BufferedReader br = new BufferedReader(new FileReader(Funcionario.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.startsWith(String.valueOf(id))) {//    * encontrou o objeto
                    String[] dados = linha.split(";");
//  * id, nome, cpf,data de nascimento(dd/MM/yyyy),  array de telefones,
//    * email, endereco,isCadastroAtivo, especialidade,
//    * salario mensal, salario/hora, numero da matricula

                    if (dados[7].equals("true")) {//    * só ler se o cadastro ainda estiver ativo

                        if (dados.length != 12) {
                            System.out.println(linha);
                            System.out.println(dados.length);
                            throw new Exception("Dados incorretos");
                        }
                        String[] dadosEndereco = dados[6].split(",");
//  * tipo de logradouro, logradouro, numero da casa, complemento, bairo, cidade, estado, cep

                        if (dadosEndereco.length != 8) {
                            throw new Exception("Dados incorretos");
                        }

                        Endereco endereco = new Endereco(dadosEndereco[0],//    * tipo de logradouro
                                dadosEndereco[1],//    * logradouro
                                dadosEndereco[2],//    * numero
                                dadosEndereco[3],//    * complemento
                                dadosEndereco[4],//    * bairro
                                dadosEndereco[5],//    * cidade
                                Enum.valueOf(EstadosBrazil.class, dadosEndereco[6]),//    * estado, seguindo o Enum
                                dadosEndereco[7]);//    * cep

                        f = new Funcionario(dados[8],//    * especialidade
                                Double.parseDouble(dados[9]),//    * salario mensal
                                Double.parseDouble(dados[10]),//    * salario hora
                                Integer.parseInt(dados[11]),//    * numero da matricula
                                dados[1],//    * nome 
                                dados[2],//    * CPF
                                new SimpleDateFormat("dd/MM/yyyy").parse(dados[3]),//    * data de nascimento
                                dados[5],//    * email
                                endereco,//    * endereço
                                dados[4].substring(dados[4].indexOf("[") + 1, dados[4].lastIndexOf("]")).split(","));//    * telefones, ignorando [] do array
                        break;//    * já encontrou o objeto
                    } else {
                        //  * pass
                    }
                }
                linha = br.readLine();
            }

        }
        return f;
    }

    @Override
    public ArrayList<Funcionario> buscarTodos() throws Exception {
        ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();

        try ( BufferedReader br = new BufferedReader(new FileReader(Funcionario.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] dados = linha.split(";");
//    * id, nome, cpf, dataNascimento,
//    * telefones, email, endereco,cadastroAtivo,
//    * especialidade, salarioMes, salarioHora, matriculaFuncionario

                if (dados.length != 12) {
                    System.out.println(linha);
                    System.out.println(Arrays.toString(dados));
                    System.out.println(dados.length);
                    throw new Exception("Dados do funcionario incorretos");
                }

                if (dados[7].equals("true")) {//    * só ler se não tiver sido excluido
                    String[] dadosEndereco = dados[6].split(",");
//  * tipo de logradouro, logradouro, numero da casa, complemento, bairo, cidade, estado, cep

                    if (dadosEndereco.length != 8) {
                        throw new Exception("Dados de endereço do funcionario incorretos");
                    }
                    String[] telefones = dados[4].substring(dados[4].indexOf("[") + 1, dados[4].lastIndexOf("]")).split(",");// * ignorando "[]"
                    for (int i = 0; i < telefones.length; i++) {
                        telefones[i] = telefones[i].trim();//   * tirando espaços em branco
                    }
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

                    listaFuncionarios.add(f);
                } else {
                    //  * pass
                }
                linha = br.readLine();
            }
        }
        return listaFuncionarios;
    }

    @Override
    public void remover(Funcionario obj) throws Exception {
        int id = buscar(obj);
        Funcionario f = buscar(id);
        if (f == null) {
            System.out.println(buscar(obj));
            System.out.println(buscar(buscar(obj)));
        } else {

            String banco = "";//    * todas as informações do banco
            try ( BufferedReader br = new BufferedReader(new FileReader(Funcionario.getNomeArquivoDisco()))) {
                String linha = br.readLine();
                while (linha != null) {
                    if (!linha.endsWith(obj.toString())) {//  * não salvar o dado que será excluido
                        banco += linha + "\n";//    * adicionando nova linha de dados, que serão salvas no banco de dados
                    } else {
//  *   pass
                    }
                    linha = br.readLine();
                }
            }
            try ( BufferedWriter br = new BufferedWriter(new FileWriter(Funcionario.getNomeArquivoDisco(), false))) {
                f.setCadastroAtivo(false);
                br.write(banco + "\n"
                        + id + ";" + f.toString());
            }
        }
    }

    @Override
    public void remover(int id) throws Exception {
        Funcionario f = buscar(id);

        StringBuffer banco = new StringBuffer();//    * todas as informações do banco
        try ( BufferedReader br = new BufferedReader(new FileReader(Funcionario.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (!linha.startsWith(String.valueOf(id))) {//  * não salvar o dado que será excluido
                    banco.append(linha).append("\n");
                } else {
//  *   pass
                }
                linha = br.readLine();
            }
        }
        try ( BufferedWriter br = new BufferedWriter(new FileWriter(Funcionario.getNomeArquivoDisco(), false))) {
            f.setCadastroAtivo(false);
            br.write(banco + "\n" + id + f.toString());
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

}
