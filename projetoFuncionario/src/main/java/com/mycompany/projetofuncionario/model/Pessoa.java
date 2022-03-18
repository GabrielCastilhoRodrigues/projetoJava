package com.mycompany.projetofuncionario.model;

import java.time.LocalDate;

/**
 * Exercício 1
 * Entidade referente a Pessoa.
 */
public class Pessoa {
    
    /**
     * Nome da Pessoa.
     */
    private String nome;
    
    /**
     * Data de Nascimento da Pessoa
     */
    private LocalDate dataNascimento;

    /**
     * Construtor para Pessoa.
     * 
     * @param nome
     * @param dataNascimento 
     */
    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
