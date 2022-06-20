/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tela;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Funcionario;
import modelos.OrdemDeServico;
import modelos.Peca;
import modelos.Pessoa;
import modelos.PessoaFisica;
import modelos.PessoaJuridica;
import modelos.Servico;
import modelos.Veiculo;
import modelos.auxiliares.MarcaVeiculo;
import modelos.auxiliares.ModeloVeiculo;
import persistencia.IManipulaBanco;
import persistencia.ManipulaBancoFuncionario;
import persistencia.ManipulaBancoMarca;
import persistencia.ManipulaBancoModelos;
import persistencia.ManipulaBancoOrdemServico;
import persistencia.ManipulaBancoPecas;
import persistencia.ManipulaBancoPessoaFisica;
import persistencia.ManipulaBancoPessoaJuridica;
import persistencia.ManipulaBancoServicos;
import persistencia.ManipulaBancoVeiculo;

/**
 *
 * @author tanak
 */
public class TelaCadastroOS extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadastroOS
     */
    public TelaCadastroOS() {
        initComponents();
        loadTableVeiculos("");
        loadTableServicos("");
        loadComboBoxFuncionarios();
        jTablePecas.setVisible(false);
        jFormattedTextFieldQuantidadePecas.setVisible(false);
        jLabelQuantidadePecas.setVisible(false);
    }

    private void loadTableVeiculos(String busca) {
        try {
            ArrayList<Veiculo> listaVeiculos = new ManipulaBancoVeiculo().buscarTodos();
            DefaultTableModel table = (DefaultTableModel) jTableVeiculos.getModel();
            table.setRowCount(0);// * apagando linhas para não duplicar os dados da tabela

            ManipulaBancoPessoaFisica mbPf = new ManipulaBancoPessoaFisica();
            ManipulaBancoPessoaJuridica mbPj = new ManipulaBancoPessoaJuridica();
            ManipulaBancoMarca mbMarca = new ManipulaBancoMarca();
            ManipulaBancoModelos mbModelo = new ManipulaBancoModelos();
            for (Veiculo v : listaVeiculos) {
                if (v.getPlaca().contains(busca)) {//   * filtrando a busca
                    MarcaVeiculo marca = mbMarca.buscar(v.getIdMarca());
                    ModeloVeiculo modelo = mbModelo.buscar(v.getIdModelo());
                    PessoaFisica pf = mbPf.buscar(v.getIdDonoVeiculo());
                    if (marca != null && modelo != null) {//    * conferindo se a marca e o modelo existem
                        if (pf != null) {//  * vendo se é uma pessoa física
                            table.addRow(new Object[]{pf.getNome(), v.getPlaca(), marca.getNomeMarca(),
                                modelo.getNomeModelo()});
                        } else {
                            PessoaJuridica pj = mbPj.buscar(v.getIdDonoVeiculo());
                            if (pj != null) {//  * caso todos os dados estejam corretos
                                table.addRow(new Object[]{pj.getNomeFantasia(), v.getPlaca(), marca.getNomeMarca(),
                                    modelo.getNomeModelo()});
                            } else {
// falha, o dono do veiculo não consta no banco de dados
                            }
                        }
                    } else {
// falha, o modelo ou a marca não consta no banco de dados
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private void loadTableServicos(String busca) {
        try {
            DefaultTableModel table = (DefaultTableModel) jTableServicos.getModel();
            table.setRowCount(0);//apagando linhas antigas para não duplicar a tabela

            ArrayList<Servico> listaServicos = new ManipulaBancoServicos().buscarTodos();// * buscando todos os serviços registrados
            for (Servico s : listaServicos) {
                if (s.getNomeServico().toUpperCase().contains(busca.toUpperCase())) {// * ignorando maiusculo e minusculo
                    table.addRow(new Object[]{s.getNomeServico(), String.format("%.2f", s.getValorMaoDeObra())});//    * adicionando linha com os dados do serviço
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private void loadComboBoxFuncionarios() {
        try {
            ArrayList<Funcionario> listaFunc = new ManipulaBancoFuncionario().buscarTodos();
            ArrayList<String> listaNomesFuncionarios = new ArrayList();
            for (Funcionario f : listaFunc) {
                listaNomesFuncionarios.add(f.getNome());
            }

            jComboBox1.setModel(new DefaultComboBoxModel(listaNomesFuncionarios.toArray()));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

    private void loadTablePecas() {
        try {
            DefaultTableModel table = (DefaultTableModel) jTablePecas.getModel();
            table.setRowCount(0);// * apagando linhas para não duplicar dados na tabela
            ArrayList<Peca> listaPecas = new ManipulaBancoPecas().buscarTodos();
            for (Peca p : listaPecas) {
                int quantidadePecasDisponiveis = p.getQuantidadeNoEstoque() - p.getQuantidadeReservadas();
                table.addRow(new Object[]{p.getCodigoPeca(), p.getDescricao(), p.getValorPeca(), quantidadePecasDisponiveis});
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

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableVeiculos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDefeitoRelatado = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableServicos = new javax.swing.JTable();
        jTextFieldBuscaServicos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextFieldDataEntrada = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTablePecas = new javax.swing.JTable();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabelQuantidadePecas = new javax.swing.JLabel();
        jFormattedTextFieldQuantidadePecas = new javax.swing.JFormattedTextField();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextFieldPorcentagemDesconto = new javax.swing.JFormattedTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1377, 758));

        jTableVeiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome do Dono", "Placa", "Marca", "Modelo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableVeiculos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTableVeiculosKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jTableVeiculos);

        jLabel1.setText("Defeito relatado: ");

        jTextAreaDefeitoRelatado.setColumns(20);
        jTextAreaDefeitoRelatado.setRows(5);
        jScrollPane1.setViewportView(jTextAreaDefeitoRelatado);

        jLabel2.setText("Serviço:");

        jTableServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Servico", "Valor mão de obra"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableServicosMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableServicos);

        jTextFieldBuscaServicos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBuscaServicosKeyTyped(evt);
            }
        });

        jLabel3.setText("Data: ");

        jFormattedTextFieldDataEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));

        jLabel4.setText("Funcionario responsável: ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Criar nova Ordem de serviço");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        jTablePecas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "preço unitário", "Quantidade disponível"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTablePecas);

        jCheckBox1.setText("vai usar peças");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabelQuantidadePecas.setText("quantidade de pecas usadas: ");

        jFormattedTextFieldQuantidadePecas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        jButton3.setText("Adicionar novo serviço");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setText("Porcentagem de desconto: ");

        jFormattedTextFieldPorcentagemDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jFormattedTextFieldDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextFieldPorcentagemDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(264, 264, 264))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jCheckBox1)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabelQuantidadePecas)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jFormattedTextFieldQuantidadePecas, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextFieldBuscaServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3)))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(29, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jFormattedTextFieldDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jFormattedTextFieldPorcentagemDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addComponent(jButton1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBuscaServicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton3))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jLabelQuantidadePecas)
                    .addComponent(jFormattedTextFieldQuantidadePecas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldBuscaServicosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscaServicosKeyTyped
        loadTableServicos(jTextFieldBuscaServicos.getText());
    }//GEN-LAST:event_jTextFieldBuscaServicosKeyTyped

    private void jTableServicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableServicosMouseClicked
        jTextFieldBuscaServicos.setText(String.valueOf(jTableServicos.getValueAt(jTableServicos.getSelectedRow(), 0)));
    }//GEN-LAST:event_jTableServicosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (jFormattedTextFieldQuantidadePecas.getText().equals("")) {
                throw new Exception("informe a quantidade de peças que serão usadas");
            }
            String defeitoRelatado = jTextAreaDefeitoRelatado.getText();
            int idServico = new ManipulaBancoServicos().buscar(String.valueOf(jTableServicos.getValueAt(jTableServicos.getSelectedRow(), 0)));
            Date dataEntrada = new SimpleDateFormat("dd/MM/yyyy").parse(jFormattedTextFieldDataEntrada.getText());
            int idFuncResponsavel = new ManipulaBancoFuncionario().buscar(String.valueOf(jComboBox1.getSelectedItem()));
            int idVeiculo = new ManipulaBancoVeiculo().buscar(String.valueOf(jTableVeiculos.getValueAt(jTableVeiculos.getSelectedRow(), 1)));
            double valorMaoDeObra = new ManipulaBancoServicos().buscar(idServico).getValorMaoDeObra();
            double porcentagemDesconto = Double.parseDouble(jFormattedTextFieldPorcentagemDesconto.getText().replace(".", "").replace(",", "."));
