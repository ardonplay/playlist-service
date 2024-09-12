package io.github.ardonplay.tactmuzik.playlistservice.repository;

import io.github.ardonplay.tactmuzik.playlistservice.entity.TrackEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface TrackRepository extends CassandraRepository<TrackEntity, UUID> {

  List<TrackEntity> findAllByIdIn(List<UUID> trackIds);
}
