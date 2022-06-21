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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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
            
            TableRowSorter<TableModel> sorterPecas = new TableRowSorter<TableModel>(jTablePecas.getModel());
            jTablePecas.setRowSorter(sorterPecas);
            
            TableRowSorter<TableModel> sorterServicos = new TableRowSorter<TableModel>(jTableServicos.getModel());
            jTableServicos.setRowSorter(sorterServicos);
            
            TableRowSorter<TableModel> sorterVeiculo = new TableRowSorter<TableModel>(jTableVeiculos.getModel());
            jTableVeiculos.setRowSorter(sorterVeiculo);

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

        jPanel1 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldDefeitoRelatado = new javax.swing.JTextField();
        jFormattedTextFieldPorcentagemDesconto = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextFieldPlaca = new javax.swing.JTextField();
        jLabelQuantidadePecas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelEspecialidadeFuncionario = new javax.swing.JLabel();
        jTextFieldBuscaServicos = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jFormattedTextFieldQuantidadePecas = new javax.swing.JFormattedTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTablePecas = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextFieldDataEntrada = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableServicos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableVeiculos = new javax.swing.JTable();

        setTitle("Cadastro de OS");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jCheckBox1.setText("vai usar algum produto");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Funcionario responsável: ");

        jFormattedTextFieldPorcentagemDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));

        jLabel6.setText("Placa: ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextFieldPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPlacaKeyReleased(evt);
            }
        });

        jLabelQuantidadePecas.setText("quantidade de produtos usados: ");

        jLabel1.setText("Defeito relatado: ");

        jLabelEspecialidadeFuncionario.setText("especialidade");

        jTextFieldBuscaServicos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBuscaServicosKeyTyped(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 204, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-mais-2-matemática-verde-30.png"))); // NOI18N
        jButton1.setText("Criar Nova Ordem de Serviço");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jFormattedTextFieldQuantidadePecas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

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

        jLabel3.setText("Data: ");

        jFormattedTextFieldDataEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));

        jLabel2.setText("Serviço:");

        jLabel5.setText("Porcentagem de desconto: ");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextFieldDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel4)
                        .addGap(2, 2, 2)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelEspecialidadeFuncionario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(10, 10, 10)
                        .addComponent(jFormattedTextFieldPorcentagemDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(252, 252, 252))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldBuscaServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelQuantidadePecas, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextFieldQuantidadePecas, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldDefeitoRelatado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDefeitoRelatado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldBuscaServicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jFormattedTextFieldQuantidadePecas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jCheckBox1)
                                    .addComponent(jLabelQuantidadePecas))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jFormattedTextFieldDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jFormattedTextFieldPorcentagemDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEspecialidadeFuncionario)
                    .addComponent(jLabel6))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JPanel jPanel1;
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
