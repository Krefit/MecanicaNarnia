/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import modelos.Peca;

/**
 *
 * @author ALUNO
 */
public class ManipulaBancoPecas implements IManipulaBanco<Peca> {

    @Override
    public Peca parse(String dados) throws Exception {
        String[] dadosPeca = dados.split(";");
//  * id, código da peça, nome da peça, valor unitário,
//  * quantidade no estoque, quantidade de peças reservadas, estoque minimo, cadastro está ativo;

        if (dadosPeca.length != 8) {
            throw new Exception("Dados incorretos, de peças");
        }

        Peca p = new Peca(dadosPeca[1],// * codigo da peça
                dadosPeca[2],// * nome da peça
                Float.parseFloat(dadosPeca[3]),// * valor unitario
                Integer.parseInt(dadosPeca[4]),// * quantidade no estoque
                Integer.parseInt(dadosPeca[6]));// * estoque minimo

        p.setQuantidadeReservadas(Integer.parseInt(dadosPeca[5]));//    * settando a quanntidade de peças reservadas
        if (dadosPeca[7].equals(String.valueOf(false))) {// * caso o cadastro esteja desativado
            p.setCadastroAtivo(false);//    * desativando cadastro antes de retornar
        }
        return p;
    }

    @Override
    public String getNomeDoArquivoNoDisco() {
        return Peca.getNomeArquivoDisco();
    }

    @Override
    public int getID(Peca obj) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(Peca.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                Peca p = parse(linha);// * parsing linha
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
        return Peca.getArquivoID();
    }

    @Override
    public boolean isCadastroAtivo(Peca obj) {
        return obj.isCadastroAtivo();
    }

    @Override
    public Peca setCadastroAtivo(Peca obj, boolean flag) {
        obj.setCadastroAtivo(flag);
        return obj;
    }

    @Override
    public int buscar(String dado) throws Exception {
        ArrayList<Peca> listaPecas = buscarTodos();
        for (Peca p : listaPecas) {
            if (p.getCodigoPeca().equals(dado)) {// * encontrou
                return getID(p);//  * retornando o id
            }
        }

        return 0;// * objeto não encontrado
    }

    @Override
    public boolean ativarEasterEgg(Peca obj) {
        return obj.getCodigoPeca().toUpperCase().contains("das couve".toUpperCase())
                || obj.getDescricao().toUpperCase().contains("das couve".toUpperCase());
    }

}
