/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import modelos.auxiliares.MarcaVeiculo;

/**
 *
 * @author tanak
 */
public class ManipulaBancoMarca implements IManipulaBanco<MarcaVeiculo> {

    @Override
    public MarcaVeiculo parse(String dadosCompletos) throws Exception {
        String[] dados = dadosCompletos.split(";");
//  * id, nome da marca, cadastro está ativo

        if (dados.length != 3) {
            System.out.println(dados.length);
            System.out.println(dadosCompletos);
            throw new Exception("Dados incorretos");
        }
        MarcaVeiculo marca = new MarcaVeiculo(dados[1]);
        if (dados[2].equals("false")) {
            marca.setCadastroAtivo(false);
        }
        return marca;
    }

    @Override
    public String getNomeDoArquivoNoDisco() {
        return MarcaVeiculo.getNomeArquivoDisco();
    }

    @Override
    public int getID(MarcaVeiculo obj) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(MarcaVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                MarcaVeiculo v = parse(linha);// * parsing linha
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
        return MarcaVeiculo.getArquivoID();
    }

    @Override
    public boolean isCadastroAtivo(MarcaVeiculo obj) {
        return obj.isCadastroAtivo();
    }

    @Override
    public MarcaVeiculo setCadastroAtivo(MarcaVeiculo obj, boolean flag) {
        obj.setCadastroAtivo(flag);
        return obj;
    }

    @Override
    public int buscar(String dado) throws Exception {
        ArrayList<MarcaVeiculo> listaMarcas = buscarTodos();
        for (MarcaVeiculo marca : listaMarcas) {
            if (marca.getNomeMarca().equals(dado)) {//  * encontrou
                return getID(marca);//  * retornando o id
            }
        }

        return 0;// * objeto não encontrado
    }

    @Override
    public boolean ativarEasterEgg(MarcaVeiculo m) {
        return m.getNomeMarca().toUpperCase().contains("das couve".toUpperCase());
    }
}
