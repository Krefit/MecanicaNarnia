/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import geradorId.GeradorId;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import tela.TelaEasterEgg;

/**
 *
 * @author tanak
 * @param <T>
 */
public interface IManipulaBanco<T> {

    public boolean ativarEasterEgg(T obj);

    public String getNomeDoArquivoNoDisco();

    public int getID(T obj) throws Exception;

    public String getNomeArquivoID();

    public boolean isCadastroAtivo(T obj);

    public T setCadastroAtivo(T obj, boolean flag);//   * vai retornar o mesmo objeto mas com o cadastro mudado de acordo com a flag

    public T parse(String dados) throws Exception;

    public default void incluir(T obj) throws Exception {
        if (ativarEasterEgg(obj)) {
            new TelaEasterEgg().setVisible(true);
        }
        ArrayList<T> listaCompleta = buscarTodos();//   * pegando todos os dados ativos do banco
        if (listaCompleta != null && !listaCompleta.isEmpty()) {//  * tem algo na lista
            for (T objAtual : listaCompleta) {//    * percorrendo toda a lista
                if (((T) obj).equals(((T) objAtual))) {//   * já existe um dado igual no banco
                    throw new Exception("Já existe um cadastro ativo disso!");
                }
            }
        }
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(getNomeDoArquivoNoDisco(), true))) {
            int id = GeradorId.getID(getNomeArquivoID());
            bw.write(id + ";" + obj.toString() + "\n");
        }
    }

    public default int buscar(T obj) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader(getNomeDoArquivoNoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                T objAtual = parse(linha);// * parsing linha
                if (obj.equals(objAtual) && isCadastroAtivo(objAtual)) {// * encontrou
                    return getID(obj);
                }
                linha = br.readLine();
            }
        }

        return 0;//  * objeto não encontrado

    }

    public int buscar(String dado) throws Exception;

    public default T buscar(int id) throws Exception {
        if (id == 0) {
            return null;
        }
        try ( BufferedReader br = new BufferedReader(new FileReader(getNomeDoArquivoNoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                T obj = parse(linha);// * parsing linha
                int idAtual = getID(obj);
                if (getID(obj) == id && isCadastroAtivo(obj)) {// * encontrou
                    return obj;
                }
                linha = br.readLine();
            }
        }

        return null;//  * objeto não encontrado
    }

    public default ArrayList<T> buscarTodos() throws Exception {
        ArrayList<T> listaCompleta = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(getNomeDoArquivoNoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                T obj = parse(linha);// * parsing linha
                if (isCadastroAtivo(obj)) {// * adicionar apenas cadastros ativos
                    listaCompleta.add(obj);
                }
                linha = br.readLine();
            }
        }

        if (listaCompleta.isEmpty()) {//    * caso não tenha nenhum cadastro ativo
            return null;
        } else {
            return listaCompleta;
        }
    }

    public default ArrayList<T> buscarTodosRemovidos() throws Exception {
        ArrayList<T> listaCompleta = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(getNomeDoArquivoNoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                T obj = parse(linha);// * parsing linha
                if (!isCadastroAtivo(obj)) {// * adicionar apenas cadastros ativos
                    listaCompleta.add(obj);
                }
                linha = br.readLine();
            }
        }

        if (listaCompleta.isEmpty()) {//    * caso não tenha nenhum cadastro ativo
            return null;
        } else {
            return listaCompleta;
        }
    }

    public default void remover(T obj) throws Exception {
        int id = buscar(obj);// * pegando o id do objeto
        remover(id);//  * usando a exclusão por id
    }

    public default void remover(int id) throws Exception {
        StringBuilder bancoCompleto = new StringBuilder();//   * vai armazenar todos os dados do banco, para serem reescritos
        try ( BufferedReader br = new BufferedReader(new FileReader(getNomeDoArquivoNoDisco()))) {
            String linha = br.readLine();
            while (linha != null) {
                T obj = parse(linha);// * parsing linha
                if (getID(obj) == id) {//   * encontrou
                    obj = setCadastroAtivo(obj, false);// * desativando o cadastro antes de reescrever
                }
                bancoCompleto.append(getID(obj)).append(";");// * salvando id do objeto
                bancoCompleto.append(obj).append("\n");//   * salvando dados para serem reescritos
                linha = br.readLine();
            }
//  * leu todos os dados do banco
            try ( BufferedWriter bw = new BufferedWriter(new FileWriter(getNomeDoArquivoNoDisco()))) {
                bw.write(bancoCompleto.toString());//   * reescreveu todo o banco
            }
        }
    }

    public default void editar(T objParaRemover, T objParaAdicionar) throws Exception {
        int id = getID(objParaRemover);//   * pegando o id do objeto que será excluido
        editar(id, objParaAdicionar);// * chamando a edição por id
    }

    public default void editar(int idObjParaRemover, T objParaAdicionar) throws Exception {
        remover(idObjParaRemover);//    * removendo objeto antigo
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(getNomeDoArquivoNoDisco(), true))) {
            bw.write(idObjParaRemover + ";" + objParaAdicionar.toString() + "\n");//    * salvando novo valor no banco e mantendo o id
        }
    }

}
