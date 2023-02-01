package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.algaworks.algafood.api.v1.model.PermissaoModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Permissões")
public interface PermissaoControllerOpenApi {

	@Operation(summary = "Lista as permissões")
	CollectionModel<PermissaoModel> listar();
	
}