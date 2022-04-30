/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enumerations;

/**
 *
 * @author ALUNO
 */
public enum SituacaoVeiculo {
    ATIVO(0), INATIVO(1);

    private int codigo;

    private SituacaoVeiculo(int codigo) {
        this.codigo = codigo;
    }

}
