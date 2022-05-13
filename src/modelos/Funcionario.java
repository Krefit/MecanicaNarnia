/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import modelos.auxiliares.Endereco;
import java.util.Date;

/**
 *
 * @author ALUNO
 */
public class Funcionario extends PessoaFisica {

    private String especialidade;
    private double salarioMensal;
    private double salariohora;
    private int matricula;

    public Funcionario(String especialidade, double salarioMensal, double salariohora, int matricula, String nome, String cpf, Date dataNascimento, String telefone, String email, Endereco endereco) {
        super(nome, cpf, dataNascimento, telefone, email, endereco);
        this.especialidade = especialidade;
        this.salarioMensal = salarioMensal;
        this.salariohora = salariohora;
        this.matricula = matricula;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public double getSalarioMensal() {
        return salarioMensal;
    }

    public void setSalarioMensal(double salarioMensal) {
        this.salarioMensal = salarioMensal;
    }

    public double getSalariohora() {
        return salariohora;
    }

    public void setSalariohora(double salariohora) {
        this.salariohora = salariohora;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + especialidade + ";" + salarioMensal + ";" + salariohora + ";" + matricula;
    }

}
