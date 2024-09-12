package io.github.ardonplay.tactmuzik.playlistservice.repository;

import io.github.ardonplay.tactmuzik.playlistservice.entity.PlaylistEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaylistRepository extends CassandraRepository<PlaylistEntity, UUID> {

  @Query("SELECT * FROM playlists where id = :id")
  Optional<PlaylistEntity> findByPlaylistId(@Param("id") UUID id);

}
