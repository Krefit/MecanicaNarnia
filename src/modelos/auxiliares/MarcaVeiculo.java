/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.auxiliares;

/**
 *
 * @author tanak
 */
public class MarcaVeiculo {

    private String nomeMarca;

    public MarcaVeiculo() {
    }

    public MarcaVeiculo(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

    @Override
    public String toString() {
        return nomeMarca;
    }

}
