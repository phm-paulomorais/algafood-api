package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.server.ResponseStatusException;

// @ResponseStatus(value = HttpStatus.NOT_FOUND) //, reason = "Entidade não encontrada")
public abstract class EntidadeNaoEncontradaException extends NegocioException  {

	private static final long serialVersionUID = 1L;

//	public EntidadeNaoEncontradaException(HttpStatus status, String mensagem) {
//		super(status, mensagem);
//	}

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
}