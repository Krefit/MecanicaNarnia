/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import persistencia.ManipulaBancoPessoaFisica;

/**
 *
 * @author witorsather
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        //setResizable(false);
        //setLocationRelativeTo(null);
        //pegarResolucao();
    }

    private void pegarResolucao() {
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dimensao = t.getScreenSize();
        this.setSize((dimensao.width + 5), (dimensao.height - 38));

    }

    private void limpaTela() {
        JInternalFrame[] listaFrames = jDesktopPanePrincipal.getAllFrames();
        if (listaFrames != null && listaFrames.length > 0) {//  * vendo se existe alguma internalFrame sendo mostrada
            for (JInternalFrame frame : listaFrames) {
                frame.setVisible(false);//  * apagando jInternalFrame
            }
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

        jDesktopPanePrincipal = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemSair = new javax.swing.JMenuItem();
        jMenuCliente = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemCadastroDeVeiculo = new javax.swing.JMenuItem();
        jMenuItemCadastroDeMarca = new javax.swing.JMenuItem();
        jMenuItemCadastroDeModelo = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItemPecas = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItemCadastrodeServicos = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        javax.swing.GroupLayout jDesktopPanePrincipalLayout = new javax.swing.GroupLayout(jDesktopPanePrincipal);
        jDesktopPanePrincipal.setLayout(jDesktopPanePrincipalLayout);
        jDesktopPanePrincipalLayout.setHorizontalGroup(
            jDesktopPanePrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 929, Short.MAX_VALUE)
        );
        jDesktopPanePrincipalLayout.setVerticalGroup(
            jDesktopPanePrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );

        jMenu1.setText("Arquivo");

        jMenuItemSair.setText("Sair");
        jMenuItemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSairActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSair);

        jMenuBar1.add(jMenu1);

        jMenuCliente.setText("Clientes");
        jMenuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuClienteActionPerformed(evt);
            }
        });

        jMenuItem1.setText("jMenuItem1");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuCliente.add(jMenuItem1);

        jMenuBar1.add(jMenuCliente);

        jMenu3.setText("Produtos");

        jMenuItem2.setText("jMenuItem2");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Veículos");

        jMenuItemCadastroDeVeiculo.setText("Cadastro de Veículo");
        jMenuItemCadastroDeVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastroDeVeiculoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemCadastroDeVeiculo);

        jMenuItemCadastroDeMarca.setText("Cadastro de Marca");
        jMenuItemCadastroDeMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastroDeMarcaActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemCadastroDeMarca);

        jMenuItemCadastroDeModelo.setText("Cadastro de Modelo");
        jMenuItemCadastroDeModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastroDeModeloActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemCadastroDeModelo);

        jMenuBar1.add(jMenu2);

        jMenu6.setText("Peças");

        jMenuItemPecas.setText("Peças");
        jMenuItemPecas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPecasActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItemPecas);

        jMenuBar1.add(jMenu6);

        jMenu5.setText("Serviços");

        jMenuItemCadastrodeServicos.setText("Cadastro de Serviços");
        jMenuItemCadastrodeServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastrodeServicosActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItemCadastrodeServicos);

        jMenuBar1.add(jMenu5);

        jMenu4.setText("Sobre");

        jMenuItem3.setText("jMenuItem3");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPanePrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPanePrincipal)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSairActionPerformed
        int confirmação = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que deseja sair?");
        //  * 0 se for sim, 1 se for não, 2 se for cancelar

        if (confirmação == 0) {//   * quer sair
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItemSairActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.setExtendedState(MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void jMenuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuClienteActionPerformed
        limpaTela();
        TelaListaCliente newTelaListaCliente = new TelaListaCliente();
        newTelaListaCliente.setVisible(true);
        jDesktopPanePrincipal.add(newTelaListaCliente);
        jDesktopPanePrincipal.setVisible(true);
    }//GEN-LAST:event_jMenuClienteActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        limpaTela();
        TelaListaCliente tela = new TelaListaCliente();
        tela.setVisible(true);
        jDesktopPanePrincipal.add(tela);
        jDesktopPanePrincipal.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        limpaTela();
        TelaListaOS tela = new TelaListaOS(this.jDesktopPanePrincipal);
        tela.setVisible(true);
        jDesktopPanePrincipal.add(tela);
        jDesktopPanePrincipal.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            URL url = new URL("https://jteles050.wixsite.com/n-rnia-desenvolvedor");
            url.openConnection();
            try {
                Runtime.getRuntime().exec("cmd.exe /C start " + "https://jteles050.wixsite.com/n-rnia-desenvolvedor");
                Runtime.getRuntime().exec("cmd.exe /C start " + "https://jteles050.wixsite.com/n-rnia-desenvolvedor");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItemCadastroDeOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastroDeOSActionPerformed
        limpaTela();
        TelaCadastroDeOS newTelaCadastroDeOS = new TelaCadastroDeOS();
        newTelaCadastroDeOS.setVisible(true);
        jDesktopPanePrincipal.add(newTelaCadastroDeOS);
        jDesktopPanePrincipal.setVisible(true);
    }//GEN-LAST:event_jMenuItemCadastroDeOSActionPerformed

    private void jMenuItemCadastrodeServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastrodeServicosActionPerformed
        limpaTela();
        TelaCadastroServico newTelaCadastroServico = new TelaCadastroServico();
        newTelaCadastroServico.setVisible(true);
        jDesktopPanePrincipal.add(newTelaCadastroServico);
        jDesktopPanePrincipal.setVisible(true);
    }//GEN-LAST:event_jMenuItemCadastrodeServicosActionPerformed

    private void jMenuItemPecasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPecasActionPerformed
        limpaTela();
        TelaListaPeca newTelaListaPeca = new TelaListaPeca();
        newTelaListaPeca.setVisible(true);
        jDesktopPanePrincipal.add(newTelaListaPeca);
        jDesktopPanePrincipal.setVisible(true);
    }//GEN-LAST:event_jMenuItemPecasActionPerformed

    private void jMenuItemCadastroDeMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastroDeMarcaActionPerformed
        limpaTela();
        TelaCadastroDeMarcas newTelaCadastroDeMarcas = new TelaCadastroDeMarcas();
        newTelaCadastroDeMarcas.setVisible(true);
        jDesktopPanePrincipal.add(newTelaCadastroDeMarcas);
        jDesktopPanePrincipal.setVisible(true);
    }//GEN-LAST:event_jMenuItemCadastroDeMarcaActionPerformed

    private void jMenuItemCadastroDeModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastroDeModeloActionPerformed
        limpaTela();
        TelaCadastroModelos newTelaCadastroModelos = new TelaCadastroModelos();
        newTelaCadastroModelos.setVisible(true);
        jDesktopPanePrincipal.add(newTelaCadastroModelos);
        jDesktopPanePrincipal.setVisible(true);
    }//GEN-LAST:event_jMenuItemCadastroDeModeloActionPerformed

    private void jMenuItemCadastroDeVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastroDeVeiculoActionPerformed
        limpaTela();
        TelaCadastroDeVeiculos newTelaCadastroDeVeiculos = new TelaCadastroDeVeiculos();
        newTelaCadastroDeVeiculos.setVisible(true);
        jDesktopPanePrincipal.add(newTelaCadastroDeVeiculos);
        jDesktopPanePrincipal.setVisible(true);
    }//GEN-LAST:event_jMenuItemCadastroDeVeiculoActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPanePrincipal;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCliente;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItemCadastroDeMarca;
    private javax.swing.JMenuItem jMenuItemCadastroDeModelo;
    private javax.swing.JMenuItem jMenuItemCadastroDeVeiculo;
    private javax.swing.JMenuItem jMenuItemCadastrodeServicos;
    private javax.swing.JMenuItem jMenuItemPecas;
    private javax.swing.JMenuItem jMenuItemSair;
    // End of variables declaration//GEN-END:variables
}
