/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import modelos.Servico;

/**
 *
 * @author tanak
 */
public class ManipulaBancoServicos implements IManipulaBanco<Servico> {

    @Override
    public Servico parse(String dados) throws Exception {
        String[] dadosServico = dados.split(";");
// * id, nomeServico, valorMaoDeObra, cadastro está ativo
        if (dadosServico.length != 4) {
            throw new Exception("Dados incorretos");
        }
        Servico s = new Servico(dadosServico[1], Double.parseDouble(dadosServico[2]));
        if (dadosServico[3].equals(String.valueOf(false))) {
            s.setCadastroAtivo(false);
        }
        return s;
    }

    @Override
    public String getNomeDoArquivoNoDisco() {
        return Servico.getNomeArquivoDisco();
    }

    @Override
    public int getID(Servico obj) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(Servico.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                Servico s = parse(linha);// * parsing linha
                if (s.equals(obj) && s.isCadastroAtivo()) {//  * encontrou
                    return Integer.parseInt(linha.split(";")[0]);// * retornando o id
                }
                linha = br.readLine();
            }
        }
        return 0;// * objeto não encontrado
    }

    @Override
    public String getNomeArquivoID() {
        return Servico.getArquivoID();
    }

    @Override
    public boolean isCadastroAtivo(Servico obj) {
        return obj.isCadastroAtivo();
    }

    @Override
    public Servico setCadastroAtivo(Servico obj, boolean flag) {
        obj.setCadastroAtivo(flag);
        return obj;
    }

    @Override
    public int buscar(String dado) throws Exception {
        ArrayList<Servico> listaServicos = buscarTodos();
        for (Servico s : listaServicos) {
            if (s.getNomeServico().equals(dado)) {//  * encontrou
                return getID(s);//  * retornando o id
            }
        }
        return 0;// * objeto não encontrado
    }

    @Override
    public boolean ativarEasterEgg(Servico obj) {
        return obj.getNomeServico().toUpperCase().contains("das couve".toUpperCase());
    }

}
