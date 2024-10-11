package io.github.ardonplay.tactmuzik.playlistservice.repository;

import io.github.ardonplay.tactmuzik.playlistservice.entity.PlaylistTrackEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface PlaylistTrackRepository extends
    ReactiveCassandraRepository<PlaylistTrackEntity, UUID> {

  Flux<PlaylistTrackEntity> findAllByIdAndTrackIdIn(UUID playlistId, List<UUID> trackIds);


  @Query("SELECT * FROM playlists_tracks WHERE playlist_id = :playlist_id")
  Flux<PlaylistTrackEntity> findAllByPlaylistId(@Param("playlist_id") UUID playlistId);


  @Query("SELECT * FROM playlist_tracks_by_track_id WHERE track_id = :track_id")
  Flux<PlaylistTrackEntity> findAllByTrackId(@Param("track_id") UUID trackId);
}
