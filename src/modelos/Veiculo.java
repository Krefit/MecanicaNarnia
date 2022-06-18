/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import enumerations.TipoCliente;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author ALUNO
 */
public class Veiculo {

    private final static String NomeArquivoDisco = "veiculo.txt";
    private final static String arquivoID = "idGeradoVeiculo.txt";

    private int idModelo;//talvez criar uma classe separada
    private int idMarca;
    private String chassi;
    private String renavam;
    private String tipoVeiculo;
    private String placa;
    private int anoFabricacao;
    private int anoModelo;
    private int quilometragem;
    private int idDonoVeiculo;
    private boolean cadastroAtivo;
    private TipoCliente tipoCliente;

    public Veiculo() {
    }

    public Veiculo(int idModelo, int idMarca, String chassi, String renavam,
            String tipoVeiculo, String placa, int anoFabricacao, int anoModelo, int quilometragem, int idDonoVeiculo, TipoCliente tipoCliente) {
        this.idModelo = idModelo;
        this.idMarca = idMarca;
        this.chassi = chassi;
        this.renavam = renavam;
        this.tipoVeiculo = tipoVeiculo;
        this.placa = placa;
        this.anoFabricacao = anoFabricacao;
        this.anoModelo = anoModelo;
        this.quilometragem = quilometragem;
        this.idDonoVeiculo = idDonoVeiculo;
        this.cadastroAtivo = true;
        this.tipoCliente = tipoCliente;
    }

    public boolean isCadastroAtivo() {
        return cadastroAtivo;
    }

    public void setCadastroAtivo(boolean cadastroAtivo) {
        this.cadastroAtivo = cadastroAtivo;
    }

    public static String getNomeArquivoDisco() {
        return NomeArquivoDisco;
    }

    public static String getArquivoID() {
        return arquivoID;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public int getIdDonoVeiculo() {
        return idDonoVeiculo;
    }

    public void setIdDonoVeiculo(int idDonoVeiculo) {
        this.idDonoVeiculo = idDonoVeiculo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.idModelo;
        hash = 37 * hash + this.idMarca;
        hash = 37 * hash + Objects.hashCode(this.chassi);
        hash = 37 * hash + Objects.hashCode(this.renavam);
        hash = 37 * hash + Objects.hashCode(this.tipoVeiculo);
        hash = 37 * hash + Objects.hashCode(this.placa);
        hash = 37 * hash + this.anoFabricacao;
        hash = 37 * hash + this.anoModelo;
        hash = 37 * hash + this.quilometragem;
        hash = 37 * hash + this.idDonoVeiculo;
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
        final Veiculo other = (Veiculo) obj;
        if (this.idModelo != other.idModelo) {
            return false;
        }
        if (this.idMarca != other.idMarca) {
            return false;
        }
        if (this.anoFabricacao != other.anoFabricacao) {
            return false;
        }
        if (this.anoModelo != other.anoModelo) {
            return false;
        }
        if (this.quilometragem != other.quilometragem) {
            return false;
        }
        if (this.idDonoVeiculo != other.idDonoVeiculo) {
            return false;
        }
        if (!Objects.equals(this.chassi, other.chassi)) {
            return false;
        }
        if (!Objects.equals(this.renavam, other.renavam)) {
            return false;
        }
        if (!Objects.equals(this.tipoVeiculo, other.tipoVeiculo)) {
            return false;
        }
        return Objects.equals(this.placa, other.placa);
    }

    @Override
    public String toString() {
        return idModelo + ";" + idMarca + ";" + chassi + ";" + renavam + ";" + tipoVeiculo + ";" + placa + ";" + anoFabricacao
                + ";" + anoModelo + ";" + quilometragem + ";" + idDonoVeiculo + ";" + cadastroAtivo + ";" + tipoCliente;
    }

}
