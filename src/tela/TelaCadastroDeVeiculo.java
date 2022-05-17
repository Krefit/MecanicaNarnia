package tela;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import modelos.Veiculo;
import persistencia.ManipulaBancoVeiculo;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Pessoa;
import modelos.PessoaFisica;
import modelos.auxiliares.MarcaVeiculo;
import modelos.auxiliares.ModeloVeiculo;
import persistencia.IManipulaBanco;
import persistencia.ManipulaBancoFuncionario;
import persistencia.ManipulaBancoMarca;
import persistencia.ManipulaBancoModelos;
import persistencia.ManipulaBancoPessoaFisica;

public class TelaCadastroDeVeiculo extends javax.swing.JFrame {
    
    public TelaCadastroDeVeiculo() {
        initComponents();
        setLocationRelativeTo(null);
//        loadComboBoxModelos();
        jComboBoxModelos.setVisible(false);
        loadComboBoxMarcas();
        loadTableClientes();
        jTextFieldDonoDoVeiculo.setEnabled(false);
        jTextFieldMarca.setEditable(false);
        jTextFieldModelo.setEditable(false);
        jButtonCadastrarVeiculo.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldModelo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("CADASTRO DE VEÍCULO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 6, -1, -1));

        jLabel2.setText("MODELO");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 43, -1, -1));
        getContentPane().add(jTextFieldModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 40, 115, -1));

        jLabel3.setText("MARCA");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 74, -1, -1));
        getContentPane().add(jTextFieldMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 74, 108, -1));

        jLabel4.setText("CHASSI");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 111, -1, -1));
        getContentPane().add(jTextFieldChassi, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 108, 91, -1));

        jLabel5.setText("RENAVAM");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 145, -1, -1));
        getContentPane().add(jTextFieldRenavam, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 142, 75, -1));

        jLabel6.setText("TIPO DO VEÍCULO");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 179, -1, -1));
        getContentPane().add(jTextFieldTipoDoVeiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 176, 102, -1));

        jLabel7.setText("PLACA");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 213, -1, -1));
        getContentPane().add(jTextFieldPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 210, 99, -1));

        jLabel8.setText("ANO DE FABRICAÇÃO");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 247, -1, -1));
        getContentPane().add(jTextFieldAnoDeFabricacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 244, 88, -1));

        jLabel9.setText("ANO DO MODELO");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 281, -1, -1));
        getContentPane().add(jTextFieldAnoDoModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 278, 73, -1));

        jLabel10.setText("DONO DO VEÍCULO");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 321, -1, -1));
        getContentPane().add(jTextFieldDonoDoVeiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 318, 210, -1));

        jButtonCadastrarCliente.setText("CADASTRAR CLIENTE");
        jButtonCadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarClienteActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCadastrarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 377, -1, -1));

        jComboBoxModelos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxModelos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxModelosActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxModelos, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 120, -1));

        jComboBoxMarcas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMarcasActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxMarcas, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 120, -1));

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
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 440, 720, 220));

        jButton1.setText("Cadastrar nova marca");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 190, -1));

        jButtonCadastrarVeiculo.setText("Cadastrar novo veiculo");
        jButtonCadastrarVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarVeiculoActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCadastrarVeiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 260, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            
        }
    }
    
    private void loadTableClientes() {
        try {
            ArrayList<PessoaFisica> listaPessoas = new ManipulaBancoPessoaFisica().buscarTodos();
            String[][] dados = new String[listaPessoas.size()][4];
            for (int i = 0; i < listaPessoas.size(); i++) {
                dados[i][0] = listaPessoas.get(i).getNome();
                dados[i][1] = listaPessoas.get(i).getCpf();
                dados[i][2] = listaPessoas.get(i).getTelefone()[0];
                dados[i][3] = listaPessoas.get(i).getEmail();
            }
            
            jTable1.setModel(new DefaultTableModel(dados, new Object[]{"Nome", "CPF", "Telefone", "Email"}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void jButtonCadastrarVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarVeiculoActionPerformed
        try {
            String cpf = String.valueOf(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 1));
            
            ManipulaBancoPessoaFisica mb = new ManipulaBancoPessoaFisica();
            int idDonoVeiculo = mb.buscar(cpf);
            
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
            
            Veiculo veiculoNovo = new Veiculo(idModelo, idMarca, chassi, renavam, tipoDoVeiculo, placa, anoDeFabricacao, anoDoModelo, idDonoVeiculo);
            
            new ManipulaBancoVeiculo().incluir(veiculoNovo);
            JOptionPane.showMessageDialog(null, "VEÍCULO CADASTRADO!");
        } catch (Exception erro) {
            System.err.println(erro.getMessage());
            erro.printStackTrace();
            JOptionPane.showMessageDialog(this, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonCadastrarVeiculoActionPerformed

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

    private void jComboBoxModelosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxModelosActionPerformed
        if (jComboBoxModelos.getSelectedIndex() > 0) {
            jTextFieldModelo.setText(String.valueOf(jComboBoxModelos.getSelectedItem()));
        } else {
            jTextFieldModelo.setText("");
        }
    }//GEN-LAST:event_jComboBoxModelosActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jTextFieldDonoDoVeiculo.setText(String.valueOf(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0)));
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        
        JOptionPane.showMessageDialog(this, "Ordenação da tabela não está pronto pra ser usado ainda!");

    }//GEN-LAST:event_jTable1KeyTyped

    private void jButtonCadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarClienteActionPerformed
        new TelaCadastroCliente().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonCadastrarClienteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new TelaCadastroMarca().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadastroDeVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroDeVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroDeVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroDeVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroDeVeiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonCadastrarCliente;
    private javax.swing.JButton jButtonCadastrarVeiculo;
    private javax.swing.JComboBox<String> jComboBoxMarcas;
    private javax.swing.JComboBox<String> jComboBoxModelos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldAnoDeFabricacao;
    private javax.swing.JTextField jTextFieldAnoDoModelo;
    private javax.swing.JTextField jTextFieldChassi;
    private javax.swing.JTextField jTextFieldDonoDoVeiculo;
    private javax.swing.JTextField jTextFieldMarca;
    private javax.swing.JTextField jTextFieldModelo;
    private javax.swing.JTextField jTextFieldPlaca;
    private javax.swing.JTextField jTextFieldRenavam;
    private javax.swing.JTextField jTextFieldTipoDoVeiculo;
    // End of variables declaration//GEN-END:variables
}
