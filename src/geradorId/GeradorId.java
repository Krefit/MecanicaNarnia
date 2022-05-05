package geradorId;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author eugeniojulio"
 */
public class GeradorId {

    public static int getID(String caminhoArquivo) throws FileNotFoundException, IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {

            //lendo o id antigo
            String linha = br.readLine();
            int id = Integer.parseInt(linha);

            //aumentando o id
            id++;

            BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo, false));
            //apagando valor antigo e escrevendo novo valor no arquivo
            bw.write(String.valueOf(id));

            bw.close();
            return id;
        }
    }
}
