package br.com.home.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import br.com.home.spring.data.orm.Funcionario;
import br.com.home.spring.data.repository.FuncionarioRepository;
import br.com.home.spring.data.specification.FuncionarioSpecification;

@Service
public class RelatorioFuncionarioDinamicoService {

	private final FuncionarioRepository repository;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatorioFuncionarioDinamicoService(FuncionarioRepository repository) {
		this.repository = repository;
	}
	
	public void inicial(Scanner scan) {
			System.out.println("\nDigite o nome");
			String nome = scan.next();
			
			if(nome.equalsIgnoreCase("NULL")) {
				nome = null;
			}
			
			System.out.println("\nDigite o CPF");
			String cpf = scan.next();
			
			if(cpf.equalsIgnoreCase("NULL")) {
				cpf = null;
			}
			
			System.out.println("\nDigite o salário");
			Double salario = scan.nextDouble();
			
			if(salario == 0) {
				salario = null;
			}
			
			System.out.println("\nDigite a data de contratação");
			String data = scan.next();
			LocalDate dataContratacao;
			
			if(data.equalsIgnoreCase("NULL")) {
				dataContratacao = null;
			}else {
				dataContratacao = LocalDate.parse(data, formatter);
			}
			
			List<Funcionario> funcionarios = repository.findAll(
					Specification.where(
							FuncionarioSpecification.nome(nome))
								.or(FuncionarioSpecification.cpf(cpf))
								.or(FuncionarioSpecification.salario(salario))
								.or(FuncionarioSpecification.dataContratacao(dataContratacao))
					);
			
			funcionarios.forEach(System.out::println);
					
	}
}
