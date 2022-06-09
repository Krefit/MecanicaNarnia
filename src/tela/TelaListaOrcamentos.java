/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tela;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Funcionario;
import modelos.OrdemDeServico;
import modelos.Peca;
import modelos.Servico;
import modelos.Veiculo;
import persistencia.ManipulaBancoFuncionario;
import persistencia.ManipulaBancoOrdemServico;
import persistencia.ManipulaBancoPecas;
import persistencia.ManipulaBancoServicos;
import persistencia.ManipulaBancoVeiculo;

/**
 *
 * @author tanak
 */
public class TelaListaOrcamentos extends javax.swing.JFrame {

    /**
     * Creates new form TelaListaOrcamentos
     */
    public TelaListaOrcamentos() {
        initComponents();
        loadTableOrcamentoss();
    }

    private void loadTableOrcamentoss() {
        try {
            ArrayList<OrdemDeServico> listaOrcamentos = new ManipulaBancoOrdemServico().BuscarTodosOrcamentos();
            DefaultTableModel table = (DefaultTableModel) jTableOrcamentos.getModel();
            table.setRowCount(0);
            for (OrdemDeServico os : listaOrcamentos) {
                Veiculo v = new ManipulaBancoVeiculo().buscar(os.getIdVeiculo());
                Servico s = new ManipulaBancoServicos().buscar(os.getIdServico());
                String dataAbertura = new SimpleDateFormat("dd/MM/yyyy").format(os.getDataEntrada());
                Funcionario f = new ManipulaBancoFuncionario().buscar(os.getIdFuncionarioResponsavel());
                Peca p = new ManipulaBancoPecas().buscar(os.getIdPeca());
                if (v != null && s != null && f != null) {//    * conseguiu buscar todos os dados sem erros
                    String[] dados = null;
                    if (p != null) {// * foi usado alguma peça
                        String[] temp = {String.valueOf(os.getCodigo()),
                            v.getPlaca(), s.getNomeServico(), dataAbertura, f.getNome(),
                            String.valueOf(s.getValorMaoDeObra()), p.getDescricao(), String.valueOf(os.getQuantidadePeca()), String.valueOf(p.getValorPeca()),
                            String.valueOf(os.calcularValorTotal())};
                        dados = temp;
                    } else {
                        String[] temp = {String.valueOf(os.getCodigo()),
                            v.getPlaca(), s.getNomeServico(), dataAbertura, f.getNome(),
                            String.valueOf(s.getValorMaoDeObra()), "0", "0", "0",
                            String.valueOf(os.calcularValorTotal())};
                        dados = temp;
                    }

                    table.addRow(dados);
                } else {//  * perdeu algum dado
                    System.out.println("dado inválido da os: " + os.toString());
                    JOptionPane.showMessageDialog(rootPane, "Erro no banco de dados, contacte o suporte tecnico");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private OrdemDeServico getSelecctedItem() throws Exception {
        int indexSelecionado = jTableOrcamentos.getSelectedRow();
        OrdemDeServico OS = null;
        if (indexSelecionado >= 0) {//    * clique válido
            String codigoOS = String.valueOf(jTableOrcamentos.getValueAt(indexSelecionado, 0));//   * pegando  o código da OS
            int idOS = new ManipulaBancoOrdemServico().buscar(codigoOS);//  *  pegando o id do objeto no banco
            OS = new ManipulaBancoOrdemServico().buscar(idOS);//    * pegando os dados do objeto
        } else {
            throw new Exception("Selecione, na tabela, qual Orçamento deseja editar");
        }
        return OS;//    * retornando a OS, ou returnando null caso não tenha encontrado
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupSituacao = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableOrcamentos = new javax.swing.JTable();
        jRadioButtonEsperandoAprovacao = new javax.swing.JRadioButton();
        jRadioButtonAprovado = new javax.swing.JRadioButton();
        jRadioButtonCancelado = new javax.swing.JRadioButton();
        jButtonConfirmar = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableOrcamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Placa do veiculo", "Servico feito", "Data de abertura", "Funcionario responsável", "Valor mão de obra", "Peça usada", "Quantidade de peças usada", "valor unitário das peças", "Valor total "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableOrcamentos);

        buttonGroupSituacao.add(jRadioButtonEsperandoAprovacao);
        jRadioButtonEsperandoAprovacao.setText("Esperando aprovação");

        buttonGroupSituacao.add(jRadioButtonAprovado);
        jRadioButtonAprovado.setText("Aprovado");

        buttonGroupSituacao.add(jRadioButtonCancelado);
        jRadioButtonCancelado.setText("Cancelado");

        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarActionPerformed(evt);
            }
        });

        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButtonCancelado)
                        .addGap(33, 33, 33)
                        .addComponent(jRadioButtonEsperandoAprovacao)
                        .addGap(29, 29, 29)
                        .addComponent(jRadioButtonAprovado)
                        .addGap(250, 250, 250)
                        .addComponent(jButtonConfirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonVoltar)
                        .addGap(59, 59, 59))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonEsperandoAprovacao)
                    .addComponent(jRadioButtonAprovado)
                    .addComponent(jRadioButtonCancelado)
                    .addComponent(jButtonConfirmar)
                    .addComponent(jButtonVoltar))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed
        try {
            OrdemDeServico os = getSelecctedItem();
            int id = new ManipulaBancoOrdemServico().buscar(os);
            if (os != null) {//   * caso tenha conseguido buscar os dados
                if (jRadioButtonCancelado.isSelected()) {
                    os.setSituacao(OrdemDeServico.SituacaoOrdemServico.CANCELADA);
                } else if (jRadioButtonEsperandoAprovacao.isSelected()) {
                    os.setSituacao(OrdemDeServico.SituacaoOrdemServico.EM_ABERTO);
                } else if (jRadioButtonAprovado.isSelected()) {
                    os.setSituacao(OrdemDeServico.SituacaoOrdemServico.EM_EXECUCAO);
                    Peca p = new ManipulaBancoPecas().buscar(os.getIdPeca());// * pegando a peça que será usada no serviço
                    p.retirarDoEstoque(os.getQuantidadePeca());//   * retirando do estoque a quantidade de peças correspondente
                } else {//    * nenhum botão foi selecionado
//  * falha no sistema
                    throw new IllegalStateException("um item foi selecionado na tabela e todos os dados foram pegos corretamente, mas a ordem de serviço não está com nenhum status válido, na OS: " + os);
                }
                new ManipulaBancoOrdemServico().editar(id, os);
            } else {//  * falhou ao buscar os dados do objeto
                System.out.println("OS não encontrada: " + os);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jButtonConfirmarActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        new TelaInicial().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaListaOrcamentos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaListaOrcamentos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaListaOrcamentos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaListaOrcamentos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaListaOrcamentos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupSituacao;
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JRadioButton jRadioButtonAprovado;
    private javax.swing.JRadioButton jRadioButtonCancelado;
    private javax.swing.JRadioButton jRadioButtonEsperandoAprovacao;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableOrcamentos;
    // End of variables declaration//GEN-END:variables
}
