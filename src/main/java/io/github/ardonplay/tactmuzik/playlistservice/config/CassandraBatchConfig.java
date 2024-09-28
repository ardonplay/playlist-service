package io.github.ardonplay.tactmuzik.playlistservice.config;

import io.github.ardonplay.tactmuzik.playlistservice.entity.TrackEntity;
import io.github.ardonplay.tactmuzik.playlistservice.repository.PlaylistRepository;
import io.github.ardonplay.tactmuzik.playlistservice.repository.PlaylistTrackBatchRepositoryImpl;
import io.github.ardonplay.tactmuzik.playlistservice.repository.PlaylistTrackRepository;
import io.github.ardonplay.tactmuzik.playlistservice.repository.TrackRepository;
import java.util.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.ReactiveCassandraOperations;
import org.springframework.data.cassandra.core.mapping.CassandraPersistentEntity;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.MappingCassandraEntityInformation;

@Configuration
public class CassandraBatchConfig {

  @Bean
  public PlaylistTrackBatchRepositoryImpl playlistTrackBatchRepository(
      ReactiveCassandraOperations operations,
      PlaylistTrackRepository playlistTrackRepository
   ) {
    final CassandraPersistentEntity<?> entity =
            operations
            .getConverter()
            .getMappingContext()
            .getRequiredPersistentEntity(TrackEntity.class);
    final CassandraEntityInformation<TrackEntity, UUID> metadata =
        new MappingCassandraEntityInformation<>(
            (CassandraPersistentEntity<TrackEntity>) entity, operations.getConverter());
    return new PlaylistTrackBatchRepositoryImpl(metadata, operations, playlistTrackRepository);
  }
}



