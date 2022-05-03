// Essa classe será usada como a superclasse de todas as classes que tenham pessoas, pois teram vários atributos iguais
package modelos;

import java.util.InputMismatchException;

/**
 *
 * @author tanak
 */
public class Pessoa {

    protected String telefone;
    protected String email;
    protected Endereco endereco;

    public Pessoa() {
    }

    public Pessoa(String telefone, String email, Endereco endereco) {

        if (!validaTelefone(telefone)) {
            throw new InputMismatchException("Telefone inválido");
        }
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (!validaTelefone(telefone)) {
            throw new InputMismatchException("Telefone inválido");
        }
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return telefone + ";" + email + ";" + endereco.toString();
    }

    public boolean validaTelefone(String telefoneParaValidar) {
        telefoneParaValidar = formataTelefone(telefoneParaValidar);//formatando o telefone para colocar os parenteses no DDD e o hifen no meio do numero
        return (telefoneParaValidar.matches("^\\([1-9]{2}\\) 9[7-9]{1}[0-9]{3}\\-[0-9]{4}$"));
        // o método matches, diz se uma String segue o padrão informado, que no caso é o padrão de telefone, com parênteses e o hifen "(xx) 9XXXX-XXXX"
    }

    public String formataTelefone(String telefoneParaFormatar) {
        if (telefoneParaFormatar.length() < 7) {//se for menor q 7 não dá pra formatar
            return "Telefone inválido";
        }
        if (telefoneParaFormatar.matches("^\\([1-9]{2}\\) 9[7-9]{1}[0-9]{3}\\-[0-9]{4}$")) {//caso ja esteja formatado
            return telefoneParaFormatar;
        }
        StringBuilder telefoneFormatado = new StringBuilder();
        telefoneFormatado.append('(').append(telefoneParaFormatar.substring(0, 2)).append(')');
        telefoneFormatado.append(' ').append(telefoneParaFormatar.substring(2, 7)).append('-');
        telefoneFormatado.append(telefoneParaFormatar.substring(7));

        return String.valueOf(telefoneFormatado);
    }

}
