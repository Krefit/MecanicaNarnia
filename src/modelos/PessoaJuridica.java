/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import modelos.auxiliares.Endereco;
import java.util.InputMismatchException;
import java.util.Objects;

/**
 *
 * @author tanak
 */
public class PessoaJuridica extends Pessoa {

    private final static String nomeArquivoDisco = "clientePessoaJuridica.txt";
    private final static String arquivoID = "idGeradoPessoaJuridica.txt";

    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;

    public PessoaJuridica() {
    }

    public PessoaJuridica(String cnpj, String razaoSocial, String nomeFantasia, String email, Endereco endereco, String... telefone) {
        super(email, endereco, telefone);
        if (!validaCNPJ(cnpj)) {
            cnpj = desformatarCNPJ(cnpj);
            if (!validaCNPJ(cnpj)) {
                throw new InputMismatchException("CNPJ inválido!");
            }
        }
        this.cnpj = formataCNPJ(cnpj);//salvando cnpj já formatado
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
    }

    public static String getNomeArquivoDisco() {
        return nomeArquivoDisco;
    }

    public static String getArquivoID() {
        return arquivoID;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if (!validaCNPJ(cnpj)) {
            cnpj = desformatarCNPJ(cnpj);
            if (!validaCNPJ(cnpj)) {
                throw new InputMismatchException("CNPJ inválido!");
            }
        }
        this.cnpj = formataCNPJ(cnpj);//salvando cnpj já formatado
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public static boolean validaCNPJ(String CNPJ) {
// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111")
                || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333")
                || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
                || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777")
                || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999")
                || (CNPJ.length() != 14)) {
            return (false);
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

// "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
// Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
// converte o i-ésimo caractere do CNPJ em um número:
// por exemplo, transforma o caractere '0' no inteiro 0
// (48 eh a posição de '0' na tabela ASCII)
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

// Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }

// Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static String formataCNPJ(String CNPJ) {
// máscara do CNPJ: 99.999.999/9999-99
        return (CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "."
                + CNPJ.substring(5, 8) + "/" + CNPJ.substring(8, 12) + "-"
                + CNPJ.substring(12, 14));
    }

    private static String desformatarCNPJ(String CNPJ) {
        return CNPJ.substring(0, 2) + CNPJ.substring(3, 6)
                + CNPJ.substring(7, 10) + CNPJ.substring(11, 15)
                + CNPJ.substring(16, 18);

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.cnpj);
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
        final PessoaJuridica other = (PessoaJuridica) obj;
        return Objects.equals(this.cnpj, other.cnpj);
    }



    @Override
    public String toString() {
        return nomeFantasia + ';' + cnpj + ';' + razaoSocial + ';' + super.toString();
    }
}
