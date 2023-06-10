package me.jorlowski.github_checker.tools;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

public class Utils {
	
	private static ObjectMapper objectMapper = getDefaultObjectMapper();
	private static WebClient webClient = getDefaultWebClient();

	private static ObjectMapper getDefaultObjectMapper() {
		ObjectMapper defaultObjectMapper = new ObjectMapper();
		return defaultObjectMapper;
	}
	
	private static WebClient getDefaultWebClient() {
		WebClient defaultWebClient = WebClient.builder().build();;
		return defaultWebClient;
	}

	public static JsonNode parse(String src) throws IOException {
		return objectMapper.readTree(src);
	}
	
	public static String fetch(String url) {
		return webClient
		.get()
		.uri(url)
		.retrieve()
		.bodyToMono(String.class)
		.onErrorResume(WebClientResponseException.class,
				e -> e.getStatusCode() == HttpStatus.NOT_FOUND ? Mono.empty() : Mono.error(e))
		.block();
	}
}
