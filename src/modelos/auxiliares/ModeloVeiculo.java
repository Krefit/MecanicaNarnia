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
public class ModeloVeiculo {

    private String NomeModelo;
    private int idMarca;

    public ModeloVeiculo() {
    }

    public ModeloVeiculo(String NomeModelo, int idMarca) {
        this.NomeModelo = NomeModelo;
        this.idMarca = idMarca;
    }

    public String getNomeModelo() {
        return NomeModelo;
    }

    public void setNomeModelo(String NomeModelo) {
        this.NomeModelo = NomeModelo;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

}
