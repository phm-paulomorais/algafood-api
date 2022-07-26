package com.algaworks.algafood.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

	// propriedades conforme especificação da RFC 7807( Problem Details for HTTP APIs) -  https://tools.ietf.org/html/rfc7807
	private Integer status;
	private String type;
	private String title;
	private String detail;
	
	// propriedades customizadas
	private String userMessage;
	private LocalDateTime timestamp;
	private List<Object> objects;
	
	
	@Getter
	@Builder
	public static class Object {
		
		private String name;
		private String userMessage;
		
	}
	
}