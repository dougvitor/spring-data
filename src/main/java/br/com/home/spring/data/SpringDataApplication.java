package br.com.home.spring.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.home.spring.data.orm.Cargo;
import br.com.home.spring.data.repository.CargoRepository;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner{
	
	private final CargoRepository repository; 
	
	public SpringDataApplication(CargoRepository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cargo entity = new Cargo();
		entity.setDescricao("DESENVOLVEDOR DE SOFTWARE");
		
		this.repository.save(entity);
	}

}
