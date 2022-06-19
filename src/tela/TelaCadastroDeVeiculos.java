/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import enumerations.TipoCliente;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldModelo = new javax.swing.JTextField();
        jTextFieldMarca = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldChassi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldRenavam = new javax.swing.JTextField();
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
        jButtonCadastrarCliente = new javax.swing.JButton();
        jComboBoxModelos = new javax.swing.JComboBox<>();
        jComboBoxMarcas = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButtonCadastrarVeiculo = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldQuilometragem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jRadioButtonPessoaJuridica = new javax.swing.JRadioButton();
        jRadioButtonPessoaFisica = new javax.swing.JRadioButton();

        setTitle("Veículos");
        setToolTipText("");

        jLabel1.setText("CADASTRO DE VEÍCULO");

        jLabel4.setText("CHASSI");

        jLabel5.setText("RENAVAM");

        jLabel6.setText("TIPO DO VEÍCULO");

        jLabel7.setText("PLACA");

        jLabel8.setText("ANO DE FABRICAÇÃO");

        jLabel9.setText("ANO DO MODELO");

        jLabel10.setText("DONO DO VEÍCULO");

        jButtonCadastrarCliente.setText("CADASTRAR CLIENTE");
        jButtonCadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarClienteActionPerformed(evt);
            }
        });

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Cadastrar nova marca");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonCadastrarVeiculo.setText("Cadastrar novo veiculo");
        jButtonCadastrarVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarVeiculoActionPerformed(evt);
            }
        });

        jButton2.setText("Cadastrar novo modelo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButtonVoltar.setText("VOLTAR");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        jLabel11.setText("QUILOMETRAGEM");

        jLabel2.setText("TIPO DE CLIENTE");

        jRadioButtonPessoaJuridica.setText("Pessoa jurídica");

        jRadioButtonPessoaFisica.setText("Pessoa física");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1177, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(256, 256, 256)
                            .addComponent(jLabel1)
                            .addGap(239, 239, 239)
                            .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(jComboBoxMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(220, 220, 220)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(jComboBoxModelos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(220, 220, 220)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(60, 60, 60)
                            .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(jLabel4)
                            .addGap(8, 8, 8)
                            .addComponent(jTextFieldChassi, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(jLabel5)
                            .addGap(10, 10, 10)
                            .addComponent(jTextFieldRenavam, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(jLabel6)
                            .addGap(14, 14, 14)
                            .addComponent(jTextFieldTipoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(jLabel7)
                            .addGap(8, 8, 8)
                            .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(jLabel8)
                            .addGap(14, 14, 14)
                            .addComponent(jTextFieldAnoDeFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(jLabel9)
                            .addGap(23, 23, 23)
                            .addComponent(jTextFieldAnoDoModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(jLabel11)
                            .addGap(32, 32, 32)
                            .addComponent(jTextFieldQuilometragem, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(jLabel10)
                            .addGap(15, 15, 15)
                            .addComponent(jTextFieldDonoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(jLabel2)
                            .addGap(29, 29, 29)
                            .addComponent(jRadioButtonPessoaFisica)
                            .addGap(20, 20, 20)
                            .addComponent(jRadioButtonPessoaJuridica))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(jButtonCadastrarCliente)
                            .addGap(108, 108, 108)
                            .addComponent(jButtonCadastrarVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(648, 648, 648)
                            .addComponent(jButtonVoltar)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 649, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(1, 1, 1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jComboBoxMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addGap(5, 5, 5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jComboBoxModelos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton2)
                        .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(14, 14, 14)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel4))
                        .addComponent(jTextFieldChassi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(15, 15, 15)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel5))
                        .addComponent(jTextFieldRenavam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(15, 15, 15)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel6))
                        .addComponent(jTextFieldTipoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(15, 15, 15)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel7))
                        .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(15, 15, 15)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel8))
                        .addComponent(jTextFieldAnoDeFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(15, 15, 15)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel9))
                        .addComponent(jTextFieldAnoDoModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(33, 33, 33)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel11)
                        .addComponent(jTextFieldQuilometragem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(11, 11, 11)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10)
                        .addComponent(jTextFieldDonoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jRadioButtonPessoaFisica)
                        .addComponent(jRadioButtonPessoaJuridica))
                    .addGap(7, 7, 7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonCadastrarCliente)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jButtonCadastrarVeiculo)))
                    .addGap(15, 15, 15)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(jButtonVoltar)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarClienteActionPerformed
        new TelaCadastroCliente().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonCadastrarClienteActionPerformed

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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jTextFieldDonoDoVeiculo.setText(String.valueOf(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 1)));
        if (jTable1.getValueAt(jTable1.getSelectedRow(), 0).equals(String.valueOf(TipoCliente.PESSOA_FISICA))) {
            jRadioButtonPessoaFisica.setSelected(true);
        } else if (jTable1.getValueAt(jTable1.getSelectedRow(), 0).equals(String.valueOf(TipoCliente.PESSOA_JURIDICA))) {
            jRadioButtonPessoaJuridica.setSelected(true);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        jTextFieldDonoDoVeiculo.setText(String.valueOf(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 1)));
        if (jTable1.getValueAt(jTable1.getSelectedRow(), 0).equals(String.valueOf(TipoCliente.PESSOA_FISICA))) {
            jRadioButtonPessoaFisica.setSelected(true);
        } else if (jTable1.getValueAt(jTable1.getSelectedRow(), 0).equals(String.valueOf(TipoCliente.PESSOA_JURIDICA))) {
            jRadioButtonPessoaJuridica.setSelected(true);
        }
    }//GEN-LAST:event_jTable1MouseReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new TelaCadastroDeMarca().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
                String cpf = String.valueOf(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 2));
                idDonoVeiculo = mb.buscar(cpf);
                tipoCliente = TipoCliente.PESSOA_FISICA;
            } else if (jRadioButtonPessoaJuridica.isSelected()) {
                ManipulaBancoPessoaJuridica mb = new ManipulaBancoPessoaJuridica();
                String cnpj = String.valueOf(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 2));
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new TelaCadastroModelo().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        new TelaInicial().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

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

            jTable1.setModel(new DefaultTableModel(dados, new Object[]{"Tipo de cliente", "Nome/Razão social", "CPF/CNPJ", "Telefone", "Email"}));

            DefaultTableModel table = (DefaultTableModel) jTable1.getModel();

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonCadastrarCliente;
    private javax.swing.JButton jButtonCadastrarVeiculo;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JComboBox<String> jComboBoxMarcas;
    private javax.swing.JComboBox<String> jComboBoxModelos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButtonPessoaFisica;
    private javax.swing.JRadioButton jRadioButtonPessoaJuridica;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldAnoDeFabricacao;
    private javax.swing.JTextField jTextFieldAnoDoModelo;
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