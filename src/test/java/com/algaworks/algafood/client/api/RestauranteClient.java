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

import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.RestauranteResumoModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestauranteClient {

	private static final String RESOURCE_PATH = "/restaurantes";
	
	private RestTemplate restTemplate;
	private String url;
	
	public List<RestauranteResumoModel> listar() {
		try {
			URI resourceUri = URI.create(url + RESOURCE_PATH);
			
			//RestauranteResumoModel[] restaurantes = restTemplate
			//		.getForObject(resourceUri, RestauranteResumoModel[].class);
			
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", "application/json");

			HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

			ResponseEntity<RestauranteResumoModel[]> restaurantes = restTemplate.exchange(
					resourceUri, HttpMethod.GET, requestEntity, RestauranteResumoModel[].class);
			
			
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