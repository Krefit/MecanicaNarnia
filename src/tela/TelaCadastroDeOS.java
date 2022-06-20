/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author witorsather
 */
public class TelaCadastroDeOS extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaCadastroDeOS
     */
    public TelaCadastroDeOS() {
        initComponents();
        initComponents();
        loadTableVeiculos("");
        loadTableServicos("");
        loadComboBoxFuncionarios();
        jComboBox1ActionPerformed(null);
        jTablePecas.setVisible(false);
        jFormattedTextFieldQuantidadePecas.setVisible(false);
        jFormattedTextFieldQuantidadePecas.setText("1");
        jFormattedTextFieldDataEntrada.setEnabled(false);
        jFormattedTextFieldDataEntrada.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        jLabelQuantidadePecas.setVisible(false);
        jFormattedTextFieldPorcentagemDesconto.setText("0,00");
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

        jLabelQuantidadePecas = new javax.swing.JLabel();
        jTextFieldBuscaServicos = new javax.swing.JTextField();
        jFormattedTextFieldQuantidadePecas = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextFieldDataEntrada = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableVeiculos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextFieldPorcentagemDesconto = new javax.swing.JFormattedTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTablePecas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableServicos = new javax.swing.JTable();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextFieldDefeitoRelatado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldPlaca = new javax.swing.JTextField();
        jLabelEspecialidadeFuncionario = new javax.swing.JLabel();

        setTitle("Cadastro de OS");

        jLabelQuantidadePecas.setText("quantidade de produtos usados: ");

        jTextFieldBuscaServicos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBuscaServicosKeyTyped(evt);
            }
        });

        jFormattedTextFieldQuantidadePecas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        jLabel3.setText("Data: ");

        jFormattedTextFieldDataEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));

        jLabel5.setText("Porcentagem de desconto: ");

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

        jLabel4.setText("Funcionario responsável: ");

        jFormattedTextFieldPorcentagemDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Defeito relatado: ");

        jButton1.setText("Criar nova Ordem de serviço");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTablePecas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Produto", "preço unitário", "Quantidade disponível"
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

        jCheckBox1.setText("vai usar algum produto");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Placa: ");

        jTextFieldPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPlacaKeyReleased(evt);
            }
        });

        jLabelEspecialidadeFuncionario.setText("jLabel7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextFieldDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(2, 2, 2)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(264, 264, 264))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBox1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelQuantidadePecas)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextFieldQuantidadePecas, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelEspecialidadeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldDefeitoRelatado, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextFieldPorcentagemDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldBuscaServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDefeitoRelatado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldBuscaServicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox1)
                            .addComponent(jLabelQuantidadePecas)
                            .addComponent(jFormattedTextFieldQuantidadePecas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jFormattedTextFieldDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jFormattedTextFieldPorcentagemDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)
                        .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelEspecialidadeFuncionario))
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldBuscaServicosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscaServicosKeyTyped
        loadTableServicos(jTextFieldBuscaServicos.getText());
    }//GEN-LAST:event_jTextFieldBuscaServicosKeyTyped

    private void jTableVeiculosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableVeiculosKeyTyped
        loadTableVeiculos(String.valueOf(evt.getKeyChar()));
    }//GEN-LAST:event_jTableVeiculosKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (jFormattedTextFieldQuantidadePecas.getText().equals("")) {
                throw new Exception("informe a quantidade de peças que serão usadas");
            }
            String defeitoRelatado = jTextFieldDefeitoRelatado.getText();
            int idServico = new ManipulaBancoServicos().buscar(String.valueOf(jTableServicos.getValueAt(jTableServicos.getSelectedRow(), 0)));
            int idFuncResponsavel = new ManipulaBancoFuncionario().buscar(String.valueOf(jComboBox1.getSelectedItem()), true);
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
                        idFuncResponsavel, idPeca, quantidadePecasUsadas, valorUnitarioPeca,
                        idVeiculo, porcentagemDesconto));
            }
            JOptionPane.showMessageDialog(rootPane, "Ordem de serviço cadastrada");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTableServicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableServicosMouseClicked
        jTextFieldBuscaServicos.setText(String.valueOf(jTableServicos.getValueAt(jTableServicos.getSelectedRow(), 0)));
    }//GEN-LAST:event_jTableServicosMouseClicked

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

    private void jTextFieldPlacaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPlacaKeyReleased
        loadTableVeiculos(jTextFieldPlaca.getText());
    }//GEN-LAST:event_jTextFieldPlacaKeyReleased

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        try {
            int idFuncionario = new ManipulaBancoFuncionario().buscar("" + jComboBox1.getSelectedItem(), true);
            Funcionario f = new ManipulaBancoFuncionario().buscar(idFuncionario);
            jLabelEspecialidadeFuncionario.setText(f.getEspecialidade());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelEspecialidadeFuncionario;
    private javax.swing.JLabel jLabelQuantidadePecas;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTablePecas;
    private javax.swing.JTable jTableServicos;
    private javax.swing.JTable jTableVeiculos;
    private javax.swing.JTextField jTextFieldBuscaServicos;
    private javax.swing.JTextField jTextFieldDefeitoRelatado;
    private javax.swing.JTextField jTextFieldPlaca;
    // End of variables declaration//GEN-END:variables
}
