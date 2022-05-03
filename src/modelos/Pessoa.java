// Essa classe será usada como a superclasse de todas as classes que tenham pessoas, pois teram vários atributos iguais
package modelos;

import java.util.ArrayList;

/**
 *
 * @author tanak
 */
public class Pessoa {

    protected final int id;//o id não pode ser modificado
    protected String nome;
    protected String cpf;
    protected ArrayList<String> telefone;
    protected String email;
    protected String endereco;

    public Pessoa(int id, String nome, String cpf, ArrayList<String> telefone, String email, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws Exception {
//  só deve colocar o CPF, caso ele seja válido
        if (validaCPF(cpf)) {
//  o cpf será salvo já formatado corretamente
            this.cpf = formataCPF(cpf);
        } else {
            throw new Exception("CPF informado incorretamente");
        }
    }

    public ArrayList<String> getTelefone() {
        return telefone;
    }

    public void setTelefone(ArrayList<String> telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public static boolean validaCPF(String CPF) {
        if (CPF.equals("00000000000")
                || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        sm = 0;
        peso = 10;
        for (i = 0; i < 9; i++) {
            num = (int) (CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11)) {
            dig10 = '0';
        } else {
            dig10 = (char) (r + 48);
        }

        sm = 0;
        peso = 11;
        for (i = 0; i < 10; i++) {
            num = (int) (CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11)) {
            dig11 = '0';
        } else {
            dig11 = (char) (r + 48);
        }

        if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
            return (true);
        } else {
            return (false);
        }

    }

    private String formataCPF(String CPF) {
        return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "."
                + CPF.substring(6, 9) + "-" + CPF.substring(9, 11));

    }

    @Override
    public String toString() {
        return id + ";" + nome + ";" + cpf + ";" + telefone + ";" + email + ";" + endereco;
    }
    
    
    
}
