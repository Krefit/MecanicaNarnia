/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import enumerations.EstadosBrazil;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import modelos.PessoaJuridica;
import modelos.auxiliares.Endereco;

/**
 *
 * @author ALUNO
 */
public class ManipulaBancoPessoaJuridica implements IManipulaBanco<PessoaJuridica> {

    @Override
    public PessoaJuridica parse(String dados) throws Exception {
        String[] dadosPessoa = dados.split(";");
//  * id, nome fantasia, CNPJ, razao social, 
//  * telefones, email, endereco, cadastro está ativo

        if (dadosPessoa.length != 8) {
            throw new Exception("Dados incorretos");
        }

        String[] dadosEndereco = dadosPessoa[6].split(",");
        if (dadosEndereco.length != 8) {
            throw new Exception("Dados incorretos, do endereco de pessoa juridica");
        }

        Endereco endereco = new Endereco(dadosEndereco[0],//    * tipo de logradouro
                dadosEndereco[1],//    * logradouro
                dadosEndereco[2],//    * numero
                dadosEndereco[3],//    * complemento
                dadosEndereco[4],//    * bairro
                dadosEndereco[5],//    * cidade
                Enum.valueOf(EstadosBrazil.class, dadosEndereco[6]),//    * estado, seguindo o Enum
                dadosEndereco[7]);//    * cep

        PessoaJuridica pj = new PessoaJuridica(dadosPessoa[2],//   * CNPJ
                dadosPessoa[3],//   * razao social
                dadosPessoa[1],//   * nome fantasia
                dadosPessoa[5],//   * email
                endereco,//   * endereco
                dadosPessoa[4].substring(1, dadosPessoa[4].length() - 1).split(","));//   * telefones
        if (dadosPessoa[7].equals(String.valueOf(false))) {//   * caso o cadastro esteja inativo
            pj.setCadastroAtivo(false);//   * inativar objeto antes de retornar
        }
        return pj;
    }

    @Override
    public String getNomeDoArquivoNoDisco() {
        return PessoaJuridica.getNomeArquivoDisco();
    }

    @Override
    public int getID(PessoaJuridica obj) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(PessoaJuridica.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                PessoaJuridica p = parse(linha);// * parsing linha
                if (p.equals(obj) && p.isCadastroAtivo()) {//  * encontrou
                    return Integer.parseInt(linha.split(";")[0]);// * retornando o id
                }
                linha = br.readLine();
            }
        }
        return 0;// * objeto não encontrado
    }

    @Override
    public String getNomeArquivoID() {
        return PessoaJuridica.getArquivoID();
    }

    @Override
    public boolean isCadastroAtivo(PessoaJuridica obj) {
        return obj.isCadastroAtivo();
    }

    @Override
    public PessoaJuridica setCadastroAtivo(PessoaJuridica obj, boolean flag) {
        obj.setCadastroAtivo(flag);
        return obj;
    }

    @Override
    public int buscar(String dado) throws Exception {
        ArrayList<PessoaJuridica> listaPessoas = buscarTodos();
        for (PessoaJuridica p : listaPessoas) {
            if (p.getCnpj().equals(dado)) {//    * encontrou
                return getID(p);//  * retornando o id
            }
        }
        return 0; //    * objeto não encontrado
    }

    public int buscarPorRazaoSocial(String nome) throws Exception {
        ArrayList<PessoaJuridica> listaPessoas = buscarTodos();
        for (PessoaJuridica p : listaPessoas) {
            if (p.getRazaoSocial().equals(nome)) {//    * encontrou
                return getID(p);//  * retornando o id
            }
        }
        return 0; //    * objeto não encontrado
    }

    @Override
    public boolean ativarEasterEgg(PessoaJuridica obj) {
        return obj.getEmail().toUpperCase().contains("das couve".toUpperCase())
                || obj.getEndereco().ativarEasterEgg()
                || obj.getNomeFantasia().toUpperCase().contains("das couve".toUpperCase())
                || obj.getRazaoSocial().toUpperCase().contains("das couve".toUpperCase());
    }

}
