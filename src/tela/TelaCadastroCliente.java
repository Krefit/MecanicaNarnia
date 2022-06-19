/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tela;

import enumerations.EstadosBrazil;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import modelos.PessoaFisica;
import modelos.PessoaJuridica;
import modelos.auxiliares.Endereco;
import persistencia.ManipulaBancoPessoaFisica;
import persistencia.ManipulaBancoPessoaJuridica;

/**
 *
 * @author ALUNO
 */
public class TelaCadastroCliente extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public TelaCadastroCliente() {
        initComponents();
        loadComboBox();
        tField_RazaoSocial.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup_TipoPessoa = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tField_nome = new javax.swing.JTextField();
        jLabel_CPF_CNPJ = new javax.swing.JLabel();
        tField_cpf_cnpj = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tField_telefone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tField_TipoLogradouro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tField_email = new javax.swing.JTextField();
        btn_criarCliente = new javax.swing.JButton();
        jLabel_DtNasc_RazSoc = new javax.swing.JLabel();
        tField_DataNascimento = new javax.swing.JFormattedTextField();
        tField_RazaoSocial = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tField_Logradouro = new javax.swing.JTextField();
        tField_Numero = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tField_Complemento = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tField_Bairro = new javax.swing.JTextField();
        tField_Cidade = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cb_Estado = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        tField_CEP = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tField_telefone1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tField_telefone2 = new javax.swing.JTextField();
        jRadioButton_PessoaFisica = new javax.swing.JRadioButton();
        jRadioButton_PessoaJuridica = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jButtonVoltar = new javax.swing.JButton();
        jLabel_Resultado = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome: ");

        jLabel_CPF_CNPJ.setText("CPF: ");

        jLabel3.setText("Celular:");

        jLabel4.setText("Tipo Logradouro:");

        jLabel5.setText("Email:");

