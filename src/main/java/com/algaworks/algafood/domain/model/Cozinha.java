package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonRootName("cozinha") // customizando a representação do recurso (resposta do cliente da API) xml e/ou json
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@JsonIgnore // customizando a representação do recurso (resposta do cliente da API) xml e/ou json
//	@JsonProperty("titulo") // customizando a representação do recurso (resposta do cliente da API) xml e/ou json
	@Column(nullable = false)
	private String nome;

	
}