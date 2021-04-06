package br.com.home.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.home.spring.data.orm.Funcionario;
import br.com.home.spring.data.orm.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {
	
	List<Funcionario> findByNome(String nome);
	
	List<Funcionario> findByUnidadeTrabalhos_Descricao(String descricao);
	
	List<Funcionario> findByNomeAndSalarioGreaterThanAndDataContratacao(String nome, Double salario, LocalDate dataContratacao);
	
	@Query("""
			SELECT f FROM Funcionario f 
			WHERE f.nome = :nome
			AND f.salario >= :salario 
			AND f.dataContratacao = :dataContratacao
		"""
	)
	List<Funcionario> findPorNomeEMaiorSalarioEDataContratacao(String nome, Double salario, LocalDate dataContratacao);
	
	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data", nativeQuery = true)
	List<Funcionario> findPorMaiorDataContratacao(LocalDate data);
	
	@Query(value = "SELECT f.id, f.nome, f.salario, c.descricao as cargoDescricao FROM funcionarios f join cargos c WHERE c.id = f.cargo_id ", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionariosSalarios();
	
}
