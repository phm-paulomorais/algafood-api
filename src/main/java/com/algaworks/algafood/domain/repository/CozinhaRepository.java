package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

	
	// Containing configura a consulta para que busque o nome de cozinha que contém a palavra informada (%nome%)
	//documentação: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	List<Cozinha> findTodasByNomeContaining(String nome);
	
	Optional<Cozinha> findByNome(String nome);
	
	boolean existsByNome(String nome);
	
	// List<Cozinha> listar();
	
	// Cozinha buscar(Long id);
	
	// Cozinha salvar(Cozinha cozinha);
	// void remover(Long id);
	
}