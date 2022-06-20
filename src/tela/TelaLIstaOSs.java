/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tela;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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
        initComponents();
        loadTableOSs();
        this.idVeiculo = idVeiculo;
        jRadioButtonOrcamento.setEnabled(false);
        
        jButtonVoltar.setVisible(false);
        
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTableOSs.getModel());
        jTableOSs.setRowSorter(sorter);
    }
    
    private void loadTableOSs() {
        try {
            
            loadTableOSsEspecifica();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }
    
    private void loadTableOSsEspecifica() throws Exception {
        
        DefaultTableModel table = (DefaultTableModel) jTableOSs.getModel();
        table.setRowCount(0);
        
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
                                String.valueOf(os.calcularValorTotalSemDesconto()),// * valor total da OS, sem desconto
                                String.valueOf(os.calcularValorTotalComDesconto())};// * valor total da OS, com desconto

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
                                String.valueOf(os.calcularValorTotalSemDesconto()),// * valor total da OS, sem desconto
                                String.valueOf(os.calcularValorTotalComDesconto())};// * valor total da OS, com desconto

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
                                String.valueOf(os.calcularValorTotalSemDesconto()),// * valor total da OS, sem desconto
                                String.valueOf(os.calcularValorTotalComDesconto())};// * valor total da OS, com desconto

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
                                String.valueOf(os.calcularValorTotalSemDesconto()),// * valor total da OS, sem desconto
                                String.valueOf(os.calcularValorTotalComDesconto())};// * valor total da OS, com desconto

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
        
        DefaultTableModel table = (DefaultTableModel) jTableOSs.getModel();
        table.setRowCount(0);
        
        ArrayList<OrdemDeServico> listaOSs = new ManipulaBancoOrdemServico().buscarTodos();
        
        if (!listaOSs.isEmpty()) {//    * caso exista alguma OS para mostrar
            for (OrdemDeServico os : listaOSs) {
                Servico servico = new ManipulaBancoServicos().buscar(os.getIdServico());
                Peca peca = new ManipulaBancoPecas().buscar(os.getIdPeca());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dados[] = new String[12];
//  * nome do serviço, valor da mão de obra, data de entrada, data de saída,
//  * código da peça usada, descrição da peça usada, quantidade de peças usada, valor unitário, valor total da nota

                dados[0] = "" + os.getCodigo();
                dados[1] = "" + os.getSituacao();
                dados[2] = servico.getNomeServico();
                dados[3] = String.format("%.2f", servico.getValorMaoDeObra());
                dados[4] = sdf.format(os.getDataEntrada());
                if (os.getDataSaida() != null) {//  * caso a ordem já tenha sido cumprida
                    dados[5] = sdf.format(os.getDataSaida());
                    
                } else {//  * caso a ordem não tenha sido cumprida
                    dados[5] = "Em aberto";
                    
                }
                if (peca != null) {//   * caso alguma peça tenha sido utilizada
                    dados[6] = peca.getCodigoPeca();
                    dados[7] = peca.getDescricao();
                    dados[8] = "" + os.getQuantidadePeca();
                    dados[9] = String.format("%.2f", peca.getValorPeca());
                    dados[10] = String.format("%.2f", servico.getValorMaoDeObra() + (os.getQuantidadePeca() * peca.getValorPeca()));
                    
                } else {//   * caso neenhuma peça tenha sido utilizada
                    dados[6] = "0";
                    dados[7] = "0";
                    dados[8] = "0";
                    dados[9] = "0";
                    dados[10] = String.format("%.2f", servico.getValorMaoDeObra());
                    
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

        buttonGroupStatus = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableOSs = new javax.swing.JTable();
        jButtonVoltar = new javax.swing.JButton();
        jRadioButtonExecutando = new javax.swing.JRadioButton();
        jRadioButtonFinalizada = new javax.swing.JRadioButton();
        jRadioButtonCancelado = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jRadioButtonOrcamento = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTableOSs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Status", "Serviço feito", "mão de obra", "Data de abertura", "Data de fechamento", "Peça usada", "Quantidade usada", "Valor unitário", "Valor sem desconto", "Valor com desconto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableOSs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableOSsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableOSs);

        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        buttonGroupStatus.add(jRadioButtonExecutando);
        jRadioButtonExecutando.setText("Executando");

        buttonGroupStatus.add(jRadioButtonFinalizada);
        jRadioButtonFinalizada.setText("Finalizada");

        buttonGroupStatus.add(jRadioButtonCancelado);
        jRadioButtonCancelado.setText("Cancelado");

        jButton1.setText("Confirmar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroupStatus.add(jRadioButtonOrcamento);
        jRadioButtonOrcamento.setText("Orçamento");

        jButton2.setText("Pesquisar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1077, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButtonOrcamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonCancelado)
                        .addGap(33, 33, 33)
                        .addComponent(jRadioButtonExecutando)
                        .addGap(29, 29, 29)
                        .addComponent(jRadioButtonFinalizada)
                        .addGap(169, 169, 169)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonVoltar))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonVoltar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jRadioButtonExecutando)
                                    .addComponent(jRadioButtonFinalizada)
                                    .addComponent(jRadioButtonCancelado)
                                    .addComponent(jButton1)
                                    .addComponent(jRadioButtonOrcamento)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))
                        .addGap(0, 18, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        new TelaInicial().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            int indexSelecionado = jTableOSs.getSelectedRow();
            if (indexSelecionado < 0) {/// * não clicou na tabela
                JOptionPane.showMessageDialog(rootPane, "Selecione, na tabela, qual orçammento deseja alterar");
            } else {//  * clique válido
                ManipulaBancoOrdemServico mb = new ManipulaBancoOrdemServico();
                int idOS = mb.buscar("" + jTableOSs.getValueAt(indexSelecionado, 0));
                OrdemDeServico os = mb.buscar(idOS);
                if (jRadioButtonExecutando.isSelected()) {
                    os.setSituacao(OrdemDeServico.SituacaoOrdemServico.EM_EXECUCAO);
                } else if (jRadioButtonCancelado.isSelected()) {
                    os.setSituacao(OrdemDeServico.SituacaoOrdemServico.CANCELADA);
                } else {//  * finalizou a OS
                    os.setSituacao(OrdemDeServico.SituacaoOrdemServico.CONCLUIDA);
                }
                mb.editar(mb.getID(os), os);//  * editando o banco de dados
                loadTableOSs();//   * recarregando a tabela
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTableOSsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableOSsMouseClicked
        try {
            int indexSelecionado = jTableOSs.getSelectedRow();
            if (indexSelecionado < 0) {/// * não clicou na tabela
                JOptionPane.showMessageDialog(rootPane, "Selecione, na tabela, qual orçammento deseja alterar");
            } else {//  * clique válido
                ManipulaBancoOrdemServico mb = new ManipulaBancoOrdemServico();
                int idOS = mb.buscar("" + jTableOSs.getValueAt(indexSelecionado, 0));
                OrdemDeServico os = mb.buscar(idOS);
                switch (os.getSituacao()) {
                    case CANCELADA:
                        jRadioButtonCancelado.setSelected(true);
                        break;
                    case CONCLUIDA:
                        jRadioButtonFinalizada.setSelected(true);
                        break;
                    case EM_EXECUCAO:
                        jRadioButtonExecutando.setSelected(true);
                        break;
                    case EM_ABERTO:
                        jRadioButtonOrcamento.setSelected(true);
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jTableOSsMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
    private javax.swing.ButtonGroup buttonGroupStatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JRadioButton jRadioButtonCancelado;
    private javax.swing.JRadioButton jRadioButtonExecutando;
    private javax.swing.JRadioButton jRadioButtonFinalizada;
    private javax.swing.JRadioButton jRadioButtonOrcamento;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableOSs;
    // End of variables declaration//GEN-END:variables
}
