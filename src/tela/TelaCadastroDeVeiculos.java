/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import enumerations.TipoCliente;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.PessoaFisica;
import modelos.PessoaJuridica;
import modelos.Veiculo;
import modelos.auxiliares.MarcaVeiculo;
import modelos.auxiliares.ModeloVeiculo;
import persistencia.ManipulaBancoMarca;
import persistencia.ManipulaBancoModelos;
import persistencia.ManipulaBancoPessoaFisica;
import persistencia.ManipulaBancoPessoaJuridica;
import persistencia.ManipulaBancoVeiculo;

/**
 *
 * @author witorsather
 */
public class TelaCadastroDeVeiculos extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaCadastroDeVeiculos
     */
    public TelaCadastroDeVeiculos() {
        initComponents();
        loadComboBoxModelos();
        jComboBoxModelos.setVisible(false);
        loadComboBoxMarcas();
        loadTableClientes();
        jTextFieldMarca.setVisible(false);
        jTextFieldModelo.setVisible(false);
        jTextFieldDonoDoVeiculo.setEditable(false);
        jTextFieldMarca.setEditable(false);
        jTextFieldModelo.setEditable(false);
        jButtonCadastrarVeiculo.setVisible(true);

        jRadioButtonPessoaFisica.setEnabled(false);
        jRadioButtonPessoaJuridica.setEnabled(false);

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTabelaClientes.getModel());
        jTabelaClientes.setRowSorter(sorter);
        this.requestFocus();
    }

    private void filtraTabelaNome(String nome) {
        try {
            DefaultTableModel table = (DefaultTableModel) jTabelaClientes.getModel();
            table.setRowCount(0);// * apagando dados da tabela para não duplicar as linhas
            ArrayList<PessoaFisica> listaPessoas = new ManipulaBancoPessoaFisica().buscarTodos();
            if (listaPessoas != null) {
                for (PessoaFisica p : listaPessoas) {
                    if (p.getNome().toUpperCase().contains(nome.toUpperCase())) {
                        String[] dados = new String[jTabelaClientes.getColumnCount()];
                        dados[0] = "" + TipoCliente.PESSOA_FISICA;
                        dados[1] = p.getNome();
                        dados[2] = p.getCpf();
                        dados[3] = p.getTelefone()[0];
                        dados[4] = p.getEmail();
                        table.addRow(dados);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void filtraTabelaRazaoSocial(String razaoSocial) {
        try {
            DefaultTableModel table = (DefaultTableModel) jTabelaClientes.getModel();
            table.setRowCount(0);// * apagando dados da tabela para não duplicar as linhas
            ArrayList<PessoaJuridica> listaPessoasJuridicas = new ManipulaBancoPessoaJuridica().buscarTodos();
            if (listaPessoasJuridicas == null) {
                return;
            }
            for (PessoaJuridica p : listaPessoasJuridicas) {
                if (p.getRazaoSocial().toUpperCase().contains(razaoSocial.toUpperCase())) {

                    String[] dados = new String[jTabelaClientes.getColumnCount()];
                    dados[0] = "" + TipoCliente.PESSOA_JURIDICA;
                    dados[1] = p.getRazaoSocial();
                    dados[2] = p.getCnpj();
                    dados[3] = p.getTelefone()[0];
                    dados[4] = p.getEmail();

                    table.addRow(dados);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelaClientes = new javax.swing.JTable();
        jButtonCadastrarVeiculo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldModelo = new javax.swing.JTextField();
        jTextFieldQuilometragem = new javax.swing.JTextField();
        jTextFieldMarca = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jRadioButtonPessoaJuridica = new javax.swing.JRadioButton();
        jTextFieldChassi = new javax.swing.JTextField();
        jRadioButtonPessoaFisica = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldRenavam = new javax.swing.JTextField();
        jTextFieldBuscaNome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldTipoDoVeiculo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldPlaca = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldAnoDeFabricacao = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldAnoDoModelo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldDonoDoVeiculo = new javax.swing.JTextField();
        jComboBoxModelos = new javax.swing.JComboBox<>();
        jComboBoxMarcas = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldBuscaRazaoSocial = new javax.swing.JTextField();

        setTitle("Veículos");
        setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTabelaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelaClientesMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTabelaClientesMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTabelaClientes);

        jButtonCadastrarVeiculo.setBackground(new java.awt.Color(0, 204, 255));
        jButtonCadastrarVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-mais-2-matemática-verde-30.png"))); // NOI18N
        jButtonCadastrarVeiculo.setText("Cadastrar novo veiculo");
        jButtonCadastrarVeiculo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonCadastrarVeiculo.setBorderPainted(false);
        jButtonCadastrarVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarVeiculoActionPerformed(evt);
            }
        });

        jLabel1.setText("CADASTRO DE VEÍCULO");

        jLabel11.setText("QUILOMETRAGEM");

        jLabel2.setText("TIPO DE CLIENTE");

        jLabel4.setText("CHASSI");

        jRadioButtonPessoaJuridica.setText("Pessoa jurídica");

        jRadioButtonPessoaFisica.setText("Pessoa física");

        jLabel5.setText("RENAVAM");

        jLabel3.setText("Nome:");

        jTextFieldBuscaNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscaNomeKeyReleased(evt);
            }
        });

        jLabel6.setText("TIPO DO VEÍCULO");

        jLabel7.setText("PLACA");

        jLabel8.setText("ANO DE FABRICAÇÃO");

        jLabel9.setText("ANO DO MODELO");

        jLabel10.setText("DONO DO VEÍCULO");

        jComboBoxModelos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxModelos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxModelosActionPerformed(evt);
            }
        });

        jComboBoxMarcas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMarcasActionPerformed(evt);
            }
        });

        jLabel12.setText("Nome/razão social: ");

        jTextFieldBuscaRazaoSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscaRazaoSocialKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButtonPessoaFisica)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButtonPessoaJuridica))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldBuscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldBuscaRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldAnoDeFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxModelos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldMarca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldModelo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(26, 26, 26)
                                    .addComponent(jTextFieldDonoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel4))
                                    .addGap(35, 35, 35)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldChassi)
                                        .addComponent(jTextFieldRenavam)
                                        .addComponent(jTextFieldTipoDoVeiculo)
                                        .addComponent(jTextFieldPlaca)
                                        .addComponent(jTextFieldAnoDoModelo)
                                        .addComponent(jTextFieldQuilometragem, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(418, 418, 418)))
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCadastrarVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(305, 305, 305))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jComboBoxMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxModelos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldChassi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldRenavam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(14, 14, 14)
                        .addComponent(jTextFieldTipoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(3, 3, 3)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextFieldAnoDeFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldAnoDoModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldBuscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(2, 2, 2)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldQuilometragem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextFieldBuscaRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jRadioButtonPessoaFisica)
                            .addComponent(jRadioButtonPessoaJuridica)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jTextFieldDonoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButtonCadastrarVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxModelosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxModelosActionPerformed
        if (jComboBoxModelos.getSelectedIndex() > 0) {
            jTextFieldModelo.setText(String.valueOf(jComboBoxModelos.getSelectedItem()));
            jComboBoxModelos.setVisible(true);
        } else {
            jTextFieldModelo.setText("");
            jComboBoxModelos.setVisible(true);
        }
    }//GEN-LAST:event_jComboBoxModelosActionPerformed

    private void jComboBoxMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMarcasActionPerformed
        if (jComboBoxMarcas.getSelectedIndex() > 0) {
            jTextFieldMarca.setText(String.valueOf(jComboBoxMarcas.getSelectedItem()));
            jComboBoxModelos.setVisible(true);
            loadComboBoxModelos();
        } else {
            jTextFieldMarca.setText("");
            jTextFieldModelo.setText("");
            jComboBoxModelos.setVisible(false);
        }
    }//GEN-LAST:event_jComboBoxMarcasActionPerformed

    private void jTabelaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaClientesMouseClicked
        jTextFieldDonoDoVeiculo.setText(String.valueOf(jTabelaClientes.getModel().getValueAt(jTabelaClientes.getSelectedRow(), 1)));
        if (jTabelaClientes.getValueAt(jTabelaClientes.getSelectedRow(), 0).equals(String.valueOf(TipoCliente.PESSOA_FISICA))) {
            jRadioButtonPessoaFisica.setSelected(true);
        } else if (jTabelaClientes.getValueAt(jTabelaClientes.getSelectedRow(), 0).equals(String.valueOf(TipoCliente.PESSOA_JURIDICA))) {
            jRadioButtonPessoaJuridica.setSelected(true);
        }
    }//GEN-LAST:event_jTabelaClientesMouseClicked

    private void jTabelaClientesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaClientesMouseReleased
        jTextFieldDonoDoVeiculo.setText(String.valueOf(jTabelaClientes.getModel().getValueAt(jTabelaClientes.getSelectedRow(), 1)));
        if (jTabelaClientes.getValueAt(jTabelaClientes.getSelectedRow(), 0).equals(String.valueOf(TipoCliente.PESSOA_FISICA))) {
            jRadioButtonPessoaFisica.setSelected(true);
        } else if (jTabelaClientes.getValueAt(jTabelaClientes.getSelectedRow(), 0).equals(String.valueOf(TipoCliente.PESSOA_JURIDICA))) {
            jRadioButtonPessoaJuridica.setSelected(true);
        }
    }//GEN-LAST:event_jTabelaClientesMouseReleased

    private void jButtonCadastrarVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarVeiculoActionPerformed
        try {
            TipoCliente tipoCliente = null;

            int idDonoVeiculo = 0;
            ManipulaBancoMarca mbd = new ManipulaBancoMarca();
            int idMarca = mbd.buscar(jTextFieldMarca.getText());
            ManipulaBancoModelos mbm = new ManipulaBancoModelos();
            int idModelo = mbm.buscar(jTextFieldModelo.getText());
            String chassi = jTextFieldChassi.getText();
            String renavam = jTextFieldRenavam.getText();
            String tipoDoVeiculo = jTextFieldTipoDoVeiculo.getText();
            String placa = jTextFieldPlaca.getText();
            int anoDeFabricacao = Integer.parseInt(jTextFieldAnoDeFabricacao.getText());
            int anoDoModelo = Integer.parseInt(jTextFieldAnoDoModelo.getText());
            int quilometragem = Integer.parseInt(jTextFieldQuilometragem.getText());

            if (jRadioButtonPessoaFisica.isSelected()) {
                ManipulaBancoPessoaFisica mb = new ManipulaBancoPessoaFisica();
                String cpf = String.valueOf(jTabelaClientes.getModel().getValueAt(jTabelaClientes.getSelectedRow(), 2));
                idDonoVeiculo = mb.buscar(cpf);
                tipoCliente = TipoCliente.PESSOA_FISICA;
            } else if (jRadioButtonPessoaJuridica.isSelected()) {
                ManipulaBancoPessoaJuridica mb = new ManipulaBancoPessoaJuridica();
                String cnpj = String.valueOf(jTabelaClientes.getModel().getValueAt(jTabelaClientes.getSelectedRow(), 2));
                idDonoVeiculo = mb.buscar(cnpj);
                tipoCliente = TipoCliente.PESSOA_JURIDICA;
            }
            if (idDonoVeiculo == 0) {
                throw new Exception("Falha ao carregar dono do veiculo");
            }
            Veiculo veiculoNovo = new Veiculo(idModelo, idMarca, chassi, renavam, tipoDoVeiculo,
                    placa, anoDeFabricacao, anoDoModelo, quilometragem, idDonoVeiculo, tipoCliente);

            new ManipulaBancoVeiculo().incluir(veiculoNovo);
            JOptionPane.showMessageDialog(null, "VEÍCULO CADASTRADO!");
        } catch (Exception erro) {
            System.err.println(erro.getMessage());
            erro.printStackTrace();
            JOptionPane.showMessageDialog(this, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonCadastrarVeiculoActionPerformed

    private void jTextFieldBuscaNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscaNomeKeyReleased
        filtraTabelaNome(jTextFieldBuscaNome.getText());
    }//GEN-LAST:event_jTextFieldBuscaNomeKeyReleased

    private void jTextFieldBuscaRazaoSocialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscaRazaoSocialKeyReleased
        filtraTabelaRazaoSocial(jTextFieldBuscaRazaoSocial.getText());
    }//GEN-LAST:event_jTextFieldBuscaRazaoSocialKeyReleased

    private void loadComboBoxModelos() {
        try {
            ArrayList<ModeloVeiculo> listaModelos = new ManipulaBancoModelos().buscarTodos();
            String[] nomeModelos = new String[listaModelos.size() + 1];
            nomeModelos[0] = "--Modelos--";
            for (int i = 0; i < listaModelos.size(); i++) {
                nomeModelos[i + 1] = listaModelos.get(i).getNomeModelo();
            }
            jComboBoxModelos.setModel(new DefaultComboBoxModel<>(nomeModelos));
        } catch (Exception e) {

        }
    }

    private void loadComboBoxMarcas() {
        try {
            ArrayList<MarcaVeiculo> listaModelos = new ManipulaBancoMarca().buscarTodos();
            String[] nomeModelos = new String[listaModelos.size() + 1];
            nomeModelos[0] = "--Marcas--";
            for (int i = 0; i < listaModelos.size(); i++) {
                nomeModelos[i + 1] = listaModelos.get(i).getNomeMarca();
            }
            jComboBoxMarcas.setModel(new DefaultComboBoxModel<>(nomeModelos));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTableClientes() {
        try {
            ArrayList<PessoaFisica> listaPessoasFisicas = new ManipulaBancoPessoaFisica().buscarTodos();
            String[][] dados = new String[listaPessoasFisicas.size()][5];
            for (int i = 0; i < listaPessoasFisicas.size(); i++) {
                dados[i][0] = "" + TipoCliente.PESSOA_FISICA;
                dados[i][1] = listaPessoasFisicas.get(i).getNome();
                dados[i][2] = listaPessoasFisicas.get(i).getCpf();
                dados[i][3] = listaPessoasFisicas.get(i).getTelefone()[0];
                dados[i][4] = listaPessoasFisicas.get(i).getEmail();
            }

            jTabelaClientes.setModel(new DefaultTableModel(dados, new Object[]{"Tipo de cliente", "Nome/Razão social", "CPF/CNPJ", "Telefone", "Email"}));

            DefaultTableModel table = (DefaultTableModel) jTabelaClientes.getModel();

            ArrayList<PessoaJuridica> listaPessoasJuridicas = new ManipulaBancoPessoaJuridica().buscarTodos();
            String[] dados2 = new String[5];
            for (int i = 0; i < listaPessoasJuridicas.size(); i++) {
                dados2[0] = "" + TipoCliente.PESSOA_JURIDICA;
                dados2[1] = listaPessoasJuridicas.get(i).getRazaoSocial();
                dados2[2] = listaPessoasJuridicas.get(i).getCnpj();
                dados2[3] = listaPessoasJuridicas.get(i).getTelefone()[0];
                dados2[4] = listaPessoasJuridicas.get(i).getEmail();
                table.addRow(dados2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCadastrarVeiculo;
    private javax.swing.JComboBox<String> jComboBoxMarcas;
    private javax.swing.JComboBox<String> jComboBoxModelos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonPessoaFisica;
    private javax.swing.JRadioButton jRadioButtonPessoaJuridica;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabelaClientes;
    private javax.swing.JTextField jTextFieldAnoDeFabricacao;
    private javax.swing.JTextField jTextFieldAnoDoModelo;
    private javax.swing.JTextField jTextFieldBuscaNome;
    private javax.swing.JTextField jTextFieldBuscaRazaoSocial;
    private javax.swing.JTextField jTextFieldChassi;
    private javax.swing.JTextField jTextFieldDonoDoVeiculo;
    private javax.swing.JTextField jTextFieldMarca;
    private javax.swing.JTextField jTextFieldModelo;
    private javax.swing.JTextField jTextFieldPlaca;
    private javax.swing.JTextField jTextFieldQuilometragem;
    private javax.swing.JTextField jTextFieldRenavam;
    private javax.swing.JTextField jTextFieldTipoDoVeiculo;
    // End of variables declaration//GEN-END:variables
}
