/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import enumerations.TipoCliente;
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
            String tipoVeiculo, String placa, int anoFabricacao, int anoModelo, int quilometragem, int idDonoVeiculo, TipoCliente tipoCliente) throws Exception {
        if (!validaPlaca(placa)) {
            throw new Exception("A placa: \"" + placa + "\" não é válida");
        }
        if (!validaRenavan(renavam)) {
            throw new Exception("O renavan: \"" + renavam + "\" não é válido");
        }
        if (!validaChassi(chassi)) {
            throw new Exception("O Chassi: \"" + chassi + "\" não é válido");
        }

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

    public void setChassi(String chassi) throws Exception {
        if (!validaChassi(chassi)) {
            throw new Exception("O Chassi: \"" + chassi + "\" não é válido");
        }
        this.chassi = chassi;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) throws Exception {
        if (!validaRenavan(renavam)) {
            throw new Exception("O renavan: \"" + renavam + "\" não é válido");
        }
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

    public void setPlaca(String placa) throws Exception {
        if (!validaPlaca(placa)) {
            throw new Exception("A placa: \"" + placa + "\" não é válida");
        }
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
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.placa);
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
        return Objects.equals(this.placa, other.placa);
    }



    private boolean validaRenavan(String renavan) {
        final String FORMATED = "(\\d{4})[.](\\d{6})-(\\d{1})";
        final String UNFORMATED = "(\\d{4})(\\d{6})(\\d{1})";
        return renavan.matches(FORMATED) || renavan.matches(UNFORMATED);
    }

    private boolean validaPlaca(String placa) {
        return placa.replace("-", "").matches("[a-zA-Z]{3}[0-9]{1}[a-zA-Z0-9]{1}[0-9]{2}");
    }

    private boolean validaChassi(String chassi) {
        chassi = chassi.toUpperCase().replace(" ", "");
        return !chassi.matches("^O") && !chassi.matches(" ") && !chassi.matches("[iIoOqQ]") && chassi.length() == 17;
    }

    @Override
    public String toString() {
        return idModelo + ";" + idMarca + ";" + chassi + ";" + renavam + ";" + tipoVeiculo + ";" + placa + ";" + anoFabricacao
                + ";" + anoModelo + ";" + quilometragem + ";" + idDonoVeiculo + ";" + cadastroAtivo + ";" + tipoCliente;
    }

}
