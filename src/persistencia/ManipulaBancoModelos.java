package persistencia;

import geradorId.GeradorId;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import modelos.auxiliares.MarcaVeiculo;
import modelos.auxiliares.ModeloVeiculo;

public class ManipulaBancoModelos implements IManipulaBanco<ModeloVeiculo> {

    @Override
    public void incluir(ModeloVeiculo obj) throws Exception {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(ModeloVeiculo.getNomeArquivoDisco(), true))) {
            int id = GeradorId.getID(MarcaVeiculo.getArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
        }//fecha arquivo
    }

    @Override
    public int buscar(ModeloVeiculo obj) throws Exception {
        return buscar(obj.getNomeModelo());//   * usando a busca por nome do modelo
    }

    public int buscar(String nomeModelo) throws Exception {//   * não  está conferindo se a marca tammbém é igual

        try ( BufferedReader br = new BufferedReader(new FileReader(ModeloVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                ModeloVeiculo m = parse(linha);
                if (m.getNomeModelo().equals(nomeModelo) && m.isCadastroAtivo()) {//  * encontrou o objeto
                    return Integer.parseInt(linha.substring(0, linha.indexOf(";")));//  * retornando o id
                }
                linha = br.readLine();
            }
        }
        return 0;// * objeto não encontrado
    }

    @Override
    public ModeloVeiculo buscar(int id) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(ModeloVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                int idObjAtual = Integer.parseInt(linha.substring(0, linha.indexOf(";")));//    * pegando id da linha
                ModeloVeiculo m = parse(linha);//   * parsing linha
                if (idObjAtual == id && m.isCadastroAtivo()) {//    * achou
                    return m;
                }
                linha = br.readLine();
            }
        }
        return null;//  * objeto não encontrado
    }

    @Override
    public ArrayList<ModeloVeiculo> buscarTodos() throws Exception {
        ArrayList<ModeloVeiculo> listaModelos = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(ModeloVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                ModeloVeiculo m = parse(linha);
                if (m.isCadastroAtivo()) {//    * só adicionar cadastros ativos
                    listaModelos.add(m);
                }
                linha = br.readLine();
            }
        }
        if (listaModelos.isEmpty()) {
            return null;//  * o banco está vazio
        }
        return listaModelos;//  * retornando lista com todos os modelos
    }

    @Override
    public void remover(ModeloVeiculo obj) throws Exception {
        int id = buscar(obj);// * pegando o id do objeto que será excluido
        remover(id);//  * usando a remoção por id
    }

    @Override
    public void remover(int id) throws Exception {

        ModeloVeiculo m = buscar(id);
        if (m == null) {
            System.out.println("id não encontrado: " + id);
            throw new Exception("Modelo não encontrado");
        }

        StringBuilder bancoCompleto = new StringBuilder();
        try ( BufferedReader br = new BufferedReader(new FileReader(ModeloVeiculo.getNomeArquivoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                int idObjAtual = Integer.parseInt(linha.substring(0, linha.indexOf(";")));//    * pegando id dessa linha
                m = parse(linha);// * parsing linha atual

                if (id == idObjAtual) {// * achou
                    m.setCadastroAtivo(false);//  * desativando cadastro antes de salvar
                }

                bancoCompleto.append(idObjAtual).append(";").append(m.toString()).append("\n");//   * adicionando linha que será salva no banco
                linha = br.readLine();
            }
//  *leu todo o banco de dados
        }

        try ( BufferedWriter br = new BufferedWriter(new FileWriter(ModeloVeiculo.getNomeArquivoDisco(), false))) {
            br.write(bancoCompleto.toString());//   * reescrevendo todo o banco
        }
    }

    @Override
    public void editar(ModeloVeiculo objParaRemover, ModeloVeiculo objParaAdicionar) throws Exception {
        remover(objParaRemover);
        incluir(objParaRemover);
    }

    @Override
    public void editar(int idObjParaRemover, ModeloVeiculo objParaAdicionar) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private ModeloVeiculo parse(String linha) throws Exception {
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

}
