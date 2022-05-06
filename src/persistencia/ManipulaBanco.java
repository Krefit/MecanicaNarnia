package persistencia;

import enumerations.EstadosBrazil;
import geradorId.GeradorId;
import java.io.BufferedReader;
import modelos.*;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import modelos.auxiliares.Endereco;

public class ManipulaBanco {

    public static void incluir(Cliente_PessoaFisica obj) throws Exception {
        try {
            int id = GeradorId.getID(obj.getarquivoID());
            //objeto.setId(id);
            //cria arquivo CLIENTE
            FileWriter fw = new FileWriter(obj.getNomeArquivoDisco(), true);//true = acumular
            //cria buffer
            BufferedWriter bw = new BufferedWriter(fw);
            //escreve arquivo
            bw.write(id + ";" + obj.toString() + "\n");
            //fecha arquivo
            bw.close();
        } catch (Exception erro) {
            throw erro;
        }
    }

    public static Cliente_PessoaFisica buscar(Cliente_PessoaFisica obj) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(obj.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                if (linha.endsWith(obj.toString())) {
                    String[] dados = linha.split(";");
                    if (dados.length != 7) {
                        throw new Exception("Dados incorretos");
                    }
                    
                    String[] dadosEndereco = dados[6].split(",");
                    if (dadosEndereco.length != 8) {
                        throw new Exception("Dados incorretos");
                    }
                    
                    Endereco endereco = new Endereco(dadosEndereco[0], dadosEndereco[1], dadosEndereco[2], dadosEndereco[3], dadosEndereco[4], dadosEndereco[5], Enum.valueOf(EstadosBrazil.class, dadosEndereco[6]), dadosEndereco[7]);

                    System.out.println(dados[2]);
                    return new Cliente_PessoaFisica(dados[1], dados[2], new Date(), dados[4], dados[5], endereco);
                }

                linha = br.readLine();
            }
        }
        throw new Exception("Cliente não encontrado");

    }

    public static void remover(Cliente_PessoaFisica obj) throws IOException, Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(obj.getNomeArquivoDisco()))) {
            boolean achou = false;
            String linha = br.readLine();
            StringBuilder lista = new StringBuilder();

            while (linha != null) {
                if (!linha.endsWith(obj.toString())) {//ignorando o ID, pois o obj não tem id
                    lista.append(linha).append("\n");
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
}