        btn_criarCliente.setText("CADASTRAR");
        btn_criarCliente.setMaximumSize(new java.awt.Dimension(96, 22));
        btn_criarCliente.setMinimumSize(new java.awt.Dimension(96, 22));
        btn_criarCliente.setPreferredSize(new java.awt.Dimension(96, 22));
        btn_criarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_criarClienteActionPerformed(evt);
            }
        });

        jLabel_DtNasc_RazSoc.setText("Data de Nascimento:");

        tField_DataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));

        jLabel7.setText("Logradouro");

        jLabel8.setText("Nº:");

        jLabel9.setText("Complemento:");

        jLabel10.setText("Bairro:");

        jLabel11.setText("Cidade:");

        jLabel12.setText("Estado:");

        jLabel13.setText("CEP:");

        jLabel14.setText("Residencial:");

        jLabel15.setText("Comercial:");

        buttonGroup_TipoPessoa.add(jRadioButton_PessoaFisica);
        jRadioButton_PessoaFisica.setSelected(true);
        jRadioButton_PessoaFisica.setText("Pessoa Fisica");
        jRadioButton_PessoaFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_PessoaFisicaActionPerformed(evt);
            }
        });

        buttonGroup_TipoPessoa.add(jRadioButton_PessoaJuridica);
        jRadioButton_PessoaJuridica.setText("Pessoa Juridica");
        jRadioButton_PessoaJuridica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_PessoaJuridicaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("Cadastro do Cliente");

        jButtonVoltar.setText("VOLTAR");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        jLabel_Resultado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_Resultado.setForeground(new java.awt.Color(51, 102, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jRadioButton_PessoaFisica)
                        .addGap(129, 129, 129)
                        .addComponent(jRadioButton_PessoaJuridica))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel1)
                        .addGap(21, 21, 21)
                        .addComponent(tField_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel_CPF_CNPJ)
                        .addGap(33, 33, 33)
                        .addComponent(tField_cpf_cnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel_DtNasc_RazSoc)
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tField_RazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tField_DataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel3)
                        .addGap(20, 20, 20)
                        .addComponent(tField_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(tField_telefone1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel15)
                        .addGap(3, 3, 3)
                        .addComponent(tField_telefone2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel5)
                        .addGap(28, 28, 28)
                        .addComponent(tField_email, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel11)
                        .addGap(20, 20, 20)
                        .addComponent(tField_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(cb_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(tField_Bairro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel13)
                        .addGap(16, 16, 16)
                        .addComponent(tField_CEP, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(tField_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tField_Logradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4)
                        .addGap(9, 9, 9)
                        .addComponent(tField_TipoLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(tField_Complemento, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel_Resultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_criarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(295, 295, 295)
                                .addComponent(jButtonVoltar)))))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton_PessoaFisica)
                    .addComponent(jRadioButton_PessoaJuridica))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tField_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_CPF_CNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tField_cpf_cnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_DtNasc_RazSoc)
                    .addComponent(tField_RazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tField_DataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tField_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tField_telefone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tField_telefone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(tField_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(tField_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(tField_Bairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(tField_CEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(tField_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tField_Logradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tField_TipoLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(8, 8, 8)
                .addComponent(jLabel9)
                .addGap(4, 4, 4)
                .addComponent(tField_Complemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_criarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVoltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_Resultado)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_criarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_criarClienteActionPerformed
        try {
            String nome = tField_nome.getText();
            /*
            private String complemento;
            private String bairro;
            private String cidade;
            private EstadosBrazil estado;
            private String CEP;*/
            String[] telefone = new String[3];
            telefone[0] = tField_telefone.getText();
            telefone[1] = tField_telefone.getText();
            telefone[2] = tField_telefone.getText();
            String tipoLogradouro = tField_TipoLogradouro.getText();
            String logradoro = tField_Logradouro.getText();
            String numero = tField_Numero.getText();
            String complemento = tField_Complemento.getText();
            String bairro = tField_Bairro.getText();
            String cidade = tField_Cidade.getText();
            String estado = cb_Estado.getSelectedItem().toString();
            String CEP = tField_CEP.getText();
            String email = tField_email.getText();
            EstadosBrazil eb = Enum.valueOf(EstadosBrazil.class, estado);

            Endereco endereco = new Endereco(tipoLogradouro, logradoro, numero, complemento, bairro, cidade, eb, CEP);
            if (jRadioButton_PessoaFisica.isSelected()) {
                String cpf = tField_cpf_cnpj.getText();
                String auxDataNascimento = tField_DataNascimento.getText();
                String[] vetDataNascimento = auxDataNascimento.split("/");
                Date dataNascimento = new Date(Integer.parseInt(vetDataNascimento[2]) - 1900, Integer.parseInt(vetDataNascimento[1]) - 1, Integer.parseInt(vetDataNascimento[0]));

                PessoaFisica clPF = new PessoaFisica(nome, cpf, dataNascimento, email, endereco, telefone[0],telefone[1],telefone[2]);
                new ManipulaBancoPessoaFisica().incluir(clPF);
            }else
                if(jRadioButton_PessoaJuridica.isSelected())
                {
                    String cnpj = tField_cpf_cnpj.getText();
                    String razaoSocial = tField_RazaoSocial.getText();
                    
                    PessoaJuridica clPJ = new PessoaJuridica(cnpj, razaoSocial, nome, email, endereco, telefone[0],telefone[1],telefone[2]);
                    new ManipulaBancoPessoaJuridica().incluir(clPJ);
                    //String razaoSocial = t
            }
            jLabel_Resultado.setText("Cliente cadastrado com sucesso!");
            

            //criando novo cliente com o id correspondente
            //Cliente_PessoaFisica clienteParaAdicionar = new Cliente_PessoaFisica(nome, cpf, endereco, telefone, email);
            //Cliente_PessoaFisica cliPF = new Cliente_PessoaFisica(nome, cpf, dataNascimento, telefone, email, endereco);
            StringBuffer saida = new StringBuffer("Cliente criado com sucesso");
            saida.append("Dados do novo cliente: ");
            //            saida.append(clienteParaAdicionar.getNome()).append("\n");
            //            saida.append(clienteParaAdicionar.getCpf()).append("\n");
            //            saida.append(clienteParaAdicionar.getTelefone()).append("\n");
            //            saida.append(clienteParaAdicionar.getEndereco()).append("\n");
            //            saida.append(clienteParaAdicionar.getEmail()).append("\n");

            //            listaClientes.add(clienteParaAdicionar);
            //tArea_saida.setText(String.valueOf(saida));
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage());
        }
    }//GEN-LAST:event_btn_criarClienteActionPerformed

    private void jRadioButton_PessoaFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_PessoaFisicaActionPerformed
        // TODO add your handling code here:
        jLabel_DtNasc_RazSoc.setText("Data Nascimento:");
        jLabel_CPF_CNPJ.setText("CPF:");
        tField_DataNascimento.setVisible(true);
        tField_RazaoSocial.setVisible(false);
    }//GEN-LAST:event_jRadioButton_PessoaFisicaActionPerformed

    private void jRadioButton_PessoaJuridicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_PessoaJuridicaActionPerformed
        // TODO add your handling code here:
        jLabel_DtNasc_RazSoc.setText("Razão Social:");
        jLabel_CPF_CNPJ.setText("CNPJ:");
        tField_DataNascimento.setVisible(false);
        tField_RazaoSocial.setVisible(true);
    }//GEN-LAST:event_jRadioButton_PessoaJuridicaActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        new TelaInicial().setVisible(true);
        dispose();
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
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_criarCliente;
    private javax.swing.ButtonGroup buttonGroup_TipoPessoa;
    private javax.swing.JComboBox<EstadosBrazil> cb_Estado;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_CPF_CNPJ;
    private javax.swing.JLabel jLabel_DtNasc_RazSoc;
    private javax.swing.JLabel jLabel_Resultado;
    private javax.swing.JRadioButton jRadioButton_PessoaFisica;
    private javax.swing.JRadioButton jRadioButton_PessoaJuridica;
    private javax.swing.JTextField tField_Bairro;
    private javax.swing.JTextField tField_CEP;
    private javax.swing.JTextField tField_Cidade;
    private javax.swing.JTextField tField_Complemento;
    private javax.swing.JFormattedTextField tField_DataNascimento;
    private javax.swing.JTextField tField_Logradouro;
    private javax.swing.JTextField tField_Numero;
    private javax.swing.JTextField tField_RazaoSocial;
    private javax.swing.JTextField tField_TipoLogradouro;
    private javax.swing.JTextField tField_cpf_cnpj;
    private javax.swing.JTextField tField_email;
    private javax.swing.JTextField tField_nome;
    private javax.swing.JTextField tField_telefone;
    private javax.swing.JTextField tField_telefone1;
    private javax.swing.JTextField tField_telefone2;
    // End of variables declaration//GEN-END:variables
 private void loadComboBox() {
        cb_Estado.setModel(new DefaultComboBoxModel<>(EstadosBrazil.values()));
    }
}
