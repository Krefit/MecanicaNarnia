
package persistencia;

import geradorId.GeradorId;
import modelos.*;
import java.io.BufferedWriter;
import java.io.FileWriter;


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
            bw.write(id+";"+obj.toString() + "\n");
            //fecha arquivo
            bw.close();
        } catch (Exception erro) {
            throw erro;
        }
    }
}
