/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tela;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.OrdemDeServico;
import modelos.Peca;
import modelos.Servico;
import persistencia.ManipulaBancoOrdemServico;
import persistencia.ManipulaBancoPecas;
import persistencia.ManipulaBancoServicos;

/**
 *
 * @author tanak
 */
public class TelaLIstaOSs extends javax.swing.JFrame {

    private final int idVeiculo;

    /**
     * Creates new form TelaLIstaOSs
     *
     * @param idVeiculo
     */
    public TelaLIstaOSs(int idVeiculo) {
        this.idVeiculo = idVeiculo;
        initComponents();
        loadTableOSs();
    }

    private void loadTableOSs() {
        try {
            if (this.idVeiculo == 0) {
                loadTableOSsTodos();
            } else {
                loadTableOSsEspecifica();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void loadTableOSsEspecifica() throws Exception {

        DefaultTableModel table = (DefaultTableModel) jTable1.getModel();
        ArrayList<OrdemDeServico> listaOSs = null;
        if (idVeiculo == 0) {// * mostrar todas as OSs do banco
            loadTableOSsTodos();
        } else {
            listaOSs = new ManipulaBancoOrdemServico().buscarTodos(this.idVeiculo);

            if (!listaOSs.isEmpty()) {//    * caso exista alguma OS para mostrar
                for (OrdemDeServico os : listaOSs) {
                    Servico servico = new ManipulaBancoServicos().buscar(os.getIdServico());
                    Peca peca = new ManipulaBancoPecas().buscar(os.getIdPeca());
                    String dataAbertura = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataEntrada());
                    if (os.getDataSaida() == null) {//  * ainda não foi concluida
                        if (peca == null) {//   * não usa nenhuma peça
                            String dados[] = {String.valueOf(os.getCodigo()),// * código da OS
                                servico.getNomeServico(),// * nome do serviço feito
                                String.valueOf(servico.getValorMaoDeObra()),//  * valor da mão de obra
                                dataAbertura,// * data de abertura da OS
                                "em aberto",//  * data de fechamento da OS
                                "-----",//  * código da peça usada
                                String.valueOf("0"),//  * quantidade de peças  usadas
                                String.valueOf("0"),//  * valor unitário da peça
                                String.valueOf(os.calcularValorTotal())};// * valor total da OS

                            table.addRow(dados);
                        } else {//  * usa peças
                            String dados[] = {String.valueOf(os.getCodigo()),// * código da OS
                                servico.getNomeServico(),// * nome do serviço feito
                                String.valueOf(servico.getValorMaoDeObra()),//  * valor da mão de obra
                                dataAbertura,// * data de abertura da OS
                                "em aberto",//  * data de fechamento da OS
                                peca.getCodigoPeca(),//  * código da peça usada
                                String.valueOf(os.getQuantidadePeca()),//  * quantidade de peças  usadas
                                String.valueOf(peca.getValorPeca()),//  * valor unitário da peça
                                String.valueOf(os.calcularValorTotal())};// * valor total da OS

                            table.addRow(dados);
                        }
                    } else {//    * já foi finalizada
                        String dataSaida = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataSaida());
                        if (peca == null) {//   * não usa nenhuma peça
                            String dados[] = {String.valueOf(os.getCodigo()),// * código da OS
                                servico.getNomeServico(),// * nome do serviço feito
                                String.valueOf(servico.getValorMaoDeObra()),//  * valor da mão de obra
                                dataAbertura,// * data de abertura da OS
                                dataSaida,//  * data de fechamento da OS
                                "-----",//  * código da peça usada
                                String.valueOf("0"),//  * quantidade de peças  usadas
                                String.valueOf("0"),//  * valor unitário da peça
                                String.valueOf(os.calcularValorTotal())};// * valor total da OS

                            table.addRow(dados);
                        } else {//  * usa peças
                            String dados[] = {String.valueOf(os.getCodigo()),// * código da OS
                                servico.getNomeServico(),// * nome do serviço feito
                                String.valueOf(servico.getValorMaoDeObra()),//  * valor da mão de obra
                                dataAbertura,// * data de abertura da OS
                                dataSaida,//  * data de fechamento da OS
                                peca.getCodigoPeca(),//  * código da peça usada
                                String.valueOf(os.getQuantidadePeca()),//  * quantidade de peças  usadas
                                String.valueOf(peca.getValorPeca()),//  * valor unitário da peça
                                String.valueOf(os.calcularValorTotal())};// * valor total da OS

                            table.addRow(dados);
                        }
                    }
//  * nome do serviço, valor da mão de obra, data de entrada, data de saída,
//  * código da peça usada, descrição da peça usada, quantidade de peças usada, valor unitário, valor total da nota
//
//                    dados[0] = servico.getNomeServico();
//                    dados[1] = String.format("%.2f", servico.getValorMaoDeObra());
////                    dados[2] = sdf.format(os.getDataEntrada());
//                    if (os.getDataSaida() != null) {//  * caso a ordem já tenha sido cumprida
//                        dados[3] = sdf.format(os.getDataSaida());
//
//                    } else {//  * caso a ordem não tenha sido cumprida
//                        dados[3] = "Em aberto";
//
//                    }
//                    if (peca != null) {//   * caso alguma peça tenha sido utilizada
//                        dados[4] = peca.getCodigoPeca();
//                        dados[5] = peca.getDescricao();
//                        dados[6] = "" + os.getQuantidadePeca();
//                        dados[7] = String.format("%.2f", peca.getValorPeca());
//                        dados[8] = String.format("%.2f", servico.getValorMaoDeObra() + (os.getQuantidadePeca() * peca.getValorPeca()));
//
//                    } else {//   * caso neenhuma peça tenha sido utilizada
//                        dados[4] = "0";
//                        dados[5] = "0";
//                        dados[6] = "0";
//                        dados[7] = "0";
//                        dados[8] = String.format("%.2f", servico.getValorMaoDeObra());
//
//                    }
//                    table.addRow(dados);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "nenhuma ordem de serviço encontrada!");
            }

        }

    }

    private void loadTableOSsTodos() throws Exception {
        DefaultTableModel table = (DefaultTableModel) jTable1.getModel();

        ArrayList<OrdemDeServico> listaOSs = new ManipulaBancoOrdemServico().buscarTodos();

        if (!listaOSs.isEmpty()) {//    * caso exista alguma OS para mostrar
            for (OrdemDeServico os : listaOSs) {
                Servico servico = new ManipulaBancoServicos().buscar(os.getIdServico());
                Peca peca = new ManipulaBancoPecas().buscar(os.getIdPeca());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dados[] = new String[9];
//  * nome do serviço, valor da mão de obra, data de entrada, data de saída,
//  * código da peça usada, descrição da peça usada, quantidade de peças usada, valor unitário, valor total da nota

                dados[0] = servico.getNomeServico();
                dados[1] = String.format("%.2f", servico.getValorMaoDeObra());
                dados[2] = sdf.format(os.getDataEntrada());
                if (os.getDataSaida() != null) {//  * caso a ordem já tenha sido cumprida
                    dados[3] = sdf.format(os.getDataSaida());

                } else {//  * caso a ordem não tenha sido cumprida
                    dados[3] = "Em aberto";

                }
                if (peca != null) {//   * caso alguma peça tenha sido utilizada
                    dados[4] = peca.getCodigoPeca();
                    dados[5] = peca.getDescricao();
                    dados[6] = "" + os.getQuantidadePeca();
                    dados[7] = String.format("%.2f", peca.getValorPeca());
                    dados[8] = String.format("%.2f", servico.getValorMaoDeObra() + (os.getQuantidadePeca() * peca.getValorPeca()));

                } else {//   * caso neenhuma peça tenha sido utilizada
                    dados[4] = "0";
                    dados[5] = "0";
                    dados[6] = "0";
                    dados[7] = "0";
                    dados[8] = String.format("%.2f", servico.getValorMaoDeObra());

                }
                table.addRow(dados);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "nenhuma ordem de serviço encontrada!");
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Serviço feito", "mão de obra", "Data de abertura", "Data de fechamento", "Peça usada", "Quantidade usada", "Valor unitário", "Valor Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLIstaOSs.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLIstaOSs.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLIstaOSs.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLIstaOSs.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLIstaOSs(0).setVisible(true);
            }

            public void run(int id) {
                new TelaLIstaOSs(id).setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
