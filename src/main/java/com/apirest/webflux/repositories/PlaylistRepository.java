package com.apirest.webflux.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.apirest.webflux.documents.Playlist;

public interface PlaylistRepository extends ReactiveMongoRepository<Playlist, String>{

}
