package com.apirest.webflux.controllers;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.webflux.documents.Playlist;
import com.apirest.webflux.services.PlaylistService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
@RequestMapping(value = "/playlists")
public class PlaylistController {

	@Autowired
	private PlaylistService service;

	@GetMapping
	public Flux<Playlist> getAll() {
		return service.findAll();
	}

	@GetMapping(value = "/{id}")
	public Mono<Playlist> getById(@PathVariable String id) {
		return service.findById(id);
	}

	@PostMapping
	public Mono<Playlist> save(@RequestBody Playlist playlist) {
		return service.save(playlist);
	}
	
	//Exemplo usado para exibir ação assincrona e não bloqueante da programação reativa
	@GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, Playlist>> getEvents() {
		// Intervalo de cada resposta|stream que são enviados ao cliente 
		// Com diferença entre eles de 10 segundos
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
		
		// Fluxo de eventos do banco de dados
		Flux<Playlist> events = service.findAll();
		
		// Retorna para cada intervalo de tempo uma playlist de acordo com o fluxo
		return Flux.zip(interval, events);
	}
}
