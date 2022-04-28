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

    public static int getID() throws FileNotFoundException, IOException {
        String nomeDoArquivo = "idGerado.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(nomeDoArquivo));
                BufferedWriter bw = new BufferedWriter(new FileWriter(nomeDoArquivo, false))) {

            //lendo o id antigo
            int id = Integer.parseInt(br.readLine());

            //aumentando o id
            id++;

            //apagando valor antigo e escrevendo novo valor no arquivo
            bw.write(id);

            return id;
        }
    }
}