//    * lendo o valor da mao de obra do serviço no banco de dados de serviços

            if (!jCheckBox1.isSelected()) {
//caso não vá usar peças
                int codigoOS = geradorId.GeradorId.getID(OrdemDeServico.getArquivoCodigo());
                new ManipulaBancoOrdemServico().incluir(new OrdemDeServico(codigoOS, defeitoRelatado, idServico, valorMaoDeObra,
                        idFuncResponsavel, idVeiculo, porcentagemDesconto));
            } else {
                int codigoOS = geradorId.GeradorId.getID(OrdemDeServico.getArquivoCodigo());
                int idPeca = new ManipulaBancoPecas().buscar(String.valueOf(jTablePecas.getValueAt(jTablePecas.getSelectedRow(), 0)));
                int quantidadePecasUsadas = Integer.parseInt(jFormattedTextFieldQuantidadePecas.getText());
                double valorUnitarioPeca = new ManipulaBancoPecas().buscar(idPeca).getValorPeca();//    * lendo o valor unitário da peça no banco de dados de peças
                new ManipulaBancoOrdemServico().incluir(new OrdemDeServico(codigoOS, defeitoRelatado, idServico, valorMaoDeObra,
                        idFuncResponsavel, idPeca, quantidadePecasUsadas,
                        valorUnitarioPeca, idVeiculo, porcentagemDesconto));
            }
            JOptionPane.showMessageDialog(rootPane, "Ordem de serviço cadastrada");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        new TelaInicial().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            jTablePecas.setVisible(true);
            jLabelQuantidadePecas.setVisible(true);
            jFormattedTextFieldQuantidadePecas.setVisible(true);
            loadTablePecas();
        } else {
            jTablePecas.setVisible(false);
            jFormattedTextFieldQuantidadePecas.setVisible(false);
            jLabelQuantidadePecas.setVisible(false);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTableVeiculosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableVeiculosKeyTyped
        loadTableVeiculos(String.valueOf(evt.getKeyChar()));
    }//GEN-LAST:event_jTableVeiculosKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new TelaCadastroServicos().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadastroOS.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroOS.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroOS.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroOS.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroOS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataEntrada;
    private javax.swing.JFormattedTextField jFormattedTextFieldPorcentagemDesconto;
    private javax.swing.JFormattedTextField jFormattedTextFieldQuantidadePecas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelQuantidadePecas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTablePecas;
    private javax.swing.JTable jTableServicos;
    private javax.swing.JTable jTableVeiculos;
    private javax.swing.JTextArea jTextAreaDefeitoRelatado;
    private javax.swing.JTextField jTextFieldBuscaServicos;
    // End of variables declaration//GEN-END:variables
}
