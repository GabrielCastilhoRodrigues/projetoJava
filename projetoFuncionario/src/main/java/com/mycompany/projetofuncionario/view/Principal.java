package com.mycompany.projetofuncionario.view;

import com.mycompany.projetofuncionario.control.OperacoesFuncionario;
import com.mycompany.projetofuncionario.model.Funcionario;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gabriel
 */
public class Principal extends javax.swing.JFrame {

    public static ArrayList<Funcionario> listaFuncionarios;
    private OperacoesFuncionario operacoes = new OperacoesFuncionario(this);
    private TextosDeTela textosDeTela = new TextosDeTela();

    public void configuraComboLista() {
        LoadTabelaFuncionario(comboLista.getSelectedIndex());
    }

    public void LoadTabelaFuncionario() {
        LoadTabelaFuncionario(0);
    }

    public void LoadTabelaFuncionario(int posicaoCombo) {
        Object colunas[] = null;
        DefaultTableModel modeloTabela = null;

        String format = "#,##0.00";
        NumberFormat formatSalario = new DecimalFormat(format);

        switch (posicaoCombo) {

            case 0: {
                colunas = new Object[]{
                    textosDeTela.funcionarioNome,
                    textosDeTela.funcionarioDataNascimento,
                    textosDeTela.funcionarioSalario,
                    textosDeTela.funcionarioFuncao};
                modeloTabela = new DefaultTableModel(colunas, 0);

                for (Funcionario funcionario : listaFuncionarios) {
                    String salarioFuncionario = formatSalario.format(
                            funcionario.getSalario().doubleValue());

                    Object linhas[]
                            = new Object[]{funcionario.getNome(),
                                funcionario.getDataNascimento().format(
                                        DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                salarioFuncionario,
                                funcionario.getFuncao()};

                    modeloTabela.addRow(linhas);
                }

                break;
            }

            case 1: {
                colunas = new Object[]{
                    textosDeTela.funcionarioNome,
                    textosDeTela.funcionarioDataNascimento,
                    textosDeTela.funcionarioSalario,
                    textosDeTela.funcionarioFuncao};
                modeloTabela = new DefaultTableModel(colunas, 0);

                ArrayList<Funcionario> listaPorFuncao
                        = operacoes.listaPorFuncao(listaFuncionarios);

                for (Funcionario funcionario : listaPorFuncao) {
                    String salarioFuncionario = formatSalario.format(
                            funcionario.getSalario().doubleValue());

                    Object linhas[] = new Object[]{funcionario.getNome(),
                        funcionario.getDataNascimento().format(
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        salarioFuncionario,
                        funcionario.getFuncao()};

                    modeloTabela.addRow(linhas);
                }

                break;
            }

            case 2: {
                colunas = new Object[]{
                    textosDeTela.funcionarioNome,
                    textosDeTela.funcionarioDataNascimento,
                    textosDeTela.funcionarioSalario,
                    textosDeTela.funcionarioFuncao};
                modeloTabela = new DefaultTableModel(colunas, 0);

                List<Funcionario> listaAniversario
                        = operacoes.listaAniversarios(listaFuncionarios);

                for (Funcionario funcionario : listaAniversario) {
                    String salarioFuncionario = formatSalario.format(
                            funcionario.getSalario().doubleValue());

                    Object linhas[] = new Object[]{funcionario.getNome(),
                        funcionario.getDataNascimento().format(
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        salarioFuncionario,
                        funcionario.getFuncao()};

                    modeloTabela.addRow(linhas);
                }

                break;
            }

            case 3: {
                Funcionario funcionarioMaisVelho
                        = operacoes.funcionarioMaiorIdade(listaFuncionarios);

                colunas = new Object[]{"Nome", "Idade"};
                modeloTabela = new DefaultTableModel(colunas, 0);

                if (funcionarioMaisVelho != null) {
                    LocalDate dataAtual = LocalDate.now();
                    Period periodo = Period.between(
                            funcionarioMaisVelho.getDataNascimento(),
                            dataAtual);

                    Object linhas[] = new Object[]{
                        funcionarioMaisVelho.getNome(),
                        periodo.getYears()};

                    modeloTabela.addRow(linhas);
                }

                break;

            }

            case 4: {
                colunas = new Object[]{
                    textosDeTela.funcionarioNome,
                    textosDeTela.funcionarioDataNascimento,
                    textosDeTela.funcionarioSalario,
                    textosDeTela.funcionarioFuncao};
                modeloTabela = new DefaultTableModel(colunas, 0);

                ArrayList<Funcionario> listaPorNome
                        = operacoes.listaPorNome(listaFuncionarios);

                for (Funcionario funcionario : listaPorNome) {
                    String salarioFuncionario = formatSalario.format(
                            funcionario.getSalario().doubleValue());

                    Object linhas[] = new Object[]{funcionario.getNome(),
                        funcionario.getDataNascimento().format(
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        salarioFuncionario,
                        funcionario.getFuncao()};

                    modeloTabela.addRow(linhas);
                }

                break;
            }

            case 5: {
                BigDecimal totalSalario
                        = operacoes.somaSalarios(listaFuncionarios);

                colunas = new Object[]{"Informação", "Valor"};
                modeloTabela = new DefaultTableModel(colunas, 0);
                Object linhas[] = new Object[]{"Total Salário", totalSalario};

                modeloTabela.addRow(linhas);

                break;
            }

            case 6: {
                ArrayList<Funcionario> novaLista
                        = operacoes.calculaSalarioMinimo(listaFuncionarios);

                colunas = new Object[]{"Nome", "Data Nascimento", "Salário",
                    "Função", "Qtde Salário Min."};
                modeloTabela = new DefaultTableModel(colunas, 0);

                for (Funcionario funcionario : novaLista) {
                    String salarioFuncionario = formatSalario.format(
                            funcionario.getSalario().doubleValue());

                    Object linhas[]
                            = new Object[]{funcionario.getNome(),
                                funcionario.getDataNascimento(),
                                salarioFuncionario,
                                funcionario.getFuncao(),
                                funcionario.getQtdeSalariosMinimos()};

                    modeloTabela.addRow(linhas);
                }

                break;
            }
        }

        tabelaFuncionarios.setModel(modeloTabela);
    }

    public void init() {
        listaFuncionarios = new ArrayList<>();

        btnCadastraFuncionario.setText(textosDeTela.cadastrar);
        btnRemoveFuncionario.setText(textosDeTela.remover);
        btnEditaFuncionario.setText(textosDeTela.editar);
        btnInsereAumento.setText(textosDeTela.atualizaSalario);
        lbOpcoesLista.setText(textosDeTela.filtroFuncionario);

        configuraComboLista();
    }

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        init();
    }

    /**
     * Exercicio 3.1
     */
    public void cadastraFuncionario() {
        CadastraFuncionario telaCadastro
                = new CadastraFuncionario(this, comboLista.getSelectedIndex());

        telaCadastro.setVisible(true);
        Principal.this.setVisible(false);
    }

    public void editaFuncionario() {
        if (tabelaFuncionarios.getSelectedRow() >= 0) {
            CadastraFuncionario telaCadastro
                    = new CadastraFuncionario(this,
                            comboLista.getSelectedIndex(),
                            listaFuncionarios.get(
                                    tabelaFuncionarios.getSelectedRow()));

            telaCadastro.setVisible(true);
            Principal.this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this,
                    textosDeTela.registroNaoSelecionado);
        }
    }

    public void removeFuncionario() {
        if (tabelaFuncionarios.getSelectedRow() != -1) {
            listaFuncionarios.remove(tabelaFuncionarios.getSelectedRow());
            LoadTabelaFuncionario();
        } else {
            JOptionPane.showMessageDialog(this,
                    textosDeTela.registroNaoSelecionado);
        }
    }

    public void atualizaSalarios() {
        if (!listaFuncionarios.isEmpty()) {
            operacoes.atualizaSalario(listaFuncionarios);
        } else {
            JOptionPane.showMessageDialog(this,
                    textosDeTela.naoExisteFuncionarioCadastrado);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFuncionarios = new javax.swing.JTable();
        btnRemoveFuncionario = new javax.swing.JButton();
        btnCadastraFuncionario = new javax.swing.JButton();
        lbOpcoesLista = new javax.swing.JLabel();
        comboLista = new javax.swing.JComboBox<>();
        btnInsereAumento = new javax.swing.JButton();
        btnEditaFuncionario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Principal");

        jScrollPane1.setBorder(null);

        tabelaFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "Data Nascimento", "Salario", "Função"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
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
        jScrollPane1.setViewportView(tabelaFuncionarios);

        btnRemoveFuncionario.setText("btnRemoveFuncionario");
        btnRemoveFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFuncionarioActionPerformed(evt);
            }
        });

        btnCadastraFuncionario.setText("btnCadastraFuncionario");
        btnCadastraFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastraFuncionarioActionPerformed(evt);
            }
        });

        lbOpcoesLista.setText("jLabel1");

        comboLista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Padrão", "Agrupar por função", "Aniversários - Mês 10 e 12", "Funcionário - Maior Idade", "Ordem Alfabética", "Funcionário - Soma total Salário", "Funcionário - Quantidade Salários  Minímos" }));
        comboLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboListaActionPerformed(evt);
            }
        });

        btnInsereAumento.setText("btnInsereAumento");
        btnInsereAumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsereAumentoActionPerformed(evt);
            }
        });

        btnEditaFuncionario.setText("btnEditaFuncionario");
        btnEditaFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditaFuncionarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbOpcoesLista)
                        .addGap(18, 18, 18)
                        .addComponent(comboLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCadastraFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnInsereAumento)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbOpcoesLista)
                    .addComponent(comboLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastraFuncionario)
                    .addComponent(btnRemoveFuncionario)
                    .addComponent(btnInsereAumento)
                    .addComponent(btnEditaFuncionario))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastraFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastraFuncionarioActionPerformed
        cadastraFuncionario();
    }//GEN-LAST:event_btnCadastraFuncionarioActionPerformed

    private void btnRemoveFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveFuncionarioActionPerformed
        removeFuncionario();
    }//GEN-LAST:event_btnRemoveFuncionarioActionPerformed

    private void btnInsereAumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsereAumentoActionPerformed
        atualizaSalarios();
    }//GEN-LAST:event_btnInsereAumentoActionPerformed

    private void comboListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboListaActionPerformed
        configuraComboLista();
    }//GEN-LAST:event_comboListaActionPerformed

    private void btnEditaFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditaFuncionarioActionPerformed
        editaFuncionario();
    }//GEN-LAST:event_btnEditaFuncionarioActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastraFuncionario;
    private javax.swing.JButton btnEditaFuncionario;
    private javax.swing.JButton btnInsereAumento;
    private javax.swing.JButton btnRemoveFuncionario;
    private javax.swing.JComboBox<String> comboLista;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbOpcoesLista;
    private javax.swing.JTable tabelaFuncionarios;
    // End of variables declaration//GEN-END:variables
}
