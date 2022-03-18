package com.mycompany.projetofuncionario.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Exercicio 2
 * Entidade referente ao Funcionário.
 */
public class Funcionario extends Pessoa{
    
    /**
     * Salário funcionário.
     */
    private BigDecimal salario;
    
    /**
     * Função do funcionário
     */
    private String funcao;
    
    ArrayList<Funcionario> listaFuncionario = new ArrayList<>();
    
    private BigDecimal qtdeSalariosMinimos;

    public Funcionario(BigDecimal salario, String funcao,
            String nome, LocalDate dataNascimento) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }
    
    public Funcionario(BigDecimal salario, String funcao, String nome,
            LocalDate dataNascimento, BigDecimal qtdeSalariosMinimos) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
        this.qtdeSalariosMinimos = qtdeSalariosMinimos;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    
    public ArrayList<Funcionario> retornaListaFuncionarios(){
        return listaFuncionario;
    }
  
    public void removeFuncionarioLista(Funcionario funcionario){
        listaFuncionario.remove(funcionario);
    }

    public BigDecimal getQtdeSalariosMinimos() {
        return qtdeSalariosMinimos;
    }

    public void setQtdeSalariosMinimos(BigDecimal qtdeSalariosMinimos) {
        this.qtdeSalariosMinimos = qtdeSalariosMinimos;
    }
}
