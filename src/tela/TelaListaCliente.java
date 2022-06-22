/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.PessoaFisica;
import persistencia.ManipulaBancoPessoaFisica;
import persistencia.ManipulaBancoVeiculo;
import enumerations.EstadosBrazil;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.PessoaJuridica;
import modelos.auxiliares.Endereco;
import persistencia.ManipulaBancoPessoaJuridica;

public class TelaListaCliente extends javax.swing.JInternalFrame {

    public enum operacaoBusca {
        CPF,
        CNPJ,
        NOME,
        RAZAO_SOCIAL,
        PESSOA_FISICA,
        PESSOA_JURIDICA;
    }

    /**
     * Creates new form TelaCadastroDeClientes
     */
    public TelaListaCliente() {
        initComponents();
        jTableClientes.setSelectionMode(1);

        jRadioButton_PessoaFisica.setSelected(true);//  * selecionando botão de pessoa fisica
        jRadioButton_PessoaFisicaActionPerformed(null);//   * iniciar com pessoa fisica selecionada

        loadComboBox();
        loadTableClientes();

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTableClientes.getModel());
        jTableClientes.setRowSorter(sorter);

        /*Font roboto = null;
        try {
            roboto = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("Fontes/RobotoRegular.ttf"));
        } catch (IOException | FontFormatException e) {
            System.out.println("Merda " + e);
        }
        roboto = roboto.deriveFont(Font.PLAIN, 20);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(roboto);
        this.setFont(roboto);*/
    }

    private void loadComboBox() {
        cb_Estado.setModel(new DefaultComboBoxModel<>(EstadosBrazil.values()));
    }

    private void loadTableClientes() {
        try {
            DefaultTableModel table = (DefaultTableModel) jTableClientes.getModel();
            table.setRowCount(0);// * apagando dados da tabela para não duplicar as linhas
            ArrayList<PessoaFisica> listaPessoas = new ManipulaBancoPessoaFisica().buscarTodos();
            ArrayList<PessoaJuridica> listaPessoasJuridicas = new ManipulaBancoPessoaJuridica().buscarTodos();
            for (PessoaFisica p : listaPessoas) {
                String[] dados = new String[jTableClientes.getColumnCount()];
                dados[0] = p.getNome();
                dados[1] = p.getCpf();
                dados[2] = p.getTelefone()[0];
                dados[3] = p.getTelefone()[1];
                dados[4] = p.getTelefone()[2];
                dados[5] = new SimpleDateFormat("dd/MM/yyyy").format(p.getDataNascimento());
                dados[6] = p.getEmail();
                dados[7] = "" + new ManipulaBancoVeiculo().getQuantidadeVeiculos(new ManipulaBancoPessoaFisica().buscar(p));

                table.addRow(dados);
            }
            for (PessoaJuridica p : listaPessoasJuridicas) {
                String[] dados = new String[jTableClientes.getColumnCount()];
                dados[0] = p.getRazaoSocial();
                dados[1] = p.getCnpj();
                dados[2] = p.getTelefone()[0];
                dados[3] = p.getTelefone()[1];
                dados[4] = p.getTelefone()[2];
                dados[5] = p.getNomeFantasia();
                dados[6] = p.getEmail();
                dados[7] = "" + new ManipulaBancoVeiculo().getQuantidadeVeiculos(new ManipulaBancoPessoaJuridica().buscar(p));

                table.addRow(dados);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void loadTableClientes(String filtro, operacaoBusca op) {
        try {
            switch (op) {
                case NOME:
                    filtraTabelaNome(filtro);
                    break;
                case RAZAO_SOCIAL:
                    filtraTabelaRazaoSocial(filtro);
                    break;
                case CNPJ:
                    filtraTabelaCnpj(filtro);
                    break;
                case CPF:
                    filtraTabelaCpf(filtro);
                    break;
                case PESSOA_FISICA:
                    filtraTabelaPessoaFisica();
                    break;
                case PESSOA_JURIDICA:
                    filtraTabelaPessoajuridica();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void filtraTabelaNome(String nome) {
        try {
            DefaultTableModel table = (DefaultTableModel) jTableClientes.getModel();
            table.setRowCount(0);// * apagando dados da tabela para não duplicar as linhas
            ArrayList<PessoaFisica> listaPessoas = new ManipulaBancoPessoaFisica().buscarTodos();
            if (listaPessoas != null) {
                for (PessoaFisica p : listaPessoas) {
                    if (p.getNome().toUpperCase().contains(nome.toUpperCase())) {
                        String[] dados = new String[jTableClientes.getColumnCount()];
                        dados[0] = p.getNome();
                        dados[1] = p.getCpf();
                        dados[2] = p.getTelefone()[0];
                        dados[3] = p.getTelefone()[1];
                        dados[4] = p.getTelefone()[2];
                        dados[5] = new SimpleDateFormat("dd/MM/yyyy").format(p.getDataNascimento());
                        dados[6] = p.getEmail();
                        dados[7] = "" + new ManipulaBancoVeiculo().getQuantidadeVeiculos(new ManipulaBancoPessoaFisica().buscar(p));

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
            DefaultTableModel table = (DefaultTableModel) jTableClientes.getModel();
            table.setRowCount(0);// * apagando dados da tabela para não duplicar as linhas
            ArrayList<PessoaJuridica> listaPessoasJuridicas = new ManipulaBancoPessoaJuridica().buscarTodos();
            if (listaPessoasJuridicas == null) {
                return;
            }
            for (PessoaJuridica p : listaPessoasJuridicas) {
                if (p.getRazaoSocial().toUpperCase().contains(razaoSocial.toUpperCase())) {

                    String[] dados = new String[jTableClientes.getColumnCount()];
                    dados[0] = p.getRazaoSocial();
                    dados[1] = p.getCnpj();
                    dados[2] = p.getTelefone()[0];
                    dados[3] = p.getTelefone()[1];
                    dados[4] = p.getTelefone()[2];
                    dados[5] = p.getNomeFantasia();
                    dados[6] = p.getEmail();
                    dados[7] = "" + new ManipulaBancoVeiculo().getQuantidadeVeiculos(new ManipulaBancoPessoaJuridica().buscar(p));

                    table.addRow(dados);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void filtraTabelaCpf(String cpf) {
        try {
            DefaultTableModel table = (DefaultTableModel) jTableClientes.getModel();
            table.setRowCount(0);// * apagando dados da tabela para não duplicar as linhas
            ArrayList<PessoaFisica> listaPessoas = new ManipulaBancoPessoaFisica().buscarTodos();
            if (listaPessoas != null) {
                for (PessoaFisica p : listaPessoas) {
                    if (p.getCpf().toUpperCase().contains(cpf)) {
                        String[] dados = new String[jTableClientes.getColumnCount()];
                        dados[0] = p.getNome();
                        dados[1] = p.getCpf();
                        dados[2] = p.getTelefone()[0];
                        dados[3] = p.getTelefone()[1];
                        dados[4] = p.getTelefone()[2];
                        dados[5] = new SimpleDateFormat("dd/MM/yyyy").format(p.getDataNascimento());
                        dados[6] = p.getEmail();
                        dados[7] = "" + new ManipulaBancoVeiculo().getQuantidadeVeiculos(new ManipulaBancoPessoaFisica().buscar(p));

                        table.addRow(dados);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void filtraTabelaCnpj(String cnpj) {
        try {
            System.out.println(cnpj.replace(".", "").replace(" ", "").replace("/", "").replace("-", ""));
            DefaultTableModel table = (DefaultTableModel) jTableClientes.getModel();
            table.setRowCount(0);// * apagando dados da tabela para não duplicar as linhas
            ArrayList<PessoaJuridica> listaPessoasJuridicas = new ManipulaBancoPessoaJuridica().buscarTodos();
            if (listaPessoasJuridicas == null) {
                return;
            }
            for (PessoaJuridica p : listaPessoasJuridicas) {
                if (p.getCnpj().contains(cnpj)) {

                    String[] dados = new String[jTableClientes.getColumnCount()];
                    dados[0] = p.getRazaoSocial();
                    dados[1] = p.getCnpj();
                    dados[2] = p.getTelefone()[0];
                    dados[3] = p.getTelefone()[1];
                    dados[4] = p.getTelefone()[2];
                    dados[5] = p.getNomeFantasia();
                    dados[6] = p.getEmail();
                    dados[7] = "" + new ManipulaBancoVeiculo().getQuantidadeVeiculos(new ManipulaBancoPessoaJuridica().buscar(p));

                    table.addRow(dados);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void filtraTabelaPessoaFisica() {
        try {
            DefaultTableModel table = (DefaultTableModel) jTableClientes.getModel();
            table.setRowCount(0);// * apagando dados da tabela para não duplicar as linhas
            ArrayList<PessoaFisica> listaPessoas = new ManipulaBancoPessoaFisica().buscarTodos();
            if (listaPessoas != null) {
                for (PessoaFisica p : listaPessoas) {
                    String[] dados = new String[jTableClientes.getColumnCount()];
                    dados[0] = p.getNome();
                    dados[1] = p.getCpf();
                    dados[2] = p.getTelefone()[0];
                    dados[3] = p.getTelefone()[1];
                    dados[4] = p.getTelefone()[2];
                    dados[5] = new SimpleDateFormat("dd/MM/yyyy").format(p.getDataNascimento());
                    dados[6] = p.getEmail();
                    dados[7] = "" + new ManipulaBancoVeiculo().getQuantidadeVeiculos(new ManipulaBancoPessoaFisica().buscar(p));

                    table.addRow(dados);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void filtraTabelaPessoajuridica() {
        try {
            DefaultTableModel table = (DefaultTableModel) jTableClientes.getModel();
            table.setRowCount(0);// * apagando dados da tabela para não duplicar as linhas
            ArrayList<PessoaJuridica> listaPessoasJuridicas = new ManipulaBancoPessoaJuridica().buscarTodos();
            if (listaPessoasJuridicas == null) {
                return;
            }
            for (PessoaJuridica p : listaPessoasJuridicas) {

                String[] dados = new String[jTableClientes.getColumnCount()];
                dados[0] = p.getRazaoSocial();
                dados[1] = p.getCnpj();
                dados[2] = p.getTelefone()[0];
                dados[3] = p.getTelefone()[1];
                dados[4] = p.getTelefone()[2];
                dados[5] = p.getNomeFantasia();
                dados[6] = p.getEmail();
                dados[7] = "" + new ManipulaBancoVeiculo().getQuantidadeVeiculos(new ManipulaBancoPessoaJuridica().buscar(p));

                table.addRow(dados);
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

        buttonGroupTipoPessoa = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jButtonEditar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jButtonRemover = new javax.swing.JButton();
        jLabelNome_razaoSocial = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jRadioButton_PessoaFisica = new javax.swing.JRadioButton();
        jLabel_CPF_CNPJ = new javax.swing.JLabel();
        jRadioButton_PessoaJuridica = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jButtonAdicionar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        tField_nome_razaoSocial = new javax.swing.JTextField();
        tField_TipoLogradouro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        tField_email = new javax.swing.JTextField();
        tField_nomeFantasia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextFieldCpf = new javax.swing.JFormattedTextField();
        tField_Logradouro = new javax.swing.JTextField();
        jFormattedTextFieldCnpj = new javax.swing.JFormattedTextField();
        tField_Numero = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jFormattedTextFieldDataNascimento = new javax.swing.JFormattedTextField();
        jFormattedTextFieldCep = new javax.swing.JFormattedTextField();
        jFormattedTextFieldTelefoneResidencial = new javax.swing.JFormattedTextField();
        jFormattedTextFieldTelefoneComercial = new javax.swing.JFormattedTextField();
        jFormattedTextFieldTelefoneCelular = new javax.swing.JFormattedTextField();
        jButtonFiltrarNome = new javax.swing.JButton();
        jButtonFiltrarNome1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabelDataNasc_NomeFantasia = new javax.swing.JLabel();
        tField_Complemento = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tField_Bairro = new javax.swing.JTextField();
        tField_Cidade = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cb_Estado = new javax.swing.JComboBox<>();

        setTitle("CLIENTES");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("CEP:");

        jButtonEditar.setBackground(new java.awt.Color(0, 204, 255));
        jButtonEditar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButtonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-lápis-30.png"))); // NOI18N
        jButtonEditar.setText("Editar");
        jButtonEditar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Residencial:");

        jButtonRemover.setBackground(new java.awt.Color(0, 204, 255));
        jButtonRemover.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButtonRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-excluir-30.png"))); // NOI18N
        jButtonRemover.setText("Remover");
        jButtonRemover.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });

        jLabelNome_razaoSocial.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelNome_razaoSocial.setText("Nome: ");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Comercial:");

        buttonGroupTipoPessoa.add(jRadioButton_PessoaFisica);
        jRadioButton_PessoaFisica.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jRadioButton_PessoaFisica.setText("Pessoa Fisica");
        jRadioButton_PessoaFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_PessoaFisicaActionPerformed(evt);
            }
        });

        jLabel_CPF_CNPJ.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel_CPF_CNPJ.setText("CPF: ");

        buttonGroupTipoPessoa.add(jRadioButton_PessoaJuridica);
        jRadioButton_PessoaJuridica.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jRadioButton_PessoaJuridica.setText("Pessoa Juridica");
        jRadioButton_PessoaJuridica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_PessoaJuridicaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Celular:");

        jButtonAdicionar.setBackground(new java.awt.Color(0, 204, 255));
        jButtonAdicionar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButtonAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-mais-2-matemática-verde-30.png"))); // NOI18N
        jButtonAdicionar.setText("Adicionar");
        jButtonAdicionar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Tipo Logradouro:");

        jTableClientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome/Razão social", "CPF/CNPJ", "Celular", "Telefone Comercial", "Telefone fixo", "Data de Nascimento/Nome social", "E-mail", "Quantidade de veiculos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableClientes);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Email:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Logradouro");

        try {
            jFormattedTextFieldCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jFormattedTextFieldCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Nº:");

        try {
            jFormattedTextFieldDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jFormattedTextFieldCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jFormattedTextFieldTelefoneResidencial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jFormattedTextFieldTelefoneComercial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jFormattedTextFieldTelefoneCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jButtonFiltrarNome.setText("Pesquisar");
        jButtonFiltrarNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFiltrarNomeActionPerformed(evt);
            }
        });

        jButtonFiltrarNome1.setText("Pesquisar");
        jButtonFiltrarNome1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFiltrarNome1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Complemento:");

        jLabelDataNasc_NomeFantasia.setText("Data nascimento");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Bairro:");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Cidade:");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Estado:");

        cb_Estado.setAutoscrolls(true);
        cb_Estado.setPreferredSize(new java.awt.Dimension(7, 22));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelNome_razaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tField_nome_razaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(7, 7, 7)
                                        .addComponent(jButtonFiltrarNome))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jFormattedTextFieldTelefoneCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(jLabel14)
                                        .addGap(5, 5, 5)
                                        .addComponent(jFormattedTextFieldTelefoneResidencial, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_CPF_CNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextFieldCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextFieldTelefoneComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(58, 58, 58)
                                .addComponent(jButtonFiltrarNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelDataNasc_NomeFantasia)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(399, 399, 399)
                                        .addComponent(tField_email, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tField_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(cb_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(tField_Bairro, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(320, 320, 320)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(636, 636, 636)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(708, 708, 708)
                                        .addComponent(jFormattedTextFieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jFormattedTextFieldDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tField_nomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addComponent(tField_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(tField_TipoLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(tField_Logradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(tField_Complemento, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(221, 221, 221)
                                .addComponent(jRadioButton_PessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(283, 283, 283)
                                .addComponent(jRadioButton_PessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(jButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jButtonRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton_PessoaFisica)
                    .addComponent(jRadioButton_PessoaJuridica))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabelNome_razaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(tField_nome_razaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonFiltrarNome))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextFieldTelefoneCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jFormattedTextFieldTelefoneResidencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel_CPF_CNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addComponent(jFormattedTextFieldTelefoneComercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jButtonFiltrarNome1)))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabelDataNasc_NomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tField_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tField_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(cb_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel10))
                            .addComponent(tField_Bairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel8))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jFormattedTextFieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jFormattedTextFieldDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(tField_nomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(tField_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(tField_TipoLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7))
                    .addComponent(tField_Logradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel9))
                    .addComponent(tField_Complemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        try {
            int indexTabela = jTableClientes.getSelectedRow();
            if (indexTabela < 0) {
                JOptionPane.showMessageDialog(rootPane, "Selecione, na tabela qual cliente deseja editar");
            } else {

                String nome_razaoSocial = tField_nome_razaoSocial.getText();
                String[] telefone = new String[3];
                telefone[0] = jFormattedTextFieldTelefoneCelular.getText().trim();//   * apagando espaços em branco
                if (jFormattedTextFieldTelefoneComercial.getText().replace("(", "").replace(")", "").replace("-", "").trim().equals("")) {//   * caso não tenha informado o telefone comercial
                    telefone[1] = telefone[0];//    * duplicando o telefone celular
                } else {//  * informou o  telefone comercial
                    telefone[1] = jFormattedTextFieldTelefoneComercial.getText().trim();//   * apagando espaços em branco
                }
                if (jFormattedTextFieldTelefoneResidencial.getText().replace("(", "").replace(")", "").replace("-", "").trim().equals("")) {// * caso não tenha informado o telefone residencial
                    telefone[2] = telefone[0];//    * duplicando o telefone celular
                } else {//  * informou o  telefone residencial
                    telefone[2] = jFormattedTextFieldTelefoneResidencial.getText().trim();//   * apagando espaços em branco
                }
                String tipoLogradouro = tField_TipoLogradouro.getText();
                String logradoro = tField_Logradouro.getText();
                String numero = tField_Numero.getText();
                String complemento = tField_Complemento.getText().replace(",", " ");
                String bairro = tField_Bairro.getText();
                String cidade = tField_Cidade.getText();
                String estado = cb_Estado.getSelectedItem().toString();
                String CEP = jFormattedTextFieldCep.getText();
                String email = tField_email.getText();
                EstadosBrazil eb = Enum.valueOf(EstadosBrazil.class, estado);

                Endereco endereco = new Endereco(tipoLogradouro, logradoro, numero, complemento, bairro, cidade, eb, CEP);
                if (jRadioButton_PessoaFisica.isSelected()) {
                    ManipulaBancoPessoaFisica mb = new ManipulaBancoPessoaFisica();
                    String cpfClienteParaExclusao = String.valueOf(jTableClientes.getValueAt(jTableClientes.getSelectedRow(), 1));
                    int idClienteParaExclusao = mb.buscar(cpfClienteParaExclusao);

                    String cpf = jFormattedTextFieldCpf.getText();
                    Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(jFormattedTextFieldDataNascimento.getText());
                    PessoaFisica clPF = new PessoaFisica(nome_razaoSocial, cpf, dataNascimento, email, endereco, telefone);
                    mb.editar(idClienteParaExclusao, clPF);
                } else if (jRadioButton_PessoaJuridica.isSelected()) {
                    String cnpjParaExclusao = String.valueOf(jTableClientes.getValueAt(jTableClientes.getSelectedRow(), 1));
                    int idClienteParaExclusao = new ManipulaBancoPessoaJuridica().buscar(cnpjParaExclusao);

                    String cnpj = jFormattedTextFieldCnpj.getText();
                    String nomeFantasia = tField_nomeFantasia.getText();
                    PessoaJuridica clPJ = new PessoaJuridica(cnpj, nome_razaoSocial, nomeFantasia, email, endereco, telefone[0], telefone[1], telefone[2]);
                    new ManipulaBancoPessoaJuridica().editar(idClienteParaExclusao, clPJ);
                }

                JOptionPane.showMessageDialog(rootPane, "Editado");
                loadTableClientes();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        try {
            int indexTabela = jTableClientes.getSelectedRow();
            if (indexTabela < 0) {
                JOptionPane.showMessageDialog(rootPane, "Selecione, na tabela qual cliente deseja editar");
            } else {
                int confirmação = JOptionPane.showConfirmDialog(rootPane, "Remover?");
//  * 0 se for sim, 1 se for não, 2 se for cancelar

                if (confirmação == 0) {//   * caso tenha confirmado a exclusão
                    if (jRadioButton_PessoaFisica.isSelected()) {
                        String cpfClienteParaExclusao = String.valueOf(jTableClientes.getValueAt(jTableClientes.getSelectedRow(), 1));
                        int idClienteParaExclusao = new ManipulaBancoPessoaFisica().buscar(cpfClienteParaExclusao);
                        if (idClienteParaExclusao != 0) {//   * é pessoa física
                            new ManipulaBancoPessoaFisica().remover(idClienteParaExclusao);
                        } else {//    * é pessoa jurídica
                        }
                    } else {
                        String cnpjClienteParaExclusao = String.valueOf(jTableClientes.getValueAt(jTableClientes.getSelectedRow(), 1));
                        int idClienteParaExclusao = new ManipulaBancoPessoaJuridica().buscar(cnpjClienteParaExclusao);
                        new ManipulaBancoPessoaJuridica().remover(idClienteParaExclusao);
                    }
                } else {//    * cancelar
//  * pass
                }
            }
            loadTableClientes();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jButtonRemoverActionPerformed

    private void jRadioButton_PessoaFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_PessoaFisicaActionPerformed
        jLabelDataNasc_NomeFantasia.setText("Data Nascimento:");
        jLabel_CPF_CNPJ.setText("CPF:");
        jFormattedTextFieldCnpj.setVisible(false);
        tField_nomeFantasia.setVisible(false);
        jFormattedTextFieldCpf.setVisible(true);
        jFormattedTextFieldDataNascimento.setVisible(true);
        jLabelNome_razaoSocial.setText("Nome:");
    }//GEN-LAST:event_jRadioButton_PessoaFisicaActionPerformed

    private void jRadioButton_PessoaJuridicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_PessoaJuridicaActionPerformed
        jLabelDataNasc_NomeFantasia.setText("Nome Fantasia:");
        jLabel_CPF_CNPJ.setText("CNPJ:");
        jFormattedTextFieldDataNascimento.setVisible(false);
        jFormattedTextFieldCpf.setVisible(false);
        tField_nomeFantasia.setVisible(true);
        jFormattedTextFieldCnpj.setVisible(true);
        jLabelNome_razaoSocial.setText("Razão Social:");
    }//GEN-LAST:event_jRadioButton_PessoaJuridicaActionPerformed

    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        //        new TelaCadastroCliente().setVisible(true);
        try {
            String nome = tField_nome_razaoSocial.getText();
            /*
            private String complemento;
            private String bairro;
            private String cidade;
            private EstadosBrazil estado;
            private String CEP;*/
            String[] telefone = new String[3];
            telefone[0] = jFormattedTextFieldTelefoneCelular.getText().trim();//   * apagando espaços em branco
            if (jFormattedTextFieldTelefoneComercial.getText().replace("(", "").replace(")", "").replace("-", "").trim().equals("")) {//   * caso não tenha informado o telefone comercial
                telefone[1] = telefone[0];//    * duplicando o telefone celular
            } else {//  * informou o  telefone comercial
                telefone[1] = jFormattedTextFieldTelefoneComercial.getText().replace("(", "").replace(")", "").replace("-", "").trim();//   * apagando espaços em branco
            }
            if (jFormattedTextFieldTelefoneResidencial.getText().replace("(", "").replace(")", "").replace("-", "").trim().equals("")) {// * caso não tenha informado o telefone residencial
                telefone[2] = telefone[0];//    * duplicando o telefone celular
            } else {//  * informou o  telefone residencial
                telefone[2] = jFormattedTextFieldTelefoneResidencial.getText().trim();//   * apagando espaços em branco
            }
            String tipoLogradouro = tField_TipoLogradouro.getText();
            String logradoro = tField_Logradouro.getText();
            String numero = tField_Numero.getText();
            String complemento = tField_Complemento.getText().replace(",", " ");
            String bairro = tField_Bairro.getText();
            String cidade = tField_Cidade.getText();
            String estado = cb_Estado.getSelectedItem().toString();
            String CEP = jFormattedTextFieldCep.getText();
            String email = tField_email.getText();
            EstadosBrazil eb = Enum.valueOf(EstadosBrazil.class, estado);

            Endereco endereco = new Endereco(tipoLogradouro, logradoro, numero, complemento, bairro, cidade, eb, CEP);
            if (jRadioButton_PessoaFisica.isSelected()) {
                String cpf = jFormattedTextFieldCpf.getText();
                String auxDataNascimento = jFormattedTextFieldDataNascimento.getText();
                String[] vetDataNascimento = auxDataNascimento.split("/");
                Date dataNascimento = new Date(Integer.parseInt(vetDataNascimento[2]) - 1900, Integer.parseInt(vetDataNascimento[1]) - 1, Integer.parseInt(vetDataNascimento[0]));

                PessoaFisica clPF = new PessoaFisica(nome, cpf, dataNascimento, email, endereco, telefone[0], telefone[1], telefone[2]);
                new ManipulaBancoPessoaFisica().incluir(clPF);
            } else if (jRadioButton_PessoaJuridica.isSelected()) {
                String cnpj = jFormattedTextFieldCnpj.getText();
                String razaoSocial = tField_nome_razaoSocial.getText();

                PessoaJuridica clPJ = new PessoaJuridica(cnpj, razaoSocial, nome, email, endereco, telefone[0], telefone[1], telefone[2]);
                new ManipulaBancoPessoaJuridica().incluir(clPJ);
                //String razaoSocial = t
            }
            JOptionPane.showMessageDialog(rootPane, "Cliente cadastrado com sucesso!");
            loadTableClientes();
        } catch (Exception erro) {
            erro.printStackTrace();
            JOptionPane.showMessageDialog(this, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    private void jTableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClientesMouseClicked
        try {
            int indexSelecionado = jTableClientes.getSelectedRow();
            if (indexSelecionado >= 0) {//   * o clique foi válido
                String cpfCnpjClienteEscolhido = String.valueOf(jTableClientes.getValueAt(indexSelecionado, 1));
                PessoaFisica pessoaFisica = new ManipulaBancoPessoaFisica().buscar(new ManipulaBancoPessoaFisica().buscar(cpfCnpjClienteEscolhido));

                if (pessoaFisica != null) {//   * é uma pessoa fisica
                    tField_nome_razaoSocial.setText(pessoaFisica.getNome());
                    tField_email.setText(pessoaFisica.getEmail());
                    jFormattedTextFieldTelefoneCelular.setText(pessoaFisica.getTelefone()[0]);
                    jFormattedTextFieldTelefoneComercial.setText(pessoaFisica.getTelefone()[1]);
                    jFormattedTextFieldTelefoneResidencial.setText(pessoaFisica.getTelefone()[2]);
                    jFormattedTextFieldDataNascimento.setText(new SimpleDateFormat("dd/MM/yyyy").format(pessoaFisica.getDataNascimento()));
                    jFormattedTextFieldCpf.setText(pessoaFisica.getCpf());

                    Endereco e = pessoaFisica.getEndereco();
                    tField_Bairro.setText(e.getBairro());
                    jFormattedTextFieldCep.setText(e.getCEP());
                    tField_Cidade.setText(e.getCidade());
                    tField_Complemento.setText(e.getComplemento());
                    tField_Logradouro.setText(e.getLogradouro());
                    tField_Numero.setText(e.getNumero());
                    tField_TipoLogradouro.setText(e.getTipoLogradouro());
                    cb_Estado.setSelectedItem(e.getEstado());

                    jRadioButton_PessoaFisicaActionPerformed(null);
                    jRadioButton_PessoaFisica.setSelected(true);
                } else {//    * é uma pessoa jurídica
                    PessoaJuridica pf = new ManipulaBancoPessoaJuridica().buscar(new ManipulaBancoPessoaJuridica().buscar(cpfCnpjClienteEscolhido));
                    if (pf != null) {
                        tField_nome_razaoSocial.setText(pf.getRazaoSocial());
                        tField_email.setText(pf.getEmail());
                        jFormattedTextFieldTelefoneCelular.setText(pf.getTelefone()[0]);
                        jFormattedTextFieldTelefoneComercial.setText(pf.getTelefone()[1]);
                        jFormattedTextFieldTelefoneResidencial.setText(pf.getTelefone()[2]);
                        tField_nomeFantasia.setText(pf.getNomeFantasia());
                        jFormattedTextFieldCnpj.setText(pf.getCnpj());

                        Endereco e = pf.getEndereco();
                        tField_Bairro.setText(e.getBairro());
                        jFormattedTextFieldCep.setText(e.getCEP());
                        tField_Cidade.setText(e.getCidade());
                        tField_Complemento.setText(e.getComplemento());
                        tField_Logradouro.setText(e.getLogradouro());
                        tField_Numero.setText(e.getNumero());
                        tField_TipoLogradouro.setText(e.getTipoLogradouro());
                        cb_Estado.setSelectedItem(e.getEstado());

                        jRadioButton_PessoaJuridicaActionPerformed(null);
                        jRadioButton_PessoaJuridica.setSelected(true);
                    } else {//    * não existe no banco
                        System.out.println("Ciente não existe no banco");
                        System.out.println("cpf: " + cpfCnpjClienteEscolhido);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jTableClientesMouseClicked

    private void jButtonFiltrarNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFiltrarNomeActionPerformed
        if (jRadioButton_PessoaFisica.isSelected()) {
            loadTableClientes(tField_nome_razaoSocial.getText(), operacaoBusca.NOME);
        } else if (jRadioButton_PessoaJuridica.isSelected()) {
            loadTableClientes(tField_nome_razaoSocial.getText(), operacaoBusca.RAZAO_SOCIAL);
        }
    }//GEN-LAST:event_jButtonFiltrarNomeActionPerformed

    private void jButtonFiltrarNome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFiltrarNome1ActionPerformed
        if (jRadioButton_PessoaFisica.isSelected()) {
            loadTableClientes(jFormattedTextFieldCpf.getText(), operacaoBusca.CPF);
        } else if (jRadioButton_PessoaJuridica.isSelected()) {
            loadTableClientes(jFormattedTextFieldCnpj.getText(), operacaoBusca.CNPJ);
        }
    }//GEN-LAST:event_jButtonFiltrarNome1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaListaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaListaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaListaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaListaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaListaCliente().setVisible(true);
            }
        });
    }

    private void excluirPessoaFisica() {
        try {
            ManipulaBancoPessoaFisica mb = new ManipulaBancoPessoaFisica();
            String cpfClienteParaExclusao = String.valueOf(jTableClientes.getValueAt(jTableClientes.getSelectedRow(), 1));
            int idClienteParaExclusao = new ManipulaBancoPessoaFisica().buscar(cpfClienteParaExclusao);
            mb.remover(idClienteParaExclusao);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void adicionarPessoaFisica() {
        try {
            String nome = tField_nome_razaoSocial.getText();
            String[] telefone = new String[3];
            telefone[0] = jFormattedTextFieldTelefoneCelular.getText().trim();//   * apagando espaços em branco
            if (jFormattedTextFieldTelefoneComercial.getText().trim().equals("")) {//   * caso não tenha informado o telefone comercial
                telefone[1] = telefone[0];//    * duplicando o telefone celular
            } else {//  * informou o  telefone comercial
                telefone[1] = jFormattedTextFieldTelefoneComercial.getText().trim();//   * apagando espaços em branco
            }
            if (jFormattedTextFieldTelefoneResidencial.getText().trim().equals("")) {// * caso não tenha informado o telefone residencial
                telefone[2] = telefone[0];//    * duplicando o telefone celular
            } else {//  * informou o  telefone residencial
                telefone[2] = jFormattedTextFieldTelefoneResidencial.getText().trim();//   * apagando espaços em branco
            }
            String tipoLogradouro = tField_TipoLogradouro.getText();
            String logradoro = tField_Logradouro.getText();
            String numero = tField_Numero.getText();
            String complemento = tField_Complemento.getText();
            String bairro = tField_Bairro.getText();
            String cidade = tField_Cidade.getText();
            String estado = cb_Estado.getSelectedItem().toString();
            String CEP = jFormattedTextFieldCep.getText();
            String email = tField_email.getText();
            EstadosBrazil eb = Enum.valueOf(EstadosBrazil.class, estado);

            Endereco endereco = new Endereco(tipoLogradouro, logradoro, numero, complemento, bairro, cidade, eb, CEP);
            if (jRadioButton_PessoaFisica.isSelected()) {
                String cpf = jFormattedTextFieldCpf.getText();
                Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(jFormattedTextFieldDataNascimento.getText());

                PessoaFisica clPF = new PessoaFisica(nome, cpf, dataNascimento, email, endereco, telefone);
                new ManipulaBancoPessoaFisica().incluir(clPF);
            } else if (jRadioButton_PessoaJuridica.isSelected()) {
                String cnpj = jFormattedTextFieldCnpj.getText();
                String razaoSocial = tField_nome_razaoSocial.getText();

                PessoaJuridica clPJ = new PessoaJuridica(cnpj, razaoSocial, nome, email, endereco, telefone[0], telefone[1], telefone[2]);
                new ManipulaBancoPessoaJuridica().incluir(clPJ);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupTipoPessoa;
    private javax.swing.JComboBox<EstadosBrazil> cb_Estado;
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonFiltrarNome;
    private javax.swing.JButton jButtonFiltrarNome1;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JFormattedTextField jFormattedTextFieldCep;
    private javax.swing.JFormattedTextField jFormattedTextFieldCnpj;
    private javax.swing.JFormattedTextField jFormattedTextFieldCpf;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataNascimento;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelefoneCelular;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelefoneComercial;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelefoneResidencial;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDataNasc_NomeFantasia;
    private javax.swing.JLabel jLabelNome_razaoSocial;
    private javax.swing.JLabel jLabel_CPF_CNPJ;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton_PessoaFisica;
    private javax.swing.JRadioButton jRadioButton_PessoaJuridica;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTextField tField_Bairro;
    private javax.swing.JTextField tField_Cidade;
    private javax.swing.JTextField tField_Complemento;
    private javax.swing.JTextField tField_Logradouro;
    private javax.swing.JTextField tField_Numero;
    private javax.swing.JTextField tField_TipoLogradouro;
    private javax.swing.JTextField tField_email;
    private javax.swing.JTextField tField_nomeFantasia;
    private javax.swing.JTextField tField_nome_razaoSocial;
    // End of variables declaration//GEN-END:variables
}
