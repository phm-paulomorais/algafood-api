package com.algaworks.algafood.core.web;

import jakarta.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

// Não precisa mais dessa configuração, pois a mesma está configurada na classe
// CorsConfig.java, a configuração de Cors foi definida tanto para o Resource Server quanto
// para o Authorization Server, conforme explicado na aula: 23.41. Juntando o Resource Server com o Authorization Server no mesmo projeto
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//			.allowedMethods("*");
//			.allowedOrigins("*")
//			.maxAge(30);
//	}
	
	@Bean // Aula 17.5. Implementando requisições condicionais com Shallow ETags
	public Filter shallowEtagHeaderFilter() {
		return new ShallowEtagHeaderFilter();
	}
	
}