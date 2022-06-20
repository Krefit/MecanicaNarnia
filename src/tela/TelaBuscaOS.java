/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package tela;

import enumerations.TipoCliente;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Funcionario;
import modelos.OrdemDeServico;
import modelos.Peca;
import modelos.Pessoa;
import modelos.PessoaFisica;
import modelos.PessoaJuridica;
import modelos.Servico;
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
import persistencia.ManipulaBancoServicos;
import persistencia.ManipulaBancoVeiculo;

/**
 *
 * @author tanak
 */
public class TelaBuscaOS extends javax.swing.JInternalFrame {

    OrdemDeServico os = null;

    /**
     * Creates new form TelaBuscaOS
     */
    public TelaBuscaOS() {
        initComponents();
        try {
            loadDadosTela(new ManipulaBancoOrdemServico().buscar(2));
            loadTableFuncionarios();
        } catch (Exception ex) {
            Logger.getLogger(TelaBuscaOS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TelaBuscaOS(int idOS) {
        initComponents();
        try {
            this.os = (new ManipulaBancoOrdemServico().buscar(idOS));
            loadDadosTela(this.os);
            loadTableFuncionarios();
        } catch (Exception ex) {
            Logger.getLogger(TelaBuscaOS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TelaBuscaOS(OrdemDeServico os) {
        initComponents();
        this.os = os;
        try {
            loadDadosTela(os);
            loadTableFuncionarios();
        } catch (Exception ex) {
            Logger.getLogger(TelaBuscaOS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTableFuncionarios() {
        try {
            ArrayList<Funcionario> listaFuncionarios = new ManipulaBancoFuncionario().buscarTodos();
            DefaultTableModel table = (DefaultTableModel) jTableFuncionarios.getModel();
            table.setRowCount(0);// * apagando linhas para não duplicar os dados da tabela
            if (listaFuncionarios == null || listaFuncionarios.isEmpty()) {
                return;
            }
            for (Funcionario f : listaFuncionarios) {
                String[] dados = new String[jTableFuncionarios.getColumnCount()];
                dados[0] = "" + f.getMatricula();
                dados[1] = f.getNome();
                dados[2] = f.getCpf();
                dados[3] = f.getEspecialidade();
                table.addRow(dados);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void loadDadosTela(OrdemDeServico os) {
        try {
            jTextFieldCodigoOS.setEnabled(false);
//  * carregando dados de veículo
            Veiculo v = new ManipulaBancoVeiculo().buscar(os.getIdVeiculo());
            ModeloVeiculo modeloV = new ManipulaBancoModelos().buscar(v.getIdModelo());
            MarcaVeiculo marcaV = new ManipulaBancoMarca().buscar(v.getIdMarca());
            jTextFieldChassi.setText(v.getChassi());
            jTextFieldRenavam.setText(v.getRenavam());
            jTextFieldPlaca.setText(v.getPlaca());
            jTextFieldTipoDoVeiculo.setText(v.getTipoVeiculo());
            jTextFieldQuilometragem.setText("" + v.getQuilometragem());
            jTextFieldModelo.setText(modeloV.getNomeModelo());
            jTextFieldMarca.setText(marcaV.getNomeMarca());
            jTextFieldAnoDoModelo.setText("" + v.getAnoModelo());
            jTextFieldAnoDeFabricacao.setText("" + v.getAnoFabricacao());

//  * carregando dados de clientePF
            Pessoa cliente = null;
            if (v.getTipoCliente().equals(TipoCliente.PESSOA_FISICA)) {
//  * settando tipo de pessoa e atualizando a tela de acordo
                jRadioButton_PessoaFisica.setSelected(true);
                jRadioButton_PessoaFisicaActionPerformed(null);

//  * settando campos de texto
                PessoaFisica clientePF = new ManipulaBancoPessoaFisica().buscar(v.getIdDonoVeiculo());
                tField_nome_razaoSocial.setText(clientePF.getNome());
                tField_cpf_cnpj.setText(clientePF.getCpf());
                tField_DataNascimento_nomeFantasia.setText(new SimpleDateFormat("dd/MM/yyyy").format(clientePF.getDataNascimento()));
                cliente = clientePF;
            } else if (v.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA)) {
//  * settando tipo de pessoa e atualizando a tela de acordo
                jRadioButton_PessoaJuridica.setSelected(true);
                jRadioButton_PessoaJuridicaActionPerformed(null);

//  * settando campos de texto
                PessoaJuridica pj = new ManipulaBancoPessoaJuridica().buscar(v.getIdDonoVeiculo());
                tField_nome_razaoSocial.setText(pj.getRazaoSocial());
                tField_cpf_cnpj.setText(pj.getCnpj());
                tField_DataNascimento_nomeFantasia.setText(pj.getRazaoSocial());
                cliente = pj;
            } else {
                throw new IllegalStateException("tentando carregar os dados de um cliente que não é nem pessoa física nem jurídica, "
                        + "na telaBuscaOS, o tipo de cliente é validado com base no atributo presente no veiculo para o qual foi executada a OS"
                        + ", que está tentando ser carregada na tela, "
                        + "tentando carregar a OS:  " + this.os.toString());
            }

            Endereco eCliente = cliente.getEndereco();

//  * settando campos de Pessoa
            tField_email.setText(cliente.getEmail());
            tField_telefone1.setText(cliente.getTelefone()[0]);
            tField_telefone2.setText(cliente.getTelefone()[1]);
            tField_telefone3.setText(cliente.getTelefone()[2]);

//  * settando dados de endereço
            tField_Bairro.setText(eCliente.getBairro());
            tField_CEP.setText(eCliente.getCEP());
            tField_Cidade.setText(eCliente.getCidade());
            tField_Complemento.setText(eCliente.getComplemento());
            tField_Logradouro.setText(eCliente.getLogradouro());
            tField_Numero.setText(eCliente.getNumero());
            tField_TipoLogradouro.setText(eCliente.getTipoLogradouro());
            jTextFieldEstado.setText("" + eCliente.getEstado());

//  * settando dados de serviço
            Servico serv = new ManipulaBancoServicos().buscar(os.getIdServico());
            jTextFieldNomeServico.setText(serv.getNomeServico());
            jFormattedTextFieldValorMaoDeObra.setText(String.format("R$ %.2f", serv.getValorMaoDeObra()));

//  * settando dados de funcionario
            Funcionario f = new ManipulaBancoFuncionario().buscar(os.getIdFuncionarioResponsavel());
            tField_nomeFunc.setText(f.getNome());
            jTextFieldEspecialidade.setText(f.getEspecialidade());
            jFormattedTextFieldMatricula.setText("" + f.getMatricula());
            jFormattedTextFieldSalarioHora.setText(String.format("R$ %.2f", f.getSalariohora()));
            jFormattedTextFieldSalarioMes.setText(String.format("R$ %.2f", f.getSalarioMensal()));
            tField_telefoneFunc.setText(f.getTelefone()[0]);
//  * settando dados da peça
            if (os.getIdPeca() != 0) {//  * usa alguma peça
                Peca p = new ManipulaBancoPecas().buscar(os.getIdPeca());
                if (p == null) {
                    throw new Exception("Falha ao buscar Peça usada na OS");
                }
                jTextFieldValorPeca.setText(String.format("R$ %.2f", p.getValorPeca()));
                jTextFieldNomePeca.setText(p.getDescricao());
                jTextFieldCodigo.setText(p.getCodigoPeca());
                jTextFieldQuantidadeDePecasUsada.setText("" + os.getQuantidadePeca());
                jTextFieldValorPecasTotal.setText(String.format("R$ %.2f", (os.getQuantidadePeca() * p.getValorPeca())));
            } else {//     * não usará peças
                jTextFieldValorPeca.setText("---");
                jTextFieldNomePeca.setText("---");
                jTextFieldCodigo.setText("---");
            }
//  * settando dados  da OS
            jTextAreaDefeitoRelatado.setText(os.getDefeitoRelatado());
            jFormattedTextFieldDataEntrada.setText(new SimpleDateFormat("dd/MM/yyyy").format(os.getDataEntrada()));
            jFormattedTextFieldPorcentagemDesconto.setText(String.format("%.2f %s", os.getPorcentagemDesconto(), "%"));
            jFormattedTextFieldTotalSemDesconto.setText(String.format("R$ %.2f", os.calcularValorTotalSemDesconto()));
            jFormattedTextFieldTotalComDesconto.setText(String.format("R$ %.2f", os.calcularValorTotalComDesconto()));
            jFormattedTextFieldValorDesconto.setText(String.format("R$ %.2f", (os.calcularValorTotalSemDesconto() - os.calcularValorTotalComDesconto())));
            jTextFieldCodigoOS.setText("" + os.getCodigo());
            if (os.getDataSaida() != null) {
                jFormattedTextFieldDataFinalizacao.setText(new SimpleDateFormat("dd/MM/yyyy").format(os.getDataSaida()));
            }
            switch (os.getSituacao()) {
                case CANCELADA:
                    jRadioButtonCancelado.setSelected(true);
                    break;
                case CONCLUIDA:
                    jRadioButtonConcluido.setSelected(true);
                    break;
                case EM_ABERTO:
                    jRadioButtonEmAberto.setSelected(true);
                    break;
                case EM_EXECUCAO:
                    jRadioButtonAprovado.setSelected(true);
                    break;
                default:
                    throw new AssertionError("parametro inválido ao carregar dados sobre a situação da ordem de serviço");
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jFormattedTextFieldDataEntrada = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextFieldPorcentagemDesconto = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDefeitoRelatado = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldChassi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldRenavam = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldTipoDoVeiculo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldPlaca = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldAnoDeFabricacao = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldAnoDoModelo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldQuilometragem = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldModelo = new javax.swing.JTextField();
        jTextFieldMarca = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldCodigo = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldValorPeca = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldNomePeca = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNomeServico = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jFormattedTextFieldValorMaoDeObra = new javax.swing.JFormattedTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldEspecialidade = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jFormattedTextFieldSalarioMes = new javax.swing.JFormattedTextField();
        jFormattedTextFieldSalarioHora = new javax.swing.JFormattedTextField();
        jLabel23 = new javax.swing.JLabel();
        jFormattedTextFieldMatricula = new javax.swing.JFormattedTextField();
        tField_nomeFunc = new javax.swing.JTextField();
        tField_telefoneFunc = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        tField_telefone1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        tField_email = new javax.swing.JTextField();
        tField_telefone2 = new javax.swing.JTextField();
        jRadioButton_PessoaFisica = new javax.swing.JRadioButton();
        jLabel_DtNasc_RazSoc = new javax.swing.JLabel();
        jRadioButton_PessoaJuridica = new javax.swing.JRadioButton();
        tField_DataNascimento_nomeFantasia = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        tField_Logradouro = new javax.swing.JTextField();
        tField_Numero = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        tField_Complemento = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabelNome_NomeFantasia = new javax.swing.JLabel();
        tField_Bairro = new javax.swing.JTextField();
        tField_nome_razaoSocial = new javax.swing.JTextField();
        tField_Cidade = new javax.swing.JTextField();
        jLabel_CPF_CNPJ = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        tField_cpf_cnpj = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        tField_telefone3 = new javax.swing.JTextField();
        tField_CEP = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        tField_TipoLogradouro = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jTextFieldEstado = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextFieldQuantidadeDePecasUsada = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jTextFieldValorPecasTotal = new javax.swing.JTextField();
        jFormattedTextFieldDataFinalizacao = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jFormattedTextFieldTotalSemDesconto = new javax.swing.JFormattedTextField();
        jLabel42 = new javax.swing.JLabel();
        jFormattedTextFieldValorDesconto = new javax.swing.JFormattedTextField();
        jLabel43 = new javax.swing.JLabel();
        jFormattedTextFieldTotalComDesconto = new javax.swing.JFormattedTextField();
        jLabel44 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel45 = new javax.swing.JLabel();
        jRadioButtonEmAberto = new javax.swing.JRadioButton();
        jRadioButtonAprovado = new javax.swing.JRadioButton();
        jRadioButtonCancelado = new javax.swing.JRadioButton();
        jButtonConfirmar = new javax.swing.JButton();
        jLabelSituacao = new javax.swing.JLabel();
        jRadioButtonConcluido = new javax.swing.JRadioButton();
        jTextFieldCodigoOS = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableFuncionarios = new javax.swing.JTable();

        jFormattedTextFieldDataEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));

        jLabel4.setText("Funcionario responsável: ");

        jFormattedTextFieldPorcentagemDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));

        jLabel1.setText("DEFEITO RELATADO");

        jTextAreaDefeitoRelatado.setColumns(20);
        jTextAreaDefeitoRelatado.setRows(5);
        jTextAreaDefeitoRelatado.setText("\n\t");
        jScrollPane1.setViewportView(jTextAreaDefeitoRelatado);

        jLabel3.setText("DATA ABERTURA");

        jLabel5.setText("PORCENTAGEM DE DESCONTO");

        jLabel6.setText("CHASSI");

        jLabel7.setText("RENAVAM");

        jLabel8.setText("TIPO DO VEÍCULO");

        jLabel9.setText("PLACA");

        jLabel10.setText("ANO DE FABRICAÇÃO");

        jLabel11.setText("ANO DO MODELO");

        jLabel13.setText("QUILOMETRAGEM");

        jLabel14.setText("MARCA");

        jLabel15.setText("MODELO");

        jLabel16.setText("CÓDIGO");

        jLabel17.setText("VALOR UNITARIO");

        jLabel18.setText("NOME");

        jLabel2.setText("Nome do serviço: ");

        jLabel19.setText("Valor serviço: ");

        jFormattedTextFieldValorMaoDeObra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        jLabel20.setText("Especialidade: ");

        jLabel21.setText("Salario/hora: ");

        jLabel22.setText("Salario/mês:");

        jFormattedTextFieldSalarioMes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jFormattedTextFieldSalarioHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextFieldSalarioHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldSalarioHoraActionPerformed(evt);
            }
        });

        jLabel23.setText("Matricula: ");

        jFormattedTextFieldMatricula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        jLabel24.setText("Nome: ");

        jLabel25.setText("Telefone: ");

        jLabel26.setText("Comercial:");

        buttonGroup1.add(jRadioButton_PessoaFisica);
        jRadioButton_PessoaFisica.setSelected(true);
        jRadioButton_PessoaFisica.setText("Pessoa Fisica");
        jRadioButton_PessoaFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_PessoaFisicaActionPerformed(evt);
            }
        });

        jLabel_DtNasc_RazSoc.setText("Data de Nascimento:");

        buttonGroup1.add(jRadioButton_PessoaJuridica);
        jRadioButton_PessoaJuridica.setText("Pessoa Juridica");
        jRadioButton_PessoaJuridica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_PessoaJuridicaActionPerformed(evt);
            }
        });

        jLabel27.setText("Logradouro");

        tField_Numero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tField_NumeroActionPerformed(evt);
            }
        });

        jLabel28.setText("Nº:");

        jLabel29.setText("Complemento:");

        jLabel30.setText("Bairro:");

        jLabel31.setText("Cidade:");

        jLabelNome_NomeFantasia.setText("Nome: ");

        jLabel_CPF_CNPJ.setText("CPF: ");

        jLabel33.setText("Estado:");

        jLabel34.setText("Celular:");

        jLabel35.setText("CEP:");

        jLabel36.setText("Tipo Logradouro:");

        jLabel37.setText("Residencial:");

        jLabel38.setText("Email:");

        jLabel32.setText("QUANTIDADE USADA");

        jLabel39.setText("VALOR TOTAL");

        jFormattedTextFieldDataFinalizacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));

        jLabel12.setText("DATA FINALIZAÇÃO");

        jLabel40.setText("PRODUTO USADO:");

        jLabel41.setText("SERVIÇO EXECUTADO");

        jFormattedTextFieldTotalSemDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));

        jLabel42.setText("VALOR TOTAL DA NOTA SEM DESCONTO");

        jFormattedTextFieldValorDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));

        jLabel43.setText("VALOR DO DESCONTO");

        jFormattedTextFieldTotalComDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));

        jLabel44.setText("VALOR TOTAL DA NOTA COM DESCONTO");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel45.setText("VEICULO");

        buttonGroup2.add(jRadioButtonEmAberto);
        jRadioButtonEmAberto.setText("Esperando aprovação");

        buttonGroup2.add(jRadioButtonAprovado);
        jRadioButtonAprovado.setText("Aprovado");

        buttonGroup2.add(jRadioButtonCancelado);
        jRadioButtonCancelado.setText("Cancelado");

        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarActionPerformed(evt);
            }
        });

        jLabelSituacao.setText("CODIGO ORDEM DE SERVIÇO");

        buttonGroup2.add(jRadioButtonConcluido);
        jRadioButtonConcluido.setText("Concluido");

        jButton1.setText("Editar OS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTableFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricula", "Nome", "CPF", "Especialidade"
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
        jTableFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableFuncionariosMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTableFuncionarios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldChassi, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldRenavam, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTipoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldAnoDeFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldAnoDoModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldQuilometragem, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jRadioButton_PessoaFisica)
                                .addGap(137, 137, 137)
                                .addComponent(jRadioButton_PessoaJuridica))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel31)
                                .addGap(20, 20, 20)
                                .addComponent(tField_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(tField_Bairro, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jTextFieldEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tField_TipoLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tField_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(72, 72, 72)
                                        .addComponent(jLabel35)
                                        .addGap(36, 36, 36)
                                        .addComponent(tField_CEP, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tField_Logradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tField_Complemento, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(tField_telefone3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel37)
                                        .addGap(18, 18, 18)
                                        .addComponent(tField_telefone1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel38)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                        .addComponent(tField_email, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(tField_telefone2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel_CPF_CNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(23, 23, 23)
                                        .addComponent(tField_cpf_cnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel_DtNasc_RazSoc, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tField_DataNascimento_nomeFantasia))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelNome_NomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tField_nome_razaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)))))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(290, 290, 290)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(105, 105, 105)
                                                .addComponent(jFormattedTextFieldDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel12)
                                                .addGap(22, 22, 22)
                                                .addComponent(jFormattedTextFieldDataFinalizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addGap(130, 130, 130)
                                                            .addComponent(jTextFieldValorPeca, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(16, 16, 16)
                                                            .addComponent(jTextFieldQuantidadeDePecasUsada, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabel39)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextFieldValorPecasTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(jLabel16)
                                                    .addGap(85, 85, 85)
                                                    .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addGap(60, 60, 60)
                                                            .addComponent(jTextFieldNomePeca, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(290, 290, 290)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(239, 239, 239)
                                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(110, 110, 110)
                                        .addComponent(jTextFieldNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(jFormattedTextFieldValorMaoDeObra, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jTextFieldCodigoOS, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tField_nomeFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextFieldPorcentagemDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextFieldValorDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jFormattedTextFieldSalarioHora, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15)
                                        .addComponent(jFormattedTextFieldSalarioMes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jFormattedTextFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(tField_telefoneFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextFieldTotalSemDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextFieldTotalComDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButtonCancelado)
                        .addGap(39, 39, 39)
                        .addComponent(jRadioButtonEmAberto, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jRadioButtonAprovado, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jRadioButtonConcluido, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109)
                        .addComponent(jButtonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(411, 411, 411)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3)
                                            .addComponent(jFormattedTextFieldDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(17, 17, 17))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(jLabel45)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jTextFieldChassi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel6))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jTextFieldRenavam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel7))
                                                .addGap(8, 8, 8)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jTextFieldTipoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel8))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel9))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jTextFieldAnoDeFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(28, 28, 28)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jTextFieldAnoDoModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel11))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jTextFieldQuilometragem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel13))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel14))
                                                .addGap(8, 8, 8)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel15)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(414, 414, 414)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jFormattedTextFieldDataFinalizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel12))))
                                        .addGap(14, 14, 14)))
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addComponent(jLabel20))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(120, 120, 120)
                                        .addComponent(jLabel23))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(90, 90, 90)
                                        .addComponent(jLabel21))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(4, 4, 4)
                                        .addComponent(tField_nomeFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jFormattedTextFieldSalarioHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(jLabel22))
                                            .addComponent(jFormattedTextFieldSalarioMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(4, 4, 4)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jFormattedTextFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tField_telefoneFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel41)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jFormattedTextFieldValorMaoDeObra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel19))
                                                .addGap(18, 18, 18)
                                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jTextFieldNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel40)
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18)
                                    .addComponent(jTextFieldNomePeca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32)
                                    .addComponent(jTextFieldQuantidadeDePecasUsada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel39)
                                    .addComponent(jTextFieldValorPecasTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldValorPeca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))
                                .addGap(28, 28, 28)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jRadioButton_PessoaFisica)
                                            .addComponent(jRadioButton_PessoaJuridica))
                                        .addGap(14, 14, 14)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabelNome_NomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tField_nome_razaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(1, 1, 1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel_CPF_CNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tField_cpf_cnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(tField_DataNascimento_nomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel_DtNasc_RazSoc))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tField_telefone3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(tField_telefone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tField_telefone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel38))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(7, 7, 7)
                                                .addComponent(tField_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel31)
                                            .addComponent(tField_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(8, 8, 8)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel30)
                                            .addComponent(tField_Bairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(8, 8, 8)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel33)
                                            .addComponent(jTextFieldEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tField_TipoLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(8, 8, 8)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel28)
                                                .addGap(4, 4, 4)
                                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(tField_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel35)
                                                    .addComponent(tField_CEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(8, 8, 8)
                                                .addComponent(tField_Logradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel29)
                                            .addComponent(tField_Complemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel43))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jFormattedTextFieldPorcentagemDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jFormattedTextFieldValorDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel44))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jFormattedTextFieldTotalSemDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jFormattedTextFieldTotalComDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSituacao)
                    .addComponent(jTextFieldCodigoOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonCancelado)
                    .addComponent(jRadioButtonEmAberto)
                    .addComponent(jRadioButtonAprovado)
                    .addComponent(jRadioButtonConcluido)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonConfirmar)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFormattedTextFieldSalarioHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldSalarioHoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldSalarioHoraActionPerformed

    private void jRadioButton_PessoaFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_PessoaFisicaActionPerformed
        // TODO add your handling code here:
        jLabel_DtNasc_RazSoc.setText("Data Nascimento:");
        jLabel_CPF_CNPJ.setText("CPF:");
        tField_DataNascimento_nomeFantasia.setVisible(false);
    }//GEN-LAST:event_jRadioButton_PessoaFisicaActionPerformed

    private void jRadioButton_PessoaJuridicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_PessoaJuridicaActionPerformed
        // TODO add your handling code here:
        jLabel_DtNasc_RazSoc.setText("Razão Social:");
        jLabel_CPF_CNPJ.setText("CNPJ:");
        tField_DataNascimento_nomeFantasia.setVisible(true);
    }//GEN-LAST:event_jRadioButton_PessoaJuridicaActionPerformed

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed
        try {
            ManipulaBancoOrdemServico mb = new ManipulaBancoOrdemServico();
            int idOS = Integer.parseInt(jTextFieldCodigoOS.getText());
            OrdemDeServico os = mb.buscar(idOS);
            if (jRadioButtonEmAberto.isSelected()) {
                os.setSituacao(OrdemDeServico.SituacaoOrdemServico.EM_ABERTO);
            } else if (jRadioButtonCancelado.isSelected()) {
                os.setSituacao(OrdemDeServico.SituacaoOrdemServico.CANCELADA);
            } else if (jRadioButtonAprovado.isSelected()) {
                os.setSituacao(OrdemDeServico.SituacaoOrdemServico.EM_EXECUCAO);
            } else if (jRadioButtonConcluido.isSelected()) {
                os.setSituacao(OrdemDeServico.SituacaoOrdemServico.CONCLUIDA);
            }
            mb.editar(mb.getID(os), os);//  * editando o banco de dados
            this.os = os;
            JOptionPane.showMessageDialog(rootPane, "Atualizado");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
        loadDadosTela(this.os);
    }//GEN-LAST:event_jButtonConfirmarActionPerformed

    private void jTableFuncionariosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFuncionariosMouseReleased
        try {
            int index = jTableFuncionarios.getSelectedRow();
            if (index < 0) {///  * cllique inválido
                return;
            }
            String matricula = "" + jTableFuncionarios.getValueAt(index, 0);
            ManipulaBancoFuncionario mb = new ManipulaBancoFuncionario();
            int id = mb.buscar(matricula);
            Funcionario f = mb.buscar(id);

            if (f == null) {
                System.out.println(index);
                System.out.println(id);
                System.out.println(matricula);
                throw new Exception("Funcionário não encontrado");
            }

            //  * settando dados do funcionario
            tField_nomeFunc.setText(f.getNome());
            tField_telefoneFunc.setText(f.getTelefone()[0]);
            jTextFieldEspecialidade.setText(f.getEspecialidade());
            jFormattedTextFieldMatricula.setText(matricula);
            jFormattedTextFieldSalarioHora.setText(String.format("%.2f", f.getSalariohora()));
            jFormattedTextFieldSalarioMes.setText(String.format("%.2f", f.getSalarioMensal()));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jTableFuncionariosMouseReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//  * pegando dados do funcionário
        try {
            if (os.getSituacao() == OrdemDeServico.SituacaoOrdemServico.CANCELADA || os.getSituacao() == OrdemDeServico.SituacaoOrdemServico.CONCLUIDA) {
                throw new Exception("Esta Ordem de serviço não pode ser editada");
            }
            int index = jTableFuncionarios.getSelectedRow();
            if (index < 0) {//    * não clicou na tabela
                throw new Exception("Para editar, selecione na tabela qual o novo funcionário responsável pelo serviço");
            }
            ManipulaBancoFuncionario mb = new ManipulaBancoFuncionario();
            int idFuncionario = mb.buscar("" + jTableFuncionarios.getValueAt(index, 0));
            if (idFuncionario == 0) {
                throw new Exception("Selecione, na tabela, qual funcionário será responsável pelo serviço");
            }
            os.setIdFuncionarioResponsavel(idFuncionario);
            ManipulaBancoOrdemServico mbOs = new ManipulaBancoOrdemServico();
            mbOs.editar(mbOs.getID(os), os);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tField_NumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tField_NumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tField_NumeroActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataEntrada;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataFinalizacao;
    private javax.swing.JFormattedTextField jFormattedTextFieldMatricula;
    private javax.swing.JFormattedTextField jFormattedTextFieldPorcentagemDesconto;
    private javax.swing.JFormattedTextField jFormattedTextFieldSalarioHora;
    private javax.swing.JFormattedTextField jFormattedTextFieldSalarioMes;
    private javax.swing.JFormattedTextField jFormattedTextFieldTotalComDesconto;
    private javax.swing.JFormattedTextField jFormattedTextFieldTotalSemDesconto;
    private javax.swing.JFormattedTextField jFormattedTextFieldValorDesconto;
    private javax.swing.JFormattedTextField jFormattedTextFieldValorMaoDeObra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelNome_NomeFantasia;
    private javax.swing.JLabel jLabelSituacao;
    private javax.swing.JLabel jLabel_CPF_CNPJ;
    private javax.swing.JLabel jLabel_DtNasc_RazSoc;
    private javax.swing.JRadioButton jRadioButtonAprovado;
    private javax.swing.JRadioButton jRadioButtonCancelado;
    private javax.swing.JRadioButton jRadioButtonConcluido;
    private javax.swing.JRadioButton jRadioButtonEmAberto;
    private javax.swing.JRadioButton jRadioButton_PessoaFisica;
    private javax.swing.JRadioButton jRadioButton_PessoaJuridica;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTableFuncionarios;
    private javax.swing.JTextArea jTextAreaDefeitoRelatado;
    private javax.swing.JTextField jTextFieldAnoDeFabricacao;
    private javax.swing.JTextField jTextFieldAnoDoModelo;
    private javax.swing.JTextField jTextFieldChassi;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldCodigoOS;
    private javax.swing.JTextField jTextFieldEspecialidade;
    private javax.swing.JTextField jTextFieldEstado;
    private javax.swing.JTextField jTextFieldMarca;
    private javax.swing.JTextField jTextFieldModelo;
    private javax.swing.JTextField jTextFieldNomePeca;
    private javax.swing.JTextField jTextFieldNomeServico;
    private javax.swing.JTextField jTextFieldPlaca;
    private javax.swing.JTextField jTextFieldQuantidadeDePecasUsada;
    private javax.swing.JTextField jTextFieldQuilometragem;
    private javax.swing.JTextField jTextFieldRenavam;
    private javax.swing.JTextField jTextFieldTipoDoVeiculo;
    private javax.swing.JTextField jTextFieldValorPeca;
    private javax.swing.JTextField jTextFieldValorPecasTotal;
    private javax.swing.JTextField tField_Bairro;
    private javax.swing.JTextField tField_CEP;
    private javax.swing.JTextField tField_Cidade;
    private javax.swing.JTextField tField_Complemento;
    private javax.swing.JTextField tField_DataNascimento_nomeFantasia;
    private javax.swing.JTextField tField_Logradouro;
    private javax.swing.JTextField tField_Numero;
    private javax.swing.JTextField tField_TipoLogradouro;
    private javax.swing.JTextField tField_cpf_cnpj;
    private javax.swing.JTextField tField_email;
    private javax.swing.JTextField tField_nomeFunc;
    private javax.swing.JTextField tField_nome_razaoSocial;
    private javax.swing.JTextField tField_telefone1;
    private javax.swing.JTextField tField_telefone2;
    private javax.swing.JTextField tField_telefone3;
    private javax.swing.JTextField tField_telefoneFunc;
    // End of variables declaration//GEN-END:variables
}
