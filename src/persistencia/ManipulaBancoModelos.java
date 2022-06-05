package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import modelos.auxiliares.ModeloVeiculo;

public class ManipulaBancoModelos implements IManipulaBanco<ModeloVeiculo> {

    @Override
    public ModeloVeiculo parse(String linha) throws Exception {
        String[] dados = linha.split(";");
//  * id, nome do modelo, id da marca, cadastro está ativo

        if (dados.length != 4) {
            throw new Exception("Dados incorretos");
        }

        ModeloVeiculo m = new ModeloVeiculo(dados[1], Integer.parseInt(dados[2]));
        if (dados[3].equals(String.valueOf(false))) {//  * o cadastro está ativo
            m.setCadastroAtivo(false);
        }
        return m;
    }

    @Override
    public String getNomeDoArquivoNoDisco() {
        return ModeloVeiculo.getNomeArquivoDisco();
    }

    @Override
    public int getID(ModeloVeiculo obj) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(ModeloVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                ModeloVeiculo modelo = parse(linha);// * parsing linha
                if (modelo.equals(obj) && modelo.isCadastroAtivo()) {//  * encontrou
                    return Integer.parseInt(linha.split(";")[0]);// * retornando o id
                }
                linha = br.readLine();
            }
        }
        return 0;// * objeto não encontrado
    }

    @Override
    public String getNomeArquivoID() {
        return ModeloVeiculo.getArquivoID();
    }

    @Override
    public boolean isCadastroAtivo(ModeloVeiculo obj) {
        return obj.isCadastroAtivo();
    }

    @Override
    public ModeloVeiculo setCadastroAtivo(ModeloVeiculo obj, boolean flag) {
        obj.setCadastroAtivo(flag);
        return obj;
    }

    @Override
    public int buscar(String dado) throws Exception {
        ArrayList<ModeloVeiculo> listaModelos = buscarTodos();

        for (ModeloVeiculo modelo : listaModelos) {
            if (modelo.getNomeModelo().equals(dado)) {//    * encontrou
                return getID(modelo);// * retornando o id
            }
        }
        return 0;// * objeto não encontrado
    }
}
