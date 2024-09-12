package io.github.ardonplay.tactmuzik.playlistservice.repository;

import io.github.ardonplay.tactmuzik.playlistservice.entity.PlaylistByuserIdEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaylistByUserIdRepository extends CassandraRepository<PlaylistByuserIdEntity, UUID> {
  @Query("SELECT * FROM playlists_by_user_id WHERE user_id = :user_id")
  List<PlaylistByuserIdEntity> findAllByUserId(@Param("user_id") UUID userId);
}
