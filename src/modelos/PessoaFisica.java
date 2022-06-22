/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.text.SimpleDateFormat;
import modelos.auxiliares.Endereco;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Objects;

/**
 *
 * @author tanak
 */
public class PessoaFisica extends Pessoa {

    private final static String nomeArquivoDisco = "clientePessoaFisica.txt";
    private final static String arquivoID = "idGeradoClientePessoaFisica.txt";

    protected String nome;
    protected String cpf;
    protected Date dataNascimento;

    public PessoaFisica(String email, Endereco endereco, String... telefone) {
        super(email, endereco, telefone);
    }

    public PessoaFisica(String nome, String cpf, Date dataNascimento, String email, Endereco endereco, String... telefone) {
        super(email, endereco, telefone);
        if (!validaCPF(cpf)) {
            throw new InputMismatchException("O CPF: " + cpf + " é inválido");
        }
        this.nome = nome;
        this.cpf = formataCPF(cpf);
        this.dataNascimento = dataNascimento;
    }

    public static String getNomeArquivoDisco() {
        return nomeArquivoDisco;
    }

    public static String getArquivoID() {
        return arquivoID;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setCpf(String cpf) throws Exception {
        if (!validaCPF(cpf)) {
            throw new Exception("CPF informado incorretamente");
        }
        this.cpf = formataCPF(cpf);//  o cpf será salvo já formatado corretamente
    }

    public static String desformatarCPF(String cpf) {
        if (cpf.matches("^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$")) {
            return cpf.substring(0, 3) + cpf.substring(4, 7) + cpf.substring(8, 11) + cpf.substring(12);
        } else {
            return cpf;
        }
    }

    public static boolean validaCPF(String CPF) {
        CPF = desformatarCPF(CPF);
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

    public static String formataCPF(String CPF) {
        CPF = desformatarCPF(CPF);
        return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "."
                + CPF.substring(6, 9) + "-" + CPF.substring(9, 11));

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.cpf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PessoaFisica other = (PessoaFisica) obj;
        return Objects.equals(this.cpf, other.cpf);
    }

    public boolean ativarEasterEgg() {
        return this.getNome().toUpperCase().contains("das couve".toUpperCase())
                || this.getEmail().toUpperCase().contains("das couve".toUpperCase())
                || this.getEndereco().ativarEasterEgg();
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return nome + ";" + cpf + ";" + sdf.format(dataNascimento) + ";" + super.toString();
    }
}
