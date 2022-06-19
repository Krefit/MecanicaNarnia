/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.auxiliares;

import enumerations.EstadosBrazil;
import java.util.Objects;

/**
 *
 * @author tanak
 */
public class Endereco {

    private String tipoLogradouro;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private EstadosBrazil estado;
    private String CEP;

    public Endereco() {
    }

    public Endereco(String tipoLogradouro, String logradouro, String numero, String complemento, String bairro, String cidade, EstadosBrazil estado, String CEP) throws Exception {
        if (!ValidaCep(CEP)) {
            throw new Exception("O CEP " + CEP + " é inválido");
        }
        this.tipoLogradouro = tipoLogradouro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.CEP = CEP;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public EstadosBrazil getEstado() {
        return estado;
    }

    public void setEstado(EstadosBrazil estado) {
        this.estado = estado;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) throws Exception {
        if (!ValidaCep(CEP)) {
            throw new Exception("O CEP " + CEP + " é inválido");
        }
        this.CEP = CEP;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.tipoLogradouro);
        hash = 73 * hash + Objects.hashCode(this.logradouro);
        hash = 73 * hash + Objects.hashCode(this.numero);
        hash = 73 * hash + Objects.hashCode(this.complemento);
        hash = 73 * hash + Objects.hashCode(this.bairro);
        hash = 73 * hash + Objects.hashCode(this.cidade);
        hash = 73 * hash + Objects.hashCode(this.estado);
        hash = 73 * hash + Objects.hashCode(this.CEP);
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
        final Endereco other = (Endereco) obj;
        if (!Objects.equals(this.tipoLogradouro, other.tipoLogradouro)) {
            return false;
        }
        if (!Objects.equals(this.logradouro, other.logradouro)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        if (!Objects.equals(this.complemento, other.complemento)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.CEP, other.CEP)) {
            return false;
        }
        return this.estado == other.estado;
    }
    //Método que valida o Cep

    private boolean ValidaCep(String cep) {
        if (cep.length() == 8) {
            cep = cep.substring(0, 5) + "-" + cep.substring(5, 8);
            //txt.Text = cep;
        }
        return cep.matches("[0-9]{5}-[0-9]{3}");
    }

    @Override
    public String toString() {
        return tipoLogradouro + "," + logradouro + "," + numero + "," + complemento + "," + bairro + "," + cidade + "," + estado + "," + CEP;
    }

}
