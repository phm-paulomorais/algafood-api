package com.algaworks.algafood.domain.repository;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, 
												JpaSpecificationExecutor<Restaurante> {
	
	// Errata: se um restaurante não tiver nenhuma forma de pagamento associada a ele,
	// esse restaurante não será retornado usando JOIN FETCH r.formasPagamento.
	// Para resolver isso, temos que usar LEFT JOIN FETCH r.formasPagamento
	//	@Query("from Restaurante r join fetch r.cozinha join fetch r.formasPagamento")
	// "select distinct r from Restaurante r join fetch r.cozinha left join fetch r.formasPagamento"
	@Query("select distinct r from Restaurante r join fetch r.cozinha")
	List<Restaurante> findAll();
	
	// Between configura para pesquisa taxa entre os valores de taxa inicial e taxa final
	// documentação: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	//@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	// Adicionada consulta no arquivo orm.xml na pasta META-INF
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);
	
	// prefixo findBy, sufixo Containing, And
	//List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);
	
	// prefixo findFirst
	Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);
	
	// prefixo findTop2By
	List<Restaurante> findTop2ByNomeContaining(String nome);
	
	// prefixo countBy
	int countByCozinhaId(Long cozinha);
	
	boolean existsResponsavel(Long restauranteId, Long usuarioId);
}
