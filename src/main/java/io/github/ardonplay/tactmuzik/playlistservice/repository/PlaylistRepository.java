package io.github.ardonplay.tactmuzik.playlistservice.repository;

import io.github.ardonplay.tactmuzik.playlistservice.entity.PlaylistEntity;
import java.util.UUID;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlaylistRepository extends ReactiveCassandraRepository<PlaylistEntity, UUID> {

  @Query("SELECT * FROM playlists where id = :id")
  Mono<PlaylistEntity> findByPlaylistId(@Param("id") UUID id);

  @Query("SELECT * FROM playlists_by_user_id WHERE user_id = :user_id")
  Flux<PlaylistEntity> findAllByUserId(@Param("user_id") UUID userId);

}
