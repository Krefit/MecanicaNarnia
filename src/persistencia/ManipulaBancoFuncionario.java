/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import enumerations.EstadosBrazil;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import modelos.Funcionario;
import modelos.auxiliares.Endereco;

/**
 *
 * @author tanak
 */
public class ManipulaBancoFuncionario implements IManipulaBanco<Funcionario> {

    @Override
    public Funcionario parse(String dadosCompletos) throws Exception {
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
                telefones);//    * telefones, ignorando [] do array
        if (dados[7].equals(String.valueOf(false))) {//    * se tiver desativado, desativar
            f.setCadastroAtivo(false);
        }
        return f;
    }

    @Override
    public String getNomeArquivoID() {
        return Funcionario.getArquivoID();
    }

    @Override
    public String getNomeDoArquivoNoDisco() {
        return Funcionario.getNomeArquivoDisco();
    }

    @Override
    public int getID(Funcionario obj) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(Funcionario.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                Funcionario f = parse(linha);// * parsing linha
                if (f.equals(obj)) {// * encontrou
                    return Integer.parseInt(linha.split(";")[0]);// * retornando o id
                }
                linha = br.readLine();
            }
        }

        return 0;// * objeto não encontrados

    }

    @Override
    public boolean isCadastroAtivo(Funcionario f) {
        return f.isCadastroAtivo();
    }

    @Override
    public Funcionario setCadastroAtivo(Funcionario obj, boolean flag) {
        obj.setCadastroAtivo(flag);
        return obj;
    }

    @Override
    public int buscar(String dado) throws Exception {
        ArrayList<Funcionario> listaFunc = buscarTodos();

        for (Funcionario f : listaFunc) {
            if (("" + f.getMatricula()).equals(dado)) {
                return getID(f);
            }
        }
        return 0;
    }

    public int buscar(String dado, boolean nome) throws Exception {
        ArrayList<Funcionario> listaFunc = buscarTodos();

        for (Funcionario f : listaFunc) {
            if (("" + f.getNome()).equals(dado)) {
                return getID(f);
            }
        }
        return 0;
    }
}
