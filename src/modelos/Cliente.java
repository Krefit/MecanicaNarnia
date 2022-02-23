/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author ALUNO
 */
public class Cliente {

    private String nome = "";
    private String cpf = "";
    private String endereco = "";
    private String telefone = "";
    private String email = "";

    public Cliente(String nome, String cpf, String endereco, String telefone, String email) throws Exception {
        this.nome = nome;
        if (!validaCPF(cpf)) {
            throw new Exception("CPF inválido!!!");
        } else {
            this.cpf = imprimeCPF(cpf);
        }

        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;

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
        if (validaCPF(cpf)) {
            this.cpf = imprimeCPF(cpf);

        } else {
            throw new Exception("CPF inválido!!!");

        }
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean validaCPF(String CPF) {
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

    public String imprimeCPF(String CPF) {
        return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "."
                + CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }

     /*   *essa funcionalidade não está completa ainda
   
    private boolean validaTelefone(String telefone) {
//  * caso a quantidade de digitos esteja errada, telefone é falso
//  * um telefone fixo tem só 10 digitos(8 numeros mais o DDD)
        if (telefone.length() == 10 || telefone.length() == 11) {
            return true;
        } else {
            return true;
        }
    }
     */
    private String formataTelefone(String telefone) {
        switch (telefone.length()) {
//  caso seja um telefone celular, com o 9
            case 11:
                return "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 7) + "-" + telefone.substring(7, telefone.length());

//  caso seja um telefone fixo
            default:
                return "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 6) + " - " + telefone.substring(6, telefone.length());
        }
    }

}
