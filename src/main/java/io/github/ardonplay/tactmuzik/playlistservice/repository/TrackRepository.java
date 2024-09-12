package io.github.ardonplay.tactmuzik.playlistservice.repository;

import io.github.ardonplay.tactmuzik.playlistservice.entity.TrackEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;

public interface TrackRepository extends ReactiveCassandraRepository<TrackEntity, UUID> {

  Flux<TrackEntity> findAllByIdIn(List<UUID> trackIds);
}
