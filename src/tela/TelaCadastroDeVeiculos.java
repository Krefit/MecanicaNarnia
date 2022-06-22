/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import enumerations.TipoCliente;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        loadTableVeiculos();
    }

    private void loadTableVeiculos() {
        try {
            ArrayList<Veiculo> listaVeiculos = new ManipulaBancoVeiculo().buscarTodos();
            DefaultTableModel table = (DefaultTableModel) jTableListaVeiculos.getModel();
            table.setRowCount(0);
            if (listaVeiculos == null || listaVeiculos.isEmpty()) {// * caso a lista esteja vazia
                return;
            }
            ManipulaBancoMarca mbMarcas = new ManipulaBancoMarca();
            ManipulaBancoModelos mbModelos = new ManipulaBancoModelos();
            ManipulaBancoPessoaFisica mbPessoaFisica = new ManipulaBancoPessoaFisica();
            ManipulaBancoPessoaJuridica mbPessoaJuridica = new ManipulaBancoPessoaJuridica();
            for (Veiculo v : listaVeiculos) {
                MarcaVeiculo marca = mbMarcas.buscar(v.getIdMarca());
                if (marca == null) {
                    System.out.println(v);
                    throw new Exception("Marca não encontrada no banco de dados");
                }
                ModeloVeiculo modelo = mbModelos.buscar(v.getIdModelo());
                if (modelo == null) {
                    System.out.println(v);
                    throw new Exception("Modelo não encontrado no banco de dados");
                }
                if (v.getTipoCliente() == TipoCliente.PESSOA_FISICA) {
                    String[] dados = {
                        mbPessoaFisica.buscar(v.getIdDonoVeiculo()).getNome(),
                        v.getPlaca(),
                        v.getChassi(),
                        v.getRenavam(),
                        marca.getNomeMarca(),
                        modelo.getNomeModelo(),
                        "" + v.getTipoVeiculo(),
                        "" + v.getAnoFabricacao(),
                        "" + v.getAnoModelo()};
                    table.addRow(dados);
                } else if (v.getTipoCliente() == TipoCliente.PESSOA_JURIDICA) {
                    String[] dados = {
                        mbPessoaJuridica.buscar(v.getIdDonoVeiculo()).getRazaoSocial(),
                        v.getPlaca(),
                        v.getChassi(),
                        v.getRenavam(),
                        marca.getNomeMarca(),
                        modelo.getNomeModelo(),
                        "" + v.getTipoVeiculo(),
                        "" + v.getAnoFabricacao(),
                        "" + v.getAnoModelo()};
                    table.addRow(dados);

                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TelaCadastroDeVeiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTableVeiculos(ArrayList<Veiculo> listaVeiculos) {
        try {
            DefaultTableModel table = (DefaultTableModel) jTableListaVeiculos.getModel();
            table.setRowCount(0);
            if (listaVeiculos == null || listaVeiculos.isEmpty()) {// * caso a lista esteja vazia
                return;
            }
            ManipulaBancoMarca mbMarcas = new ManipulaBancoMarca();
            ManipulaBancoModelos mbModelos = new ManipulaBancoModelos();
            ManipulaBancoPessoaFisica mbPessoaFisica = new ManipulaBancoPessoaFisica();
            ManipulaBancoPessoaJuridica mbPessoaJuridica = new ManipulaBancoPessoaJuridica();
            for (Veiculo v : listaVeiculos) {
                MarcaVeiculo marca = mbMarcas.buscar(v.getIdMarca());
                if (marca == null) {
                    System.out.println(v);
                    throw new Exception("Marca não encontrada no banco de dados");
                }
                ModeloVeiculo modelo = mbModelos.buscar(v.getIdModelo());
                if (modelo == null) {
                    System.out.println(v);
                    throw new Exception("Modelo não encontrado no banco de dados");
                }
                if (v.getTipoCliente() == TipoCliente.PESSOA_FISICA) {
                    String[] dados = {
                        v.getPlaca(),
                        v.getChassi(),
                        v.getRenavam(),
                        marca.getNomeMarca(),
                        modelo.getNomeModelo(),
                        "" + v.getTipoVeiculo(),
                        "" + v.getAnoFabricacao(),
                        "" + v.getAnoModelo(),
                        mbPessoaFisica.buscar(v.getIdDonoVeiculo()).getNome()};
                    table.addRow(dados);
                } else if (v.getTipoCliente() == TipoCliente.PESSOA_JURIDICA) {
                    String[] dados = {
                        v.getPlaca(),
                        v.getChassi(),
                        v.getRenavam(),
                        marca.getNomeMarca(),
                        modelo.getNomeModelo(),
                        "" + v.getTipoVeiculo(),
                        "" + v.getAnoFabricacao(),
                        "" + v.getAnoModelo(),
                        mbPessoaJuridica.buscar(v.getIdDonoVeiculo()).getRazaoSocial()};
                    table.addRow(dados);

                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TelaCadastroDeVeiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void FiltraTableVeiculosPlaca(String filtro) throws Exception {
        ArrayList<Veiculo> listaVeiculos = new ManipulaBancoVeiculo().buscarTodos();
        ArrayList<Veiculo> listaVeiculosFiltrada = new ArrayList<>();
        if (listaVeiculos == null || listaVeiculos.isEmpty()) {// * caso a lista esteja vazia
            return;
        }
        for (Veiculo v : listaVeiculos) {
            if (v.getPlaca().toUpperCase().contains(filtro.toUpperCase())) {
                listaVeiculosFiltrada.add(v);
            }
        }
        loadTableVeiculos(listaVeiculosFiltrada);
    }

    private void FiltraTableVeiculosChassis(String filtro) throws Exception {
        ArrayList<Veiculo> listaVeiculos = new ManipulaBancoVeiculo().buscarTodos();
        ArrayList<Veiculo> listaVeiculosFiltrada = new ArrayList<>();
        if (listaVeiculos == null || listaVeiculos.isEmpty()) {// * caso a lista esteja vazia
            return;
        }
        for (Veiculo v : listaVeiculos) {// * filtrando tabela
            if (v.getChassi().replace(" ", "").toUpperCase().contains(filtro.replace(" ", "").toUpperCase())) {
                listaVeiculosFiltrada.add(v);
            }
        }
        loadTableVeiculos(listaVeiculosFiltrada);
    }

    private void FiltraTableVeiculosRenavan(String filtro) throws Exception {
        ArrayList<Veiculo> listaVeiculos = new ManipulaBancoVeiculo().buscarTodos();
        ArrayList<Veiculo> listaVeiculosFiltrada = new ArrayList<>();
        if (listaVeiculos == null || listaVeiculos.isEmpty()) {// * caso a lista esteja vazia
            return;
        }
        for (Veiculo v : listaVeiculos) {
            if (v.getRenavam().toUpperCase().contains(filtro.toUpperCase())) {
                listaVeiculosFiltrada.add(v);
            }
        }
        loadTableVeiculos(listaVeiculosFiltrada);
    }

    private void FiltraTableVeiculosNomeDono(String filtro) throws Exception {
        ArrayList<Veiculo> listaVeiculos = new ManipulaBancoVeiculo().buscarTodos();
        ArrayList<Veiculo> listaVeiculosFiltrada = new ArrayList<>();
        if (listaVeiculos == null || listaVeiculos.isEmpty()) {// * caso a lista esteja vazia
            return;
        }
        ManipulaBancoPessoaFisica mbPessoaFisica = new ManipulaBancoPessoaFisica();
        ManipulaBancoPessoaJuridica mbPessoaJuridica = new ManipulaBancoPessoaJuridica();
        for (Veiculo v : listaVeiculos) {
            if (v.getTipoCliente() == TipoCliente.PESSOA_FISICA) {
                if (mbPessoaFisica.buscar(v.getIdDonoVeiculo()).getNome().toUpperCase().contains(filtro.toUpperCase())) {
                    listaVeiculosFiltrada.add(v);
                }
            } else if (v.getTipoCliente() == TipoCliente.PESSOA_JURIDICA) {
                if (mbPessoaJuridica.buscar(v.getIdDonoVeiculo()).getRazaoSocial().toUpperCase().contains(filtro.toUpperCase())) {
                    listaVeiculosFiltrada.add(v);
                }
            }
        }
        loadTableVeiculos(listaVeiculosFiltrada);
    }

    private void FiltraTableVeiculosMarca(String filtro) throws Exception {
        ArrayList<Veiculo> listaVeiculos = new ManipulaBancoVeiculo().buscarTodos();
        ArrayList<Veiculo> listaVeiculosFiltrada = new ArrayList<>();
        if (listaVeiculos == null || listaVeiculos.isEmpty()) {// * caso a lista esteja vazia
            return;
        }
        ManipulaBancoMarca mbMarcas = new ManipulaBancoMarca();
        for (Veiculo v : listaVeiculos) {
            if (mbMarcas.buscar(v.getIdMarca()).getNomeMarca().toUpperCase().contains(filtro.toUpperCase())) {
                listaVeiculosFiltrada.add(v);
            }
        }
        loadTableVeiculos(listaVeiculosFiltrada);
    }

    private void FiltraTableVeiculosModelo(String filtro) throws Exception {
        ArrayList<Veiculo> listaVeiculos = new ManipulaBancoVeiculo().buscarTodos();
        ArrayList<Veiculo> listaVeiculosFiltrada = new ArrayList<>();
        if (listaVeiculos == null || listaVeiculos.isEmpty()) {// * caso a lista esteja vazia
            return;
        }
        ManipulaBancoModelos mbModelos = new ManipulaBancoModelos();
        for (Veiculo v : listaVeiculos) {
            if (mbModelos.buscar(v.getIdMarca()).getNomeModelo().toUpperCase().contains(filtro.toUpperCase())) {
                listaVeiculosFiltrada.add(v);
            }
        }
        loadTableVeiculos(listaVeiculosFiltrada);
    }

    private void FiltraTableVeiculosTipoVeiculo(String filtro) throws Exception {
        ArrayList<Veiculo> listaVeiculos = new ManipulaBancoVeiculo().buscarTodos();
        ArrayList<Veiculo> listaVeiculosFiltrada = new ArrayList<>();
        if (listaVeiculos == null || listaVeiculos.isEmpty()) {// * caso a lista esteja vazia
            return;
        }
        for (Veiculo v : listaVeiculos) {
            if (v.getTipoVeiculo().toUpperCase().contains(filtro.toUpperCase())) {
                listaVeiculosFiltrada.add(v);
            }
        }
        loadTableVeiculos(listaVeiculosFiltrada);
    }

    private void FiltraTableVeiculosAnoModelo(String filtro) throws Exception {
        ArrayList<Veiculo> listaVeiculos = new ManipulaBancoVeiculo().buscarTodos();
        ArrayList<Veiculo> listaVeiculosFiltrada = new ArrayList<>();
        if (listaVeiculos == null || listaVeiculos.isEmpty()) {// * caso a lista esteja vazia
            return;
        }
        for (Veiculo v : listaVeiculos) {
            if (("" + v.getAnoModelo()).toUpperCase().contains(filtro.toUpperCase())) {
                listaVeiculosFiltrada.add(v);
            }
        }
        loadTableVeiculos(listaVeiculosFiltrada);
    }

    private void FiltraTableVeiculosAnoFabricacao(String filtro) throws Exception {
        ArrayList<Veiculo> listaVeiculos = new ManipulaBancoVeiculo().buscarTodos();
        ArrayList<Veiculo> listaVeiculosFiltrada = new ArrayList<>();
        if (listaVeiculos == null || listaVeiculos.isEmpty()) {// * caso a lista esteja vazia
            return;
        }
        for (Veiculo v : listaVeiculos) {
            if (("" + v.getAnoFabricacao()).toUpperCase().contains(filtro.toUpperCase())) {
                listaVeiculosFiltrada.add(v);
            }
        }
        loadTableVeiculos(listaVeiculosFiltrada);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableListaVeiculos = new javax.swing.JTable();
        jButtonPesquisar = new javax.swing.JButton();
        jComboBoxTipoBusca = new javax.swing.JComboBox<>();
        jTextFieldFiltro = new javax.swing.JTextField();
        jButtonEditar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();

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
        jButtonCadastrarVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarVeiculoActionPerformed(evt);
            }
        });

        jLabel1.setText("CADASTRO DE VEÍCULO");

        jLabel11.setText("QUILOMETRAGEM");

        jLabel2.setText("TIPO DE CLIENTE");

        jLabel4.setText("CHASSI");

        buttonGroup1.add(jRadioButtonPessoaJuridica);
        jRadioButtonPessoaJuridica.setText("Pessoa jurídica");

        buttonGroup1.add(jRadioButtonPessoaFisica);
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

        jLabel12.setText("Razão social: ");

        jTextFieldBuscaRazaoSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscaRazaoSocialKeyReleased(evt);
            }
        });

        jTableListaVeiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dono do veiculo", "Placa", "Chassi", "Renavan", "Marca", "Modelo", "Tipo de veiculo", "Ano de fabricação", "Ano do modelo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableListaVeiculos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableListaVeiculosMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTableListaVeiculos);

        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        jComboBoxTipoBusca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Placa", "Chassi", "Renavan", "Nome do dono", "Modelo", "Marca", "Tipo de veiculo", "Ano de fabricação", "Ano do modelo" }));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1381, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonPesquisar)
                                .addGap(28, 28, 28)
                                .addComponent(jComboBoxTipoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(123, 123, 123)
                                .addComponent(jComboBoxMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(jComboBoxModelos, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(jLabel4)
                                .addGap(83, 83, 83)
                                .addComponent(jTextFieldChassi, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5)
                        .addComponent(jLabel5)
                        .addGap(47, 47, 47)
                        .addComponent(jTextFieldRenavam, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldTipoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldPlaca)
                                .addGap(7, 7, 7)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldAnoDeFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addGap(5, 5, 5)
                                .addComponent(jTextFieldAnoDoModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(jLabel11)
                                .addGap(22, 22, 22)
                                .addComponent(jTextFieldQuilometragem, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldDonoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87)
                                .addComponent(jTextFieldBuscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldBuscaRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(25, 25, 25)
                                .addComponent(jRadioButtonPessoaFisica)
                                .addGap(39, 39, 39)
                                .addComponent(jRadioButtonPessoaJuridica))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1381, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonCadastrarVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(315, 315, 315)
                                .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(744, 744, 744)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonPesquisar)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxTipoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxModelos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldChassi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldRenavam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTipoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldAnoDeFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8))
                                    .addComponent(jTextFieldAnoDoModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldQuilometragem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel11))))
                                .addGap(22, 22, 22)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldDonoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel3))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jTextFieldBuscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jTextFieldBuscaRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jRadioButtonPessoaFisica)
                                    .addComponent(jRadioButtonPessoaJuridica))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonRemover, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonCadastrarVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCadastrarVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarVeiculoActionPerformed
        try {
            TipoCliente tipoCliente = null;

            if (jTabelaClientes.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(null, "Selecione pelo o dono do veículo na tabela de clientes.");
                return;
            }
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
            loadTableVeiculos();
        } catch (Exception erro) {
            System.err.println(erro.getMessage());
            erro.printStackTrace();
            JOptionPane.showMessageDialog(this, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonCadastrarVeiculoActionPerformed

    private void jTextFieldBuscaRazaoSocialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscaRazaoSocialKeyReleased
        filtraTabelaRazaoSocial(jTextFieldBuscaRazaoSocial.getText());
    }//GEN-LAST:event_jTextFieldBuscaRazaoSocialKeyReleased

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
            jComboBoxModelos.setVisible(true);
        } else {
            jTextFieldModelo.setText("");
            jComboBoxModelos.setVisible(true);
        }
    }//GEN-LAST:event_jComboBoxModelosActionPerformed

    private void jTabelaClientesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaClientesMouseReleased
        jTextFieldDonoDoVeiculo.setText(String.valueOf(jTabelaClientes.getModel().getValueAt(jTabelaClientes.getSelectedRow(), 1)));
        if (jTabelaClientes.getValueAt(jTabelaClientes.getSelectedRow(), 0).equals(String.valueOf(TipoCliente.PESSOA_FISICA))) {
            jRadioButtonPessoaFisica.setSelected(true);
        } else if (jTabelaClientes.getValueAt(jTabelaClientes.getSelectedRow(), 0).equals(String.valueOf(TipoCliente.PESSOA_JURIDICA))) {
            jRadioButtonPessoaJuridica.setSelected(true);
        }
    }//GEN-LAST:event_jTabelaClientesMouseReleased

    private void jTabelaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaClientesMouseClicked
        jTextFieldDonoDoVeiculo.setText(String.valueOf(jTabelaClientes.getModel().getValueAt(jTabelaClientes.getSelectedRow(), 1)));
        if (jTabelaClientes.getValueAt(jTabelaClientes.getSelectedRow(), 0).equals(String.valueOf(TipoCliente.PESSOA_FISICA))) {
            jRadioButtonPessoaFisica.setSelected(true);
        } else if (jTabelaClientes.getValueAt(jTabelaClientes.getSelectedRow(), 0).equals(String.valueOf(TipoCliente.PESSOA_JURIDICA))) {
            jRadioButtonPessoaJuridica.setSelected(true);
        }
    }//GEN-LAST:event_jTabelaClientesMouseClicked

    private void jTextFieldBuscaNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscaNomeKeyReleased
        filtraTabelaNome(jTextFieldBuscaNome.getText());
    }//GEN-LAST:event_jTextFieldBuscaNomeKeyReleased

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        try {
            String filtro = jTextFieldFiltro.getText();
            switch (jComboBoxTipoBusca.getSelectedIndex()) {
                case 0:
                    FiltraTableVeiculosPlaca(filtro);
                    break;
                case 1:
                    FiltraTableVeiculosChassis(filtro);
                    break;
                case 2:
                    FiltraTableVeiculosRenavan(filtro);
                    break;
                case 3:
                    FiltraTableVeiculosNomeDono(filtro);
                    break;
                case 4:
                    FiltraTableVeiculosModelo(filtro);
                    break;
                case 5:
                    FiltraTableVeiculosMarca(filtro);
                    break;
                case 6:
                    FiltraTableVeiculosTipoVeiculo(filtro);
                    break;
                case 7:
                    FiltraTableVeiculosAnoFabricacao(filtro);
                    break;
                case 8:
                    FiltraTableVeiculosAnoModelo(filtro);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        try {
            int indexTabela = jTableListaVeiculos.getSelectedRow();
            if (indexTabela < 0) {
                JOptionPane.showMessageDialog(rootPane, "Selecione, na tabela qual cliente deseja editar");
            } else {

                String placa = "" + jTableListaVeiculos.getValueAt(indexTabela, 1);

                if (JOptionPane.showConfirmDialog(rootPane, "Editar veiculo com a placa: " + placa + "?") != 0) {// * não confirmou
                    return;
                }
                String nomeDono = jTextFieldDonoDoVeiculo.getText();
                if (jTabelaClientes.getSelectedRow() >= 0) {//    * a tabela foi clicada
                    nomeDono = "" + jTabelaClientes.getValueAt(jTabelaClientes.getSelectedRow(), 0);
                    if (JOptionPane.showConfirmDialog(rootPane, "Trocar o dono do veiculo para: " + nomeDono + "?") != 0) {//    * não confirmou a mudança
                        return;
                    }
                }
                ManipulaBancoVeiculo mb = new ManipulaBancoVeiculo();
                int idAntigo = mb.getID(mb.buscar(mb.buscar(placa)));

                TipoCliente tipoCliente = null;

                int idDonoVeiculo = 0;
                ManipulaBancoMarca mbd = new ManipulaBancoMarca();
                int idMarca = mbd.buscar(jTextFieldMarca.getText());
                ManipulaBancoModelos mbm = new ManipulaBancoModelos();
                int idModelo = mbm.buscar(jTextFieldModelo.getText());
                placa = jTextFieldPlaca.getText();
                String chassi = jTextFieldChassi.getText();
                String renavam = jTextFieldRenavam.getText();
                String tipoDoVeiculo = jTextFieldTipoDoVeiculo.getText();
                int anoDeFabricacao = Integer.parseInt(jTextFieldAnoDeFabricacao.getText());
                int anoDoModelo = Integer.parseInt(jTextFieldAnoDoModelo.getText());
                int quilometragem = Integer.parseInt(jTextFieldQuilometragem.getText());

                if (jRadioButtonPessoaFisica.isSelected()) {
                    ManipulaBancoPessoaFisica mbPF = new ManipulaBancoPessoaFisica();
                    idDonoVeiculo = mbPF.buscarPorNome(nomeDono);
                    tipoCliente = TipoCliente.PESSOA_FISICA;
                } else if (jRadioButtonPessoaJuridica.isSelected()) {
                    ManipulaBancoPessoaJuridica mbPJ = new ManipulaBancoPessoaJuridica();
                    idDonoVeiculo = mbPJ.buscarPorRazaoSocial(nomeDono);
                    tipoCliente = TipoCliente.PESSOA_JURIDICA;
                }
                if (idDonoVeiculo == 0) {
                    throw new Exception("Falha ao carregar dono do veiculo");
                }
                Veiculo v = new Veiculo(idModelo,
                        idMarca,
                        chassi,
                        renavam,
                        tipoDoVeiculo,
                        placa,
                        anoDeFabricacao,
                        anoDoModelo,
                        quilometragem,
                        idDonoVeiculo,
                        tipoCliente);

                mb.editar(idAntigo, v);

                loadTableVeiculos();
                JOptionPane.showMessageDialog(rootPane, "Editado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jTableListaVeiculosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaVeiculosMouseReleased
        try {
            int index = jTableListaVeiculos.getSelectedRow();
            if (index < 0) {//  * clique inválido
                return;
            }
            String placa = "" + jTableListaVeiculos.getValueAt(index, 1);
            ManipulaBancoVeiculo mbVeiculo = new ManipulaBancoVeiculo();
            ManipulaBancoMarca mbMarca = new ManipulaBancoMarca();
            ManipulaBancoModelos mbModelo = new ManipulaBancoModelos();
            Veiculo v = mbVeiculo.buscar(mbVeiculo.buscar(placa));
            if (v == null) {
                new Exception().printStackTrace();
                return;
            }

            jTextFieldAnoDeFabricacao.setText("" + v.getAnoFabricacao());
            jTextFieldAnoDoModelo.setText("" + v.getAnoModelo());
            jTextFieldQuilometragem.setText("" + v.getQuilometragem());
            jTextFieldChassi.setText(v.getChassi());
            jTextFieldPlaca.setText(v.getPlaca());
            jTextFieldRenavam.setText(v.getRenavam());
            jTextFieldTipoDoVeiculo.setText(v.getTipoVeiculo());
            jComboBoxMarcas.setSelectedItem(mbMarca.buscar(v.getIdMarca()).getNomeMarca());
            jComboBoxModelos.setSelectedItem(mbModelo.buscar(v.getIdModelo()).getNomeModelo());
            jTextFieldDonoDoVeiculo.setText("" + jTableListaVeiculos.getValueAt(index, 0));
            if (v.getTipoCliente() == TipoCliente.PESSOA_FISICA) {
                jRadioButtonPessoaFisica.setSelected(true);
            } else if (v.getTipoCliente() == TipoCliente.PESSOA_JURIDICA) {
                jRadioButtonPessoaJuridica.setSelected(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jTableListaVeiculosMouseReleased

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        try {
            int indexTabela = jTableListaVeiculos.getSelectedRow();
            if (indexTabela < 0) {
                JOptionPane.showMessageDialog(rootPane, "Selecione, na tabela qual cliente deseja editar");
            } else {
                ManipulaBancoVeiculo mb = new ManipulaBancoVeiculo();
                String placa = "" + jTableListaVeiculos.getValueAt(indexTabela, 1);
                int confirmação = JOptionPane.showConfirmDialog(rootPane, "Remover veiculo com a placa: " + placa + " ?");
                //  * 0 se for sim, 1 se for não, 2 se for cancelar

                if (confirmação == 0) {//   * caso tenha confirmado a exclusão
                    mb.remover(mb.buscar(placa));
                    loadTableVeiculos();
                    JOptionPane.showMessageDialog(rootPane, "Removido");
                } else {//    * cancelar
                    //  * pass
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jButtonRemoverActionPerformed

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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonCadastrarVeiculo;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JComboBox<String> jComboBoxMarcas;
    private javax.swing.JComboBox<String> jComboBoxModelos;
    private javax.swing.JComboBox<String> jComboBoxTipoBusca;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTabelaClientes;
    private javax.swing.JTable jTableListaVeiculos;
    private javax.swing.JTextField jTextFieldAnoDeFabricacao;
    private javax.swing.JTextField jTextFieldAnoDoModelo;
    private javax.swing.JTextField jTextFieldBuscaNome;
    private javax.swing.JTextField jTextFieldBuscaRazaoSocial;
    private javax.swing.JTextField jTextFieldChassi;
    private javax.swing.JTextField jTextFieldDonoDoVeiculo;
    private javax.swing.JTextField jTextFieldFiltro;
    private javax.swing.JTextField jTextFieldMarca;
    private javax.swing.JTextField jTextFieldModelo;
    private javax.swing.JTextField jTextFieldPlaca;
    private javax.swing.JTextField jTextFieldQuilometragem;
    private javax.swing.JTextField jTextFieldRenavam;
    private javax.swing.JTextField jTextFieldTipoDoVeiculo;
    // End of variables declaration//GEN-END:variables
}
