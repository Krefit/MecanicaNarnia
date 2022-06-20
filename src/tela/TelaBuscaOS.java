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

        getContentPane().setLayout(null);

        jFormattedTextFieldDataEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        getContentPane().add(jFormattedTextFieldDataEntrada);
        jFormattedTextFieldDataEntrada.setBounds(1064, 480, 149, 22);

        jLabel4.setText("Funcionario responsável: ");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(800, 540, 134, 16);

        jFormattedTextFieldPorcentagemDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        getContentPane().add(jFormattedTextFieldPorcentagemDesconto);
        jFormattedTextFieldPorcentagemDesconto.setBounds(1410, 550, 110, 22);

        jLabel1.setText("DEFEITO RELATADO");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(1234, 320, 116, 16);

        jTextAreaDefeitoRelatado.setColumns(20);
        jTextAreaDefeitoRelatado.setRows(5);
        jTextAreaDefeitoRelatado.setText("\n\t");
        jScrollPane1.setViewportView(jTextAreaDefeitoRelatado);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(944, 350, 577, 109);

        jLabel3.setText("DATA ABERTURA");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(954, 480, 90, 16);

        jLabel5.setText("PORCENTAGEM DE DESCONTO");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(1210, 550, 176, 16);

        jLabel6.setText("CHASSI");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(0, 156, 40, 16);
        getContentPane().add(jTextFieldChassi);
        jTextFieldChassi.setBounds(136, 153, 192, 22);

        jLabel7.setText("RENAVAM");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(0, 196, 56, 16);
        getContentPane().add(jTextFieldRenavam);
        jTextFieldRenavam.setBounds(136, 193, 192, 22);

        jLabel8.setText("TIPO DO VEÍCULO");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(0, 226, 95, 16);
        getContentPane().add(jTextFieldTipoDoVeiculo);
        jTextFieldTipoDoVeiculo.setBounds(136, 223, 192, 22);

        jLabel9.setText("PLACA");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(0, 266, 37, 16);
        getContentPane().add(jTextFieldPlaca);
        jTextFieldPlaca.setBounds(136, 263, 192, 22);

        jLabel10.setText("ANO DE FABRICAÇÃO");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(0, 306, 118, 16);
        getContentPane().add(jTextFieldAnoDeFabricacao);
        jTextFieldAnoDeFabricacao.setBounds(136, 303, 192, 22);

        jLabel11.setText("ANO DO MODELO");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(0, 355, 98, 16);
        getContentPane().add(jTextFieldAnoDoModelo);
        jTextFieldAnoDoModelo.setBounds(136, 352, 192, 22);

        jLabel13.setText("QUILOMETRAGEM");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(0, 402, 98, 16);
        getContentPane().add(jTextFieldQuilometragem);
        jTextFieldQuilometragem.setBounds(136, 399, 192, 22);

        jLabel14.setText("MARCA");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(0, 437, 51, 16);

        jLabel15.setText("MODELO");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(0, 472, 49, 16);
        getContentPane().add(jTextFieldModelo);
        jTextFieldModelo.setBounds(136, 469, 192, 22);
        getContentPane().add(jTextFieldMarca);
        jTextFieldMarca.setBounds(136, 434, 192, 22);

        jLabel16.setText("CÓDIGO");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(944, 200, 45, 16);
        getContentPane().add(jTextFieldCodigo);
        jTextFieldCodigo.setBounds(1074, 200, 156, 22);

        jLabel17.setText("VALOR UNITARIO");
        getContentPane().add(jLabel17);
        jLabel17.setBounds(944, 270, 93, 16);
        getContentPane().add(jTextFieldValorPeca);
        jTextFieldValorPeca.setBounds(1074, 270, 156, 22);

        jLabel18.setText("NOME");
        getContentPane().add(jLabel18);
        jLabel18.setBounds(1290, 200, 35, 16);
        getContentPane().add(jTextFieldNomePeca);
        jTextFieldNomePeca.setBounds(1350, 200, 154, 22);

        jLabel2.setText("Nome do serviço: ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(934, 110, 96, 16);
        getContentPane().add(jTextFieldNomeServico);
        jTextFieldNomeServico.setBounds(1034, 110, 173, 22);

        jLabel19.setText("Valor serviço: ");
        getContentPane().add(jLabel19);
        jLabel19.setBounds(1234, 110, 96, 16);

        jFormattedTextFieldValorMaoDeObra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        getContentPane().add(jFormattedTextFieldValorMaoDeObra);
        jFormattedTextFieldValorMaoDeObra.setBounds(1354, 110, 172, 22);

        jLabel20.setText("Especialidade: ");
        getContentPane().add(jLabel20);
        jLabel20.setBounds(606, 592, 77, 16);
        getContentPane().add(jTextFieldEspecialidade);
        jTextFieldEspecialidade.setBounds(686, 592, 370, 22);

        jLabel21.setText("Salario/hora: ");
        getContentPane().add(jLabel21);
        jLabel21.setBounds(606, 626, 70, 16);

        jLabel22.setText("Salario/mês:");
        getContentPane().add(jLabel22);
        jLabel22.setBounds(816, 636, 65, 16);

        jFormattedTextFieldSalarioMes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        getContentPane().add(jFormattedTextFieldSalarioMes);
        jFormattedTextFieldSalarioMes.setBounds(896, 626, 160, 22);

        jFormattedTextFieldSalarioHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextFieldSalarioHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldSalarioHoraActionPerformed(evt);
            }
        });
        getContentPane().add(jFormattedTextFieldSalarioHora);
        jFormattedTextFieldSalarioHora.setBounds(686, 626, 118, 22);

        jLabel23.setText("Matricula: ");
        getContentPane().add(jLabel23);
        jLabel23.setBounds(606, 660, 56, 16);

        jFormattedTextFieldMatricula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        getContentPane().add(jFormattedTextFieldMatricula);
        jFormattedTextFieldMatricula.setBounds(686, 656, 118, 22);
        getContentPane().add(tField_nomeFunc);
        tField_nomeFunc.setBounds(686, 556, 370, 22);
        getContentPane().add(tField_telefoneFunc);
        tField_telefoneFunc.setBounds(896, 655, 160, 22);

        jLabel24.setText("Nome: ");
        getContentPane().add(jLabel24);
        jLabel24.setBounds(606, 557, 39, 20);

        jLabel25.setText("Telefone: ");
        getContentPane().add(jLabel25);
        jLabel25.setBounds(816, 656, 51, 20);
        getContentPane().add(tField_telefone1);
        tField_telefone1.setBounds(700, 190, 200, 22);

        jLabel26.setText("Comercial:");
        getContentPane().add(jLabel26);
        jLabel26.setBounds(370, 220, 57, 20);
        getContentPane().add(tField_email);
        tField_email.setBounds(700, 220, 200, 22);
        getContentPane().add(tField_telefone2);
        tField_telefone2.setBounds(430, 220, 140, 22);

        buttonGroup1.add(jRadioButton_PessoaFisica);
        jRadioButton_PessoaFisica.setSelected(true);
        jRadioButton_PessoaFisica.setText("Pessoa Fisica");
        jRadioButton_PessoaFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_PessoaFisicaActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton_PessoaFisica);
        jRadioButton_PessoaFisica.setBounds(417, 88, 93, 25);

        jLabel_DtNasc_RazSoc.setText("Data de Nascimento:");
        getContentPane().add(jLabel_DtNasc_RazSoc);
        jLabel_DtNasc_RazSoc.setBounds(580, 160, 120, 16);

        buttonGroup1.add(jRadioButton_PessoaJuridica);
        jRadioButton_PessoaJuridica.setText("Pessoa Juridica");
        jRadioButton_PessoaJuridica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_PessoaJuridicaActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton_PessoaJuridica);
        jRadioButton_PessoaJuridica.setBounds(639, 88, 105, 25);
        getContentPane().add(tField_DataNascimento_nomeFantasia);
        tField_DataNascimento_nomeFantasia.setBounds(700, 160, 200, 22);

        jLabel27.setText("Logradouro");
        getContentPane().add(jLabel27);
        jLabel27.setBounds(360, 360, 70, 32);
        getContentPane().add(tField_Logradouro);
        tField_Logradouro.setBounds(430, 370, 470, 22);

        tField_Numero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tField_NumeroActionPerformed(evt);
            }
        });
        getContentPane().add(tField_Numero);
        tField_Numero.setBounds(430, 340, 140, 22);

        jLabel28.setText("Nº:");
        getContentPane().add(jLabel28);
        jLabel28.setBounds(370, 340, 32, 16);

        jLabel29.setText("Complemento:");
        getContentPane().add(jLabel29);
        jLabel29.setBounds(350, 410, 80, 16);
        getContentPane().add(tField_Complemento);
        tField_Complemento.setBounds(430, 410, 470, 22);

        jLabel30.setText("Bairro:");
        getContentPane().add(jLabel30);
        jLabel30.setBounds(370, 280, 36, 16);

        jLabel31.setText("Cidade:");
        getContentPane().add(jLabel31);
        jLabel31.setBounds(370, 250, 40, 16);

        jLabelNome_NomeFantasia.setText("Nome: ");
        getContentPane().add(jLabelNome_NomeFantasia);
        jLabelNome_NomeFantasia.setBounds(380, 130, 39, 20);
        getContentPane().add(tField_Bairro);
        tField_Bairro.setBounds(430, 280, 470, 22);
        getContentPane().add(tField_nome_razaoSocial);
        tField_nome_razaoSocial.setBounds(430, 130, 470, 22);
        getContentPane().add(tField_Cidade);
        tField_Cidade.setBounds(430, 250, 470, 22);

        jLabel_CPF_CNPJ.setText("CPF: ");
        getContentPane().add(jLabel_CPF_CNPJ);
        jLabel_CPF_CNPJ.setBounds(380, 160, 27, 20);

        jLabel33.setText("Estado:");
        getContentPane().add(jLabel33);
        jLabel33.setBounds(370, 310, 40, 16);
        getContentPane().add(tField_cpf_cnpj);
        tField_cpf_cnpj.setBounds(430, 160, 140, 22);

        jLabel34.setText("Celular:");
        getContentPane().add(jLabel34);
        jLabel34.setBounds(380, 190, 40, 20);

        jLabel35.setText("CEP:");
        getContentPane().add(jLabel35);
        jLabel35.setBounds(640, 340, 24, 16);
        getContentPane().add(tField_telefone3);
        tField_telefone3.setBounds(430, 190, 140, 22);
        getContentPane().add(tField_CEP);
        tField_CEP.setBounds(700, 340, 200, 22);

        jLabel36.setText("Tipo Logradouro:");
        getContentPane().add(jLabel36);
        jLabel36.setBounds(600, 310, 100, 20);

        jLabel37.setText("Residencial:");
        getContentPane().add(jLabel37);
        jLabel37.setBounds(620, 190, 62, 20);
        getContentPane().add(tField_TipoLogradouro);
        tField_TipoLogradouro.setBounds(700, 310, 200, 22);

        jLabel38.setText("Email:");
        getContentPane().add(jLabel38);
        jLabel38.setBounds(620, 220, 32, 16);
        getContentPane().add(jTextFieldEstado);
        jTextFieldEstado.setBounds(430, 310, 140, 22);

        jLabel32.setText("QUANTIDADE USADA");
        getContentPane().add(jLabel32);
        jLabel32.setBounds(944, 240, 114, 16);
        getContentPane().add(jTextFieldQuantidadeDePecasUsada);
        jTextFieldQuantidadeDePecasUsada.setBounds(1074, 240, 156, 22);

        jLabel39.setText("VALOR TOTAL");
        getContentPane().add(jLabel39);
        jLabel39.setBounds(1280, 270, 75, 16);
        getContentPane().add(jTextFieldValorPecasTotal);
        jTextFieldValorPecasTotal.setBounds(1364, 270, 154, 22);

        jFormattedTextFieldDataFinalizacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        getContentPane().add(jFormattedTextFieldDataFinalizacao);
        jFormattedTextFieldDataFinalizacao.setBounds(1364, 480, 160, 22);

        jLabel12.setText("DATA FINALIZAÇÃO");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(1234, 480, 108, 16);

        jLabel40.setText("PRODUTO USADO:");
        getContentPane().add(jLabel40);
        jLabel40.setBounds(1234, 170, 110, 16);

        jLabel41.setText("SERVIÇO EXECUTADO");
        getContentPane().add(jLabel41);
        jLabel41.setBounds(1214, 70, 130, 16);

        jFormattedTextFieldTotalSemDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        getContentPane().add(jFormattedTextFieldTotalSemDesconto);
        jFormattedTextFieldTotalSemDesconto.setBounds(1410, 640, 110, 22);

        jLabel42.setText("VALOR TOTAL DA NOTA SEM DESCONTO");
        getContentPane().add(jLabel42);
        jLabel42.setBounds(1160, 640, 230, 16);

        jFormattedTextFieldValorDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        getContentPane().add(jFormattedTextFieldValorDesconto);
        jFormattedTextFieldValorDesconto.setBounds(1410, 580, 110, 22);

        jLabel43.setText("VALOR DO DESCONTO");
        getContentPane().add(jLabel43);
        jLabel43.setBounds(1210, 580, 176, 16);

        jFormattedTextFieldTotalComDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        getContentPane().add(jFormattedTextFieldTotalComDesconto);
        jFormattedTextFieldTotalComDesconto.setBounds(1410, 670, 110, 22);

        jLabel44.setText("VALOR TOTAL DA NOTA COM DESCONTO");
        getContentPane().add(jLabel44);
        jLabel44.setBounds(1160, 670, 240, 16);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(924, 150, 670, 10);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(0, 530, 1570, 10);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(346, 30, 10, 500);
        getContentPane().add(jSeparator4);
        jSeparator4.setBounds(927, 310, 670, 10);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator5);
        jSeparator5.setBounds(924, 0, 19, 530);

        jLabel45.setText("VEICULO");
        getContentPane().add(jLabel45);
        jLabel45.setBounds(165, 119, 47, 16);

        buttonGroup2.add(jRadioButtonEmAberto);
        jRadioButtonEmAberto.setText("Esperando aprovação");
        getContentPane().add(jRadioButtonEmAberto);
        jRadioButtonEmAberto.setBounds(726, 721, 139, 25);

        buttonGroup2.add(jRadioButtonAprovado);
        jRadioButtonAprovado.setText("Aprovado");
        getContentPane().add(jRadioButtonAprovado);
        jRadioButtonAprovado.setBounds(885, 721, 77, 25);

        buttonGroup2.add(jRadioButtonCancelado);
        jRadioButtonCancelado.setText("Cancelado");
        getContentPane().add(jRadioButtonCancelado);
        jRadioButtonCancelado.setBounds(612, 721, 81, 25);

        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonConfirmar);
        jButtonConfirmar.setBounds(1167, 721, 87, 25);

        jLabelSituacao.setText("CODIGO ORDEM DE SERVIÇO");
        getContentPane().add(jLabelSituacao);
        jLabelSituacao.setBounds(0, 725, 160, 16);

        buttonGroup2.add(jRadioButtonConcluido);
        jRadioButtonConcluido.setText("Concluido");
        getContentPane().add(jRadioButtonConcluido);
        jRadioButtonConcluido.setBounds(982, 721, 81, 25);
        getContentPane().add(jTextFieldCodigoOS);
        jTextFieldCodigoOS.setBounds(179, 722, 420, 22);

        jButton1.setText("Editar OS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(1362, 721, 140, 25);

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

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(0, 541, 601, 146);

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
            Endereco e = f.getEndereco();
            if (e == null) {
                throw new Exception("Endereço do Funcionário não encontrado");
            }
            //  * settando dados de endereco
            tField_Bairro.setText(e.getBairro());
            tField_CEP.setText(e.getCEP());
            tField_Cidade.setText(e.getCidade());
            tField_Complemento.setText(e.getComplemento());
            tField_Logradouro.setText(e.getNumero());
            tField_Numero.setText(e.getNumero());
            tField_TipoLogradouro.setText(e.getTipoLogradouro());

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
            int index = jTableFuncionarios.getSelectedRow();
            if (index < 0) {//    * não clicou na tabela
                throw new Exception("Para editar, selecione na tabela qual o novo funcionário responsável pelo serviço");
            }
            ManipulaBancoFuncionario mb = new ManipulaBancoFuncionario();
            int idFuncionario = mb.buscar("" + jTableFuncionarios.getValueAt(index, 0), true);
            if (idFuncionario == 0) {
                throw new Exception("selecione, na tabela, qual funcionario deseja atribuir a essa OS");
            }
//            não implementou a troca de funcionario responsável ainda
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
