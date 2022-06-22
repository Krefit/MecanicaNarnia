/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import enumerations.TipoCliente;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import modelos.Veiculo;

/**
 *
 * @author tanak
 */
public class ManipulaBancoVeiculo implements IManipulaBanco<Veiculo> {

    public ManipulaBancoVeiculo() {
    }

    @Override
    public Veiculo parse(String dados) throws Exception {
        String[] dadosVeiculo = dados.split(";");
//  * id, id do modelo, id da marca, chassi,
//  * renavan, tipo do veiculo, placa, ano de fabricação(dd/MM/yyyy), 
//  * ano do modelo(dd/MM/yyyy), quilometragem, id do dono, cadastro está ativo

        if (dadosVeiculo.length != 13) {
            System.out.println(dados);
            throw new Exception("Dados incorretos");
        }
        TipoCliente tipoCliente = Enum.valueOf(TipoCliente.class, dadosVeiculo[12]);
        Veiculo veiculo = new Veiculo(Integer.parseInt(dadosVeiculo[1]),//  * id do modelo
                Integer.parseInt(dadosVeiculo[2]),//    * id da marca
                dadosVeiculo[3],//  * chassi
                dadosVeiculo[4],//  * renavan
                dadosVeiculo[5],//  * tipo do veiculo
                dadosVeiculo[6],//  * placa
                Integer.parseInt(dadosVeiculo[7]),//    * ano de fabricação
                Integer.parseInt(dadosVeiculo[8]),//    * ano do modelo
                Integer.parseInt(dadosVeiculo[9]),//    * quilometragem
                Integer.parseInt(dadosVeiculo[10]),//  * id do dono
                tipoCliente);// * tipo de cliente (pessoa fisica ou juridica)

        if (dadosVeiculo[11].equals(String.valueOf(false))) {
            veiculo.setCadastroAtivo(false);
        }
        return veiculo;
    }

    @Override
    public String getNomeDoArquivoNoDisco() {
        return Veiculo.getNomeArquivoDisco();
    }

    @Override
    public int getID(Veiculo obj) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(Veiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                Veiculo v = parse(linha);// * parsing linha
                if (v.equals(obj) && v.isCadastroAtivo()) {//  * encontrou
                    return Integer.parseInt(linha.split(";")[0]);// * retornando o id
                }
                linha = br.readLine();
            }
        }
        return 0;// * objeto não encontrado
    }

    @Override
    public String getNomeArquivoID() {
        return Veiculo.getArquivoID();
    }

    @Override
    public boolean isCadastroAtivo(Veiculo obj) {
        return obj.isCadastroAtivo();
    }

    @Override
    public Veiculo setCadastroAtivo(Veiculo obj, boolean flag) {
        obj.setCadastroAtivo(flag);
        return obj;
    }

    @Override
    public int buscar(String dado) throws Exception {
        ArrayList<Veiculo> listaVeiculos = buscarTodos();
        for (Veiculo v : listaVeiculos) {
            if (v.getPlaca().equals(dado)) {//  * achou
                return getID(v);
            }
        }
        return 0;// * objeto não encontrado
    }

    public int getQuantidadeVeiculos() throws Exception {
        return buscarTodos().size();
    }

    public int getQuantidadeVeiculos(int idDono) throws Exception {
        ArrayList<Veiculo> lista = buscarTodos();
        int quantidade = 0;
        for (Veiculo v : lista) {
            if (v.getIdDonoVeiculo() == idDono) {
                quantidade++;
            }
        }
        return quantidade;
    }

    @Override
    public boolean ativarEasterEgg(Veiculo obj) {
        return obj.getChassi().toUpperCase().contains("das couve".toUpperCase())
                || obj.getTipoVeiculo().toUpperCase().contains("das couve".toUpperCase());
    }
}
