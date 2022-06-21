/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package tela;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.OrdemDeServico;
import modelos.Peca;
import modelos.Servico;
import modelos.Veiculo;
import persistencia.ManipulaBancoOrdemServico;
import persistencia.ManipulaBancoPecas;
import persistencia.ManipulaBancoServicos;
import persistencia.ManipulaBancoVeiculo;

/**
 *
 * @author tanak
 */
public class TelaListaOS extends javax.swing.JInternalFrame {

    JDesktopPane telaAnterior;

    /**
     * Creates new form TelaListaOS
     *
     * @param telaAnterior
     */
    public TelaListaOS(JDesktopPane telaAnterior) {
        initComponents();
        this.telaAnterior = telaAnterior;
        loadTableOSs();

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTableOSs.getModel());
        jTableOSs.setRowSorter(sorter);
    }

    private void loadTableOSs() {
        try {

            DefaultTableModel table = (DefaultTableModel) jTableOSs.getModel();
            table.setRowCount(0);

            ArrayList<OrdemDeServico> listaOSs = null;
            listaOSs = new ManipulaBancoOrdemServico().buscarTodos();

            if (listaOSs != null && !listaOSs.isEmpty()) {//    * caso exista alguma OS para mostrar
                for (OrdemDeServico os : listaOSs) {
                    Veiculo v = new ManipulaBancoVeiculo().buscar(os.getIdVeiculo());
                    Servico servico = new ManipulaBancoServicos().buscar(os.getIdServico());
                    Peca peca = new ManipulaBancoPecas().buscar(os.getIdPeca());
                    String dataAbertura = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataEntrada());
                    String dados[] = {String.valueOf(os.getCodigo()),// * código da OS
                        v.getPlaca(),// * placa do veículo
                        String.valueOf(os.getSituacao()),// * status da OS
                        servico.getNomeServico(),// * nome do serviço feito
                        String.valueOf(servico.getValorMaoDeObra()),//  * valor da mão de obra
                        dataAbertura,// * data de abertura da OS
                        "em aberto",//  * data de fechamento da OS
                        "-----",//  * código da peça usada
                        String.valueOf("0"),//  * quantidade de peças  usadas
                        String.valueOf("0"),//  * valor unitário da peça
                        String.valueOf(os.calcularValorTotalSemDesconto()),// * valor total da OS, sem desconto
                        String.valueOf(os.calcularValorTotalComDesconto())};// * valor total da OS, com desconto

                    if (os.getDataSaida() != null) {//  * já foi concluida
                        dados[6] = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataSaida());

                    }
                    if (peca != null) {//   * usa alguma peça
                        dados[7] = peca.getCodigoPeca();//  * código da peça usada
                        dados[8] = String.valueOf(os.getQuantidadePeca());//  * quantidade de peças  usadas
                        dados[9] = String.valueOf(peca.getValorPeca());//  * valor unitário da peça
                        dados[10] = String.valueOf(os.calcularValorTotalSemDesconto());// * valor total da OS, sem desconto
                        dados[11] = String.valueOf(os.calcularValorTotalComDesconto());// * valor total da OS, com desconto
                    }
                    table.addRow(dados);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void filtraTabelaOrcamentosCancelados() {
        try {

            DefaultTableModel table = (DefaultTableModel) jTableOSs.getModel();
            table.setRowCount(0);

            ArrayList<OrdemDeServico> listaOSs = null;
            listaOSs = new ManipulaBancoOrdemServico().buscarTodos();

            if (listaOSs != null && !listaOSs.isEmpty()) {//    * caso exista alguma OS para mostrar
                for (OrdemDeServico os : listaOSs) {
                    if (os.getSituacao() == OrdemDeServico.SituacaoOrdemServico.CANCELADA) {
                        Veiculo v = new ManipulaBancoVeiculo().buscar(os.getIdVeiculo());
                        Servico servico = new ManipulaBancoServicos().buscar(os.getIdServico());
                        Peca peca = new ManipulaBancoPecas().buscar(os.getIdPeca());
                        String dataAbertura = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataEntrada());
                        String dados[] = {String.valueOf(os.getCodigo()),// * código da OS
                            v.getPlaca(),// * placa do veículo
                            String.valueOf(os.getSituacao()),// * status da OS
                            servico.getNomeServico(),// * nome do serviço feito
                            String.valueOf(servico.getValorMaoDeObra()),//  * valor da mão de obra
                            dataAbertura,// * data de abertura da OS
                            "em aberto",//  * data de fechamento da OS
                            "-----",//  * código da peça usada
                            String.valueOf("0"),//  * quantidade de peças  usadas
                            String.valueOf("0"),//  * valor unitário da peça
                            String.valueOf(os.calcularValorTotalSemDesconto()),// * valor total da OS, sem desconto
                            String.valueOf(os.calcularValorTotalComDesconto())};// * valor total da OS, com desconto

                        if (os.getDataSaida() != null) {//  * já foi concluida
                            dados[6] = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataSaida());

                        }
                        if (peca != null) {//   * usa alguma peça
                            dados[7] = peca.getCodigoPeca();//  * código da peça usada
                            dados[8] = String.valueOf(os.getQuantidadePeca());//  * quantidade de peças  usadas
                            dados[9] = String.valueOf(peca.getValorPeca());//  * valor unitário da peça
                            dados[10] = String.valueOf(os.calcularValorTotalSemDesconto());// * valor total da OS, sem desconto
                            dados[11] = String.valueOf(os.calcularValorTotalComDesconto());// * valor total da OS, com desconto
                        }
                        table.addRow(dados);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void filtraTabelaOrcamentos() {
        try {

            DefaultTableModel table = (DefaultTableModel) jTableOSs.getModel();
            table.setRowCount(0);

            ArrayList<OrdemDeServico> listaOSs = null;
            listaOSs = new ManipulaBancoOrdemServico().buscarTodos();

            if (listaOSs != null && !listaOSs.isEmpty()) {//    * caso exista alguma OS para mostrar
                for (OrdemDeServico os : listaOSs) {
                    if (os.getSituacao() == OrdemDeServico.SituacaoOrdemServico.EM_ABERTO) {
                        Veiculo v = new ManipulaBancoVeiculo().buscar(os.getIdVeiculo());
                        Servico servico = new ManipulaBancoServicos().buscar(os.getIdServico());
                        Peca peca = new ManipulaBancoPecas().buscar(os.getIdPeca());
                        String dataAbertura = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataEntrada());
                        String dados[] = {String.valueOf(os.getCodigo()),// * código da OS
                            v.getPlaca(),// * placa do veículo
                            String.valueOf(os.getSituacao()),// * status da OS
                            servico.getNomeServico(),// * nome do serviço feito
                            String.valueOf(servico.getValorMaoDeObra()),//  * valor da mão de obra
                            dataAbertura,// * data de abertura da OS
                            "em aberto",//  * data de fechamento da OS
                            "-----",//  * código da peça usada
                            String.valueOf("0"),//  * quantidade de peças  usadas
                            String.valueOf("0"),//  * valor unitário da peça
                            String.valueOf(os.calcularValorTotalSemDesconto()),// * valor total da OS, sem desconto
                            String.valueOf(os.calcularValorTotalComDesconto())};// * valor total da OS, com desconto

                        if (os.getDataSaida() != null) {//  * já foi concluida
                            dados[6] = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataSaida());

                        }
                        if (peca != null) {//   * usa alguma peça
                            dados[7] = peca.getCodigoPeca();//  * código da peça usada
                            dados[8] = String.valueOf(os.getQuantidadePeca());//  * quantidade de peças  usadas
                            dados[9] = String.valueOf(peca.getValorPeca());//  * valor unitário da peça
                            dados[10] = String.valueOf(os.calcularValorTotalSemDesconto());// * valor total da OS, sem desconto
                            dados[11] = String.valueOf(os.calcularValorTotalComDesconto());// * valor total da OS, com desconto
                        }
                        table.addRow(dados);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void filtraTabelaOrdensDeServico() {
        try {

            DefaultTableModel table = (DefaultTableModel) jTableOSs.getModel();
            table.setRowCount(0);

            ArrayList<OrdemDeServico> listaOSs = null;
            listaOSs = new ManipulaBancoOrdemServico().buscarTodos();

            if (listaOSs != null && !listaOSs.isEmpty()) {//    * caso exista alguma OS para mostrar
                for (OrdemDeServico os : listaOSs) {
                    if (os.getSituacao() == OrdemDeServico.SituacaoOrdemServico.EM_EXECUCAO) {
                        Veiculo v = new ManipulaBancoVeiculo().buscar(os.getIdVeiculo());
                        Servico servico = new ManipulaBancoServicos().buscar(os.getIdServico());
                        Peca peca = new ManipulaBancoPecas().buscar(os.getIdPeca());
                        String dataAbertura = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataEntrada());
                        String dados[] = {String.valueOf(os.getCodigo()),// * código da OS
                            v.getPlaca(),// * placa do veículo
                            String.valueOf(os.getSituacao()),// * status da OS
                            servico.getNomeServico(),// * nome do serviço feito
                            String.valueOf(servico.getValorMaoDeObra()),//  * valor da mão de obra
                            dataAbertura,// * data de abertura da OS
                            "em aberto",//  * data de fechamento da OS
                            "-----",//  * código da peça usada
                            String.valueOf("0"),//  * quantidade de peças  usadas
                            String.valueOf("0"),//  * valor unitário da peça
                            String.valueOf(os.calcularValorTotalSemDesconto()),// * valor total da OS, sem desconto
                            String.valueOf(os.calcularValorTotalComDesconto())};// * valor total da OS, com desconto

                        if (os.getDataSaida() != null) {//  * já foi concluida
                            dados[6] = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataSaida());

                        }
                        if (peca != null) {//   * usa alguma peça
                            dados[7] = peca.getCodigoPeca();//  * código da peça usada
                            dados[8] = String.valueOf(os.getQuantidadePeca());//  * quantidade de peças  usadas
                            dados[9] = String.valueOf(peca.getValorPeca());//  * valor unitário da peça
                            dados[10] = String.valueOf(os.calcularValorTotalSemDesconto());// * valor total da OS, sem desconto
                            dados[11] = String.valueOf(os.calcularValorTotalComDesconto());// * valor total da OS, com desconto
                        }
                        table.addRow(dados);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void filtraTabelaNotasFiscais() {
        try {

            DefaultTableModel table = (DefaultTableModel) jTableOSs.getModel();
            table.setRowCount(0);

            ArrayList<OrdemDeServico> listaOSs = null;
            listaOSs = new ManipulaBancoOrdemServico().buscarTodos();

            if (listaOSs != null && !listaOSs.isEmpty()) {//    * caso exista alguma OS para mostrar
                for (OrdemDeServico os : listaOSs) {
                    if (os.getSituacao() == OrdemDeServico.SituacaoOrdemServico.CONCLUIDA) {
                        Veiculo v = new ManipulaBancoVeiculo().buscar(os.getIdVeiculo());
                        Servico servico = new ManipulaBancoServicos().buscar(os.getIdServico());
                        Peca peca = new ManipulaBancoPecas().buscar(os.getIdPeca());
                        String dataAbertura = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataEntrada());
                        String dados[] = {String.valueOf(os.getCodigo()),// * código da OS
                            v.getPlaca(),// * placa do veículo
                            String.valueOf(os.getSituacao()),// * status da OS
                            servico.getNomeServico(),// * nome do serviço feito
                            String.valueOf(servico.getValorMaoDeObra()),//  * valor da mão de obra
                            dataAbertura,// * data de abertura da OS
                            "em aberto",//  * data de fechamento da OS
                            "-----",//  * código da peça usada
                            String.valueOf("0"),//  * quantidade de peças  usadas
                            String.valueOf("0"),//  * valor unitário da peça
                            String.valueOf(os.calcularValorTotalSemDesconto()),// * valor total da OS, sem desconto
                            String.valueOf(os.calcularValorTotalComDesconto())};// * valor total da OS, com desconto

                        if (os.getDataSaida() != null) {//  * já foi concluida
                            dados[6] = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataSaida());

                        }
                        if (peca != null) {//   * usa alguma peça
                            dados[7] = peca.getCodigoPeca();//  * código da peça usada
                            dados[8] = String.valueOf(os.getQuantidadePeca());//  * quantidade de peças  usadas
                            dados[9] = String.valueOf(peca.getValorPeca());//  * valor unitário da peça
                            dados[10] = String.valueOf(os.calcularValorTotalSemDesconto());// * valor total da OS, sem desconto
                            dados[11] = String.valueOf(os.calcularValorTotalComDesconto());// * valor total da OS, com desconto
                        }
                        table.addRow(dados);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void filtraTabelaPorCodigo(String filtro) {
        try {

            DefaultTableModel table = (DefaultTableModel) jTableOSs.getModel();
            table.setRowCount(0);

            ArrayList<OrdemDeServico> listaOSs = null;
            listaOSs = new ManipulaBancoOrdemServico().buscarTodos();

            if (listaOSs != null && !listaOSs.isEmpty()) {//    * caso exista alguma OS para mostrar
                for (OrdemDeServico os : listaOSs) {
                    if (("" + os.getCodigo()).contains(filtro)) {// * caso contenha o que está procurando
                        Veiculo v = new ManipulaBancoVeiculo().buscar(os.getIdVeiculo());
                        Servico servico = new ManipulaBancoServicos().buscar(os.getIdServico());
                        Peca peca = new ManipulaBancoPecas().buscar(os.getIdPeca());
                        String dataAbertura = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataEntrada());
                        String dados[] = {String.valueOf(os.getCodigo()),// * código da OS
                            v.getPlaca(),// * placa do veículo
                            String.valueOf(os.getSituacao()),// * status da OS
                            servico.getNomeServico(),// * nome do serviço feito
                            String.valueOf(servico.getValorMaoDeObra()),//  * valor da mão de obra
                            dataAbertura,// * data de abertura da OS
                            "em aberto",//  * data de fechamento da OS
                            "-----",//  * código da peça usada
                            String.valueOf("0"),//  * quantidade de peças  usadas
                            String.valueOf("0"),//  * valor unitário da peça
                            String.valueOf(os.calcularValorTotalSemDesconto()),// * valor total da OS, sem desconto
                            String.valueOf(os.calcularValorTotalComDesconto())};// * valor total da OS, com desconto

                        if (os.getDataSaida() != null) {//  * já foi concluida
                            dados[6] = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataSaida());

                        }
                        if (peca != null) {//   * usa alguma peça
                            dados[7] = peca.getCodigoPeca();//  * código da peça usada
                            dados[8] = String.valueOf(os.getQuantidadePeca());//  * quantidade de peças  usadas
                            dados[9] = String.valueOf(peca.getValorPeca());//  * valor unitário da peça
                            dados[10] = String.valueOf(os.calcularValorTotalSemDesconto());// * valor total da OS, sem desconto
                            dados[11] = String.valueOf(os.calcularValorTotalComDesconto());// * valor total da OS, com desconto
                        }
                        table.addRow(dados);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void filtraTabelaPorPlaca(String filtro) {
        try {

            DefaultTableModel table = (DefaultTableModel) jTableOSs.getModel();
            table.setRowCount(0);

            ArrayList<OrdemDeServico> listaOSs = null;
            listaOSs = new ManipulaBancoOrdemServico().buscarTodos();

            if (listaOSs != null && !listaOSs.isEmpty()) {//    * caso exista alguma OS para mostrar
                for (OrdemDeServico os : listaOSs) {
                    Veiculo v = new ManipulaBancoVeiculo().buscar(os.getIdVeiculo());
                    if (v.getPlaca().toUpperCase().contains(filtro.toUpperCase())) {// * caso contenha o que está procurando, ignorando maiusculo e minusculo
                        Servico servico = new ManipulaBancoServicos().buscar(os.getIdServico());
                        Peca peca = new ManipulaBancoPecas().buscar(os.getIdPeca());
                        String dataAbertura = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataEntrada());
                        String dados[] = {String.valueOf(os.getCodigo()),// * código da OS
                            v.getPlaca(),// * placa do veículo
                            String.valueOf(os.getSituacao()),// * status da OS
                            servico.getNomeServico(),// * nome do serviço feito
                            String.valueOf(servico.getValorMaoDeObra()),//  * valor da mão de obra
                            dataAbertura,// * data de abertura da OS
                            "em aberto",//  * data de fechamento da OS
                            "-----",//  * código da peça usada
                            String.valueOf("0"),//  * quantidade de peças  usadas
                            String.valueOf("0"),//  * valor unitário da peça
                            String.valueOf(os.calcularValorTotalSemDesconto()),// * valor total da OS, sem desconto
                            String.valueOf(os.calcularValorTotalComDesconto())};// * valor total da OS, com desconto

                        if (os.getDataSaida() != null) {//  * já foi concluida
                            dados[6] = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataSaida());

                        }
                        if (peca != null) {//   * usa alguma peça
                            dados[7] = peca.getCodigoPeca();//  * código da peça usada
                            dados[8] = String.valueOf(os.getQuantidadePeca());//  * quantidade de peças  usadas
                            dados[9] = String.valueOf(peca.getValorPeca());//  * valor unitário da peça
                            dados[10] = String.valueOf(os.calcularValorTotalSemDesconto());// * valor total da OS, sem desconto
                            dados[11] = String.valueOf(os.calcularValorTotalComDesconto());// * valor total da OS, com desconto
                        }
                        table.addRow(dados);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void filtraTabelaPorServico(String filtro) {
        try {

            DefaultTableModel table = (DefaultTableModel) jTableOSs.getModel();
            table.setRowCount(0);

            ArrayList<OrdemDeServico> listaOSs = null;
            listaOSs = new ManipulaBancoOrdemServico().buscarTodos();

            if (listaOSs != null && !listaOSs.isEmpty()) {//    * caso exista alguma OS para mostrar
                for (OrdemDeServico os : listaOSs) {
                    Servico servico = new ManipulaBancoServicos().buscar(os.getIdServico());
                    if (servico.getNomeServico().toUpperCase().contains(filtro.toUpperCase())) {// * caso contenha o que está procurando, ignorando maiusculo e minusculo
                        Veiculo v = new ManipulaBancoVeiculo().buscar(os.getIdVeiculo());
                        Peca peca = new ManipulaBancoPecas().buscar(os.getIdPeca());
                        String dataAbertura = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataEntrada());
                        String dados[] = {String.valueOf(os.getCodigo()),// * código da OS
                            v.getPlaca(),// * placa do veículo
                            String.valueOf(os.getSituacao()),// * status da OS
                            servico.getNomeServico(),// * nome do serviço feito
                            String.valueOf(servico.getValorMaoDeObra()),//  * valor da mão de obra
                            dataAbertura,// * data de abertura da OS
                            "em aberto",//  * data de fechamento da OS
                            "-----",//  * código da peça usada
                            String.valueOf("0"),//  * quantidade de peças  usadas
                            String.valueOf("0"),//  * valor unitário da peça
                            String.valueOf(os.calcularValorTotalSemDesconto()),// * valor total da OS, sem desconto
                            String.valueOf(os.calcularValorTotalComDesconto())};// * valor total da OS, com desconto

                        if (os.getDataSaida() != null) {//  * já foi concluida
                            dados[6] = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataSaida());

                        }
                        if (peca != null) {//   * usa alguma peça
                            dados[7] = peca.getCodigoPeca();//  * código da peça usada
                            dados[8] = String.valueOf(os.getQuantidadePeca());//  * quantidade de peças  usadas
                            dados[9] = String.valueOf(peca.getValorPeca());//  * valor unitário da peça
                            dados[10] = String.valueOf(os.calcularValorTotalSemDesconto());// * valor total da OS, sem desconto
                            dados[11] = String.valueOf(os.calcularValorTotalComDesconto());// * valor total da OS, com desconto
                        }
                        table.addRow(dados);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void filtraTabelaPorDataAbertura(String filtro) {
        try {

            DefaultTableModel table = (DefaultTableModel) jTableOSs.getModel();
            table.setRowCount(0);

            ArrayList<OrdemDeServico> listaOSs = null;
            listaOSs = new ManipulaBancoOrdemServico().buscarTodos();

            if (listaOSs != null && !listaOSs.isEmpty()) {//    * caso exista alguma OS para mostrar
                for (OrdemDeServico os : listaOSs) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    if (sdf.format(os.getDataEntrada()).contains(filtro)) {// * caso contenha o que está procurando
                        Veiculo v = new ManipulaBancoVeiculo().buscar(os.getIdVeiculo());
                        Servico servico = new ManipulaBancoServicos().buscar(os.getIdServico());
                        Peca peca = new ManipulaBancoPecas().buscar(os.getIdPeca());
                        String dataAbertura = sdf.format(os.getDataEntrada());
                        String dados[] = {String.valueOf(os.getCodigo()),// * código da OS
                            v.getPlaca(),// * placa do veículo
                            String.valueOf(os.getSituacao()),// * status da OS
                            servico.getNomeServico(),// * nome do serviço feito
                            String.valueOf(servico.getValorMaoDeObra()),//  * valor da mão de obra
                            dataAbertura,// * data de abertura da OS
                            "em aberto",//  * data de fechamento da OS
                            "-----",//  * código da peça usada
                            String.valueOf("0"),//  * quantidade de peças  usadas
                            String.valueOf("0"),//  * valor unitário da peça
                            String.valueOf(os.calcularValorTotalSemDesconto()),// * valor total da OS, sem desconto
                            String.valueOf(os.calcularValorTotalComDesconto())};// * valor total da OS, com desconto

                        if (os.getDataSaida() != null) {//  * já foi concluida
                            dados[6] = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataSaida());

                        }
                        if (peca != null) {//   * usa alguma peça
                            dados[7] = peca.getCodigoPeca();//  * código da peça usada
                            dados[8] = String.valueOf(os.getQuantidadePeca());//  * quantidade de peças  usadas
                            dados[9] = String.valueOf(peca.getValorPeca());//  * valor unitário da peça
                            dados[10] = String.valueOf(os.calcularValorTotalSemDesconto());// * valor total da OS, sem desconto
                            dados[11] = String.valueOf(os.calcularValorTotalComDesconto());// * valor total da OS, com desconto
                        }
                        table.addRow(dados);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void filtraTabelaPorDataFinalizacao(String filtro) {
        try {

            DefaultTableModel table = (DefaultTableModel) jTableOSs.getModel();
            table.setRowCount(0);

            ArrayList<OrdemDeServico> listaOSs = null;
            listaOSs = new ManipulaBancoOrdemServico().buscarTodos();

            if (listaOSs != null && !listaOSs.isEmpty()) {//    * caso exista alguma OS para mostrar
                for (OrdemDeServico os : listaOSs) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    if (os.getDataSaida() == null) {
                        continue;// * não buscar caso não tenha data
                    }
                    if (sdf.format(os.getDataSaida()).contains(filtro)) {// * caso contenha o que está procurando
                        Veiculo v = new ManipulaBancoVeiculo().buscar(os.getIdVeiculo());
                        Servico servico = new ManipulaBancoServicos().buscar(os.getIdServico());
                        Peca peca = new ManipulaBancoPecas().buscar(os.getIdPeca());
                        String dataAbertura = sdf.format(os.getDataEntrada());
                        String dados[] = {String.valueOf(os.getCodigo()),// * código da OS
                            v.getPlaca(),// * placa do veículo
                            String.valueOf(os.getSituacao()),// * status da OS
                            servico.getNomeServico(),// * nome do serviço feito
                            String.valueOf(servico.getValorMaoDeObra()),//  * valor da mão de obra
                            dataAbertura,// * data de abertura da OS
                            "em aberto",//  * data de fechamento da OS
                            "-----",//  * código da peça usada
                            String.valueOf("0"),//  * quantidade de peças  usadas
                            String.valueOf("0"),//  * valor unitário da peça
                            String.valueOf(os.calcularValorTotalSemDesconto()),// * valor total da OS, sem desconto
                            String.valueOf(os.calcularValorTotalComDesconto())};// * valor total da OS, com desconto

                        if (os.getDataSaida() != null) {//  * já foi concluida
                            dados[6] = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataSaida());

                        }
                        if (peca != null) {//   * usa alguma peça
                            dados[7] = peca.getCodigoPeca();//  * código da peça usada
                            dados[8] = String.valueOf(os.getQuantidadePeca());//  * quantidade de peças  usadas
                            dados[9] = String.valueOf(peca.getValorPeca());//  * valor unitário da peça
                            dados[10] = String.valueOf(os.calcularValorTotalSemDesconto());// * valor total da OS, sem desconto
                            dados[11] = String.valueOf(os.calcularValorTotalComDesconto());// * valor total da OS, com desconto
                        }
                        table.addRow(dados);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldBusca = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxTipoFiltro = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableOSs = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jRadioButtonOrcamentos = new javax.swing.JRadioButton();
        jRadioButtonOSs = new javax.swing.JRadioButton();
        jRadioButtonNotasFiscais = new javax.swing.JRadioButton();
        jRadioButtonOrcamentosCancelados = new javax.swing.JRadioButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextFieldBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscaKeyReleased(evt);
            }
        });

        jLabel1.setText("Filtrar:");

        jComboBoxTipoFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Placa", "Servico", "Data de criacao", "Data de finalizacao" }));

        jTableOSs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Placa do veículo", "Status", "Serviço feito", "mão de obra", "Data de abertura", "Data de fechamento", "Peça usada", "Quantidade usada", "Valor unitário", "Valor sem desconto", "Valor com desconto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableOSs);

        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonOrcamentos);
        jRadioButtonOrcamentos.setText("orçamentos");
        jRadioButtonOrcamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonOrcamentosActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonOSs);
        jRadioButtonOSs.setText("ordens de serviço");
        jRadioButtonOSs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonOSsActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonNotasFiscais);
        jRadioButtonNotasFiscais.setText("notas fiscais");
        jRadioButtonNotasFiscais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNotasFiscaisActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonOrcamentosCancelados);
        jRadioButtonOrcamentosCancelados.setText("canceladas");
        jRadioButtonOrcamentosCancelados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonOrcamentosCanceladosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButtonOrcamentosCancelados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonOrcamentos)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonOSs)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonNotasFiscais)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jComboBoxTipoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1314, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jRadioButtonOrcamentos)
                    .addComponent(jRadioButtonOSs)
                    .addComponent(jRadioButtonNotasFiscais)
                    .addComponent(jRadioButtonOrcamentosCancelados)
                    .addComponent(jTextFieldBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxTipoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (jTableOSs.getSelectedRow() < 0) {//    * não escolheu na tabela
                throw new Exception("selecione na tabela qual OS deseja pesquisar");
            }
            int idOSSelecionada = new ManipulaBancoOrdemServico().buscar("" + jTableOSs.getValueAt(jTableOSs.getSelectedRow(), 0));
            if (idOSSelecionada == 0) {
                throw new Exception("Falha ao carregar o id do objeto selecionado na tabela");
            }

            TelaBuscaOS tela = new TelaBuscaOS(idOSSelecionada);
            tela.setVisible(true);
            this.telaAnterior.add(tela);

            this.telaAnterior.remove(this);
            this.telaAnterior.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButtonOrcamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonOrcamentosActionPerformed
        filtraTabelaOrcamentos();
    }//GEN-LAST:event_jRadioButtonOrcamentosActionPerformed

    private void jRadioButtonOrcamentosCanceladosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonOrcamentosCanceladosActionPerformed
        filtraTabelaOrcamentosCancelados();
    }//GEN-LAST:event_jRadioButtonOrcamentosCanceladosActionPerformed

    private void jRadioButtonOSsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonOSsActionPerformed
        filtraTabelaOrdensDeServico();
    }//GEN-LAST:event_jRadioButtonOSsActionPerformed

    private void jRadioButtonNotasFiscaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNotasFiscaisActionPerformed
        filtraTabelaNotasFiscais();
    }//GEN-LAST:event_jRadioButtonNotasFiscaisActionPerformed

    private void jTextFieldBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscaKeyReleased

        switch (jComboBoxTipoFiltro.getSelectedIndex()) {
            case 0:
                filtraTabelaPorCodigo(jTextFieldBusca.getText());
                break;
            case 1:
                filtraTabelaPorPlaca(jTextFieldBusca.getText());
                break;
            case 2:
                filtraTabelaPorServico(jTextFieldBusca.getText());
                break;
            case 3:
                filtraTabelaPorDataAbertura(jTextFieldBusca.getText());
                break;
            case 4:
                filtraTabelaPorDataFinalizacao(jTextFieldBusca.getText());
                break;
        }
    }//GEN-LAST:event_jTextFieldBuscaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxTipoFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonNotasFiscais;
    private javax.swing.JRadioButton jRadioButtonOSs;
    private javax.swing.JRadioButton jRadioButtonOrcamentos;
    private javax.swing.JRadioButton jRadioButtonOrcamentosCancelados;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableOSs;
    private javax.swing.JTextField jTextFieldBusca;
    // End of variables declaration//GEN-END:variables
}
