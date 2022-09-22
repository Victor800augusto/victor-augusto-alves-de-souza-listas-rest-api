package com.listas.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class openAPIConfig {

	@Bean
	public OpenAPI springShopOpenApi() {
		OpenAPI openApi = new OpenAPI();
		
		Info info = new Info(); 
		info.title("Avaliação 03 - Victor Augusto Alves de Souza"); 
		info.version("v0.0.1"); 
		openApi.info(info);
		
		openApi.addTagsItem(new Tag().name("Lista").description("Gerencia as listas do sistema"));
		openApi.addTagsItem(new Tag().name("Item").description("Gerencia as itens do sistema"));
		
		info.description("Projeto da avaliação 03"); 
		
		return openApi;
	}
}
