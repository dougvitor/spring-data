package br.com.home.spring.data.service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.home.spring.data.orm.Cargo;
import br.com.home.spring.data.repository.CargoRepository;
import br.com.home.spring.data.util.Menu;

@Service
public class CrudCargoService {

	private final CargoRepository repository;
	
	private boolean execute = true;
	
	public CrudCargoService(CargoRepository repository) {
		this.repository = repository;
	}
	
	public void inicial(Scanner scan) {
		while(execute) {
			System.out.println("\nQual ação de cargo deseja executar?");
			Menu.exibirMenu(List.of("Sair", "Salvar", "Atualizar", "Visualizar", "Excluir"));
			int action = scan.nextInt();
			scan.nextLine();
			
			switch(action) {
				case 0 -> {
					execute = false;
					System.out.println("Execução do sistema finalizada!");
				}
				case 1 -> this.salvar(scan);
				case 2 -> this.atualizar(scan);
				case 3 -> this.visualizar();
				case 4 -> this.excluir(scan);
				default -> {
					execute = false;
					System.out.println("Opção informada inválida!\n");
				}
			}
		}
	}
	
	private void salvar(Scanner scan) {
		System.out.println("\nDescrição do Cargo:");
		String descricao = scan.nextLine();
		
		Cargo entity = new Cargo();
		entity.setDescricao(descricao);
		
		this.repository.save(entity);
		System.out.println("Cargo salvo!\n");
	}
	
	private void atualizar(Scanner scan) {
		System.out.println("\nId do Cargo:");
		int id = scan.nextInt();
		System.out.println("Descrição do Cargo:");
		scan.nextLine();
		String descricao = scan.nextLine();
		
		Cargo entity = new Cargo();
		entity.setId(id);
		entity.setDescricao(descricao);
		
		this.repository.save(entity);
		System.out.println("Cargo atualizado!");
	}
	
	private void visualizar() {
		Iterable<Cargo> cargos = repository.findAll();
		if(cargos != null && cargos.iterator().hasNext()) {
			cargos.forEach(System.out::println);
		}
	}
	
	private void excluir(Scanner scan) {
		System.out.println("\nId do Cargo:");
		int id = scan.nextInt();
		
		Optional<Cargo> cargo = repository.findById(id);
		cargo.ifPresentOrElse(
				c -> {
					this.repository.delete(c);
					System.out.println("Cargo removido!");
				},
				()-> System.out.println("Cargo removido!")
			);
		
	}
}
