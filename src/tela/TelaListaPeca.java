/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Peca;
import persistencia.ManipulaBancoPecas;

/**
 *
 * @author witorsather
 */
public class TelaListaPeca extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaListaPeca
     */
    public TelaListaPeca() {
        initComponents();
        jFormattedTextFieldQuantidadeReservadas.setEditable(false);
        jFormattedTextFieldQuantidadeReservadas.setText("0");
        jFormattedTextFieldQuantidadeEstoqueMinimo.setText("0");
        jFormattedTextFieldQuantidadeEstoque.setText("0");
        LoadTableListaPecas();
    }

    private void LoadTableListaPecas() {
        try {
            DefaultTableModel table = (DefaultTableModel) jTableListaPecas.getModel();
            table.setRowCount(0);
            ArrayList<Peca> listaPecas = new ManipulaBancoPecas().buscarTodos();
            for (Peca p : listaPecas) {
                String[] dados = {String.valueOf(p.getCodigoPeca()),
                    String.valueOf(p.getDescricao()),
                    String.format("%.2f", p.getValorPeca()),
                    String.valueOf(p.getQuantidadeNoEstoque()),
                    String.valueOf(p.getQuantidadeReservadas()),
                    String.valueOf(p.getQuantidadeNoEstoque() - p.getQuantidadeReservadas()),
                    String.valueOf(p.getEstoquequantidadeMinima())};
                table.addRow(dados);
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

        jFormattedTextFieldValorUnitario = new javax.swing.JFormattedTextField();
        jFormattedTextFieldQuantidadeEstoque = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListaPecas = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextFieldQuantidadeEstoqueMinimo = new javax.swing.JFormattedTextField();
        jButtonIncluir = new javax.swing.JButton();
        jFormattedTextFieldQuantidadeReservadas = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jButtonEditar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButtonExcluir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldCodigo = new javax.swing.JTextField();
        jTextFieldDescricao = new javax.swing.JTextField();
        jButtonVoltar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setTitle("Peças");

        jFormattedTextFieldValorUnitario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));

        jFormattedTextFieldQuantidadeEstoque.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        jLabel6.setText("QUANTIDADE RESERVADAS:");

        jTableListaPecas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Valor unitário", "quantidade total no estoque", "quantidade reservadas", "Quantidade disponível", "Estoque mínimo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableListaPecas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableListaPecasMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableListaPecas);

        jLabel7.setText("QUANTIDADE ESTOQUE MINIMO:");

        jFormattedTextFieldQuantidadeEstoqueMinimo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextFieldQuantidadeEstoqueMinimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldQuantidadeEstoqueMinimoActionPerformed(evt);
            }
        });

        jButtonIncluir.setText("INCLUIR");
        jButtonIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncluirActionPerformed(evt);
            }
        });

        jFormattedTextFieldQuantidadeReservadas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextFieldQuantidadeReservadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldQuantidadeReservadasActionPerformed(evt);
            }
        });

        jLabel3.setText("CÓDIGO");

        jButtonEditar.setText("EDITAR");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        jLabel4.setText("VALOR UNITÁRIO:");

        jButtonExcluir.setText("EXCLUIR");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jLabel2.setText("DESCRIÇÃO:");

        jButtonVoltar.setText("VOLTAR");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        jLabel5.setText("QUANTIDADE NO ESTOQUE:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addGap(31, 31, 31)
                                .addComponent(jTextFieldDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextFieldValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(98, 98, 98)
                                .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(124, 124, 124)
                                .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jFormattedTextFieldQuantidadeEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addGap(16, 16, 16)
                                .addComponent(jFormattedTextFieldQuantidadeReservadas, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextFieldQuantidadeEstoqueMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jFormattedTextFieldValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jFormattedTextFieldQuantidadeEstoqueMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jFormattedTextFieldQuantidadeEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldQuantidadeReservadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonIncluir, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addComponent(jButtonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(74, 74, 74))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableListaPecasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaPecasMouseReleased
        try {
            int indexItemSelecionado = jTableListaPecas.getSelectedRow();// * peganod index do item que foi selecionado na tabela
            if (indexItemSelecionado >= 0) {//    * clique válido
                String codigoPecaSelecionada = String.valueOf(jTableListaPecas.getValueAt(indexItemSelecionado, 0));//  * pegando o código da peça, que será usado na consulta ao banco
                int idPeca = new ManipulaBancoPecas().buscar(codigoPecaSelecionada);//  * pegando o id da peça selecionada
                Peca p = new ManipulaBancoPecas().buscar(idPeca);// * pegando peça selecionada
                //  * pegou os dados da peça, agora preencher os campos
                jTextFieldCodigo.setText(p.getCodigoPeca());
                jTextFieldDescricao.setText(p.getDescricao());
                jFormattedTextFieldValorUnitario.setText(String.format("%.2f", p.getValorPeca()));//    * formatando antes de mostrar na tela
                jFormattedTextFieldQuantidadeEstoque.setText("" + p.getQuantidadeNoEstoque());
                jFormattedTextFieldQuantidadeReservadas.setText("" + p.getQuantidadeReservadas());
                jFormattedTextFieldQuantidadeEstoqueMinimo.setText("" + p.getEstoquequantidadeMinima());
            } else {//    * clique inválido
                //  * pass
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jTableListaPecasMouseReleased

    private void jFormattedTextFieldQuantidadeEstoqueMinimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldQuantidadeEstoqueMinimoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldQuantidadeEstoqueMinimoActionPerformed

    private void jButtonIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirActionPerformed
        try {
            String codigoPeca = jTextFieldCodigo.getText();
            String descricaoPeca = jTextFieldDescricao.getText();
            float valorUnitario = Float.parseFloat(jFormattedTextFieldValorUnitario.getText().replace(".", "").replace(",", "."));
            Peca p = new Peca(codigoPeca, descricaoPeca, valorUnitario);

            int quantidadeNoEstoque = Integer.parseInt(jFormattedTextFieldQuantidadeEstoque.getText());
            int quantidadeReservadas = Integer.parseInt(jFormattedTextFieldQuantidadeReservadas.getText());
            int quantidadeMinimaNoEstoque = Integer.parseInt(jFormattedTextFieldQuantidadeEstoqueMinimo.getText());
            p.setEstoquequantidadeMinima(quantidadeMinimaNoEstoque);
            p.setQuantidadeNoEstoque(quantidadeNoEstoque);
            p.setQuantidadeReservadas(quantidadeReservadas);

            new ManipulaBancoPecas().incluir(p);//  * incluindo no banco de dados

            LoadTableListaPecas();
            //jLabelCriadoComSucesso.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonIncluirActionPerformed

    private void jFormattedTextFieldQuantidadeReservadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldQuantidadeReservadasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldQuantidadeReservadasActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        try {
            int indexItemSelecionado = jTableListaPecas.getSelectedRow();
            if (indexItemSelecionado >= 0) {//    * clique válido
                String codigoPecaSelecionada = String.valueOf(jTableListaPecas.getValueAt(indexItemSelecionado, 0));
                int idPeca = new ManipulaBancoPecas().buscar(codigoPecaSelecionada);
                Peca p = new ManipulaBancoPecas().buscar(idPeca);

                String codigo = jTextFieldCodigo.getText();
                String descricao = jTextFieldDescricao.getText();
                String valor = jFormattedTextFieldValorUnitario.getText().replace(".", "").replace(",", ".");
                float valorUnitario = Float.parseFloat(jFormattedTextFieldValorUnitario.getText().replace(".", "").replace(",", "."));

                int quantidadeNoEstoque = Integer.parseInt(jFormattedTextFieldQuantidadeEstoque.getText());
                int quantidadeReservadas = Integer.parseInt(jFormattedTextFieldQuantidadeReservadas.getText());
                int quantidadeMinimaNoEstoque = Integer.parseInt(jFormattedTextFieldQuantidadeEstoqueMinimo.getText());
                Peca novaPeca = new Peca(codigo, descricao, valorUnitario, quantidadeNoEstoque, quantidadeMinimaNoEstoque);
                novaPeca.setQuantidadeReservadas(quantidadeReservadas);
                //  * pegou todos os dados, agora editar
                new ManipulaBancoPecas().editar(p, novaPeca);// * removendo p e adicionando novaPeca
                JOptionPane.showMessageDialog(rootPane, "Editado com sucesso");
                LoadTableListaPecas();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Selecione, na tabela, o que deseja editar");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        try {
            int indexItemSelecionado = jTableListaPecas.getSelectedRow();
            if (indexItemSelecionado >= 0) {//    * clique válido
                String codigoPecaSelecionada = String.valueOf(jTableListaPecas.getValueAt(indexItemSelecionado, 0));
                int idPeca = new ManipulaBancoPecas().buscar(codigoPecaSelecionada);
                Peca p = new ManipulaBancoPecas().buscar(idPeca);

                new ManipulaBancoPecas().remover(p);
                LoadTableListaPecas();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Selecione, na tabela, o que deseja editar");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        new TelaInicial().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonIncluir;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JFormattedTextField jFormattedTextFieldQuantidadeEstoque;
    private javax.swing.JFormattedTextField jFormattedTextFieldQuantidadeEstoqueMinimo;
    private javax.swing.JFormattedTextField jFormattedTextFieldQuantidadeReservadas;
    private javax.swing.JFormattedTextField jFormattedTextFieldValorUnitario;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListaPecas;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldDescricao;
    // End of variables declaration//GEN-END:variables
}
