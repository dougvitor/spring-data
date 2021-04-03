package br.com.home.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.home.spring.data.orm.Funcionario;
import br.com.home.spring.data.repository.FuncionarioRepository;
import br.com.home.spring.data.util.Menu;

@Service
public class RelatoriosService {
	
	private boolean execute = true;
	
	private final DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private FuncionarioRepository repository;
	
	public RelatoriosService(FuncionarioRepository repository) {
		this.repository = repository;
	}
	
	public void inicial(Scanner scan) {
		while(execute) {
			System.out.println("\nQual ação de cargo deseja executar?");
			Menu.exibirMenu(List.of("Sair", 
					"Busca funcionário por nome", 
					"Busca funcionário por nome, maior salário e data de contratação",
					"Busca funcionário por data de contratação"));
			int action = scan.nextInt();
			scan.nextLine();
			
			switch(action) {
				case 0 -> {
					execute = false;
					System.out.println("Execução do sistema finalizada!");
				}
				case 1 -> this.buscaFuncionarioByNome(scan);
				case 2 -> this.buscaFuncionarioByNomeMaiorSalarioDataContratacao(scan);
				case 3 -> this.buscaFuncionarioByDataContratacao(scan);
				default -> {
					execute = false;
					System.out.println("Opção informada inválida!\n");
				}
			}
		}
	}
	
	private void buscaFuncionarioByNome(Scanner scan) {
		System.out.println("Qual nome deseja pesquisar?");
		String nome = scan.next();
		List<Funcionario> funcionarios = repository.findByNome(nome);
		funcionarios.forEach(System.out::println);
	}
	
	private void buscaFuncionarioByNomeMaiorSalarioDataContratacao(Scanner scan) {
		System.out.println("Qual nome deseja pesquisar?");
		String nome = scan.next();
		
		System.out.println("Qual salário deseja pesquisar?");
		Double salario = scan.nextDouble();
		
		System.out.println("Qual data de contratação deseja pesquisar?");
		LocalDate dataContratacao = LocalDate.parse(scan.next(), formatter);
		
		List<Funcionario> funcionarios = repository.findPorNomeEMaiorSalarioEDataContratacao(nome, salario, dataContratacao);
		funcionarios.forEach(System.out::println);
	}
	
	private void buscaFuncionarioByDataContratacao(Scanner scan) {
		
		System.out.println("Qual data de contratação deseja pesquisar?");
		LocalDate dataContratacao = LocalDate.parse(scan.next(), formatter);
		
		List<Funcionario> funcionarios = repository.findPorMaiorDataContratacao(dataContratacao);
		funcionarios.forEach(System.out::println);
		
	}

}
