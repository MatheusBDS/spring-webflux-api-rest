//package com.apirest.webflux;
//
//import java.util.UUID;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.apirest.webflux.document.Playlist;
//import com.apirest.webflux.repository.PlaylistRepository;
//
//import reactor.core.publisher.Flux;
//
//@Component
//public class DummyData implements CommandLineRunner {
//
//	private final PlaylistRepository playlistRepository;
//
//	public DummyData(PlaylistRepository playlistRepository) {
//		this.playlistRepository = playlistRepository;
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		playlistRepository.deleteAll()
//			.thenMany(
//					Flux.just("API REST Spring Boot", "Deploy de uma aplicação java no IBM Cloud", "Java 11",
//							"Github", "Spring Security", "Web Service RESTFULL", "Bean no Spring Framework")
//						.map(nome -> Playlist.builder()
//												.id(UUID.randomUUID().toString())
//												.nome(nome)
//												.build())
//						.flatMap(playlistRepository::save))
//			.subscribe(System.out::println);
//	}
//}
