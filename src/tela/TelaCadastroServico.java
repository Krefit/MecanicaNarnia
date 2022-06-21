/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Servico;
import persistencia.ManipulaBancoServicos;

/**
 *
 * @author witorsather
 */
public class TelaCadastroServico extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaCadastroServico
     */
    public TelaCadastroServico() {
        initComponents();
        loadTableServicos("");
    }

    private void loadTableServicos(String busca) {
        try {
            DefaultTableModel table = (DefaultTableModel) jTableListaServicos.getModel();
            table.setRowCount(0);//apagando linhas antigas para não duplicar a tabela

            ArrayList<Servico> listaServicos = new ManipulaBancoServicos().buscarTodos();// * buscando todos os serviços registrados
            for (Servico s : listaServicos) {
                if (s.getNomeServico().toUpperCase().contains(busca.toUpperCase())) {
                    table.addRow(new Object[]{s.getNomeServico(), String.format("%.2f", s.getValorMaoDeObra())});//    * adicionando linha com os dados do serviço
                }
            }

            TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTableListaServicos.getModel());
            jTableListaServicos.setRowSorter(sorter);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNomeServico = new javax.swing.JTextField();
        jButtonAddServico = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextFieldValorMaoDeObra = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListaServicos = new javax.swing.JTable();

        setTitle("Cadastro de Serviços");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Serviços:");

        jLabel2.setText("Nome do serviço: ");

        jTextFieldNomeServico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNomeServicoKeyTyped(evt);
            }
        });

        jButtonAddServico.setBackground(new java.awt.Color(0, 204, 255));
        jButtonAddServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-mais-2-matemática-verde-30.png"))); // NOI18N
        jButtonAddServico.setText("Adicionar serviço");
        jButtonAddServico.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonAddServico.setBorderPainted(false);
        jButtonAddServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddServicoActionPerformed(evt);
            }
        });

        jButtonEditar.setBackground(new java.awt.Color(0, 204, 255));
        jButtonEditar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButtonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-lápis-30.png"))); // NOI18N
        jButtonEditar.setText("Editar");
        jButtonEditar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonEditar.setBorderPainted(false);
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        jButtonRemover.setBackground(new java.awt.Color(0, 204, 255));
        jButtonRemover.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButtonRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-excluir-30.png"))); // NOI18N
        jButtonRemover.setText("Remover");
        jButtonRemover.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonRemover.setBorderPainted(false);
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });

        jLabel3.setText("Valor serviço: ");

        jFormattedTextFieldValorMaoDeObra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        jTableListaServicos.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableListaServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableListaServicosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableListaServicos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNomeServico))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextFieldValorMaoDeObra, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonAddServico, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jFormattedTextFieldValorMaoDeObra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAddServico, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRemover, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNomeServicoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomeServicoKeyTyped
        loadTableServicos(jTextFieldNomeServico.getText());
    }//GEN-LAST:event_jTextFieldNomeServicoKeyTyped

    private void jButtonAddServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddServicoActionPerformed
        try {
            String nomeServico = jTextFieldNomeServico.getText();
            double valorMaoDeObra = Double.parseDouble(jFormattedTextFieldValorMaoDeObra.getText().replace(".", "").replace(",", "."));
            //  * apagando ponto de separação numérico e substituindo virgula por ponto

            Servico s = new Servico(nomeServico, valorMaoDeObra);
            new ManipulaBancoServicos().incluir(s);
            JOptionPane.showMessageDialog(this, "Serviço Registrado");
            loadTableServicos("");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonAddServicoActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        try {
            int indexTabela = jTableListaServicos.getSelectedRow();
            if (indexTabela < 0) {
                JOptionPane.showMessageDialog(rootPane, "Selecione, na tabela qual cliente deseja editar");
            } else {
                String nomeServicoSelecionado = "" + jTableListaServicos.getValueAt(jTableListaServicos.getSelectedRow(), 0);

                String nomeServico = jTextFieldNomeServico.getText();
                double valorServico = Double.parseDouble(jFormattedTextFieldValorMaoDeObra.getText().replace(".", "").replace(",", "."));
                Servico s = new Servico(nomeServico, valorServico);

                ManipulaBancoServicos mb = new ManipulaBancoServicos();
                int idParaEditar = mb.buscar(nomeServicoSelecionado);
                mb.editar(idParaEditar, s);

                loadTableServicos("");
                JOptionPane.showMessageDialog(rootPane, "Editado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        try {
            int indexTabela = jTableListaServicos.getSelectedRow();
            if (indexTabela < 0) {
                JOptionPane.showMessageDialog(rootPane, "Selecione, na tabela qual cliente deseja editar");
            } else {
                int confirmação = JOptionPane.showConfirmDialog(rootPane, "Remover?");
                //  * 0 se for sim, 1 se for não, 2 se for cancelar

                if (confirmação == 0) {//   * caso tenha confirmado a exclusão
                    String nomeServico = "" + jTableListaServicos.getValueAt(indexTabela, 0);
                    ManipulaBancoServicos mb = new ManipulaBancoServicos();
                    int idServico = mb.buscar(nomeServico);
                    if (idServico == 0) {// * servico não encontrado
                        throw new Exception("Selecione, na tabela qual cliente deseja editar");
                    }
                    mb.remover(idServico);
                }
                loadTableServicos("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jButtonRemoverActionPerformed

    private void jTableListaServicosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaServicosMouseReleased
        try {
            int indexSelecionado = jTableListaServicos.getSelectedRow();
            if (indexSelecionado < 0) {//   * clique inválido
                throw new Exception("Selecione, na tabela qual cliente deseja editar");
            }
            ManipulaBancoServicos mb = new ManipulaBancoServicos();
            String nome = "" + jTableListaServicos.getValueAt(indexSelecionado, 0);
            Servico s = mb.buscar(mb.buscar(nome));
            if (s == null) {//  * servico não encontrado
                throw new Exception("Selecione, na tabela qual cliente deseja editar");
            }
            jTextFieldNomeServico.setText(s.getNomeServico());
            jFormattedTextFieldValorMaoDeObra.setText(String.format("%.2f", s.getValorMaoDeObra()));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }

    }//GEN-LAST:event_jTableListaServicosMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddServico;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JFormattedTextField jFormattedTextFieldValorMaoDeObra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListaServicos;
    private javax.swing.JTextField jTextFieldNomeServico;
    // End of variables declaration//GEN-END:variables
}
