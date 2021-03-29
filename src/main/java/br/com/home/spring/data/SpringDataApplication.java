package br.com.home.spring.data;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.home.spring.data.service.CrudCargoService;
import br.com.home.spring.data.service.CrudFuncionarioService;
import br.com.home.spring.data.service.CrudUnidadeTrabalhoService;
import br.com.home.spring.data.util.Menu;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner{
	

	private final CrudCargoService cargoService;

	private final CrudFuncionarioService funcionarioService;

	private final CrudUnidadeTrabalhoService unidadeTrabalhoService; 
	
	public SpringDataApplication(CrudCargoService cargoService,
			CrudFuncionarioService funcionarioService, 
			CrudUnidadeTrabalhoService unidadeTrabalhoService) {
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Qual função deseja executar?");
		Menu.exibirMenu(List.of("Sair", "Funcionário", "Cargo", "Unidade"));
		int action = scan.nextInt();
		
		switch(action) {
			case 1 -> this.funcionarioService.inicial(scan);
			case 2 -> this.cargoService.inicial(scan);
			case 3 -> this.unidadeTrabalhoService.inicial(scan);
			default -> System.out.println("Execução do sistema finalizada!");
		}
	}
}
