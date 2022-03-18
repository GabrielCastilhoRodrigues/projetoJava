package com.mycompany.projetofuncionario.control;

import com.mycompany.projetofuncionario.model.Funcionario;
import com.mycompany.projetofuncionario.view.Principal;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author gabriel
 */
public class OperacoesFuncionario {

    private Principal principal;

    public OperacoesFuncionario() {
    }

    public OperacoesFuncionario(Principal principal) {
        this.principal = principal;
    }
    
    public void atualizaFuncionario(Funcionario funcionario, String nome,
            LocalDate dataNascimento, BigDecimal salario, String funcao){
        funcionario.setNome(nome);
        funcionario.setDataNascimento(dataNascimento);
        funcionario.setSalario(salario);
        funcionario.setFuncao(funcao);
    }
    
    public Funcionario insereFuncionario(String nome, LocalDate dataNascimento,
            BigDecimal salario, String funcao) {
        Funcionario novoFuncionario = new Funcionario(salario, funcao,
                nome, dataNascimento);

        return novoFuncionario;
    }

    public void atualizaSalario(ArrayList<Funcionario> funcionarios) {
        if (funcionarios != null) {
            for (Funcionario funcionario : funcionarios) {
                BigDecimal salario = funcionario.getSalario();
                BigDecimal porcAjusteSalario = new BigDecimal(0.10);

                funcionario.setSalario(salario.add(
                        salario.multiply(porcAjusteSalario))
                        .setScale(2, RoundingMode.HALF_EVEN));
            }

            principal.LoadTabelaFuncionario();
        }
    }

    public List<Funcionario>
            listaAniversarios(ArrayList<Funcionario> funcionarios) {

        if (!funcionarios.isEmpty()) {
            List<Funcionario> novaLista = new ArrayList<>(funcionarios);

            novaLista = novaLista.stream().filter(funcionario
                    -> funcionario.getDataNascimento().getMonthValue() == 10
                    || funcionario.getDataNascimento().getMonthValue() == 12)
                    .sorted((Funcionario func1, Funcionario func2)
                            -> func1.getDataNascimento()
                            .compareTo(func2.getDataNascimento()))
                    .collect(Collectors.toList());

            return novaLista;
        } else {
            return funcionarios;
        }
    }

    public ArrayList<Funcionario>
            listaPorFuncao(ArrayList<Funcionario> funcionarios) {

        if (funcionarios != null) {
            ArrayList<Funcionario> novaLista = new ArrayList<>(funcionarios);

            novaLista.sort((Funcionario func1, Funcionario func2)
                    -> func1.getFuncao().compareTo(func2.getFuncao()));

            return novaLista;
        } else {
            return funcionarios;
        }
    }

    public ArrayList<Funcionario>
            listaPorNome(ArrayList<Funcionario> funcionarios) {

        if (!funcionarios.isEmpty()) {
            ArrayList<Funcionario> novaLista = new ArrayList<>(funcionarios);
            novaLista.sort((Funcionario func1, Funcionario func2)
                    -> func1.getNome().compareTo(func2.getNome()));

            return novaLista;
        } else {
            return funcionarios;
        }
    }

    public BigDecimal somaSalarios(ArrayList<Funcionario> funcionarios) {
        if (!funcionarios.isEmpty()) {
            BigDecimal totalSalarios = BigDecimal.ZERO;

            for (Funcionario funcionario : funcionarios) {
                totalSalarios = totalSalarios.add(funcionario.getSalario());
            }

            return totalSalarios;
        } else {
            return BigDecimal.ZERO;
        }
    }

    public Funcionario
            funcionarioMaiorIdade(ArrayList<Funcionario> funcionarios) {

        Funcionario funcionarioMaisVelho = null;

        if (!funcionarios.isEmpty()) {
            int maiorIdade = 0;
            LocalDate dataAtual = LocalDate.now();
            Period periodo = null;

            for (Funcionario funcionario : funcionarios) {
                int idade = periodo.between(funcionario.getDataNascimento(),
                        dataAtual).getYears();

                if (maiorIdade < idade) {
                    maiorIdade = idade;
                    funcionarioMaisVelho = funcionario;
                }
            }
        }

        return funcionarioMaisVelho;
    }

    public ArrayList<Funcionario>
            calculaSalarioMinimo(ArrayList<Funcionario> funcionarios) {

        ArrayList<Funcionario> novaLista = new ArrayList<>();

        if (!funcionarios.isEmpty()) {
            for (Funcionario funcionario : funcionarios) {
                BigDecimal salario = funcionario.getSalario();
                BigDecimal valorSalarioMinimo = new BigDecimal(1212.00);
                BigDecimal salarioMinimo
                        = salario.divide(valorSalarioMinimo, RoundingMode.HALF_UP);

                funcionario.setQtdeSalariosMinimos(salarioMinimo);

                novaLista.add(funcionario);
            }
        }

        return novaLista;
    }
}
