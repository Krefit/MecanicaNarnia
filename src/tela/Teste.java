/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tela;
import modelos.Funcionario;
<<<<<<< Updated upstream
import modelos.Peça;
=======
import modelos.OrdemDeServico;
import modelos.Peca;
import modelos.PessoaFisica;
import modelos.PessoaJuridica;
import modelos.Veiculo;
import modelos.auxiliares.Endereco;
import modelos.auxiliares.MarcaVeiculo;
import modelos.auxiliares.ModeloVeiculo;
import persistencia.ManipulaBancoFuncionario;
import persistencia.ManipulaBancoMarca;
import persistencia.ManipulaBancoModelos;
import persistencia.ManipulaBancoOrdemServico;
import persistencia.ManipulaBancoPecas;
import persistencia.ManipulaBancoPessoaFisica;
import persistencia.ManipulaBancoPessoaJuridica;
import persistencia.ManipulaBancoVeiculo;

>>>>>>> Stashed changes
/**
 *
 * @author ALUNO
 */
public class Teste extends javax.swing.JFrame {

    /**
     * Creates new form Teste
     */
    public Teste() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
<<<<<<< Updated upstream
        modelos.Funcionario p = new Funcionario("João", "123.456.789", "Rua sem nome", "62988888", "62999999", "teste@email.com", "Trabalhador");
        jTextArea1.setText(p.toString());
=======
        try {
//            Peca p = new Peca("codigoPeca", "descricao", 23.2f, 0, 10);
//            ManipulaBancoPecas mbp = new ManipulaBancoPecas();
//
//            Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse("11/12/2002");
//
//            Endereco e = new Endereco("tipoLogradouro", "logradouro", "numero", "complemento",
//                    "bairro", "cidade", EstadosBrazil.GO, "CEP");
//            Funcionario f = new Funcionario("especialidade", 1200.0f, 15, 123, "nome", "71817240021",
//                    dataNascimento, "email", e, "62987666666");
//                        mbp.incluir(p);
//                        System.out.println(mbp.buscar(p));
//            ManipulaBancoFuncionario mbFunc = new ManipulaBancoFuncionario();
//            System.out.println(mbFunc.buscar(f));
//            MarcaVeiculo m = new MarcaVeiculo("nomeMarca2");
//            
//            ManipulaBancoMarca mbm = new ManipulaBancoMarca();
//            System.out.println(mbm.buscar(3));
//            ModeloVeiculo mv = new ModeloVeiculo("NomeModelo", 2);
//            ManipulaBancoModelos mbv = new ManipulaBancoModelos();
//            ManipulaBancoFuncionario mbf = new ManipulaBancoFuncionario();
////            mbv.incluir(mv);
//            ManipulaBancoPessoaFisica mbpf = new ManipulaBancoPessoaFisica();
//            ManipulaBancoPessoaJuridica mbpj = new ManipulaBancoPessoaJuridica();
//
            Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse("11/12/2002");
//
////            Endereco e = new Endereco("tipoLogradouro", "logradouro", "numero", "complemento",
////                    "bairro", "cidade", EstadosBrazil.GO, "CEP");
//
//            PessoaJuridica pf = new PessoaJuridica("03993120000125", "razaoSocial", " nomeFantasia", " email", e, "62998887777");
////            mbpj.incluir(pf);
//            ManipulaBancoVeiculo mbv2 = new ManipulaBancoVeiculo();
////            ArrayList<Veiculo> listaFunc = mbv.buscarTodos();
//
//            System.out.println(mbFunc.buscar(f));
//            System.out.println(mbm.buscar(m));
//            System.out.println(mbp.buscar(p));
//            System.out.println(mbpj.buscar(pf));
//            for (Veiculo f : listaFunc) {
//                System.out.println(f.toString());
//            }
//            System.out.println(mbv.buscar(5));

            ManipulaBancoOrdemServico mbOs = new ManipulaBancoOrdemServico();

            OrdemDeServico os = new OrdemDeServico("defeitoRelatado", "servico",
                    23.5, dataNascimento, 2, 51, 3, 1);
            System.out.println(os);
            mbOs.incluir(os);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
>>>>>>> Stashed changes
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
            java.util.logging.Logger.getLogger(Teste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Teste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Teste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Teste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Teste().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
