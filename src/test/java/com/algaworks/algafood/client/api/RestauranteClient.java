package com.algaworks.algafood.client.api;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.algaworks.algafood.api.v1.model.RestauranteApenasNomeModel;
import com.algaworks.algafood.api.v1.model.RestauranteModel;
import com.algaworks.algafood.api.v1.model.input.RestauranteInput;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestauranteClient {

	private static final String RESOURCE_PATH = "/restaurantes";
	
	private RestTemplate restTemplate;
	private String url;
	
	public List<RestauranteApenasNomeModel> listar() {
		try {
			URI resourceUri = URI.create(url + RESOURCE_PATH);
			
			//RestauranteResumoModel[] restaurantes = restTemplate
			//		.getForObject(resourceUri, RestauranteResumoModel[].class);
			
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", "application/json");

			HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

			ResponseEntity<RestauranteApenasNomeModel[]> restaurantes = restTemplate.exchange(
					resourceUri, HttpMethod.GET, requestEntity, RestauranteApenasNomeModel[].class);
			
			
			return Arrays.asList(restaurantes.getBody());
		} catch (RestClientResponseException e) {
			throw new ClientApiException(e.getMessage(), e);
		}
	}
	
	public RestauranteModel adicionar(RestauranteInput restaurante) {
		var resourceUri = URI.create(url + RESOURCE_PATH);
		
		try {
			return restTemplate
					.postForObject(resourceUri, restaurante, RestauranteModel.class);
		} catch (HttpClientErrorException e) {
			throw new ClientApiException(e.getMessage(), e);
		}
	}
	
}