package io.github.ardonplay.tactmuzik.playlistservice.repository;

import io.github.ardonplay.tactmuzik.playlistservice.entity.PlaylistTrackEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaylistTrackRepository extends CassandraRepository<PlaylistTrackEntity, UUID> {

  List<PlaylistTrackEntity> findAllByIdAndTrackIdIn(UUID playlistId, List<UUID> trackIds);


  @Query("SELECT * FROM playlists_tracks WHERE playlist_id = :playlist_id")
  List<PlaylistTrackEntity> findAllByPlaylistId(@Param("playlist_id") UUID playlistId);
}
